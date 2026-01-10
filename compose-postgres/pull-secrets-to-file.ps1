param(
    [string]$Environment = "dev"
)

$vaultName = "kv-northwinds-$Environment"
$envFile = "secrets.env"

Write-Host "Pulling secrets from $vaultName..." -ForegroundColor Cyan

# Clear existing file
"" | Out-File $envFile -Encoding utf8

function Pull-Secret {
    param([string]$secretName, [string]$envVarName)

    $value = az keyvault secret show --vault-name $vaultName --name $secretName --query value -o tsv
    Write-Host "Pulled secret $secretName [$value]"
    "$envVarName=$value" | Out-File $envFile -Append -Encoding utf8
}

Pull-Secret "postgres-user" "POSTGRES_USER"
Pull-Secret "postgres-password" "POSTGRES_PASSWORD"
Pull-Secret "postgres-db" "POSTGRES_DB"
Pull-Secret "admin-db-user" "ADMIN_DB_USER"
Pull-Secret "admin-db-password" "ADMIN_DB_PASSWORD"
Pull-Secret "app-db-user" "APP_DB_USER"
Pull-Secret "app-db-password" "APP_DB_PASSWORD"
Pull-Secret "read-db-user" "READ_DB_USER"
Pull-Secret "read-db-password" "READ_DB_PASSWORD"
Pull-Secret "pgadmin-default-email" "PGADMIN_DEFAULT_EMAIL"
Pull-Secret "pgadmin-default-password" "PGADMIN_DEFAULT_PASSWORD"
Pull-Secret "pgadmin-port" "PGADMIN_PORT"
Pull-Secret "spring-datasource-password" "SPRING_DATASOURCE_PASSWORD"

Write-Host "`nSecrets written to $envFile" -ForegroundColor Green