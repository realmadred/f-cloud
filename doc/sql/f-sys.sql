-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: f-sys
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sys_dict`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dict` (
  `id` bigint NOT NULL COMMENT '主键',
  `code` varchar(31) NOT NULL DEFAULT '' COMMENT '编号',
  `name` varchar(31) NOT NULL DEFAULT '' COMMENT '名称',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 （0 未删除 , 1 删除）',
  `create_id` bigint DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_name` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人姓名',
  `update_id` bigint DEFAULT '0' COMMENT '修改人id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_name` varchar(31) DEFAULT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_code` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数据字典';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict`
--

LOCK TABLES `sys_dict` WRITE;
/*!40000 ALTER TABLE `sys_dict` DISABLE KEYS */;
INSERT INTO `sys_dict` VALUES (1,'payMethod','支付方式',0,0,NULL,NULL,0,NULL,NULL),(2,'invoiceType','发票类型',0,0,NULL,NULL,0,NULL,NULL),(3,'invoiceHeaderType','发票抬头类型',0,0,NULL,NULL,0,NULL,NULL),(4,'orderStatus','订单状态',0,0,NULL,NULL,0,NULL,NULL),(5,'in_out','收入支出',0,0,NULL,NULL,0,NULL,NULL),(8550957391874,'yes_no','是否',0,0,'2020-05-20 10:10:05',NULL,0,'2020-05-20 10:10:05',NULL),(8991911358465,'enable_status','启用状态',0,0,'2020-05-20 12:11:03',NULL,0,'2020-05-20 12:11:03',NULL),(14671686754847,'test','测试数据1',0,1,'2022-01-18 18:59:15','admin',1,'2022-02-11 09:35:24','admin'),(83761268785153,'job_status','在职状态',0,0,'2020-05-20 13:49:28',NULL,0,'2020-05-20 13:49:28',NULL),(84010456580097,'user_status','账号状态',0,0,'2020-05-20 13:50:28',NULL,0,'2020-05-20 13:50:28',NULL),(84261347262465,'sex','性别',0,0,'2020-05-20 13:51:28',NULL,0,'2020-05-20 13:51:28',NULL),(84652092817409,'menu_type','菜单类型',0,0,'2020-05-20 13:53:01',NULL,1,'2022-01-18 18:58:23','admin'),(84806338347009,'is_delete','是否删除',0,0,'2020-05-20 13:53:38',NULL,0,'2020-05-20 13:53:38',NULL),(85139311558658,'log_type','日志类型',0,0,'2020-05-20 13:54:57',NULL,0,'2020-05-20 13:54:57',NULL);
/*!40000 ALTER TABLE `sys_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_detail`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dict_detail` (
  `id` bigint NOT NULL COMMENT '主键',
  `dict_id` bigint NOT NULL DEFAULT '0' COMMENT '字典id',
  `dict_code` varchar(31) NOT NULL DEFAULT '' COMMENT '编号',
  `name` varchar(31) NOT NULL DEFAULT '' COMMENT '名称',
  `value` varchar(31) NOT NULL DEFAULT '' COMMENT '字典值',
  `sort` smallint NOT NULL DEFAULT '1' COMMENT '排序',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 （0 未删除 , 1 删除）',
  `create_id` bigint DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_name` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人姓名',
  `update_id` bigint DEFAULT '0' COMMENT '修改人id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_name` varchar(31) DEFAULT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`),
  KEY `idx_code` (`dict_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数据字典详情';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_detail`
--

LOCK TABLES `sys_dict_detail` WRITE;
/*!40000 ALTER TABLE `sys_dict_detail` DISABLE KEYS */;
INSERT INTO `sys_dict_detail` VALUES (1,1,'payMethod','微信支付','1',1,0,0,NULL,NULL,0,NULL,NULL),(2,1,'payMethod','支付宝支付','2',2,0,0,NULL,NULL,0,NULL,NULL),(3,1,'payMethod','银行卡支付','3',3,0,0,NULL,NULL,0,NULL,NULL),(4,2,'invoiceType','电子普通发票','1',1,0,0,NULL,NULL,0,NULL,NULL),(5,2,'invoiceType','增值税专用发票','2',2,0,0,NULL,NULL,0,NULL,NULL),(6,3,'invoiceHeaderType','企业','2',2,0,0,NULL,NULL,0,NULL,NULL),(7,4,'orderStatus','未支付','0',1,0,0,NULL,NULL,0,NULL,NULL),(8,4,'orderStatus','支付定金','1',2,0,0,NULL,NULL,0,NULL,NULL),(9,4,'orderStatus','已量房','2',3,0,0,NULL,NULL,0,NULL,NULL),(10,4,'orderStatus','设计中','3',4,0,0,NULL,NULL,0,NULL,NULL),(11,4,'orderStatus','已复尺','4',5,0,0,NULL,NULL,0,NULL,NULL),(12,4,'orderStatus','设计确认','5',6,0,0,NULL,NULL,0,NULL,NULL),(13,4,'orderStatus','下单','6',7,0,0,NULL,NULL,0,NULL,NULL),(14,4,'orderStatus','安装','7',8,0,0,NULL,NULL,0,NULL,NULL),(15,4,'orderStatus','收尾款','8',9,0,0,NULL,NULL,0,NULL,NULL),(16,4,'orderStatus','完成','9',10,0,0,NULL,NULL,0,NULL,NULL),(17,5,'in_out','支出','1',1,0,0,NULL,NULL,0,NULL,NULL),(18,5,'in_out','收入','2',2,0,0,NULL,NULL,0,NULL,NULL),(19,6,'yes_no','是','1',1,0,0,NULL,NULL,0,NULL,NULL),(20,6,'yes_no','否','0',2,0,0,NULL,NULL,0,NULL,NULL),(25,3,'invoiceHeaderType','个人','1',1,0,0,NULL,NULL,0,NULL,NULL),(14671474229775,84652092817409,'menu_type','菜单','1',1,0,1,'2022-01-18 18:58:23','admin',1,'2022-01-18 18:58:23','admin'),(14671474229776,84652092817409,'menu_type','按钮','2',2,0,1,'2022-01-18 18:58:23','admin',1,'2022-01-18 18:58:23','admin'),(23026583200854,14671686754847,'test','名称1','1',1,0,1,'2022-02-11 09:35:24','admin',1,'2022-02-11 09:35:24','admin'),(23026583200855,14671686754847,'test','名称3','3',3,0,1,'2022-02-11 09:35:24','admin',1,'2022-02-11 09:35:24','admin'),(23026583200856,14671686754847,'test','名称4','4',4,0,1,'2022-02-11 09:35:24','admin',1,'2022-02-11 09:35:24','admin'),(23026583200857,14671686754847,'test','名称5','5',5,0,1,'2022-02-11 09:35:24','admin',1,'2022-02-11 09:35:24','admin'),(989881920180226,8550957391874,'yes_no','是','1',1,0,0,'2020-05-20 14:13:48',NULL,0,'2020-05-20 14:13:48',NULL),(990215283462146,8550957391874,'yes_no','否','0',2,0,0,'2020-05-20 14:15:07',NULL,0,'2020-05-20 14:15:07',NULL),(991329567428609,8991911358465,'enable_status','启用','1',1,0,0,'2020-05-20 14:19:33',NULL,0,'2020-05-20 14:19:33',NULL),(991370004713474,8991911358465,'enable_status','停用','0',2,0,0,'2020-05-20 14:19:43',NULL,0,'2020-05-20 14:19:43',NULL),(991683101118466,83761268785153,'job_status','在职','1',1,0,0,'2020-05-20 14:20:57',NULL,0,'2020-05-20 14:20:57',NULL),(991772292993026,83761268785153,'job_status','离职','2',2,0,0,'2020-05-20 14:21:18',NULL,0,'2020-05-20 14:21:18',NULL),(991981173526530,84010456580097,'user_status','正常','1',1,0,0,'2020-05-20 14:22:08',NULL,0,'2020-05-20 14:22:08',NULL),(992074463236097,84010456580097,'user_status','冻结','2',2,0,0,'2020-05-20 14:22:30',NULL,0,'2020-05-20 14:22:30',NULL),(992119887548418,84010456580097,'user_status','禁用','3',3,0,0,'2020-05-20 14:22:41',NULL,0,'2020-05-20 14:22:41',NULL),(992263001395201,84261347262465,'sex','女','0',1,0,0,'2020-05-20 14:23:15',NULL,0,'2020-05-20 14:23:15',NULL),(992296396443649,84261347262465,'sex','男','1',2,0,0,'2020-05-20 14:23:23',NULL,0,'2020-05-20 14:23:23',NULL),(992362708389889,84261347262465,'sex','未设置','2',3,0,0,'2020-05-20 14:23:39',NULL,0,'2020-05-20 14:23:39',NULL),(992660483002369,84652092817409,'menu_type','菜单','2',2,1,0,'2020-05-20 14:24:50',NULL,0,'2020-05-20 14:24:50',NULL),(992732381761538,84652092817409,'menu_type','按钮','3',3,1,0,'2020-05-20 14:25:07',NULL,0,'2020-05-20 14:25:07',NULL),(992922568282113,84806338347009,'is_delete','未删除','0',1,0,0,'2020-05-20 14:25:53',NULL,0,'2020-05-20 14:25:53',NULL),(992954507907073,84806338347009,'is_delete','已删除','1',2,0,0,'2020-05-20 14:26:00',NULL,0,'2020-05-20 14:26:00',NULL),(993486718947329,85139311558658,'log_type','普通','1',1,0,0,'2020-05-20 14:28:07',NULL,0,'2020-05-20 14:28:07',NULL),(993518738264066,85139311558658,'log_type','错误','2',2,0,0,'2020-05-20 14:28:15',NULL,0,'2020-05-20 14:28:15',NULL);
/*!40000 ALTER TABLE `sys_dict_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_log`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_log` (
  `id` bigint NOT NULL COMMENT '主键',
  `type` tinyint NOT NULL DEFAULT '0' COMMENT '日志类型(1 普通 2 错误)',
  `class_name` varchar(127) NOT NULL COMMENT '类名',
  `method_name` varchar(127) NOT NULL COMMENT '方法名',
  `request_uri` varchar(127) NOT NULL COMMENT '请求路径',
  `params` varchar(1023) NOT NULL COMMENT '参数',
  `request_ip` varchar(15) NOT NULL COMMENT '请求ip',
  `create_name` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人姓名',
  `create_id` bigint DEFAULT '0' COMMENT '创建人id',
  `error_detail` varchar(1023) DEFAULT NULL COMMENT '错误信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log`
--

LOCK TABLES `sys_log` WRITE;
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
INSERT INTO `sys_log` VALUES (54683124404674,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:12'),(54683135697348,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:15'),(54683138302406,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:16'),(54683139068360,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:16'),(54683139834314,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:16'),(54683140473292,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:16'),(54683141173710,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:16'),(54683167150544,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:23'),(54683168043474,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:23'),(54683168809428,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:23'),(54683169575382,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:23'),(54683170271704,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:23'),(54683170980314,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:24'),(54683171676636,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:24'),(54683173474782,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:24'),(54683174314464,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:24'),(54683175342562,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:25'),(54683176047076,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:25'),(54683176763878,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:25'),(54683177652712,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:25'),(54683178545642,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:25'),(54683179377132,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:26'),(54683186794990,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:27'),(54683187618288,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:28'),(54683188310514,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:28'),(54683189015028,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:28'),(54683189776886,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:28'),(54683190477304,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:28'),(54683191951866,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:29'),(54683192717820,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:29'),(54683194880510,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:29'),(54683279561153,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-11 20:26:50'),(54684640772548,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-11 20:32:22'),(54685232591302,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:34:47'),(54685287010760,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-11 20:35:00'),(54685735317963,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-11 20:36:50'),(54687517893070,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-11 20:44:05'),(54687837573571,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-11 20:45:23'),(54688811442630,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-11 20:49:21'),(54689238106569,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-11 20:51:05'),(54689578766796,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-11 20:52:28'),(54692275859921,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'java.io.IOException: 你的主机中的软件中止了一个已建立的连接。','2022-05-11 21:03:26'),(54693709980099,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-11 21:09:17'),(54693718565318,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-11 21:09:19'),(54693876982217,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-11 21:09:57'),(54695517438412,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-11 21:16:38'),(54695558193615,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-11 21:16:48'),(54695841751506,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-11 21:17:57'),(54696449651157,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-11 21:20:25'),(54700515975640,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'\r\n### Error updating database.  Cause: java.sql.SQLException: Failed to report branch status false\r\n### The error may exist in com/f/mapper/SysLogMapper.java (best guess)\r\n### The error may involve com.f.mapper.SysLogMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO sys_log  ( id,    type, class_name, method_name, request_uri, params, request_ip )  VALUES  ( ?,    ?, ?, ?, ?, ?, ? )\r\n### Cause: java.sql.SQLException: Failed to report branch status false\n; uncategorized SQLException; SQL state [null]; error code [0]; Failed to report branch status false; nested exception is java.sql.SQLException: Failed to report branch status false','2022-05-11 21:36:58'),(54700645310939,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'Handler dispatch failed; nested exception is java.lang.AssertionError: com.f.exception.ServiceException: test Fallback','2022-05-11 21:37:30'),(55015420522946,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-12 18:58:19'),(55015428362692,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-12 18:58:21'),(55015617458631,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'\r\n### Error updating database.  Cause: java.sql.SQLException: java.lang.NoClassDefFoundError: de/javakaffee/kryoserializers/JdkProxySerializer\r\n### The error may exist in com/f/mapper/SysLogMapper.java (best guess)\r\n### The error may involve com.f.mapper.SysLogMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO sys_log  ( id,    type, class_name, method_name, request_uri, params, request_ip )  VALUES  ( ?,    ?, ?, ?, ?, ?, ? )\r\n### Cause: java.sql.SQLException: java.lang.NoClassDefFoundError: de/javakaffee/kryoserializers/JdkProxySerializer\n; uncategorized SQLException; SQL state [null]; error code [0]; java.lang.NoClassDefFoundError: de/javakaffee/kryoserializers/JdkProxySerializer; nested exception is java.sql.SQLException: java.lang.NoClassDefFoundError: de/javakaffee/kryoserializers/JdkProxySerializer','2022-05-12 18:59:07'),(55019393262019,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 19:14:29'),(55019901592006,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 19:16:33'),(55020556194249,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 19:19:13'),(55020737577411,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 19:19:57'),(55020979069382,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 19:20:56'),(55022170370505,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 19:25:47'),(55047427936716,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'Handler dispatch failed; nested exception is java.lang.AssertionError: com.f.exception.ServiceException: test Fallback','2022-05-12 21:08:33'),(55048328139215,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:12:13'),(55049325695442,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:16:17'),(55049823678933,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:18:18'),(55049838277080,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:18:22'),(55050046685659,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:19:13'),(55050093818334,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:19:24'),(55055680229827,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:42:08'),(55055819211206,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:42:42'),(55055823786441,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:42:43'),(55055827653068,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:42:44'),(55055832187343,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:42:45'),(55055846384082,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:42:49'),(55055850656213,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:42:50'),(55055854809560,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:42:51'),(55056015004123,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:43:30'),(55056085610974,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:43:47'),(55056088793569,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:43:48'),(55056092287460,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:43:49'),(55056095920615,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:43:50'),(55056099398122,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:43:51'),(55056102502893,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:43:51'),(55056105390576,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:43:52'),(55056109060595,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:43:53'),(55056114524662,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:43:54'),(55056117645817,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:43:55'),(55056269238780,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'Handler dispatch failed; nested exception is java.lang.AssertionError: com.f.exception.ServiceException: test Fallback','2022-05-12 21:44:32'),(55056306156031,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'Handler dispatch failed; nested exception is java.lang.AssertionError: com.f.exception.ServiceException: test Fallback','2022-05-12 21:44:41'),(55056351646146,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'Handler dispatch failed; nested exception is java.lang.AssertionError: com.f.exception.ServiceException: test Fallback','2022-05-12 21:44:52'),(55056398741957,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'Handler dispatch failed; nested exception is java.lang.AssertionError: com.f.exception.ServiceException: test Fallback','2022-05-12 21:45:04'),(55056431956424,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'Handler dispatch failed; nested exception is java.lang.AssertionError: com.f.exception.ServiceException: test Fallback','2022-05-12 21:45:12'),(55056523047371,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'Handler dispatch failed; nested exception is java.lang.AssertionError: com.f.exception.ServiceException: test Fallback','2022-05-12 21:45:34'),(55058866393539,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'client fail','2022-05-12 21:55:06'),(55058993910214,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'client fail','2022-05-12 21:55:37'),(55058997027273,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'client fail','2022-05-12 21:55:38'),(55058999468492,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'client fail','2022-05-12 21:55:39'),(55059000492495,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'client fail','2022-05-12 21:55:39'),(55059001442770,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'client fail','2022-05-12 21:55:39'),(55059002651093,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'client fail','2022-05-12 21:55:39'),(55059003589080,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'client fail','2022-05-12 21:55:40'),(55059004096987,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'client fail','2022-05-12 21:55:40'),(55059006140894,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'client fail','2022-05-12 21:55:40'),(55059008369121,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'client fail','2022-05-12 21:55:41'),(55059009200612,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'client fail','2022-05-12 21:55:41'),(55059009970663,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'client fail','2022-05-12 21:55:41'),(55059010720234,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'client fail','2022-05-12 21:55:41'),(55059013083629,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'client fail','2022-05-12 21:55:42'),(55059013902832,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'client fail','2022-05-12 21:55:42'),(55059014726131,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'client fail','2022-05-12 21:55:42'),(55059016954358,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'client fail','2022-05-12 21:55:43'),(55059017781753,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'client fail','2022-05-12 21:55:43'),(55059018543612,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'client fail','2022-05-12 21:55:43'),(55059019121151,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'client fail','2022-05-12 21:55:43'),(55059021574594,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'client fail','2022-05-12 21:55:44'),(55059022520773,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'client fail','2022-05-12 21:55:44'),(55059347198408,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'client fail','2022-05-12 21:57:03'),(55059420013003,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'client fail','2022-05-12 21:57:21'),(55059869327822,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:59:11'),(55059872657873,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:59:12'),(55059875574228,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:59:12'),(55059878244823,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:59:13'),(55059880550874,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:59:14'),(55059881574877,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:59:14'),(55059882267104,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:59:14'),(55059883094499,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:59:14'),(55059885101542,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:59:15'),(55059885928937,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:59:15'),(55059886707180,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:59:15'),(55059888992751,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:59:16'),(55059889758706,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:59:16'),(55059890647541,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:59:16'),(55059892617720,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:59:17'),(55059893387771,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:59:17'),(55059894088190,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-12 21:59:17'),(55060321534401,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'/ by zero','2022-05-12 22:01:01'),(55060326887876,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'/ by zero','2022-05-12 22:01:03'),(55060330086855,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'/ by zero','2022-05-12 22:01:03'),(55060454076874,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'/ by zero','2022-05-12 22:01:34'),(56357628551618,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-16 13:59:47'),(56357635723716,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-16 13:59:48'),(56357639578054,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-16 13:59:49'),(56357640475080,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-16 13:59:50'),(56357641241034,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-16 13:59:50'),(56357641949644,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-16 13:59:50'),(56357660680654,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-16 13:59:55'),(56357673697744,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-16 13:59:58'),(56357774397906,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-16 14:00:22'),(56357777392084,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-16 14:00:23'),(56357779628502,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-16 14:00:24'),(56357781488088,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-16 14:00:24'),(56357782389210,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-16 14:00:24'),(56357783343580,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-16 14:00:24'),(56357784179166,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-16 14:00:25'),(56357785027040,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-16 14:00:25'),(56357785858530,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-16 14:00:25'),(56357786575332,1,'AppController','version','/app/version','{}','127.0.0.1','',0,NULL,'2022-05-16 14:00:25'),(56357823267302,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'No available service','2022-05-16 14:00:34'),(56358254227945,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-16 14:02:19'),(56358836929004,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'/ by zero','2022-05-16 14:04:42'),(56359455474159,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'/ by zero','2022-05-16 14:07:13'),(56359787192818,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'/ by zero','2022-05-16 14:08:34'),(56361057702389,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'/ by zero','2022-05-16 14:13:44'),(56361160258038,1,'SysLogServiceImpl','seataTest','/seata','','',NULL,0,NULL,NULL),(56361160454648,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-16 14:14:09'),(56361163801081,1,'SysLogServiceImpl','seataTest','/seata','','',NULL,0,NULL,NULL),(56361163993595,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-16 14:14:10'),(56361164702204,1,'SysLogServiceImpl','seataTest','/seata','','',NULL,0,NULL,NULL),(56361164956158,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-16 14:14:10'),(56361165480447,1,'SysLogServiceImpl','seataTest','/seata','','',NULL,0,NULL,NULL),(56361165738433,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-16 14:14:10'),(56361764766148,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'client fail','2022-05-16 14:16:36'),(56361768735175,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'client fail','2022-05-16 14:16:37'),(56361771450826,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'client fail','2022-05-16 14:16:38'),(56361773023693,2,'AppController','seata','/app/seata','{}','127.0.0.1','',0,'client fail','2022-05-16 14:16:38'),(56361890963918,1,'SysLogServiceImpl','seataTest','/seata','','',NULL,0,NULL,NULL),(56361891160528,1,'AppController','seata','/app/seata','{}','127.0.0.1','',0,NULL,'2022-05-16 14:17:07');
/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_menu` (
  `id` bigint NOT NULL COMMENT '主键',
  `pid` bigint NOT NULL DEFAULT '0' COMMENT '上级id',
  `pids` varchar(255) NOT NULL DEFAULT '0' COMMENT '上级ids（多个用,隔开）',
  `name` varchar(31) NOT NULL DEFAULT '' COMMENT '名称',
  `title` varchar(63) NOT NULL DEFAULT '' COMMENT '标题，需要国际化',
  `url` varchar(63) NOT NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(63) NOT NULL DEFAULT '' COMMENT 'vue组件',
  `is_frame` tinyint NOT NULL DEFAULT '0' COMMENT '是否外链（0 不是 , 1 是）',
  `link` varchar(127) NOT NULL DEFAULT '0' COMMENT '开启外链',
  `is_keep_alive` tinyint NOT NULL DEFAULT '1' COMMENT '菜单是否缓存',
  `is_affix` tinyint NOT NULL DEFAULT '0' COMMENT '菜单是否固定',
  `redirect` varchar(63) DEFAULT '' COMMENT '重定向路径',
  `perms` varchar(255) NOT NULL DEFAULT '' COMMENT '权限标识',
  `icon` varchar(63) NOT NULL DEFAULT '' COMMENT '菜单图标',
  `type` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '菜单类型（1 目录 2  菜单 3 按钮 ）',
  `level` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '层级',
  `sort` smallint NOT NULL DEFAULT '1' COMMENT '排序',
  `status` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '状态（0 停用 , 1 启用）',
  `remarks` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 （0 未删除 , 1 删除）',
  `create_id` bigint DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_name` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人姓名',
  `update_id` bigint DEFAULT '0' COMMENT '修改人id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_name` varchar(31) DEFAULT NULL COMMENT '修改人姓名',
  `sub_count` smallint NOT NULL DEFAULT '0' COMMENT '子菜单数目（直接下级）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统菜单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (9346244317697,0,'0','home','message.router.home','/home','home/index',0,'',1,1,'','','iconfont icon-shouye',1,1,1,1,'',0,2689815704065,'2022-01-03 17:49:58','liufeng',2689815704065,'2022-01-03 17:49:58','liufeng',0),(9346244317698,0,'0','system','message.router.system','/system','layout/routerView/parent',0,'',1,0,'/system/menu','','iconfont icon-xitongshezhi',1,1,2,1,'',0,2689815704065,'2022-01-03 17:49:58','liufeng',1,'2022-01-12 17:58:45','admin',2),(9346244321795,9346244317698,'9346244317698','systemMenu','message.router.systemMenu','/system/menu','system/menu/index',0,'',1,0,'','','iconfont icon-caidan',1,2,1,1,'',0,2689815704065,'2022-01-03 17:49:58','liufeng',2689815704065,'2022-01-03 17:49:58','liufeng',0),(9346244321796,9346244317698,'9346244317698','systemUser','message.router.systemUser','/system/user','system/user/index',0,'',1,0,'','','iconfont icon-icon-',1,2,1,1,'',0,2689815704065,'2022-01-03 17:49:58','liufeng',2689815704065,'2022-01-03 17:49:58','liufeng',0),(9346244321811,0,'0','personal','message.router.personal','/personal','personal/index',0,'',1,0,'','','iconfont icon-gerenzhongxin',1,1,7,1,'',0,2689815704065,'2022-01-03 17:49:58','liufeng',2689815704065,'2022-01-03 17:49:58','liufeng',0),(9346244321813,0,'0','layoutLinkView','message.router.layoutLinkView','/link','layout/routerView/link',0,'https://element-plus.gitee.io/#/zh-CN/component/installation',0,0,'','','iconfont icon-caozuo-wailian',1,1,9,1,'',0,2689815704065,'2022-01-03 17:49:58','liufeng',2689815704065,'2022-01-03 17:49:58','liufeng',0),(11806234821121,9346244317698,'9346244317698','systemRole','message.router.systemRole','/system/role','/system/role/index',0,'',1,0,'','','iconfont icon-gerenzhongxin',1,2,11,1,'',0,1,'2022-01-10 16:39:42','admin',1,'2022-01-10 21:04:59','admin',0),(12584787636747,9346244321795,'9346244317698,9346244321795','','message.router.add','','',0,'',1,0,'','/sysMenu/post','',2,3,1,1,'',0,1,'2022-01-12 21:27:38','admin',1,'2022-01-12 21:27:38','admin',0),(12857272197633,9346244321795,'9346244317698,9346244321795','','message.router.update','','',0,'',1,0,'','/sysMenu/put','',2,3,2,1,'',0,1,'2022-01-13 15:56:22','admin',1,'2022-01-13 15:56:22','admin',0),(12858490110466,9346244321795,'9346244317698,9346244321795','','message.router.delete','','',0,'',1,0,'','/sysMenu/delete','',2,3,3,1,'',0,1,'2022-01-13 16:01:20','admin',1,'2022-01-13 16:01:20','admin',0),(12858679345667,9346244321796,'9346244317698,9346244321796','','message.router.add','','',0,'',1,0,'','/sysUser/post','',2,3,1,1,'',0,1,'2022-01-13 16:02:06','admin',1,'2022-01-13 16:02:06','admin',0),(12858733580804,9346244321796,'9346244317698,9346244321796','','message.router.update','','',0,'',1,0,'','/sysUser/put','',2,3,2,1,'',0,1,'2022-01-13 16:02:19','admin',1,'2022-01-13 16:02:19','admin',0),(12858780197381,9346244321796,'9346244317698,9346244321796','','message.router.delete','','',0,'',1,0,'','/sysUser/delete','',2,3,3,1,'',0,1,'2022-01-13 16:02:31','admin',1,'2022-01-13 16:02:31','admin',0),(12859214053894,11806234821121,'9346244317698,11806234821121','','message.router.add','','',0,'',1,0,'','/sysRole/post','',2,3,1,1,'',0,1,'2022-01-13 16:04:17','admin',1,'2022-01-13 16:04:17','admin',0),(12859259920903,11806234821121,'9346244317698,11806234821121','','message.router.update','','',0,'',1,0,'','/sysRole/put','',2,3,2,1,'',0,1,'2022-01-13 16:04:28','admin',1,'2022-01-13 16:04:28','admin',0),(12859299734024,11806234821121,'9346244317698,11806234821121','','message.router.delete','','',0,'',1,0,'','/sysRole/delete','',2,3,3,1,'',0,1,'2022-01-13 16:04:37','admin',1,'2022-01-13 16:04:37','admin',0),(14610968408611,9346244317698,'0,9346244317698','systemLog','message.router.systemLog','/system/log','/system/log/index',0,'',1,0,'','','elementEdit',1,2,4,1,'',0,1,'2022-01-18 14:52:11','admin',1,'2022-01-18 14:52:11','admin',0),(14623462507035,9346244317698,'0,9346244317698','systemDict','message.router.systemDict','/system/dict','/system/dict/index',0,'',1,0,'','','iconfont icon-fuwenbenkuang',1,2,5,1,'',0,1,'2022-01-18 15:43:01','admin',1,'2022-01-18 18:28:58','admin',0);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(31) NOT NULL DEFAULT '' COMMENT '名称',
  `description` varchar(31) NOT NULL DEFAULT '' COMMENT '描述',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 （0 未删除 , 1 删除）',
  `create_id` bigint DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_name` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人姓名',
  `update_id` bigint DEFAULT '0' COMMENT '修改人id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_name` varchar(31) DEFAULT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'超级管理员','超级管理员',0,1,'2021-09-23 16:22:12','admin',1,'2021-09-23 16:22:12','admin'),(2,'运营总监','运营管理',0,1,'2021-09-23 16:22:12','admin',1,'2022-01-11 21:11:53','admin'),(3,'商家运营','商家/品牌运营/设计师管理/',1,1,'2021-09-23 16:22:12','admin',1,'2021-09-23 16:22:12','admin'),(4,'活动运营','活动管理',0,1,'2021-09-23 16:22:12','admin',1,'2022-01-11 21:12:02','admin'),(5,'财务经理','财务管理',0,1,'2021-09-23 16:22:12','admin',1,'2022-01-11 21:12:12','admin'),(6,'测试人员','测试人员',0,1,'2021-09-23 16:22:12','admin',1,'2021-09-23 16:22:12','admin'),(12163886801409,'123','1231',1,1,'2022-01-11 16:54:59','admin',1,'2022-01-11 16:58:08','admin'),(12164621758978,'444','4',1,1,'2022-01-11 16:57:58','admin',1,'2022-01-11 16:58:13','admin'),(12169281565187,'66666','77777',1,1,'2022-01-11 17:16:56','admin',1,'2022-01-11 21:11:24','admin'),(12481547104769,'测试1','测试角色1',1,1,'2022-01-12 14:27:33','admin',1,'2022-01-12 14:28:46','admin'),(12481709048322,'测试2','测试角色2',1,1,'2022-01-12 14:28:12','admin',1,'2022-01-12 14:28:44','admin'),(12481879642625,'测试3','测试角色3',1,1,'2022-01-12 14:28:54','admin',1,'2022-01-12 14:29:42','admin'),(12482197987842,'测试345','测试角色345',1,1,'2022-01-12 14:30:12','admin',1,'2022-01-12 15:09:16','admin'),(14608167608884,'测试1','测试角色',1,1,'2022-01-18 14:40:47','admin',1,'2022-01-18 14:40:53','admin');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_roles_menus`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_roles_menus` (
  `id` bigint NOT NULL COMMENT '主键',
  `role_id` bigint NOT NULL DEFAULT '0' COMMENT '角色id',
  `menu_id` bigint NOT NULL DEFAULT '0' COMMENT '菜单id',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色菜单关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_roles_menus`
--

LOCK TABLES `sys_roles_menus` WRITE;
/*!40000 ALTER TABLE `sys_roles_menus` DISABLE KEYS */;
INSERT INTO `sys_roles_menus` VALUES (12212347052560,12169281565187,9346244321810),(12212355723793,12169281565187,9346244321810),(12225047233025,12169281565187,9346244317698),(12225047233027,12169281565187,9346244321796),(12225047233028,12169281565187,11806234821121),(12225201803793,2,9346244321809),(12225278030357,4,9346244321813),(12225308545564,5,9346244321806),(12225316360734,5,9346244321810),(12226070094368,6,9346244321809),(12226074346017,6,9346244321810),(12226078994978,6,9346244317697),(12226083615267,6,9346244321811),(12226087580196,6,9346244321812),(12226091618853,6,9346244321813),(12226703266349,12169281565187,9346244317697),(12226706965038,12169281565187,9346244321795),(12226755433007,5,9346244317697),(12226776240688,4,9346244317697),(12228731347511,2,9346244317697),(12228741718585,2,9346244321810),(12229097603644,5,9346244321805),(12229171192381,5,9346244321808),(12229171192382,5,9346244321809),(12232164073999,6,9346244321808),(12239839404567,2,9346244321808),(12239955698202,6,9346244321802),(12239955698203,6,9346244321800),(12239955698204,6,9346244321801),(12239967875613,6,9346244321804),(12239967875614,6,9346244321803),(12239976505887,6,9346244321807),(12472718193165,1,9346244317697),(12472727925269,1,9346244321800),(12472727925270,1,9346244321801),(12472727925271,1,9346244321802),(12472727925272,1,9346244321803),(12472727925273,1,9346244321804),(12472727925274,1,9346244321805),(12472727925275,1,9346244321806),(12472727925276,1,9346244321807),(12472730841629,1,9346244321808),(12472730841630,1,9346244321809),(12472733200927,1,9346244321810),(12472736154144,1,9346244321811),(12472739168801,1,9346244321812),(12472742113826,1,9346244321813),(12472745722403,1,9346244321814),(12535084048898,5,9346244321800),(12535084048899,5,9346244321801),(12535084048900,5,9346244321802),(12535084048901,5,9346244321803),(12535084048902,5,9346244321804),(12535084048903,5,9346244321807),(12942637576705,1,9346244317698),(12942637576706,1,9346244321795),(12942637576707,1,12584787636747),(12942637576708,1,12857272197633),(12942637576709,1,12858490110466),(12942637576710,1,9346244321796),(12942637576711,1,12858679345667),(12942637576712,1,12858733580804),(12942637576713,1,12858780197381),(12942637576714,1,11806234821121),(12942637576715,1,12859214053894),(12942637576716,1,12859259920903),(12942637576717,1,12859299734024),(22860678476874,1,14610968408611),(22860692477004,1,14623462507035),(22860754453584,2,9346244321811),(22860756886610,2,9346244321813),(22860803413078,4,9346244321811),(22860821115994,5,9346244321811),(22860823549020,5,9346244321813),(22860849665120,6,14610968408611),(22860849665121,6,9346244317698),(22860854232163,6,14623462507035),(22864436450429,2,14623462507035),(22864436450430,2,9346244317698);
/*!40000 ALTER TABLE `sys_roles_menus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(31) NOT NULL DEFAULT '' COMMENT '姓名',
  `password` varchar(63) NOT NULL DEFAULT '' COMMENT '密码',
  `phone` varchar(15) NOT NULL DEFAULT '' COMMENT '手机',
  `email` varchar(63) DEFAULT '' COMMENT '邮箱',
  `head_image` varchar(255) DEFAULT '' COMMENT '头像',
  `sex` tinyint NOT NULL DEFAULT '3' COMMENT '性别（0 女 1 男  3 未设置 ）',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '账号状态（1 正常 2 冻结 3 禁用）',
  `dept_id` bigint DEFAULT NULL COMMENT '部门id',
  `post_id` bigint DEFAULT NULL COMMENT '岗位id',
  `job_status` tinyint NOT NULL DEFAULT '1' COMMENT '在职状态(1 在职 2 离职)',
  `password_error_num` smallint NOT NULL DEFAULT '0' COMMENT '密码错误次数',
  `update_password_time` datetime DEFAULT NULL COMMENT '修改密码时间',
  `is_reset_password` tinyint NOT NULL DEFAULT '0' COMMENT '是否重置初始密码（0 否 1 是）',
  `is_admin` tinyint NOT NULL DEFAULT '0' COMMENT '是否管理员（0 否 1 是）',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 （0 未删除 , 1 删除）',
  `create_id` bigint DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_name` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人姓名',
  `update_id` bigint DEFAULT '0' COMMENT '修改人id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_name` varchar(31) DEFAULT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'admin','$2a$10$ZkNzjcQMZfdpRJ1z04l4r.G4g2/0v7MPSnYqIggK57dOtSPOVEXse','18999999999','1@qq.com','/sys/79dee3e1fc157605fcab598f7a6dbe602.png',3,1,1263045492112351233,0,1,0,'2021-06-17 16:49:28',1,1,0,0,'2020-05-21 16:36:17','admin',1,'2022-04-02 17:00:09','admin'),(2689815704065,'liufeng','$2a$10$ZkNzjcQMZfdpRJ1z04l4r.G4g2/0v7MPSnYqIggK57dOtSPOVEXse','18633333333','123456@qq.com','/sys/70befb95a8421bf0fc6b7de0434b7eb2.png',1,1,NULL,NULL,1,0,NULL,0,0,0,0,'2021-12-15 22:24:53','',1,'2022-04-02 17:00:34','admin'),(9687140418049,'zhangs','','18963636363','123@163.com','/sys/5854a32509720c67a5b09ebca3cfef33.png',1,1,NULL,NULL,1,0,NULL,0,0,0,1,'2022-01-04 16:57:05','admin',1,'2022-04-02 17:00:52','admin'),(9688243069442,'lis','$2a$10$RFoYvXE.5W6kAivraWEyi.Y47Xda/GzkrOSMbYlkRzclrwCiF3JDe','18933333333','123456@163.com','',1,1,NULL,NULL,1,0,NULL,0,0,0,1,'2022-01-04 17:01:34','admin',1,'2022-02-11 11:23:11','admin'),(9688674755075,'wangwu','123456','18639948888','1@qq.com','',1,1,NULL,NULL,1,0,NULL,0,0,0,1,'2022-01-04 17:03:19','admin',1,'2022-01-05 15:28:27','admin'),(9688890032644,'zhaoliu','66666666','18977777777','123@163.com','/sys/d32a868cae452efb3983640fed35f77b.png',1,1,NULL,NULL,1,0,NULL,0,0,0,1,'2022-01-04 17:04:12','admin',1,'2022-04-02 17:01:15','admin'),(9692978156033,'zhaoyun','123456798','18788888888','123456798@qq.com','/sys/efb1befc6f27429efea472133589edc8.png',1,1,NULL,NULL,1,0,NULL,0,0,0,1,'2022-01-04 17:20:50','admin',1,'2022-04-02 17:02:48','admin'),(9693739528705,'likui','123124','18799999999','123456@163.com','/sys/09f797764390017d8b69c12d89d152c2.png',1,1,NULL,NULL,1,0,NULL,0,0,0,1,'2022-01-04 17:23:56','admin',1,'2022-04-02 17:03:07','admin'),(10007044579841,'刘备','123456','13655555555','123@qq.com','',1,1,NULL,NULL,1,0,NULL,0,0,0,1,'2022-01-05 14:38:46','admin',1,'2022-01-05 15:32:08','admin'),(10007155495426,'张飞','$2a$10$MOBv1cubJ9ql2i58uHxvJOd5rxMq0aKEscb0dWZr.wyUCX3zvvJCm','13666666666','123@qq.com','',1,0,NULL,NULL,1,0,NULL,0,0,0,1,'2022-01-05 14:39:13','admin',1,'2022-02-09 16:18:31','admin'),(10007217418755,'关羽','123456','13677777777','123@qq.com','',1,1,NULL,NULL,1,0,NULL,0,0,0,1,'2022-01-05 14:39:28','admin',1,'2022-01-05 15:33:20','admin'),(10023037043204,'历史','1','18966666666','123@qq.com','/sys/41bfb685fc004be681c16e711c4a437a.png',1,1,NULL,NULL,1,0,NULL,0,0,0,1,'2022-01-05 15:43:51','admin',1,'2022-04-02 17:03:32','admin'),(10027981636101,'','','','','',3,0,NULL,NULL,1,0,NULL,0,0,1,1,'2022-01-05 16:03:58','admin',1,'2022-01-06 14:05:24','admin'),(10037252731398,'','','','','',1,0,NULL,NULL,1,0,NULL,0,0,1,1,'2022-01-05 16:41:41','admin',1,'2022-01-06 14:05:22','admin'),(10041126011399,'','','','','',1,0,NULL,NULL,1,0,NULL,0,0,1,1,'2022-01-05 16:57:27','admin',1,'2022-01-06 14:06:13','admin'),(10043190067720,'','','','','',1,0,NULL,NULL,1,0,NULL,0,0,1,1,'2022-01-05 17:05:51','admin',1,'2022-01-06 14:05:18','admin'),(10043854172681,'','','','','',1,0,NULL,NULL,1,0,NULL,0,0,1,1,'2022-01-05 17:08:33','admin',1,'2022-01-06 14:05:07','admin'),(10045006045706,'1','','1','1','1',1,0,NULL,NULL,1,0,NULL,0,0,1,1,'2022-01-05 17:13:14','admin',1,'2022-01-06 15:27:37','admin'),(10045461955083,'','','','','',1,0,NULL,NULL,1,0,NULL,0,0,1,1,'2022-01-05 17:15:05','admin',1,'2022-01-06 14:05:05','admin'),(10045894586892,'','','','','',1,0,NULL,NULL,1,0,NULL,0,0,1,1,'2022-01-05 17:16:51','admin',1,'2022-01-06 14:05:02','admin'),(10046522716685,'','','','','',1,0,NULL,NULL,1,0,NULL,0,0,1,1,'2022-01-05 17:19:24','admin',1,'2022-01-06 14:04:55','admin'),(10046997676558,'','','','','',1,0,NULL,NULL,1,0,NULL,0,0,1,1,'2022-01-05 17:21:20','admin',1,'2022-01-06 14:01:47','admin'),(10654762189313,'','','','','',3,0,NULL,NULL,1,0,NULL,0,0,1,1,'2022-01-07 10:34:20','admin',1,'2022-01-07 11:10:05','admin'),(10655326458370,'','','','','',3,0,NULL,NULL,1,0,NULL,0,0,1,1,'2022-01-07 10:36:38','admin',1,'2022-01-07 11:54:16','admin'),(10663488446979,'zhangsan','1213243','12312412322','123456@163.com','',1,1,NULL,NULL,1,0,NULL,0,0,0,1,'2022-01-07 11:09:51','admin',1,'2022-01-07 11:09:51','admin'),(14607875318303,'加密123','$2a$10$MdQ/p6XLUF5sTN0wdYKfDOUODIVj3Le.HFhsxR0SNLnZotV5wtwHC','13656565656','123@163.com','',0,1,NULL,NULL,1,0,NULL,0,0,0,1,'2022-01-18 14:39:36','admin',1,'2022-01-19 13:42:12','admin'),(29465162562856,'大王','$2a$10$UBm6sYSWS8ETYZIScFPoB.jhwoDYxWZDf/1JUaAni6pI3QXiMKXhi','13726896589','dawang@163.com','/sys/8fa027bc3c5dc385fb85126d47386717.png',0,1,NULL,NULL,1,0,NULL,0,0,0,1,'2022-03-01 14:14:03','admin',1,'2022-04-02 17:04:25','admin');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_users_roles`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_users_roles` (
  `id` bigint NOT NULL COMMENT '主键',
  `role_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '角色id',
  `user_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_users_roles`
--

LOCK TABLES `sys_users_roles` WRITE;
/*!40000 ALTER TABLE `sys_users_roles` DISABLE KEYS */;
INSERT INTO `sys_users_roles` VALUES (12442642948610,6,2689815704065),(12442689167875,6,9687140418049),(12442717086212,4,9688674755075),(12442717086213,5,9688674755075),(12442741404166,6,9693739528705),(12442760692231,6,10007155495426),(12442831221257,6,10007044579841),(12472247611915,6,9688243069442),(12472287093260,5,9693739528705),(12472810881572,1,2689815704065),(12472921121317,6,10023037043204);
/*!40000 ALTER TABLE `sys_users_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `undo_log`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `undo_log` (
  `branch_id` bigint NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(128) NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime(6) NOT NULL COMMENT 'create datetime',
  `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='AT transaction mode undo table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `undo_log`
--

LOCK TABLES `undo_log` WRITE;
/*!40000 ALTER TABLE `undo_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `undo_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'f-sys'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-24 13:47:44
