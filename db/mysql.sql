CREATE TABLE `sys_user` (
  `id` varchar(64) NOT NULL,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `realname` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `headimg` varchar(45) DEFAULT NULL,
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT NULL COMMENT '删除标记  0，正常 1 删除',
  `create_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户';

