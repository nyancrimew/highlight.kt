#!/bin/sh

#rm -rf highlight.js
#git clone git@github.com:highlightjs/highlight.js.git
rm -rf ../src/test/resources/*
cp -r highlight.js/test/detect ../src/test/resources/detect/
cp -r highlight.js/test/markup ../src/test/resources/markup/
python3 classgen.py