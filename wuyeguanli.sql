/*
 Navicat Premium Data Transfer

 Source Server         : 47.120.47.223_3306
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : 47.120.47.223:3306
 Source Schema         : wuyeguanli

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 04/01/2024 17:27:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for charge_detail
-- ----------------------------
DROP TABLE IF EXISTS `charge_detail`;
CREATE TABLE `charge_detail`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '收费明细ID',
  `charge_item_id` int NULL DEFAULT NULL COMMENT '收费项目ID',
  `owner_id` int NULL DEFAULT NULL COMMENT '付款人id',
  `pay_money` double NULL DEFAULT NULL COMMENT '应收金额',
  `actual_money` double NULL DEFAULT NULL COMMENT '实收金额',
  `pay_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '缴费时间',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `charge_detail_ibfk_1`(`owner_id` ASC) USING BTREE,
  INDEX `charge_detail_ibfk_2`(`charge_item_id` ASC) USING BTREE,
  CONSTRAINT `charge_detail_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `owner` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `charge_detail_ibfk_2` FOREIGN KEY (`charge_item_id`) REFERENCES `charge_item` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1012 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收费名细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of charge_detail
-- ----------------------------
INSERT INTO `charge_detail` VALUES (8, 3, 537, 783.96, 777, '2022-03-28 09:54:42', '2023-12-18 11:42:21', NULL);
INSERT INTO `charge_detail` VALUES (9, 3, 942, 761.32, 458.25, '2019-03-26 08:49:32', '2023-12-18 11:42:21', NULL);
INSERT INTO `charge_detail` VALUES (10, 3, 572, 280.09, 803.11, '2015-07-20 23:59:59', '2023-12-18 11:42:21', NULL);
INSERT INTO `charge_detail` VALUES (11, 3, 387, 119.04, 880.55, '2012-02-27 07:22:05', '2023-12-18 11:42:21', NULL);
INSERT INTO `charge_detail` VALUES (12, 1, 900, 71600.02, 569.42, '2000-07-18 06:14:04', '2023-12-18 11:42:21', '2024-01-03 16:58:21');
INSERT INTO `charge_detail` VALUES (13, 1, 626, 3500.45, 970.05, '2002-10-17 05:44:53', '2023-12-18 11:42:21', '2023-12-18 14:06:42');
INSERT INTO `charge_detail` VALUES (14, 2, 297, 209.02, 141.29, '2018-11-26 18:28:57', '2023-12-18 11:42:21', NULL);
INSERT INTO `charge_detail` VALUES (15, 2, 933, 944.59, 148.86, '2000-12-27 19:27:09', '2023-12-18 11:42:21', NULL);
INSERT INTO `charge_detail` VALUES (994, 2, 3, 813.82, 537.47, '2008-07-15 17:53:48', '2023-12-18 11:42:21', NULL);
INSERT INTO `charge_detail` VALUES (995, 2, 816, 147.65, 489.93, '2002-01-10 02:08:46', '2023-12-18 11:42:21', NULL);
INSERT INTO `charge_detail` VALUES (996, 1, 475, 392.21, 320.65, '2000-09-09 06:39:22', '2023-12-18 11:42:21', NULL);
INSERT INTO `charge_detail` VALUES (997, 1, 993, 906.61, 263.04, '2002-04-14 16:09:44', '2023-12-18 11:42:21', NULL);
INSERT INTO `charge_detail` VALUES (998, 2, 234, 431.41, 197.08, '2019-08-30 18:34:15', '2023-12-18 11:42:21', NULL);
INSERT INTO `charge_detail` VALUES (999, 3, 374, 63.59, 431.42, '2002-10-10 20:01:19', '2023-12-18 11:42:21', NULL);
INSERT INTO `charge_detail` VALUES (1000, 2, 437, 7.93, 505.53, '2003-06-28 16:14:48', '2023-12-18 11:42:21', NULL);
INSERT INTO `charge_detail` VALUES (1001, 2, 721, 255.37, 260.57, '2019-07-15 15:06:34', '2023-12-18 11:42:21', NULL);
INSERT INTO `charge_detail` VALUES (1002, 3, 230, 431.16, 447.84, '2022-07-29 01:51:33', '2023-12-18 11:42:21', NULL);
INSERT INTO `charge_detail` VALUES (1003, 3, 779, 732.07, 891.93, '2022-02-14 00:38:44', '2023-12-18 11:42:21', NULL);
INSERT INTO `charge_detail` VALUES (1004, 2, 721, 321.73, 851.61, '2022-09-22 11:38:14', '2023-12-18 11:42:21', NULL);
INSERT INTO `charge_detail` VALUES (1005, 2, 830, 219.58, 328.09, '2016-06-03 16:28:38', '2023-12-18 11:42:21', NULL);
INSERT INTO `charge_detail` VALUES (1006, 2, 328, 136.88, 293.45, '2006-12-14 04:42:47', '2023-12-18 11:42:21', NULL);
INSERT INTO `charge_detail` VALUES (1007, 2, 3, 100, 100, '2024-01-10 16:00:00', '2024-01-03 16:58:40', NULL);
INSERT INTO `charge_detail` VALUES (1008, 1, 6, 100, 100, '2024-01-04 08:26:22', '2024-01-04 16:26:24', NULL);
INSERT INTO `charge_detail` VALUES (1009, 7, 6, 100, 100, '2024-01-04 08:26:22', '2024-01-04 16:26:31', NULL);
INSERT INTO `charge_detail` VALUES (1010, 7, 4, 100, 100, '2024-01-04 08:26:22', '2024-01-04 16:26:37', NULL);
INSERT INTO `charge_detail` VALUES (1011, 3, 4, 100, 100, '2024-01-04 08:26:22', '2024-01-04 16:26:41', NULL);

-- ----------------------------
-- Table structure for charge_item
-- ----------------------------
DROP TABLE IF EXISTS `charge_item`;
CREATE TABLE `charge_item`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '收费项目ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `money` double NULL DEFAULT NULL COMMENT '项目收费金额（元）',
  `period` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收费周期',
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收费项目表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of charge_item
-- ----------------------------
INSERT INTO `charge_item` VALUES (1, '物业费', 500, '每年', '/平方米', '2023-12-17 12:07:26', '2023-12-18 14:36:28');
INSERT INTO `charge_item` VALUES (2, '水电费', 600, '每年', NULL, '2023-12-18 11:35:30', '2023-12-18 14:40:01');
INSERT INTO `charge_item` VALUES (3, '车位管理费', 700, '每年', '/车位', '2023-12-18 11:35:42', '2023-12-18 14:36:58');
INSERT INTO `charge_item` VALUES (7, '绿化养护费', 100, '每年', '', '2024-01-03 10:53:09', '2024-01-03 10:53:09');

-- ----------------------------
-- Table structure for complain
-- ----------------------------
DROP TABLE IF EXISTS `complain`;
CREATE TABLE `complain`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '投诉ID',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '投诉具体描述',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `personnel_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `personnel_phone_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态：0-未受理，1-已受理（默认），2-已处理完毕',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '投诉表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of complain
-- ----------------------------
INSERT INTO `complain` VALUES (47, '水表故障没有人维修', '张三', '男', '15267650251', '武致远', '117', '1', '2024-01-04 11:38:43', '2024-01-04 13:04:53');
INSERT INTO `complain` VALUES (48, '电表故障没有人维修', '张三', '男', '15267650521', NULL, NULL, '0', '2024-01-04 11:42:09', '2024-01-04 11:42:09');
INSERT INTO `complain` VALUES (49, '天然气管道故障', '李四', '男', '15266666666', NULL, NULL, '0', '2024-01-04 13:07:08', '2024-01-04 13:07:08');

-- ----------------------------
-- Table structure for complain_personnel_mapping
-- ----------------------------
DROP TABLE IF EXISTS `complain_personnel_mapping`;
CREATE TABLE `complain_personnel_mapping`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `complain_id` int NULL DEFAULT NULL,
  `personnel_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `complain_personnel_mapping_fibk1`(`complain_id` ASC) USING BTREE,
  INDEX `complain_personnel_mapping_fibk2`(`personnel_id` ASC) USING BTREE,
  CONSTRAINT `complain_personnel_mapping_fibk1` FOREIGN KEY (`complain_id`) REFERENCES `complain` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `complain_personnel_mapping_fibk2` FOREIGN KEY (`personnel_id`) REFERENCES `personnel` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of complain_personnel_mapping
-- ----------------------------
INSERT INTO `complain_personnel_mapping` VALUES (3, 47, 4);

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '设备ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备名称',
  `unit_price` double NULL DEFAULT NULL COMMENT '购买单价',
  `num` int NULL DEFAULT NULL COMMENT '购买数量',
  `expected_useful_life` int NULL DEFAULT NULL COMMENT '预计使用年限(年)',
  `purchase_date` timestamp NULL DEFAULT NULL COMMENT '购买日期',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1021 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES (1001, '毛安琪', 235.83, 269, 627, '2010-11-29 21:14:55', '2009-03-02 07:09:10', '2010-03-29 00:08:31');
INSERT INTO `device` VALUES (1002, '龙晓明', 501.79, 419, 836, '2012-05-22 21:07:57', '2006-02-19 14:44:33', '2012-03-22 02:15:44');
INSERT INTO `device` VALUES (1003, '杜子异', 124.54, 67, 347, '2019-12-03 13:45:40', '2009-04-26 04:21:45', '2022-09-14 05:20:03');
INSERT INTO `device` VALUES (1004, '刘詩涵', 378.69, 750, 49, '2005-11-08 22:48:39', '2006-02-26 12:06:25', '2009-06-12 08:41:56');
INSERT INTO `device` VALUES (1005, '石秀英', 1.92, 544, 2, '2014-07-09 01:50:05', '2005-12-17 17:18:39', '2008-11-18 01:28:55');
INSERT INTO `device` VALUES (1006, '侯杰宏', 518.18, 45, 624, '2017-05-28 10:31:53', '2010-06-11 17:21:13', '2015-09-25 13:26:48');
INSERT INTO `device` VALUES (1007, '贾詩涵', 750.64, 502, 521, '2022-09-06 09:34:27', '2020-05-13 05:58:54', '2003-05-05 01:45:57');
INSERT INTO `device` VALUES (1008, '林子异', 808.13, 612, 557, '2013-07-19 14:32:11', '2008-05-17 05:55:46', '2012-05-26 06:13:23');
INSERT INTO `device` VALUES (1009, '夏杰宏', 273.53, 785, 693, '2012-07-09 12:26:02', '2012-07-10 19:14:35', '2004-02-11 12:17:53');
INSERT INTO `device` VALUES (1010, '林璐', 404.71, 767, 784, '2011-03-04 15:40:53', '2016-11-30 01:08:43', '2006-12-21 09:26:43');
INSERT INTO `device` VALUES (1011, '钱子韬', 199.3, 403, 761, '2000-07-10 12:58:40', '2014-07-04 09:23:31', '2016-12-12 12:13:33');
INSERT INTO `device` VALUES (1012, '傅致远', 749.95, 484, 103, '2003-03-01 21:43:09', '2015-07-04 10:36:01', '2020-05-06 15:58:51');
INSERT INTO `device` VALUES (1013, '熊璐', 308.21, 110, 841, '2018-11-22 11:07:16', '2005-10-14 13:09:45', '2010-12-21 02:43:57');
INSERT INTO `device` VALUES (1014, '程宇宁', 269.7, 794, 955, '2008-03-24 18:12:33', '2020-04-13 20:30:02', '2008-07-14 15:47:35');
INSERT INTO `device` VALUES (1015, '龙秀英', 858.85, 595, 785, '2000-07-12 19:34:57', '2020-07-26 04:31:58', '2021-07-19 10:32:35');
INSERT INTO `device` VALUES (1016, '宋子异', 370.5, 927, 131, '2004-04-29 17:10:19', '2018-12-06 04:11:59', '2017-11-27 20:29:36');
INSERT INTO `device` VALUES (1017, '陶秀英', 427.84, 218, 97, '2017-03-19 06:11:31', '2005-11-30 05:39:11', '2021-04-18 18:14:26');
INSERT INTO `device` VALUES (1018, '丁秀英', 576.85, 558, 740, '2018-05-28 21:35:48', '2007-07-08 07:42:41', '2020-09-23 01:14:59');
INSERT INTO `device` VALUES (1019, '苏杰宏', 258.36, 357, 216, '2017-09-16 22:49:45', '2015-01-25 11:29:43', '2000-10-26 13:09:27');
INSERT INTO `device` VALUES (1020, '余秀英', 110.33, 453, 624, '2012-06-20 07:16:23', '2018-12-16 14:29:17', '2020-11-15 06:47:01');

-- ----------------------------
-- Table structure for device_repair_personnel_mapping
-- ----------------------------
DROP TABLE IF EXISTS `device_repair_personnel_mapping`;
CREATE TABLE `device_repair_personnel_mapping`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `device_id` int NULL DEFAULT NULL,
  `repair_id` int NULL DEFAULT NULL,
  `personnel_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parking_owner_mapping_fibk1`(`device_id` ASC) USING BTREE,
  INDEX `parking_owner_mapping_fibk3`(`personnel_id` ASC) USING BTREE,
  INDEX `parking_owner_mapping_fibk2`(`repair_id` ASC) USING BTREE,
  CONSTRAINT `parking_owner_mapping_fibk1` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `parking_owner_mapping_fibk2` FOREIGN KEY (`repair_id`) REFERENCES `repair` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `parking_owner_mapping_fibk3` FOREIGN KEY (`personnel_id`) REFERENCES `personnel` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device_repair_personnel_mapping
-- ----------------------------

-- ----------------------------
-- Table structure for house
-- ----------------------------
DROP TABLE IF EXISTS `house`;
CREATE TABLE `house`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '房屋ID',
  `building_id` int NULL DEFAULT NULL COMMENT '所属栋数ID',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房产名称',
  `owner_id` int NULL DEFAULT NULL COMMENT '业主ID',
  `room_num` int NULL DEFAULT NULL COMMENT '房间数',
  `unit` int NULL DEFAULT NULL COMMENT '单元',
  `floor` int NULL DEFAULT NULL COMMENT '楼层',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `live_time` timestamp NULL DEFAULT NULL COMMENT '入住时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `house_ibfk_1`(`owner_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '房屋表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of house
-- ----------------------------
INSERT INTO `house` VALUES (1, 1, '622', 1, 5, 2, 6, NULL, NULL);
INSERT INTO `house` VALUES (2, 2, '522', 2, 4, 2, 5, NULL, NULL);
INSERT INTO `house` VALUES (3, 424, 'Meng On Kay', 445, 401, 410, 267, 'mcG1uL6vPh', '2018-10-20 21:45:50');
INSERT INTO `house` VALUES (4, 661, 'Shing Ka Ming', 241, 116, 340, 68, 's5tio0AhBN', '2008-01-14 14:54:12');
INSERT INTO `house` VALUES (5, 95, 'Arthur Castro', 866, 560, 186, 585, 'DwAQtDYZS7', '2009-07-09 06:55:30');
INSERT INTO `house` VALUES (6, 872, 'Yamashita Nanami', 121, 227, 842, 860, 'uf9BKoHwg8', '2011-08-24 12:12:10');
INSERT INTO `house` VALUES (7, 281, 'Joanne Ross', 455, 866, 955, 671, '4yLZXln5iM', '2001-08-17 14:28:55');
INSERT INTO `house` VALUES (8, 417, 'Miyamoto Rin', 18, 195, 930, 333, 'HeOaJnUm6b', '2002-10-28 00:14:59');
INSERT INTO `house` VALUES (9, 976, 'Amber Cole', 420, 247, 184, 324, 'vlWUXpHe69', '2011-05-17 10:29:08');
INSERT INTO `house` VALUES (10, 144, 'Jeffrey Lee', 570, 364, 612, 667, 'ihUVRMQOYs', '2015-08-23 08:33:20');
INSERT INTO `house` VALUES (11, 234, '李四', 6, 432, 3, 2, '41', '2024-01-04 13:03:11');

-- ----------------------------
-- Table structure for house_device_mapping
-- ----------------------------
DROP TABLE IF EXISTS `house_device_mapping`;
CREATE TABLE `house_device_mapping`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `house_id` int NULL DEFAULT NULL,
  `device_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `house_device_mapping_fibk1`(`house_id` ASC) USING BTREE,
  INDEX `house_device_mapping_fibk2`(`device_id` ASC) USING BTREE,
  CONSTRAINT `house_device_mapping_fibk1` FOREIGN KEY (`house_id`) REFERENCES `house` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `house_device_mapping_fibk2` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of house_device_mapping
-- ----------------------------
INSERT INTO `house_device_mapping` VALUES (1, 1, 1011);
INSERT INTO `house_device_mapping` VALUES (2, 8, 1007);
INSERT INTO `house_device_mapping` VALUES (3, 10, 1011);
INSERT INTO `house_device_mapping` VALUES (4, 9, 1010);
INSERT INTO `house_device_mapping` VALUES (5, 6, 1002);
INSERT INTO `house_device_mapping` VALUES (6, 5, 1011);
INSERT INTO `house_device_mapping` VALUES (7, 7, 1015);
INSERT INTO `house_device_mapping` VALUES (8, 7, 1015);
INSERT INTO `house_device_mapping` VALUES (9, 2, 1007);
INSERT INTO `house_device_mapping` VALUES (10, 2, 1006);
INSERT INTO `house_device_mapping` VALUES (11, 5, 1008);
INSERT INTO `house_device_mapping` VALUES (12, 4, 1003);
INSERT INTO `house_device_mapping` VALUES (13, 9, 1018);
INSERT INTO `house_device_mapping` VALUES (14, 5, 1003);
INSERT INTO `house_device_mapping` VALUES (15, 8, 1020);
INSERT INTO `house_device_mapping` VALUES (16, 5, 1005);
INSERT INTO `house_device_mapping` VALUES (17, 1, 1015);
INSERT INTO `house_device_mapping` VALUES (18, 9, 1007);
INSERT INTO `house_device_mapping` VALUES (19, 2, 1019);
INSERT INTO `house_device_mapping` VALUES (20, 9, 1003);

-- ----------------------------
-- Table structure for info
-- ----------------------------
DROP TABLE IF EXISTS `info`;
CREATE TABLE `info`  (
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '小区名称',
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小区地址',
  `scale` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小区规模',
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物业资料表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of info
-- ----------------------------
INSERT INTO `info` VALUES ('浙理小区', '浙江省杭州市钱塘新区学林街110号', '600亩');

-- ----------------------------
-- Table structure for meter
-- ----------------------------
DROP TABLE IF EXISTS `meter`;
CREATE TABLE `meter`  (
  `meter_id` int NOT NULL AUTO_INCREMENT COMMENT '仪表ID',
  `meter_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '仪表类型',
  `meter_value` double NULL DEFAULT NULL COMMENT '仪表示数',
  `personnel` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '抄表物业人员',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`meter_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '计量仪表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of meter
-- ----------------------------
INSERT INTO `meter` VALUES (4, '公共电表', 2000, '张一', '2024-01-02 22:16:05', '2024-01-02 23:47:35');
INSERT INTO `meter` VALUES (6, '公共水表', 20000, NULL, '2024-01-02 22:17:31', '2024-01-03 09:30:49');
INSERT INTO `meter` VALUES (7, '阿斯顿', 100, NULL, '2024-01-02 23:58:37', '2024-01-02 23:58:37');

-- ----------------------------
-- Table structure for owner
-- ----------------------------
DROP TABLE IF EXISTS `owner`;
CREATE TABLE `owner`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '业主ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `id_card` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `profession` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职业',
  `sex` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '性别:0-男，1-女',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '类型:0-房主，1-租客',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '无' COMMENT '备注',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '业主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of owner
-- ----------------------------
INSERT INTO `owner` VALUES (3, 'Miguel Jordan', 'YVnNfxGf8N', '914', 'z2fflkB1k8', '男', '0', 'qRtu8WK2hY', '2006-05-26', '2014-05-10 19:50:35', '2024-01-04 16:29:00');
INSERT INTO `owner` VALUES (4, '张三', '331081200212122010', '666', 'dHV8UCVOO2', '男', '0', '无', '2024-01-17', '2024-01-03 20:15:45', '2024-01-03 20:15:55');
INSERT INTO `owner` VALUES (6, '李四', 'a1gkGYEAqf', '414', 'NTtMOwdENi', '男', '0', 'SHbjhCmwvY', '2006-01-14', '2002-05-20 14:40:29', '2024-01-04 16:29:01');
INSERT INTO `owner` VALUES (7, 'Inoue Kasumi', '36425YTe32', '130', 'FAqOTWTIjy', '男', '0', '7BXxMXu4D7', '2011-09-15', '2013-04-30 03:20:31', '2024-01-04 16:29:02');
INSERT INTO `owner` VALUES (9, 'Yamamoto Akina', 'OTqqC1uZOf', '926', 'kS0HsLPG2e', '女', '0', 'iudkuQcKtp', '2019-09-03', '2006-04-04 03:02:32', '2024-01-04 16:29:03');
INSERT INTO `owner` VALUES (10, 'Tian Shihan', 'UbGopt5h98', '897', 'dHV8UCVOO2', '女', '0', 'XolzEafZr1', '2012-09-01', '2008-03-26 21:18:08', '2024-01-04 16:29:05');
INSERT INTO `owner` VALUES (11, '王五', '331081200212132010', '15267650251', NULL, '男', '0', '无', '2024-01-04', '2024-01-04 16:28:57', '2024-01-04 16:29:15');

-- ----------------------------
-- Table structure for owner_complain_mapping
-- ----------------------------
DROP TABLE IF EXISTS `owner_complain_mapping`;
CREATE TABLE `owner_complain_mapping`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `owner_id` int NULL DEFAULT NULL,
  `complaint_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `owner_complain_mapping_fibk1`(`owner_id` ASC) USING BTREE,
  INDEX `owner_complain_mapping_fibk2`(`complaint_id` ASC) USING BTREE,
  CONSTRAINT `owner_complain_mapping_fibk1` FOREIGN KEY (`owner_id`) REFERENCES `owner` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `owner_complain_mapping_fibk2` FOREIGN KEY (`complaint_id`) REFERENCES `complain` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of owner_complain_mapping
-- ----------------------------

-- ----------------------------
-- Table structure for owner_house_mapping
-- ----------------------------
DROP TABLE IF EXISTS `owner_house_mapping`;
CREATE TABLE `owner_house_mapping`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `owner_id` int NULL DEFAULT NULL,
  `house_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `owner_house_mapping_fibk1`(`owner_id` ASC) USING BTREE,
  INDEX `owner_house_mapping_fibk2`(`house_id` ASC) USING BTREE,
  CONSTRAINT `owner_house_mapping_fibk1` FOREIGN KEY (`owner_id`) REFERENCES `owner` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `owner_house_mapping_fibk2` FOREIGN KEY (`house_id`) REFERENCES `house` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of owner_house_mapping
-- ----------------------------
INSERT INTO `owner_house_mapping` VALUES (2, 2, 1);
INSERT INTO `owner_house_mapping` VALUES (3, 4, 2);
INSERT INTO `owner_house_mapping` VALUES (7, 6, 1);
INSERT INTO `owner_house_mapping` VALUES (8, 1008, NULL);
INSERT INTO `owner_house_mapping` VALUES (9, 6, 11);

-- ----------------------------
-- Table structure for parking
-- ----------------------------
DROP TABLE IF EXISTS `parking`;
CREATE TABLE `parking`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '车位ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 132 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '车位表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of parking
-- ----------------------------
INSERT INTO `parking` VALUES (110, '刘詩涵', '0', '741852963', '2021-09-25 11:35:16', '2024-01-03 02:42:03');
INSERT INTO `parking` VALUES (111, 'Cho Sai Wing', 'M', '414', '2004-01-24 02:48:44', '2023-12-30 23:05:55');
INSERT INTO `parking` VALUES (112, 'Tian Shihan', 'F', '897', '2010-05-26 16:07:54', '2023-12-25 13:31:24');
INSERT INTO `parking` VALUES (113, 'Inoue Kasumi', 'M', '130', '2007-02-21 03:16:00', '2023-12-30 23:06:18');
INSERT INTO `parking` VALUES (114, 'Cho Sai Wing', 'M', '414', '2012-03-24 05:15:20', '2023-12-30 23:06:28');
INSERT INTO `parking` VALUES (115, 'Chin Wing Fat', 'M', '164', '2020-04-03 09:33:20', '2002-06-07 17:04:08');
INSERT INTO `parking` VALUES (116, 'Yeung Wing Suen', 'F', '139', '2011-08-31 21:56:41', '2018-07-22 12:21:34');
INSERT INTO `parking` VALUES (117, 'Kwok Lik Sun', 'M', '685', '2011-10-10 01:31:26', '2004-12-25 04:50:51');
INSERT INTO `parking` VALUES (118, 'Miyamoto Mai', 'F', '620', '2019-02-07 14:28:31', '2019-07-10 08:52:52');
INSERT INTO `parking` VALUES (120, 'Yamamoto Akina', 'F', '926', '2023-12-25 10:33:14', '2023-12-25 10:33:21');
INSERT INTO `parking` VALUES (121, 'Wu Zitao', 'M', '14', '2019-12-01 05:51:01', '2001-03-20 06:08:46');
INSERT INTO `parking` VALUES (122, 'Meng Wing Fat', 'M', '69', '2010-06-07 09:08:01', '2018-07-08 02:34:29');
INSERT INTO `parking` VALUES (123, 'Lu Lu', 'F', '744', '2004-12-16 12:27:47', '2004-05-22 06:10:11');
INSERT INTO `parking` VALUES (124, 'Beverly Adams', 'F', '312', '2012-09-07 04:45:41', '2000-08-05 11:28:39');
INSERT INTO `parking` VALUES (125, 'Duan Shihan', 'F', '485', '2014-10-10 18:31:53', '2010-05-08 23:25:16');
INSERT INTO `parking` VALUES (126, 'Lok Lik Sun', 'M', '164', '2008-12-27 22:32:54', '2015-04-13 21:42:01');
INSERT INTO `parking` VALUES (127, 'Betty Holmes', 'F', '40', '2023-08-12 06:15:12', '2023-07-07 10:48:22');
INSERT INTO `parking` VALUES (129, 'Siu Tsz Ching', 'F', '129', '2002-01-02 15:21:39', '2010-06-08 19:14:37');
INSERT INTO `parking` VALUES (130, 'Nakamori Hazuki', 'F', '542', '2012-02-28 09:40:44', '2001-04-07 05:10:57');
INSERT INTO `parking` VALUES (131, 'Tian Zhennan', 'M', '778', '2023-09-06 12:50:11', '2021-02-17 17:08:30');

-- ----------------------------
-- Table structure for parking_owner_mapping
-- ----------------------------
DROP TABLE IF EXISTS `parking_owner_mapping`;
CREATE TABLE `parking_owner_mapping`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `parking_id` int NULL DEFAULT NULL,
  `owner_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parking_owner_id_fibk1`(`parking_id` ASC) USING BTREE,
  INDEX `parking_owner_id_fibk2`(`owner_id` ASC) USING BTREE,
  CONSTRAINT `parking_owner_id_fibk1` FOREIGN KEY (`parking_id`) REFERENCES `parking` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `parking_owner_id_fibk2` FOREIGN KEY (`owner_id`) REFERENCES `owner` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of parking_owner_mapping
-- ----------------------------
INSERT INTO `parking_owner_mapping` VALUES (3, 120, 9);
INSERT INTO `parking_owner_mapping` VALUES (4, 112, 10);
INSERT INTO `parking_owner_mapping` VALUES (6, 110, 2);
INSERT INTO `parking_owner_mapping` VALUES (7, 111, 6);
INSERT INTO `parking_owner_mapping` VALUES (8, 113, 7);
INSERT INTO `parking_owner_mapping` VALUES (9, 114, 6);

-- ----------------------------
-- Table structure for pay
-- ----------------------------
DROP TABLE IF EXISTS `pay`;
CREATE TABLE `pay`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `money` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pay
-- ----------------------------
INSERT INTO `pay` VALUES ('1704216833820', 100);
INSERT INTO `pay` VALUES ('1704217256326', 100);
INSERT INTO `pay` VALUES ('1704219633334', 100);
INSERT INTO `pay` VALUES ('1704248483569', 100);
INSERT INTO `pay` VALUES ('1704274362603', 200);
INSERT INTO `pay` VALUES ('1704274622985', 100);
INSERT INTO `pay` VALUES ('1704275842764', 100);
INSERT INTO `pay` VALUES ('1704339935926', 100);

-- ----------------------------
-- Table structure for personnel
-- ----------------------------
DROP TABLE IF EXISTS `personnel`;
CREATE TABLE `personnel`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '物业人员ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物业人员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of personnel
-- ----------------------------
INSERT INTO `personnel` VALUES (4, '武致远', '117', 'wz212@gmail.com', '2011-01-15 13:07:30', '2001-02-10 08:16:39');
INSERT INTO `personnel` VALUES (5, '周子韬', '996', 'zzhou@gmail.com', '2016-06-08 14:32:00', '2017-04-02 19:38:18');
INSERT INTO `personnel` VALUES (6, '宋子韬', '764', 'sonzi6@mail.com', '2014-07-25 11:13:24', '2023-01-13 12:33:16');
INSERT INTO `personnel` VALUES (7, '李云熙', '225', 'lyunxi@gmail.com', '2014-08-01 02:17:53', '2021-07-27 01:59:10');
INSERT INTO `personnel` VALUES (8, '韦秀英', '432', 'xiuyingwe@outlook.com', '2000-07-03 23:35:12', '2012-05-21 10:21:55');
INSERT INTO `personnel` VALUES (9, '杜宇宁', '322', 'yuningdu3@outlook.com', '2010-02-14 20:45:01', '2014-11-17 17:24:38');
INSERT INTO `personnel` VALUES (10, '顾震南', '306', 'zheg721@icloud.com', '2016-08-01 01:19:01', '2011-06-18 18:33:01');
INSERT INTO `personnel` VALUES (11, '陈岚', '417', 'lan3@icloud.com', '2015-11-10 13:23:31', '2016-03-31 11:50:19');
INSERT INTO `personnel` VALUES (12, '叶震南', '285', 'zhennany8@gmail.com', '2016-10-17 03:52:54', '2019-10-07 11:57:10');
INSERT INTO `personnel` VALUES (13, '沈子韬', '930', 'shenzitao@outlook.com', '2008-06-13 14:59:20', '2021-02-26 23:41:22');

-- ----------------------------
-- Table structure for pre_payment
-- ----------------------------
DROP TABLE IF EXISTS `pre_payment`;
CREATE TABLE `pre_payment`  (
  `owner_id` int NOT NULL COMMENT '业主ID',
  `balance` double NULL DEFAULT NULL COMMENT '余额',
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间\r\n',
  PRIMARY KEY (`owner_id`) USING BTREE,
  CONSTRAINT `pre_payment_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `owner` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '预付款表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pre_payment
-- ----------------------------
INSERT INTO `pre_payment` VALUES (3, 600, '123', '2024-01-03 18:57:54');
INSERT INTO `pre_payment` VALUES (4, 500, '167-6186-4164', '2024-01-04 11:46:36');
INSERT INTO `pre_payment` VALUES (6, 0, '414', NULL);
INSERT INTO `pre_payment` VALUES (7, 200, '755-9170-2113', '2024-01-03 18:56:54');

-- ----------------------------
-- Table structure for repair
-- ----------------------------
DROP TABLE IF EXISTS `repair`;
CREATE TABLE `repair`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '维修ID',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报修描述',
  `house_id` int NULL DEFAULT NULL,
  `device_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `personnel_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `personnel_phone_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` char(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态：0-待受理，1-已受理，2-修理完毕',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `repair_fibk1`(`house_id` ASC) USING BTREE,
  CONSTRAINT `repair_fibk1` FOREIGN KEY (`house_id`) REFERENCES `house` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1049 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '维修表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repair
-- ----------------------------
INSERT INTO `repair` VALUES (1030, 'WjkXywrHxD', 2, 'Zhu Lan', 'Zhu Lan', '7875 391563', '陈岚', '417', 'tRfDt', '2012-01-11 15:39:15', '2024-01-03 12:46:40');
INSERT INTO `repair` VALUES (1034, '622电表故障', 1, '电表', '任嘉伦', '138-6666-6666', '周子韬', '165-7679-0062', '1', '2024-01-03 13:00:37', '2024-01-03 15:04:22');
INSERT INTO `repair` VALUES (1044, '123', 1, '123', 'null', NULL, NULL, NULL, '0', '2024-01-03 20:20:21', '2024-01-03 20:20:21');
INSERT INTO `repair` VALUES (1045, '水表故障', 2, '水表', '张三', NULL, '武致远', '117', '0', '2024-01-04 11:38:00', '2024-01-04 11:39:08');
INSERT INTO `repair` VALUES (1046, '电表故障', 2, '电表', '张三', NULL, '武致远', '117', '0', '2024-01-04 11:38:16', '2024-01-04 11:39:11');
INSERT INTO `repair` VALUES (1047, '水表故障', 11, '水表', '李四', NULL, NULL, NULL, '0', '2024-01-04 13:03:44', '2024-01-04 13:03:44');
INSERT INTO `repair` VALUES (1048, '天然气管道故障', 11, '天然气管道', '李四', NULL, NULL, NULL, '0', '2024-01-04 13:04:28', '2024-01-04 13:04:28');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户类型，0-管理员，1-住户，2-物业人员',
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', '0');
INSERT INTO `user` VALUES ('123', '123', '1');
INSERT INTO `user` VALUES ('2', '2', '1');
INSERT INTO `user` VALUES ('6', '6', '1');
INSERT INTO `user` VALUES ('7', '7', '1');
INSERT INTO `user` VALUES ('wuzhiyuan', 'wuzhiyuan', '2');

-- ----------------------------
-- Table structure for user_owner_mapping
-- ----------------------------
DROP TABLE IF EXISTS `user_owner_mapping`;
CREATE TABLE `user_owner_mapping`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `owner_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_owner_mapping_fibk1`(`username` ASC) USING BTREE,
  INDEX `user_owner_mapping_fibk2`(`owner_id` ASC) USING BTREE,
  CONSTRAINT `user_owner_mapping_fibk1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_owner_mapping_fibk2` FOREIGN KEY (`owner_id`) REFERENCES `owner` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_owner_mapping
-- ----------------------------
INSERT INTO `user_owner_mapping` VALUES (12, '123', 4);
INSERT INTO `user_owner_mapping` VALUES (17, '6', 6);

-- ----------------------------
-- Table structure for user_personnel_mapping
-- ----------------------------
DROP TABLE IF EXISTS `user_personnel_mapping`;
CREATE TABLE `user_personnel_mapping`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `personnel_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_personnel_mapping
-- ----------------------------
INSERT INTO `user_personnel_mapping` VALUES (1, 'wuzhiyuan', 4);

SET FOREIGN_KEY_CHECKS = 1;
