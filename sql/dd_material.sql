/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.24 : Database - dd_material
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dd_material` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `dd_material`;

/*Table structure for table `dd_category` */

DROP TABLE IF EXISTS `dd_category`;

CREATE TABLE `dd_category` (
  `cid` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `cname` varchar(50) DEFAULT NULL COMMENT '分类名',
  `isparent` int(11) DEFAULT NULL COMMENT '是否是父亲',
  `parent_id` int(11) DEFAULT NULL COMMENT '父亲Id',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

/*Data for the table `dd_category` */

insert  into `dd_category`(`cid`,`cname`,`isparent`,`parent_id`) values (1,'家具生活',1,0),(2,'美食菜谱',1,0),(3,'手工DIY',1,0),(4,'时尚搭配',1,0),(5,'美妆造型',1,0),(6,'婚纱婚礼',1,0),(7,'文字句子',1,0),(8,'插画绘画',1,0),(9,'影音书籍',1,0),(10,'人物明星',1,0),(11,'1植物多肉',1,0),(12,'生活百科',1,0),(13,'搞笑萌宠',1,0),(14,'人文艺术',1,0),(15,'设计',1,0),(16,'古风',1,0),(17,'壁纸',1,0),(18,'旅行',1,0),(19,'头像',1,0),(20,'摄影',1,0),(21,'空间',1,1),(22,'家具',1,1),(23,'风格',1,1),(24,'客厅',0,21),(25,'卧室',0,21),(26,'楼梯',0,21),(27,'阁楼',0,21),(28,'儿童房',0,21),(29,'厨房',0,21),(30,'浴室',0,21),(31,'沙发',0,22),(32,'椅子',0,22),(33,'桌子',0,22),(34,'柜子',0,22),(35,'北欧',0,23),(36,'料理',1,2),(37,'甜点/饮品',1,2),(38,'菜谱',0,36),(39,'家常菜',0,36),(40,'主食',0,36),(41,'糖粥羹',0,36),(42,'西餐',0,36),(43,'日料',0,36),(44,'早餐',0,36),(45,'便当',0,36),(46,'甜点',0,37),(47,'糖果',0,37),(48,'饼干',0,37),(49,'蛋糕',0,37),(50,'马卡龙',0,37),(51,'冰淇淋',0,37),(52,'水果',0,37),(53,'冰品',0,37),(54,'咖啡',0,37),(55,'调酒',0,37);

/*Table structure for table `dd_category_user` */

DROP TABLE IF EXISTS `dd_category_user`;

CREATE TABLE `dd_category_user` (
  `sid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) DEFAULT NULL COMMENT '用户ID',
  `cid` int(11) DEFAULT NULL COMMENT '分类ID',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `dd_category_user` */

insert  into `dd_category_user`(`sid`,`uid`,`cid`) values (5,39,9),(6,39,10),(7,39,14),(8,39,18),(9,39,11),(10,39,15),(11,39,19),(12,40,5),(13,40,1),(14,40,9);

/*Table structure for table `dd_list` */

DROP TABLE IF EXISTS `dd_list`;

CREATE TABLE `dd_list` (
  `lid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `path` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`lid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `dd_list` */

insert  into `dd_list`(`lid`,`name`,`path`) values (1,'图片','/jx_image'),(3,'文章','/jx_article');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
