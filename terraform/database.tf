module "rds" {
  source  = "terraform-aws-modules/rds/aws"
  version = "5.1.0"

  identifier = "hibicode-beerstore-rds"

  engine = "postgres"
  engine_version = "13.7"
  instance_class = "db.t3.micro"
  allocated_storage = "25"

  db_name = "beerstore"
  username = "beerstore"
  password = "beerstore"
  port = "5432"

  vpc_security_group_ids = [aws_security_group.database.id]

  maintenance_window = "Thu:03:30-Thu:05:30"
  backup_window = "05:30-06:30"
  storage_type = "gp2"
  multi_az = "false"
  family = "postgres13"

  subnet_ids = flatten(chunklist(aws_subnet.private_subnet.*.id, 1))

  db_subnet_group_name = aws_db_subnet_group.default_test.name

  major_engine_version = "13"
}