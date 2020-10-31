/*
SQLyog - Free MySQL GUI v5.02
Host - 5.0.19-nt : Database - bank
*********************************************************************
Server version : 5.0.19-nt
*/


create database if not exists `bank`;

USE `bank`;

/*Table structure for table `account_bal` */

DROP TABLE IF EXISTS `account_bal`;

CREATE TABLE `account_bal` (
  `accountno` int(20) NOT NULL,
  `balance` float default NULL,
  PRIMARY KEY  (`accountno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `account_bal` */

insert into `account_bal` values 
(1,7350),
(2,4400),
(3,10000);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `accountno` int(20) NOT NULL,
  `password` varchar(15) default NULL,
  PRIMARY KEY  (`accountno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert into `users` values 
(1,'abc'),
(2,'pqr'),
(3,'xyz'),
(4,'iop');
