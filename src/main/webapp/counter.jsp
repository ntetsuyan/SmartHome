<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="cnt" scope="session" class="bean.CounterBean" />

<html>
<head>
	<title>カウンター</title>
</head>
<body>
	<h2><jsp:getProperty name="cnt" property="count" />回目のアクセスです</h2>
</body>
</html>