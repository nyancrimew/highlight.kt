import os
import re

folderpath = "highlight.js/src/languages/"
outputpath = "temp/"
header = """package com.codewaves.codehighlight.languages

import com.codewaves.codehighlight.core.Mode
import com.codewaves.codehighlight.core.Keyword
import com.codewaves.codehighlight.core.keywords

"""

directory = os.fsencode(folderpath)

for file in os.listdir(directory):
    filename = os.fsdecode(file)
    print(f"Starting to parse {filename}")
    language = filename[:3]
    className = language.capitalize() + "Language"
    with open(folderpath + filename, 'r', encoding="utf8") as f:
        content = f.read()

    # Transform content to kotlin
    # 0. Read Language name
    m = re.search(r"(?m)Language:(.+)$", content)
    if(m):
        language = m.group(1).split(',', 1)[0].split('(', 1)[0].split(' / ', 1)[0].strip()
        className = re.sub(r"\+", "Plus", language)
        className = re.sub(r"#", "Sharp", className)
        className = re.sub(r"é", "e", className)
        parts = re.split(r"[\s'/\-:.]", className)
        newParts = []
        for part in parts:
            newParts += part[:1].upper() + part[1:]
        className = "".join(newParts) + "Language"
    # 1. Add package definition and imports
    content = header + content
    # 2. Add classname
    content = re.sub(r"function\(hljs\)", f"class {className} : LanguageBuilder ", content)
    # 3. Turn return into override
    content = re.sub(r"\sreturn {", " override fun build() = {", content)
    # 4. Turn javascript vars into Kotlin val 
    content = re.sub(r"\svar\s", " val ", content)

    # Write the new class
    with open(outputpath + className + '.kt', 'w', encoding="utf8") as f:
        f.write(content)