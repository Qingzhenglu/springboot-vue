CREATE DATABASE IF NOT EXISTS mybatis_plus_db ;

USE `mybatis_plus_db`;
DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `name` varchar(20) DEFAULT NULL COMMENT '用户名字',
  `age` int DEFAULT NULL COMMENT '用户年龄',
  `version` int DEFAULT '1' COMMENT '乐观锁',
  `deleted` int DEFAULT '0' COMMENT '逻辑删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1482996505408204804 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `mybatis_plus_db`.`t_user` (`id`, `name`, `age`, `version`, `deleted`, `create_time`, `update_time`) VALUES ('1', 'markerhub', 22, 1, 0, '2020-04-20 10:44:01', '2020-04-20 20:44:01');
