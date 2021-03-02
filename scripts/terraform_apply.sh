cd terraform
terraform init
export TF_VAR_git_hash=$(git rev-parse HEAD)
export TF_VAR_s3_bucket_repository='REPLACE_THIS_BY_ACTUAL_BUCKET'
terraform apply -var-file=dev.tfvars --auto-approve
