<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <%--<link href="<c:url value="${pageContext.request.contextPath}/resources/css/style.css" />" rel="stylesheet">--%>
    <title>Shops page</title>
</head>

<body>
<jsp:include page="../fragments/header.jsp" />

<h1>All shops</h1>

<c:if test="${not empty msg}">
    <div class="${css}">
        <strong>${msg}</strong>
    </div>
</c:if>

<c:url var="addUrl" value="/shops/add" />


<table>
    <thead>
    <tr>
        <th>id</th>
        <th>Name</th>
        <th>Address</th>
        <th>Tel</th>
        <th>Working time</th>
        <th colspan="3"></th>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="shops" type="nom.alekseyLevchenko.transformersManager.domain.Shop"--%>

    <c:forEach items="${shops}" var="shop" varStatus="varStatus">
        <spring:url var="showUrl" value="/shops/${shop.id}" />

        <tr class="${varStatus.index % 2 == 0 ? 'oddLine' : 'evenLine'}">
            <td>${shop.id}</td>
            <td>${shop.name}</td>
            <td>${shop.address}</td>
            <td>${shop.tel}</td>
            <td>${shop.workingTime}</td>

            <td class="button-cell">
                <form action="${showUrl}" method="get">
                    <button>Details</button>
                </form>
            </td>

        </tr>
    </c:forEach>
    </tbody>

</table>

<form action="${addUrl}" method="get">
    <button>Add</button>
</form>


<jsp:include page="../fragments/footer.jsp" />
</body>

</html>
