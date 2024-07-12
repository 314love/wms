/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : wms

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 27/07/2023 19:44:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '物品类型名称',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '体育类', '健身专家\n');
INSERT INTO `goods` VALUES (2, '书籍类', '知识海洋');
INSERT INTO `goods` VALUES (3, '食品类', '超好吃');

-- ----------------------------
-- Table structure for goodslist
-- ----------------------------
DROP TABLE IF EXISTS `goodslist`;
CREATE TABLE `goodslist`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '物品名称',
  `storage` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '仓库名称id',
  `goods` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '物品类型名称id',
  `count` int(0) NULL DEFAULT NULL COMMENT '物品数量',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goodslist
-- ----------------------------
INSERT INTO `goodslist` VALUES (1, '足球', '2', '1', 33, '22');
INSERT INTO `goodslist` VALUES (2, '西游记', '2', '2', 57, '33');
INSERT INTO `goodslist` VALUES (5, '三只松鼠', '1', '3', 111, '好吃，真好吃');
INSERT INTO `goodslist` VALUES (6, '哑铃', '1', '1', 122, '');

-- ----------------------------
-- Table structure for maintenance
-- ----------------------------
DROP TABLE IF EXISTS `maintenance`;
CREATE TABLE `maintenance`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type_name` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '维修队名称',
  `phone` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '电子邮件',
  `count` int(0) NULL DEFAULT NULL COMMENT '队员数量',
  `remark` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of maintenance
-- ----------------------------
INSERT INTO `maintenance` VALUES (1, '虫子维修队', '19890905753', '3170540355@qq.com', 4, '是很棒的小组Oops');
INSERT INTO `maintenance` VALUES (3, '小火箭飞机队', '19890905753', '3170540355@qq.com', 3, '速度的代名词');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(0) NOT NULL COMMENT '主键',
  `menuCode` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单编码',
  `menuName` varchar(16) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单名字',
  `menuLevel` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单级别',
  `menuParentCode` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单的父code',
  `menuClick` varchar(16) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '点击触发的函数',
  `menuRight` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '权限 0超级管理员，1表示管理员，2表示普通用户，可以用逗号组合使用',
  `menuComponent` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'vue文件名称',
  `menuIcon` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'element-ui按钮名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '001', '管理员管理', '1', NULL, 'Admin', '0', 'admin/AdminManage.vue', 'el-icon-s-custom');
INSERT INTO `menu` VALUES (2, '002', '用户管理', '1', NULL, 'User', '0,1', 'user/UserManage.vue', 'el-icon-user-solid');
INSERT INTO `menu` VALUES (3, '003', '仓库管理', '1', NULL, 'Storage', '0,1', 'storage/StorageManage.vue', 'el-icon-office-building');
INSERT INTO `menu` VALUES (4, '004', '物品分类管理', '1', NULL, 'Goods', '0,1', 'goods/GoodsManage.vue', 'el-icon-s-grid');
INSERT INTO `menu` VALUES (5, '005', '物品管理', '1', NULL, 'GoodsList', '0,1,2', 'goodslist/GoodslistManage.vue', 'el-icon-s-shop');
INSERT INTO `menu` VALUES (6, '006', '出入库管理', '1', NULL, 'Repository', '0,1', 'repository/RepositoryManage.vue', 'el-icon-s-data');
INSERT INTO `menu` VALUES (7, '007', '记录管理', '1', NULL, 'Record', '0,1,2', 'record/RecordManage.vue', 'el-icon-s-order');
INSERT INTO `menu` VALUES (8, '008', '维修团队管理', '1', NULL, 'Maintenance', '0,1,2', 'maintenance/MaintenanceManage.vue', 'el-icon-phone');
INSERT INTO `menu` VALUES (9, '009', '预约维修管理', '1', NULL, 'Order', '0,1', 'order/OrderManage.vue', 'el-icon-s-comment');
INSERT INTO `menu` VALUES (10, '0010', '预约记录管理', '1', NULL, 'RecordTwo', '0,1,2', 'recordTwo/RecordTwoManage.vue', 'el-icon-s-order');

-- ----------------------------
-- Table structure for mytable
-- ----------------------------
DROP TABLE IF EXISTS `mytable`;
CREATE TABLE `mytable`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mytable
-- ----------------------------
INSERT INTO `mytable` VALUES (1, 'zhanbgsan');

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goodslist` int(0) NULL DEFAULT NULL COMMENT '货品id',
  `userId` int(0) NULL DEFAULT NULL COMMENT '申请人id',
  `adminId` int(0) NULL DEFAULT NULL COMMENT '操作人id',
  `count` int(0) NULL DEFAULT NULL COMMENT '数量',
  `createtime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '操作时间',
  `remark` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES (1, 1, 3, 5, 22, '2022-11-24 16:31:59', NULL);
INSERT INTO `record` VALUES (2, 2, 3, 5, 33, '2022-11-24 21:10:58', NULL);
INSERT INTO `record` VALUES (14, 1, 3, 1, 20, '2022-11-25 18:11:05', '进20个');
INSERT INTO `record` VALUES (15, 1, 3, 1, 22, '2022-11-25 18:13:25', '2');
INSERT INTO `record` VALUES (16, 1, 3, 1, 22, '2022-11-25 18:13:39', '222');
INSERT INTO `record` VALUES (17, 1, 3, 1, 1, '2022-11-25 18:18:10', '11');
INSERT INTO `record` VALUES (61, 1, 6, 1, 24, '2022-11-27 21:43:32', '');
INSERT INTO `record` VALUES (62, 2, 6, 1, 33, '2022-11-27 21:43:48', '');
INSERT INTO `record` VALUES (63, 1, 3, 1, -1, '2022-11-28 20:50:43', '');
INSERT INTO `record` VALUES (64, 1, 3, 1, -1, '2022-11-28 20:52:48', '');

-- ----------------------------
-- Table structure for recordtwo
-- ----------------------------
DROP TABLE IF EXISTS `recordtwo`;
CREATE TABLE `recordtwo`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `maintenance` int(0) NULL DEFAULT NULL COMMENT '维修队名称id',
  `userId` int(0) NULL DEFAULT NULL COMMENT '申请人id',
  `adminId` int(0) NULL DEFAULT NULL COMMENT '操作人id',
  `count` int(0) NULL DEFAULT NULL COMMENT '数量',
  `createtime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '操作时间',
  `remark` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recordtwo
