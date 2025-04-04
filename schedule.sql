
-- Member Table 생성
CREATE TABLE IF NOT EXISTS study_db.member
(
    created_at DATETIME(6)  NULL,
    id         BIGINT AUTO_INCREMENT
        PRIMARY KEY,
    updated_at DATETIME(6)  NULL ,
    email      VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL ,
    username   VARCHAR(255) NOT NULL ,
    CONSTRAINT UKmbmcqelty0fbrvxp1q58dn57t
        UNIQUE (email)
);

-- Schedule Table 생성
CREATE TABLE IF NOT EXISTS study_db.schedule
(
    created_at DATETIME(6)  NULL,
    id         BIGINT AUTO_INCREMENT
        PRIMARY KEY,
    member_id  BIGINT       NULL,
    updated_at DATETIME(6)  NULL,
    contents   LONGTEXT     NOT NULL,
    title      VARCHAR(255) NOT NULL,
    CONSTRAINT FKn7js9p799qcts7le20pec9bxr
        FOREIGN KEY (member_id) REFERENCES study_db.member (id)
            ON DELETE CASCADE
);


