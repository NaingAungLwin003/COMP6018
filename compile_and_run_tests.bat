@echo off
echo Compiling source and test files...

REM Compile and output class files to current folder
javac -d . -cp .;.\junit-platform-console-standalone-1.10.2.jar src/battleShips/*.java test/battleShips/*.java

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo Compilation failed! Check your source files.
    pause
    exit /b
)

echo.
echo Running JUnit 5 tests...

java -jar .\junit-platform-console-standalone-1.10.2.jar --class-path . --scan-class-path --details tree

pause
