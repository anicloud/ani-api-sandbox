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
//        webSocket = new WebSocket("ws://172.20.10.3:8082/sunny/websocket");
		//webSocket = new WebSocket("ws://192.168.1.132:8082/sunny/websocket");
        webSocket = new WebSocket("ws://device.yatsen.bj.anicloud.cn:8081/sunny/websocket")
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
				<td>设备名</td>
				<td>描述</td>
				<td>masterId</td>
				<td>状态</td>
			</tr>
		</thead>
		<tbody>
			<script type="text/javascript">

			</script>
			<jsp:useBean id="masters" scope="request" type="java.util.List"/>
			<c:forEach items="${masters}" var="obj" varStatus="status">
				<tr>
					<td>${obj.name}</td>
					<td>${obj.description}</td>
					<td>${obj.masterId}</td>
					<td>${obj.deviceState}</td>
					<td><a href="${ctx}/device/slave/${obj.masterId}">查看slave</a></td>
					<td><a href="${ctx}/device/stub/${obj.masterId}">查看stub</a>/td>

				</tr>
			</c:forEach>
			<td><a href="${ctx}/message/show">查看消息列表</a>/td>

		</tbody>
	</table>
</div>

<script type="text/JavaScript">

</script>
</body>
</html>


