<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title>Tutorial: Multiselect index</title>
<link rel="stylesheet" type="text/css" href="${f:url('/css/sa.css')}" />
</head>
<body>

<h1>Tutorial: Multiselect index</h1>

<html:errors/>
<s:form>
select1:<br />
<html:select property="select1" multiple="true" size="3">
<html:option value="1">One</html:option>
<html:option value="2">Two</html:option>
<html:option value="3">Three</html:option>
</html:select>
<br />
<input type="submit" name="submit" value="サブミット"/>
</s:form>
</body>
</html>