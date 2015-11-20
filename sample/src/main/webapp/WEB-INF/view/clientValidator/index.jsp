<%@page pageEncoding="UTF-8"%>
<html>
<head>
<html:javascript formName="clientValidatorActionForm_submit"/>
<html:javascript formName="clientValidatorActionForm_submit2"/>
<title>Tutorial: Client Validator</title>
<link rel="stylesheet" type="text/css" href="${f:url('/css/sa.css')}" />
</head>
<body>

<h1>Tutorial: Client Validator</h1>

<html:errors/>
<s:form>
<table>
<tr><td>aaa</td><td><html:text property="aaa"/></td></tr>
<tr><td>bbb</td><td><html:text property="bbb"/></td></tr>
</table>
<s:submit property="submit" clientValidate="true">aaaが必須</s:submit>
<s:submit property="submit2" clientValidate="true">bbbが必須</s:submit>
</s:form>
</body>
</html>