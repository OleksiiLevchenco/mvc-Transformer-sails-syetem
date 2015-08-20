<%@page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <title>Spring MVC Form Handling Example</title>

    <spring:url value="/resources/css/style.css" var="coreCss"/>
    <link href="${coreCss}" rel="stylesheet"/>

</head>

<spring:url value="/" var="urlHome"/>
<spring:url value="/shops" var="shopListUrl"/>
<spring:url value="/transformer/list" var="transformerListUrl"/>


<nav>

    <a href="${urlHome}">TSS Transformers Sails System</a> |
    <a href="${shopListUrl}">All shops</a> |
    <a href="${transformerListUrl}">Transformers catalog</a>

</nav>

<hr>