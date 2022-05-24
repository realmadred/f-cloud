-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: f-boot
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
  `code` varchar(32) NOT NULL DEFAULT '' COMMENT '编号',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '名称',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 （0 未删除 , 1 删除）',
  `create_id` bigint DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人姓名',
  `update_id` bigint DEFAULT '0' COMMENT '修改人id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_name` varchar(32) DEFAULT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_code` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数据字典';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict`
--

LOCK TABLES `sys_dict` WRITE;
/*!40000 ALTER TABLE `sys_dict` DISABLE KEYS */;
INSERT INTO `sys_dict` VALUES (8550957391874,'yes_no','是否',0,0,'2020-05-20 10:10:05',NULL,0,'2020-05-20 10:10:05',NULL),(8991911358465,'enable_status','启用状态',0,0,'2020-05-20 12:11:03',NULL,0,'2020-05-20 12:11:03',NULL),(14671686754847,'test','测试数据1',1,1,'2022-01-18 18:59:15','admin',1,'2022-01-20 11:51:31','admin'),(83761268785153,'job_status','在职状态',0,0,'2020-05-20 13:49:28',NULL,0,'2020-05-20 13:49:28',NULL),(84010456580097,'user_status','账号状态',0,0,'2020-05-20 13:50:28',NULL,0,'2020-05-20 13:50:28',NULL),(84261347262465,'sex','性别',0,0,'2020-05-20 13:51:28',NULL,1,'2022-01-20 13:48:27','admin'),(84652092817409,'menu_type','菜单类型',0,0,'2020-05-20 13:53:01',NULL,1,'2022-01-20 11:48:39','admin'),(84806338347009,'is_delete','是否删除',0,0,'2020-05-20 13:53:38',NULL,0,'2020-05-20 13:53:38',NULL),(85139311558658,'log_type','日志类型',0,0,'2020-05-20 13:54:57',NULL,0,'2020-05-20 13:54:57',NULL);
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
  `dict_code` varchar(32) NOT NULL DEFAULT '' COMMENT '编号',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '名称',
  `value` varchar(32) NOT NULL DEFAULT '' COMMENT '字典值',
  `sort` smallint NOT NULL DEFAULT '1' COMMENT '排序',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 （0 未删除 , 1 删除）',
  `create_id` bigint DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人姓名',
  `update_id` bigint DEFAULT '0' COMMENT '修改人id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_name` varchar(32) DEFAULT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`),
  KEY `idx_code` (`dict_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数据字典详情';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_detail`
--

