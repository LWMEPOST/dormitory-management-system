/*
Navicat MySQL Data Transfer

Source Server         : 13
Source Server Version : 80044
Source Host           : localhost:3306
Source Database       : dormitory

Target Server Type    : MYSQL
Target Server Version : 80044
File Encoding         : 65001

Date: 2026-01-07 18:39:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for check_in
-- ----------------------------
DROP TABLE IF EXISTS `check_in`;
CREATE TABLE `check_in` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `dormitory_id` int NOT NULL,
  `check_in_date` date NOT NULL,
  `note` text COLLATE utf8mb4_unicode_ci,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`),
  KEY `dormitory_id` (`dormitory_id`),
  CONSTRAINT `check_in_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE,
  CONSTRAINT `check_in_ibfk_2` FOREIGN KEY (`dormitory_id`) REFERENCES `dormitory` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of check_in
-- ----------------------------
INSERT INTO `check_in` VALUES ('1', '1', '1', '2023-09-01', '新生入住', '2026-01-06 00:58:20');
INSERT INTO `check_in` VALUES ('2', '2', '1', '2023-09-01', '新生入住', '2026-01-06 00:58:20');
INSERT INTO `check_in` VALUES ('3', '3', '2', '2023-09-01', '新生入住', '2026-01-06 00:58:20');
INSERT INTO `check_in` VALUES ('4', '4', '2', '2023-09-01', '新生入住', '2026-01-06 00:58:20');
INSERT INTO `check_in` VALUES ('5', '5', '3', '2023-09-01', '新生入住', '2026-01-06 00:58:20');

-- ----------------------------
-- Table structure for check_out
-- ----------------------------
DROP TABLE IF EXISTS `check_out`;
CREATE TABLE `check_out` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `dormitory_id` int NOT NULL,
  `check_out_date` date NOT NULL,
  `reason` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `note` text COLLATE utf8mb4_unicode_ci,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`),
  KEY `dormitory_id` (`dormitory_id`),
  CONSTRAINT `check_out_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE,
  CONSTRAINT `check_out_ibfk_2` FOREIGN KEY (`dormitory_id`) REFERENCES `dormitory` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of check_out
-- ----------------------------

-- ----------------------------
-- Table structure for dormitory
-- ----------------------------
DROP TABLE IF EXISTS `dormitory`;
CREATE TABLE `dormitory` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dormitory_number` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `building` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `floor` int NOT NULL,
  `capacity` int NOT NULL,
  `current_occupancy` int NOT NULL DEFAULT '0',
  `type` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '男生/女生',
  `status` int NOT NULL DEFAULT '0' COMMENT '0-空闲，1-已入住，2-维修中',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dormitory_number` (`dormitory_number`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of dormitory
-- ----------------------------
INSERT INTO `dormitory` VALUES ('1', '101', 'A栋', '1', '4', '0', '男生', '1', '2026-01-06 00:57:28');
INSERT INTO `dormitory` VALUES ('2', '102', 'A栋', '1', '4', '0', '四人间', '0', '2026-01-06 00:57:28');
INSERT INTO `dormitory` VALUES ('3', '103', 'A栋', '1', '4', '0', '四人间', '0', '2026-01-06 00:57:28');
INSERT INTO `dormitory` VALUES ('4', '201', 'A栋', '2', '4', '0', '四人间', '0', '2026-01-06 00:57:28');
INSERT INTO `dormitory` VALUES ('5', '202', 'A栋', '2', '4', '0', '四人间', '0', '2026-01-06 00:57:28');
INSERT INTO `dormitory` VALUES ('6', '203', 'A栋', '2', '4', '0', '四人间', '0', '2026-01-06 00:57:28');
INSERT INTO `dormitory` VALUES ('7', '301', 'B栋', '3', '6', '0', '六人间', '0', '2026-01-06 00:57:28');
INSERT INTO `dormitory` VALUES ('8', '302', 'B栋', '3', '6', '0', '六人间', '0', '2026-01-06 00:57:28');
INSERT INTO `dormitory` VALUES ('9', '303', 'B栋', '3', '6', '0', '六人间', '0', '2026-01-06 00:57:28');
INSERT INTO `dormitory` VALUES ('10', '401', 'B栋', '4', '6', '0', '六人间', '0', '2026-01-06 00:57:28');

-- ----------------------------
-- Table structure for repair
-- ----------------------------
DROP TABLE IF EXISTS `repair`;
CREATE TABLE `repair` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `dormitory_id` int NOT NULL,
  `title` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` int NOT NULL DEFAULT '0' COMMENT '0-待处理，1-处理中，2-已完成',
  `repair_date` date NOT NULL,
  `complete_date` date DEFAULT NULL,
  `handler` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `note` text COLLATE utf8mb4_unicode_ci,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`),
  KEY `dormitory_id` (`dormitory_id`),
  CONSTRAINT `repair_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE,
  CONSTRAINT `repair_ibfk_2` FOREIGN KEY (`dormitory_id`) REFERENCES `dormitory` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of repair
-- ----------------------------
INSERT INTO `repair` VALUES ('1', '1', '1', '空调不制冷', '宿舍空调无法制冷，影响正常休息', '1', '2023-10-15', '2023-10-16', '张师傅', '已更换空调压缩机', '2026-01-06 00:58:46');
INSERT INTO `repair` VALUES ('2', '2', '1', '电灯损坏', '宿舍内一盏电灯损坏，需要更换', '1', '2023-10-20', '2023-10-20', '李师傅', '已更换灯泡', '2026-01-06 00:58:46');
INSERT INTO `repair` VALUES ('3', '3', '2', '水龙头漏水', '卫生间水龙头滴水，浪费水资源', '2', '2023-11-01', '2026-01-06', '管理员', '已经完成修复', '2026-01-06 00:58:46');
INSERT INTO `repair` VALUES ('4', '4', '2', '门锁损坏', '宿舍门锁无法正常使用，存在安全隐患', '0', '2023-11-05', null, null, null, '2026-01-06 00:58:46');
INSERT INTO `repair` VALUES ('5', '5', '3', '窗户密封不好', '窗户密封胶老化，漏风严重', '1', '2023-11-10', '2023-11-12', '王师傅', '已重新打密封胶', '2026-01-06 00:58:46');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `gender` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `age` int NOT NULL,
  `department` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `major` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `class_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `student_id` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '2023001', '张三', '男', '20', '计算机学院', '计算机科学与技术', '计科2301', '13800138001', '2026-01-06 00:57:55');
INSERT INTO `student` VALUES ('2', '2023002', '李四', '男', '19', '计算机学院', '软件工程', '软工2301', '13800138002', '2026-01-06 00:57:55');
INSERT INTO `student` VALUES ('3', '2023003', '王五', '女', '20', '电子信息学院', '电子信息工程', '电信2301', '13800138003', '2026-01-06 00:57:55');
INSERT INTO `student` VALUES ('4', '2023004', '赵六', '男', '21', '机械工程学院', '机械设计制造及其自动化', '机械2301', '13800138004', '2026-01-06 00:57:55');
INSERT INTO `student` VALUES ('5', '2023005', '孙七', '女', '19', '经济管理学院', '会计学', '会计2301', '13800138005', '2026-01-06 00:57:55');
INSERT INTO `student` VALUES ('6', '2023006', '周八', '男', '20', '外国语学院', '英语', '英语2301', '13800138006', '2026-01-06 00:57:55');
INSERT INTO `student` VALUES ('7', '2023007', '吴九', '女', '21', '数学学院', '数学与应用数学', '数学2301', '13800138007', '2026-01-06 00:57:55');
INSERT INTO `student` VALUES ('8', '2023008', '郑十', '男', '19', '物理学院', '物理学', '物理2301', '13800138008', '2026-01-06 00:57:55');
INSERT INTO `student` VALUES ('9', '2023009', '王十一', '女', '20', '化学学院', '化学', '化学2301', '13800138009', '2026-01-06 00:57:55');
INSERT INTO `student` VALUES ('10', '2023010', '李十二', '男', '21', '材料学院', '材料科学与工程', '材料2301', '13800138010', '2026-01-06 00:57:55');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` int NOT NULL COMMENT '0-管理员，1-宿管',
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', '0', '管理员', '13800138000', '2026-01-06 00:15:16');
