create database Account
go
use Account
go
create table Student(
StudentID varchar(3),
StudentName varchar(38),
UserName varchar(16),
[Password]varchar(16)
)
go
insert into Student values('1','tan','user','password')