LOCK TABLES `sys_dict_detail` WRITE;
/*!40000 ALTER TABLE `sys_dict_detail` DISABLE KEYS */;
INSERT INTO `sys_dict_detail` VALUES (25,3,'InvoiceHeaderType','个人','1',1,0,0,NULL,NULL,0,NULL,NULL),(14671860417062,14671686754847,'test','名称1','1',1,0,1,'2022-01-18 18:59:57','admin',1,'2022-01-18 18:59:57','admin'),(14671860417063,14671686754847,'test','名称2','2',2,0,1,'2022-01-18 18:59:57','admin',1,'2022-01-18 18:59:57','admin'),(14671860417064,14671686754847,'test','名称3','3',3,0,1,'2022-01-18 18:59:57','admin',1,'2022-01-18 18:59:57','admin'),(15273653371395,84652092817409,'menu_type','菜单','1',1,0,1,'2022-01-20 11:48:39','admin',1,'2022-01-20 11:48:39','admin'),(15273653371396,84652092817409,'menu_type','按钮','2',2,0,1,'2022-01-20 11:48:39','admin',1,'2022-01-20 11:48:39','admin'),(15303094514181,84261347262465,'sex','女','0',1,0,1,'2022-01-20 13:48:27','admin',1,'2022-01-20 13:48:27','admin'),(15303094514182,84261347262465,'sex','男','1',2,0,1,'2022-01-20 13:48:27','admin',1,'2022-01-20 13:48:27','admin'),(15303094514183,84261347262465,'sex','未设置','2',3,0,1,'2022-01-20 13:48:27','admin',1,'2022-01-20 13:48:27','admin'),(989881920180226,8550957391874,'yes_no','是','1',1,0,0,'2020-05-20 14:13:48',NULL,0,'2020-05-20 14:13:48',NULL),(990215283462146,8550957391874,'yes_no','否','0',2,0,0,'2020-05-20 14:15:07',NULL,0,'2020-05-20 14:15:07',NULL),(991329567428609,8991911358465,'enable_status','启用','1',1,0,0,'2020-05-20 14:19:33',NULL,0,'2020-05-20 14:19:33',NULL),(991370004713474,8991911358465,'enable_status','停用','0',2,0,0,'2020-05-20 14:19:43',NULL,0,'2020-05-20 14:19:43',NULL),(991683101118466,83761268785153,'job_status','在职','1',1,0,0,'2020-05-20 14:20:57',NULL,0,'2020-05-20 14:20:57',NULL),(991772292993026,83761268785153,'job_status','离职','2',2,0,0,'2020-05-20 14:21:18',NULL,0,'2020-05-20 14:21:18',NULL),(991981173526530,84010456580097,'user_status','正常','1',1,0,0,'2020-05-20 14:22:08',NULL,0,'2020-05-20 14:22:08',NULL),(992074463236097,84010456580097,'user_status','冻结','2',2,0,0,'2020-05-20 14:22:30',NULL,0,'2020-05-20 14:22:30',NULL),(992119887548418,84010456580097,'user_status','禁用','3',3,0,0,'2020-05-20 14:22:41',NULL,0,'2020-05-20 14:22:41',NULL),(992922568282113,84806338347009,'is_delete','未删除','0',1,0,0,'2020-05-20 14:25:53',NULL,0,'2020-05-20 14:25:53',NULL),(992954507907073,84806338347009,'is_delete','已删除','1',2,0,0,'2020-05-20 14:26:00',NULL,0,'2020-05-20 14:26:00',NULL),(993486718947329,85139311558658,'log_type','普通','1',1,0,0,'2020-05-20 14:28:07',NULL,0,'2020-05-20 14:28:07',NULL),(993518738264066,85139311558658,'log_type','错误','2',2,0,0,'2020-05-20 14:28:15',NULL,0,'2020-05-20 14:28:15',NULL);
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
  `class_name` varchar(128) NOT NULL COMMENT '类名',
  `method_name` varchar(128) NOT NULL COMMENT '方法名',
  `request_uri` varchar(128) NOT NULL COMMENT '请求路径',
  `params` varchar(1024) NOT NULL COMMENT '参数',
  `request_ip` varchar(16) NOT NULL COMMENT '请求ip',
  `create_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人姓名',
  `create_id` bigint DEFAULT '0' COMMENT '创建人id',
  `error_detail` varchar(1024) DEFAULT NULL COMMENT '错误信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log`
--

