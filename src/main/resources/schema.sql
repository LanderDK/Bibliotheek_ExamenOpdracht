DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS authorities;

CREATE table users (
  username varchar(255) NOT NULL,
  enabled bit(1) DEFAULT NULL,
  favorieten varbinary(255) DEFAULT NULL,
  max_aantal_favs int DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  PRIMARY KEY (username));

create table authorities (
  username varchar(50) not null,
  authority varchar(50) not null,
  foreign key (username) references users (username),
  unique index authorities_idx_1 (username, authority));