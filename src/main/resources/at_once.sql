/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50527
 Source Host           : localhost:3306
 Source Schema         : at_once

 Target Server Type    : MySQL
 Target Server Version : 50527
 File Encoding         : 65001

 Date: 05/08/2021 19:05:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for album
-- ----------------------------
DROP TABLE IF EXISTS `album`;
CREATE TABLE `album`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cover_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creat_time` datetime NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_time` datetime NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `openid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of album
-- ----------------------------
INSERT INTO `album` VALUES (1, '///', '2020-02-29 17:16:47', '修改2', '2020-02-27 14:31:03', '第1个测试相册', '123456', 2);
INSERT INTO `album` VALUES (4, 'asdfghjkl', '2020-02-28 23:00:04', NULL, '2020-02-28 23:00:04', NULL, NULL, 0);
INSERT INTO `album` VALUES (9, 'AAAAAAAAAA', '2020-02-29 17:54:26', '修改2', '2020-02-29 17:54:26', '第1个测试相册ahbsd', '12345', 2);
INSERT INTO `album` VALUES (11, '123456', '2020-03-06 09:56:16', '啦啦啦啦', '2020-03-06 09:56:16', '第一个', '12345', 0);
INSERT INTO `album` VALUES (12, '123456', '2020-03-06 09:56:16', '这是我在小程序的第一个相册', '2020-03-06 09:56:16', '第一个相册', '12345', 0);
INSERT INTO `album` VALUES (13, '123456', '2020-03-06 09:56:41', '这是我在小程序的第一个相册', '2020-03-06 09:56:41', '第一个相册', '12345', 0);
INSERT INTO `album` VALUES (14, '123456', '2020-03-06 10:00:37', '我在小程序的第一个相册', '2020-03-06 10:00:37', '第4个相册', '12345', 0);
INSERT INTO `album` VALUES (15, '123456', '2020-03-06 09:58:48', '啦啦啦啦', '2020-03-06 09:58:48', '第2个', '12345', 0);
INSERT INTO `album` VALUES (16, '123456', '2020-03-06 10:01:15', '我在小程序的第一个相册', '2020-03-06 10:01:15', '第5个相册', '12345', 0);
INSERT INTO `album` VALUES (17, '/default.jpg', '2020-03-06 10:22:26', '啦啦啦啦', '2020-03-06 10:22:26', '第5个', '12345', 0);
INSERT INTO `album` VALUES (18, '/default.jpg', '2020-03-06 10:07:30', '啦啦啦啦', '2020-03-06 10:07:30', '第5个', '12345', 0);
INSERT INTO `album` VALUES (19, '123456', '2020-03-06 10:03:31', '我在小程序的第一个相册', '2020-03-06 10:03:31', '第4个相册', '12345', 0);
INSERT INTO `album` VALUES (20, '/default.jpg', '2020-04-22 21:45:44', '这是我在小程序的第一个相册', '2020-04-22 21:45:44', '第一个相册', '123456', 0);
INSERT INTO `album` VALUES (21, '/default.jpg', '2020-04-22 21:45:44', '这是我在小程序的第一个相册', '2020-04-22 21:45:44', '第一个相册', '123456', 0);
INSERT INTO `album` VALUES (22, '/default.jpg', '2020-04-22 21:45:44', '这是我在小程序的第一个相册', '2020-04-22 21:45:44', '第一个相册', '123456', 0);
INSERT INTO `album` VALUES (23, '/default.jpg', '2020-04-26 20:54:56', NULL, '2020-04-26 20:54:56', NULL, NULL, 0);
INSERT INTO `album` VALUES (24, '/default.jpg', '2020-04-26 20:54:56', NULL, '2020-04-26 20:54:56', NULL, NULL, 0);
INSERT INTO `album` VALUES (25, '/default.jpg', '2020-04-26 20:54:56', NULL, '2020-04-26 20:54:56', NULL, NULL, 0);
INSERT INTO `album` VALUES (26, '/default.jpg', '2020-04-26 21:00:37', NULL, '2020-04-26 21:00:37', NULL, NULL, 0);
INSERT INTO `album` VALUES (27, '/default.jpg', '2020-04-26 21:06:08', NULL, '2020-04-26 21:06:08', NULL, NULL, 0);
INSERT INTO `album` VALUES (28, '/default.jpg', '2020-04-26 21:12:41', NULL, '2020-04-26 21:12:41', NULL, NULL, 0);
INSERT INTO `album` VALUES (29, '/default.jpg', '2020-04-26 21:12:41', NULL, '2020-04-26 21:12:41', NULL, NULL, 0);
INSERT INTO `album` VALUES (30, '/default.jpg', '2020-04-28 23:38:53', NULL, '2020-04-28 23:38:53', NULL, NULL, 0);
INSERT INTO `album` VALUES (31, '/default.jpg', '2020-04-28 23:38:55', NULL, '2020-04-28 23:38:55', NULL, NULL, 0);
INSERT INTO `album` VALUES (32, '/default.jpg', '2020-04-28 23:38:56', NULL, '2020-04-28 23:38:56', NULL, NULL, 0);
INSERT INTO `album` VALUES (33, '/default.jpg', '2020-04-28 23:41:41', NULL, '2020-04-28 23:41:41', NULL, NULL, 0);
INSERT INTO `album` VALUES (34, '/default.jpg', '2020-04-29 17:13:31', 'aaa', '2020-04-29 17:13:31', 'undefined', NULL, 0);
INSERT INTO `album` VALUES (35, '/default.jpg', '2020-04-29 17:14:07', 'aaa', '2020-04-29 17:14:07', 'undefined', NULL, 0);
INSERT INTO `album` VALUES (36, '/default.jpg', '2020-04-29 17:16:37', 'aaaaaa', '2020-04-29 17:16:37', 'undefined', NULL, 0);
INSERT INTO `album` VALUES (37, '/default.jpg', '2020-04-29 17:23:08', 'undefined', '2020-04-29 17:23:08', 'undefined', NULL, 0);
INSERT INTO `album` VALUES (38, '/default.jpg', '2020-04-29 17:42:37', 'sss', '2020-04-29 17:42:37', 'aaa', 'odsKv4icKfjXTQg6ehAYrV7nkR9w', 0);
INSERT INTO `album` VALUES (39, '/default.jpg', '2020-04-29 17:48:56', '哈哈哈', '2020-04-29 17:48:56', '测试', 'odsKv4icKfjXTQg6ehAYrV7nkR9w', 0);
INSERT INTO `album` VALUES (40, '/default.jpg', '2020-04-29 18:52:24', '123', '2020-04-29 18:52:24', '321', 'odsKv4icKfjXTQg6ehAYrV7nkR9w', 0);
INSERT INTO `album` VALUES (41, '/default.jpg', '2020-04-29 19:00:54', 'sadad', '2020-04-29 19:00:54', 'as', 'odsKv4icKfjXTQg6ehAYrV7nkR9w', 0);
INSERT INTO `album` VALUES (42, '/default.jpg', '2020-05-02 21:43:40', 'bkbcxks', '2020-05-02 21:43:40', '11', 'odsKv4icKfjXTQg6ehAYrV7nkR9w', 0);

-- ----------------------------
-- Table structure for notefolder
-- ----------------------------
DROP TABLE IF EXISTS `notefolder`;
CREATE TABLE `notefolder`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creat_time` datetime NULL DEFAULT NULL,
  `last_time` datetime NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` int(11) NULL DEFAULT NULL,
  `openid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of notefolder
