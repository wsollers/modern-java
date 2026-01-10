param(
    [switch]$Confirm = $false
)

$resourceGroup = "rg-northwinds"

if (-not $Confirm) {
    Write-Host "This will DELETE the entire resource group '$resourceGroup' and ALL resources in it!" -ForegroundColor Red
    Write-Host "Resources to be deleted:" -ForegroundColor Yellow
    az resource list --resource-group $resourceGroup --query "[].{Name:name, Type:type}" -o table

    $response = Read-Host "`nType 'yes' to confirm deletion"
    if ($response -ne "yes") {
        Write-Host "Aborted." -ForegroundColor Yellow
        exit
    }
}

Write-Host "`nDeleting resource group $resourceGroup..." -ForegroundColor Yellow
az group delete --name $resourceGroup --yes --no-wait

Write-Host "Deletion initiated. This may take several minutes to complete." -ForegroundColor Cyan
Write-Host "Check status: az group show --name $resourceGroup --query properties.provisioningState -o tsv"