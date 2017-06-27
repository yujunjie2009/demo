DROP SCHEMA IF EXISTS `newtiming_finance` ;
CREATE SCHEMA IF NOT EXISTS `newtiming_finance` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;

GRANT ALL ON newtiming_finance.* TO 'xdmfinance'@'%' IDENTIFIED BY 'xdmfinance123$' WITH GRANT OPTION;
flush privileges;

GRANT ALL ON newtiming_finance.* TO 'xdmfinance'@'localhost' IDENTIFIED BY 'xdmfinance123$' WITH GRANT OPTION;
flush privileges;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `create_datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '权限名',
  `code` varchar(20) NOT NULL COMMENT '权限码',
  `group_id` bigint(11) DEFAULT NULL COMMENT '权限组id',
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '0：无效；1：删除',
  `create_datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Table structure for sys_permission_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_group`;
CREATE TABLE `sys_permission_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT '' COMMENT '权限组名称',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '0：无效；1：正常',
  `create_datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='权限组表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_code` varchar(50) DEFAULT NULL,
  `role_name` varchar(50) NOT NULL COMMENT '用户名',
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '0：无效；1：删除',
  `create_datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_id` bigint(20) NOT NULL COMMENT '用户名',
  `permission_id` bigint(20) NOT NULL COMMENT '主键',
  `create_datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`role_id`,`permission_id`),
  UNIQUE KEY `unq-sys_role_permission` (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-权限表';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `full_name` varchar(50) NOT NULL COMMENT '姓名',
  `department_id` bigint(20) NOT NULL COMMENT '部门id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `phone` varchar(15) NOT NULL COMMENT '手机',
  `email` varchar(20) NOT NULL,
  `create_user_id` bigint(20) NOT NULL COMMENT '创建人id',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除(0：否；1：是)',
  `create_datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for t_account
