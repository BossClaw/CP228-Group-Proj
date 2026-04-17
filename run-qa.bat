@echo off
echo Starting Pixel Vault in QA mode (MySQL + Docker)...
echo Press ENTER to continue
pause

docker-compose down
docker-compose up --build

pause
