/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50544
Source Host           : localhost:3306
Source Database       : ml

Target Server Type    : MYSQL
Target Server Version : 50544
File Encoding         : 65001

Date: 2018-11-22 11:22:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for watermelondataset2
-- ----------------------------
DROP TABLE IF EXISTS `watermelondataset2`;
CREATE TABLE `watermelondataset2` (
  `id` int(11) NOT NULL,
  `colourAndLustre` varchar(255) DEFAULT NULL,
  `rootAndBase` varchar(255) DEFAULT NULL,
  `stroke` varchar(255) DEFAULT NULL,
  `venation` varchar(255) DEFAULT NULL,
  `umbilicalRegion` varchar(255) DEFAULT NULL,
  `touch` varchar(255) DEFAULT NULL,
  `isGood` varchar(255) DEFAULT NULL,
  `continuousAttr` varchar(255) DEFAULT NULL,
  `discreteAttr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of watermelondataset2
-- ----------------------------
INSERT INTO `watermelondataset2` VALUES ('1', '青绿', '蜷缩', '浊响', '清晰', '凹陷', '硬滑', '是', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2` VALUES ('2', '乌黑', '蜷缩', '沉闷', '清晰', '凹陷', '硬滑', '是', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2` VALUES ('3', '乌黑', '蜷缩', '浊响', '清晰', '凹陷', '硬滑', '是', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2` VALUES ('4', '青绿', '蜷缩', '沉闷', '清晰', '凹陷', '硬滑', '是', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2` VALUES ('5', '浅白', '蜷缩', '浊响', '清晰', '凹陷', '硬滑', '是', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2` VALUES ('6', '青绿', '稍蜷', '浊响', '清晰', '稍凹', '软粘', '是', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2` VALUES ('7', '乌黑', '稍蜷', '浊响', '稍糊', '稍凹', '软粘', '是', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2` VALUES ('8', '乌黑', '稍蜷', '浊响', '清晰', '稍凹', '硬滑', '是', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2` VALUES ('9', '乌黑', '稍蜷', '沉闷', '稍糊', '稍凹', '硬滑', '否', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2` VALUES ('10', '青绿', '硬挺', '清脆', '清晰', '平坦', '软粘', '否', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2` VALUES ('11', '浅白', '硬挺', '清脆', '模糊', '平坦', '硬滑', '否', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2` VALUES ('12', '浅白', '蜷缩', '浊响', '模糊', '平坦', '软粘', '否', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2` VALUES ('13', '青绿', '稍蜷', '浊响', '稍糊', '凹陷', '硬滑', '否', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2` VALUES ('14', '浅白', '稍蜷', '沉闷', '稍糊', '凹陷', '硬滑', '否', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2` VALUES ('15', '乌黑', '稍蜷', '浊响', '清晰', '稍凹', '软粘', '否', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2` VALUES ('16', '浅白', '蜷缩', '浊响', '模糊', '平坦', '硬滑', '否', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2` VALUES ('17', '青绿', '蜷缩', '沉闷', '稍糊', '稍凹', '硬滑', '否', null, '色泽,根蒂,敲声,纹理,脐部,触感');

-- ----------------------------
-- Table structure for watermelondataset2a
-- ----------------------------
DROP TABLE IF EXISTS `watermelondataset2a`;
CREATE TABLE `watermelondataset2a` (
  `id` int(11) NOT NULL,
  `colourAndLustre` varchar(255) DEFAULT NULL,
  `rootAndBase` varchar(255) DEFAULT NULL,
  `stroke` varchar(255) DEFAULT NULL,
  `touch` varchar(255) DEFAULT NULL,
  `venation` varchar(255) DEFAULT NULL,
  `umbilicalRegion` varchar(255) DEFAULT NULL,
  `isGood` varchar(255) DEFAULT NULL,
  `continuousAttr` varchar(255) DEFAULT NULL,
  `discreteAttr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of watermelondataset2a
-- ----------------------------
INSERT INTO `watermelondataset2a` VALUES ('1', '', '蜷缩', '浊响', '硬滑', '清晰', '凹陷', '是', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2a` VALUES ('2', '乌黑', '蜷缩', '沉闷', '', '清晰', '凹陷', '是', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2a` VALUES ('3', '乌黑', '蜷缩', '', '硬滑', '清晰', '凹陷', '是', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2a` VALUES ('4', '青绿', '蜷缩', '沉闷', '硬滑', '清晰', '凹陷', '是', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2a` VALUES ('5', '', '蜷缩', '浊响', '硬滑', '清晰', '凹陷', '是', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2a` VALUES ('6', '青绿', '稍蜷', '浊响', '软粘', '清晰', '', '是', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2a` VALUES ('7', '乌黑', '稍蜷', '浊响', '软粘', '稍糊', '稍凹', '是', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2a` VALUES ('8', '乌黑', '稍蜷', '浊响', '硬滑', '', '稍凹', '是', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2a` VALUES ('9', '乌黑', '', '沉闷', '硬滑', '稍糊', '稍凹', '否', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2a` VALUES ('10', '青绿', '硬挺', '清脆', '软粘', '', '平坦', '否', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2a` VALUES ('11', '浅白', '硬挺', '清脆', '', '模糊', '平坦', '否', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2a` VALUES ('12', '浅白', '蜷缩', '', '软粘', '模糊', '平坦', '否', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2a` VALUES ('13', '', '稍蜷', '浊响', '硬滑', '稍糊', '凹陷', '否', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2a` VALUES ('14', '浅白', '稍蜷', '沉闷', '硬滑', '稍糊', '凹陷', '否', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2a` VALUES ('15', '乌黑', '稍蜷', '浊响', '软粘', '清晰', '', '否', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2a` VALUES ('16', '浅白', '蜷缩', '浊响', '硬滑', '模糊', '平坦', '否', null, '色泽,根蒂,敲声,纹理,脐部,触感');
INSERT INTO `watermelondataset2a` VALUES ('17', '青绿', '', '沉闷', '硬滑', '稍糊', '稍凹', '否', null, '色泽,根蒂,敲声,纹理,脐部,触感');

-- ----------------------------
-- Table structure for watermelondataset3
-- ----------------------------
DROP TABLE IF EXISTS `watermelondataset3`;
CREATE TABLE `watermelondataset3` (
  `id` int(11) NOT NULL,
  `colourAndLustre` varchar(255) DEFAULT NULL,
  `rootAndBase` varchar(255) DEFAULT NULL,
  `stroke` varchar(255) DEFAULT NULL,
  `venation` varchar(255) DEFAULT NULL,
  `umbilicalRegion` varchar(255) DEFAULT NULL,
  `touch` varchar(255) DEFAULT NULL,
  `density` double DEFAULT NULL,
  `sugarContent` double DEFAULT NULL,
  `isGood` varchar(255) DEFAULT NULL,
  `discreteAttr` varchar(255) DEFAULT NULL,
  `continuousAttr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of watermelondataset3
-- ----------------------------
INSERT INTO `watermelondataset3` VALUES ('1', '青绿', '蜷缩', '浊响', '清晰', '凹陷', '硬滑', '0.697', '0.46', '是', '色泽,根蒂,敲声,纹理,脐部,触感', '密度,含糖率');
INSERT INTO `watermelondataset3` VALUES ('2', '乌黑', '蜷缩', '沉闷', '清晰', '凹陷', '硬滑', '0.774', '0.376', '是', '色泽,根蒂,敲声,纹理,脐部,触感', '密度,含糖率');
INSERT INTO `watermelondataset3` VALUES ('3', '乌黑', '蜷缩', '浊响', '清晰', '凹陷', '硬滑', '0.634', '0.264', '是', '色泽,根蒂,敲声,纹理,脐部,触感', '密度,含糖率');
INSERT INTO `watermelondataset3` VALUES ('4', '青绿', '蜷缩', '沉闷', '清晰', '凹陷', '硬滑', '0.608', '0.318', '是', '色泽,根蒂,敲声,纹理,脐部,触感', '密度,含糖率');
INSERT INTO `watermelondataset3` VALUES ('5', '浅白', '蜷缩', '浊响', '清晰', '凹陷', '硬滑', '0.556', '0.215', '是', '色泽,根蒂,敲声,纹理,脐部,触感', '密度,含糖率');
INSERT INTO `watermelondataset3` VALUES ('6', '青绿', '稍蜷', '浊响', '清晰', '稍凹', '软粘', '0.403', '0.237', '是', '色泽,根蒂,敲声,纹理,脐部,触感', '密度,含糖率');
INSERT INTO `watermelondataset3` VALUES ('7', '乌黑', '稍蜷', '浊响', '稍糊', '稍凹', '软粘', '0.481', '0.149', '是', '色泽,根蒂,敲声,纹理,脐部,触感', '密度,含糖率');
INSERT INTO `watermelondataset3` VALUES ('8', '乌黑', '稍蜷', '浊响', '清晰', '稍凹', '硬滑', '0.437', '0.211', '是', '色泽,根蒂,敲声,纹理,脐部,触感', '密度,含糖率');
INSERT INTO `watermelondataset3` VALUES ('9', '乌黑', '稍蜷', '沉闷', '稍糊', '稍凹', '硬滑', '0.666', '0.091', '否', '色泽,根蒂,敲声,纹理,脐部,触感', '密度,含糖率');
INSERT INTO `watermelondataset3` VALUES ('10', '青绿', '硬挺', '清脆', '清晰', '平坦', '软粘', '0.243', '0.267', '否', '色泽,根蒂,敲声,纹理,脐部,触感', '密度,含糖率');
INSERT INTO `watermelondataset3` VALUES ('11', '浅白', '硬挺', '清脆', '模糊', '平坦', '硬滑', '0.245', '0.057', '否', '色泽,根蒂,敲声,纹理,脐部,触感', '密度,含糖率');
INSERT INTO `watermelondataset3` VALUES ('12', '浅白', '蜷缩', '浊响', '模糊', '平坦', '软粘', '0.343', '0.099', '否', '色泽,根蒂,敲声,纹理,脐部,触感', '密度,含糖率');
INSERT INTO `watermelondataset3` VALUES ('13', '青绿', '稍蜷', '浊响', '稍糊', '凹陷', '硬滑', '0.639', '0.161', '否', '色泽,根蒂,敲声,纹理,脐部,触感', '密度,含糖率');
INSERT INTO `watermelondataset3` VALUES ('14', '浅白', '稍蜷', '沉闷', '稍糊', '凹陷', '硬滑', '0.657', '0.198', '否', '色泽,根蒂,敲声,纹理,脐部,触感', '密度,含糖率');
INSERT INTO `watermelondataset3` VALUES ('15', '乌黑', '稍蜷', '浊响', '清晰', '稍凹', '软粘', '0.36', '0.37', '否', '色泽,根蒂,敲声,纹理,脐部,触感', '密度,含糖率');
INSERT INTO `watermelondataset3` VALUES ('16', '浅白', '蜷缩', '浊响', '模糊', '平坦', '硬滑', '0.593', '0.042', '否', '色泽,根蒂,敲声,纹理,脐部,触感', '密度,含糖率');
INSERT INTO `watermelondataset3` VALUES ('17', '青绿', '蜷缩', '沉闷', '稍糊', '稍凹', '硬滑', '0.719', '0.103', '否', '色泽,根蒂,敲声,纹理,脐部,触感', '密度,含糖率');

-- ----------------------------
-- Table structure for watermelondataset3a
-- ----------------------------
DROP TABLE IF EXISTS `watermelondataset3a`;
CREATE TABLE `watermelondataset3a` (
  `id` int(11) NOT NULL,
  `density` double DEFAULT NULL,
  `sugarContent` double DEFAULT NULL,
  `isGood` varchar(255) DEFAULT NULL,
  `continuousAttr` varchar(255) DEFAULT NULL,
  `discreteAttr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of watermelondataset3a
-- ----------------------------
INSERT INTO `watermelondataset3a` VALUES ('1', '0.697', '0.46', '是', '密度,含糖率', null);
INSERT INTO `watermelondataset3a` VALUES ('2', '0.774', '0.376', '是', '密度,含糖率', null);
INSERT INTO `watermelondataset3a` VALUES ('3', '0.634', '0.264', '是', '密度,含糖率', null);
INSERT INTO `watermelondataset3a` VALUES ('4', '0.608', '0.318', '是', '密度,含糖率', null);
INSERT INTO `watermelondataset3a` VALUES ('5', '0.556', '0.215', '是', '密度,含糖率', null);
INSERT INTO `watermelondataset3a` VALUES ('6', '0.403', '0.237', '是', '密度,含糖率', null);
INSERT INTO `watermelondataset3a` VALUES ('7', '0.481', '0.149', '是', '密度,含糖率', null);
INSERT INTO `watermelondataset3a` VALUES ('8', '0.437', '0.211', '是', '密度,含糖率', null);
INSERT INTO `watermelondataset3a` VALUES ('9', '0.666', '0.091', '否', '密度,含糖率', null);
INSERT INTO `watermelondataset3a` VALUES ('10', '0.243', '0.267', '否', '密度,含糖率', null);
INSERT INTO `watermelondataset3a` VALUES ('11', '0.245', '0.057', '否', '密度,含糖率', null);
INSERT INTO `watermelondataset3a` VALUES ('12', '0.343', '0.099', '否', '密度,含糖率', null);
INSERT INTO `watermelondataset3a` VALUES ('13', '0.639', '0.161', '否', '密度,含糖率', null);
INSERT INTO `watermelondataset3a` VALUES ('14', '0.657', '0.198', '否', '密度,含糖率', null);
INSERT INTO `watermelondataset3a` VALUES ('15', '0.36', '0.37', '否', '密度,含糖率', null);
INSERT INTO `watermelondataset3a` VALUES ('16', '0.593', '0.042', '否', '密度,含糖率', null);
INSERT INTO `watermelondataset3a` VALUES ('17', '0.719', '0.103', '否', '密度,含糖率', null);

-- ----------------------------
-- Table structure for watermelondataset4
-- ----------------------------
DROP TABLE IF EXISTS `watermelondataset4`;
CREATE TABLE `watermelondataset4` (
  `id` int(11) NOT NULL,
  `density` double DEFAULT NULL,
  `sugarContent` double DEFAULT NULL,
  `continuousAttr` varchar(255) DEFAULT NULL,
  `discreteAttr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of watermelondataset4
-- ----------------------------
INSERT INTO `watermelondataset4` VALUES ('1', '0.697', '0.46', '密度,含糖率', '');
INSERT INTO `watermelondataset4` VALUES ('2', '0.774', '0.376', '密度,含糖率', null);
INSERT INTO `watermelondataset4` VALUES ('3', '0.634', '0.264', '密度,含糖率', null);
INSERT INTO `watermelondataset4` VALUES ('4', '0.608', '0.318', '密度,含糖率', null);
INSERT INTO `watermelondataset4` VALUES ('5', '0.556', '0.215', '密度,含糖率', null);
INSERT INTO `watermelondataset4` VALUES ('6', '0.403', '0.237', '密度,含糖率', null);
INSERT INTO `watermelondataset4` VALUES ('7', '0.481', '0.149', '密度,含糖率', null);
INSERT INTO `watermelondataset4` VALUES ('8', '0.437', '0.211', '密度,含糖率', null);
INSERT INTO `watermelondataset4` VALUES ('9', '0.666', '0.091', '密度,含糖率', null);
INSERT INTO `watermelondataset4` VALUES ('10', '0.243', '0.267', '密度,含糖率', null);
INSERT INTO `watermelondataset4` VALUES ('11', '0.245', '0.057', '密度,含糖率', null);
INSERT INTO `watermelondataset4` VALUES ('12', '0.343', '0.099', '密度,含糖率', null);
INSERT INTO `watermelondataset4` VALUES ('13', '0.639', '0.161', '密度,含糖率', null);
INSERT INTO `watermelondataset4` VALUES ('14', '0.657', '0.198', '密度,含糖率', null);
INSERT INTO `watermelondataset4` VALUES ('15', '0.36', '0.37', '密度,含糖率', null);
INSERT INTO `watermelondataset4` VALUES ('16', '0.593', '0.042', '密度,含糖率', null);
INSERT INTO `watermelondataset4` VALUES ('17', '0.719', '0.103', '密度,含糖率', null);
INSERT INTO `watermelondataset4` VALUES ('18', '0.359', '0.188', '密度,含糖率', null);
INSERT INTO `watermelondataset4` VALUES ('19', '0.339', '0.241', '密度,含糖率', null);
INSERT INTO `watermelondataset4` VALUES ('20', '0.282', '0.257', '密度,含糖率', null);
INSERT INTO `watermelondataset4` VALUES ('21', '0.748', '0.232', '密度,含糖率', null);
INSERT INTO `watermelondataset4` VALUES ('22', '0.714', '0.346', '密度,含糖率', null);
INSERT INTO `watermelondataset4` VALUES ('23', '0.483', '0.312', '密度,含糖率', null);
INSERT INTO `watermelondataset4` VALUES ('24', '0.478', '0.437', '密度,含糖率', null);
INSERT INTO `watermelondataset4` VALUES ('25', '0.525', '0.369', '密度,含糖率', null);
INSERT INTO `watermelondataset4` VALUES ('26', '0.751', '0.489', '密度,含糖率', null);
INSERT INTO `watermelondataset4` VALUES ('27', '0.532', '0.472', '密度,含糖率', null);
INSERT INTO `watermelondataset4` VALUES ('28', '0.473', '0.376', '密度,含糖率', null);
INSERT INTO `watermelondataset4` VALUES ('29', '0.725', '0.445', '密度,含糖率', null);
INSERT INTO `watermelondataset4` VALUES ('30', '0.446', '0.459', '密度,含糖率', null);
