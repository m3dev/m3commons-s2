<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title>Tutorial: Upload</title>
<link rel="stylesheet" type="text/css" href="${f:url('/css/sa.css')}" />
</head>
<body>

<h1>Tutorial: Upload</h1>

<html:errors/>

<s:form enctype="multipart/form-data">
<input type="file" name="formFile" /><br />
<c:forEach varStatus="s" begin="0" end="1">
<input type="file" name="formFiles[${s.index}]" /><br />
</c:forEach>

<input type="submit" name="upload" value="アップロード"/>
</s:form>
</body>
</html>