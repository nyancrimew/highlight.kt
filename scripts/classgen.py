import hashlib
import os
import re
import shutil
from textwrap import dedent

def md5(fname):
    hash_md5 = hashlib.md5()
    with open(fname, "rb") as f:
        for chunk in iter(lambda: f.read(4096), b""):
            hash_md5.update(chunk)
    return hash_md5.hexdigest()

MANUAL_MAP = {
    'arduino': '0e2a1c2eeb594800015be691fea0a578',
    'armasm': '1ddfd69d5b5807cbe5267f24b731d574',
    'abnf': '4a74d5edda09e836fb627b7fdc75baf9',
    'crmsh': '76246f2aaa3792d0a01b031b8adb54d7',
    'routeros': '6caa650eda28855908c68c6586af7a5b',
    'objectivec': 'ade954d7efb59d8abeb3b9f70269cd30',
    'hy': '4f9dca39aa5e54f181d4ac33a0ee683b',
    'julia': '9b14ae55800e16b74b1771b5bbf98e74',
    'mercury': 'f63f64e46a56b69e35b0966301e7d214',
    'moonscript': '9732b778c9b889dba36926874a1ad448',
    'n1ql': '35e76a8491bc73ed3432d55eb12c0cb0',
    'pf': 'a3cee2511362adf55bd55e6a4bf71109',
    'scala': '8368a4d7200da2cfc014c30c75dcb7a0',
    'scheme': 'ea5f497cccc18059f2c14814a96928a0',
    'twig': 'f74a14f620eba5879ada1a996262172d',
    'lua': 'b2916995c0ab9af36d9706ff801a98a0',
    'livescript': 'cfa609857ea99514348b49c7670c2d16',
    'lisp': 'ceb2e573e0745639aeeea1784382d8dd',
    'openscad': 'd4986c9572a30d4ae67139f584a34087',
    'parser3': 'af8de20e7ac586dac61005a9e2967ca7',
    'arcade': '833ba17c4f7fe568576d7464b38990bf',
    'coffeescript': 'c636bce2955fe93d354d5f371a864e1f',
    'http': '4299339f060ed2fd48a7d9bfb3b71a29',
    'isbl': '5a372af48469608cc3d00602a9abef28',
    'ruby': '8c93ef1e789f9fd59b8995339236901d',
    'stylus': '48f6fdaf3175f31def6b3e6f550d0940',
    'subunit': '1b63b50b36c86db99202ad74d99bf17f',
    'vbnet': '19a6048635fedbb413732ecc85e8ade1',
    'xml': '8d1fe692045603803f24f0f60fd6f450',
    'ceylon': '436e1781b0c28b573d94547a23d3ac7d',
    # TODO: fix assignment conversion
    'cs': '655a3949a8d3e20b23d828760db9fea2',
    # TODO: get rid of extra closing brackets with concat
    'cpp': '49ca5457a543a3a99247661fc33540eb',
    # TODO: improve end bracket regex to account for comments
    'purebasic': '4a988b296bf2a58d342d752e31de855c',
    'plaintext': '0c27fb547d7eb6bcb56f0b09c14dffbe',
    'properties': 'c3a2ab075bc64a66c7e92bf798250736',
    # Ensure Mode( doesn't get added inside strings
    'markdown': '9c568504b931710b0eb4a9202c3f107f',
    'powershell': 'eeefb299ee3b9e7997b0964ef8bfc2dc',
    'rust': '944a870df40c227ab39873c82bc14bbb'
}

# Languages which we'll probably have to port manually
SKIP = [
    'reasonml',
    # This one is hella complex
    'pgsql',
    # ?!
    'less',
    'gauss',
    'crystal',
    # This one is literally impossible as the keywords string is far too long
    'mathematica'
]

folderpath = "highlight.js/src/languages/"
outputpath = "../src/main/java/ch/deletescape/highlight/languages/"
highlighterpath = "../src/main/java/ch/deletescape/highlight/core/Highlighter.kt"
header = dedent("""\
            package ch.deletescape.highlight.languages

            import ch.deletescape.highlight.core.*

""")
try:
    shutil.copytree(outputpath, "temp/")
    shutil.rmtree(outputpath)
    os.mkdir(outputpath)
except Exception as e:
    print(e)

directory = os.fsencode(folderpath)

langmap = {}

