-- 投资信息表
CREATE TABLE `invest_info` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '系统id',
  `project_name` varchar(100) DEFAULT NULL COMMENT '投资项目',
  `project_type` varchar(6) DEFAULT NULL COMMENT '项目类型',
  `main_channel` varchar(6) DEFAULT NULL COMMENT '主渠道',
  `sub_channel` varchar(6) DEFAULT NULL COMMENT '子渠道',
  `cost` decimal(15,2) DEFAULT '0.00' COMMENT '成本',
  `income` decimal(15,2) DEFAULT '0.00' COMMENT '到期收益',
  `begin_date` varchar(8) DEFAULT NULL COMMENT '起投日期',
  `end_date` varchar(8) DEFAULT NULL COMMENT '截止日期',
  `creator_id` varchar(32) DEFAULT NULL COMMENT '创建人id',
  `create_tm` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` varchar(32) DEFAULT NULL COMMENT '修改人id',
  `modify_tm` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
)
-- 公共参数表
CREATE TABLE `common_params` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `param_group` varchar(30) NOT NULL COMMENT '参数组',
  `param_code` varchar(30) NOT NULL COMMENT '参数代码',
  `param_value` varchar(100) DEFAULT NULL COMMENT '参数值',
  `param_desc` varchar(255) DEFAULT NULL COMMENT '描述',
  `creator_id` varchar(32) DEFAULT NULL COMMENT '创建人id',
  `create_tm` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` varchar(32) DEFAULT NULL COMMENT '修改人id',
  `modify_tm` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_common_params_1` (`param_group`,`param_code`)
);

