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
	
	<%
		String age = request.getParameter("age");
		if(age.equals("child")){
			age = "18歳未満";
		}else{
			age = "18歳以上";
		}	
	%>
	年齢：<strong><%= age %></strong><br />
	
	JSPで生成
	
</body>
</html>