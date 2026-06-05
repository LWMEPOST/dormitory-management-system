<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>宿舍管理系统 - 首页</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f2f5;
        }
        
        /* 头部样式 */
        .header {
            background-color: #1890ff;
            color: white;
            padding: 15px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        
        .header h1 {
            font-size: 24px;
            font-weight: bold;
        }
        
        .header .user-info {
            display: flex;
            align-items: center;
        }
        
        .header .user-info span {
            margin-right: 20px;
        }
        
        .header .user-info a {
            color: white;
            text-decoration: none;
            padding: 5px 10px;
            background-color: #40a9ff;
            border-radius: 4px;
        }
        
        .header .user-info a:hover {
            background-color: #69c0ff;
        }
        
        /* 主容器样式 */
        .main-container {
            display: flex;
            min-height: calc(100vh - 60px);
        }
        
        /* 侧边栏样式 */
        .sidebar {
            width: 200px;
            background-color: #001529;
            color: white;
        }
        
        .sidebar ul {
            list-style: none;
        }
        
        .sidebar ul li {
            border-bottom: 1px solid #002140;
        }
        
        .sidebar ul li a {
            display: block;
            padding: 15px 20px;
            color: white;
            text-decoration: none;
            transition: background-color 0.3s;
        }
        
        .sidebar ul li a:hover {
            background-color: #1890ff;
        }
        
        /* 内容区域样式 */
        .content {
            flex: 1;
            padding: 20px;
            background-color: #f0f2f5;
        }
        
        .content h2 {
            color: #333;
            margin-bottom: 20px;
        }
        
        /* 卡片样式 */
        .card-container {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
        }
        
        .card {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        
        .card h3 {
            color: #333;
            margin-bottom: 10px;
        }
        
        .card p {
            color: #666;
            margin-bottom: 20px;
        }
        
        .card .btn {
            display: inline-block;
            padding: 8px 16px;
            background-color: #1890ff;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s;
        }
        
        .card .btn:hover {
            background-color: #40a9ff;
        }
    </style>
</head>
<body>
    <!-- 头部 -->
    <div class="header">
        <h1>宿舍管理系统</h1>
        <div class="user-info">
            <span>欢迎您，${user.name}（${user.role == 0 ? '管理员' : '宿管'}）</span>
            <a href="logout">退出登录</a>
        </div>
    </div>
    
    <!-- 主容器 -->
    <div class="main-container">
        <!-- 侧边栏 -->
        <div class="sidebar">
            <ul>
                <li><a href="index.jsp">首页</a></li>
                <li><a href="user/">用户管理</a></li>
                <li><a href="student/">学生管理</a></li>
                <li><a href="dormitory/">宿舍管理</a></li>
                <li><a href="checkin/">入住管理</a></li>
                <li><a href="checkout/">退宿管理</a></li>
                <li><a href="repair/">报修管理</a></li>
            </ul>
        </div>
        
        <!-- 内容区域 -->
        <div class="content">
            <h2>欢迎使用宿舍管理系统</h2>
            
            <div class="card-container">
                <div class="card">
                    <h3>用户管理</h3>
                    <p>管理系统用户，包括添加、编辑、删除用户</p>
                    <a href="user/" class="btn">进入管理</a>
                </div>
                
                <div class="card">
                    <h3>学生管理</h3>
                    <p>管理学生信息，包括添加、编辑、删除学生</p>
                    <a href="student/" class="btn">进入管理</a>
                </div>
                
                <div class="card">
                    <h3>宿舍管理</h3>
                    <p>管理宿舍信息，包括添加、编辑、删除宿舍</p>
                    <a href="dormitory/" class="btn">进入管理</a>
                </div>
                
                <div class="card">
                    <h3>入住管理</h3>
                    <p>管理学生入住信息，包括添加入住记录</p>
                    <a href="checkin/" class="btn">进入管理</a>
                </div>
                
                <div class="card">
                    <h3>退宿管理</h3>
                    <p>管理学生退宿信息，包括添加退宿记录</p>
                    <a href="checkout/" class="btn">进入管理</a>
                </div>
                
                <div class="card">
                    <h3>报修管理</h3>
                    <p>管理宿舍报修信息，包括处理报修记录</p>
                    <a href="repair/" class="btn">进入管理</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