# useful regex parts:
# Ignore comment contents: (^(?:(?<!//).)*)
try:
    #TODO: remove useless escapes again
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
        classHeader = dedent(f"""\
                                 /**
                                  * This function was automatically generated, avoid directly editing it if possible!
                                  * Origin {filepath} MD5 <{hash}>
                                  */
                                 internal fun {classNameCode}(): Mode """)
        content = re.sub(r"function.*?\(hljs\)", classHeader, content)
        # 3. Return Mode(
        content = re.sub(r"(\s+)return {", "\g<1>return Mode(", content)
        # 4. Turn javascript vars into Kotlin val
        # content = re.sub(r"\svar\s", " val ", content)
        # 7. Turn concatenated strings into one long string
        content = re.sub(r"(?m)['\"]\s*?\+(\s|/[/\*].*$)*?['\"]", "", content)
        # 8. Add missing escapes
        # Escape "${}
        content = re.sub(r"\"'\"", "'\''", content)
        content = re.sub(r"\"''\"", "'\'\''", content)
        content = re.sub(r"\"'''\"", "'\'\'\''", content)
        p = re.compile(r"((?<!\\)'.*?)(?<=(?:(?<=[^\\]\\)\\|[^\\]))([\"$])(.*?(?<!\\)')")
        while p.search(content):
            content = p.sub(r"\g<1>\\\g<2>\g<3>", content)
        p = re.compile(r"((?<!\\)'.*?)(?<=(?:(?<=[^\\]\\)\\|[^\\]))({(?![\d'](?=.*}))|(?<=[^\d'])})(.*?(?<!\\)')")
        while p.search(content):
            content = p.sub(r"\g<1>\\\g<2>\g<3>", content)
        # Escape illegal escapes
        p = re.compile(r"((?<!\\)'.*?)(?<=(?:[^\\]\\|(?<=[^\\]\\)\\\\))([sSx%:@\-{}<>/?(). &!#])(.*?(?<!\\)')")
        while p.search(content):
            content = p.sub(r"\g<1>\\\g<2>\g<3>", content)
        # 8. Convert chain instantiation to multiple variables
        p = re.compile(r"(?m)( *var +?[\w\-]+ = (?:{[^\r]*?}|(?:[^,]|[,](?! *$))*?)),( *$\s+)([\w\-]+ +?=(?: *?{[^\r]*?}|(?:[^,;]|[,;](?! *$))*?)[,;] *$)")
        while p.search(content):
            content = p.sub("\g<1>;\g<2>var \g<3>", content)
        # 8. Convert Keyword map initializers
        p = re.compile(
            r"(?ims)(keywords|built_?ins|literals|_reserved)\$?(\s*?[:=]\s+){((?:(?://[^\n]*?|\s)|Keyword\([^\)]*?\),)*?)((?://[^\n]*?$|\s)*)['\"]?([\w-]+?)['\"]? ?:((?://[^\n]*?|\s|[^},])+)(.*?)}($|[,;)])")
        while p.search(content):
            content = p.sub("\g<1>$\g<2>{\g<3>\g<4>Keyword(className = \"\g<5>\", value = \g<6>)\g<7>}\g<8>", content)
        content = re.sub(r"(?ims)(keywords|built_?ins|literals|_reserved)\$(\s*?[=:]\s+){", "\g<1>\g<2>listOf(", content)
        # 11. Convert keyword string initializers
        content = re.sub(r"(?m)((?<!val) *|^)(\w*[kK]eywords) ?[=:](\s+)(?!(?:Keyword|listOf)\()(.*?)($|[,;}])",
                         "\g<1>\g<2> =\g<3>keywords(\g<4>)\g<5>", content)
        # 3. Add line breaks after assignments
        p = re.compile(r"(?m)(^(?:(?<!//.)*?,))[ \t]*?([\w\-'\"]+?\s?[:=+])")
        while p.search(content):
            content = p.sub("\g<1>\n\g<2>", content)
        p = re.compile(r"(?m)(^(?:(?<!//).)*?[\"']\)?)((?:\s*?\+)|,)( +)([^\s])")
        while p.search(content):
            content = p.sub("\g<1>\g<2>\n\g<3>\g<4>", content)
        p = re.compile(r"(^(?:(?<!//).)*?',) *?'")
        while p.search(content):
            content = p.sub("\g<1>',\n'", content)
        # 5. Turn regex literal into Kotlin raw string
        p = re.compile(r"([\s(\[,:])\/(?!\/)([^\*].*?)(?<!(?<!\\)\/|(?<!\\)\\)\/[gimxa]*([\s;,})])")
        while p.search(content):
            content = p.sub("\g<1>\"\"\"\g<2>\"\"\"\g<3>", content)
        # 6. Turn string literals into Kotlin strings
        content = re.sub(r"\\'", "'", content)
        content = re.sub(r"(?<=[\s(\[,:+=])'(.*)'(?=[\s,;}+)\]])", "\"\g<1>\"", content)
        # 9. Turn array initializers into Kotlin list initializers
        content = re.sub(r"([\s,;:}(])\[", "\g<1>listOf(", content)
        content = re.sub(r"]([\s,;}.)])", ")\g<1>", content)
        p = re.compile(r"\"(.*?)listOf\((.*?)](.*?)\"")
        while p.search(content):
            content = p.sub("\"\g<1>[\g<2>]\g<3>\"", content)
        # 10. Turn JSON property initialization into assignment
        p = re.compile(r"(?m)(^(?:[^\n](?!//))+?[\w-]+) ?: ?")
        while p.search(content):
            content = p.sub("\g<1> = ", content)
        # 12. Assume all left over curly blocks are Mode initializers
        content = re.sub(r"(?m)(^|(?://.*$|[=,\(]|\*/)\s*?){", "\g<1>Mode(", content)
        # 13. Close all initializers
        p = re.compile(r"(?ms)}(\s*?[,);}/]|$.*?})")
        while p.search(content):
            content = p.sub(")\g<1>", content)
        # 14. Clean up some general stuff
        p = re.compile(r"(?m)(^(?:(?<!//).)*=)=\s+\"")
        while p.search(content):
            content = p.sub("\g<1>= \"", content)
        p = re.compile(r"(?m)(^(?:(?<!//).)*=)\s+\"\n\s+")
        while p.search(content):
            content = p.sub("\g<1>= \"", content)
        p = re.compile(r"(?m)(^(?:(?<!//).)*,) (\w+?) =\s+([^\s])")
        while p.search(content):
            content = p.sub("\g<1>\n \g<2> = \g<3>", content)
        p = re.compile(r"(^(?:(?<!//).)*\)), (\w+)")
        while p.search(content):
            content = p.sub("\g<1>, \n\g<2>", content)
        content = re.sub(r"^( *?).*?\"\"\"\n\s*?([^\s])", "\"\"\"\n\g<1>\g<2>", content)
        content = re.sub(r"\n *?\"\"\"", "\"\"\"", content)
        content = re.sub(r"\"\"\"(.*)\"\"\".trimIndent\(\)", "\"\"\"\g<1>\"\"\"", content)
        p = re.compile(r"(?s)(?<!\")\"{3}(?!\")(.*?)((?<!\${')[$](?!{'))(.*)(?<!\")\"{3}(?!\")")
        while p.search(content):
            content = p.sub("\"\"\"\g<1>${'\g<2>'}\g<3>\"\"\"", content)
        content = re.sub(r"\s\"\"\"\"([\s,);}/])", " \"\\\"\\\"\"\g<1>", content)
        content = re.sub(r"(?m),((?:\s|//[^\n]*$)*)\)", "\g<1>)", content)
        content = re.sub(r"\"self\"(\s*[,\)])", "hljs.SELF\g<1>", content)
        content = re.sub(r"\[(\d+)\)", "[\g<1>]", content)
        content = re.sub(r"(?m)(^(?:(?<!//).)*)\n\n", "\g<1>\n", content)
        # 15. Replace some functions used
        content = re.sub(r"\.concat\(([^\)]*)\)", " + \g<1>", content)
        content = re.sub(r"\.push\(([^\)]*)\)", " += \g<1>", content)
        content = re.sub(r"\.join\(", ".joinToString(", content)
        content = re.sub(r"\.reverse\(", ".reversed(", content)
        content = re.sub(r"(?s)map\(function\((.*?)\) ?{\s*?return\s*(.*?)\)\)", "map { \g<1> -> \g<2> }", content)
        content = re.sub(r"(?s)forEach\(function\((.*?)\) ?{(.*?)\)\)", "forEach { \g<1> -> \g<2> }", content)
        content = re.sub(r"(?m)\+= ?((?:[\w-]+(?:, ?))*[\w-]+)(;?\s*$)", "+= listOf(\g<1>)\g<2>", content)
        content = re.sub(r"([\w-]+).keywords.keyword", "\g<1>.keywords.map { it.value }", content)
        content = re.sub(r"\\\${'\$", "${'$", content)
        # 17. Remove our custom escapes
        content = re.sub(r"\\:", ":", content)

        # Write the new class
        with open(outfilepath, 'w', encoding="utf8") as f:
            f.write(content)
except Exception as e:
    print(e)
    shutil.rmtree(outputpath)
    shutil.copytree("temp/", outputpath)
registerCode = ""
cpp = langmap["cpp"]
if cpp:
    # While languages are not lazily loaded yet we need to ensure that cpp is loaded before arduino
    registerCode += f"            registerLanguage(\"cpp\", {cpp}())\n"
# Sort the languages to ensure stable behavior
for lang in sorted(langmap.keys()):
    if lang != "cpp":
        registerCode += f"            registerLanguage(\"{lang}\", {langmap[lang]}())\n"
registerCode = registerCode[0:-1]

with open(highlighterpath, 'r', encoding="utf8") as f:
    current = f.read()

with open(highlighterpath, 'w', encoding="utf8") as f:
    content = f"""            //START
{registerCode}
            //END"""
    new = re.sub(r"(?sm)^\s*?//START.*?//END\s*?$", content, current)
    f.write(new)

try:
    shutil.rmtree("temp/")
except Exception as e:
    print(e)
