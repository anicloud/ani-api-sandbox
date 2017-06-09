<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="base.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>设备</title>
</head>

<body>
<div>
    <table id="master_table" width="98%" >
        <thead>
        <tr>
            <td>设备名</td>
            <td>描述</td>
            <td>masterId</td>
            <td>状态</td>
        </tr>
        </thead>
        <tbody>
        <script>

            var webSocket = null;
            var tryTime = 0;
            function initSocket() {
                if (!window.WebSocket) {
                    alert("您的浏览器不支持websocket！");
                    return false;
                }

                webSocket = new WebSocket("ws://localhost:8080/sunny/websocket");
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
                    // 重试10次，每次之间间隔10秒
                    if (tryTime < 10) {
                        setTimeout(function () {
                            webSocket = null;
                            tryTime++;
                            initSocket();
                        }, 500);
                    } else {
                        tryTime = 0;
                    }
                };

            }
            initSocket();
            /**
             * 初始化websocket，建立连接
             */
        </script>
        <script type="text/javascript">

        </script>

        </tbody>
    </table>
</div>

<script type="text/JavaScript">

</script>
</body>
</html>

