/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.24 : Database - dd_image
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dd_image` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `dd_image`;

/*Table structure for table `dd_album` */

DROP TABLE IF EXISTS `dd_album`;

CREATE TABLE `dd_album` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `aname` varchar(20) NOT NULL DEFAULT '默认' COMMENT '专辑名称',
  `uid` int(11) NOT NULL COMMENT '专辑所属用户',
  `info` varchar(200) DEFAULT NULL COMMENT '专辑描述',
  `label` varchar(50) DEFAULT NULL COMMENT '专辑标签',
  PRIMARY KEY (`aid`),
  UNIQUE KEY `aname` (`aname`,`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

/*Data for the table `dd_album` */

insert  into `dd_album`(`aid`,`aname`,`uid`,`info`,`label`) values (12,'漫头',39,NULL,NULL),(18,'明星',39,'明星','明星'),(19,'灿烂的微笑',40,NULL,NULL),(26,'测试',39,NULL,NULL),(27,'111111111',39,'111111111',' 设计 旅行');

/*Table structure for table `dd_image_review` */

DROP TABLE IF EXISTS `dd_image_review`;

CREATE TABLE `dd_image_review` (
  `rid` bigint(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  `parentId` int(11) DEFAULT '0' COMMENT '父亲评论id,没有为0',
  `info` varchar(200) NOT NULL COMMENT '评论内容',
  `infotime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '评论时间',
  `lookStatus` int(1) NOT NULL DEFAULT '0' COMMENT '评论查看状态',
  `imgid` int(11) NOT NULL COMMENT '所属img集',
  `likeNum` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数量',
  `toUid` int(11) NOT NULL COMMENT '接收人id',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `dd_image_review` */

insert  into `dd_image_review`(`rid`,`uid`,`parentId`,`info`,`infotime`,`lookStatus`,`imgid`,`likeNum`,`toUid`) values (8,40,0,'一楼~笑','2020-03-10 16:17:18',1,20,0,39),(9,40,0,'1231','2020-03-10 16:17:18',1,20,0,39),(10,40,0,'1111111111111','2020-03-10 16:17:18',1,20,0,39),(11,40,0,'爱恋','2020-03-10 16:17:18',1,24,1,39),(12,39,0,'123123123','2020-03-10 16:11:04',1,20,1,39),(13,39,0,'1231313123234','2020-03-10 16:11:04',1,20,0,39),(14,39,0,'123131','2020-03-10 16:11:04',1,20,0,39),(15,39,0,'1231311222222222222222222222','2020-03-10 16:11:04',1,20,0,39),(16,39,0,'1231233112312313123','2020-03-10 16:11:04',1,20,0,39),(17,39,0,'eeeeeeeeeeee','2020-03-10 16:11:04',1,20,0,39),(18,39,0,'eeeeeeeeeeee','2020-03-10 16:11:04',1,20,0,39),(19,39,0,'1231312313111111111111111111111111111111','2020-03-10 16:11:04',1,20,1,39),(20,39,0,'12312313\n','2020-03-10 23:49:11',1,33,0,39),(21,40,0,'测试删除','2020-03-10 23:55:30',1,35,0,39),(22,39,0,'123131313111111111111','2020-03-11 12:37:32',1,19,0,39),(23,39,0,'mmmmm\n13232','2020-03-11 12:37:32',1,22,1,39);

/*Table structure for table `dd_image_review_count` */

DROP TABLE IF EXISTS `dd_image_review_count`;

CREATE TABLE `dd_image_review_count` (
  `rid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) NOT NULL COMMENT '用户id',
  `reviewId` int(11) NOT NULL COMMENT '评论id',
  `liketime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '点赞时间',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `dd_image_review_count` */

insert  into `dd_image_review_count`(`rid`,`uid`,`reviewId`,`liketime`) values (9,39,12,'2020-03-10 10:55:39'),(11,39,19,'2020-03-10 11:05:53'),(13,39,11,'2020-03-10 23:55:11'),(15,39,23,'2020-03-11 10:30:51');

/*Table structure for table `dd_img` */

DROP TABLE IF EXISTS `dd_img`;

CREATE TABLE `dd_img` (
  `iid` int(11) NOT NULL AUTO_INCREMENT,
  `aid` int(11) DEFAULT NULL COMMENT '所属专辑',
  `uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `dataurl` varchar(1000) DEFAULT NULL COMMENT '图片资源地址',
  `info` varchar(300) DEFAULT NULL COMMENT '描述',
  `likenum` int(11) DEFAULT NULL COMMENT '点赞数',
  `collectnum` int(11) DEFAULT NULL COMMENT '收藏数',
  `tags` varchar(60) DEFAULT NULL COMMENT '标签栏',
  PRIMARY KEY (`iid`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

/*Data for the table `dd_img` */

insert  into `dd_img`(`iid`,`aid`,`uptime`,`dataurl`,`info`,`likenum`,`collectnum`,`tags`) values (19,26,'2020-03-07 15:19:42','http://img.redyu.run/FohnyyKGtaLgceIt4BtuQFMR3TMr','12312313',0,1,'23123123'),(20,12,'2020-03-09 03:37:01','http://img.redyu.run/FrZ5Ha2o_x3XVJOw8KyQRKckOXUg','加油~',1,1,'漫头'),(21,18,'2020-03-09 04:11:00','http://img.redyu.run/Ftp5kgWMcd7YkFk3rXN7D15-BS2Y','灿烂的微笑',0,0,'灿烂的微笑'),(22,18,'2020-03-09 04:13:39','http://img.redyu.run/FmjItt5aZEbok2W3ommJdL-1otjl,http://img.redyu.run/FlRnMcA8KdgyEplPO4Mjtjg08aq6,http://img.redyu.run/FgXcGFSc56zg3_NaaPsBVcS46rrb,http://img.redyu.run/Fv3c2WRoRIaeQfNWaigPS8eXgLCf,http://img.redyu.run/FszlF-LmtYszqxY-3l5yLvR1kaFG,http://img.redyu.run/FifCbf4ytrBSWMoYmjj3kuRH4vGT,http://img.redyu.run/FnO6t5Nv25OZgA-exFiL9E5pzf7y,http://img.redyu.run/FpAu4tGl4QMDPpjzJnCM8esRZYnA,http://img.redyu.run/FoshNNsPOk5K0Qqs0TgQuXsg8iaU','滴滴滴',1,1,'明星'),(23,12,'2020-03-09 04:16:14','http://img.redyu.run/Flosf6ehTpnrdjbMIEgOymBDM3oQ,http://img.redyu.run/FrvHlSU0e_9_soEuwHoAqY2CjvhA','小樱~',0,0,'小樱,情头,百变小樱魔术卡'),(24,12,'2020-03-09 04:17:34','http://img.redyu.run/FlOskXRjSP1EyRv2u4967KYiId1H,http://img.redyu.run/FggyMQ4SKDkYOVE5AyIayorb1z_y,http://img.redyu.run/Fn2eM1JOZsuaixLkmWp6oKww4NUg,http://img.redyu.run/FtUuH3ATUFhRAe1p7owVfuVNOIev,http://img.redyu.run/FnaOoqRGXeJsjBWpmq7WbP26PX6D,http://img.redyu.run/FhBvCO1SV8jx_6jTO3cwOX1a_2Ku,http://img.redyu.run/Fv--NROSzJ7mxFZzMsUnQstrtkZM,http://img.redyu.run/FshJMTI9EB2bfJgB7WAU378wvFDm,http://img.redyu.run/Fj3LvMGsydn4U6l_o1Uf2YAfxzak','爱恋~',1,0,'情头,套图'),(25,12,'2020-03-09 04:19:11','http://img.redyu.run/FnqgIWWln0VaGXEYrsvEr4whxLcy','漫头~',0,0,'漫头'),(26,12,'2020-03-09 04:25:27','http://img.redyu.run/FkBJeGxTup7yyQUCi6F0e5fFbTpr','金木小天使',0,0,'金木,东京喰种,漫头'),(27,12,'2020-03-09 04:30:18','http://img.redyu.run/Fs4h6e7nkAC1XrqeqF1JzHIfpKdA','漫头',0,0,'漫头,动漫'),(28,12,'2020-03-09 04:30:39','http://img.redyu.run/FhMnnOsUFnx2vF2E5l3m5NkePheV','漫头',0,0,'漫头'),(29,12,'2020-03-09 04:31:51','http://img.redyu.run/FsgaNZyMX-BFnz1X-JzNNzGU4U3a','漫头',0,0,'漫头,动漫'),(30,12,'2020-03-09 04:32:39','http://img.redyu.run/FlIJIKf2h7WXCsMgDm2HWeojzbm9','漫头二花~',0,0,'漫头,二花,即使中二病也要谈恋爱'),(31,12,'2020-03-09 04:36:30','http://img.redyu.run/Fqdmp6UrvFUzP-Uqu9_Fx2bF4Kzm','动漫头像',0,0,'动漫头像'),(32,12,'2020-03-09 04:41:09','http://img.redyu.run/FldWn1kCdSubtGPW94oWCxIa0o3Q','漫头',0,0,'漫头,12313'),(33,12,'2020-03-21 08:02:26','http://img.redyu.run/FtOW7LFdNMeYlAspAcFXarSFg2xW','111',0,0,'qqq');

/*Table structure for table `dd_img_collect` */

DROP TABLE IF EXISTS `dd_img_collect`;

CREATE TABLE `dd_img_collect` (
  `cid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  `img_id` int(11) DEFAULT NULL COMMENT '图片集id',
  `collecttime` timestamp NULL DEFAULT NULL COMMENT '收藏时间',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

/*Data for the table `dd_img_collect` */

insert  into `dd_img_collect`(`cid`,`uid`,`img_id`,`collecttime`) values (39,39,33,'2020-03-10 15:46:36'),(40,39,20,'2020-03-10 23:56:48'),(42,39,22,'2020-03-10 23:58:43');

/*Table structure for table `dd_img_like` */

DROP TABLE IF EXISTS `dd_img_like`;

CREATE TABLE `dd_img_like` (
  `lid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `imgId` int(11) NOT NULL COMMENT '图片id',
  `uid` int(11) NOT NULL COMMENT '用户id',
  `liketime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '点赞时间',
  PRIMARY KEY (`lid`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

/*Data for the table `dd_img_like` */

insert  into `dd_img_like`(`lid`,`imgId`,`uid`,`liketime`) values (20,16,39,'2020-03-08 06:49:41'),(21,17,39,'2020-03-09 01:45:03'),(28,20,39,'2020-03-10 08:48:39'),(29,33,39,'2020-03-10 15:46:37'),(30,24,39,'2020-03-10 23:58:37'),(35,22,39,'2020-03-10 23:58:42');

/*Table structure for table `dd_often_label` */

DROP TABLE IF EXISTS `dd_often_label`;

CREATE TABLE `dd_often_label` (
  `oid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `oname` varchar(30) NOT NULL COMMENT '标签名',
  `appearnum` int(11) DEFAULT NULL COMMENT '出现次数',
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `dd_often_label` */

insert  into `dd_often_label`(`oid`,`oname`,`appearnum`) values (1,'家具',4),(3,'设计',13),(4,'插画',3),(5,'电影',2),(6,'旅行',11),(7,'手工',7),(8,'女装',9),(9,'男装',6),(13,'明星',0),(14,'',0);

/*Table structure for table `dd_searchimg_history` */

DROP TABLE IF EXISTS `dd_searchimg_history`;

CREATE TABLE `dd_searchimg_history` (
  `sid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sname` varchar(50) DEFAULT NULL COMMENT '搜索名称',
  `snum` int(11) DEFAULT NULL COMMENT '搜索次数',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dd_searchimg_history` */

/* Procedure structure for procedure `add_label` */

/*!50003 DROP PROCEDURE IF EXISTS  `add_label` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`skip-grants user`@`skip-grants host` PROCEDURE `add_label`(aname VARCHAR(20))
BEGIN
IF
(
(SELECT COUNT(oid) FROM dd_often_label WHERE oname = aname)>0
)
THEN
UPDATE dd_often_label SET appearnum=appearnum+1 WHERE oname = aname;
ELSE
INSERT INTO dd_often_label VALUES(NULL,aname,0);
END IF;
 
END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
