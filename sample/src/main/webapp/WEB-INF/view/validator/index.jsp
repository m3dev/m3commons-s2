<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title>Tutorial: Validator</title>
<link rel="stylesheet" type="text/css" href="${f:url('/css/sa.css')}" />
</head>
<body>
<h1>Tutorial: Validator</h1>


<html:errors/>
<s:form>
<table>
<tr>
<td>バイト:</td><td><html:text property="byteText" errorStyleClass="err" /></td>
</tr>
<tr>
<td>短整数:</td><td><html:text property="shortText" errorStyleClass="err" /></td>
</tr>
<tr>
<td>整数:</td><td><html:text property="integerText" errorStyleClass="err" /></td>
</tr>
<tr>
<td>長整数:</td><td><html:text property="longText" errorStyleClass="err" /></td>
</tr>
<tr>
<td>短精度実数:</td><td><html:text property="floatText" errorStyleClass="err" /></td>
</tr>
<tr>
<td>長精度実数:</td><td><html:text property="doubleText" errorStyleClass="err" /></td>
</tr>
<tr>
<td>日付:</td><td><html:text property="dateText" errorStyleClass="err" /></td>
</tr>
<tr>
<td>クレジットカード(9999999999999999の形式):</td><td><html:text property="creditcardText" errorStyleClass="err" /></td>
</tr>
<tr>
<td>メールアドレス:</td><td><html:text property="emailText" errorStyleClass="err" /></td>
</tr>
<tr>
<td>URL:</td><td><html:text property="urlText" errorStyleClass="err" /></td>
</tr>
<tr>
<td>整数(3～10):</td><td><html:text property="intRangeText" errorStyleClass="err" /></td>
</tr>
<tr>
<td>長整数(3～10):</td><td><html:text property="longRangeText" errorStyleClass="err" /></td>
</tr>
<tr>
<td>短精度実数(3.0～10.0):</td><td><html:text property="floatRangeText" errorStyleClass="err" /></td>
</tr>
<tr>
<td>倍精度実数(3.0～10.0):</td><td><html:text property="doubleRangeText" errorStyleClass="err" /></td>
</tr>
<tr>
<td>文字列の長さの最小値が3:</td><td><html:text property="minlengthText" errorStyleClass="err" /></td>
</tr>
<tr>
<td>文字列の長さの最大値が10:</td><td><html:text property="maxlengthText" errorStyleClass="err" /></td>
</tr>
<tr>
<td>文字列のバイト長の最小値が3:</td><td><html:text property="minbytelengthText" errorStyleClass="err" /></td>
</tr>
<tr>
<td>文字列のバイト長の最大値が10:</td><td><html:text property="maxbytelengthText" errorStyleClass="err" /></td>
</tr>
<tr>
<td>電話番号:</td><td><html:text property="phoneText" errorStyleClass="err" /></td>
</tr>
<tr>
<td>validwhen1Text:</td><td><html:text property="validwhen1Text" errorStyleClass="err" /></td>
</tr>
<tr>
<td>validwhen2Text<br />
(validwhen1Textが入力されたときには入力必須):</td><td><html:text property="validwhen2Text" errorStyleClass="err" /></td>
</tr>
</table>
<input type="submit" name="submit" value="サブミット"/>
</s:form>
</body>
</html>