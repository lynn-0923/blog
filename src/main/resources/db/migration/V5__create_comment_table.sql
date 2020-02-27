CREATE TABLE comment
(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    parent_id bigint,
    type int,
    commentator bigint NOT NULL,
    gmt_create bigint,
    gmt_modified bigint NOT NULL,
    praise_count bigint DEFAULT 0,
    content varchar(1024)
);