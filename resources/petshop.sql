/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : petshop

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-08-18 22:10:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `ac_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '账目id',
  `deal_type` int(1) DEFAULT NULL COMMENT '交易类型：1.商店卖给主人；2.主人卖给商店',
  `pet_id` int(4) DEFAULT NULL COMMENT '宠物id',
  `seller_id` int(4) DEFAULT NULL COMMENT '卖家id',
  `buyer_id` int(4) DEFAULT NULL COMMENT '买家id',
  `price` int(4) DEFAULT NULL COMMENT '价格',
  `deal_time` datetime DEFAULT NULL COMMENT '交易时间',
  PRIMARY KEY (`ac_id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('2', '1', '2', '1', '2', '500', '2018-09-04 16:23:19');
INSERT INTO `account` VALUES ('3', '1', '1', '1', '1', '100', '2018-09-04 17:01:31');
INSERT INTO `account` VALUES ('4', '2', '1', '2', '1', '8000', '2018-09-04 17:02:47');
INSERT INTO `account` VALUES ('5', '1', '3', '3', '3', '98', '2019-01-10 17:08:58');
INSERT INTO `account` VALUES ('6', '1', '1', '1', '3', '60', '2019-01-10 17:14:29');
INSERT INTO `account` VALUES ('7', '1', '1', '1', '3', '50', '2019-01-10 17:17:41');
INSERT INTO `account` VALUES ('8', '1', '3', '3', '1', '60', '2019-01-11 10:43:48');
INSERT INTO `account` VALUES ('9', '1', '5', '3', '2', '50', '2019-01-11 11:29:00');
INSERT INTO `account` VALUES ('10', '2', '2', '1', '2', '80', '2019-01-11 12:16:49');
INSERT INTO `account` VALUES ('11', '2', '3', '1', '3', '80', '2019-01-11 12:19:10');
INSERT INTO `account` VALUES ('12', '1', '2', '3', '1', '60', '2019-01-11 14:53:36');
INSERT INTO `account` VALUES ('13', '1', '3', '3', '1', '80', '2019-01-11 14:55:02');
INSERT INTO `account` VALUES ('14', '1', '6', '1', '1', '70', '2019-01-11 15:35:55');
INSERT INTO `account` VALUES ('15', '2', '2', '1', '1', '100', '2019-01-11 16:30:53');
INSERT INTO `account` VALUES ('16', '1', '2', '1', '1', '200', '2019-01-12 17:38:05');
INSERT INTO `account` VALUES ('17', '1', '2', '2', '1', '200', '2019-01-12 18:32:07');
INSERT INTO `account` VALUES ('18', '1', '1', '2', '1', '300', '2019-01-12 18:45:16');
INSERT INTO `account` VALUES ('19', '1', '1', '2', '1', '100', '2019-01-12 18:49:27');
INSERT INTO `account` VALUES ('20', '1', '2', '2', '1', '100', '2019-01-12 18:57:16');
INSERT INTO `account` VALUES ('21', '1', '2', '3', '1', '200', '2019-01-12 19:04:14');
INSERT INTO `account` VALUES ('22', '1', '1', '2', '1', '100', '2019-01-12 19:09:31');
INSERT INTO `account` VALUES ('23', '1', '1', '2', '1', '100', '2019-01-12 19:19:10');
INSERT INTO `account` VALUES ('24', '1', '2', '2', '1', '100', '2019-01-12 19:21:23');
INSERT INTO `account` VALUES ('25', '1', '1', '2', '1', '100', '2019-01-12 19:35:06');
INSERT INTO `account` VALUES ('26', '1', '2', '2', '1', '100', '2019-01-12 19:39:59');
INSERT INTO `account` VALUES ('27', '1', '1', '2', '1', '100', '2019-01-13 06:39:51');
INSERT INTO `account` VALUES ('28', '2', '1', '1', '2', '300', '2019-01-13 06:40:56');
INSERT INTO `account` VALUES ('29', '2', '1', '1', '2', '300', '2019-01-13 06:40:56');
INSERT INTO `account` VALUES ('30', '2', '3', '1', '1', '100', '2019-01-13 06:43:53');
INSERT INTO `account` VALUES ('31', '2', '3', '1', '1', '100', '2019-01-13 06:43:53');
INSERT INTO `account` VALUES ('32', '2', '7', '1', '1', '100', '2019-01-13 06:45:14');
INSERT INTO `account` VALUES ('33', '2', '7', '1', '1', '100', '2019-01-13 06:45:14');
INSERT INTO `account` VALUES ('34', '1', '1', '2', '1', '200', '2019-01-13 06:48:04');
INSERT INTO `account` VALUES ('35', '1', '2', '2', '1', '200', '2019-01-13 21:03:50');
INSERT INTO `account` VALUES ('36', '1', '7', '1', '1', '50', '2019-01-13 21:54:36');
INSERT INTO `account` VALUES ('37', '2', '7', '1', '3', '200', '2019-01-13 21:55:00');
INSERT INTO `account` VALUES ('38', '2', '7', '1', '3', '200', '2019-01-13 21:55:00');
INSERT INTO `account` VALUES ('39', '1', '7', '3', '1', '300', '2019-01-13 21:55:57');
INSERT INTO `account` VALUES ('40', '1', '3', '1', '1', '50', '2019-01-13 23:57:07');
INSERT INTO `account` VALUES ('41', '2', '1', '1', '1', '100', '2019-01-14 00:39:47');
INSERT INTO `account` VALUES ('42', '2', '1', '1', '1', '100', '2019-01-14 00:39:47');
INSERT INTO `account` VALUES ('43', '2', '2', '1', '1', '100', '2019-01-14 00:40:01');
INSERT INTO `account` VALUES ('44', '2', '2', '1', '1', '100', '2019-01-14 00:40:01');
INSERT INTO `account` VALUES ('45', '2', '7', '1', '1', '150', '2019-01-14 00:41:11');
INSERT INTO `account` VALUES ('46', '2', '7', '1', '1', '150', '2019-01-14 00:41:11');
INSERT INTO `account` VALUES ('47', '1', '7', '1', '1', '350', '2019-01-14 01:17:54');
INSERT INTO `account` VALUES ('48', '2', '7', '1', '3', '800', '2019-01-14 01:21:24');
INSERT INTO `account` VALUES ('49', '2', '7', '1', '3', '800', '2019-01-14 01:21:24');
INSERT INTO `account` VALUES ('50', '2', '6', '1', '1', '1000', '2019-01-14 01:22:08');
INSERT INTO `account` VALUES ('51', '2', '6', '1', '1', '1000', '2019-01-14 01:22:08');
INSERT INTO `account` VALUES ('52', '1', '2', '1', '1', '300', '2019-01-14 05:28:58');
INSERT INTO `account` VALUES ('53', '2', '2', '1', '1', '200', '2019-01-14 05:29:38');
INSERT INTO `account` VALUES ('54', '1', '2', '1', '1', '200', '2019-01-14 05:29:53');
INSERT INTO `account` VALUES ('55', '2', '2', '1', '1', '200', '2019-01-14 05:30:22');
INSERT INTO `account` VALUES ('56', '1', '2', '1', '1', '200', '2019-01-14 05:30:41');
INSERT INTO `account` VALUES ('57', '1', '7', '1', '1', '300', '2019-01-14 05:34:29');
INSERT INTO `account` VALUES ('58', '1', '1', '1', '1', '200', '2019-01-14 05:38:23');
INSERT INTO `account` VALUES ('59', '1', '6', '1', '1', '200', '2019-01-14 05:38:45');
INSERT INTO `account` VALUES ('60', '2', '1', '1', '1', '100', '2019-01-14 05:44:37');
INSERT INTO `account` VALUES ('61', '1', '1', '1', '1', '200', '2019-01-14 05:45:11');
INSERT INTO `account` VALUES ('62', '2', '1', '1', '1', '100', '2019-01-14 05:54:32');
INSERT INTO `account` VALUES ('63', '2', '2', '1', '1', '100', '2019-01-14 05:54:42');
INSERT INTO `account` VALUES ('64', '1', '2', '1', '1', '200', '2019-01-14 05:55:04');
INSERT INTO `account` VALUES ('65', '2', '3', '1', '2', '200', '2019-01-14 05:55:24');
INSERT INTO `account` VALUES ('66', '1', '1', '1', '1', '100', '2019-01-14 06:12:45');
INSERT INTO `account` VALUES ('67', '2', '1', '1', '1', '200', '2019-01-14 06:12:56');
INSERT INTO `account` VALUES ('68', '1', '7', '1', '1', '300', '2019-01-14 06:13:07');
INSERT INTO `account` VALUES ('69', '1', '3', '2', '1', '200', '2019-01-14 06:13:26');
INSERT INTO `account` VALUES ('70', '2', '6', '1', '2', '200', '2019-01-14 06:13:57');
INSERT INTO `account` VALUES ('71', '1', '6', '2', '1', '400', '2019-01-14 06:14:10');
INSERT INTO `account` VALUES ('72', '2', '7', '1', '1', '200', '2019-01-14 07:06:21');
INSERT INTO `account` VALUES ('73', '1', '7', '1', '1', '500', '2019-01-14 07:06:53');
INSERT INTO `account` VALUES ('74', '2', '7', '1', '3', '200', '2019-01-14 07:08:59');
INSERT INTO `account` VALUES ('75', '1', '4', '1', '1', '100', '2019-01-14 07:09:15');
INSERT INTO `account` VALUES ('76', '1', '1', '1', '1', '400', '2019-01-14 07:09:31');
INSERT INTO `account` VALUES ('77', '2', '1', '1', '1', '200', '2019-01-14 07:10:44');
INSERT INTO `account` VALUES ('78', '2', '2', '1', '1', '200', '2019-01-14 07:10:58');
INSERT INTO `account` VALUES ('79', '2', '3', '1', '1', '200', '2019-01-14 07:11:09');
INSERT INTO `account` VALUES ('80', '2', '4', '1', '1', '100', '2019-01-14 07:48:43');
INSERT INTO `account` VALUES ('81', '1', '1', '1', '1', '200', '2019-01-14 07:48:58');
INSERT INTO `account` VALUES ('82', '2', '1', '1', '1', '200', '2019-01-14 07:49:12');
INSERT INTO `account` VALUES ('83', '1', '1', '1', '1', '200', '2019-01-14 07:49:20');
INSERT INTO `account` VALUES ('84', '2', '1', '1', '1', '200', '2019-01-14 07:49:56');
INSERT INTO `account` VALUES ('85', '1', '1', '1', '1', '200', '2019-01-14 07:50:19');
INSERT INTO `account` VALUES ('86', '1', '2', '1', '1', '200', '2019-01-14 07:50:29');
INSERT INTO `account` VALUES ('87', '2', '1', '1', '1', '200', '2019-01-14 07:50:38');
INSERT INTO `account` VALUES ('88', '2', '5', '1', '2', '12', '2019-01-14 08:10:17');

