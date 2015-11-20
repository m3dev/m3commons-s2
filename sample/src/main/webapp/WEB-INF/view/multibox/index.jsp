<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title>Tutorial: Multibox index</title>
<link rel="stylesheet" type="text/css" href="${f:url('/css/sa.css')}" />
</head>
<body>

<h1>Tutorial: Multibox index</h1>

<html:errors/>
<s:form>
check1:<br />
<html:multibox property="check1" value="1"/>One
<html:multibox property="check1" value="2"/>Two<br />
<input type="submit" name="submit" value="サブミット"/>
</s:form>
</body>
</html>