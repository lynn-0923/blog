create table notification
(
    id bigint auto_increment primary key,
    notifier bigint not null,
    receiver bigint not null,
    outid bigint,
    type int,
    gmt_create bigint,
    status int default 0
);