-- ----------------------------
INSERT INTO `notefolder` VALUES (1, '2020-05-02 22:22:06', '2021-07-12 17:58:59', '未分类', 3, 'odsKv4sBqUMxMjzUcYxZJN7mr0C8', NULL);
INSERT INTO `notefolder` VALUES (2, '2021-07-12 11:13:03', '2021-07-12 16:26:52', '文件夹', 2, 'odsKv4sBqUMxMjzUcYxZJN7mr0C8', '123');
INSERT INTO `notefolder` VALUES (4, '2021-07-12 16:26:46', '2021-07-12 16:26:46', '哈哈', 0, 'odsKv4sBqUMxMjzUcYxZJN7mr0C8', NULL);

-- ----------------------------
-- Table structure for notepad
-- ----------------------------
DROP TABLE IF EXISTS `notepad`;
CREATE TABLE `notepad`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classify` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creat_time` datetime NULL DEFAULT NULL,
  `delta` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `html` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_time` datetime NULL DEFAULT NULL,
  `openid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `folderid` int(11) NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of notepad
-- ----------------------------
INSERT INTO `notepad` VALUES (1, '文件夹2', '2020-03-04 22:08:41', '[{\"insert\":\"这是我的第一篇文章\\n\"},{\"attributes\":{\"bold\":\"strong\"},\"insert\":\"啦啦啦啦\"},{\"insert\":\"\\n\"}]', '<p>这是我的第一篇文章</p><p><strong>啦啦啦啦</strong></p>', '2021-07-12 15:42:51', 'odsKv4sBqUMxMjzUcYxZJN7mr0C8', 1, '这是我的第一篇文章\n啦啦啦啦\n', '第一篇文章', 2, '123');
INSERT INTO `notepad` VALUES (2, '文件夹2', '2020-03-04 22:42:38', '[{\"insert\":\"啦啦啦啦啦\\n\"}]', '<p>啦啦啦啦啦</p>', '2021-07-12 17:57:17', 'odsKv4sBqUMxMjzUcYxZJN7mr0C8', 1, '啦啦啦啦啦\n', '第2篇文章', 2, '111');
INSERT INTO `notepad` VALUES (5, '未分类', '2020-04-29 21:21:32', '[{\"insert\":\"这是在小程序提交的第一次测试，希望能成功。加油，奥里给！！！\\n\"},{\"attributes\":{\"bold\":\"strong\"},\"insert\":\"哈哈哈\"},{\"insert\":\"\\n\"}]', '<p>这是在小程序提交的第一次测试，希望能成功。加油，奥里给！！！</p><p><strong>哈哈哈</strong></p>', '2021-07-05 18:03:13', 'odsKv4sBqUMxMjzUcYxZJN7mr0C8', 1, '这是在小程序提交的第一次测试，希望能成功。加油，奥里给！！！\n哈哈哈\n', '第一次测试', 1, '123');
INSERT INTO `notepad` VALUES (6, '未分类', '2020-04-29 21:26:27', '[{\"insert\":\"这是我的第一篇文章\\n\"},{\"attributes\":{\"bold\":\"strong\"},\"insert\":\"啦啦啦啦\"},{\"insert\":\"\\n\"},{\"attributes\":{\"bold\":\"strong\"},\"insert\":\"asjh \"},{\"insert\":\"\\nDSADF\"},{\"attributes\":{\"header\":1},\"insert\":\"\\n\"}]', '<p wx:nodeid=\"55\">这是我的第一篇文章</p><p wx:nodeid=\"58\"><strong wx:nodeid=\"59\">啦啦啦啦</strong></p><p wx:nodeid=\"64\"><strong wx:nodeid=\"65\">asjh </strong></p><h1 wx:nodeid=\"81\">DSADF</h1>', '2021-07-12 16:37:03', 'odsKv4sBqUMxMjzUcYxZJN7mr0C8', 1, '这是我的第一篇文章\n啦啦啦啦\nasjh \nDSADF\n', '第一篇文章', 1, NULL);
INSERT INTO `notepad` VALUES (7, '回收站', '2020-05-01 13:08:15', '[{\"insert\":\"asd\\n\"}]', '<p>asd</p>', '2021-07-12 14:33:00', 'odsKv4sBqUMxMjzUcYxZJN7mr0C8', 0, 'asd\n', 'ddd', 0, '123');
INSERT INTO `notepad` VALUES (8, '回收站', '2020-05-01 13:34:52', '[{\"insert\":\"吧v村法国\\n\"}]', '<p>吧v村法国</p>', '2021-07-12 14:39:13', 'odsKv4sBqUMxMjzUcYxZJN7mr0C8', 0, '吧v村法国\n', '啊啊啊', 0, '123');
INSERT INTO `notepad` VALUES (9, '回收站', '2020-05-01 14:14:36', '[{\"insert\":\"自行车v不那么，\\n\"}]', '<p>自行车v不那么，</p>', '2021-07-12 14:40:17', 'odsKv4sBqUMxMjzUcYxZJN7mr0C8', 0, '自行车v不那么，\n', 'ccc', 0, NULL);
INSERT INTO `notepad` VALUES (10, '未分类', '2021-07-12 17:58:59', '[{\"insert\":\"啊哈哈哈\\n改bug快疯了\\n\"}]', '<p>啊哈哈哈</p><p>改bug快疯了</p>', '2021-07-12 17:58:59', 'odsKv4sBqUMxMjzUcYxZJN7mr0C8', 1, '啊哈哈哈\n改bug快疯了\n', '测试文章', 1, NULL);

