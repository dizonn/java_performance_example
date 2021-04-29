DROP TABLE IF EXISTS post;

create table post
(
    id    integer auto_increment primary key,
    title varchar(40) not null
);

insert into post (title) values
('post 1'),
('post 2'),
('post 3'),
('post 4'),
('post 5'),
('post 6'),
('post 7'),
('post 8'),
('post 9'),
('post 10')