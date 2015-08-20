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
<c:if test="${pageType == 'add'}"><h2>Add transformer</h2></c:if>
<c:if test="${pageType == 'edit'}"><h2>Edit transformer</h2></c:if>
<c:if test="${pageType == 'delete'}"><h2>Delete transformer</h2></c:if>

<c:url var="transformersListUrl" value="/transformer/list"/>
<c:url var="addTransformerUrl" value="/transformer/add"/>

<c:set var="readeble" value="${false}"/>

<c:if test="${pageType == 'add'}">
    <c:url var="actionUrl" value="/transformer/add"/>
    <%--<c:set value="readeble" property="true"/>--%>
</c:if>
<c:if test="${pageType == 'edit'}">
    <c:url var="actionUrl" value="/transformer/edit"/>
    <%--<c:set value="readeble" property="true"/>--%>
</c:if>
<c:if test="${pageType == 'delete'}">
    <c:url var="actionUrl" value="/transformer/delete"/>
    <c:set var="readeble" value="${true}"/>
</c:if>


<springForm:form modelAttribute="transformerAttribute" method="post" action="${actionUrl}">
    <table>

        <c:if test="${!empty transformerAttribute.id}">
            <tr>
                <td><springForm:label path="id"> id </springForm:label></td>
                <td>
                    <form:input path="id" readonly="true" />
                    <%--<form:hidden path="id"/>--%>
                </td>
            </tr>
        </c:if>

        <tr>
            <td><springForm:label path="inputVoltage">Input voltage</springForm:label></td>
            <td><springForm:input path="inputVoltage" readonly="${readeble}"/></td>
            <td><springForm:errors path="inputVoltage" cssClass="error"/></td>
        </tr>

        <tr>
            <td><springForm:label path="outputVoltage">Output voltage</springForm:label></td>
            <td><springForm:input path="outputVoltage" readonly="${readeble}" /></td>
            <td><springForm:errors path="outputVoltage" cssClass="error"/></td>
        </tr>
        <tr>
            <td><springForm:label path="outputCurrent">Output current</springForm:label></td>
            <td><springForm:input path="outputCurrent" readonly="${readeble}"/></td>
            <td><springForm:errors path="outputCurrent" cssClass="error"/></td>
        </tr>
        <tr>
            <td><springForm:label path="mass">Mass</springForm:label></td>
            <td><springForm:input path="mass" readonly="${readeble}"/></td>
            <td><springForm:errors path="mass" cssClass="error"/></td>
        </tr>
        <tr>
            <td><springForm:label path="price">Price</springForm:label></td>
            <td><springForm:input path="price" readonly="${readeble}"/></td>
            <td><springForm:errors path="price" cssClass="error"/></td>
        </tr>
    </table>
    <c:if test="${pageType == 'add'}"> <input type="submit" value="Save"> </c:if>
    <c:if test="${pageType == 'edit'}"> <input type="submit" value="Save"> </c:if>
    <c:if test="${pageType == 'delete'}"> <input type="submit" value="Delete"> </c:if>

</springForm:form>

<p><a href="${transformersListUrl}">
    Return to transformers List
    <img src="${pageContext.request.contextPath}/resources/images/Music.png" alt="Music.png"
         style="width:40px; height:40px "/>
</a></p>


</body>

</html>
