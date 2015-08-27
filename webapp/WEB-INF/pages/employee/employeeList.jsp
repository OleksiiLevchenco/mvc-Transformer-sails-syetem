<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/style.css" />" rel="stylesheet">
    <title>Employees page</title>
</head>

<body>
<jsp:include page="../fragments/header.jsp"/>

<c:url var="addUrl" value="employees/add"/>
<c:url var="returnUrl" value="/shops/${shopId}"/>

<c:if test="${not empty msg}">
    <div class="${css}">
        <strong>${msg}</strong>
    </div>
</c:if>

<c:choose>
    <c:when test="${!empty shopName}">
        <H2>
            <a href="${returnUrl}" style="text-decoration: none">
                <span class="shopName">
                    ${shopName}
                </span>
            </a>
            shop employees list
        </H2>
    </c:when>
    <c:otherwise><H2>Employees common database</H2></c:otherwise>
</c:choose>



<table style="border: 3px solid; text-align: center">
    <thead style="background: #ccf">
    <tr>
        <th>id</th>
        <th>Name</th>
        <c:if test="${empty shopId}">
            <th>Shop</th>
        </c:if>
        <th colspan="4"></th>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="shops" type="nom.alekseyLevchenko.transformersManager.domain.Shop"--%>

    <c:forEach items="${employees}" var="employee" varStatus="varStatus">
        <c:url var="editUrl" value="employees/${employee.id}/update"/>
        <c:url var="deleteUrl" value="employees/${employee.id}/delete"/>
        <c:url var="showUtl" value="employees/${employee.id}"/>

        <tr class="${varStatus.index % 2 == 0 ? 'oddLine' : 'evenLine'}">
            <td>${employee.id}</td>
            <td>${employee.name}</td>
            <c:if test="${empty shopId}">
                <td>${employee.shop.id}</td>
            </c:if>
            <td><a href="${showUtl}">
                <button>Show details</button>
            </a></td>
            <td><a href="${editUrl}">
                <button>Edit</button>
            </a></td>
            <td><a href="${deleteUrl}">
                <button>Delete</button>
            </a></td>
            <td>
                <img src="${employee.imgUrl}" height="50"/>
            </td>
        </tr>
    </c:forEach>

    </tbody>

</table>


<a href="${addUrl}">
    <button style="margin-top: 10px">Add new employee</button>
</a>
<a href="${returnUrl}">
    <button style="margin-top: 10px; margin-left: 10px">Back</button>
</a>


<jsp:include page="../fragments/footer.jsp"/>
</body>

</html>
