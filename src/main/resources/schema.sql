CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fullname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

insert into users (fullname, username, password) values ('user1', 'user1', 'password1');
insert into users (fullname, username, password) values ('user2', 'user2', 'password2');
insert into users (fullname, username, password) values ('user3', 'user3', 'password3');
