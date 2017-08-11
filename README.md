# Monitor
Spring MVC, RESTful service, Hibernate, JPA, Thymeleaf and Maven

#
install maven & jdk 8

#
IntelliJ

#
Click Run->Edit Configuration->+->Maven->
Command line -> spring-boot:run

#
create database monitor;

Run the project
Hibernate will create tables for you
#
INSERT INTO `monitor`.`role` (`role`, `description`) VALUES ('ADMIN', 'Administrator');
#
INSERT INTO `monitor`.`role` (`role`, `description`) VALUES ('USER', 'User');
#
INSERT INTO `monitor`.`role` (`role`, `description`) VALUES ('ANONYMOUS', 'Anonymous');
#
INSERT INTO `monitor`.`nav` (`href`, `icon`, `name`,`role`) VALUES ('/', 'fa fa-home', 'Home', 'ANONYMOUS');
#
INSERT INTO `monitor`.`nav` (`href`, `icon`, `name`,`role`) VALUES ('/about', 'fa fa-wpforms', 'About', 'ANONYMOUS');


#
localhost:8080
