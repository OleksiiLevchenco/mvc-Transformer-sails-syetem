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
<c:choose>
    <c:when test="${not empty shopAttribute.id}">
        <h1>Update shop details</h1>
    </c:when>
    <c:otherwise>
        <h1>Add new shop</h1>
    </c:otherwise>
</c:choose>

<c:url var="actionUrl" value="/shops"/>

<springForm:form modelAttribute="shopAttribute" method="post" action="${actionUrl}">
    <table>

        <c:if test="${not empty shopAttribute.id}">
            <tr>
                <td><springForm:label path="id"> id </springForm:label></td>
                <td><form:input path="id" readonly="true" /></td>
            </tr>
        </c:if>

        <tr>
            <td><springForm:label path="name">Name</springForm:label></td>
            <td><springForm:input path="name"/></td>
            <td><springForm:errors path="name" cssClass="error"/></td>
        </tr>

        <tr>
            <td><springForm:label path="address">Address</springForm:label></td>
            <td><springForm:input path="address" /></td>
            <td><springForm:errors path="address" cssClass="error"/></td>
        </tr>
        <tr>
            <td><springForm:label path="tel">Tel</springForm:label></td>
            <td><springForm:input path="tel"/></td>
            <td ><springForm:errors path="tel" cssClass="error"/></td>
        </tr>
        <tr>
            <td><springForm:label path="workingTime">Working time</springForm:label></td>
            <td><springForm:input path="workingTime"/></td>
            <td ><springForm:errors path="workingTime" cssClass="error"/></td>
        </tr>
    </table>

    <input type="submit" value="Save">

</springForm:form>

<jsp:include page="../fragments/footer.jsp" />

</body>

</html>
