@echo off
echo [INFO] Package the war in target dir.

cd %~dp0
cd ..
call mvn clean package -Dmaven.test.skip=true
# mvn clean package -Dmaven.test.skip=true -P dev
cd bin
pause