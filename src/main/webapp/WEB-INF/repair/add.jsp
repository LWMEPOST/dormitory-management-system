<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>添加报修记录 - 宿舍管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="main-container">
        <!-- 左侧导航栏 -->
        <div class="sidebar">
            <h2>宿舍管理系统</h2>
            <ul class="menu">
                <li><a href="${pageContext.request.contextPath}/index.jsp">首页</a></li>
                <li><a href="${pageContext.request.contextPath}/user/list">用户管理</a></li>
                <li><a href="${pageContext.request.contextPath}/student/list">学生管理</a></li>
                <li><a href="${pageContext.request.contextPath}/dormitory/list">宿舍管理</a></li>
                <li><a href="${pageContext.request.contextPath}/checkin/list">入住登记</a></li>
                <li><a href="${pageContext.request.contextPath}/checkout/list">退宿管理</a></li>
                <li><a href="${pageContext.request.contextPath}/repair/list">报修处理</a></li>
            </ul>
        </div>
        
        <!-- 主内容区域 -->
        <div class="content">
            <div class="header">
                <h1>添加报修记录</h1>
                <div class="user-info">
                    <span>欢迎您，${sessionScope.user.name}（${sessionScope.user.role == 0 ? '管理员' : '宿管'}）</span>
                    <a href="${pageContext.request.contextPath}/logout">退出登录</a>
                </div>
            </div>
            
            <!-- 消息提示 -->
            <c:if test="${not empty requestScope.message}">
                <div class="message ${requestScope.type eq 'success' ? 'success' : 'error'}">
                    ${requestScope.message}
                </div>
            </c:if>
            
            <!-- 表单 -->
            <div class="form-container">
                <form action="add" method="post">
                    <div class="form-group">
                        <label for="studentId">学生ID</label>
                        <input type="number" id="studentId" name="studentId" placeholder="请输入学生ID" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="dormitoryId">宿舍ID</label>
                        <input type="number" id="dormitoryId" name="dormitoryId" placeholder="请输入宿舍ID" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="title">报修标题</label>
                        <input type="text" id="title" name="title" placeholder="请输入报修标题" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="content">报修内容</label>
                        <textarea id="content" name="content" placeholder="请输入报修详细内容" required></textarea>
                    </div>
                    
                    <div class="form-group">
                        <label for="repairDate">报修日期</label>
                        <input type="date" id="repairDate" name="repairDate" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="note">备注</label>
                        <textarea id="note" name="note" placeholder="请输入备注信息"></textarea>
                    </div>
                    
                    <button type="submit" class="btn">添加</button>
                    <a href="${pageContext.request.contextPath}/repair/list" class="btn btn-cancel">取消</a>
                </form>
            </div>
        </div>
    </div>
</body>
</html>