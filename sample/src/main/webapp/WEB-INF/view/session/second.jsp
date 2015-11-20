<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title>Tutorial: Session second</title>
<link rel="stylesheet" type="text/css" href="${f:url('/css/sa.css')}" />
</head>
<body>

<h1>Tutorial: Session second</h1>

<html:errors/>
<s:form>
<table>
<tr><td>First</td><td>${f:h(first)}</td></tr>
<tr><td>Second</td><td><html:text property="second"/></td></tr>
</table>
<input type="submit" name="goThird" value="Thirdへ"/>
<input type="submit" name="index" value="戻る"/>
</s:form>
</body>
</html>