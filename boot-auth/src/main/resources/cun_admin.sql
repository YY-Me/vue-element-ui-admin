/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : cun_admin

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 29/07/2020 10:18:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_biz_tenant
-- ----------------------------
DROP TABLE IF EXISTS `sys_biz_tenant`;
CREATE TABLE `sys_biz_tenant`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '内部id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '租户名称',
  `app_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'app_id',
  `app_secret` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'app_secret',
  `qps` int(5) NOT NULL COMMENT 'qps，默认0（无限制）',
  `day_limit` int(11) NOT NULL COMMENT '日请求上限，默认0（无限制）',
  `is_enable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否启用，默认1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统-租户列表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_biz_tenant
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'menu id',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '唯一，找路由使用',
  `permission` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `p_id` int(11) NOT NULL DEFAULT 0 COMMENT '父类id，默认0（第一级）',
  `t_id` int(11) NOT NULL DEFAULT 0 COMMENT '顶部id，默认0',
  `type` int(1) NOT NULL DEFAULT 1 COMMENT '1：菜单，2：按钮',
  `m_type` int(1) NOT NULL DEFAULT 1 COMMENT '1：左侧菜单，2：顶部菜单',
  `path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路径，一级加/，其他不要加/',
  `icon` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `sort` int(3) NOT NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统-菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '首页', NULL, 'index', 0, 12, 1, 1, '/index', 'dashboard', 1);
INSERT INTO `sys_menu` VALUES (2, '控制台', 'dashboard', 'dashboard', 1, 0, 1, 1, 'console', 'dashboard', 0);
INSERT INTO `sys_menu` VALUES (3, '系统用户', NULL, 'sys:user:index', 0, 11, 1, 1, '/system', 'system-user', 3);
INSERT INTO `sys_menu` VALUES (4, '用户管理', 'systemUser', 'sys:user:list', 3, 0, 1, 1, 'user', 'system-user', 0);
INSERT INTO `sys_menu` VALUES (5, '权限中心', NULL, 'sys:permission:index', 0, 11, 1, 1, '/permission', 'permission', 4);
INSERT INTO `sys_menu` VALUES (6, '角色管理', 'systemRole', 'sys:role:list', 5, 0, 1, 1, 'role', 'role', 0);
INSERT INTO `sys_menu` VALUES (7, '菜单管理', 'systemMenu', 'sys:menu:list', 5, 0, 1, 1, 'menu', 'menu', 0);
INSERT INTO `sys_menu` VALUES (8, '租户管理', NULL, 'sys:tenant:index', 0, 12, 1, 1, '/tenant', 'tenant', 2);
INSERT INTO `sys_menu` VALUES (9, '租户管理', 'tenant', 'sys:tenant:list', 8, 0, 1, 1, 'tenant', 'tenant', 0);
INSERT INTO `sys_menu` VALUES (11, '系统配置', NULL, 'top:user:center', 0, 0, 1, 2, '/top2', 'system-config', 2);
INSERT INTO `sys_menu` VALUES (12, '业务系统', NULL, 'top:biz:center', 0, 0, 1, 2, '/top1', 'biz', 1);
INSERT INTO `sys_menu` VALUES (14, '顶部菜单', '', 'top:test:1', 0, 0, 1, 2, '/top3', 'menu', 3);
INSERT INTO `sys_menu` VALUES (15, '测试1', 'test1', 'top:test:1:1', 14, 0, 1, 2, 'test1', 'test', 1);
INSERT INTO `sys_menu` VALUES (16, '测试2', 'test2', 'top:test:1:2', 14, 0, 1, 2, 'test2', 'test', 2);
INSERT INTO `sys_menu` VALUES (19, '增加菜单', NULL, 'sys:menu:add', 7, 0, 2, 1, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (20, '删除菜单', NULL, 'sys:menu:del', 7, 0, 2, 1, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (21, '修改菜单', NULL, 'sys:menu:update', 7, 0, 2, 1, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (23, '增加用户', NULL, 'sys:user:add', 4, 0, 2, 1, '', '', 0);
INSERT INTO `sys_menu` VALUES (24, '删除用户', NULL, 'sys:user:del', 4, 0, 2, 1, '', '', 0);
INSERT INTO `sys_menu` VALUES (25, '修改用户', NULL, 'sys:user:update', 4, 0, 2, 1, '', '', 0);
INSERT INTO `sys_menu` VALUES (26, '增加角色', NULL, 'sys:role:add', 6, 0, 2, 1, '', '', 0);
INSERT INTO `sys_menu` VALUES (27, '删除角色', NULL, 'sys:role:del', 6, 0, 2, 1, '', '', 0);
INSERT INTO `sys_menu` VALUES (28, '修改角色', NULL, 'sys:role:update', 6, 0, 2, 1, '', '', 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `is_enable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否启用，默认1',
  `is_system` tinyint(1) NOT NULL DEFAULT 0 COMMENT '系统级别，禁止删除，默认0',
  `update_user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人账号',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统-角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '顶级管理员', '谨慎！，一不注意就把自己玩死', 1, 1, 'admin', '2020-05-18 16:00:39', '2020-05-18 16:07:10');
INSERT INTO `sys_role` VALUES (2, '测试', '测试手术', 1, 0, 'admin', '2020-05-26 13:50:44', '2020-05-27 09:43:09');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` int(11) NOT NULL COMMENT 'sys role id',
  `menu_id` int(11) NOT NULL COMMENT 'sys menu id',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE,
  UNIQUE INDEX `uk_role_id_menu_id`(`role_id`, `menu_id`) USING BTREE COMMENT '唯一'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统-角色-菜单（权限）关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 4);
