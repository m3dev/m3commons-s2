<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title>Tutorial: Ajax</title>
<link rel="stylesheet" type="text/css" href="${f:url('/css/sa.css')}" />
<script src="${f:url('/js/jquery.js')}"></script>
</head>
<body>

<h1>Tutorial: Ajax</h1>
<span id="message"></span><br />
<input type="button" value="hello"
    onclick="$('#message').load('${f:url('hello')}');"/>
</body>
</html>