<%@page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Transformer</title>
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/style.css" />" rel="stylesheet">
</head>

<jsp:include page="../fragments/header.jsp" />

<body>
<c:if test="${not empty msg}">
    <div class="${css}">
        <strong>${msg}</strong>
    </div>
</c:if>

<h1>Transformer details</h1>

<table style="margin-left: 15px">

    <tr class="oddLine">
        <th>ID</th>
        <td>${transformerAttribute.id}</td>
    </tr>
    <tr class="evenLine">
        <th>Input voltage</th>
        <td>${transformerAttribute.inputVoltage}</td>
    </tr>
    <tr class="oddLine">
        <th>Output voltage</th>
        <td>${transformerAttribute.outputVoltage}</td>
    </tr>
    <tr class="evenLine">
        <th>Output current</th>
        <td>${transformerAttribute.outputCurrent}</td>
    </tr>
    <tr class="oddLine">
        <th>Mass</th>
        <td>${transformerAttribute.mass}</td>
    </tr>
    <tr class="evenLine">
        <th>Price</th>
        <td>${transformerAttribute.price}</td>
    </tr>

</table>



<spring:url var="updateUrl" value="/transformers/${transformerAttribute.id}/update" />
<spring:url var="deleteUrl" value="/transformers/${transformerAttribute.id}/delete" />
<c:url var="listUrl" value="/transformers" />


<div class="button-group">
    <button><a href="${updateUrl}">Edit</a></button>
    <button><a href="${deleteUrl}">Delete</a></button>
    <button><a href="${listUrl}">Back</a></button>
</div>

<jsp:include page="../fragments/footer.jsp" />

</body>

</html>