-- ----------------------------
-- Table structure for `owner`
-- ----------------------------
DROP TABLE IF EXISTS `owner`;
CREATE TABLE `owner` (
  `ow_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '主人id',
  `ow_name` varchar(10) DEFAULT NULL COMMENT '主人姓名',
  `password` varchar(10) DEFAULT NULL COMMENT '主人密码',
  `money` int(4) DEFAULT NULL COMMENT '元宝数',
  PRIMARY KEY (`ow_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of owner
-- ----------------------------
INSERT INTO `owner` VALUES ('1', '老王', '123456', '1100');
INSERT INTO `owner` VALUES ('2', '老张', '666', '1012');
INSERT INTO `owner` VALUES ('3', '老李', '123456', '1000');

-- ----------------------------
-- Table structure for `pet`
-- ----------------------------
DROP TABLE IF EXISTS `pet`;
CREATE TABLE `pet` (
  `p_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '宠物id',
  `p_name` varchar(10) DEFAULT NULL COMMENT '宠物名',
  `type_name` varchar(10) DEFAULT NULL COMMENT '宠物类型',
  `health` int(4) DEFAULT NULL COMMENT '健康值',
  `love` int(4) DEFAULT NULL COMMENT '亲密度',
  `birthDay` datetime DEFAULT NULL COMMENT '出生日期',
  `owner_id` int(4) DEFAULT NULL COMMENT '宠物主人id',
  `store_id` int(4) DEFAULT NULL COMMENT '宠物商店id',
  `can_sell` int(1) NOT NULL COMMENT '是否培育完成，0.培育中,1.新培育完成，2.卖出，3.库存-回收',
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pet
-- ----------------------------
INSERT INTO `pet` VALUES ('1', '旺财', '狗狗', '90', '100', '2018-09-01 00:00:00', '0', '1', '3');
INSERT INTO `pet` VALUES ('2', '小黑', '猫咪', '100', '90', '2018-09-02 00:00:00', '1', '0', '2');
INSERT INTO `pet` VALUES ('3', '小强', '狗狗', '91', '99', '2019-01-01 00:00:00', '0', '1', '3');
INSERT INTO `pet` VALUES ('4', '小花', '猫咪', '110', '100', '2019-01-10 00:00:00', '0', '1', '3');
INSERT INTO `pet` VALUES ('5', '花花', '兔子', '92', '92', '2019-01-10 11:26:57', '0', '1', '3');
INSERT INTO `pet` VALUES ('6', '小白', '仓鼠', '93', '93', '2019-01-09 00:00:00', '1', '0', '2');
INSERT INTO `pet` VALUES ('7', '贵孙儿', '儿子', '11', '11', '2019-01-11 00:00:00', '0', '3', '3');

-- ----------------------------
-- Table structure for `store`
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
  `st_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '商店id',
  `st_name` varchar(10) DEFAULT NULL COMMENT '商店名',
  `password` varchar(10) DEFAULT NULL COMMENT '商店密码',
  `balance` int(4) DEFAULT NULL COMMENT '商店余额',
  PRIMARY KEY (`st_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES ('1', '亮亮宠物店', '233333', '2588');
INSERT INTO `store` VALUES ('2', '闪闪宠物店', '466666', '1600');
INSERT INTO `store` VALUES ('3', '晶晶宠物店', '555555', '100');
