<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title>Tutorial: Add</title>
<link rel="stylesheet" type="text/css" href="${f:url('/css/sa.css')}" />
</head>
<body>

<h1>Employees</h1>
<table border="1">
    <tr>
    <th>ID</th>
    <th>氏名</th>
    <th>部署名</th>
    </tr>
<c:forEach var="emp" items="${employees}">
    <tr>
    <td>${f:h(emp.id)}</td>
    <td>${f:h(emp.name)}</td>
    <td>${f:h(emp.department.name)}</td>
    </tr>
</c:forEach>
</table>

</body>
</html>