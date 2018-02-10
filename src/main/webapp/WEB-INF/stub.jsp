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
           // 172.20.10.3
//            webSocket = new WebSocket("ws://172.20.10.3:8082/sunny/websocket");
//            webSocket = new WebSocket("ws://192.168.1.132:8082/sunny/websocket");
            webSocket = new WebSocket("ws://device.yatsen.bj.anicloud.cn:8081/sunny/websocket")
        };
        // 收到服务端消息
        webSocket.onmessage = function (msg) {
            alert(msg);
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
                        <br>参数值：<input type="text" name="value${status.index}" id="parameters"></br>
                        <br><input type="file" id="my_file" onchange="aaa()"></br>
                 </c:forEach>
                 <input type="submit" value="submit">
                </form>
                </td>
            </tr>

        </c:forEach>
        </tbody>
    </table>
</div>
<script type="text/javascript" >
    function aaa() {
        //alert("执行了吗");
        var selectedFile = document.getElementById("my_file").files[0];//获取读取的File对象
        var name = selectedFile.name;//读取选中文件的文件名
        var size = selectedFile.size;//读取选中文件的大小
        var reader = new FileReader();//这里是核心！！！读取操作就是由它完成的。
        reader.readAsText(selectedFile);//读取文件的内容
        reader.onload = function () {
    //        console.log(this.result);//当读取完成之后会回调这个函数，然后此时文件的内容存储到了result中。直接操作即可。
            document.getElementById("parameters").value=this.result;
//            alert(document.getElementById("parameters").value);
//            alert("读取完毕");
        }

    }

</script>
</body>
</html>
