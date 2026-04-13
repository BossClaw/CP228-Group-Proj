@echo off
echo Starting Pixel Vault in DEV mode (H2)...
set SPRING_PROFILES_ACTIVE=dev
mvnw.cmd spring-boot:run
