@echo off
title AgriSmart - Start All
color 0A
echo ==========================================
echo       AGRISMART FULL-STACK STARTUP
echo ==========================================
echo.
echo Make sure MySQL is running first.
echo.
start "AgriSmart Backend" cmd /k "cd /d %~dp0backend && mvnw.cmd spring-boot:run"
timeout /t 6 /nobreak >nul
start "AgriSmart Frontend" cmd /k "cd /d %~dp0frontend && if not exist node_modules npm.cmd install && npm.cmd run dev"
echo.
echo Backend:  http://localhost:8080
 echo Frontend: open the Local URL shown by Vite (normally http://localhost:5173)
echo.
pause
