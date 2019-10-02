/*
 Navicat Premium Data Transfer

 Source Server         : test001
 Source Server Type    : MySQL
 Source Server Version : 50714
 Source Host           : localhost:3306
 Source Schema         : moban

 Target Server Type    : MySQL
 Target Server Version : 50714
 File Encoding         : 65001

 Date: 20/09/2019 17:49:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父菜单',
  `level` bigint(2) NULL DEFAULT NULL COMMENT '菜单层级',
  `parent_ids` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父菜单联集',
  `sort` smallint(6) NULL DEFAULT NULL COMMENT '排序',
  `href` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接地址',
  `target` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '打开方式',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `bg_color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '显示背景色',
  `is_show` tinyint(2) NULL DEFAULT NULL COMMENT '是否显示',
  `permission` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `create_by` int(11) NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `update_by` int(11) NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `del_flag` tinyint(2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', NULL, 1, '1,', 20, NULL, NULL, NULL, NULL, 1, '', 1, '2019-09-18 13:38:21', NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (2, '权限管理', 1, 2, '1,2,', 20, '/admin/menu', NULL, NULL, '#d4573b', 1, 'sys:menu:list', 1, '2019-09-18 13:46:42', NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (3, '角色管理', 1, 2, '1,3,', 10, '/admin/role', NULL, NULL, '#23ab9', 1, 'sys:role:list', 1, '2019-09-18 14:56:09', NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (4, '新增权限', 2, 3, '1,2,4,', 0, NULL, NULL, NULL, NULL, 0, 'sys:menu:add', 1, '2019-09-18 15:32:14', NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (5, '编辑权限', 2, 3, '1,2,5', 0, NULL, NULL, NULL, NULL, 0, 'sys:menu:edit', 1, '2019-09-18 15:35:45', NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (6, '删除权限', 2, 3, '1,2,6', 0, NULL, NULL, NULL, NULL, 0, 'sys:menu:delete', 1, '2019-09-18 15:36:16', NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (7, '新增角色', 3, 3, '1,3,7', 0, NULL, NULL, NULL, NULL, 0, 'sys:role:add', 1, '2019-09-18 15:54:36', NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (8, '修改角色', 3, 3, '1,3,8', 0, NULL, NULL, NULL, NULL, 0, 'sys:role:edit', 1, '2019-09-18 15:55:15', NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (9, '删除角色', 3, 3, '1,3,9', 0, NULL, NULL, NULL, NULL, 0, 'sys:role:delete', 1, '2019-09-18 15:55:47', NULL, NULL, NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
