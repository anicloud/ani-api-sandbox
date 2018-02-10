<%--
  Created by IntelliJ IDEA.
  User: zhanglina
  Date: 18-2-1
  Time: 下午2:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://cdn.bootcss.com/FileSaver.js/2014-11-29/FileSaver.js"></script>
    <%--<script type="text/javascript" src="./js/FileSaver.js"></script>--%>
</head>
<body>
<form>
   <input type="text" value="${values}" id="argumentValue">
    <input type="button" value="export" id="my_file" onclick="aaa()">
</form>



<script type="text/javascript">
     function aaa() {
         var content=document.getElementById("argumentValue").value;
         var blob = new Blob([content], {type: "text/plain;charset=utf-8"});
         saveAs(blob, "remote.sdp");
     }
</script>


</body>
</html>
