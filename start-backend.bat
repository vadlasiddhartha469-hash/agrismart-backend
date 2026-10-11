@echo off
title AgriSmart Backend
cd /d "%~dp0backend"
echo Starting AgriSmart Spring Boot backend...
mvnw.cmd spring-boot:run
pause
