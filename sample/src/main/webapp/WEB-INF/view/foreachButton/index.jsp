<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title>Tutorial: Foreach Button</title>
<link rel="stylesheet" type="text/css" href="${f:url('/css/sa.css')}" />
</head>
<body>

<h1>Tutorial: Foreach Button</h1>

<table border="1">
<c:forEach var="m" items="${mapItems}">
<tr>
<td>${f:h(m.id)}</td>
<td>${f:h(m.name)}</td>
<td>
<s:form>
<html:hidden property="id" value="${m.id}"/>
<input type="submit" name="result" value="結果ページへ"/>
</s:form>
</td>
</tr>
</c:forEach>
</table>

</body>
</html>