-- ----------------------------
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_name` varchar(255) NOT NULL COMMENT '账目名称',
  `account_type` int(2) NOT NULL COMMENT '账目类型',
  `refund_type` varchar(200) DEFAULT NULL COMMENT '还款方式',
  `partner` varchar(200) DEFAULT NULL COMMENT '合作方',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` int(2) DEFAULT '1' COMMENT '0:无效;1:正常',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除(0：否；1：是)',
  `create_user_id` bigint(20) NOT NULL COMMENT '创建人id',
  `create_datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='账目表';

-- ----------------------------
-- Table structure for t_account_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_account_detail`;
CREATE TABLE `t_account_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_id` bigint(20) NOT NULL COMMENT '账目id',
  `amount` double(20,2) NOT NULL DEFAULT '0.00' COMMENT '金额',
  `annualized` float(6,3) DEFAULT NULL COMMENT '年化收益率',
  `period` int(11) DEFAULT NULL COMMENT '周期(月)',
  `content` varchar(200) NOT NULL COMMENT '摘要',
  `record_type` int(2) NOT NULL COMMENT '1:入账;2:出账',
  `detail_type` int(2) NOT NULL COMMENT '明细类型',
  `expect_datetime` datetime NOT NULL COMMENT '预期入/出账时间',
  `real_datetime` datetime DEFAULT NULL COMMENT '实际入/出账时间',
  `create_user_id` bigint(20) NOT NULL COMMENT '创建人id',
  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改人id',
  `recheck_user_id` bigint(20) DEFAULT NULL COMMENT '复核人id',
  `confirm_user_id` bigint(20) DEFAULT NULL COMMENT '确认人id',
  `is_remind` int(1) NOT NULL DEFAULT '0' COMMENT '是否已设置提醒（0:否;1:是）',
  `split` int(1) NOT NULL DEFAULT '0' COMMENT '是否可分解(0:否;1:是)',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '1:待复核；2：待确认；3：已确认',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除(0：否；1：是)',
  `create_datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8 COMMENT='收支明细表';

-- ----------------------------
-- Table structure for t_balance
-- ----------------------------
DROP TABLE IF EXISTS `t_balance`;
CREATE TABLE `t_balance` (
  `update_datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `balance` double(20,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='余额表';

-- ----------------------------
-- Table structure for t_remind
-- ----------------------------
DROP TABLE IF EXISTS `t_remind`;
CREATE TABLE `t_remind` (
  `account_detail_id` bigint(20) NOT NULL COMMENT '明细id',
  `remind_today` int(1) NOT NULL DEFAULT '0' COMMENT '是否今天提醒(0：否；1：是)',
  `remind_days7` int(1) NOT NULL DEFAULT '0' COMMENT '是否提前7天提醒(0：否；1：是)',
  `remind_days30` int(1) NOT NULL DEFAULT '0' COMMENT '是否提前30天提醒(0：否；1：是)',
  `send_sms` int(1) NOT NULL DEFAULT '0' COMMENT '是否发送短信(0：否；1：是)',
  `send_email` int(1) NOT NULL DEFAULT '0' COMMENT '是否发送邮件(0：否；1：是)',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '0:无效;1:正常',
  `create_user_id` bigint(20) DEFAULT NULL,
  `create_datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`account_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收支明细提醒表';

-- ----------------------------
-- Table structure for t_remind_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_remind_detail`;
CREATE TABLE `t_remind_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_detail_id` bigint(20) NOT NULL COMMENT '明细id',
  `user_id` bigint(20) NOT NULL COMMENT '接收人id',
  `send_sms` int(1) DEFAULT '0' COMMENT '是否发送短信(0:否;1:是)',
  `send_sms_result` int(1) DEFAULT '0' COMMENT '短信发送是否成功(0:否;1:是)',
  `send_email` int(1) DEFAULT '0' COMMENT '是否发送邮件(0:否;1:是)',
  `send_email_result` int(1) DEFAULT '0' COMMENT '邮件发送是否成功(0:否;1:是)',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0:未发送;1:已发送',
  `create_datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='收支明细提醒明细';



INSERT INTO `newtiming_finance`.`sys_department` (`id`, `name`, `create_datetime`) VALUES ('1', '管理者', '2017-06-01 14:04:50');
INSERT INTO `newtiming_finance`.`sys_department` (`id`, `name`, `create_datetime`) VALUES ('2', '财务部', '2017-06-23 09:07:48');
INSERT INTO `newtiming_finance`.`sys_department` (`id`, `name`, `create_datetime`) VALUES ('3', '金融部', '2017-06-23 09:08:04');
INSERT INTO `newtiming_finance`.`sys_department` (`id`, `name`, `create_datetime`) VALUES ('4', '投资部', '2017-06-23 09:08:47');
INSERT INTO `newtiming_finance`.`sys_department` (`id`, `name`, `create_datetime`) VALUES ('5', '法务部', '2017-06-23 09:08:51');
INSERT INTO `newtiming_finance`.`sys_department` (`id`, `name`, `create_datetime`) VALUES ('6', '子公司', '2017-06-23 09:09:21');


INSERT INTO `t_balance` (`update_datetime`, `balance`) VALUES ('2017-06-01 00:00:00', '0.00');


INSERT INTO `sys_permission` (`id`, `name`, `code`, `group_id`, `status`, `create_datetime`) VALUES ('1', '编辑权限', 'FUND_EDIT', '1', '1', '2017-06-01 10:20:12');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `group_id`, `status`, `create_datetime`) VALUES ('2', '复核权限', 'FUND_RECHECK', '1', '1', '2017-06-01 13:54:00');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `group_id`, `status`, `create_datetime`) VALUES ('3', '确认权限', 'FUND_CONFIRM', '1', '1', '2017-06-01 13:54:45');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `group_id`, `status`, `create_datetime`) VALUES ('4', '查看权限', 'FUND_LOOK', '1', '1', '2017-06-01 13:55:34');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `group_id`, `status`, `create_datetime`) VALUES ('5', '编辑权限', 'PROJECT_EDIT', '2', '1', '2017-06-01 13:57:22');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `group_id`, `status`, `create_datetime`) VALUES ('6', '复核权限', 'PROJECT_RECHECK', '2', '1', '2017-06-01 13:57:22');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `group_id`, `status`, `create_datetime`) VALUES ('7', '确认权限', 'PROJECT_CONFIRM', '2', '1', '2017-06-01 13:57:22');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `group_id`, `status`, `create_datetime`) VALUES ('8', '查看权限', 'PROJECT_LOOK', '2', '1', '2017-06-01 13:57:22');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `group_id`, `status`, `create_datetime`) VALUES ('9', '编辑权限', 'BANK_EDIT', '3', '1', '2017-06-01 13:58:02');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `group_id`, `status`, `create_datetime`) VALUES ('10', '复核权限', 'BANK_RECHECK', '3', '1', '2017-06-01 13:58:02');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `group_id`, `status`, `create_datetime`) VALUES ('11', '确认权限', 'BANK_CONFIRM', '3', '1', '2017-06-01 13:58:02');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `group_id`, `status`, `create_datetime`) VALUES ('12', '查看权限', 'BANK_LOOK', '3', '1', '2017-06-01 13:58:02');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `group_id`, `status`, `create_datetime`) VALUES ('13', '编辑权限', 'OTHER_EDIT', '4', '1', '2017-06-01 13:59:00');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `group_id`, `status`, `create_datetime`) VALUES ('14', '复核权限', 'OTHER_RECHECK', '4', '1', '2017-06-01 13:59:00');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `group_id`, `status`, `create_datetime`) VALUES ('15', '确认权限', 'OTHER_CONFIRM', '4', '1', '2017-06-01 13:59:00');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `group_id`, `status`, `create_datetime`) VALUES ('16', '查看权限', 'OTHER_LOOK', '4', '1', '2017-06-01 13:59:00');
INSERT INTO `sys_permission` (`id`, `name`, `code`, `group_id`, `status`, `create_datetime`) VALUES ('17', '资金规划权限', 'ACCOUNT_PLAN', '5', '1', '2017-06-01 14:00:02');
