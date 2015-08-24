<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Employee</title>
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/style.css" />" rel="stylesheet">
</head>

<body>

<jsp:include page="../fragments/header.jsp"/>

<c:choose>
    <c:when test="${not empty employeeAttribute.id}">
        <h1>Edit employee</h1>
    </c:when>
    <c:otherwise>
        <h1>Add new employee</h1>
    </c:otherwise>
</c:choose>


<c:choose>
    <c:when test="${not empty shopId}">
        <c:url var="actionUrl" value="/shops/${shopId}/employees"/>
    </c:when>
    <c:otherwise>
        <c:url var="actionUrl" value="/shops/${employeeAttribute.shop.id}/employees"/>
    </c:otherwise>
</c:choose>


<%--<springForm:form modelAttribute="employeeAttribute" method="post" action="${actionUrl}">--%>
<springForm:form modelAttribute="employeeAttribute" method="post" action="${actionUrl}" enctype="multipart/form-data">

    <table>
        <c:if test="${not empty employeeAttribute.id}">
            <tr>
                <td><springForm:label path="id"> id </springForm:label></td>
                <td>
                    <form:input path="id" readonly="true" />

                </td>
            </tr>
            <form:hidden path="imgUrl" />
        </c:if>

        <tr>
            <td><springForm:label path="name">Name</springForm:label></td>
            <td><springForm:input path="name"/></td>
            <td><springForm:errors path="name" cssClass="error"/></td>
        </tr>



        <tr>
            <th>Image</th>
            <td>
                <%--accept="image/jpeg,image/png,image/gif"--%>
                <input type="file" name="file">
            </td>
            <td><springForm:errors path="imgUrl" cssClass="error"/></td>
        </tr>

    </table>

    <input type="submit" value="Save">

</springForm:form>

<jsp:include page="../fragments/footer.jsp"/>
</body>

</html>
