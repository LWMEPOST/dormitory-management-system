<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>编辑用户 - 宿舍管理系统</title>
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
                <li><a href="${pageContext.request.contextPath}/checkin/list">入住登记</a></li>
                <li><a href="${pageContext.request.contextPath}/checkout/list">退宿管理</a></li>
                <li><a href="${pageContext.request.contextPath}/repair/list">报修处理</a></li>
            </ul>
        </div>
        
        <!-- 内容区域 -->
        <div class="content">
            <h2>编辑用户</h2>
            
            <!-- 消息提示 -->
            <c:if test="${not empty requestScope.message}">
                <div class="message ${requestScope.type eq 'success' ? 'success' : 'error'}">
                    ${requestScope.message}
                </div>
            </c:if>
            
            <!-- 表单容器 -->
            <div class="form-container">
                <form action="edit" method="post">
                    <input type="hidden" name="id" value="${user.id}">
                    <div class="form-group">
                        <label for="username">用户名</label>
                        <input type="text" id="username" name="username" value="${user.username}" placeholder="请输入用户名" required>
                    </div>
                    <div class="form-group">
                        <label for="password">密码</label>
                        <input type="password" id="password" name="password" value="${user.password}" placeholder="请输入密码" required>
                    </div>
                    <div class="form-group">
                        <label for="role">角色</label>
                        <select id="role" name="role" required>
                            <option value="0" ${user.role == 0 ? 'selected' : ''}>管理员</option>
                            <option value="1" ${user.role == 1 ? 'selected' : ''}>宿管</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="name">姓名</label>
                        <input type="text" id="name" name="name" value="${user.name}" placeholder="请输入姓名" required>
                    </div>
                    <div class="form-group">
                        <label for="phone">电话</label>
                        <input type="text" id="phone" name="phone" value="${user.phone}" placeholder="请输入电话" required>
                    </div>
                    <div class="form-actions">
                        <a href="${pageContext.request.contextPath}/user/list" class="btn btn-cancel">取消</a>
                        <button type="submit" class="btn">保存</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>