-- ----------------------------
-- Table structure for photo
-- ----------------------------
DROP TABLE IF EXISTS `photo`;
CREATE TABLE `photo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classify` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creat_time` datetime NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `img_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_time` datetime NULL DEFAULT NULL,
  `openid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `album_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of photo
-- ----------------------------
INSERT INTO `photo` VALUES (1, '第1个测试相册ahbs', '2020-02-27 15:59:40', 'test', '/test.jpg', '2020-04-22 20:35:08', '123456', 1, 9);
INSERT INTO `photo` VALUES (2, '第1个测试相册ahbs', '2020-02-27 15:59:40', 'test2', '/', '2020-04-22 20:35:10', '123456', 1, 9);
INSERT INTO `photo` VALUES (3, '第1个测试相册ahbs', '2020-02-27 20:54:41', 'fgd', '/', '2020-04-10 21:02:12', '123456', 0, 0);
INSERT INTO `photo` VALUES (4, '第1个测试相册ahbs', '2020-03-04 14:56:15', '啦啦啦啦', '/test.jpg', '2020-04-10 21:02:12', '123456', 0, 0);
INSERT INTO `photo` VALUES (5, '第1个测试相册', '2020-04-10 21:14:59', 'test', '/', '2020-04-10 21:14:59', '123456', 1, 1);
INSERT INTO `photo` VALUES (6, '第1个测试相册', '2020-04-10 21:14:59', 'test2', '/', '2020-04-10 21:14:59', '123456', 1, 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `openid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatarurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creat_time` datetime NULL DEFAULT NULL,
  `gender` int(11) NULL DEFAULT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`openid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for user_pc
