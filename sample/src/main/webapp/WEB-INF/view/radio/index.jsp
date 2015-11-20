<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title>Tutorial: Radio</title>
<link rel="stylesheet" type="text/css" href="${f:url('/css/sa.css')}" />
</head>
<body>

<h1>Tutorial: Radio</h1>

<html:errors/>
<s:form>
<html:radio property="radio" value="1"/>One
<html:radio property="radio" value="2"/>Two
<html:radio property="radio" value="3"/>Three
<br />
${f:h(radio)}<br />
<input type="submit" name="submit" value="サブミット"/>
</s:form>
</body>
</html>