/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.17-log : Database - petshop
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`petshop` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `petshop`;

/*Table structure for table `account` */

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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `account` */

insert  into `account`(`ac_id`,`deal_type`,`pet_id`,`seller_id`,`buyer_id`,`price`,`deal_time`) values (2,1,2,1,2,500,'2018-09-04 16:23:19'),(3,1,1,1,1,100,'2018-09-04 17:01:31'),(4,2,1,2,1,8000,'2018-09-04 17:02:47'),(5,1,3,3,3,98,'2019-01-10 17:08:58'),(6,1,1,1,3,60,'2019-01-10 17:14:29'),(7,1,1,1,3,50,'2019-01-10 17:17:41'),(8,1,3,3,1,60,'2019-01-11 10:43:48'),(9,1,5,3,2,50,'2019-01-11 11:29:00'),(10,2,2,1,2,80,'2019-01-11 12:16:49'),(11,2,3,1,3,80,'2019-01-11 12:19:10'),(12,1,2,3,1,60,'2019-01-11 14:53:36'),(13,1,3,3,1,80,'2019-01-11 14:55:02'),(14,1,6,1,1,70,'2019-01-11 15:35:55'),(15,2,2,1,1,100,'2019-01-11 16:30:53');

/*Table structure for table `owner` */

DROP TABLE IF EXISTS `owner`;

CREATE TABLE `owner` (
  `ow_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '主人id',
  `ow_name` varchar(10) DEFAULT NULL COMMENT '主人姓名',
  `password` varchar(10) DEFAULT NULL COMMENT '主人密码',
  `money` int(4) DEFAULT NULL COMMENT '元宝数',
  PRIMARY KEY (`ow_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `owner` */

insert  into `owner`(`ow_id`,`ow_name`,`password`,`money`) values (1,'老王','123456',8118),(2,'老张','666',948),(3,'老李','123456',-110);

/*Table structure for table `pet` */

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `pet` */

insert  into `pet`(`p_id`,`p_name`,`type_name`,`health`,`love`,`birthDay`,`owner_id`,`store_id`,`can_sell`) values (1,'旺财','狗狗',90,100,'2018-09-01 11:36:35',3,0,2),(2,'小黑','猫咪',100,90,'2018-09-02 11:37:35',0,1,3),(3,'小强','狗狗',91,99,'2019-01-01 16:20:03',1,0,2),(4,'小花','猫咪',110,100,'2019-01-10 09:09:01',0,2,0),(5,'花花','兔子',92,92,'2019-01-10 11:26:57',2,0,2),(6,'小白','仓鼠',93,93,'2019-01-09 11:27:41',1,0,2);

/*Table structure for table `store` */

DROP TABLE IF EXISTS `store`;

CREATE TABLE `store` (
  `st_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '商店id',
  `st_name` varchar(10) DEFAULT NULL COMMENT '商店名',
  `password` varchar(10) DEFAULT NULL COMMENT '商店密码',
  `balance` int(4) DEFAULT NULL COMMENT '商店余额',
  PRIMARY KEY (`st_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `store` */

insert  into `store`(`st_id`,`st_name`,`password`,`balance`) values (1,'亮亮宠物店','233333',10120),(2,'闪闪宠物店','466666',2600),(3,'晶晶宠物店','555555',210);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
