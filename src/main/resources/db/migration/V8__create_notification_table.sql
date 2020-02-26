CREATE TABLE notification
(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    notifier bigint NOT NULL,
    receiver bigint NOT NULL,
    outId bigint,
    type int,
    gmt_create bigint,
    status int DEFAULT 0
);