docker stop northwinds-postgres
docker rm northwinds-postgres
docker build -f northwinds-postgres -t northwinds-postgres:latest .
docker run -d --name northwinds-postgres --env-file .\secrets.env -p 5432:5432 northwinds-postgres:latest

