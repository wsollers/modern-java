param(
    [string]$Environment = "prod",
    [string]$Location = "eastus"
)

$resourceGroup = "rg-northwinds"
$acrName = "northwindsacr"
$vaultName = "kv-northwinds-$Environment"
$identityName = "northwinds-identity"
$envName = "northwinds-env"

Write-Host "=== Creating Azure Infrastructure ===" -ForegroundColor Cyan

# 1. Resource Group
Write-Host "`n[1/6] Creating Resource Group..." -ForegroundColor Yellow
az group create --name $resourceGroup --location $Location

# 2. Container Registry
Write-Host "`n[2/6] Creating Container Registry..." -ForegroundColor Yellow
az acr create --name $acrName --resource-group $resourceGroup --sku Basic
az acr login --name $acrName

# 3. Key Vault
Write-Host "`n[3/6] Creating Key Vault..." -ForegroundColor Yellow
az keyvault create --name $vaultName --resource-group $resourceGroup --location $Location --enable-rbac-authorization true

# Grant yourself access to Key Vault
$userObjectId = az ad signed-in-user show --query id -o tsv
$vaultId = az keyvault show --name $vaultName --query id -o tsv
az role assignment create --assignee $userObjectId --role "Key Vault Secrets Officer" --scope $vaultId

Write-Host "Waiting for RBAC to propagate..." -ForegroundColor Gray
Start-Sleep -Seconds 30

# 4. Key Vault Secrets
Write-Host "`n[4/6] Creating Key Vault Secrets..." -ForegroundColor Yellow
$secrets = @{
    "postgres-user" = "postgres"
    "postgres-password" = "postgres_bootstrap_password"
    "postgres-db" = "modern_java"
    "admin-db-user" = "modern_java_admin"
    "admin-db-password" = "admin_strong_password"
    "app-db-user" = "modern_java_write"
    "app-db-password" = "write_strong_password"
    "read-db-user" = "modern_java_read"
    "read-db-password" = "read_strong_password"
    "pgadmin-default-email" = "admin@example.com"
    "pgadmin-default-password" = "pgadmin_strong_password"
    "pgadmin-port" = "5050"
    "spring-datasource-password" = "write_strong_password"
    "spring-datasource-url" = "jdbc:postgresql://northwinds-postgres:5432/modern_java"
}

foreach ($secret in $secrets.GetEnumerator()) {
    az keyvault secret set --vault-name $vaultName --name $secret.Key --value $secret.Value | Out-Null
    Write-Host "  Created secret: $($secret.Key)"
}

# 5. Managed Identity
Write-Host "`n[5/6] Creating Managed Identity..." -ForegroundColor Yellow
az identity create --name $identityName --resource-group $resourceGroup

$principalId = az identity show --name $identityName --resource-group $resourceGroup --query principalId -o tsv
$clientId = az identity show --name $identityName --resource-group $resourceGroup --query clientId -o tsv
$identityId = az identity show --name $identityName --resource-group $resourceGroup --query id -o tsv

# Grant identity access to Key Vault
az role assignment create --assignee $principalId --role "Key Vault Secrets User" --scope $vaultId

# Grant identity access to ACR
$acrId = az acr show --name $acrName --query id -o tsv
az role assignment create --assignee $principalId --role "AcrPull" --scope $acrId

Write-Host "Waiting for RBAC to propagate..." -ForegroundColor Gray
Start-Sleep -Seconds 30

# 6. Container Apps Environment
Write-Host "`n[6/6] Creating Container Apps Environment..." -ForegroundColor Yellow
az containerapp env create --name $envName --resource-group $resourceGroup --location $Location

Write-Host "`n=== Infrastructure Created ===" -ForegroundColor Green
Write-Host "Resource Group:    $resourceGroup"
Write-Host "Container Registry: $acrName.azurecr.io"
Write-Host "Key Vault:         $vaultName"
Write-Host "Managed Identity:  $identityName (Client ID: $clientId)"
Write-Host "Environment:       $envName"
Write-Host "`nNext steps:"
Write-Host "  1. Build and push images: .\build-and-push-images.ps1"
Write-Host "  2. Deploy containers:     .\deploy-container-apps.ps1"