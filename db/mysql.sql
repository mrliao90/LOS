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

CREATE TABLE `sys_dict` (
  `dict_value` varchar(64) NOT NULL COMMENT '字典值，固定不变的',
  `dict_name` varchar(50) NOT NULL COMMENT '字典名称',
  PRIMARY KEY (`dict_value`),
  UNIQUE KEY `type` (`dict_value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典表';

CREATE TABLE `sys_dict_data` (
  `id` varchar(64) NOT NULL,
  `dict_value` varchar(45) DEFAULT NULL,
  `dict_data_name` varchar(45) DEFAULT NULL,
  `dict_data_value` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





