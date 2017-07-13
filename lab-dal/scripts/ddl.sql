CREATE TABLE `invest_info` (
`id`  varchar(32) NOT NULL COMMENT '系统id' ,
`project_name`  varchar(100) NULL COMMENT '投资项目' ,
`main_channel`  varchar(3) NULL COMMENT '主渠道' ,
`sub_channel`  varchar(3) NULL COMMENT '子渠道' ,
`cost`  decimal(15,2) NULL DEFAULT 0 COMMENT '成本' ,
`income`  decimal(15,2) NULL DEFAULT 0 COMMENT '到期收益' ,
`begin_date`  varchar(8) NULL COMMENT '起投日期' ,
`end_date`  varchar(8) NULL COMMENT '截止日期' ,
`tm`  datetime NULL COMMENT '时间' ,
PRIMARY KEY (`id`)
)
;