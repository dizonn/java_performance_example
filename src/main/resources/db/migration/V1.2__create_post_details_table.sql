DROP TABLE IF EXISTS post_details;

create table post_details
(
    id         integer auto_increment primary key,
    created_on datetime    not null,
    created_by varchar(40) not null,
    post_id    int         not null,
    foreign key (post_id) references post (id)
);

insert into post_details (created_on, created_by, post_id) values
 (current_date(), 'test 1', 1)