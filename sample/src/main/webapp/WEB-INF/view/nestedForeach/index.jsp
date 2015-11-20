<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title>Tutorial: Nested Foreach</title>
<link rel="stylesheet" type="text/css" href="${f:url('/css/sa.css')}" />
</head>
<body>

<h1>Tutorial: Nested Foreach</h1>

<table border="1">
<c:forEach var="mapItems" items="${mapItemsItems}">
    <tr>
    <c:forEach var="m" items="${mapItems}">
        <td>${f:h(m.id)}</td>
        <td>${f:h(m.name)}</td>
    </c:forEach>
    </tr>
</c:forEach>
</table>

</body>
</html>