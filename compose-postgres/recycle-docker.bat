# one-time or when schema changes
# docker compose --env-file secrets.env down -v
# docker compose --env-file secrets.env build --no-cache
# docker compose --env-file secrets.env up -d
docker build -t northwinds-postgres .

docker run -d --name northwinds --env-file secrets.env -p 5432:5432 northwinds-postgres