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

            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <jsp:useBean id="stubs" class="com.ani.octopus.commons.stub.dto.StubInfoDto" scope="request" type="java.util.List"/>
        <c:forEach items="${stubs}" var="obj" varStatus="status">
            <tr>
                <td>${obj.name}</td>


            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script type="text/JavaScript">

</script>
</body>
</html>
