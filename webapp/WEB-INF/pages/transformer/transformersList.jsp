<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/style.css" />" rel="stylesheet">
    <title>transformersPage</title>
</head>

<body>

<c:url var="addUrl" value="/transformer/get?pageType=add" />
<table style="border: 3px solid; width: 500px; text-align: center">
    <thead style="background: #ccf">
    <tr>
        <th>id</th>
        <th>Input voltage</th>
        <th>Output voltage</th>
        <th>Output current</th>
        <th>Mass</th>
        <th>Price</th>
        <th colspan="2"></th>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="transformers" type="nom.alekseyLevchenko.transformersManager.domain.Transformer"--%>

    <c:forEach items="${transformers}" var="transformer" varStatus="varStatus">
        <c:url var="editUrl" value="/transformer/get?pageType=edit&id=${transformer.id}" />
        <c:url var="deleteUrl" value="/transformer/get?id=${transformer.id}&pageType=delete" />

        <tr class="${varStatus.index % 2 == 0 ? 'oddLine' : 'evenLine'}">
            <td>${transformer.id}</td>
            <td>${transformer.inputVoltage}</td>
            <td>${transformer.outputVoltage}</td>
            <td>${transformer.outputCurrent}</td>
            <td>${transformer.mass}</td>
            <td>${transformer.price}</td>
            <td><a href="${editUrl}"> <button>Edit</button> </a></td>
            <td><a href="${deleteUrl}"> <button>Delete</button> </a></td>
        </tr>
    </c:forEach>
    </tbody>

</table>

<a href="${addUrl}">
    <button>Add</button>
</a>

<br>
<a href="${pageContext.request.contextPath}/shop/list">shop list</a>

<br>
<a href="${pageContext.request.contextPath}/employee/listWithShops">employee list</a>

</body>

</html>
