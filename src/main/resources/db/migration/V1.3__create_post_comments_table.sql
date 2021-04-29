DROP TABLE IF EXISTS comment;

create table comment
(
    id         integer auto_increment primary key,
    text       varchar(40) not null,
    post_id    int         not null,
    foreign key (post_id) references post (id)
);

insert into comment (text, post_id) values
('comment 1', 1),
('comment 2', 1),
('comment 3', 1),
('comment 4', 2)