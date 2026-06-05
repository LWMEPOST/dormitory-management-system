<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>编辑学生 - 宿舍管理系统</title>
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
            <h2>编辑学生</h2>
            
            <!-- 消息提示 -->
            <c:if test="${not empty requestScope.message}">
                <div class="message ${requestScope.type eq 'success' ? 'success' : 'error'}">
                    ${requestScope.message}
                </div>
            </c:if>
            
            <!-- 表单容器 -->
            <div class="form-container">
                <form action="edit" method="post">
                    <input type="hidden" name="id" value="${student.id}">
                    <div class="form-group">
                        <label for="studentId">学号</label>
                        <input type="text" id="studentId" name="studentId" value="${student.studentId}" placeholder="请输入学号" required>
                    </div>
                    <div class="form-group">
                        <label for="name">姓名</label>
                        <input type="text" id="name" name="name" value="${student.name}" placeholder="请输入姓名" required>
                    </div>
                    <div class="form-group">
                        <label for="gender">性别</label>
                        <select id="gender" name="gender" required>
                            <option value="男" ${student.gender == '男' ? 'selected' : ''}>男</option>
                            <option value="女" ${student.gender == '女' ? 'selected' : ''}>女</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="age">年龄</label>
                        <input type="number" id="age" name="age" value="${student.age}" placeholder="请输入年龄" required>
                    </div>
                    <div class="form-group">
                        <label for="department">院系</label>
                        <input type="text" id="department" name="department" value="${student.department}" placeholder="请输入院系" required>
                    </div>
                    <div class="form-group">
                        <label for="major">专业</label>
                        <input type="text" id="major" name="major" value="${student.major}" placeholder="请输入专业" required>
                    </div>
                    <div class="form-group">
                        <label for="className">班级</label>
                        <input type="text" id="className" name="className" value="${student.className}" placeholder="请输入班级" required>
                    </div>
                    <div class="form-group">
                        <label for="phone">电话</label>
                        <input type="text" id="phone" name="phone" value="${student.phone}" placeholder="请输入电话" required>
                    </div>
                    <div class="form-actions">
                        <a href="${pageContext.request.contextPath}/student/list" class="btn btn-cancel">取消</a>
                        <button type="submit" class="btn">保存</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>