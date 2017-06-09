<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="base.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>设备</title>
</head>

<body>
<script>

    var webSocket = null;
    window.onload=function () {
        webSocket = new WebSocket("ws://localhost:8080/sunny/websocket");
    };
    // 收到服务端消息
    webSocket.onmessage = function (msg) {
        console.log(msg);
    };

    // 异常
    webSocket.onerror = function (event) {
        console.log(event);
    };

    // 建立连接
    webSocket.onopen = function (event) {
        console.log(event);
    };

    // 断线重连
    webSocket.onclose = function () {

    };
    /**
     * 初始化websocket，建立连接
     */
</script>
<div>
    <table id="slave_table" width="98%" >
        <thead>
        <tr>
            <td>设备名</td>
            <td>描述</td>
            <td>slaveId</td>
            <td>状态</td>
        </tr>
        </thead>
        <tbody>
        <jsp:useBean id="slaves" scope="request" type="java.util.List"/>
        <c:forEach items="${slaves}" var="obj" varStatus="status">
            <tr>
                <td>${obj.name}</td>
                <td>${obj.description}</td>
                <td>${obj.slaveId}</td>
                <td>${obj.deviceState}</td>
                <td><a href="${ctx}/device/slave/stubs/${obj.masterId}/${obj.slaveId}">查看详情</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script type="text/JavaScript">

</script>
</body>
</html>
