<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title>Tutorial: Protect Index</title>
<link rel="stylesheet" type="text/css" href="${f:url('/css/sa.css')}" />
</head>
<body>

<h1>Tutorial: Protect Index</h1>

<html:errors/>
このページは、tomcatとrole1ユーザが表示できます。<br />
<s:form>
<input type="submit" name="tomcat"
  value="tomcatユーザだけが押せます"/>
</s:form>
</body>
</html>