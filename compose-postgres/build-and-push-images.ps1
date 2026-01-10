param(
    [string]$Tag = "v1"
)

$acrName = "northwindsacr"

Write-Host "=== Building and Pushing Images ===" -ForegroundColor Cyan

# Login to ACR
Write-Host "`nLogging into ACR..." -ForegroundColor Yellow
az acr login --name $acrName

# Build and push Postgres
Write-Host "`nBuilding Postgres image..." -ForegroundColor Yellow
docker build -t "$acrName.azurecr.io/northwinds-postgres:$Tag" -f compose-postgres/postgres/Dockerfile ./compose-postgres/postgres
docker push "$acrName.azurecr.io/northwinds-postgres:$Tag"
Write-Host "Pushed northwinds-postgres:$Tag" -ForegroundColor Green

# Build and push App
Write-Host "`nBuilding App image..." -ForegroundColor Yellow
./gradlew build
docker build -t "$acrName.azurecr.io/northwinds-app:$Tag" -f compose-postgres/northwinds-app/Dockerfile .
docker push "$acrName.azurecr.io/northwinds-app:$Tag"
Write-Host "Pushed northwinds-app:$Tag" -ForegroundColor Green

Write-Host "`n=== Images Pushed ===" -ForegroundColor Green
Write-Host "  $acrName.azurecr.io/northwinds-postgres:$Tag"
Write-Host "  $acrName.azurecr.io/northwinds-app:$Tag"