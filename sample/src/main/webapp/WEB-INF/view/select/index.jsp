<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title>Tutorial: Select</title>
<link rel="stylesheet" type="text/css" href="${f:url('/css/sa.css')}" />
</head>
<body>

<h1>Tutorial: Select</h1>

<html:errors/>
<s:form>
<html:select property="select">
<html:option value="1">One</html:option>
<html:option value="2">Two</html:option>
<html:option value="3">Three</html:option>
</html:select>
<br />
${f:h(select)}<br />
<input type="submit" name="submit" value="サブミット"/>
</s:form>
</body>
</html>