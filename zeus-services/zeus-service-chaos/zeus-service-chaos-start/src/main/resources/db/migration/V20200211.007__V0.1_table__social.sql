DROP TABLE IF EXISTS `ums_social`;
CREATE TABLE `ums_social`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `user_id`      BIGINT      NOT NULL
        COMMENT '账号ID',
    `social_type`     VARCHAR(16) NOT NULL
        COMMENT '社交媒体类型',
    `social_id`       VARCHAR(64) NOT NULL
        COMMENT '社交媒体账号ID',
    --
    `created_at`      DATETIME(6) NOT NULL
        COMMENT '创建时间',
    `last_updated_at` DATETIME(6)
        COMMENT '更新时间',

    UNIQUE KEY (user_id, social_type, social_id),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[用户模块] 绑定社交账号表';
