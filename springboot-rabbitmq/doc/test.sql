/*
Navicat MySQL Data Transfer

Source Server         : native-93
Source Server Version : 50646
Source Host           : 47.107.152.93:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50646
File Encoding         : 65001

Date: 2020-04-16 14:25:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for login_log
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `type` int(11) NOT NULL COMMENT '日志类型:1登录 2登出',
  `description` varchar(255) DEFAULT NULL COMMENT '日志描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2045 DEFAULT CHARSET=utf8mb4 COMMENT='登录日志表';

-- ----------------------------
-- Records of login_log
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unq_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
