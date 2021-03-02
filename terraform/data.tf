data "aws_caller_identity" "current" {}
data "aws_vpc" "default" {}

data "aws_subnet_ids" "all_from_default_vpc" {
  vpc_id = data.aws_vpc.default.id
}


output "account_id" {
  value = data.aws_caller_identity.current.account_id
}

output "vpc_id" {
  value = data.aws_vpc.default.id
}
output "subnet_ides" {
  value = join(",", data.aws_subnet_ids.all_from_default_vpc.ids)
}