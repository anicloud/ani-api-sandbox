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
 //       webSocket = new WebSocket("ws://192.168.1.132:8082/sunny/websocket");
        webSocket = new WebSocket("ws://sandbox.bj.anicel.cn:8000/sunny/websocket")
    };
    // 收到服务端消息
    webSocket.onmessage = function (event) {
        console.log(event.data);
    };

    // 异常
    webSocket.onerror = function (event) {
        console.log(event);
    };

    // 建立连接
    webSocket.onopen = function (event) {
        alert(event);
    };

    /**
     * 初始化websocket，建立连接
     */
</script>
<div>
    <table id="master_table" width="98%" >
        <thead>
        <tr>
            <td>消息</td>

        </tr>
        </thead>
        <tbody>
        <script type="text/javascript">

        </script>
        <jsp:useBean id="messages" scope="request" type="java.util.List"/>
        <c:forEach items="${messages}" var="obj" varStatus="status">
            <tr>
                <td>${obj}</td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>

<script type="text/JavaScript">

</script>
</body>
</html>

</html>