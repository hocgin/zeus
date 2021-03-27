DROP TABLE IF EXISTS `com_district`;
CREATE TABLE `com_district`
(
    id        BIGINT AUTO_INCREMENT,
    parent_id bigint,
    --
    tree_path varchar(255)        NOT NULL
        COMMENT '树路径，组成方式: /父路径/当前ID',
    `enabled` TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '启用状态',
    adcode    VARCHAR(32)         NOT NULL
        COMMENT '区域编码',
    city_code VARCHAR(32)
        COMMENT '城市编码',
    level     VARCHAR(32)         NOT NULL
        COMMENT '城市规划级别',
    lat       decimal(10, 6)
        COMMENT '中心(纬度)',
    lng       decimal(10, 6)
        COMMENT '中心(经度)',
    title     VARCHAR(32)         NOT NULL
        COMMENT '名称',
    --
    UNIQUE KEY (tree_path),
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[基础模块] 城市规划表';

DROP TABLE IF EXISTS `com_file`;
CREATE TABLE `com_file`
(
    id         BIGINT AUTO_INCREMENT,
    filename   VARCHAR(200) NOT NULL DEFAULT 'unknown'
        COMMENT '文件名',
    file_url   VARCHAR(200) NOT NULL
        COMMENT '链接地址',
    ref_id     BIGINT       NOT NULL
        COMMENT '业务ID',
    ref_type   VARCHAR(10)  NOT NULL DEFAULT 'unknown'
        COMMENT '业务类型',
    priority       INT      NOT NULL DEFAULT 1000
        COMMENT '排序,默认:1000',
    created_at TIMESTAMP(6) NOT NULL
        COMMENT '创建时间',
    creator    BIGINT       NOT NULL
        COMMENT '创建人',
    --
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT '[基础模块] 文件引用表';

DROP TABLE IF EXISTS `com_data_dict`;
CREATE TABLE `com_data_dict`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `title`           VARCHAR(64)         NOT NULL
        COMMENT '字典名称',
    `code`            VARCHAR(64)         NOT NULL UNIQUE
        COMMENT '字典标识',
    `remark`          VARCHAR(255)
        COMMENT '备注',
    `enabled`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '启用状态',
    --
    `created_at`      DATETIME(6)         NOT NULL
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    --
    UNIQUE (`code`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[基础模块] 数据字典表';

DROP TABLE IF EXISTS `com_data_dict_item`;
CREATE TABLE `com_data_dict_item`
(
    `id`              BIGINT AUTO_INCREMENT
        COMMENT 'ID',
    `dict_id`         BIGINT              NOT NULL
        COMMENT 'com_data_dict id',
    `title`           varchar(64)         NOT NULL
        COMMENT '字典项名称',
    `code`            varchar(64)         NOT NULL
        COMMENT '字典标识',
    `remark`          varchar(255)
        COMMENT '备注',
    `priority`            INT(10)             NOT NULL DEFAULT 1000
        COMMENT '排序, 从大到小降序',
    `enabled`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 1
        COMMENT '启用状态',
    --
    `created_at`      DATETIME(6)         NOT NULL
        COMMENT '创建时间',
    `creator`         BIGINT
        COMMENT '创建者',
    `last_updated_at` DATETIME(6)
        COMMENT '更新时间',
    `last_updater`    BIGINT
        COMMENT '更新者',
    --
    UNIQUE (`dict_id`, `code`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '[基础模块] 数据字典项表';
