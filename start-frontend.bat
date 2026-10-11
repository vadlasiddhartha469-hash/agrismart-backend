@echo off
title AgriSmart Frontend
cd /d "%~dp0frontend"
echo Installing frontend packages if needed...
if not exist node_modules npm.cmd install
echo Starting AgriSmart React frontend...
npm.cmd run dev
pause
