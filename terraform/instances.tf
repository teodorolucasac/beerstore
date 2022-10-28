resource "aws_key_pair" "keypair" {
  public_key = file("key/beerstore_key.pub")
}

resource "aws_instance" "instances" {
  count = 3

  ami = "ami-09d3b3274b6c5d4aa"
  instance_type = "t3.micro"

  subnet_id = element(aws_subnet.public_subnet.*.id, count.index)

  key_name = aws_key_pair.keypair.key_name

  vpc_security_group_ids = [aws_security_group.allow_ssh.id]

  tags = {
    Name = "hibicode_instances"
  }
}

output "public_ips" {
  value = join(", ", aws_instance.instances.*.public_ip)
}