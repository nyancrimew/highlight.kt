import hashlib
import os
import re
import shutil


def md5(fname):
    hash_md5 = hashlib.md5()
    with open(fname, "rb") as f:
        for chunk in iter(lambda: f.read(4096), b""):
            hash_md5.update(chunk)
    return hash_md5.hexdigest()


MANUAL_MAP = {
    'armasm': '1ddfd69d5b5807cbe5267f24b731d574',
    'arcade': '833ba17c4f7fe568576d7464b38990bf',
    'arduion': '0e2a1c2eeb594800015be691fea0a578',
    'stata': 'e503aab1ef4a0ecae2c005e51cee9f7f',
    'haxe': 'c6cfecdda00f7f9cd16d0640c5a4fb00',
    'stan': '3c2fd7077a3a306bdc3997997332a3d8',
    'twig': 'f74a14f620eba5879ada1a996262172d',
    'stylus': '48f6fdaf3175f31def6b3e6f550d0940',
    'scss': '0fdbb90a01af6a13c001c1055283d0ef',
    'scheme': 'ea5f497cccc18059f2c14814a96928a0',
    'scala': '8368a4d7200da2cfc014c30c75dcb7a0',
    # TODO: Fix parts of keyword map extraction
    'rust': '944a870df40c227ab39873c82bc14bbb',
    'routeros': '6caa650eda28855908c68c6586af7a5b',
    'purebasic': '4a988b296bf2a58d342d752e31de855c',
    'properties': 'c3a2ab075bc64a66c7e92bf798250736',
    'php': 'fd6f2fb76d231d04e0d173a34bd84c09',
    'pf': 'a3cee2511362adf55bd55e6a4bf71109',
    'objectivec': 'ade954d7efb59d8abeb3b9f70269cd30',
    'asciidoc': '268cb13ac07143dcc007c937a26d9e02',
    'clojure': '8c2e940809c0d6be003b78bee3505aef',
    'ceylon': '436e1781b0c28b573d94547a23d3ac7d',
    'cmake': '75d95bf89f0a73e08753207639de9fc1',
    'moonscript': '9732b778c9b889dba36926874a1ad448',
    'mercury': 'f63f64e46a56b69e35b0966301e7d214',
    'mathematica': '596ded5a455504f67e37dc0702ae30a7',
    'llvm': '3ceaf1220a58647d6f8aaa30cc817671',
    'json': 'e7c66d259be22095e1900dee8845b531',
    # TODO: handle multi var initialization
    'openscad': 'd4986c9572a30d4ae67139f584a34087',
    'autoit': '488b03d4a95baeba2e9368542c9c5ba6',
    # TODO: eat comments
    'cos': '0dfa54a85db8468163f4190e30aabc91',
    'n1ql': '35e76a8491bc73ed3432d55eb12c0cb0',
    'css': '4ba74836522ca0a3af0a5d5a556c392e',
    # TODO: figure out missing brackets / commas
    'nginx': '3eb6d23f437e2d916435764bc07fd412',
    'markdown': '9c568504b931710b0eb4a9202c3f107f',
    'julia': '9b14ae55800e16b74b1771b5bbf98e74',
    'gcode': '5b9a284d41a4ab4a7a9a8b89a7e092ca',
    'elm': '2d3cecc220a46550c065a4e02e6ee6f8',
    'cpp': '49ca5457a543a3a99247661fc33540eb',
    # TODO: fix string style keywords
    'javascript': '25eaaa93c794a55ca6973f622674dde2',
    # TODO: fix auto escaping
    'isbl': '5a372af48469608cc3d00602a9abef28',
    'hy': '4f9dca39aa5e54f181d4ac33a0ee683b',
    'haskell': '22dbe4aa16f2388ad23d35ebb1f9f77b',
    'groovy': '41805acf6c6c3722f0b8eb065b2fe0b8',
    'excel': '0b0b2432a052238fc76eb340445b0a92',
    'dts': '575328d7b4ccad223f396141689c8121',
    'd': '8703202cc851e4355d004a1090ba2232',
    'dart': '018cfe8db327f0274122a9f3c0041e01',
    'crmsh': '76246f2aaa3792d0a01b031b8adb54d7',
    # TODO: fix assignment conversion
    'cs': '655a3949a8d3e20b23d828760db9fea2',
    # ugh
    'abnf': '4a74d5edda09e836fb627b7fdc75baf9'
}

# Languages which we'll probably have to port manually
SKIP = [
    'reasonml',
    # This one is hella complex
    'pgsql',
    # ?!
    'less',
    'gauss',
    'crystal'
]

folderpath = "highlight.js/src/languages/"
outputpath = "../src/main/java/com/codewaves/codehighlight/languages/"
highlighterpath = "../src/main/java/com/codewaves/codehighlight/core/Highlighter.kt"
header = """package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.*

"""
try:
    shutil.copytree(outputpath, "temp/")
    shutil.rmtree(outputpath)
    os.mkdir(outputpath)
