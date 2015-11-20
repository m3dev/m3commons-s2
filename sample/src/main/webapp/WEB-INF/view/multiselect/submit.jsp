<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title>Tutorial: Multiselect submit</title>
<link rel="stylesheet" type="text/css" href="${f:url('/css/sa.css')}" />
</head>
<body>

<h1>Tutorial: Multiselect submit</h1>

<html:errors/>
<s:form>
select1:${f:h(select1)}<br />
select2:<br />
<html:select property="select2" multiple="true" size="3">
<c:forEach var="v" items="${select2List}">
<html:option value="${v.value}">${f:h(v.label)}</html:option>
</c:forEach>
</html:select><br />
<input type="submit" name="submit2" value="サブミット2"/>
</s:form>
</body>
</html>