<%@page pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Welcome to Super Agile Struts</title>

<link rel="stylesheet" type="text/css" href="${f:url('/css/sa.css')}" />
</head>
<body>

<h1>Welcome to Super Agile Struts Tutorial</h1>

<p>
Tomcatの設定はお済みですか?<br />
まだなら、
<a href="http://sastruts.seasar.org/fileReference.html#server">conf/server.xml</a>
をリンクの指示に従って修正してください。
</p>
<p>
文字化けになる場合も上記の設定を確認してください。
</p>
<p>
アクションフォームやDtoでHOT deployがうまくいかない場合は
<a href="http://sastruts.seasar.org/fileReference.html#context">conf/context.xml</a>
の設定を確認してください。
</p>

<ul>
<li><a href="http://sastruts.seasar.org/index.html">SAStrutsのサイト</a></li>
<li><a href="add/">足し算</a></li>
<li><a href="session/">セッションスコープのアクションフォーム</a></li>
<li><a href="redirect/">他のサイトを表示</a></li>
<li><a href="foreach/">繰り返し</a></li>
<li><a href="foreachButton/">ボタンを使った繰り返し</a></li>
<li><a href="nestedForeach/">ネストした繰り返し</a></li>
<li><a href="foreachUpdate/">更新可能な繰り返し</a></li>
<li><a href="nestedForeachUpdate/">更新可能なネストした繰り返し</a></li>
<li><a href="validator/">アノテーションを使った検証</a></li>
<li><a href="clientValidator/">クライアントサイド検証</a></li>
<li><a href="select/">選択リスト</a></li>
<li><a href="multiselect/">複数選択リスト</a></li>
<li><a href="checkbox/">チェックボックス</a></li>
<li><a href="multibox/">複数選択可能なチェックボックス</a></li>
<li><a href="radio/">ラジオボタン</a></li>
<li><a href="textarea/">テキストエリア</a></li>
<li><a href="condition/1">条件(id=1)</a></li>
<li><a href="condition/2">条件(id=2)</a></li>
<li><a href="token/">ボタンの2度押し対策</a></li>
<li><a href="upload/">ファイルアップロード</a></li>
<li><a href="download/">ファイルダウンロード</a></li>
<li><a href="tiles/">レイアウト</a></li>
<li><a href="protect/">保護されたページ</a></li>
<li><a href="logout/">ログアウト</a></li>
<li><a href="ajax/">Ajax</a></li>
</ul>
</body>
</html>