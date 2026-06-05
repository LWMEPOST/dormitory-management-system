<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>宿舍管理系统 - 添加入住记录</title>
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
                <li><a href="${pageContext.request.contextPath}/user/">用户管理</a></li>
                <li><a href="${pageContext.request.contextPath}/student/">学生管理</a></li>
                <li><a href="${pageContext.request.contextPath}/dormitory/">宿舍管理</a></li>
                <li><a href="${pageContext.request.contextPath}/checkin/">入住管理</a></li>
                <li><a href="${pageContext.request.contextPath}/checkout/">退宿管理</a></li>
                <li><a href="${pageContext.request.contextPath}/repair/">报修管理</a></li>
            </ul>
        </div>
        
        <!-- 内容区域 -->
        <div class="content">
            <h2>添加入住记录</h2>
            
            <!-- 表单容器 -->
            <div class="form-container">
                <form action="add" method="post">
                    <div class="form-group">
                        <label for="studentId">学生</label>
                        <select id="studentId" name="studentId" required>
                            <c:forEach var="student" items="${studentList}">
                                <option value="${student.id}">${student.name} (${student.studentId})</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="dormitoryId">宿舍</label>
                        <select id="dormitoryId" name="dormitoryId" required>
                            <c:forEach var="dormitory" items="${dormitoryList}">
                                <option value="${dormitory.id}">${dormitory.dormitoryNumber} (${dormitory.building}栋 ${dormitory.floor}层)</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="checkInDate">入住日期</label>
                        <input type="date" id="checkInDate" name="checkInDate" required>
                    </div>
                    <div class="form-group">
                        <label for="note">备注</label>
                        <textarea id="note" name="note" placeholder="请输入备注"></textarea>
                    </div>
                    <div class="form-actions">
                        <a href="${pageContext.request.contextPath}/checkin/list" class="btn btn-cancel">取消</a>
                        <button type="submit" class="btn">添加</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
