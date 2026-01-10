param(
    [string]$Environment = "prod"
)

$resourceGroup = "rg-northwinds"
$location = "eastus"
$envName = "northwinds-env"
$acrName = "northwindsacr"
$vaultName = "kv-northwinds-$Environment"
$identityName = "northwinds-identity"

# Get identity ID
$identityId = az identity show `
  --name $identityName `
  --resource-group $resourceGroup `
  --query id `
  -o tsv

# Get client ID for Azure SDK
$clientId = az identity show `
  --name $identityName `
  --resource-group $resourceGroup `
  --query clientId `
  -o tsv

$vaultUrl = "https://$vaultName.vault.azure.net/secrets"

Write-Host "Identity ID: $identityId" -ForegroundColor Gray
Write-Host "Client ID: $clientId" -ForegroundColor Gray

Write-Host "Deploying Postgres container..." -ForegroundColor Cyan

az containerapp create `
  --name northwinds-postgres `
  --resource-group $resourceGroup `
  --environment $envName `
  --image "$acrName.azurecr.io/northwinds-postgres:v1" `
  --registry-server "$acrName.azurecr.io" `
  --registry-identity $identityId `
  --user-assigned $identityId `
  --target-port 5432 `
  --exposed-port 5432 `
  --ingress internal `
  --transport tcp `
  --min-replicas 1 `
  --secrets "postgres-user=keyvaultref:$vaultUrl/postgres-user,identityref:$identityId" "postgres-password=keyvaultref:$vaultUrl/postgres-password,identityref:$identityId" "postgres-db=keyvaultref:$vaultUrl/postgres-db,identityref:$identityId" "admin-db-user=keyvaultref:$vaultUrl/admin-db-user,identityref:$identityId" "admin-db-password=keyvaultref:$vaultUrl/admin-db-password,identityref:$identityId" "app-db-user=keyvaultref:$vaultUrl/app-db-user,identityref:$identityId" "app-db-password=keyvaultref:$vaultUrl/app-db-password,identityref:$identityId" "read-db-user=keyvaultref:$vaultUrl/read-db-user,identityref:$identityId" "read-db-password=keyvaultref:$vaultUrl/read-db-password,identityref:$identityId" `
  --env-vars "POSTGRES_USER=secretref:postgres-user" "POSTGRES_PASSWORD=secretref:postgres-password" "POSTGRES_DB=secretref:postgres-db" "ADMIN_DB_USER=secretref:admin-db-user" "ADMIN_DB_PASSWORD=secretref:admin-db-password" "APP_DB_USER=secretref:app-db-user" "APP_DB_PASSWORD=secretref:app-db-password" "READ_DB_USER=secretref:read-db-user" "READ_DB_PASSWORD=secretref:read-db-password"

Write-Host "Waiting for Postgres to be ready..." -ForegroundColor Yellow
Start-Sleep -Seconds 30

# Get Postgres internal URL
$dbHost = az containerapp show `
  --name northwinds-postgres `
  --resource-group $resourceGroup `
  --query "properties.configuration.ingress.fqdn" `
  -o tsv

Write-Host "Postgres available at: $dbHost" -ForegroundColor Green

Write-Host "Deploying App container..." -ForegroundColor Cyan

az containerapp create `
  --name northwinds-app `
  --resource-group $resourceGroup `
  --environment $envName `
  --image "$acrName.azurecr.io/northwinds-app:v1" `
  --registry-server "$acrName.azurecr.io" `
  --registry-identity $identityId `
  --user-assigned $identityId `
  --target-port 8080 `
  --ingress external `
  --min-replicas 1 `
  --secrets "postgres-user=keyvaultref:$vaultUrl/postgres-user,identityref:$identityId" "postgres-password=keyvaultref:$vaultUrl/postgres-password,identityref:$identityId" "postgres-db=keyvaultref:$vaultUrl/postgres-db,identityref:$identityId" "admin-db-user=keyvaultref:$vaultUrl/admin-db-user,identityref:$identityId" "admin-db-password=keyvaultref:$vaultUrl/admin-db-password,identityref:$identityId" "app-db-user=keyvaultref:$vaultUrl/app-db-user,identityref:$identityId" "app-db-password=keyvaultref:$vaultUrl/app-db-password,identityref:$identityId" "read-db-user=keyvaultref:$vaultUrl/read-db-user,identityref:$identityId" "read-db-password=keyvaultref:$vaultUrl/read-db-password,identityref:$identityId" "pgadmin-default-email=keyvaultref:$vaultUrl/pgadmin-default-email,identityref:$identityId" "pgadmin-default-password=keyvaultref:$vaultUrl/pgadmin-default-password,identityref:$identityId" "pgadmin-port=keyvaultref:$vaultUrl/pgadmin-port,identityref:$identityId" "spring-datasource-password=keyvaultref:$vaultUrl/spring-datasource-password,identityref:$identityId" `
  --env-vars "AZURE_CLIENT_ID=$clientId" "SPRING_PROFILES_ACTIVE=$Environment" "SPRING_DATASOURCE_URL=jdbc:postgresql://${dbHost}:5432/modern_java" "SPRING_DATASOURCE_USERNAME=secretref:app-db-user" "SPRING_DATASOURCE_PASSWORD=secretref:spring-datasource-password" "POSTGRES_USER=secretref:postgres-user" "POSTGRES_PASSWORD=secretref:postgres-password" "POSTGRES_DB=secretref:postgres-db" "ADMIN_DB_USER=secretref:admin-db-user" "ADMIN_DB_PASSWORD=secretref:admin-db-password" "APP_DB_USER=secretref:app-db-user" "APP_DB_PASSWORD=secretref:app-db-password" "READ_DB_USER=secretref:read-db-user" "READ_DB_PASSWORD=secretref:read-db-password" "PGADMIN_DEFAULT_EMAIL=secretref:pgadmin-default-email" "PGADMIN_DEFAULT_PASSWORD=secretref:pgadmin-default-password" "PGADMIN_PORT=secretref:pgadmin-port"

# Get app URL
$appUrl = az containerapp show `
  --name northwinds-app `
  --resource-group $resourceGroup `
  --query "properties.configuration.ingress.fqdn" `
  -o tsv

Write-Host "`nDeployment complete!" -ForegroundColor Green
Write-Host "App URL: https://$appUrl" -ForegroundColor Cyan