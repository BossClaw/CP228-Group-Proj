@echo off
echo Starting Pixel Vault in QA mode (MySQL + Docker)...
docker-compose down
docker-compose up --build