LOCK TABLES `sys_log` WRITE;
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
INSERT INTO `sys_log` VALUES (15271128216066,1,'AuthController','getKey','/auth/getKey','{\"key\":[\"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCWKVzsnC0G05Z7/F5A PgxAh9YulA5OOvrng00k 0B7iBLpQf1EW6gUdb KDYu/cA3JAH9jh3wjCk25zv/3PuInWpygPA9bUg8gUDUici2X4nLFDvycBOCSh3mLNZpL3a1OBWpXNbCKJMrxbGG1C x0M6PM7ry060afeE8TVGDzQIDAQAB\"]}','127.0.0.1','',0,NULL,'2022-01-20 11:38:23');
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
  `pids` varchar(256) NOT NULL DEFAULT '0' COMMENT '上级ids（多个用,隔开）',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '名称',
  `title` varchar(63) NOT NULL DEFAULT '' COMMENT '标题，需要国际化',
  `url` varchar(64) NOT NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(64) NOT NULL DEFAULT '' COMMENT 'vue组件',
  `is_frame` tinyint NOT NULL DEFAULT '0' COMMENT '是否外链（0 不是 , 1 是）',
  `link` varchar(127) NOT NULL DEFAULT '0' COMMENT '开启外链',
  `is_keep_alive` tinyint NOT NULL DEFAULT '1' COMMENT '菜单是否缓存',
  `is_affix` tinyint NOT NULL DEFAULT '0' COMMENT '菜单是否固定',
  `redirect` varchar(63) DEFAULT '' COMMENT '重定向路径',
  `perms` varchar(256) NOT NULL DEFAULT '' COMMENT '权限标识',
  `icon` varchar(63) NOT NULL DEFAULT '' COMMENT '菜单图标',
  `type` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '菜单类型（1 目录 2  菜单 3 按钮 ）',
  `level` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '层级',
  `sort` smallint NOT NULL DEFAULT '1' COMMENT '排序',
  `status` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '状态（0 停用 , 1 启用）',
  `remarks` varchar(256) NOT NULL DEFAULT '' COMMENT '备注',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 （0 未删除 , 1 删除）',
  `create_id` bigint DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人姓名',
  `update_id` bigint DEFAULT '0' COMMENT '修改人id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_name` varchar(32) DEFAULT NULL COMMENT '修改人姓名',
  `sub_count` smallint NOT NULL DEFAULT '0' COMMENT '子菜单数目（直接下级）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统菜单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (9346244317697,0,'0','home','message.router.home','/home','home/index',0,'',1,1,'','','iconfont icon-shouye',1,1,1,1,'',0,2689815704065,'2022-01-03 17:49:58','liufeng',2689815704065,'2022-01-03 17:49:58','liufeng',0),(9346244317698,0,'0','system','message.router.system','/system','layout/routerView/parent',0,'',1,0,'/system/menu','','iconfont icon-xitongshezhi',1,1,2,1,'',0,2689815704065,'2022-01-03 17:49:58','liufeng',1,'2022-01-12 17:58:45','admin',2),(9346244321795,9346244317698,'9346244317698','systemMenu','message.router.systemMenu','/system/menu','system/menu/index',0,'',1,0,'','','iconfont icon-caidan',1,2,1,1,'',0,2689815704065,'2022-01-03 17:49:58','liufeng',2689815704065,'2022-01-03 17:49:58','liufeng',0),(9346244321796,9346244317698,'9346244317698','systemUser','message.router.systemUser','/system/user','system/user/index',0,'',1,0,'','','iconfont icon-icon-',1,2,1,1,'',0,2689815704065,'2022-01-03 17:49:58','liufeng',2689815704065,'2022-01-03 17:49:58','liufeng',0),(9346244321797,0,'0','limits','message.router.limits','/limits','layout/routerView/parent',0,'',1,0,'/limits/frontEnd','','iconfont icon-quanxian',1,1,3,1,'',0,2689815704065,'2022-01-03 17:49:58','liufeng',1,'2022-01-12 18:01:04','admin',1),(9346244321798,9346244321797,'9346244321797','limitsBackEnd','message.router.limitsBackEnd','/limits/backEnd','layout/routerView/parent',0,'',1,0,'','','iconfont icon-shouye',1,2,1,1,'',0,2689815704065,'2022-01-03 17:49:58','liufeng',1,'2022-01-12 20:44:58','admin',1),(9346244321799,9346244321798,'9346244321797,9346244321798','limitsBackEndEndPage','message.router.limitsBackEndEndPage','/limits/backEnd/page','limits/backEnd/page/index',0,'',1,0,'','','iconfont icon-fuzhiyemian',1,3,1,1,'',0,2689815704065,'2022-01-03 17:49:58','liufeng',1,'2022-01-12 20:45:29','admin',0),(9346244321800,0,'0','menu','message.router.menu','/menu','layout/routerView/parent',0,'',1,0,'/menu/menu1','','iconfont icon-caidan',1,1,4,1,'',0,2689815704065,'2022-01-03 17:49:58','liufeng',1,'2022-01-12 18:01:19','admin',2),(9346244321801,9346244321800,'9346244321800','menu1','message.router.menu1','/menu/menu1','layout/routerView/parent',0,'',1,0,'/menu/menu1/menu11','','iconfont icon-caidan',1,2,1,1,'',0,2689815704065,'2022-01-03 17:49:58','liufeng',2689815704065,'2022-01-03 17:49:58','liufeng',3),(9346244321802,9346244321801,'9346244321800,9346244321801','menu11','message.router.menu11','/menu/menu1/menu11','menu/menu1/menu11/index',0,'',1,0,'','','iconfont icon-caidan',1,3,1,1,'',0,2689815704065,'2022-01-03 17:49:58','liufeng',2689815704065,'2022-01-03 17:49:58','liufeng',0),(9346244321803,9346244321801,'9346244321800,9346244321801','menu12','message.router.menu12','/menu/menu1/menu12','layout/routerView/parent',0,'',1,0,'/menu/menu1/menu12/menu121','','iconfont icon-caidan',1,3,1,1,'',0,2689815704065,'2022-01-03 17:49:58','liufeng',2689815704065,'2022-01-03 17:49:58','liufeng',2),(9346244321804,9346244321803,'9346244321800,9346244321801,9346244321803','menu121','message.router.menu121','/menu/menu1/menu12/menu121','menu/menu1/menu12/menu121/index',0,'',1,0,'','','iconfont icon-caidan',1,4,1,1,'',0,2689815704065,'2022-01-03 17:49:58','liufeng',2689815704065,'2022-01-03 17:49:58','liufeng',0),(9346244321805,9346244321803,'9346244321800,9346244321801,9346244321803','menu122','message.router.menu122','/menu/menu1/menu12/menu122','menu/menu1/menu12/menu122/index',0,'',1,0,'','','iconfont icon-caidan',1,4,1,1,'',0,2689815704065,'2022-01-03 17:49:58','liufeng',2689815704065,'2022-01-03 17:49:58','liufeng',0),(9346244321806,9346244321801,'9346244321800,9346244321801','menu13','message.router.menu13','/menu/menu1/menu13','menu/menu1/menu13/index',0,'',1,0,'','','iconfont icon-caidan',1,3,1,1,'',0,2689815704065,'2022-01-03 17:49:58','liufeng',2689815704065,'2022-01-03 17:49:58','liufeng',0),(9346244321807,9346244321800,'9346244321800','menu2','message.router.menu2','/menu/menu2','menu/menu2/index',0,'',1,0,'','','iconfont icon-caidan',1,2,1,1,'',0,2689815704065,'2022-01-03 17:49:58','liufeng',2689815704065,'2022-01-03 17:49:58','liufeng',0),(9346244321808,0,'0','funIndex','message.router.funIndex','/fun','layout/routerView/parent',0,'',1,0,'/fun/tagsView','','iconfont icon-crew_feature',1,1,5,1,'',0,2689815704065,'2022-01-03 17:49:58','liufeng',1,'2022-01-12 18:01:28','admin',1),(9346244321809,9346244321808,'9346244321808','funTagsView','message.router.funTagsView','/fun/tagsView','fun/tagsView/index',0,'',1,0,'','','elementPointer',1,2,1,1,'',0,2689815704065,'2022-01-03 17:49:58','liufeng',2689815704065,'2022-01-03 17:49:58','liufeng',0),(9346244321810,0,'0','chartIndex','message.router.chartIndex','/chart','chart/index',0,'',1,0,'','','iconfont icon-ico_shuju',1,1,6,1,'',0,2689815704065,'2022-01-03 17:49:58','liufeng',2689815704065,'2022-01-03 17:49:58','liufeng',0),(9346244321811,0,'0','personal','message.router.personal','/personal','personal/index',0,'',1,0,'','','iconfont icon-gerenzhongxin',1,1,7,1,'',0,2689815704065,'2022-01-03 17:49:58','liufeng',2689815704065,'2022-01-03 17:49:58','liufeng',0),(9346244321812,0,'0','tools','message.router.tools','/tools','tools/index',0,'',1,0,'','','iconfont icon-gongju',1,1,8,1,'',0,2689815704065,'2022-01-03 17:49:58','liufeng',2689815704065,'2022-01-03 17:49:58','liufeng',0),(9346244321813,0,'0','layoutLinkView','message.router.layoutLinkView','/link','layout/routerView/link',0,'https://element-plus.gitee.io/#/zh-CN/component/installation',0,0,'','','iconfont icon-caozuo-wailian',1,1,9,1,'',0,2689815704065,'2022-01-03 17:49:58','liufeng',2689815704065,'2022-01-03 17:49:58','liufeng',0),(9346244321814,0,'0','layoutIfameView','message.router.layoutIfameView','/iframes','layout/routerView/iframe',1,'http://localhost:8888/#/system/menu',1,0,'','','iconfont icon-neiqianshujuchucun',1,1,10,1,'',0,2689815704065,'2022-01-03 17:49:58','liufeng',2689815704065,'2022-01-03 17:49:58','liufeng',0),(11806234821121,9346244317698,'9346244317698','systemRole','message.router.systemRole','/system/role','/system/role/index',0,'',1,0,'','','iconfont icon-gerenzhongxin',1,2,11,1,'',0,1,'2022-01-10 16:39:42','admin',1,'2022-01-10 21:04:59','admin',0),(12575811940872,9346244321797,'0,9346244321797','limitsFrontEnd','message.router.limitsFrontEnd','/limits/frontEnd','layout/routerView/parent',0,'',1,0,'','','iconfont icon-shuju1',1,2,2,1,'',0,1,'2022-01-12 20:51:07','admin',1,'2022-01-12 20:51:07','admin',0),(12577548255753,12575811940872,'0,9346244321797,12575811940872','limitsFrontEndPage','message.router.limitsBackEndEndPage','/limits/frontEnd/page','limits/frontEnd/page/index',0,'',1,0,'','','iconfont icon-zhongduancanshuchaxun',1,3,2,1,'',0,1,'2022-01-12 20:58:10','admin',1,'2022-01-12 20:58:39','admin',0),(12578552869386,12575811940872,'0,9346244321797,12575811940872','limitsFrontEndBtn','message.router.limitsFrontEndBtn','/limits/frontEnd/btn','limits/frontEnd/btn/index',0,'',1,0,'','','elementPointer',1,3,2,1,'',0,1,'2022-01-12 21:02:16','admin',1,'2022-01-12 21:02:16','admin',0),(12584787636747,9346244321795,'9346244317698,9346244321795','','message.router.add','','',0,'',1,0,'','/sysMenu/post','',2,3,1,1,'',0,1,'2022-01-12 21:27:38','admin',1,'2022-01-12 21:27:38','admin',0),(12857272197633,9346244321795,'9346244317698,9346244321795','','message.router.update','','',0,'',1,0,'','/sysMenu/put','',2,3,2,1,'',0,1,'2022-01-13 15:56:22','admin',1,'2022-01-13 15:56:22','admin',0),(12858490110466,9346244321795,'9346244317698,9346244321795','','message.router.delete','','',0,'',1,0,'','/sysMenu/delete','',2,3,3,1,'',0,1,'2022-01-13 16:01:20','admin',1,'2022-01-13 16:01:20','admin',0),(12858679345667,9346244321796,'9346244317698,9346244321796','','message.router.add','','',0,'',1,0,'','/sysUser/post','',2,3,1,1,'',0,1,'2022-01-13 16:02:06','admin',1,'2022-01-13 16:02:06','admin',0),(12858733580804,9346244321796,'9346244317698,9346244321796','','message.router.update','','',0,'',1,0,'','/sysUser/put','',2,3,2,1,'',0,1,'2022-01-13 16:02:19','admin',1,'2022-01-13 16:02:19','admin',0),(12858780197381,9346244321796,'9346244317698,9346244321796','','message.router.delete','','',0,'',1,0,'','/sysUser/delete','',2,3,3,1,'',0,1,'2022-01-13 16:02:31','admin',1,'2022-01-13 16:02:31','admin',0),(12859214053894,11806234821121,'9346244317698,11806234821121','','message.router.add','','',0,'',1,0,'','/sysRole/post','',2,3,1,1,'',0,1,'2022-01-13 16:04:17','admin',1,'2022-01-13 16:04:17','admin',0),(12859259920903,11806234821121,'9346244317698,11806234821121','','message.router.update','','',0,'',1,0,'','/sysRole/put','',2,3,2,1,'',0,1,'2022-01-13 16:04:28','admin',1,'2022-01-13 16:04:28','admin',0),(12859299734024,11806234821121,'9346244317698,11806234821121','','message.router.delete','','',0,'',1,0,'','/sysRole/delete','',2,3,3,1,'',0,1,'2022-01-13 16:04:37','admin',1,'2022-01-13 16:04:37','admin',0),(14610968408611,9346244317698,'0,9346244317698','systemLog','message.router.systemLog','/system/log','/system/log/index',0,'',1,0,'','','elementEdit',1,2,4,1,'',0,1,'2022-01-18 14:52:11','admin',1,'2022-01-18 14:52:11','admin',0),(14623462507035,9346244317698,'0,9346244317698','systemDict','message.router.systemDict','/system/dict','/system/dict/index',0,'',1,0,'','','iconfont icon-fuwenbenkuang',1,2,5,1,'',0,1,'2022-01-18 15:43:01','admin',1,'2022-01-18 18:28:58','admin',0);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '名称',
  `description` varchar(32) NOT NULL DEFAULT '' COMMENT '描述',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 （0 未删除 , 1 删除）',
  `create_id` bigint DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人姓名',
  `update_id` bigint DEFAULT '0' COMMENT '修改人id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_name` varchar(32) DEFAULT NULL COMMENT '修改人姓名',
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
INSERT INTO `sys_roles_menus` VALUES (12212347052560,12169281565187,9346244321810),(12212355723793,12169281565187,9346244321810),(12225047233025,12169281565187,9346244317698),(12225047233027,12169281565187,9346244321796),(12225047233028,12169281565187,11806234821121),(12225201803793,2,9346244321809),(12225255096850,4,9346244321810),(12225278030357,4,9346244321813),(12225308545564,5,9346244321806),(12225316360734,5,9346244321810),(12226070094368,6,9346244321809),(12226074346017,6,9346244321810),(12226078994978,6,9346244317697),(12226083615267,6,9346244321811),(12226087580196,6,9346244321812),(12226091618853,6,9346244321813),(12226628190758,4,9346244321801),(12226628190759,4,9346244321802),(12226628190763,4,9346244321806),(12226628190764,4,9346244321800),(12226703266349,12169281565187,9346244317697),(12226706965038,12169281565187,9346244321795),(12226755433007,5,9346244317697),(12226776240688,4,9346244317697),(12226776240689,4,9346244321807),(12228731347511,2,9346244317697),(12228741718585,2,9346244321810),(12229097603644,5,9346244321805),(12229171192381,5,9346244321808),(12229171192382,5,9346244321809),(12232164073999,6,9346244321808),(12239839404567,2,9346244321808),(12239955698202,6,9346244321802),(12239955698203,6,9346244321800),(12239955698204,6,9346244321801),(12239967875613,6,9346244321804),(12239967875614,6,9346244321803),(12239976505887,6,9346244321807),(12472718193165,1,9346244317697),(12472727925269,1,9346244321800),(12472727925270,1,9346244321801),(12472727925271,1,9346244321802),(12472727925272,1,9346244321803),(12472727925273,1,9346244321804),(12472727925274,1,9346244321805),(12472727925275,1,9346244321806),(12472727925276,1,9346244321807),(12472730841629,1,9346244321808),(12472730841630,1,9346244321809),(12472733200927,1,9346244321810),(12472736154144,1,9346244321811),(12472739168801,1,9346244321812),(12472742113826,1,9346244321813),(12472745722403,1,9346244321814),(12535084048898,5,9346244321800),(12535084048899,5,9346244321801),(12535084048900,5,9346244321802),(12535084048901,5,9346244321803),(12535084048902,5,9346244321804),(12535084048903,5,9346244321807),(12942637576705,1,9346244317698),(12942637576706,1,9346244321795),(12942637576707,1,12584787636747),(12942637576708,1,12857272197633),(12942637576709,1,12858490110466),(12942637576710,1,9346244321796),(12942637576711,1,12858679345667),(12942637576712,1,12858733580804),(12942637576713,1,12858780197381),(12942637576714,1,11806234821121),(12942637576715,1,12859214053894),(12942637576716,1,12859259920903),(12942637576717,1,12859299734024);
/*!40000 ALTER TABLE `sys_roles_menus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '姓名',
  `password` varchar(64) NOT NULL DEFAULT '' COMMENT '密码',
  `phone` varchar(16) NOT NULL DEFAULT '' COMMENT '手机',
  `email` varchar(64) DEFAULT '' COMMENT '邮箱',
  `head_image` varchar(160) DEFAULT '' COMMENT '头像',
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
  `create_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人姓名',
  `update_id` bigint DEFAULT '0' COMMENT '修改人id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_name` varchar(32) DEFAULT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'admin','$2a$10$ZkNzjcQMZfdpRJ1z04l4r.G4g2/0v7MPSnYqIggK57dOtSPOVEXse','18999999999','1@qq.com','/public/photo.jpg',3,1,1263045492112351233,0,1,0,'2021-06-17 16:49:28',1,1,0,0,'2020-05-21 16:36:17','admin',1,'2021-06-17 16:49:28','admin'),(2689815704065,'liufeng','$2a$10$ZkNzjcQMZfdpRJ1z04l4r.G4g2/0v7MPSnYqIggK57dOtSPOVEXse','18633333333','123456@qq.com','/public/photo.jpg',1,1,NULL,NULL,1,0,NULL,0,0,0,0,'2021-12-15 22:24:53','',1,'2022-01-10 15:42:10','admin'),(9687140418049,'zhangs','','18963636363','123@163.com','',1,1,NULL,NULL,1,0,NULL,0,0,0,1,'2022-01-04 16:57:05','admin',1,'2022-01-04 16:57:05','admin'),(9688243069442,'lis','$2a$10$RFoYvXE.5W6kAivraWEyi.Y47Xda/GzkrOSMbYlkRzclrwCiF3JDe','18933333333','123456@163.com','123456',1,1,NULL,NULL,1,0,NULL,0,0,0,1,'2022-01-04 17:01:34','admin',1,'2022-01-18 14:37:54','admin'),(9688674755075,'wangwu','123456','18639948888','1@qq.com','',1,1,NULL,NULL,1,0,NULL,0,0,0,1,'2022-01-04 17:03:19','admin',1,'2022-01-05 15:28:27','admin'),(9688890032644,'zhaoliu','66666666','18977777777','123@163.com','2',1,1,NULL,NULL,1,0,NULL,0,0,0,1,'2022-01-04 17:04:12','admin',1,'2022-01-05 15:29:08','admin'),(9692978156033,'zhaoyun','123456798','18788888888','123456798@qq.com','3',1,1,NULL,NULL,1,0,NULL,0,0,0,1,'2022-01-04 17:20:50','admin',1,'2022-01-05 15:29:54','admin'),(9693739528705,'likui456','123124','18799999999','123456@163.com','3',1,1,NULL,NULL,1,0,NULL,0,0,0,1,'2022-01-04 17:23:56','admin',2689815704065,'2022-01-13 21:58:55','liufeng'),(10007044579841,'刘备','123456','13655555555','123@qq.com','',1,1,NULL,NULL,1,0,NULL,0,0,0,1,'2022-01-05 14:38:46','admin',1,'2022-01-05 15:32:08','admin'),(10007155495426,'张飞','123456','13666666666','123@qq.com','',1,0,NULL,NULL,1,0,NULL,0,0,0,1,'2022-01-05 14:39:13','admin',1,'2022-01-05 14:39:13','admin'),(10007217418755,'关羽','123456','13677777777','123@qq.com','',1,1,NULL,NULL,1,0,NULL,0,0,0,1,'2022-01-05 14:39:28','admin',1,'2022-01-05 15:33:20','admin'),(10023037043204,'历史','1','18966666666','123@qq.com','1',1,1,NULL,NULL,1,0,NULL,0,0,0,1,'2022-01-05 15:43:51','admin',1,'2022-01-06 14:06:59','admin'),(10027981636101,'','','','','',3,0,NULL,NULL,1,0,NULL,0,0,1,1,'2022-01-05 16:03:58','admin',1,'2022-01-06 14:05:24','admin'),(10037252731398,'','','','','',1,0,NULL,NULL,1,0,NULL,0,0,1,1,'2022-01-05 16:41:41','admin',1,'2022-01-06 14:05:22','admin'),(10041126011399,'','','','','',1,0,NULL,NULL,1,0,NULL,0,0,1,1,'2022-01-05 16:57:27','admin',1,'2022-01-06 14:06:13','admin'),(10043190067720,'','','','','',1,0,NULL,NULL,1,0,NULL,0,0,1,1,'2022-01-05 17:05:51','admin',1,'2022-01-06 14:05:18','admin'),(10043854172681,'','','','','',1,0,NULL,NULL,1,0,NULL,0,0,1,1,'2022-01-05 17:08:33','admin',1,'2022-01-06 14:05:07','admin'),(10045006045706,'1','','1','1','1',1,0,NULL,NULL,1,0,NULL,0,0,1,1,'2022-01-05 17:13:14','admin',1,'2022-01-06 15:27:37','admin'),(10045461955083,'','','','','',1,0,NULL,NULL,1,0,NULL,0,0,1,1,'2022-01-05 17:15:05','admin',1,'2022-01-06 14:05:05','admin'),(10045894586892,'','','','','',1,0,NULL,NULL,1,0,NULL,0,0,1,1,'2022-01-05 17:16:51','admin',1,'2022-01-06 14:05:02','admin'),(10046522716685,'','','','','',1,0,NULL,NULL,1,0,NULL,0,0,1,1,'2022-01-05 17:19:24','admin',1,'2022-01-06 14:04:55','admin'),(10046997676558,'','','','','',1,0,NULL,NULL,1,0,NULL,0,0,1,1,'2022-01-05 17:21:20','admin',1,'2022-01-06 14:01:47','admin'),(10654762189313,'','','','','',3,0,NULL,NULL,1,0,NULL,0,0,1,1,'2022-01-07 10:34:20','admin',1,'2022-01-07 11:10:05','admin'),(10655326458370,'','','','','',3,0,NULL,NULL,1,0,NULL,0,0,1,1,'2022-01-07 10:36:38','admin',1,'2022-01-07 11:54:16','admin'),(10663488446979,'zhangsan','1213243','12312412322','123456@163.com','',1,1,NULL,NULL,1,0,NULL,0,0,0,1,'2022-01-07 11:09:51','admin',1,'2022-01-07 11:09:51','admin'),(14607875318303,'加密123','$2a$10$MdQ/p6XLUF5sTN0wdYKfDOUODIVj3Le.HFhsxR0SNLnZotV5wtwHC','13656565656','123@163.com','',0,1,NULL,NULL,1,0,NULL,0,0,0,1,'2022-01-18 14:39:36','admin',1,'2022-01-19 13:42:12','admin');
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
-- Dumping routines for database 'f-boot'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-24 13:47:43
