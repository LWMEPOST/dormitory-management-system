# 宿舍管理系统

## 项目介绍

本项目是一个基于 Java Web 的宿舍管理系统，用于宿舍、学生、入住信息和管理员基础数据维护。项目适合课程设计或 Java Web 入门实践，采用 Maven 管理依赖并以 WAR 包方式部署到 Servlet 容器。

## 技术栈

- Java Servlet / JSP
- Maven
- MySQL
- JDBC
- HTML/CSS/JavaScript

## 部署要求

- JDK 8 或以上
- Maven 3.x
- MySQL 5.7/8.0
- Tomcat 9/10 或兼容 Servlet 容器

## 运行流程

1. 创建 MySQL 数据库并导入 dormitory.sql。
2. 检查 src/main/resources 或代码中的数据库连接配置。
3. 执行 mvn clean package 生成 WAR 包。
4. 将 WAR 部署到 Tomcat，或在 IDE 中配置 Tomcat 运行。
5. 浏览器访问系统入口页面进行登录和功能测试。

## 项目结构

- src/main/java：Servlet、实体类和业务代码
- src/main/webapp：JSP 页面和静态资源
- dormitory.sql：数据库初始化脚本
- pom.xml：Maven 依赖和打包配置
