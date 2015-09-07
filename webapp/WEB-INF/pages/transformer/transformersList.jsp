<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/style.css" />" rel="stylesheet">
    <title>transformersPage</title>
</head>

<body>

<c:if test="${not empty msg}">
    <div class="${css}">
        <strong>${msg}</strong>
    </div>
</c:if>


<jsp:include page="../fragments/header.jsp"/>

<c:url var="addUrl" value="/transformers/add"/>
<table>
    <thead>
    <tr>
        <th>id</th>
        <th>Input voltage</th>
        <th>Output voltage</th>
        <th>Output current</th>
        <th>Mass</th>
        <th>Price</th>
        <th colspan="3"></th>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="transformers" type="nom.alekseyLevchenko.transformersManager.domain.Transformer"--%>

    <c:forEach items="${transformers}" var="transformer" varStatus="varStatus">
        <c:url var="editUrl" value="/transformers/${transformer.id}/update"/>
        <c:url var="deleteUrl" value="/transformers/${transformer.id}/delete"/>
        <c:url var="show" value="/transformers/${transformer.id}"/>

        <tr class="${varStatus.index % 2 == 0 ? 'oddLine' : 'evenLine'}">
            <td>${transformer.id}</td>
            <td>${transformer.inputVoltage}</td>
            <td>${transformer.outputVoltage}</td>
            <td>${transformer.outputCurrent}</td>
            <td>${transformer.mass}</td>
            <td>${transformer.price}</td>

            <td class="button-cell">
                <form action="${show}" method="get">
                    <button>Show</button>
                </form>
            </td>
            <td class="button-cell">
                <form action="${editUrl}" method="get">
                    <button>Edit</button>
                </form>
            </td>
            <td class="button-cell">
                <form action="${deleteUrl}" method="get">
                    <button>Delete</button>
                </form>
            </td>

        </tr>
    </c:forEach>
    </tbody>

</table>
<p>

<form action="${addUrl}" method="get">
    <button>Add</button>
</form>
</p>

<jsp:include page="../fragments/footer.jsp"/>

</body>

</html>
