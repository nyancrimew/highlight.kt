import os
import re
import shutil

folderpath = "highlight.js/src/languages/"
outputpath = "../src/main/java/com/codewaves/codehighlight/languages/"
highlighterpath = "../src/main/java/com/codewaves/codehighlight/core/Highlighter.kt"
header = """package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

"""

shutil.rmtree(outputpath)
os.mkdir(outputpath)

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
    content = re.sub(r"\B\/(.+?)\/([\s;,}])", "\"\"\"\g<1>\"\"\"\g<2>", content)
    # 6. Turn string literals into Kotlin strings
    content = re.sub(r"(\s)'\"'([\s,;}])", "\g<1>'\\\"'\g<2>", content)
    content = re.sub(r"(\s)'\"\"\"'([\s,;}])", "\g<1>'\\\"\\\"\\\"'\g<2>", content)
    content = re.sub(r"'([\s,;}+)\]])", "\"\g<1>", content)
    content = re.sub(r"([\s(\[,:])'", "\g<1>\"", content)
    # 7. Turn concatenated strings into one long string
    content = re.sub(r"(?s)\"\s*?\+.*?\"", "", content)
    # 8. Add line breaks after assignments
    content = re.sub(r",\s+?(\w+?:)", ",\n\g<1>", content)
    # 8. Convert Keyword map initializers
    content = re.sub(r"(?ism)(eywords(?:\s*?[:=])[^\n]+?){(.+?)}([\s,]?)", "\g<1>keywordsJson(\"\"\"\g<2>\"\"\".trimIndent())\g<3>", content)
    # 9. Turn array initializers into Kotlin list initializers
    content = re.sub(r"([\s,;}(])\[", "\g<1>listOf(", content)
    content = re.sub(r"]([\s,;}.)])", ")\g<1>", content)
    # 10. Turn JSON property initialization into assignment
    content = re.sub(r"([a-z\s]):\s", "\g<1> = ", content)
    # 11. Properly initialize keywords from strings
    content = re.sub(r"eywords =\s+?(.+?)(\s*?(?:$|[,;}])?)", "eywords = keywords(\g<1>)\g<2>", content)
    # 12. Assume all left over curly blocks are Mode initializers
    content = re.sub(r"(?m)(^|(?://.*$|[=,\(])\s*?){", "\g<1>Mode(", content)
    # 13. Close all initializers
    content = re.sub(r"(?ms)}(\s*?[,);}/]|$.*?})", ")\g<1>", content)
    content = re.sub(r"(?ms)}(\s*?[,);}/]|$.*?})", ")\g<1>", content)
    # 14. Remove unneccesary semicolons
    content = re.sub(r"(?m);$", "", content)
    # 15. Clean up some genral stuff
    content = re.sub(r"=\s+\"", "= \"", content)
    content = re.sub(r"\"\"\"\n\s*?([^\s])", "\"\"\"\n\g<1>", content)
    content = re.sub(r"(?s)\n\s*?\"\"\"", "\"\"\"", content)
    content = re.sub(r"\"\"\"(.*)\"\"\".trimIndent\(\)", "\"\"\"\g<1>\"\"\"", content)
    content = re.sub(r"(?s)\"([^$]*?)\$([^$]*?)\"", "\"\g<1>${\'$\'}\g<2>\"", content)
    content = re.sub(r"\s\"\"\"\"([\s,);}/])", " \"\\\"\\\"\"\g<1>", content)
    content = re.sub(r"\s\"\"([^\"])", " \"\\\"\g<1>", content)
    content = re.sub(r"(?s),(\s*?\))", "\g<1>", content)
    # TODO: check how we can actually implement this in Highlight.kt
    content = re.sub(r"\"self\",", "", content)
    # 16. Replace some common js functions
    content = re.sub(r"\.concat\(", " + ", content)
    content = re.sub(r"\.join\(", ".joinToString(", content)
    # 17. Remove our custom escapes
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