-- ----------------------------
DROP TABLE IF EXISTS `user_pc`;
CREATE TABLE `user_pc`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `avatarurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creat_time` datetime NULL DEFAULT NULL,
  `openid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_pc
-- ----------------------------
INSERT INTO `user_pc` VALUES (1, '//', '2020-03-14 22:26:29', '123456', '123456', 'zhangsan');

-- ----------------------------
-- Table structure for user_wx
-- ----------------------------
DROP TABLE IF EXISTS `user_wx`;
CREATE TABLE `user_wx`  (
  `openid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatarurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creat_time` datetime NULL DEFAULT NULL,
  `gender` int(11) NULL DEFAULT NULL COMMENT '用户性别，0:未知;1:男性;2:女性;',
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`openid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_wx
-- ----------------------------
INSERT INTO `user_wx` VALUES ('odsKv4icKfjXTQg6ehAYrV7nkR9w', 'China/Henan/Zhengzhou', 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eosZcrHt4icWfq9gicdt60RqBTE9MFGe0epOCsWpVibFsKsK6nDEwsIfOq24Fjib8s7UmuXVsVRJRGOTA/132', NULL, 2, 'Akashic');
INSERT INTO `user_wx` VALUES ('odsKv4sBqUMxMjzUcYxZJN7mr0C8', '//', 'https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132', NULL, 0, '微信用户');
INSERT INTO `user_wx` VALUES ('oTOo346itIOj1Z8Jk2kATZMQh4Tc', 'China/Henan/Zhoukou', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIumVBWFYf7anbalxNFy6C8aSzz61jibaPY7mzO49iacTBBTziaPErh7iadzJIz2wd5qSbWGshMBXXFLA/132', NULL, 1, '无名');
INSERT INTO `user_wx` VALUES ('oTOo346skb33AUkaaqP56od3UHs0', 'China/Henan/Zhengzhou', 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eotdV9ib3sJ7xNjy8RNKDzUL7E2AnEkl54dBSLvvjljH8AcWAGoqSCT5Jka7xnIib3IKjZqO98ticbEA/132', NULL, 2, 'Akashic');

SET FOREIGN_KEY_CHECKS = 1;
