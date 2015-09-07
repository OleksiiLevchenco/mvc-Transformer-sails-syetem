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

<body>
<jsp:include page="../fragments/header.jsp" />

<c:choose>
    <c:when test="${not empty transformerAttribute.id}">
        <h1>Update transformer details</h1>
    </c:when>
    <c:otherwise>
        <h1>Add new transformer</h1>
    </c:otherwise>
</c:choose>

<c:url var="actionUrl" value="/transformers"/>
<c:url var="backUrl" value="/transformers"/>

<springForm:form modelAttribute="transformerAttribute" method="post" action="${actionUrl}">
    <table>

        <c:if test="${not empty transformerAttribute.id}">
            <tr>
                <td><springForm:label path="id"> id </springForm:label></td>
                <td><springForm:input path="id" readonly="true"/></td>
            </tr>
        </c:if>

        <tr>
            <td><springForm:label path="inputVoltage">Input voltage</springForm:label></td>
            <td><springForm:input path="inputVoltage"/></td>
            <td><springForm:errors path="inputVoltage" cssClass="error"/></td>
        </tr>

        <tr>
            <td><springForm:label path="outputVoltage">Output voltage</springForm:label></td>
            <td><springForm:input path="outputVoltage"/></td>
            <td><springForm:errors path="outputVoltage" cssClass="error"/></td>
        </tr>
        <tr>
            <td><springForm:label path="outputCurrent">Output current</springForm:label></td>
            <td><springForm:input path="outputCurrent"/></td>
            <td><springForm:errors path="outputCurrent" cssClass="error"/></td>
        </tr>
        <tr>
            <td><springForm:label path="mass">Mass</springForm:label></td>
            <td><springForm:input path="mass"/></td>
            <td><springForm:errors path="mass" cssClass="error"/></td>
        </tr>
        <tr>
            <td><springForm:label path="price">Price</springForm:label></td>
            <td><springForm:input path="price"/></td>
            <td><springForm:errors path="price" cssClass="error"/></td>
        </tr>

    </table>


    <button type="submit">Save</button>
</springForm:form>

<button>
    <a href="${backUrl}">
        <img class="icon" src="${pageContext.request.contextPath}/resources/res_images/Command-Undo-128.png"
             alt="Music.png"/>
    </a>
</button>

<jsp:include page="../fragments/footer.jsp" />

</body>

</html>