INSERT INTO `sys_role_menu` VALUES (1, 5);
INSERT INTO `sys_role_menu` VALUES (1, 6);
INSERT INTO `sys_role_menu` VALUES (1, 7);
INSERT INTO `sys_role_menu` VALUES (1, 8);
INSERT INTO `sys_role_menu` VALUES (1, 9);
INSERT INTO `sys_role_menu` VALUES (1, 11);
INSERT INTO `sys_role_menu` VALUES (1, 12);
INSERT INTO `sys_role_menu` VALUES (1, 14);
INSERT INTO `sys_role_menu` VALUES (1, 15);
INSERT INTO `sys_role_menu` VALUES (1, 16);
INSERT INTO `sys_role_menu` VALUES (1, 19);
INSERT INTO `sys_role_menu` VALUES (1, 20);
INSERT INTO `sys_role_menu` VALUES (1, 21);
INSERT INTO `sys_role_menu` VALUES (1, 23);
INSERT INTO `sys_role_menu` VALUES (1, 24);
INSERT INTO `sys_role_menu` VALUES (1, 25);
INSERT INTO `sys_role_menu` VALUES (1, 26);
INSERT INTO `sys_role_menu` VALUES (1, 27);
INSERT INTO `sys_role_menu` VALUES (1, 28);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，内部id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `nick_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `is_enable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '账户是否启用，默认1',
  `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '账户是否删除，默认0',
  `update_user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人账号',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统-用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '顶级管理员', '$2a$10$wstYYE51yTB8MI4do5obpeC7iw/Bngv5Z39mloL0NPQvjabD..knC', NULL, 1, 0, 'admin', '2020-05-15 16:21:02', '2020-07-02 10:41:31');
INSERT INTO `sys_user` VALUES (2, 'test001', 'test001', '$2a$10$6G1tv9izcOxyuavexuS9/OSX9LYbWBGXKVtzwQ2znJEtxbYIxN5Ai', NULL, 1, 0, 'admin', '2020-05-18 17:23:53', '2020-05-18 17:23:53');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` int(11) NOT NULL COMMENT 'sys user id',
  `role_id` int(11) NOT NULL COMMENT 'sys role id',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  UNIQUE INDEX `uk_user_id_role_id`(`user_id`, `role_id`) USING BTREE COMMENT '唯一'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统-用户-角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);

SET FOREIGN_KEY_CHECKS = 1;
