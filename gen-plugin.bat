@echo off
echo [INFO] 请保证已安装MVN及JDK

if exist generated-project (rmdir /s/q generated-project)
mkdir generated-project
cd generated-project



echo [INFO] 已在%cd%\generated-project下生成项目.

echo [INFO] 为新项目初始化依赖jar.
cd generated-project
for /D %%a in (*) do cd "%%a"
pause