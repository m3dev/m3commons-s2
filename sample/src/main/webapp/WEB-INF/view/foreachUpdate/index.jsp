<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title>Tutorial: Foreach Update</title>
<link rel="stylesheet" type="text/css" href="${f:url('/css/sa.css')}" />
</head>
<body>

<h1>Tutorial: Foreach Update</h1>
<html:errors/>
<s:form>
<table border="1">
<c:forEach var="mapItems" items="${mapItems}">
<tr>
<td><html:text name="mapItems" property="id" indexed="true"/></td>
<td><html:text name="mapItems" property="name" indexed="true"/></td>
</tr>
</c:forEach>
</table><br />
<input type="submit" name="submit" value="サブミット"/>
</s:form>
</body>
</html>