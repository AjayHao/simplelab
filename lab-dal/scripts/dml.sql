INSERT INTO `springlab`.`common_params` (`param_group`, `param_code`, `param_value`, `param_desc`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('PROJECT_TYPE', 'PT001', '活期理财', '项目类型-活期理财', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`common_params` (`param_group`, `param_code`, `param_value`, `param_desc`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('PROJECT_TYPE', 'PT002', '定期理财', '项目类型-定期理财', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`common_params` (`param_group`, `param_code`, `param_value`, `param_desc`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('PROJECT_TYPE', 'PT003', '活期存款', '项目类型-活期存款', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`common_params` (`param_group`, `param_code`, `param_value`, `param_desc`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('PROJECT_TYPE', 'PT004', '定期存款', '项目类型-定期存款', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`common_params` (`param_group`, `param_code`, `param_value`, `param_desc`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('PROJECT_TYPE', 'PT005', '货币基金', '项目类型-活期理财', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`common_params` (`param_group`, `param_code`, `param_value`, `param_desc`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('PROJECT_TYPE', 'PT006', '组合基金', '项目类型-组合基金', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`common_params` (`param_group`, `param_code`, `param_value`, `param_desc`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('PROJECT_TYPE', 'PT007', '混合基金', '项目类型-混合基金', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`common_params` (`param_group`, `param_code`, `param_value`, `param_desc`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('PROJECT_TYPE', 'PT008', '分级基金', '项目类型-分级基金', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`common_params` (`param_group`, `param_code`, `param_value`, `param_desc`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('MAIN_CHANNEL', 'MC001', '银行', '购买渠道-银行', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`common_params` (`param_group`, `param_code`, `param_value`, `param_desc`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('MAIN_CHANNEL', 'MC002', '券商', '购买渠道-券商', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`common_params` (`param_group`, `param_code`, `param_value`, `param_desc`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('MAIN_CHANNEL', 'MC003', '基金', '购买渠道-基金', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`common_params` (`param_group`, `param_code`, `param_value`, `param_desc`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('MAIN_CHANNEL', 'MC004', '互金平台', '购买渠道-互金平台', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`common_params` (`param_group`, `param_code`, `param_value`, `param_desc`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('SUB_CHANNEL', 'YH001', '工商银行', '子渠道-工行', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`common_params` (`param_group`, `param_code`, `param_value`, `param_desc`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('SUB_CHANNEL', 'YH002', '建设银行', '子渠道-建行', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`common_params` (`param_group`, `param_code`, `param_value`, `param_desc`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('SUB_CHANNEL', 'YH003', '渤海银行', '子渠道-渤海', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`common_params` (`param_group`, `param_code`, `param_value`, `param_desc`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('SUB_CHANNEL', 'YH004', '招商银行', '子渠道-招行', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`common_params` (`param_group`, `param_code`, `param_value`, `param_desc`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('SUB_CHANNEL', 'HJ001', '蚂蚁聚宝', '子渠道-蚂蚁', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`common_params` (`param_group`, `param_code`, `param_value`, `param_desc`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('SUB_CHANNEL', 'HJ002', '陆金所', '子渠道-陆金所', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`common_params` (`param_group`, `param_code`, `param_value`, `param_desc`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('SUB_CHANNEL', 'HJ003', '天天基金', '子渠道-天天', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`common_params` (`param_group`, `param_code`, `param_value`, `param_desc`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('SUB_CHANNEL', 'HJ004', '钱滚滚', '子渠道-钱滚滚', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`common_params` (`param_group`, `param_code`, `param_value`, `param_desc`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('SUB_CHANNEL', 'QS001', '东方证券', '子渠道-东方', 'sys', now(), 'sys', now());


/* 历史记录 */INSERT INTO `springlab`.`invest_info` (`project_name`, `project_type`, `main_channel`, `sub_channel`, `cost`, `income`, `begin_date`, `end_date`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('中海货币', 'PT005', 'MC001', 'YH001', '50000.00', NULL, NULL, NULL, 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`invest_info` (`project_name`, `project_type`, `main_channel`, `sub_channel`, `cost`, `income`, `begin_date`, `end_date`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('定期存款', 'PT004', 'MC001', 'YH001', '34000.00', NULL, NULL, NULL,'sys', now(), 'sys', now());
INSERT INTO `springlab`.`invest_info` (`project_name`, `project_type`, `main_channel`, `sub_channel`, `cost`, `income`, `begin_date`, `end_date`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('活期存款', 'PT003', 'MC001', 'YH001', '28000.00', NULL, NULL, NULL, 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`invest_info` (`project_name`, `project_type`, `main_channel`, `sub_channel`, `cost`, `income`, `begin_date`, `end_date`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('2017年渤盛161号', 'PT002', 'MC001', 'YH002', '100000.00', NULL, '20170228', '20170804', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`invest_info` (`project_name`, `project_type`, `main_channel`, `sub_channel`, `cost`, `income`, `begin_date`, `end_date`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('活期存款', 'PT003', 'MC001', 'YH003', '39000.00', NULL, NULL, NULL, 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`invest_info` (`project_name`, `project_type`, `main_channel`, `sub_channel`, `cost`, `income`, `begin_date`, `end_date`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('建信养老飞月宝', 'PT002', 'MC004', 'HJ001', '36000.00', NULL, '20170714', '20170814', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`invest_info` (`project_name`, `project_type`, `main_channel`, `sub_channel`, `cost`, `income`, `begin_date`, `end_date`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('余额宝', 'PT001', 'MC004', 'HJ001', '73000.00', NULL, NULL, NULL, 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`invest_info` (`project_name`, `project_type`, `main_channel`, `sub_channel`, `cost`, `income`, `begin_date`, `end_date`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('摩羯24号', 'PT006', 'MC001', 'YH004', '50000.00', NULL, '20170714', NULL, 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`invest_info` (`project_name`, `project_type`, `main_channel`, `sub_channel`, `cost`, `income`, `begin_date`, `end_date`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('财富汇-05M825A号', 'PT002', 'MC004', 'HJ002', '100000.00', NULL, '20170303', '20170817', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`invest_info` (`project_name`, `project_type`, `main_channel`, `sub_channel`, `cost`, `income`, `begin_date`, `end_date`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('财富汇-03M646F号', 'PT002', 'MC004', 'HJ002', '110000.00', NULL, '20170713', NULL, 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`invest_info` (`project_name`, `project_type`, `main_channel`, `sub_channel`, `cost`, `income`, `begin_date`, `end_date`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('零活宝-28日聚财', 'PT001', 'MC004', 'HJ002', '7100.00', NULL, NULL, NULL, 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`invest_info` (`project_name`, `project_type`, `main_channel`, `sub_channel`, `cost`, `income`, `begin_date`, `end_date`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('股票', 'PT001', 'MC002', 'QS001', '55000.00', NULL, NULL, NULL, 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`invest_info` (`project_name`, `project_type`, `main_channel`, `sub_channel`, `cost`, `income`, `begin_date`, `end_date`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('富国低碳环保混合', 'PT007', 'MC004', 'HJ003', '44000.00', NULL, NULL, NULL, 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`invest_info` (`project_name`, `project_type`, `main_channel`, `sub_channel`, `cost`, `income`, `begin_date`, `end_date`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('富国天惠成长混合A', 'PT007', 'MC004', 'HJ003', '40000.00', NULL, NULL, NULL, 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`invest_info` (`project_name`, `project_type`, `main_channel`, `sub_channel`, `cost`, `income`, `begin_date`, `end_date`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('富国中证军工指数分级', 'PT008', 'MC004', 'HJ003', '22000.00', NULL, NULL, NULL, 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`invest_info` (`project_name`, `project_type`, `main_channel`, `sub_channel`, `cost`, `income`, `begin_date`, `end_date`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('安心宝7天2号', 'PT002', 'MC004', 'HJ004', '50000.00', '57.54', '20161130', '20161208', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`invest_info` (`project_name`, `project_type`, `main_channel`, `sub_channel`, `cost`, `income`, `begin_date`, `end_date`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('安心宝7天3号', 'PT002', 'MC004', 'HJ004', '50000.00', '57.54', '20161207', '20161215', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`invest_info` (`project_name`, `project_type`, `main_channel`, `sub_channel`, `cost`, `income`, `begin_date`, `end_date`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('安盈宝32天1号', 'PT002', 'MC004', 'HJ004', '50000.00', '87.67', '20161222', '20170124', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`invest_info` (`project_name`, `project_type`, `main_channel`, `sub_channel`, `cost`, `income`, `begin_date`, `end_date`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('安心宝7天11号', 'PT002', 'MC004', 'HJ004', '50000.00', '52.74', '20170308', '20170316', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`invest_info` (`project_name`, `project_type`, `main_channel`, `sub_channel`, `cost`, `income`, `begin_date`, `end_date`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('安盈宝3号33天', 'PT002', 'MC004', 'HJ004', '50000.00', '200.17', '20170308', '20170411', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`invest_info` (`project_name`, `project_type`, `main_channel`, `sub_channel`, `cost`, `income`, `begin_date`, `end_date`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('安心宝1月期6号', 'PT002', 'MC004', 'HJ004', '50000.00', NULL, '20170714', '20170819', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`invest_info` (`project_name`, `project_type`, `main_channel`, `sub_channel`, `cost`, `income`, `begin_date`, `end_date`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('安心宝7天13号', 'PT002', 'MC004', 'HJ004', '50000.00', '50.83', '20170322', '20170330', 'sys', now(), 'sys', now());
INSERT INTO `springlab`.`invest_info` (`project_name`, `project_type`, `main_channel`, `sub_channel`, `cost`, `income`, `begin_date`, `end_date`, `creator_id`, `create_tm`, `modifier_id`, `modify_tm`) VALUES ('安盈宝34天港股看涨', 'PT002', 'MC004', 'HJ004', '50000.00', '208.96', '20170413', '20170518', 'sys', now(), 'sys', now());
