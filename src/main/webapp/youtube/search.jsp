<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="beanYoutube" scope="request" class="bean.beanYoutube" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="Cache-Control" content="no-cache">
	<title>Insert title here</title>
</head>
<body>

<form name="frmSearch" action="/SmartHome/Youtube_search_java" method="post">
	YouTubeAPIkey = AIzaSyBUCK9gLjBJogSP7kK4CPR-By-0xnddK8s<br/>
	<input type="submit" value="検索jsp"><br/>
	<input type="text" value="https://www.googleapis.com/youtube/v3/videos?id=zvrsGCyPvF0&key=AIzaSyBUCK9gLjBJogSP7kK4CPR-By-0xnddK8s&part=statistics" /><br/>
	<input type="text" value="https://www.googleapis.com/youtube/v3/videos?id=zvrsGCyPvF0&key=AIzaSyBUCK9gLjBJogSP7kK4CPR-By-0xnddK8s&part=snippet" /><br/>
	
	<%= beanYoutube.getKind() %><br/>
	<%= beanYoutube.getTitle() %>
</form>

</body>
</html>