call runcrud
if "%ERRORLEVEL%" == "0" goto openfirefox
echo.
echo RUN CRUD has errors - breaking work
goto fail

:openfirefox
cd "C:\Program Files\Mozilla Firefox\
start firefox.exe http://localhost:8080/crud/v1/task/getTasks
goto end

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.