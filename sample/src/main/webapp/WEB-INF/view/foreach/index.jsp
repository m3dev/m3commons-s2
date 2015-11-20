<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title>Tutorial: Foreach</title>
<link rel="stylesheet" type="text/css" href="${f:url('/css/sa.css')}" />
</head>
<body>

<h1>Tutorial: Foreach</h1>


<table border="1">
<c:forEach var="m" varStatus="s" items="${mapItems}">
<tr style="background-color:${s.index % 2 == 0 ? 'pink' : 'yellow'}">
<td>${f:h(m.id)}</td>
<td>${f:h(m.name)}</td>
<td><s:link href="result/${m.id}">結果ページへ</s:link></td>
</tr>
</c:forEach>
</table>

</body>
</html>