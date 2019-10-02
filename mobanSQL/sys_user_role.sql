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

 Date: 19/09/2019 09:12:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);

SET FOREIGN_KEY_CHECKS = 1;
