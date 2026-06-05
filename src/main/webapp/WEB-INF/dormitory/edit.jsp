<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>宿舍管理系统 - 编辑宿舍</title>
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
            <h2>编辑宿舍</h2>
            
            <!-- 表单容器 -->
            <div class="form-container">
                <form action="edit" method="post">
                    <input type="hidden" name="id" value="${dormitory.id}">
                    <div class="form-group">
                        <label for="dormitoryNumber">宿舍号</label>
                        <input type="text" id="dormitoryNumber" name="dormitoryNumber" value="${dormitory.dormitoryNumber}" placeholder="请输入宿舍号" required>
                    </div>
                    <div class="form-group">
                        <label for="building">楼栋</label>
                        <input type="text" id="building" name="building" value="${dormitory.building}" placeholder="请输入楼栋" required>
                    </div>
                    <div class="form-group">
                        <label for="floor">楼层</label>
                        <input type="number" id="floor" name="floor" value="${dormitory.floor}" placeholder="请输入楼层" required>
                    </div>
                    <div class="form-group">
                        <label for="capacity">容量</label>
                        <input type="number" id="capacity" name="capacity" value="${dormitory.capacity}" placeholder="请输入容量" required>
                    </div>
                    <div class="form-group">
                        <label for="currentOccupancy">当前入住人数</label>
                        <input type="number" id="currentOccupancy" name="currentOccupancy" value="${dormitory.currentOccupancy}" placeholder="请输入当前入住人数" required>
                    </div>
                    <div class="form-group">
                        <label for="type">类型</label>
                        <select id="type" name="type" required>
                            <option value="男生" ${dormitory.type == '男生' ? 'selected' : ''}>男生</option>
                            <option value="女生" ${dormitory.type == '女生' ? 'selected' : ''}>女生</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="status">状态</label>
                        <select id="status" name="status" required>
                            <option value="0" ${dormitory.status == 0 ? 'selected' : ''}>空闲</option>
                            <option value="1" ${dormitory.status == 1 ? 'selected' : ''}>已入住</option>
                            <option value="2" ${dormitory.status == 2 ? 'selected' : ''}>维修中</option>
                        </select>
                    </div>
                    <div class="form-actions">
                        <a href="${pageContext.request.contextPath}/dormitory/list" class="btn btn-cancel">取消</a>
                        <button type="submit" class="btn">保存</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
