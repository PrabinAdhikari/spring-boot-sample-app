variable "environment_name" {
  type = string
}
variable "app_name" {
  default = "spring-boot-sample-app"
}
variable "s3_bucket_repository" {}
variable "git_hash" {}
variable "min_size" {}
variable "max_size" {}
variable "instance_type" {
  default = "t2.micro"
}