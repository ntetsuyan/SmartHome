<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
%>
<html>
<head>
	<title>確認画面</title>
</head>
<body>
	<h2>入力情報を確認して登録ボタンを押してください</h2>
	氏名：<strong><%= request.getParameter("name") %></strong><br/>
	パスワード：<strong><%= request.getParameter("pass") %></strong><br/>
	年齢：<strong><%= request.getAttribute("age") %></strong><br />
	サーブレット＆JSPで生成
	
</body>
</html>