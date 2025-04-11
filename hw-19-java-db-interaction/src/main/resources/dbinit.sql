CREATE TABLE IF NOT EXISTS users(
    id bigserial primary key,
    login varchar(255),
    password varchar(255),
    nickname varchar(255));
CREATE TABLE IF NOT EXISTS bonuses (
    id bigserial primary key,
    owner_login varchar(255),
    amount int);