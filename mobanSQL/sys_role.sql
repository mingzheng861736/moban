/*
 Navicat Premium Data Transfer

 Source Server         : jiuba 
 Source Server Type    : MySQL
 Source Server Version : 50553
 Source Host           : 192.168.1.166:3306
 Source Schema         : bar

 Target Server Type    : MySQL
 Target Server Version : 50553
 File Encoding         : 65001

 Date: 19/09/2019 09:11:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `create_date` datetime NULL DEFAULT NULL,
  `create_by` int(11) NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `update_by` int(11) NULL DEFAULT NULL,
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `del_flag` tinyint(2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', '2019-09-18 11:41:14', 1, '2019-09-18 15:16:47', 1, '管理员', 0);

SET FOREIGN_KEY_CHECKS = 1;
