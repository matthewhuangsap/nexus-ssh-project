@echo off
echo [INFO] �뱣֤�Ѱ�װMVN��JDK

if exist generated-project (rmdir /s/q generated-project)
mkdir generated-project
cd generated-project



echo [INFO] ����%cd%\generated-project��������Ŀ.

echo [INFO] Ϊ����Ŀ��ʼ������jar.
cd generated-project
for /D %%a in (*) do cd "%%a"
pause