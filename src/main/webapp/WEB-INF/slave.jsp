<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="base.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>设备</title>
</head>

<body>
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
        <jsp:useBean id="masters" class="com.ani.bus.service.commons.dto.anidevice.DeviceSlaveObjInfoDto" scope="request" type="java.util.List"/>
        <c:forEach items="${slaves}" var="obj" varStatus="status">
            <tr>
                <td>${obj.name}</td>
                <td>${obj.description}</td>
                <td>${obj.objectId}</td>
                <td>${obj.state}</td>
                <td><a href="${ctx}/slave/stubs/${masterId}/${obj.objectId}">查看详情</a></td>
        </c:forEach>
        </tbody>
    </table>
</div>

<script type="text/JavaScript">

</script>
</body>
</html>
