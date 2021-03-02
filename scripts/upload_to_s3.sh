GIT_HASH=$(git rev-parse HEAD)
S3_OBJECT_PATH="REPLACE_THIS_BY_ACTUAL_BUCKET/spring-boot-sample-app/${GIT_HASH}/spring-boot-0.0.1-SNAPSHOT.jar"
aws s3 cp target/spring-boot-0.0.1-SNAPSHOT.jar "s3://${S3_OBJECT_PATH}" --region us-east-1