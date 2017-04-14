-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.31-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 jsche 的数据库结构
CREATE DATABASE IF NOT EXISTS `jsche` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `jsche`;


-- 导出  表 jsche.system_usages 结构
CREATE TABLE IF NOT EXISTS `system_usages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_ip` varchar(255) DEFAULT NULL,
  `date_stamp` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 jsche.tasks 结构
CREATE TABLE IF NOT EXISTS `tasks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `duration` int(11) NOT NULL,
  `priority` int(11) NOT NULL,
  `start_date` datetime DEFAULT NULL,
  `status` int(11) NOT NULL,
  `task_name` varchar(255) DEFAULT NULL,
  `task_type` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  过程 jsche.temp_week 结构
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `temp_week`()
BEGIN
	declare i int;
	declare pos int;
	declare date_string varchar(20);
	set i=0;
	set pos = 1;
	
	delete from temp_week_serial;
	
	while i < 7 do
		select date_sub(curdate(),INTERVAL WEEKDAY(curdate()) + pos DAY) from dual into date_string;
		insert into temp_week_serial(start_date,count)
		values (date_string,0) ;
		set pos = pos - 1;
		set i = i+1;	
	end while;
END//
DELIMITER ;


-- 导出  表 jsche.temp_week_serial 结构
CREATE TABLE IF NOT EXISTS `temp_week_serial` (
  `start_date` datetime DEFAULT NULL,
  `count` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 jsche.users 结构
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `avatar` varchar(1000) NOT NULL,
  `customized_avatar` bit(1) DEFAULT b'0',
  `city` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  事件 jsche.weekly_serial 结构
DELIMITER //
CREATE DEFINER=`root`@`localhost` EVENT `weekly_serial` ON SCHEDULE EVERY 7 DAY STARTS '2017-04-07 15:41:15' ON COMPLETION NOT PRESERVE ENABLE DO CALL `temp_week`()//
DELIMITER ;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
