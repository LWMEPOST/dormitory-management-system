<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>宿舍管理系统 - 宿舍管理</title>
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
            <h2>宿舍管理</h2>
            
            <!-- 操作栏 -->
            <div class="action-bar">
                <h3>宿舍列表</h3>
                <a href="add" class="btn">添加宿舍</a>
            </div>
            
            <!-- 表格容器 -->
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>宿舍号</th>
                            <th>楼栋</th>
                            <th>楼层</th>
                            <th>容量</th>
                            <th>当前入住人数</th>
                            <th>类型</th>
                            <th>状态</th>
                            <th>创建时间</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dormitory" items="${dormitoryList}">
                            <tr>
                                <td>${dormitory.id}</td>
                                <td>${dormitory.dormitoryNumber}</td>
                                <td>${dormitory.building}</td>
                                <td>${dormitory.floor}</td>
                                <td>${dormitory.capacity}</td>
                                <td>${dormitory.currentOccupancy}</td>
                                <td>${dormitory.type}</td>
                                <td>${dormitory.status == 0 ? '空闲' : (dormitory.status == 1 ? '已入住' : '维修中')}</td>
                                <td>${dormitory.createTime}</td>
                                <td>
                                    <a href="edit?id=${dormitory.id}" class="btn">编辑</a>
                                    <a href="delete?id=${dormitory.id}" class="btn btn-danger" onclick="return confirm('确定要删除该宿舍吗？');">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
