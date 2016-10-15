<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/10/15
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title></title>
</head>
<body>
<table border="1">
    <c:forEach var="item" items="${list}">
        <tr>
            <td>${item.name}</td>
            <td>${item.sex == 1?'男':'女'}</td>
            <td>${item.job}</td>
            <td>${item.identityCard}</td>
            <td>${item.deptNo}</td>
            <td><fmt:formatDate value="${item.createDate}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
