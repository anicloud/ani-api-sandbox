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
            <td>stub名</td>
            <td>stub值</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <jsp:useBean id="args" scope="request" type="java.util.List"/>

        <form action="">
        <c:forEach items="${args}" var="obj" varStatus="status">
            <tr>
                <td>${obj.name}</td>
                <td><input id="stubArgvalue" name="" type="text"></td>
                <td><input></td>
            </tr>
        </c:forEach>
            <input type="submit" value="执行">
        </form>

        </tbody>
    </table>
</div>

<script type="text/JavaScript">

</script>
</body>
</html>
