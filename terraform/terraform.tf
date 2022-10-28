terraform {
  backend "s3" {
    bucket = "hibicode-terraform-teodoro"
    key = "beerstore-curso-online"
    region = "us-east-1"
    profile = "terraform"
  }
}