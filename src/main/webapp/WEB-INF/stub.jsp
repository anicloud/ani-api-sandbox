<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="base.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>stub</title>
</head>

<body>
<script>

    var webSocket = null;
    window.onload=function () {
        webSocket = new WebSocket("ws://localhost:8082/sunny/websocket");
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
    <table id="master_table" width="98%" >
        <thead>
        <tr>
            <td>stub名</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <jsp:useBean id="stubs" scope="request" type="java.util.List"/>
        <c:forEach items="${stubs}" var="obj" varStatus="status">
            <tr>
                <td>${obj.name}</td>
                <td>
                <form action="/sunny/stub/invoke/${obj.masterId}/${obj.slaveId}/${obj.groupId}/${obj.stubId}">

                 <c:forEach items="${obj.inputArguments}" var="ob1" varStatus="status">

                        <br>参数名：<input type="text" name="name" value="${ob1.name}" disabled="disabled" ></br>
                        <br>参数值：<input type="text" name="value${status.index}"></br>
                 </c:forEach>
                 <input type="submit" value="submit">
                </form>
                </td>
            </tr>

        </c:forEach>
        </tbody>
    </table>
</div>

<script type="text/JavaScript">

</script>
</body>
</html>
