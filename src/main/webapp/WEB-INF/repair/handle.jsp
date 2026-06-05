<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>处理报修记录 - 宿舍管理系统</title>
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
            <h2>处理报修记录</h2>
            
            <!-- 消息提示 -->
            <c:if test="${not empty requestScope.message}">
                <div class="message ${requestScope.type eq 'success' ? 'success' : 'error'}">
                    ${requestScope.message}
                </div>
            </c:if>
            
            <!-- 表单容器 -->
            <div class="form-container">
                <form action="handle" method="post">
                    <!-- 隐藏字段 -->
                    <input type="hidden" name="id" value="${requestScope.repair.id}">
                    
                    <!-- 报修详情 -->
                    <div class="repair-detail">
                        <p><strong>报修ID：</strong>${requestScope.repair.id}</p>
                        <p><strong>学生ID：</strong>${requestScope.repair.studentId}</p>
                        <p><strong>宿舍ID：</strong>${requestScope.repair.dormitoryId}</p>
                        <p><strong>报修标题：</strong>${requestScope.repair.title}</p>
                        <p><strong>报修内容：</strong>${requestScope.repair.content}</p>
                        <p><strong>报修日期：</strong>${requestScope.repair.repairDate}</p>
                        <p><strong>当前状态：</strong>
                            <c:choose>
                                <c:when test="${requestScope.repair.status == 0}">待处理</c:when>
                                <c:when test="${requestScope.repair.status == 1}">处理中</c:when>
                                <c:when test="${requestScope.repair.status == 2}">已完成</c:when>
                            </c:choose>
                        </p>
                    </div>
                    
                    <div class="form-group">
                        <label for="status">处理状态</label>
                        <select id="status" name="status" required>
                            <option value="1" ${requestScope.repair.status == 1 ? 'selected' : ''}>处理中</option>
                            <option value="2" ${requestScope.repair.status == 2 ? 'selected' : ''}>已完成</option>
                        </select>
                    </div>
                    
                    <div class="form-group">
                        <label for="completeDate">完成日期</label>
                        <input type="date" id="completeDate" name="completeDate">
                    </div>
                    
                    <div class="form-group">
                        <label for="handler">处理人</label>
                        <input type="text" id="handler" name="handler" value="${sessionScope.user.name}" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="note">处理备注</label>
                        <textarea id="note" name="note" placeholder="请输入处理备注">${requestScope.repair.note}</textarea>
                    </div>
                    
                    <div class="form-actions">
                        <a href="${pageContext.request.contextPath}/repair/list" class="btn btn-cancel">取消</a>
                        <button type="submit" class="btn">保存处理结果</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>