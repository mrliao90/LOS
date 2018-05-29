CREATE TABLE `sys_user` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL COMMENT '系统账号',
  `realname` varchar(45) DEFAULT NULL COMMENT '真实姓名或者昵称',
  `password` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL COMMENT '用户状态，0 正常，1不正常',
  `headimg` varchar(45) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
;