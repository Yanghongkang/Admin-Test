#!/bin/bash

echo "[INFO] Package the war in target dir."

cd ..

mvn clean package -Dmaven.test.skip=true
# mvn clean package -Dmaven.test.skip=true -P dev
cd bin
