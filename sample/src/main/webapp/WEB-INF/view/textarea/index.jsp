<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title>Tutorial: Textarea</title>
<link rel="stylesheet" type="text/css" href="${f:url('/css/sa.css')}" />
</head>
<body>

<h1>Tutorial: Textarea</h1>

<html:errors/>
<s:form>
<html:textarea property="textarea"/><br />
${f:br(f:nbsp(f:h(textarea)))}<br />
<input type="submit" name="submit" value="サブミット"/>
</s:form>
</body>
</html>