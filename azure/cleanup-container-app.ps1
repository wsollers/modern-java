param(
    [switch]$IncludeEnvironment = $false
)

$resourceGroup = "rg-northwinds"

Write-Host "=== Deleting Container Apps ===" -ForegroundColor Yellow

Write-Host "Deleting northwinds-app..." -ForegroundColor Gray
az containerapp delete --name northwinds-app --resource-group $resourceGroup --yes 2>$null

Write-Host "Deleting northwinds-postgres..." -ForegroundColor Gray
az containerapp delete --name northwinds-postgres --resource-group $resourceGroup --yes 2>$null

if ($IncludeEnvironment) {
    Write-Host "Deleting environment..." -ForegroundColor Gray
    az containerapp env delete --name northwinds-env --resource-group $resourceGroup --yes
}

Write-Host "`n=== Container Apps Deleted ===" -ForegroundColor Green