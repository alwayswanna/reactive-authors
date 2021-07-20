create table posts
(
    id          bigserial primary key,
    title       varchar(1024),
    description varchar(2048),
    post_text   varchar(4096),
    author_user_name varchar (2048),
    likes       int8
);


create table administration
(
    id  bigserial primary key,
    username varchar(2048),
    password varchar(2048),
    name     varchar(2048),
    surname  varchar(2048),
    email    varchar(2048),
    active   boolean,
    roles     varchar(64)
);

create table user_role
(
    user_id int8 not null,
    roles   varchar(255)
);
