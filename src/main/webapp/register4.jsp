<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="rb" scope="request" class="bean.RegisterBean" />

<html>
<head>
	<title>確認画面</title>
</head>

<script src="js/smarthome.js" ></script>

<body>
	<form action="/SmartHome/register41" method="post" name="mainview">
	
	<h2>入力情報を確認して登録ボタンを押してくださいjsp</h2>
	氏名：<input name="name" value="<jsp:getProperty name="rb" property="name" />"><br/>
	パスワード：<strong><%= request.getParameter("pass") %></strong><br/>
	年齢：<strong><%= rb.getJpnAge() %></strong><br />
	Bean＆サーブレット＆JSPで生成<br />
	
	<table border=1>
		<tr>
			<td width="100px">id</td>
			<td width="300px">memo</td>
		</tr>
		<%= rb.getListItem() %>
	</table>
	
	
		<input type="button" value="送信" onClick="return fncConfirm()">
		<input type="reset" value="取消">
	</form>
</body>
</html>