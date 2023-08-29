CREATE TABLE `corpus`
(
    `corpus_id`       varchar(32)  NOT NULL COMMENT '语料ID',
    `embed_id`        varchar(64)  NOT NULL COMMENT '向量ID',
    `corpus_question` varchar(255) NOT NULL COMMENT '语料问题',
    `corpus_answer`   varchar(255) NOT NULL COMMENT '语料答案',
    `create_time`     TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    `update_time`     TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    PRIMARY KEY (`corpus_id`)
) ENGINE = InnoDB COMMENT ='语料内容表';