DROP TABLE IF EXISTS `ams_authority`;
CREATE TABLE `ams_authority`
(
    `id`              BIGINT AUTO_INCREMENT,
    `title`           varchar(25)  NOT NULL
        COMMENT '权限名称',
    `encoding`        varchar(64)  NOT NULL UNIQUE
        COMMENT '编码',
    `project_id`      BIGINT       NOT NULL
        COMMENT '项目ID',
    `remark`          varchar(255)
        COMMENT '描述',
    `priority`        INT(5)       NOT NULL DEFAULT 1000
        COMMENT '优先级, 越大优先级越低',
    --
    `parent_id`       BIGINT
        COMMENT '父级ID, 顶级为 NULL',
    `tree_path`       varchar(255) NOT NULL
        COMMENT '树路径，组成方式: /父路径/当前ID',
    `enabled`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '启用状态',
    --
    `created_at`      DATETIME(6)  NOT NULL
        COMMENT '创建时间',
    `creator`         BIGINT       NOT NULL
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[权限模块] 权限表';

DROP TABLE IF EXISTS `ams_role`;
CREATE TABLE `ams_role`
(
    `id`              BIGINT AUTO_INCREMENT,
    `title`           varchar(25)     NOT NULL
        COMMENT '角色名称',
    `encoding`        varchar(64)     NOT NULL UNIQUE
        COMMENT '角色授权码',
    `remark`          varchar(255)
        COMMENT '角色描述',
    `enabled`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '启用状态',
    is_persist        INT(2) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '是否保留',
    --
    `created_at`      DATETIME(6)     NOT NULL
        COMMENT '创建时间',
    `creator`         BIGINT          NOT NULL
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[权限模块] 角色表';

DROP TABLE IF EXISTS `ams_role_authority_ref`;
CREATE TABLE `ams_role_authority_ref`
(
    `id`           BIGINT AUTO_INCREMENT,
    `role_id`      BIGINT NOT NULL
        COMMENT '角色',
    `authority_id` BIGINT NOT NULL
        COMMENT '权限',

    UNIQUE (`role_id`, `authority_id`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[权限模块] 角色x权限表';

DROP TABLE IF EXISTS `ams_role_user_ref`;
CREATE TABLE `ams_role_user_ref`
(
    `id`      BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `role_id` BIGINT NOT NULL
        COMMENT '角色',
    `user_id` BIGINT NOT NULL
        COMMENT '用户',
    UNIQUE (`role_id`, `user_id`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[权限模块] 角色x账号表';

DROP TABLE IF EXISTS `ams_api`;
CREATE TABLE `ams_api`
(
    id                BIGINT UNSIGNED AUTO_INCREMENT,
    encoding          VARCHAR(64)     NOT NULL
        COMMENT '接口编码',
    title             VARCHAR(64)     NOT NULL
        COMMENT '接口名称',
    remark            VARCHAR(255)
        COMMENT '接口描述',
    request_method    VARCHAR(64)     NOT NULL
        COMMENT '请求方式: 比如: GET,POST 或者 *',
    service_prefix    VARCHAR(100)    NOT NULL
        COMMENT '服务接口前缀, 比如: /chaos 或者 *',
    request_uri       VARCHAR(255)    NOT NULL
        COMMENT '请求URI, 比如: /xx/{id}  或者 /**',
    `enabled`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '启用状态',
    priority          INT(5)                   DEFAULT 1000
        COMMENT '优先级, 越大优先级越低',
    is_persist        INT(2) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '是否保留权限',
    --
    `created_at`      DATETIME(6)     NOT NULL
        COMMENT '创建时间',
    `creator`         BIGINT          NOT NULL
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    UNIQUE KEY (encoding),
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[权限模块] 接口表';

DROP TABLE IF EXISTS `ams_authority_api_ref`;
CREATE TABLE `ams_authority_api_ref`
(
    id           BIGINT UNSIGNED AUTO_INCREMENT,
    authority_id BIGINT UNSIGNED NOT NULL
        COMMENT '权限ID',
    api_id       BIGINT UNSIGNED NOT NULL
        COMMENT '接口ID',
    UNIQUE KEY (authority_id, api_id),
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[权限模块] 权限x接口表';

DROP TABLE IF EXISTS `ams_user_group`;
CREATE TABLE `ams_user_group`
(
    `id`              BIGINT UNSIGNED AUTO_INCREMENT,
    `title`           varchar(25)     NOT NULL
        COMMENT '名称',
    `encoding`        varchar(64)     NOT NULL UNIQUE
        COMMENT '编码',
    `remark`          varchar(255)
        COMMENT '描述',
    `enabled`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '启用状态',
    is_persist        INT(2) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '是否保留',
    --
    `created_at`      DATETIME(6)     NOT NULL
        COMMENT '创建时间',
    `creator`         BIGINT          NOT NULL
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[权限模块] 用户组表';

DROP TABLE IF EXISTS `ams_user_group_user_ref`;
CREATE TABLE `ams_user_group_user_ref`
(
    id            BIGINT UNSIGNED AUTO_INCREMENT,
    user_group_id BIGINT UNSIGNED NOT NULL
        COMMENT '用户组ID',
    user_id       BIGINT UNSIGNED NOT NULL
        COMMENT '用户ID',
    UNIQUE KEY (user_group_id, user_id),
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[权限模块] 用户组x用户表';

DROP TABLE IF EXISTS `ams_user_group_authority_ref`;
CREATE TABLE `ams_user_group_authority_ref`
(
    `id`            BIGINT AUTO_INCREMENT,
    `user_group_id` BIGINT NOT NULL
        COMMENT '用户组',
    `authority_id`  BIGINT NOT NULL
        COMMENT '权限',
    UNIQUE (`user_group_id`, `authority_id`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[权限模块] 用户组x权限表';
