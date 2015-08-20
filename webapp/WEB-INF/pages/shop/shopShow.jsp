<%@page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Shop</title>
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/style.css" />" rel="stylesheet">
</head>

<jsp:include page="../fragments/header.jsp" />

<body>
<c:if test="${not empty msg}">
        <div class="${css}">
            <strong>${msg}</strong>
        </div>
</c:if>

<h1>Shop details</h1>

<table style="margin-left: 15px">
    <tr class="oddLine">
        <th>ID</th>
        <td>${shopAttribute.id}</td>
    </tr>
    <tr class="evenLine">
        <th>Name</th>
        <td>${shopAttribute.name}</td>
    </tr>
    <tr class="oddLine">
        <th>Address</th>
        <td>${shopAttribute.address}</td>
    </tr>
    <tr class="evenLine">
        <th>Tel</th>
        <td>${shopAttribute.tel}</td>
    </tr>
    <tr class="oddLine">
        <th>Working time</th>
        <td>${shopAttribute.workingTime}</td>
    </tr>
</table>

<spring:url var="updateUrl" value="/shops/${shopAttribute.id}/update" />
<spring:url var="deleteUrl" value="/shops/${shopAttribute.id}/delete" />
<c:url var="employeesUrl" value="${shopAttribute.id}/employees" />
<c:url var="shopListUrl" value="/shops" />


<div style="margin-top: 15px; height: 30px">

    <div  style="margin-left: 15px; display: inline;">
        <a href="${updateUrl}">
            <button>Edit</button>
        </a>
    </div>

    <div style="margin-left: 15px; display: inline; width: 150px ">
        <a href="${deleteUrl}">
            <button>Delete</button>
        </a>
    </div>

    <div style="margin-left: 15px; display: inline;">
        <a href="${employeesUrl}">
            <button>Employees list</button>
        </a>
    </div>

    <div style="margin-left: 15px; display: inline;">
        <a href="${shopListUrl}">
            <button>Return to shop list</button>
        </a>
    </div>


</div>

<jsp:include page="../fragments/footer.jsp" />

</body>

</html>
