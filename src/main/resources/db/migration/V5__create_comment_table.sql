create table comment
(
    id bigint auto_increment primary key,
    parent_id bigint,
    type int,
    commentator bigint not null,
    gmt_create bigint,
    gmt_modified bigint not null,
    praise_count bigint default 0,
    content varchar(1024)
);