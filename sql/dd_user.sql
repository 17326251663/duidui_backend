/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.24 : Database - dd_user
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dd_user` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `dd_user`;

/*Table structure for table `dd_user` */

DROP TABLE IF EXISTS `dd_user`;

CREATE TABLE `dd_user` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `telephone` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `email` varchar(60) DEFAULT NULL COMMENT '邮箱',
  `password` varchar(50) NOT NULL COMMENT '秘密',
  `imageUrl` varchar(200) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `telephone` (`telephone`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

/*Data for the table `dd_user` */

insert  into `dd_user`(`uid`,`username`,`telephone`,`email`,`password`,`imageUrl`) values (39,'不是很蔡','17326251663',NULL,'17326251663gg','http://img.redyu.run/FohnyyKGtaLgceIt4BtuQFMR3TMr'),(40,'孤独诗人','17326251662',NULL,'17326251662..','http://img.redyu.run/FohnyyKGtaLgceIt4BtuQFMR3TMr');

/*Table structure for table `dd_user_attention_count` */

DROP TABLE IF EXISTS `dd_user_attention_count`;

CREATE TABLE `dd_user_attention_count` (
  `aid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) NOT NULL COMMENT '用户id',
  `fansId` int(11) NOT NULL COMMENT '粉丝id',
  `attentionTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '关注时间',
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `dd_user_attention_count` */

insert  into `dd_user_attention_count`(`aid`,`uid`,`fansId`,`attentionTime`) values (5,39,39,'2020-03-21 16:05:23');

/*Table structure for table `dd_user_createtime` */

DROP TABLE IF EXISTS `dd_user_createtime`;

CREATE TABLE `dd_user_createtime` (
  `uid` int(11) NOT NULL COMMENT '用户id',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '账号创建时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dd_user_createtime` */

insert  into `dd_user_createtime`(`uid`,`createtime`) values (39,'2020-02-11 12:00:00'),(40,'2020-02-20 10:31:43');

/*Table structure for table `dd_user_num` */

DROP TABLE IF EXISTS `dd_user_num`;

CREATE TABLE `dd_user_num` (
  `nid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  `anum` int(11) DEFAULT '0' COMMENT '关注数量',
  `fnum` int(11) DEFAULT '0' COMMENT '粉丝数量',
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `dd_user_num` */

insert  into `dd_user_num`(`nid`,`uid`,`anum`,`fnum`) values (1,39,1,1),(2,40,0,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
