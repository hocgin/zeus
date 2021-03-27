DROP TABLE IF EXISTS `ums_user`;
CREATE TABLE `ums_user`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `nickname`        VARCHAR(16)         NOT NULL
        COMMENT '昵称;显示使用',
    `username`        VARCHAR(20)         NOT NULL UNIQUE
        COMMENT '用户名;唯一,登录使用',
    `email`           VARCHAR(20) UNIQUE
        COMMENT '邮箱;唯一,登录使用',
    `phone`           VARCHAR(20) UNIQUE
        COMMENT '手机号码;唯一,登录使用',
    `password`        VARCHAR(100)        NOT NULL
        COMMENT '密码',
    `avatar_url`      VARCHAR(255)
        COMMENT '头像地址',
    `gender`          TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '性别(0:女, 1:男)',
    `expired`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '过期状态',
    `locked`          TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '锁定状态',
    `enabled`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '启用状态',
    `created_ip`      VARCHAR(15)
        COMMENT '注册时使用的IP',
    --
    `created_at`      DATETIME(6)         NOT NULL
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',

    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[用户模块] 账号表';
