#!/bin/sh

if [[ `uname -s` =~ (CYGWIN|MINGW32|MSYS)* ]]; then
    echo "Running on Windows, setting python3 alias"
    alias python3="py -3"
fi

if [ ! -d "bin/" ]; then
    echo "Downloading required tools"
    mkdir bin/
fi

if [ ! -f "bin/ktlint" ]; then
    echo "Setting up ktlint"
    curl -sSLO https://github.com/pinterest/ktlint/releases/download/0.34.2/ktlint && chmod a+x ktlint && mv ktlint bin/
fi

if [ ! -d "highlight.js" ]; then
    echo "Cloning highlight.js"
    git clone git@github.com:highlightjs/highlight.js.git
else
    echo "Updating highlight.js"
    git -C highlight.js fetch
    git -C highlight.js reset --hard origin/master
fi

echo "Updating test resources from highlight.js"
rm -rf ../src/test/resources/*
cp -r highlight.js/test/detect ../src/test/resources/detect/
cp -r highlight.js/test/markup ../src/test/resources/markup/

echo "Generating language classes"
python3 classgen.py

echo "Formatting generated classes"
./bin/ktlint --experimental -F "../src/main/java/com/codewaves/codehighlight/languages/*.kt"