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


CREATE DATABASE IF NOT EXISTS shiro_test_db ;

USE `shiro_test_db`;

DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
                               `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
                               `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
                               `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接口路径',
                               `method` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '请求方式（0-get；1-post）',
                               `service` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '服务名',
                               `parent_id` int(10) NOT NULL DEFAULT 0 COMMENT '父级权限id',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统权限表' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
                         `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
                         `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '名称',
                         `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission`  (
                                    `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
                                    `role_id` int(10) UNSIGNED NOT NULL COMMENT '角色id',
                                    `permission_id` int(10) UNSIGNED NOT NULL COMMENT '权限id',
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色-权限关联表' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
                              `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
                              `role_id` int(10) UNSIGNED NOT NULL COMMENT '角色id，数据来源于role表的主键',
                              `user_id` int(10) UNSIGNED NOT NULL COMMENT '用户id，数据来源于user表的主键',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户-角色关系表表' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
                         `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
                         `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
                         `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
                         `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
                         `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
                         `gender` tinyint(4) UNSIGNED NOT NULL COMMENT '性别',
                         `enabled` tinyint(4) UNSIGNED NOT NULL COMMENT '是否启用（0-未启用；1-启用中）',
                         `last_login_time` datetime NULL DEFAULT NULL COMMENT '上一次登录时间',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;


INSERT INTO `t_role` VALUES (1, '超级管理员', '超管，拥有最高权限');
INSERT INTO `t_role` VALUES (2, '系统管理员', '管理员，拥有操作权限');

INSERT INTO `t_user` VALUES ('超级管理员', 'admin', '123456', '13500000001', 1, 1, '2023-10-01 00:00:01');
INSERT INTO `t_user` VALUES ('系统管理员', 'normal', '123456', '13800000001', 2, 1, '2023-10-01 00:00:01');
INSERT INTO `t_user` VALUES ('系统管理员', 'root', '123456', '13800000001', 2, 1, '2023-10-01 00:00:01');

INSERT INTO `t_user_role` VALUES (1, 1, 1);
INSERT INTO `t_user_role` VALUES (2, 2, 2);

INSERT INTO `t_permission` VALUES (1, '用户管理', '/user/manager', 0, 'manager', 0);
INSERT INTO `t_permission` VALUES (2, '用户列表查询', '/user/list', 1, 'list', 1);
INSERT INTO `t_permission` VALUES (3, '删除用户', '/user/delete', 0, 'delete', 1);
INSERT INTO `t_permission` VALUES (4, '更新用户', '/user/update', 1, 'update', 1);
INSERT INTO `t_permission` VALUES (5, '新增用户', '/user/save', 1, 'save', 1);
INSERT INTO `t_permission` VALUES (6, '用户详情', '/user/getById', 0, 'getById', 1);

INSERT INTO `t_role_permission` VALUES (1, 1, 1);
INSERT INTO `t_role_permission` VALUES (2, 1, 2);
INSERT INTO `t_role_permission` VALUES (3, 1, 3);
INSERT INTO `t_role_permission` VALUES (4, 1, 4);
INSERT INTO `t_role_permission` VALUES (5, 1, 5);
INSERT INTO `t_role_permission` VALUES (6, 1, 6);

INSERT INTO `t_role_permission` VALUES (7, 2, 1);
INSERT INTO `t_role_permission` VALUES (8, 2, 2);
INSERT INTO `t_role_permission` VALUES (9, 2, 3);
INSERT INTO `t_role_permission` VALUES (10, 2, 4);
INSERT INTO `t_role_permission` VALUES (11, 2, 5);
INSERT INTO `t_role_permission` VALUES (12, 2, 6);
