<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title>Tutorial: Multibox submit</title>
<link rel="stylesheet" type="text/css" href="${f:url('/css/sa.css')}" />
</head>
<body>

<h1>Tutorial: Multibox submit</h1>

<html:errors/>
<s:form>
check1:${f:h(check1)}<br />
check2:<br />
<c:forEach var="v" items="${check2List}">
<html:multibox property="check2" value="${v.value}"/>${f:h(v.label)}
</c:forEach><br />
<input type="submit" name="submit2" value="サブミット2"/>
</s:form>
</body>
</html>