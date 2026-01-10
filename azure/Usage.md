# First time setup - create everything
.\create-infrastructure.ps1 -Environment prod

# Build and push images
.\build-and-push-images.ps1 -Tag v1

# Deploy containers
.\deploy-container-apps.ps1 -Environment prod -Tag v1

# Quick redeploy (just containers)
.\delete-container-apps.ps1
.\deploy-container-apps.ps1 -Environment prod -Tag v1

# Full teardown (everything)
.\delete-infrastructure.ps1

# Teardown with auto-confirm
.\delete-infrastructure.ps1 -Confirm