resource "aws_elastic_beanstalk_application_version" "default" {
  name        = "spring-boot-sample-app-${var.git_hash}"
  application = var.app_name
  description = "application version created by terraform"
  bucket      = var.s3_bucket_repository
  key         = "spring-boot-sample-app/${var.git_hash}/spring-boot-0.0.1-SNAPSHOT.jar"
}

resource "aws_elastic_beanstalk_application" "spring_boot_v1" {
  name        = var.app_name
  description = "Sample application for terraform workshop"
}

resource "aws_elastic_beanstalk_environment" "spring_boot_env1" {
  name                = "${var.app_name}-${var.environment_name}"
  application         = aws_elastic_beanstalk_application.spring_boot_v1.name
  version_label = aws_elastic_beanstalk_application_version.default.name
  solution_stack_name = "64bit Amazon Linux 2 v3.1.6 running Corretto 8"

  setting {
    namespace = "aws:autoscaling:asg"
    name      = "MinSize"
    value     = var.min_size
  }
  setting {
    namespace = "aws:autoscaling:asg"
    name      = "MaxSize"
    value     = var.max_size
  }

  setting {
    namespace = "aws:autoscaling:launchconfiguration"
    name      = "IamInstanceProfile"
    value     = "arn:aws:iam::${data.aws_caller_identity.current.account_id}:instance-profile/aws-elasticbeanstalk-ec2-role"
  }

  setting {
    namespace = "aws:autoscaling:launchconfiguration"
    name      = "InstanceType"
    value     = var.instance_type
  }

  setting {
    namespace = "aws:ec2:vpc"
    name      = "VPCId"
    value     = data.aws_vpc.default.id
  }

  setting {
    namespace = "aws:ec2:vpc"
    name      = "Subnets"
    value     = join(",", data.aws_subnet_ids.all_from_default_vpc.ids)
  }

  setting {
    namespace = "aws:ec2:vpc"
    name      = "ELBSubnets"
    value     = join(",", data.aws_subnet_ids.all_from_default_vpc.ids)
  }

  setting {
    namespace = "aws:ec2:vpc"
    name      = "ELBScheme"
    value     = "public"
  }

  setting {
    namespace = "aws:elasticbeanstalk:application:environment"
    name      = "environment"
    value     = var.environment_name
  }

  setting {
    namespace = "aws:elasticbeanstalk:application"
    name      = "Application Healthcheck URL"
    value     = "/actuator/health"
  }

  setting {
    namespace = "aws:elasticbeanstalk:command"
    name      = "DeploymentPolicy"
    value     = "AllAtOnce"
  }

  setting {
    namespace = "aws:autoscaling:updatepolicy:rollingupdate"
    name      = "RollingUpdateType"
    value     = "Health"
  }
  setting {
    namespace = "aws:elasticbeanstalk:environment"
    name      = "LoadBalancerType"
    value     = "application"
  }
  setting {
    namespace = "aws:elasticbeanstalk:environment:process:default"
    name      = "HealthCheckPath"
    value     = "/actuator/health"
  }
  setting {
    namespace = "aws:elb:loadbalancer"
    name      = "CrossZone"
    value     = "true"
  }

  setting {
    namespace = "aws:elb:policies"
    name      = "ConnectionDrainingEnabled"
    value     = "true"
  }
  tags = {
    Team        = "Name"
    Environment = "tf-workshop"
  }
}

data "aws_alb" "created_by_eb" {
  arn  = aws_elastic_beanstalk_environment.spring_boot_env1.load_balancers[0]
}

output "dns_name" {
  value = data.aws_alb.created_by_eb.dns_name
}