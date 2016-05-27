/*
Navicat MySQL Data Transfer

Source Server         : 139.219.12.110 公测
Source Server Version : 50616
Source Host           : 139.219.12.110 :3309
Source Database       : linan

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2016-05-06 16:29:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `comm_province`
-- ----------------------------
DROP TABLE IF EXISTS `comm_province`;
CREATE TABLE `comm_province` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `province_name` varchar(50) NOT NULL COMMENT '省份名称',
  `province_code` varchar(20) DEFAULT NULL COMMENT '省份编码',
  PRIMARY KEY (`id`),
  KEY `province_code_index` (`province_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=260 DEFAULT CHARSET=utf8 COMMENT='省份';

-- ----------------------------
-- Records of comm_province
-- ----------------------------
INSERT INTO `comm_province` VALUES ('229', '广东省', '440000');
INSERT INTO `comm_province` VALUES ('230', '北京市', '110000');
INSERT INTO `comm_province` VALUES ('231', '天津市', '120000');
INSERT INTO `comm_province` VALUES ('232', '河北省', '130000');
INSERT INTO `comm_province` VALUES ('233', '山西省', '140000');
INSERT INTO `comm_province` VALUES ('234', '内蒙古省', '150000');
INSERT INTO `comm_province` VALUES ('235', '辽宁省', '210000');
INSERT INTO `comm_province` VALUES ('236', '吉林省', '220000');
INSERT INTO `comm_province` VALUES ('237', '黑龙江省', '230000');
INSERT INTO `comm_province` VALUES ('238', '上海市', '310000');
INSERT INTO `comm_province` VALUES ('239', '江苏省', '320000');
INSERT INTO `comm_province` VALUES ('240', '浙江省', '330000');
INSERT INTO `comm_province` VALUES ('241', '安徽省', '340000');
INSERT INTO `comm_province` VALUES ('242', '福建省', '350000');
INSERT INTO `comm_province` VALUES ('243', '江西省', '360000');
INSERT INTO `comm_province` VALUES ('244', '山东省', '370000');
INSERT INTO `comm_province` VALUES ('245', '河南省', '410000');
INSERT INTO `comm_province` VALUES ('246', '湖北省', '420000');
INSERT INTO `comm_province` VALUES ('247', '湖南省', '430000');
INSERT INTO `comm_province` VALUES ('248', '广西省', '450000');
INSERT INTO `comm_province` VALUES ('249', '海南省', '460000');
INSERT INTO `comm_province` VALUES ('250', '重庆市', '500000');
INSERT INTO `comm_province` VALUES ('251', '四川省', '510000');
INSERT INTO `comm_province` VALUES ('252', '贵州省', '520000');
INSERT INTO `comm_province` VALUES ('253', '云南省', '530000');
INSERT INTO `comm_province` VALUES ('254', '西藏省', '540000');
INSERT INTO `comm_province` VALUES ('255', '陕西省', '610000');
INSERT INTO `comm_province` VALUES ('256', '甘肃省', '620000');
INSERT INTO `comm_province` VALUES ('257', '青海省', '630000');
INSERT INTO `comm_province` VALUES ('258', '宁夏省', '640000');
INSERT INTO `comm_province` VALUES ('259', '新疆省', '650000');
