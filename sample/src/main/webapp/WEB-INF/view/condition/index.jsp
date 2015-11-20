<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title>Tutorial: Condition: ${f:h(id)}</title>
<link rel="stylesheet" type="text/css" href="${f:url('/css/sa.css')}" />
</head>
<body>

<h1>Tutorial: Condition: ${f:h(id)}</h1>

<html:errors/>
"id" is ${f:h(id)}.<br />
<c:if test="${id != null}">
"id" is not null.
</c:if>
<c:if test="${id == null}">
"id" is null.
</c:if>
<br />
<c:choose>
<c:when test="${id == '1'}">
"id" is one.
</c:when>
<c:when test="${id == '2'}">
"id" is two.
</c:when>
<c:otherwise>
"id" is other.
</c:otherwise>
</c:choose>
</body>
</html>