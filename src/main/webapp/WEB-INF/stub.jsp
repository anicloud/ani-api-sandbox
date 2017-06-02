<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="base.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>stub</title>
</head>

<body>
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
                <form action="/sunny/device/argument/${obj.masterId}/${obj.slaveId}/${obj.stubId}">

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
