
create table posts (
    id bigserial primary key,
    title varchar (1024),
    description varchar (2048),
    post_text varchar (4096),
    likes int8
)
