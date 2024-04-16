#!/bin/bash

# Define the secrets to rotate
secrets=("API_KEY" "DB_PASSWORD" "JWT_SECRET")

# Generate new secret values
for secret in "${secrets[@]}"; do
  new_secret=$(openssl rand -hex 32)  # Generate a new random secret value
  echo "$secret=$new_secret" >> new_secrets.env  # Save the new secret to a new file
done

# Update the environment file with the new secrets
mv new_secrets.env secrets.env

# Restart the Docker containers to apply the new secrets
docker-compose down
docker-compose up -d
