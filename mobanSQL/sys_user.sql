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

 Date: 19/09/2019 09:12:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `login_name` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录名',
  `nick_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `icon` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'shiro加密盐',
  `tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱地址',
  `locked` tinyint(2) NULL DEFAULT NULL COMMENT '是否锁定 0：不锁，1：锁',
  `create_date` datetime NULL DEFAULT NULL,
  `create_by` int(11) NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `update_by` int(11) NULL DEFAULT NULL,
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `del_flag` tinyint(4) NOT NULL COMMENT '0：不删除，1：删除',
  `is_admin` tinyint(2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '管理员', NULL, '9c5feb7aba88c7c87bc047c7cec7c6e3b63e1894', '08c5900125b80cd2', '15301362587', NULL, 0, '2019-09-18 14:04:26', 1, NULL, NULL, NULL, 0, 1);

SET FOREIGN_KEY_CHECKS = 1;
