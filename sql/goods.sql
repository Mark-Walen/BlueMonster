/*
MySQL Workbench
MySQL - 8.0.19 : Database - green_orange_goods
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`blue_monster_goods` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `green_orange_goods`;

/*Table structure for table `tb_album` */

DROP TABLE IF EXISTS `tb_album`;

CREATE TABLE `tb_album` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `title` varchar(100) DEFAULT NULL COMMENT '相册名称',
  `image` varchar(100) DEFAULT NULL COMMENT '相册封面',
  `desc` varchar(100) DEFAULT NULL COMMENT '相册描述',
  `image_items` text COMMENT '图片列表',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*Data for the table `tb_album` */

/*Table structure for table `tb_brand` */

DROP TABLE IF EXISTS `tb_brand`;

CREATE TABLE `tb_brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '品牌id',
  `name` varchar(100) NOT NULL COMMENT '品牌名称',
  `image` varchar(1000) DEFAULT '' COMMENT '品牌图片地址',
  `letter` char(1) DEFAULT '' COMMENT '品牌的首字母',
  `seq` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=325414 DEFAULT CHARSET=utf8 COMMENT='品牌表';

/*Data for the table `tb_brand` */

/*Table structure for table `tb_category` */

DROP TABLE IF EXISTS `tb_category`;

CREATE TABLE `tb_category` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `name` varchar(50) DEFAULT NULL COMMENT '分类名称',
  `goods_num` int(11) DEFAULT '0' COMMENT '商品数量',
  `is_show` char(1) DEFAULT NULL COMMENT '是否显示',
  `is_menu` char(1) DEFAULT NULL COMMENT '是否导航',
  `seq` int(11) DEFAULT NULL COMMENT '排序',
  `parent_id` int(20) DEFAULT NULL COMMENT '上级ID',
  `template_id` int(11) DEFAULT NULL COMMENT '模板ID',
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1217 DEFAULT CHARSET=utf8 COMMENT='商品类目';

/*Table structure for table `tb_category_brand` */
DROP TABLE IF EXISTS `tb_category_brand`;

CREATE TABLE `tb_category_brand` (
  `category_id` int(11) NOT NULL COMMENT '分类ID',
  `brand_id` int(11) NOT NULL COMMENT '品牌ID',
  PRIMARY KEY (`category_id`,`brand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*Data for the table `tb_category_brand` */

/*Table structure for table `tb_para` */
DROP TABLE IF EXISTS `tb_para`;

CREATE TABLE `tb_para` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `options` varchar(2000) DEFAULT NULL COMMENT '选项',
  `seq` int(11) DEFAULT NULL COMMENT '排序',
  `template_id` int(11) DEFAULT NULL COMMENT '模板ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tb_para` */

/*Table structure for table `tb_pref` */

DROP TABLE IF EXISTS `tb_pref`;

CREATE TABLE `tb_pref` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `cate_id` int(11) DEFAULT NULL COMMENT '分类ID',
  `buy_money` int(11) DEFAULT NULL COMMENT '消费金额',
  `pre_money` int(11) DEFAULT NULL COMMENT '优惠金额',
  `start_time` date DEFAULT NULL COMMENT '活动开始日期',
  `end_time` date DEFAULT NULL COMMENT '活动截至日期',
  `type` char(1) DEFAULT NULL COMMENT '类型',
  `state` char(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `tb_pref` */