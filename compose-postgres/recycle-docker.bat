docker stop northwinds-postgres
docker rm northwinds-postgres
docker build -f northwinds-postgres -t northwinds-postgres:latest .
docker run -d --name northwinds-postgres --env-file .\secrets.env -p 5432:5432 northwinds-postgres:latest

docker compose -f postgres-compose up --build

./pull-secrets-to-file.ps1
docker compose -f postgres-compose.yml down -v
docker compose -f postgres-compose.yml build --no-cache
docker compose -f postgres-compose.yml --env-file ./secrets.env up

docker compose -f northwinds-compose.yml down -v
docker compose -f northwinds-compose.yml build --no-cache
docker compose -f northwinds-compose.yml --env-file ./secrets.env up