<%@page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <title>Spring MVC Form Handling Example</title>
    <link href="../../../resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <spring:url value="/resources/css/style.css" var="coreCss"/>
    <link href="${coreCss}" rel="stylesheet"/>



</head>

<spring:url value="/" var="urlHome"/>
<spring:url value="/shops" var="shopListUrl"/>
<spring:url value="/transformers" var="transformerListUrl"/>

<div class="container">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <a class="navbar-brand" href="${urlHome}" title="Transformers sails system">TSS</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li <%--class="active"--%>><a href="${shopListUrl}">All shops</a></li>
                    <li><a href="${transformerListUrl}">Transformers catalog</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="mailto:oleksii.levchenko23@gmail.com">Contact Us</a></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>


<%--<nav>--%>

    <%--<a href="${urlHome}">Tss</a> |--%>
    <%--<a href="${shopListUrl}">All shops</a> |--%>
    <%--<a href="${transformerListUrl}">Transformers catalog</a>--%>

<%--</nav>--%>

<hr>