<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>宿舍管理系统 - 入住管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <!-- 头部 -->
    <div class="header">
        <h1>宿舍管理系统</h1>
        <div class="user-info">
            <span>欢迎您，${sessionScope.user.name}（${sessionScope.user.role == 0 ? '管理员' : '宿管'}）</span>
            <a href="${pageContext.request.contextPath}/logout">退出登录</a>
        </div>
    </div>

    <!-- 主容器 -->
    <div class="main-container">
        <!-- 侧边栏 -->
        <div class="sidebar">
            <ul>
                <li><a href="${pageContext.request.contextPath}/index.jsp">首页</a></li>
                <li><a href="${pageContext.request.contextPath}/user/list">用户管理</a></li>
                <li><a href="${pageContext.request.contextPath}/student/list">学生管理</a></li>
                <li><a href="${pageContext.request.contextPath}/dormitory/list">宿舍管理</a></li>
                <li><a href="${pageContext.request.contextPath}/checkin/list">入住管理</a></li>
                <li><a href="${pageContext.request.contextPath}/checkout/list">退宿管理</a></li>
                <li><a href="${pageContext.request.contextPath}/repair/list">报修管理</a></li>
            </ul>
        </div>
        
        <!-- 内容区域 -->
        <div class="content">
            <h2>入住管理</h2>
            
            <!-- 操作栏 -->
            <div class="action-bar">
                <h3>入住记录列表</h3>
                <a href="add" class="btn">添加入住记录</a>
            </div>
            
            <!-- 表格容器 -->
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>学生ID</th>
                            <th>宿舍ID</th>
                            <th>入住日期</th>
                            <th>备注</th>
                            <th>创建时间</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="checkIn" items="${checkInList}">
                            <tr>
                                <td>${checkIn.id}</td>
                                <td>${checkIn.studentId}</td>
                                <td>${checkIn.dormitoryId}</td>
                                <td>${checkIn.checkInDate}</td>
                                <td>${checkIn.note}</td>
                                <td>${checkIn.createTime}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
