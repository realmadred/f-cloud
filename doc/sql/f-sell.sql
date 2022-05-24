-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: f-sell
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
-- Table structure for table `finance_flow`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `finance_flow` (
  `id` bigint NOT NULL DEFAULT '0' COMMENT '主键',
  `order_id` bigint NOT NULL DEFAULT '0' COMMENT '订单ID',
  `amount` bigint NOT NULL DEFAULT '0' COMMENT '金额',
  `in_out` tinyint NOT NULL DEFAULT '0' COMMENT '收入支出',
  `order_in_amount` bigint NOT NULL DEFAULT '0' COMMENT '订单实际收入',
  `remark` varchar(127) NOT NULL DEFAULT '' COMMENT '备注',
  `is_check` tinyint NOT NULL DEFAULT '0' COMMENT '是否对账',
  `create_id` bigint NOT NULL DEFAULT '0' COMMENT '创建人id',
  `create_name` varchar(15) DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` bigint NOT NULL DEFAULT '0' COMMENT '修改人id',
  `update_name` varchar(15) DEFAULT '' COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='财务流水';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finance_flow`
--

LOCK TABLES `finance_flow` WRITE;
/*!40000 ALTER TABLE `finance_flow` DISABLE KEYS */;
/*!40000 ALTER TABLE `finance_flow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sell_community`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sell_community` (
  `id` bigint NOT NULL DEFAULT '0' COMMENT 'id',
  `name` varchar(15) NOT NULL DEFAULT '' COMMENT '小区名称',
  `address` varchar(63) NOT NULL DEFAULT '' COMMENT '地址',
  `house_number` int NOT NULL DEFAULT '0' COMMENT '户数',
  `create_id` bigint NOT NULL DEFAULT '0' COMMENT '创建人id',
  `create_name` varchar(15) DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` bigint NOT NULL DEFAULT '0' COMMENT '修改人id',
  `update_name` varchar(15) DEFAULT '' COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='小区';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sell_community`
--

LOCK TABLES `sell_community` WRITE;
/*!40000 ALTER TABLE `sell_community` DISABLE KEYS */;
INSERT INTO `sell_community` VALUES (56361160323334,'test','beijing',1000,0,'','2022-05-16 14:14:09',0,'','2022-05-16 14:14:09',0),(56361163866375,'test','beijing',1000,0,'','2022-05-16 14:14:10',0,'','2022-05-16 14:14:10',0),(56361164763400,'test','beijing',1000,0,'','2022-05-16 14:14:10',0,'','2022-05-16 14:14:10',0),(56361165607177,'test','beijing',1000,0,'','2022-05-16 14:14:10',0,'','2022-05-16 14:14:10',0),(56361891033358,'test','beijing',1000,0,'','2022-05-16 14:17:07',0,'','2022-05-16 14:17:07',0);
/*!40000 ALTER TABLE `sell_community` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sell_order`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sell_order` (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(31) NOT NULL DEFAULT '' COMMENT '名称',
  `amount` bigint NOT NULL DEFAULT '0' COMMENT '订单金额',
  `pay_amount` bigint NOT NULL DEFAULT '0' COMMENT '首付金额',
  `pay_method` tinyint NOT NULL DEFAULT '0' COMMENT '支付方式',
  `discount_amount` bigint NOT NULL DEFAULT '0' COMMENT '优惠金额',
  `last_pay_amount` bigint NOT NULL DEFAULT '0' COMMENT '尾款金额',
  `past_pay_method` tinyint DEFAULT '0' COMMENT '尾款支付方式',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `last_pay_time` datetime DEFAULT NULL COMMENT '尾款支付时间',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态;0-创建、1-支付定金、2-已量房、3-设计中、4-已复尺、5-设计确认、6-下单、7-安装、8-安装完成、9-完成',
  `community_id` bigint NOT NULL DEFAULT '0' COMMENT '小区id',
  `community_name` varchar(31) NOT NULL DEFAULT '' COMMENT '小区名称',
  `member_name` varchar(15) NOT NULL DEFAULT '' COMMENT '业主姓名',
  `member_phone` varchar(15) NOT NULL DEFAULT '' COMMENT '业主电话',
  `remark` varchar(127) NOT NULL DEFAULT '' COMMENT '备注',
  `create_id` bigint NOT NULL DEFAULT '0' COMMENT '创建人id',
  `create_name` varchar(15) DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` bigint NOT NULL DEFAULT '0' COMMENT '修改人id',
  `update_name` varchar(15) DEFAULT '' COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_member_name` (`member_name`),
  KEY `idx_community_id` (`community_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sell_order`
--

LOCK TABLES `sell_order` WRITE;
/*!40000 ALTER TABLE `sell_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `sell_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sell_order_item`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sell_order_item` (
  `id` bigint NOT NULL COMMENT '主键',
  `order_id` bigint NOT NULL DEFAULT '0' COMMENT '订单ID',
  `name` varchar(31) NOT NULL DEFAULT '' COMMENT '项目名称',
  `amount` bigint NOT NULL DEFAULT '0' COMMENT '金额',
  `remark` varchar(127) NOT NULL DEFAULT '' COMMENT '备注',
  `create_id` bigint NOT NULL DEFAULT '0' COMMENT '创建人id',
  `create_name` varchar(15) DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` bigint NOT NULL DEFAULT '0' COMMENT '修改人id',
  `update_name` varchar(15) DEFAULT '' COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单明细';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sell_order_item`
--

LOCK TABLES `sell_order_item` WRITE;
/*!40000 ALTER TABLE `sell_order_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `sell_order_item` ENABLE KEYS */;
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
-- Dumping routines for database 'f-sell'
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
