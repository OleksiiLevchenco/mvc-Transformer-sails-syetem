<%--
  Created by IntelliJ IDEA.
  User: Main
  Date: 03.08.2015
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
  <title>Success</title>
  <link href="<c:url value="${pageContext.request.contextPath}/resources/css/style.css" />" rel="stylesheet">
</head>

<body>

<c:if test="${not empty msg}">
  <div class="${css}">
    <strong>${msg}</strong>
  </div>
</c:if>

<c:choose>
  <c:when test="${!null == returnUrl}">
    <c:url var="returnUrl" value="${returnUrl}"/>
  </c:when>
  <c:otherwise>
    <c:choose>
      <c:when test="${entityType == 'Transformer'}"> <c:url var="returnUrl" value="/transformer/list"/> </c:when>
      <c:when test="${entityType == 'Shop'}"> <c:url var="returnUrl" value="/shop/list"/> </c:when>
      <c:when test="${entityType == 'Employee'}"> <c:url var="returnUrl" value="/shop/employeesList?shopId=${shopId}"/> </c:when>
    </c:choose>
  </c:otherwise>
</c:choose>




<c:if test="${pageType == 'add'}">
    <h2 class="success">
      ${entityType} has been successfully added.
    </h2>
</c:if>

<c:if test="${pageType == 'edit'}">
  <h2 class="success">
      ${entityType} has been successfully update.
  </h2>
</c:if>

<c:if test="${pageType == 'delete'}">
  <h2 class="success">
      ${entityType} has been successfully deleted.
  </h2>
</c:if>

<p>Return to <a href="${returnUrl}">${entityType} List</a></p>
</body>

</html>
