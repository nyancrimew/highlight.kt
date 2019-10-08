import os
import re
import shutil

folderpath = "highlight.js/src/languages/"
outputpath = "../src/main/java/com/codewaves/codehighlight/languages/"
highlighterpath = "../src/main/java/com/codewaves/codehighlight/core/Highlighter.kt"
header = """package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

"""
try:
    shutil.rmtree(outputpath)
    os.mkdir(outputpath)
except Exception as e:
    print(e)

directory = os.fsencode(folderpath)

langmap = {}

for file in os.listdir(directory):
    filename = os.fsdecode(file)
    print(f"Starting to parse {filename}")
    language = filename[0:-3]
    sanitizedName = language[:1].upper() + language[1:]
    sanitizedName = re.sub(r"[-]", "", sanitizedName)
    className = sanitizedName + "Language"
    classNameCode = re.sub(r"^(\d.*)$", "`\g<1>`", className)
    langmap[language] = classNameCode
    with open(folderpath + filename, 'r', encoding="utf8") as f:
        content = f.read()

    # Transform content to kotlin
    # 1. Add package definition and imports
    content = header + content
    # 2. Add classname
    classHeader = f"""/**
 * This class is automatically generated, avoid directly editing it if possible!
 */
class {classNameCode} \: LanguageBuilder """
    content = re.sub(r"function.*?\(hljs\)", classHeader, content)
    # 3. Turn return into override
    content = re.sub(r"\sreturn {", " override fun build() = {", content)
    # 4. Turn javascript vars into Kotlin val 
    content = re.sub(r"\svar\s", " val ", content)
    # 5. Turn regex literal into Kotlin raw string
    content = re.sub(r"([\s(\[,:])\/(?!\/)([^\*].*?)\/([\s;,})])", "\g<1>\"\"\"\g<2>\"\"\"\g<3>", content)
    # 6. Turn string literals into Kotlin strings
    content = re.sub(r"(\s)'\"'([\s,;}])", "\g<1>'\\\"'\g<2>", content)
    content = re.sub(r"(\s)'\"\"\"'([\s,;}])", "\g<1>'\\\"\\\"\\\"'\g<2>", content)
    content = re.sub(r"'([\s,;}+)\]])", "\"\g<1>", content)
    content = re.sub(r"([\s(\[,:+=])'", "\g<1>\"", content)
    # 7. Turn concatenated strings into one long string
    content = re.sub(r"(?m)\"\s*?\+(\s|//.*$)*?\"", "", content)
    # 8. Add line breaks after assignments
    content = re.sub(r"^(\s*?).*?,\s+?(\w+?:)", ",\n\g<1>\g<2>", content)
    # 8. Convert Keyword map initializers
    p = re.compile(r"(?ims)(eywords)\$?(\s*?[:=]\s+){((?:(?://[^\n]*?|\s)*?Keyword\(.*?\),)*?)((?://[^\n]*?|\s)*)['\"]?([\w-]+?)['\"]?:((?://[^\n]*?|\s|[^,}])+)(.*?)}($|[,;)])")
    while p.search(content):
        content = p.sub("\g<1>$\g<2>{\g<3>\g<4>Keyword(className = \"\g<5>\", value = \g<6>)\g<7>}\g<8>", content)
    content = re.sub(r"(?ims)(eywords)\$(\s*?[=:]\s+){", "\g<1>\g<2>listOf(", content)
    #content = re.sub(r"(?is)(eywords)\s?:(\s+){\s*?['\"]?(.+?)['\"]?:\s*([^,]+?)}($|[,;)])", "\g<1>:\g<2>Keyword(className = \"\g<3>\", value = \g<4>)\g<5>", content)
    #content = re.sub(r"(?ism)(eywords(?:\s*?[:=])[^\n]+?){(.+?)}([\s,]?)", "\g<1>keywordsJson(\"\"\"\g<2>\"\"\".trimIndent())\g<3>", content)
    # 9. Turn array initializers into Kotlin list initializers
    content = re.sub(r"([\s,;}(])\[", "\g<1>listOf(", content)
    content = re.sub(r"]([\s,;}.)])", ")\g<1>", content)
    # 10. Turn JSON property initialization into assignment
    content = re.sub(r"([a-z\s]):\s", "\g<1> = ", content)
    # 11. Properly initialize keywords from strings
    content = re.sub(r"eywords =(\s+)(?!(?:Keyword|keywordsJson)\()(.*)($|[,;}])", "eywords =\g<1>keywords(\g<2>)\g<3>", content)
    # 12. Assume all left over curly blocks are Mode initializers
    content = re.sub(r"(?m)(^|(?://.*$|[=,\(]|\*/)\s*?){", "\g<1>Mode(", content)
    # 13. Close all initializers
    content = re.sub(r"(?ms)}(\s*?[,);}/]|$.*?})", ")\g<1>", content)
    content = re.sub(r"(?ms)}(\s*?[,);}/]|$.*?})", ")\g<1>", content)
    # 14. Clean up some general stuff
    content = re.sub(r"=\s+\"", "= \"", content)
    content = re.sub(r"=\s+\"\n\s+", "= \"", content)
    content = re.sub(r", (\w+?) =\s+([^\s])", ",\n \g<1> = \g<2>", content)
    content = re.sub(r"^(\s*?).*?\"\"\"\n\s*?([^\s])", "\"\"\"\n\g<1>\g<2>", content)
    content = re.sub(r"(?s)\n\s*?\"\"\"", "\"\"\"", content)
    content = re.sub(r"\"\"\"(.*)\"\"\".trimIndent\(\)", "\"\"\"\g<1>\"\"\"", content)
    content = re.sub(r"\"{1}((?:\s*?\+)|,)(\s+)([^\s])", "\"\g<1>\n\g<2>\g<3>", content)
    content = re.sub(r"([\s(\[,:])\"\\\"([,);/])", "\g<1>\"\\\\\"\g<2>", content)
    #content = re.sub(r"(?s)\"([^$]*?)\$([^$]*?)\"", "\"\g<1>${\'$\'}\g<2>\"", content)
    p = re.compile(r"(?<!\"{1})\"(?!\"{2})(.*?)((?<!\\)[\"$])(.*)\"(?!\"{2})")
    while p.search(content):
        content = p.sub("\"\g<1>\\\\\\g<2>\g<3>\"", content)
    p = re.compile(r"\"{3}(.*?)((?<!\${')[$](?!{'))(.*)\"{3}")
    while p.search(content):
        content = p.sub("\"\"\"\g<1>${'\g<2>'}\g<3>\"\"\"", content)
    content = re.sub(r"\s\"\"\"\"([\s,);}/])", " \"\\\"\\\"\"\g<1>", content)
    content = re.sub(r"\s\"\"([^\"])", " \"\\\"\g<1>", content)
    content = re.sub(r"(?m),((?:\s|//.*)+)\)", "\g<1>)", content)
    # TODO: check how we can actually implement this in Highlight.kt
    content = re.sub(r"\"self\",", "", content)
    content = re.sub(r"\[(\d+)\)", "[\g<1>]", content)
    # 15. Replace some common js functions
    content = re.sub(r"\.concat\(", " + ", content)
    content = re.sub(r"\.join\(", ".joinToString(", content)
    content = re.sub(r"map\(function\((.*?)\){\s*?return\s*(.*?)\)\)", "map { \g<1> -> \g<2> }", content)
    # 16. Remove our custom escapes
    content = re.sub(r"\\:", ":", content)

    # Write the new class
    with open(outputpath + className + '.kt', 'w', encoding="utf8") as f:
        f.write(content)

registerCode = ""
for lang in langmap.keys():
    registerCode += f"            registerLanguage(\"{lang}\", {langmap[lang]}().build());\n"
registerCode = registerCode[0:-1]

with open(highlighterpath, 'r', encoding="utf8") as f:
    current = f.read()

with open(highlighterpath, 'w', encoding="utf8") as f:
    content = f"""//START
{registerCode}
//END"""
    new = re.sub(r"(?sm)^\s*?//START.*?//END\s*?$", content, current)
    f.write(new)