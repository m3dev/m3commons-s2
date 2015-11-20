<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title>Tutorial: Session index</title>
<link rel="stylesheet" type="text/css" href="${f:url('/css/sa.css')}" />
</head>
<body>

<h1>Tutorial: Session index</h1>
<html:errors/>
<s:form>
First:<html:text property="first"/><br />
<input type="submit" name="goSecond" value="Secondへ"/>
</s:form>
</body>
</html>