except Exception as e:
    print(e)

directory = os.fsencode(folderpath)

langmap = {}

for file in os.listdir(directory):
    filename = os.fsdecode(file)
    filepath = folderpath + filename
    language = filename[0:-3]
    if language in SKIP:
        print(f"{language} is currently unsupported. Skipping.")
        continue
    className = language
    classNameCode = className
    if '-' in className or re.match(r"^(\d.*)$", className):
        classNameCode = f"`{classNameCode}`"
    elif className == 'kotlin':
        classNameCode = "kt"
    tempfilepath = "temp/" + className + '.kt'
    outfilepath = outputpath + className + '.kt'
    langmap[language] = classNameCode
    print(f"Starting to parse {filename}")
    with open(folderpath + filename, 'r', encoding="utf8") as f:
        content = f.read()

    hash = md5(filepath)
    if language in MANUAL_MAP.keys():
        knownHash = MANUAL_MAP[language]
        if hash == knownHash:
            print(f"[{filename}] hasn't changed, copying previous kotlin version")
            shutil.copy2(tempfilepath, outfilepath)
            continue
        else:
            print(f"[{filename}] has changed({knownHash} -> {hash}), {className}.kt will require special attention")

    # Transform content to kotlin
    # 1. Add package definition and imports
    content = header + content
    # 2. Add classname
    classHeader = f"""/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin {filepath} MD5 <{hash}>
 */
internal fun {classNameCode}(): Mode """
    content = re.sub(r"function.*?\(hljs\)", classHeader, content)
    # 3. Return Mode(
    content = re.sub(r"(\s+)return {", "\g<1>return Mode(", content)
    # 4. Turn javascript vars into Kotlin val
    # content = re.sub(r"\svar\s", " val ", content)
    # 5. Turn regex literal into Kotlin raw string
    content = re.sub(r"([\s(\[,:])\/(?!\/)([^\*].*?)(?<!\/|(?<!\\)\\)\/[gimxa]*([\s;,})])",
                     "\g<1>\"\"\"\g<2>\"\"\"\g<3>", content)
    # 6. Turn string literals into Kotlin strings
    content = re.sub(r"\\'", "'", content)
    content = re.sub(r"([\s,;}(])'(.)\"'([\s,;}\)])", "\g<1>\"\g<2>\\\\\"\"\g<3>", content)
    content = re.sub(r"([\s,;}(])'\"'([\s,;}\)])", "\g<1>\"\\\\\"\"\g<2>", content)
    content = re.sub(r"([\s,;}(])'\"\"\"'([\s,;}\)])", "\g<1>\"\\\\\"\\\\\"\\\\\"\"\g<2>", content)
    content = re.sub(r"([\s,;}(])'(.)\"\"\"'([\s,;}\)])", "\g<1>\"\g<2>\\\\\"\\\\\"\\\\\"\"\g<3>", content)
    content = re.sub(r"'([\s,;}+)\]])", "\"\g<1>", content)
    content = re.sub(r"([\s(\[,:+=])'", "\g<1>\"", content)
    # 7. Turn concatenated strings into one long string
    content = re.sub(r"(?m)\"\s*?\+(\s|//.*$)*?\"", "", content)
    # 8. Add line breaks after assignments
    content = re.sub(r"^(\s*?).*?,\s+?(\w+?:)", ",\n\g<1>\g<2>", content)
    # 8. Convert Keyword map initializers
    p = re.compile(
        r"(?ims)(keywords|built_?ins|literals)\$?(\s*?[:=]\s+){((?:(?://[^\n]*?|\s)|Keyword\([^\)]*?\),)*?)((?://[^\n]*?$|\s)*)['\"]?([\w-]+?)['\"]?:((?://[^\n]*?|\s|[^},])+)(.*?)}($|[,;)])")
    while p.search(content):
        content = p.sub("\g<1>$\g<2>{\g<3>\g<4>Keyword(className = \"\g<5>\", value = \g<6>)\g<7>}\g<8>", content)
    content = re.sub(r"(?ims)(keywords|built_?ins|literals)\$(\s*?[=:]\s+){", "\g<1>\g<2>listOf(", content)
    # 9. Turn array initializers into Kotlin list initializers
    content = re.sub(r"([\s,;:}(])\[", "\g<1>listOf(", content)
    content = re.sub(r"]([\s,;}.)])", ")\g<1>", content)
    p = re.compile(r"\"(.*?)listOf\((.*?)](.*?)\"")
    while p.search(content):
        content = p.sub("\"\g<1>[\g<2>]\g<3>\"", content)
    # 10. Turn JSON property initialization into assignment
    p = re.compile(r"(?m)(^(?:[^\n](?!//))+?[\w-]+) ?:\s?")
    while p.search(content):
        content = p.sub("\g<1> = ", content)
    # 11. Convert keyword string initializers
    content = re.sub(r"(?m)((?<!val) +|^)(\w*[kK]eywords) =(\s+)(?!(?:Keyword|listOf)\()(.*?)($|[,;}])",
                     "\g<1>\g<2> =\g<3>keywords(\g<4>)\g<5>", content)
    # 12. Assume all left over curly blocks are Mode initializers
    content = re.sub(r"(?m)(^|(?://.*$|[=,\(]|\*/)\s*?){", "\g<1>Mode(", content)
    # 13. Close all initializers
    p = re.compile(r"(?ms)}(\s*?[,);}/]|$.*?})")
    while p.search(content):
        content = p.sub(")\g<1>", content)
    # 14. Clean up some general stuff
    content = re.sub(r"=\s+\"", "= \"", content)
    content = re.sub(r"=\s+\"\n\s+", "= \"", content)
    content = re.sub(r", (\w+?) =\s+([^\s])", ",\n \g<1> = \g<2>", content)
    content = re.sub(r"\)\.(\w+)", ")\n.\g<1>", content)
    content = re.sub(r"^(\s*?).*?\"\"\"\n\s*?([^\s])", "\"\"\"\n\g<1>\g<2>", content)
    content = re.sub(r"(?s)\n\s*?\"\"\"", "\"\"\"", content)
    content = re.sub(r"\"\"\"(.*)\"\"\".trimIndent\(\)", "\"\"\"\g<1>\"\"\"", content)
    p = re.compile(r"(^(?:(?<!//).)*?\"{1}\)?)((?:\s*?\+)|,)( +)([^\s])")
    while p.search(content):
        content = p.sub("\g<1>\g<2>\n\g<3>\g<4>", content)
    content = re.sub(r"([\s(\[,:])\"\\\"([,);/])", "\g<1>\"\\\\\"\g<2>", content)
    # content = re.sub(r"(?s)\"([^$]*?)\$([^$]*?)\"", "\"\g<1>${\'$\'}\g<2>\"", content)
    p = re.compile(r"(?<!\")\"(?!\"{2})(.*?)((?<!\\)[\"$])(.*)(?<!\")\"(?!\"{2})")
    while p.search(content):
        content = p.sub("\"\g<1>\\\\\g<2>\g<3>\"", content)
    content = re.sub(r"(?<!\"{1})\"(?!\"{2})(.*?)((?<=\\\\\\)[\"$])(.*)\"(?!\"{2})", "\"\g<1>\\\\\g<2>\g<3>\"", content)
    p = re.compile(r"(?s)(?<!\")\"{3}(?!\")(.*?)((?<!\${')[$](?!{'))(.*)(?<!\")\"{3}(?!\")")
    while p.search(content):
        content = p.sub("\"\"\"\g<1>${'\g<2>'}\g<3>\"\"\"", content)
    content = re.sub(r"\s\"\"\"\"([\s,);}/])", " \"\\\"\\\"\"\g<1>", content)
    # content = re.sub(r"(?<!\")\"\\\"", " \"\\\"", content)
    content = re.sub(r"(?m),((?:\s|//.*)*)\)", "\g<1>)", content)
    content = re.sub(r"\"self\"(\s*[,\)])", "hljs.SELF\g<1>", content)
    content = re.sub(r"\[(\d+)\)", "[\g<1>]", content)
    content = re.sub(r"\n\n", "\n", content)
    # 15. Replace some common js functions
    content = re.sub(r"\.concat\(([^\)]*)\)", " + \g<1>", content)
    content = re.sub(r"\.push\(([^\)]*)\)", " += \g<1>", content)
    content = re.sub(r"\.join\(", ".joinToString(", content)
    content = re.sub(r"(?s)map\(function\((.*?)\){\s*?return\s*(.*?)\)\)", "map { \g<1> -> \g<2> }", content)
    # 16. Remove our custom escapes
    content = re.sub(r"\\:", ":", content)

    # Write the new class
    with open(outfilepath, 'w', encoding="utf8") as f:
        f.write(content)

registerCode = ""
for lang in langmap.keys():
    registerCode += f"            registerLanguage(\"{lang}\", {langmap[lang]}());\n"
registerCode = registerCode[0:-1]

with open(highlighterpath, 'r', encoding="utf8") as f:
    current = f.read()

with open(highlighterpath, 'w', encoding="utf8") as f:
    content = f"""//START
{registerCode}
//END"""
    new = re.sub(r"(?sm)^\s*?//START.*?//END\s*?$", content, current)
    f.write(new)

try:
    shutil.rmtree("temp/")
except Exception as e:
    print(e)
