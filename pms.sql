/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80034
 Source Host           : localhost:3306
 Source Schema         : pms

 Target Server Type    : MySQL
 Target Server Version : 80034
 File Encoding         : 65001

 Date: 12/07/2024 10:25:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for budgets
-- ----------------------------
DROP TABLE IF EXISTS `budgets`;
CREATE TABLE `budgets`  (
  `budget_id` int NOT NULL AUTO_INCREMENT,
  `project_id` int NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `amount` decimal(10, 2) NOT NULL,
  `currency` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` enum('SETTLED','UNSETTLED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'UNSETTLED',
  `settled_date` date NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`budget_id`) USING BTREE,
  INDEX `budgets_ibfk_1`(`project_id` ASC) USING BTREE,
  CONSTRAINT `budgets_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `project_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 112 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of budgets
-- ----------------------------
INSERT INTO `budgets` VALUES (37, 1, '项目Alpha', '项目Alpha的初始预算', 10000.00, 'USD', 'SETTLED', NULL, '2024-07-10 17:27:51', '2024-07-10 17:27:51');
INSERT INTO `budgets` VALUES (38, 1, '年度运营成本预算', '包括办公开支、市场推广费用等', 50000.00, 'CNY', 'SETTLED', '2024-01-15', '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (39, 1, '人力资源费用预算', '员工工资、福利及培训费用', 30000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (40, 1, '技术开发支出预算', '软件开发、硬件采购费用', 70000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (41, 1, '营销活动费用预算', '线上线下活动推广费用', 20000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (42, 1, '办公设备采购预算', '电脑、打印机及办公家具', 15000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (43, 2, '产品研发预算', '新产品研发及测试费用', 80000.00, 'CNY', 'SETTLED', '2024-02-20', '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (44, 2, '市场推广费用预算', '线上广告投放及促销费用', 40000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (45, 2, '人员招聘费用预算', '招聘顾问费及面试费用', 25000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (46, 3, '新项目启动资金预算', '项目立项阶段资金投入', 60000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (47, 3, '市场调研及分析费用预算', '市场调查报告及数据分析费用', 35000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (48, 3, '客户拓展活动费用预算', '客户会议及招待费用', 18000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (49, 3, 'IT基础设施建设费用预算', '服务器购买及云服务费用', 45000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (50, 4, '产品改进及优化预算', '产品功能优化及更新费用', 70000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (51, 4, '市场推广活动费用预算', '线下展会及市场推广费用', 30000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (52, 5, '业务拓展费用预算', '新市场业务拓展费用', 55000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (53, 5, '产品生产及运输成本预算', '产品生产及物流费用', 40000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (54, 6, '市场竞争分析费用预算', '竞品调研及市场分析费用', 30000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (55, 6, '供应链优化费用预算', '供应商评估及合作优化费用', 25000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (56, 6, '法律顾问及合规费用预算', '法律咨询及合规审查费用', 18000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (57, 6, '研究与开发费用预算', '新技术研发及专利申请费用', 60000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (58, 7, '人力资源培训及发展费用预算', '员工培训及职业发展费用', 35000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (59, 7, '客户服务及支持费用预算', '客户服务中心及售后支持费用', 25000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (60, 7, '市场推广及品牌宣传费用预算', '品牌宣传及市场推广活动费用', 45000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (61, 8, '财务及会计服务费用预算', '财务报表编制及会计服务费用', 30000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (62, 8, '信息技术基础设施升级费用预算', 'IT系统及基础设施升级费用', 40000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (63, 8, '客户关系管理系统开发费用预算', 'CRM系统开发及运维费用', 50000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (64, 9, '产品品质管理及检验费用预算', '产品质量管理及检验费用', 25000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (65, 9, '客户体验优化费用预算', '网站用户体验优化及客户反馈调研', 20000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (66, 10, '市场竞争战略分析费用预算', '市场竞争分析及战略策划费用', 35000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (67, 10, '创新科技研究及开发费用预算', '前沿技术研究及创新开发费用', 50000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (68, 11, '员工福利及员工活动费用预算', '员工福利及团建活动费用', 18000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (69, 11, '环境保护及可持续发展费用预算', '环境保护项目及可持续发展倡议', 40000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (70, 11, '市场调研及消费者行为分析费用预算', '市场调研及消费者行为分析费用', 30000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (71, 12, '销售渠道拓展费用预算', '新销售渠道开发及合作伙伴拓展', 45000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (72, 12, '品牌宣传及市场推广费用预算', '品牌宣传及市场推广费用', 35000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (73, 13, '供应链管理及优化费用预算', '供应链管理及效率优化费用', 40000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (74, 13, '市场竞争分析及策略制定费用预算', '市场竞争分析及市场策略制定', 55000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (75, 14, '人力资源战略及招聘费用预算', '人力资源战略及招聘费用', 30000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (76, 14, '法律顾问及合规咨询费用预算', '法律顾问及合规咨询费用', 25000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (77, 14, '技术研发及创新投入费用预算', '技术研发及创新投入费用', 60000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (78, 15, '市场调研及消费者洞察费用预算', '市场调研及消费者洞察费用', 35000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (79, 15, '企业社会责任及公益活动费用预算', '企业社会责任及公益活动费用', 18000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (80, 16, '客户关系管理及客户服务费用预算', '客户关系管理及客户服务费用', 45000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (81, 16, '品牌推广及市场宣传费用预算', '品牌推广及市场宣传费用', 30000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (82, 17, '供应链管理及物流优化费用预算', '供应链管理及物流优化费用', 40000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (83, 17, '市场竞争分析及市场策略费用预算', '市场竞争分析及市场策略费用', 55000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (84, 18, '人才培养及团队建设费用预算', '人才培养及团队建设费用', 30000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (85, 18, '法律事务及合规审查费用预算', '法律事务及合规审查费用', 25000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (86, 19, '技术创新及研发投入费用预算', '技术创新及研发投入费用', 60000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (87, 19, '市场推广活动及促销费用预算', '市场推广活动及促销费用', 45000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (88, 20, '企业文化建设及员工福利费用预算', '企业文化建设及员工福利费用', 18000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (89, 20, '环保项目及可持续发展投入费用预算', '环保项目及可持续发展投入费用', 40000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (90, 21, '市场调研及市场分析费用预算', '市场调研及市场分析费用', 30000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (91, 21, '消费者行为研究及数据分析费用预算', '消费者行为研究及数据分析费用', 25000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (92, 22, 'IT基础设施建设及系统优化费用预算', 'IT基础设施建设及系统优化费用', 40000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (93, 22, '软件开发及技术支持费用预算', '软件开发及技术支持费用', 50000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (94, 23, '客户服务中心及售后支持费用预算', '客户服务中心及售后支持费用', 35000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (95, 23, '市场宣传及品牌推广费用预算', '市场宣传及品牌推广费用', 30000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (96, 24, '供应链管理及供应商评估费用预算', '供应链管理及供应商评估费用', 40000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (97, 24, '市场竞争分析及竞品调研费用预算', '市场竞争分析及竞品调研费用', 55000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (98, 25, '人力资源招聘及培训费用预算', '人力资源招聘及培训费用', 30000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (99, 25, '法律顾问及合规审查费用预算', '法律顾问及合规审查费用', 25000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (100, 26, '技术创新及前沿研发费用预算', '技术创新及前沿研发费用', 60000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (101, 26, '市场推广活动及品牌传播费用预算', '市场推广活动及品牌传播费用', 45000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (102, 27, '企业社会责任及公益活动费用预算', '企业社会责任及公益活动费用', 18000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (103, 27, '市场调研及消费者洞察费用预算', '市场调研及消费者洞察费用', 35000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (104, 28, '客户关系管理及服务优化费用预算', '客户关系管理及服务优化费用', 45000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (105, 28, '品牌推广及市场宣传费用预算', '品牌推广及市场宣传费用', 30000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (106, 29, '供应链管理及物流成本优化费用预算', '供应链管理及物流成本优化费用', 40000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (107, 29, '市场竞争分析及市场策略制定费用预算', '市场竞争分析及市场策略制定费用', 55000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (108, 30, '人才培养及团队建设费用预算', '人才培养及团队建设费用', 30000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (109, 30, '法律事务及合规审查费用预算', '法律事务及合规审查费用', 25000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (110, 31, '技术创新及研发投入费用预算', '技术创新及研发投入费用', 60000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');
INSERT INTO `budgets` VALUES (111, 31, '市场推广活动及促销费用预算', '市场推广活动及促销费用', 45000.00, 'CNY', 'UNSETTLED', NULL, '2024-07-10 18:32:50', '2024-07-10 18:32:50');

-- ----------------------------
-- Table structure for companies
-- ----------------------------
DROP TABLE IF EXISTS `companies`;
CREATE TABLE `companies`  (
  `company_id` int NOT NULL AUTO_INCREMENT,
  `company_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`company_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '所属公司表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of companies
-- ----------------------------
INSERT INTO `companies` VALUES (2, '华夏科技有限公司');
INSERT INTO `companies` VALUES (3, '中联创新股份有限公司');
INSERT INTO `companies` VALUES (4, '东方智造集团');
INSERT INTO `companies` VALUES (5, '天翼信息技术有限公司');
INSERT INTO `companies` VALUES (6, '北斗导航股份有限公司');
INSERT INTO `companies` VALUES (7, '蓝海数码科技有限公司');
INSERT INTO `companies` VALUES (8, '金盾安全系统有限公司');
INSERT INTO `companies` VALUES (9, '长江新能源有限公司');
INSERT INTO `companies` VALUES (10, '未来智能设备有限公司');
INSERT INTO `companies` VALUES (11, '飞跃电气股份有限公司');

-- ----------------------------
-- Table structure for departments
-- ----------------------------
DROP TABLE IF EXISTS `departments`;
CREATE TABLE `departments`  (
  `department_id` int NOT NULL AUTO_INCREMENT,
  `department_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`department_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '所属部门表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of departments
-- ----------------------------
INSERT INTO `departments` VALUES (1, '人力资源部');
INSERT INTO `departments` VALUES (2, '市场部');
INSERT INTO `departments` VALUES (3, '财务部');
INSERT INTO `departments` VALUES (4, '运营部');
INSERT INTO `departments` VALUES (5, '信息技术部');
INSERT INTO `departments` VALUES (6, '销售部');
INSERT INTO `departments` VALUES (7, '客户支持部');
INSERT INTO `departments` VALUES (8, '研发部');
INSERT INTO `departments` VALUES (9, '法务部');
INSERT INTO `departments` VALUES (10, '产品管理部');

-- ----------------------------
-- Table structure for plans
-- ----------------------------
DROP TABLE IF EXISTS `plans`;
CREATE TABLE `plans`  (
  `plan_id` int NOT NULL AUTO_INCREMENT COMMENT '项目计划ID，主键，自动递增',
  `project_id` int NOT NULL COMMENT '所属项目ID，外键关联 projects_info 表',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '项目计划名称，不能为空',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '项目计划描述，可以为空',
  `start_date` date NOT NULL COMMENT '项目计划开始日期，不能为空，必须早于截止日期，并且晚于前置计划的完成日期',
  `due_date` date NOT NULL COMMENT '项目计划截止日期，不能为空',
  `priority` enum('LOW','MEDIUM','HIGH') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'MEDIUM' COMMENT '项目计划优先级，默认值为 medium',
  `status` enum('COMPLETED','NOT_COMPLETED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NOT_COMPLETED' COMMENT '项目计划状态，默认值为未完成',
  `assigned_to` int NOT NULL COMMENT '项目计划负责人ID，外键关联 users 表，不能为空',
  `predecessor_plan_id` int NULL DEFAULT NULL COMMENT '前置项目计划ID，外键关联 plans 表，可以为空',
  `completion_date` date NULL DEFAULT NULL COMMENT '项目计划完成日期，可以为空',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '项目计划创建时间，默认值为当前时间戳',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '项目计划更新时间，默认值为当前时间戳，并在更新时自动更新',
  PRIMARY KEY (`plan_id`) USING BTREE,
  INDEX `plans_ibfk_1`(`project_id` ASC) USING BTREE,
  INDEX `plans_ibfk_2`(`assigned_to` ASC) USING BTREE,
  INDEX `plans_ibfk_3`(`predecessor_plan_id` ASC) USING BTREE,
  CONSTRAINT `plans_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `project_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `plans_ibfk_2` FOREIGN KEY (`assigned_to`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `plans_ibfk_3` FOREIGN KEY (`predecessor_plan_id`) REFERENCES `plans` (`plan_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `plans_chk_1` CHECK (`start_date` < `due_date`)
) ENGINE = InnoDB AUTO_INCREMENT = 271 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of plans
-- ----------------------------
INSERT INTO `plans` VALUES (172, 1, '新产品开发项目', '新产品的设计、开发和测试', '2024-01-15', '2024-03-15', 'MEDIUM', 'NOT_COMPLETED', 240720, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (173, 1, '新产品开发项目', '市场定位和竞争分析', '2024-02-01', '2024-04-01', 'HIGH', 'NOT_COMPLETED', 240721, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (174, 1, '新产品开发项目', '产品原型开发和用户反馈', '2024-02-15', '2024-04-15', 'MEDIUM', 'COMPLETED', 240722, NULL, '2024-04-14', '2024-07-10 19:00:20', '2024-07-11 17:22:03');
INSERT INTO `plans` VALUES (175, 2, '市场推广活动', '市场营销策略制定和执行', '2024-02-15', '2024-04-15', 'HIGH', 'NOT_COMPLETED', 240723, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (176, 2, '市场推广活动', '广告发布和品牌推广', '2024-03-01', '2024-05-01', 'MEDIUM', 'NOT_COMPLETED', 240718, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (177, 2, '市场推广活动', '社交媒体宣传和用户互动', '2024-03-15', '2024-05-15', 'HIGH', 'NOT_COMPLETED', 240720, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (178, 3, '新硬件设计', '硬件架构设计和系统集成', '2024-03-15', '2024-05-15', 'MEDIUM', 'NOT_COMPLETED', 240721, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (179, 3, '新硬件设计', '电路设计和原型制作', '2024-04-01', '2024-06-01', 'HIGH', 'NOT_COMPLETED', 240722, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (180, 3, '新硬件设计', '软件驱动开发和测试', '2024-04-15', '2024-06-15', 'MEDIUM', 'NOT_COMPLETED', 240723, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (181, 4, '内部流程优化', '流程分析和改进建议', '2024-04-15', '2024-06-15', 'HIGH', 'NOT_COMPLETED', 240718, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (182, 4, '内部流程优化', '部门间协作和信息流优化', '2024-05-01', '2024-07-01', 'MEDIUM', 'NOT_COMPLETED', 240720, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (183, 4, '内部流程优化', '系统集成和自动化实施', '2024-05-15', '2024-07-15', 'HIGH', 'NOT_COMPLETED', 240721, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (184, 5, '客户关系管理系统', 'CRM系统需求分析和功能设计', '2024-05-15', '2024-07-15', 'MEDIUM', 'COMPLETED', 240722, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:05:23');
INSERT INTO `plans` VALUES (185, 5, '客户关系管理系统', '数据库架构设计和系统部署', '2024-06-01', '2024-08-01', 'HIGH', 'NOT_COMPLETED', 240723, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (186, 5, '客户关系管理系统', '用户培训和系统测试', '2024-06-15', '2024-08-15', 'MEDIUM', 'NOT_COMPLETED', 240718, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (187, 6, '国际市场拓展', '市场需求分析和竞争情报收集', '2024-06-15', '2024-08-15', 'HIGH', 'NOT_COMPLETED', 240720, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (188, 6, '国际市场拓展', '业务合作伙伴招募和洽谈', '2024-07-01', '2024-09-01', 'MEDIUM', 'NOT_COMPLETED', 240721, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (189, 6, '国际市场拓展', '市场推广活动和品牌建设', '2024-07-15', '2024-09-15', 'HIGH', 'NOT_COMPLETED', 240722, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (190, 7, '移动应用升级', '应用性能优化和用户体验改进', '2024-07-15', '2024-09-15', 'MEDIUM', 'COMPLETED', 240723, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:09:02');
INSERT INTO `plans` VALUES (191, 7, '移动应用升级', '新功能开发和版本发布', '2024-08-01', '2024-10-01', 'HIGH', 'NOT_COMPLETED', 240718, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (192, 7, '移动应用升级', '数据安全加密和后端系统更新', '2024-08-15', '2024-10-15', 'MEDIUM', 'NOT_COMPLETED', 240720, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (193, 8, '财务系统更新', '财务软件功能改进和账务处理', '2024-08-15', '2024-10-15', 'HIGH', 'NOT_COMPLETED', 240721, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (194, 8, '财务系统更新', '税务合规和财务报告生成', '2024-09-01', '2024-11-01', 'MEDIUM', 'COMPLETED', 240722, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:09:05');
INSERT INTO `plans` VALUES (195, 8, '财务系统更新', '财务系统安全审计和系统优化', '2024-09-15', '2024-11-15', 'HIGH', 'NOT_COMPLETED', 240723, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (196, 9, '北美市场研究', '市场调研报告和市场趋势分析', '2024-09-15', '2024-11-15', 'MEDIUM', 'NOT_COMPLETED', 240718, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (197, 9, '北美市场研究', '客户需求调查和潜在客户分析', '2024-10-01', '2024-12-01', 'HIGH', 'NOT_COMPLETED', 240720, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (198, 9, '北美市场研究', '市场策略制定和销售预测', '2024-10-15', '2025-01-15', 'MEDIUM', 'COMPLETED', 240721, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:09:03');
INSERT INTO `plans` VALUES (199, 10, '法律合规审查', '公司法务合规检查和法律风险评估', '2024-10-15', '2025-01-15', 'HIGH', 'NOT_COMPLETED', 240722, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (200, 10, '法律合规审查', '合同审查和法律文件更新', '2024-11-01', '2025-02-01', 'MEDIUM', 'NOT_COMPLETED', 240723, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (201, 10, '法律合规审查', '知识产权保护和法律培训', '2024-11-15', '2025-03-01', 'HIGH', 'NOT_COMPLETED', 240718, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (202, 11, '新产品测试', '新产品功能测试和用户体验评估', '2024-11-15', '2025-03-01', 'MEDIUM', 'COMPLETED', 240720, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:09:08');
INSERT INTO `plans` VALUES (203, 11, '新产品测试', '系统稳定性测试和质量保证', '2024-12-01', '2025-03-15', 'HIGH', 'NOT_COMPLETED', 240721, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (204, 11, '新产品测试', '产品发布前的最后测试和修复', '2024-12-15', '2025-04-01', 'MEDIUM', 'NOT_COMPLETED', 240722, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (205, 12, '网络安全升级', '网络安全漏洞扫描和修复', '2025-01-15', '2025-04-01', 'HIGH', 'COMPLETED', 240723, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:09:10');
INSERT INTO `plans` VALUES (206, 12, '网络安全升级', '数据加密和访问控制更新', '2025-02-01', '2025-05-01', 'MEDIUM', 'NOT_COMPLETED', 240718, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (207, 12, '网络安全升级', '安全策略更新和员工培训', '2025-02-15', '2025-05-15', 'HIGH', 'NOT_COMPLETED', 240720, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (208, 13, '品牌重塑', '品牌定位和市场调查', '2025-02-15', '2025-05-15', 'MEDIUM', 'NOT_COMPLETED', 240721, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (209, 13, '品牌重塑', '品牌视觉识别系统设计', '2025-03-01', '2025-06-01', 'HIGH', 'COMPLETED', 240722, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:09:13');
INSERT INTO `plans` VALUES (210, 13, '品牌重塑', '品牌宣传和形象重建', '2025-03-15', '2025-07-01', 'MEDIUM', 'NOT_COMPLETED', 240723, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (211, 14, '用户体验改进', '用户界面设计和交互体验优化', '2025-03-15', '2025-07-01', 'HIGH', 'NOT_COMPLETED', 240718, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (212, 14, '用户体验改进', '用户反馈分析和功能优化', '2025-04-01', '2025-08-01', 'MEDIUM', 'NOT_COMPLETED', 240720, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (213, 14, '用户体验改进', '用户培训和技术支持改进', '2025-04-15', '2025-08-15', 'HIGH', 'NOT_COMPLETED', 240721, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (214, 15, '产品培训计划', '产品知识培训和销售技巧训练', '2025-04-15', '2025-08-15', 'MEDIUM', 'NOT_COMPLETED', 240722, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (215, 15, '产品培训计划', '客户服务技能培训和应对策略', '2025-05-01', '2025-09-01', 'HIGH', 'NOT_COMPLETED', 240723, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (216, 15, '产品培训计划', '销售团队协作和案例分享', '2025-05-15', '2025-09-15', 'MEDIUM', 'NOT_COMPLETED', 240718, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (217, 16, '市场营销活动', '市场活动策划和执行计划', '2025-05-15', '2025-09-15', 'HIGH', 'NOT_COMPLETED', 240720, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (218, 16, '市场营销活动', '推广资料制作和媒体投放', '2025-06-01', '2025-10-01', 'MEDIUM', 'NOT_COMPLETED', 240721, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (219, 16, '市场营销活动', '市场反馈分析和效果评估', '2025-06-15', '2025-10-15', 'HIGH', 'NOT_COMPLETED', 240722, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (220, 17, '产品发布会', '发布会策划和活动布置', '2025-06-15', '2025-10-15', 'MEDIUM', 'NOT_COMPLETED', 240723, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (221, 17, '产品发布会', '演讲嘉宾邀请和媒体关注', '2025-07-01', '2025-11-01', 'HIGH', 'NOT_COMPLETED', 240718, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (222, 17, '产品发布会', '现场直播和社交媒体互动', '2025-07-15', '2025-11-15', 'MEDIUM', 'NOT_COMPLETED', 240720, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (223, 18, '内部审计', '公司内部审计和风险评估', '2025-07-15', '2025-11-15', 'HIGH', 'NOT_COMPLETED', 240721, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (224, 18, '内部审计', '审计报告撰写和内部控制建议', '2025-08-01', '2025-12-01', 'MEDIUM', 'NOT_COMPLETED', 240722, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (225, 18, '内部审计', '内部流程改善和合规建议', '2025-08-15', '2025-12-15', 'HIGH', 'NOT_COMPLETED', 240723, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (226, 19, '客户满意度调查', '客户调研问卷设计和分发', '2025-08-15', '2025-12-15', 'MEDIUM', 'NOT_COMPLETED', 240718, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (227, 19, '客户满意度调查', '调查结果分析和反馈汇报', '2025-09-01', '2026-01-15', 'HIGH', 'NOT_COMPLETED', 240720, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (228, 19, '客户满意度调查', '客户投诉处理和服务改进', '2025-09-15', '2026-02-01', 'MEDIUM', 'NOT_COMPLETED', 240721, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (229, 20, '新业务拓展', '新业务模式探索和市场测试', '2025-09-15', '2026-02-01', 'HIGH', 'NOT_COMPLETED', 240722, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (230, 20, '新业务拓展', '业务合作伙伴招募和合作协议', '2025-10-01', '2026-03-01', 'MEDIUM', 'NOT_COMPLETED', 240723, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (231, 20, '新业务拓展', '业务推广和客户关系建立', '2025-10-15', '2026-03-15', 'HIGH', 'NOT_COMPLETED', 240718, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (232, 21, '培训课程开发', '员工技能培训计划制定', '2025-10-15', '2026-03-15', 'MEDIUM', 'NOT_COMPLETED', 240720, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (233, 21, '培训课程开发', '课程内容编写和教材制作', '2025-11-01', '2026-04-01', 'HIGH', 'NOT_COMPLETED', 240721, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (234, 21, '培训课程开发', '培训计划实施和效果评估', '2025-11-15', '2026-04-15', 'MEDIUM', 'NOT_COMPLETED', 240722, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (235, 22, '新技术研究', '新技术领域的市场调研', '2026-01-15', '2026-04-01', 'HIGH', 'NOT_COMPLETED', 240723, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (236, 22, '新技术研究', '技术原理分析和实验设计', '2026-02-01', '2026-05-01', 'MEDIUM', 'NOT_COMPLETED', 240718, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (237, 22, '新技术研究', '技术应用探索和前瞻性研究', '2026-02-15', '2026-05-15', 'HIGH', 'NOT_COMPLETED', 240720, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (238, 23, '客户服务改进', '客户支持流程分析和改善', '2026-02-15', '2026-05-15', 'MEDIUM', 'NOT_COMPLETED', 240721, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (239, 23, '客户服务改进', '客户服务技能培训和团队建设', '2026-03-01', '2026-06-01', 'HIGH', 'NOT_COMPLETED', 240722, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (240, 23, '客户服务改进', '客户反馈处理和服务质量监控', '2026-03-15', '2026-07-01', 'MEDIUM', 'NOT_COMPLETED', 240723, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (241, 24, '在线平台优化', '用户体验分析和界面优化', '2026-03-15', '2026-07-01', 'HIGH', 'NOT_COMPLETED', 240718, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (242, 24, '在线平台优化', '平台功能更新和性能优化', '2026-04-01', '2026-08-01', 'MEDIUM', 'NOT_COMPLETED', 240720, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (243, 24, '在线平台优化', '安全防护措施和系统稳定性', '2026-04-15', '2026-08-15', 'HIGH', 'NOT_COMPLETED', 240721, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (244, 25, '新市场调研', '市场需求分析和竞争对手研究', '2026-04-15', '2026-08-15', 'MEDIUM', 'NOT_COMPLETED', 240722, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (245, 25, '新市场调研', '新市场潜力评估和市场定位', '2026-05-01', '2026-09-01', 'HIGH', 'NOT_COMPLETED', 240723, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (246, 25, '新市场调研', '市场推广策略制定和实施', '2026-05-15', '2026-09-15', 'MEDIUM', 'NOT_COMPLETED', 240718, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (247, 26, '员工福利升级', '员工福利调查和需求分析', '2026-05-15', '2026-09-15', 'HIGH', 'NOT_COMPLETED', 240720, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (248, 26, '员工福利升级', '福利政策设计和方案制定', '2026-06-01', '2026-10-01', 'MEDIUM', 'NOT_COMPLETED', 240721, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (249, 26, '员工福利升级', '福利项目推广和员工反馈', '2026-06-15', '2026-10-15', 'HIGH', 'NOT_COMPLETED', 240722, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (250, 27, '生产线自动化', '生产流程分析和自动化可行性研究', '2026-06-15', '2026-10-15', 'MEDIUM', 'NOT_COMPLETED', 240723, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (251, 27, '生产线自动化', '自动化设备采购和系统集成', '2026-07-01', '2026-11-01', 'HIGH', 'NOT_COMPLETED', 240718, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (252, 27, '生产线自动化', '生产效率测试和工艺优化', '2026-07-15', '2026-11-15', 'MEDIUM', 'NOT_COMPLETED', 240720, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (253, 28, '社交媒体策略', '社交媒体平台分析和用户洞察', '2026-07-15', '2026-11-15', 'HIGH', 'NOT_COMPLETED', 240721, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (254, 28, '社交媒体策略', '内容创意策略和社区管理', '2026-08-01', '2026-12-01', 'MEDIUM', 'NOT_COMPLETED', 240722, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (255, 28, '社交媒体策略', '社交媒体广告投放和效果监控', '2026-08-15', '2026-12-15', 'HIGH', 'NOT_COMPLETED', 240723, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (256, 29, '国际合作项目', '国际市场合作伙伴招募和谈判', '2026-08-15', '2026-12-15', 'MEDIUM', 'NOT_COMPLETED', 240718, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (257, 29, '国际合作项目', '合作协议签订和项目启动', '2026-09-01', '2027-01-15', 'HIGH', 'NOT_COMPLETED', 240720, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (258, 29, '国际合作项目', '项目执行和跨文化管理', '2026-09-15', '2027-02-01', 'MEDIUM', 'NOT_COMPLETED', 240721, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (259, 30, '新产品发布', '产品发布计划和市场推广', '2026-09-15', '2027-02-01', 'HIGH', 'NOT_COMPLETED', 240722, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (260, 30, '新产品发布', '新产品宣传资料制作和发布', '2026-10-01', '2027-03-01', 'MEDIUM', 'NOT_COMPLETED', 240723, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (261, 30, '新产品发布', '市场反馈收集和产品改进', '2026-10-15', '2027-03-15', 'HIGH', 'NOT_COMPLETED', 240718, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (262, 31, '员工培训计划', '新员工入职培训和职业素养提升', '2026-10-15', '2027-03-15', 'MEDIUM', 'NOT_COMPLETED', 240720, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (263, 31, '员工培训计划', '团队协作技能培训和领导力发展', '2026-11-01', '2027-04-01', 'HIGH', 'NOT_COMPLETED', 240721, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (264, 31, '员工培训计划', '员工绩效评估和个人成长规划', '2026-11-15', '2027-04-15', 'MEDIUM', 'NOT_COMPLETED', 240722, NULL, NULL, '2024-07-10 19:00:20', '2024-07-10 19:00:20');
INSERT INTO `plans` VALUES (265, 1, 'Feature Enhancement', 'Enhance existing features based on user feedback', '2024-07-15', '2024-08-30', 'MEDIUM', 'NOT_COMPLETED', 240722, NULL, NULL, '2024-07-10 19:07:46', '2024-07-10 19:07:46');
INSERT INTO `plans` VALUES (266, 1, 'Bug Fixes', 'Resolve reported bugs in the application', '2024-07-20', '2024-08-10', 'HIGH', 'NOT_COMPLETED', 240723, NULL, NULL, '2024-07-10 19:07:46', '2024-07-10 19:07:46');

-- ----------------------------
-- Table structure for project_categories
-- ----------------------------
DROP TABLE IF EXISTS `project_categories`;
CREATE TABLE `project_categories`  (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '项目大类表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of project_categories
-- ----------------------------
INSERT INTO `project_categories` VALUES (1, '软件开发');
INSERT INTO `project_categories` VALUES (2, '硬件开发');
INSERT INTO `project_categories` VALUES (3, '市场推广');
INSERT INTO `project_categories` VALUES (4, '运营管理');

-- ----------------------------
-- Table structure for project_info
-- ----------------------------
DROP TABLE IF EXISTS `project_info`;
CREATE TABLE `project_info`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '项目ID',
  `project_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '项目名称',
  `english_abbreviation` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '英文简称',
  `project_stage` int NOT NULL COMMENT '项目阶段ID',
  `planned_launch_date` date NOT NULL COMMENT '计划上线日期',
  `financial_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '财务编码',
  `project_category` int NOT NULL COMMENT '项目大类ID',
  `planned_initial_inspection_date` date NOT NULL COMMENT '计划初验日期',
  `department` int NOT NULL COMMENT '所属部门ID',
  `project_sub_category` int NOT NULL COMMENT '项目小类ID',
  `planned_final_inspection_date` date NOT NULL COMMENT '计划终验日期',
  `region` int NOT NULL COMMENT '所属区域ID',
  `importance_level` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '重要程度',
  `project_start_date` date NOT NULL COMMENT '项目启动日期',
  `planned_completion_date` date NOT NULL COMMENT '计划结项日期',
  `company` int NOT NULL COMMENT '所属公司ID',
  `created_at` date NOT NULL COMMENT '项目创建时间\r\n',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '项目完成状态\r\n',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `project_info_ibfk_1`(`project_stage` ASC) USING BTREE,
  INDEX `project_info_ibfk_2`(`project_category` ASC) USING BTREE,
  INDEX `project_info_ibfk_3`(`department` ASC) USING BTREE,
  INDEX `project_info_ibfk_4`(`project_sub_category` ASC) USING BTREE,
  INDEX `project_info_ibfk_5`(`region` ASC) USING BTREE,
  INDEX `project_info_ibfk_6`(`company` ASC) USING BTREE,
  CONSTRAINT `project_info_ibfk_1` FOREIGN KEY (`project_stage`) REFERENCES `project_stages` (`stage_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project_info_ibfk_2` FOREIGN KEY (`project_category`) REFERENCES `project_categories` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project_info_ibfk_3` FOREIGN KEY (`department`) REFERENCES `departments` (`department_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project_info_ibfk_4` FOREIGN KEY (`project_sub_category`) REFERENCES `project_subcategories` (`subcategory_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project_info_ibfk_5` FOREIGN KEY (`region`) REFERENCES `regions` (`region_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project_info_ibfk_6` FOREIGN KEY (`company`) REFERENCES `companies` (`company_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '项目信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of project_info
-- ----------------------------
INSERT INTO `project_info` VALUES (1, '新产品开发项目', 'NPD', 1, '2024-12-01', 'FIN2024001', 1, '2024-09-01', 5, 2, '2025-01-15', 1, '重要', '2024-07-10', '2025-02-28', 2, '2024-07-10', '在途');
INSERT INTO `project_info` VALUES (2, '市场推广活动', 'MKT', 2, '2024-10-15', 'FIN2024002', 3, '2024-08-01', 2, 4, '2024-11-01', 2, '中等', '2024-07-11', '2024-11-30', 2, '2024-07-10', '已结项');
INSERT INTO `project_info` VALUES (3, '新硬件设计', 'HDW', 1, '2025-03-20', 'FIN2024003', 2, '2024-12-01', 3, 3, '2025-05-01', 3, '不重要', '2024-07-12', '2025-06-15', 3, '2024-07-10', '在途');
INSERT INTO `project_info` VALUES (4, '内部流程优化', 'PRO', 3, '2024-09-10', 'FIN2024004', 4, '2024-08-20', 4, 6, '2024-09-30', 4, '不重要', '2024-07-13', '2024-10-01', 4, '2024-07-10', '已结项');
INSERT INTO `project_info` VALUES (5, '客户关系管理系统', 'CRM', 2, '2024-11-05', 'FIN2024005', 1, '2024-10-01', 1, 1, '2024-11-20', 5, '中等', '2024-07-14', '2024-12-10', 5, '2024-07-10', '在途');
INSERT INTO `project_info` VALUES (6, '国际市场拓展', 'INT', 4, '2025-01-30', 'FIN2024006', 3, '2024-09-25', 6, 5, '2025-03-10', 6, '不重要', '2024-07-15', '2025-04-20', 6, '2024-07-10', '已结项');
INSERT INTO `project_info` VALUES (7, '移动应用升级', 'APP', 1, '2024-12-20', 'FIN2024007', 1, '2024-11-10', 7, 1, '2025-01-01', 7, '中等', '2024-07-16', '2025-02-15', 7, '2024-07-10', '在途');
INSERT INTO `project_info` VALUES (8, '财务系统更新', 'FIN', 2, '2024-09-15', 'FIN2024008', 4, '2024-08-05', 8, 6, '2024-10-01', 8, '不重要', '2024-07-17', '2024-10-20', 8, '2024-07-10', '已结项');
INSERT INTO `project_info` VALUES (9, '北美市场研究', 'NAM', 3, '2024-10-25', 'FIN2024009', 3, '2024-09-05', 9, 4, '2024-11-10', 8, '不重要', '2024-07-18', '2024-12-01', 9, '2024-07-10', '在途');
INSERT INTO `project_info` VALUES (10, '法律合规审查', 'LGL', 4, '2024-12-10', 'FIN2024010', 4, '2024-11-01', 10, 6, '2025-01-05', 9, '中等', '2024-07-19', '2025-02-20', 10, '2024-07-10', '已结项');
INSERT INTO `project_info` VALUES (11, '新产品测试', 'TST', 1, '2025-02-15', 'FIN2024011', 1, '2024-11-25', 1, 2, '2025-03-01', 10, '不重要', '2024-07-20', '2025-04-10', 2, '2024-07-10', '在途');
INSERT INTO `project_info` VALUES (12, '网络安全升级', 'SEC', 2, '2024-11-30', 'FIN2024012', 2, '2024-10-15', 2, 3, '2024-12-20', 4, '不重要', '2024-07-21', '2025-01-05', 3, '2024-07-10', '已结项');
INSERT INTO `project_info` VALUES (13, '品牌重塑', 'BRD', 3, '2025-01-10', 'FIN2024013', 3, '2024-11-05', 3, 4, '2025-02-01', 5, '不重要', '2024-07-22', '2025-03-15', 4, '2024-07-10', '在途');
INSERT INTO `project_info` VALUES (14, '用户体验改进', 'UX', 4, '2024-10-05', 'FIN2024014', 4, '2024-08-25', 4, 6, '2024-11-15', 6, '中等', '2024-07-23', '2024-12-01', 5, '2024-07-10', '在途');
INSERT INTO `project_info` VALUES (15, '产品培训计划', 'TRN', 1, '2024-12-05', 'FIN2024015', 1, '2024-09-20', 5, 2, '2025-01-10', 7, '重要', '2024-07-24', '2025-02-25', 6, '2024-07-10', '已结项');
INSERT INTO `project_info` VALUES (16, '市场营销活动', 'MRK', 2, '2025-01-25', 'FIN2024016', 3, '2024-11-15', 6, 5, '2025-03-05', 8, '重要', '2024-07-25', '2025-04-10', 7, '2024-07-10', '在途');
INSERT INTO `project_info` VALUES (17, '产品发布会', 'LAU', 3, '2024-09-20', 'FIN2024017', 4, '2024-08-10', 7, 6, '2024-10-05', 9, '重要', '2024-07-26', '2024-11-01', 8, '2024-07-10', '在途');
INSERT INTO `project_info` VALUES (18, '内部审计', 'AUD', 4, '2024-12-15', 'FIN2024018', 1, '2024-10-05', 8, 1, '2025-01-20', 10, '中等', '2024-07-27', '2025-02-28', 9, '2024-07-10', '已结项');
INSERT INTO `project_info` VALUES (19, '客户满意度调查', 'CST', 1, '2025-02-10', 'FIN2024019', 3, '2024-12-20', 9, 4, '2025-03-15', 2, '重要', '2024-07-28', '2025-04-05', 10, '2024-07-10', '在途');
INSERT INTO `project_info` VALUES (20, '新业务拓展', 'BIZ', 2, '2024-11-10', 'FIN2024020', 2, '2024-09-30', 1, 3, '2024-12-25', 3, '重要', '2024-07-29', '2025-01-15', 2, '2024-07-10', '在途');
INSERT INTO `project_info` VALUES (21, '培训课程开发', 'TRC', 3, '2025-03-05', 'FIN2024021', 4, '2024-12-10', 2, 6, '2025-04-20', 4, '不重要', '2024-07-30', '2025-05-30', 3, '2024-07-10', '已结项');
INSERT INTO `project_info` VALUES (22, '新技术研究', 'RCH', 4, '2024-10-30', 'FIN2024022', 1, '2024-09-10', 3, 2, '2024-11-25', 5, '中等', '2024-07-31', '2024-12-20', 4, '2024-07-10', '已结项');
INSERT INTO `project_info` VALUES (23, '客户服务改进', 'CST', 1, '2025-01-15', 'FIN2024023', 3, '2024-10-20', 4, 5, '2025-02-10', 6, '不重要', '2024-08-01', '2025-03-30', 5, '2024-07-10', '在途');
INSERT INTO `project_info` VALUES (24, '在线平台优化', 'OPT', 2, '2024-12-25', 'FIN2024024', 2, '2024-11-10', 5, 3, '2025-02-01', 7, '中等', '2024-08-02', '2025-03-10', 6, '2024-07-10', '在途');
INSERT INTO `project_info` VALUES (25, '新市场调研', 'RSM', 3, '2025-02-05', 'FIN2024025', 3, '2024-12-01', 6, 4, '2025-03-20', 8, '中等', '2024-08-03', '2025-04-15', 7, '2024-07-10', '在途');
INSERT INTO `project_info` VALUES (26, '员工福利升级', 'WEL', 4, '2024-11-20', 'FIN2024026', 4, '2024-09-15', 7, 6, '2025-01-25', 9, '重要', '2024-08-04', '2025-03-05', 8, '2024-07-10', '在途');
INSERT INTO `project_info` VALUES (27, '生产线自动化', 'AUT', 1, '2025-03-25', 'FIN2024027', 1, '2024-10-25', 8, 2, '2025-05-10', 10, '中等', '2024-08-05', '2025-06-20', 9, '2024-07-10', '已结项');
INSERT INTO `project_info` VALUES (28, '社交媒体策略', 'SOC', 2, '2024-10-15', 'FIN2024028', 2, '2024-08-20', 9, 3, '2024-11-05', 2, '不重要', '2024-08-06', '2024-12-15', 10, '2024-07-10', '在途');
INSERT INTO `project_info` VALUES (29, '国际合作项目', 'INT', 3, '2025-01-20', 'FIN2024029', 3, '2024-11-25', 1, 4, '2025-03-01', 3, '重要', '2024-08-07', '2025-04-10', 2, '2024-07-10', '在途');
INSERT INTO `project_info` VALUES (30, '新产品发布', 'NPD', 4, '2024-12-15', 'FIN2024030', 4, '2024-09-30', 2, 6, '2025-01-20', 4, '中等', '2024-08-08', '2025-03-05', 3, '2024-07-10', '在途');
INSERT INTO `project_info` VALUES (31, '员工培训计划', 'EMP', 1, '2025-02-25', 'FIN2024031', 1, '2024-11-10', 3, 2, '2025-04-10', 5, '中等', '2024-08-09', '2025-05-20', 4, '2024-07-10', '已结项');
INSERT INTO `project_info` VALUES (32, '振兴工大', 'RHFUT', 1, '2024-07-03', 'ABC123', 1, '2024-07-09', 2, 1, '2024-07-11', 2, '重要', '2024-07-01', '2024-07-12', 4, '2024-07-10', '已结项');
INSERT INTO `project_info` VALUES (34, 'dwafa', 'adw', 2, '2024-07-14', 'dawd', 1, '2024-07-14', 2, 1, '2024-07-15', 2, '重要', '2024-07-13', '2024-07-16', 3, '2024-07-12', '在途');

-- ----------------------------
-- Table structure for project_members
-- ----------------------------
DROP TABLE IF EXISTS `project_members`;
CREATE TABLE `project_members`  (
  `member_id` int NOT NULL AUTO_INCREMENT,
  `project_id` int NOT NULL,
  `user_id` int NOT NULL,
  `role` enum('MANAGER','EMPLOYEE') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `joined_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `permissions` json NULL,
  PRIMARY KEY (`member_id`) USING BTREE,
  UNIQUE INDEX `unique_project_user`(`project_id` ASC, `user_id` ASC) USING BTREE,
  INDEX `project_members_ibfk_2`(`user_id` ASC) USING BTREE,
  CONSTRAINT `project_members_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `project_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project_members_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 104 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '项目成员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of project_members
-- ----------------------------
INSERT INTO `project_members` VALUES (19, 1, 240718, 'MANAGER', '2024-07-10 18:20:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (20, 1, 240720, 'EMPLOYEE', '2024-07-10 18:21:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (21, 1, 240721, 'EMPLOYEE', '2024-07-10 18:22:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (22, 2, 240722, 'MANAGER', '2024-07-10 18:23:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (23, 2, 240723, 'EMPLOYEE', '2024-07-10 18:24:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (24, 2, 240718, 'EMPLOYEE', '2024-07-10 18:25:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (25, 3, 240720, 'MANAGER', '2024-07-10 18:26:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (26, 3, 240721, 'EMPLOYEE', '2024-07-10 18:27:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (27, 3, 240722, 'EMPLOYEE', '2024-07-10 18:28:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (28, 4, 240721, 'MANAGER', '2024-07-10 18:29:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (29, 4, 240722, 'EMPLOYEE', '2024-07-10 18:30:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (30, 4, 240723, 'EMPLOYEE', '2024-07-10 18:31:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (31, 5, 240718, 'MANAGER', '2024-07-10 18:32:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (32, 5, 240720, 'EMPLOYEE', '2024-07-10 18:33:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (33, 5, 240721, 'EMPLOYEE', '2024-07-10 18:34:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (34, 6, 240722, 'MANAGER', '2024-07-10 18:35:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (35, 6, 240723, 'EMPLOYEE', '2024-07-10 18:36:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (36, 6, 240718, 'EMPLOYEE', '2024-07-10 18:37:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (37, 7, 240720, 'MANAGER', '2024-07-10 18:38:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (38, 7, 240721, 'EMPLOYEE', '2024-07-10 18:39:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (39, 8, 240722, 'MANAGER', '2024-07-10 18:40:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (40, 8, 240723, 'EMPLOYEE', '2024-07-10 18:41:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (41, 8, 240718, 'EMPLOYEE', '2024-07-10 18:42:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (42, 8, 240720, 'EMPLOYEE', '2024-07-10 18:43:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (43, 9, 240721, 'MANAGER', '2024-07-10 18:44:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (44, 9, 240722, 'EMPLOYEE', '2024-07-10 18:45:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (45, 10, 240723, 'MANAGER', '2024-07-10 18:46:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (46, 10, 240718, 'EMPLOYEE', '2024-07-10 18:47:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (47, 10, 240720, 'EMPLOYEE', '2024-07-10 18:48:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (48, 10, 240721, 'EMPLOYEE', '2024-07-10 18:49:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (49, 11, 240722, 'MANAGER', '2024-07-10 18:50:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (50, 11, 240723, 'EMPLOYEE', '2024-07-10 18:51:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (51, 11, 240718, 'EMPLOYEE', '2024-07-10 18:52:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (52, 12, 240720, 'MANAGER', '2024-07-10 18:53:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (53, 12, 240721, 'EMPLOYEE', '2024-07-10 18:54:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (54, 13, 240722, 'MANAGER', '2024-07-10 18:55:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (55, 13, 240723, 'EMPLOYEE', '2024-07-10 18:56:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (56, 14, 240718, 'MANAGER', '2024-07-10 18:57:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (57, 14, 240720, 'EMPLOYEE', '2024-07-10 18:58:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (58, 14, 240721, 'EMPLOYEE', '2024-07-10 18:59:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (59, 15, 240722, 'MANAGER', '2024-07-10 19:00:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (60, 15, 240723, 'EMPLOYEE', '2024-07-10 19:01:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (61, 16, 240718, 'MANAGER', '2024-07-10 19:02:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (62, 16, 240720, 'EMPLOYEE', '2024-07-10 19:03:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (63, 16, 240721, 'EMPLOYEE', '2024-07-10 19:04:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (64, 16, 240722, 'EMPLOYEE', '2024-07-10 19:05:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (65, 17, 240723, 'MANAGER', '2024-07-10 19:06:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (66, 17, 240718, 'EMPLOYEE', '2024-07-10 19:07:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (67, 18, 240720, 'MANAGER', '2024-07-10 19:08:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (68, 18, 240721, 'EMPLOYEE', '2024-07-10 19:09:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (69, 19, 240722, 'MANAGER', '2024-07-10 19:10:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (70, 19, 240723, 'EMPLOYEE', '2024-07-10 19:11:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (71, 20, 240718, 'MANAGER', '2024-07-10 19:12:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (72, 20, 240720, 'EMPLOYEE', '2024-07-10 19:13:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (73, 20, 240721, 'EMPLOYEE', '2024-07-10 19:14:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (74, 20, 240722, 'EMPLOYEE', '2024-07-10 19:15:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (75, 21, 240723, 'MANAGER', '2024-07-10 19:16:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (76, 21, 240718, 'EMPLOYEE', '2024-07-10 19:17:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (77, 22, 240720, 'MANAGER', '2024-07-10 19:18:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (78, 22, 240721, 'EMPLOYEE', '2024-07-10 19:19:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (79, 23, 240722, 'MANAGER', '2024-07-10 19:20:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (80, 23, 240723, 'EMPLOYEE', '2024-07-10 19:21:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (81, 24, 240718, 'MANAGER', '2024-07-10 19:22:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (82, 24, 240720, 'EMPLOYEE', '2024-07-10 19:23:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (83, 24, 240721, 'EMPLOYEE', '2024-07-10 19:24:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (84, 24, 240722, 'EMPLOYEE', '2024-07-10 19:25:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (85, 25, 240723, 'MANAGER', '2024-07-10 19:26:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (86, 25, 240718, 'EMPLOYEE', '2024-07-10 19:27:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (87, 26, 240720, 'MANAGER', '2024-07-10 19:28:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (88, 26, 240721, 'EMPLOYEE', '2024-07-10 19:29:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (89, 27, 240722, 'MANAGER', '2024-07-10 19:30:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (90, 27, 240723, 'EMPLOYEE', '2024-07-10 19:31:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (91, 28, 240718, 'MANAGER', '2024-07-10 19:32:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (92, 28, 240720, 'EMPLOYEE', '2024-07-10 19:33:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (93, 28, 240721, 'EMPLOYEE', '2024-07-10 19:34:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (94, 28, 240722, 'EMPLOYEE', '2024-07-10 19:35:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (95, 29, 240723, 'MANAGER', '2024-07-10 19:36:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (96, 29, 240718, 'EMPLOYEE', '2024-07-10 19:37:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (97, 30, 240720, 'MANAGER', '2024-07-10 19:38:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (98, 30, 240721, 'EMPLOYEE', '2024-07-10 19:39:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (99, 31, 240722, 'MANAGER', '2024-07-10 19:40:00', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (100, 31, 240723, 'EMPLOYEE', '2024-07-10 19:41:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (101, 31, 240718, 'EMPLOYEE', '2024-07-10 19:42:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (102, 31, 240720, 'EMPLOYEE', '2024-07-10 19:43:00', '{\"add\": false, \"view\": true, \"delete\": false}');
INSERT INTO `project_members` VALUES (103, 32, 240718, 'MANAGER', '2024-07-10 21:19:13', '{\"add\": true, \"view\": true, \"delete\": true}');
INSERT INTO `project_members` VALUES (105, 34, 240718, 'MANAGER', '2024-07-12 09:51:47', '{\"add\": true, \"view\": true, \"delete\": true}');

-- ----------------------------
-- Table structure for project_stages
-- ----------------------------
DROP TABLE IF EXISTS `project_stages`;
CREATE TABLE `project_stages`  (
  `stage_id` int NOT NULL AUTO_INCREMENT,
  `stage_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`stage_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '项目阶段表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of project_stages
-- ----------------------------
INSERT INTO `project_stages` VALUES (1, '启动阶段');
INSERT INTO `project_stages` VALUES (2, '规划阶段');
INSERT INTO `project_stages` VALUES (3, '执行阶段');
INSERT INTO `project_stages` VALUES (4, '收尾阶段');

-- ----------------------------
-- Table structure for project_subcategories
-- ----------------------------
DROP TABLE IF EXISTS `project_subcategories`;
CREATE TABLE `project_subcategories`  (
  `subcategory_id` int NOT NULL AUTO_INCREMENT,
  `subcategory_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `category_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`subcategory_id`) USING BTREE,
  INDEX `project_subcategories_ibfk_1`(`category_id` ASC) USING BTREE,
  CONSTRAINT `project_subcategories_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `project_categories` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '项目小类表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of project_subcategories
-- ----------------------------
INSERT INTO `project_subcategories` VALUES (1, '移动应用开发', 1);
INSERT INTO `project_subcategories` VALUES (2, '网站开发', 1);
INSERT INTO `project_subcategories` VALUES (3, '硬件设计', 2);
INSERT INTO `project_subcategories` VALUES (4, '市场调研', 3);
INSERT INTO `project_subcategories` VALUES (5, '广告投放', 3);
INSERT INTO `project_subcategories` VALUES (6, '业务流程优化', 4);

-- ----------------------------
-- Table structure for regions
-- ----------------------------
DROP TABLE IF EXISTS `regions`;
CREATE TABLE `regions`  (
  `region_id` int NOT NULL AUTO_INCREMENT,
  `region_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`region_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '所属区域表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of regions
-- ----------------------------
INSERT INTO `regions` VALUES (1, '华北');
INSERT INTO `regions` VALUES (2, '华东');
INSERT INTO `regions` VALUES (3, '华南');
INSERT INTO `regions` VALUES (4, '西南');
INSERT INTO `regions` VALUES (5, '东北');
INSERT INTO `regions` VALUES (6, '华中');
INSERT INTO `regions` VALUES (7, '西北');
INSERT INTO `regions` VALUES (8, '北美');
INSERT INTO `regions` VALUES (9, '欧洲');
INSERT INTO `regions` VALUES (10, '亚洲其他地区');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '主键，自动递增，唯一标识用户',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户的登录名，必须唯一且不能为空',
  `phone_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户的手机号码，必须唯一且不能为空',
  `password_hash` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码的哈希值，不能为空',
  `full_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户的全名，可以为空',
  `role` enum('admin','user') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户的角色，不能为空',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像图片的URL地址，可以为空',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录用户创建时间，默认为当前时间戳',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录用户最后一次更新时间，默认为当前时间戳，并在更新记录时自动更新',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE,
  UNIQUE INDEX `phone_number`(`phone_number` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 240724 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (240718, 'LeaderOne', '13514995984', '{bcrypt}$2a$10$NC4GG6iyrYL.hmR4UMOVYOOztJT3SmBrGTbz6U1JQq0VvY/VWA.0W', '徐力行', 'user', '887f2448-4887-4143-9713-a066db059cd7.png', '2024-07-10 16:40:26', '2024-07-12 09:38:08');
INSERT INTO `users` VALUES (240720, '大熊维尼', '15531105836', '{bcrypt}$2a$10$Qcpx1M6vRh327S3biy560uuK0yM98v/vb3SHeYV.0.tI314hpOqAe', '李健', 'user', '', '2024-07-10 17:59:09', '2024-07-10 17:59:09');
INSERT INTO `users` VALUES (240721, '方块头海绵', '13514995985', '{bcrypt}$2a$10$dRQaPJWm0NEYjPDue.tJPOJY4WN5TbKQJ4r4DfBOXtkIcMYp9jK4C', '徐磊', 'user', 'c8cf94a8-a152-4545-ad67-11664ee2bcfc.png', '2024-07-10 18:00:13', '2024-07-11 21:27:46');
INSERT INTO `users` VALUES (240722, 'LM10', '15831131886', '{bcrypt}$2a$10$HG8Yfv6X/T.wvcf6tMER1eqHYFa9GMRj8//34niv/Om9NPbLN23i2', '张泽昊', 'user', '', '2024-07-10 18:01:14', '2024-07-10 18:01:14');
INSERT INTO `users` VALUES (240723, '星之卡比', '18318979086', '{bcrypt}$2a$10$pX8b9ZAQI.wrteYs2DbySuhmGThdhsnWjqG9g6PsvcGbR7MxkNnD.', '陈远飞', 'user', '', '2024-07-10 18:02:33', '2024-07-10 18:02:33');

-- ----------------------------
-- View structure for project_list_view
-- ----------------------------
DROP VIEW IF EXISTS `project_list_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `project_list_view` AS select `pi`.`id` AS `project_id`,`pi`.`project_name` AS `project_name`,`pc`.`category_name` AS `project_category_name`,`ps`.`subcategory_name` AS `project_subcategory_name`,`pi`.`importance_level` AS `importance_level`,coalesce((select sum((case `b`.`currency` when 'CNY' then `b`.`amount` when 'USD' then (`b`.`amount` * 7.2) when 'EUR' then (`b`.`amount` * 7.8) when 'JPY' then (`b`.`amount` * 0.05) when 'GBP' then (`b`.`amount` * 9.3) else `b`.`amount` end)) from `budgets` `b` where (`b`.`project_id` = `pi`.`id`)),0) AS `total_budget`,(case when (count(`p`.`plan_id`) = 0) then 0 else ((sum((case when (`p`.`status` = 'completed') then 1 else 0 end)) * 100.0) / count(`p`.`plan_id`)) end) AS `project_progress`,`pi`.`created_at` AS `project_creation_time`,`pi`.`status` AS `project_status` from (((`project_info` `pi` left join `project_categories` `pc` on((`pi`.`project_category` = `pc`.`category_id`))) left join `project_subcategories` `ps` on((`pi`.`project_sub_category` = `ps`.`subcategory_id`))) left join `plans` `p` on((`pi`.`id` = `p`.`project_id`))) group by `pi`.`id`,`pi`.`project_name`,`pc`.`category_name`,`ps`.`subcategory_name`,`pi`.`importance_level`,`pi`.`created_at`,`pi`.`status`;

-- ----------------------------
-- Triggers structure for table plans
-- ----------------------------
DROP TRIGGER IF EXISTS `before_insert_update_plans`;
delimiter ;;
CREATE TRIGGER `before_insert_update_plans` BEFORE INSERT ON `plans` FOR EACH ROW BEGIN
    DECLARE completion_date DATE;
    IF NEW.predecessor_plan_id IS NOT NULL THEN
        SELECT completion_date INTO completion_date
        FROM plans
        WHERE plan_id = NEW.predecessor_plan_id;
        IF NEW.start_date < completion_date THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Start date cannot be earlier than predecessor plan completion date';
        END IF;
    END IF;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
