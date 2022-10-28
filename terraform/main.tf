provider "aws" {
#  version = "~> 4.36"
  shared_config_files = ["~/.aws/credentials"]
  profile = "terraform"
  region = "us-east-1"
}