-- ----------------------------
INSERT INTO `recordtwo` VALUES (1, 1, 3, 2, 2, '2022-11-28 17:48:36', NULL);
INSERT INTO `recordtwo` VALUES (63, 1, 3, 1, -1, '2022-11-28 18:35:08', '');
INSERT INTO `recordtwo` VALUES (64, 1, 3, 1, -1, '2022-11-28 18:36:41', '');
INSERT INTO `recordtwo` VALUES (65, 1, 3, 1, -5, '2022-11-28 18:44:14', '');
INSERT INTO `recordtwo` VALUES (66, 1, 6, 1, -1, '2022-11-28 20:49:37', '');
INSERT INTO `recordtwo` VALUES (67, 3, 6, 1, -1, '2022-11-28 21:49:18', '修理');
INSERT INTO `recordtwo` VALUES (68, 1, 4, 1, -1, '2023-01-02 21:31:06', '');

-- ----------------------------
-- Table structure for storage
-- ----------------------------
DROP TABLE IF EXISTS `storage`;
CREATE TABLE `storage`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '仓库名称',
  `remark` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of storage
-- ----------------------------
INSERT INTO `storage` VALUES (1, '仓库1', 'A\n');
INSERT INTO `storage` VALUES (2, '仓库2', 'B');
INSERT INTO `storage` VALUES (3, '仓库3', 'C');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `no` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '账号',
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '名字',
  `password` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `sex` int(0) NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '电话',
  `role_id` int(0) NULL DEFAULT NULL COMMENT '角色 0超级管理员，1管理员，2普通账号',
  `isValid` varchar(4) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT 'Y' COMMENT '是否有效，Y有效，其他无效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'sa', '超级管理员', '111', 18, 1, '19890905753', 0, 'Y');
INSERT INTO `user` VALUES (2, 'wwe', '管理员', '111', 11, 1, '19890905753', 1, 'Y');
INSERT INTO `user` VALUES (3, 'bu', '用户', '111', 22, 1, '19890905753', 2, 'Y');
INSERT INTO `user` VALUES (4, 'ff', '用户2', '111', 22, 1, '19890905753', 2, 'Y');
INSERT INTO `user` VALUES (5, 'gg', '管理员2', '111', 33, 0, '19890905753', 1, 'Y');
INSERT INTO `user` VALUES (6, 'cc', '小美', '111', 15, 0, '19890905753', 2, 'Y');

SET FOREIGN_KEY_CHECKS = 1;
