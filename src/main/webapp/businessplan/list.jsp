<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="beanBP" scope="request" class="bean.beanBusinessPlan" />

<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="Cache-Control" content="no-cache">
	<link rel="stylesheet" href="./css/top.css">
	<link rel="stylesheet" href="./css/businessplan.css">
	<title>SmartHome -事業計画-</title>
	<style></style>
</head>

<script type="text/javascript">

function fncSingleClickList(i){
	alert('aaa')
}

function fncDoubleClickList(i){
	alert(i)
}

function fncClickBusinessPlanListDelete(i){
	var deleteitem = document.getElementById('hd_delete_' + i);
	deleteitem.value=1;
	document.frmBusinessPlanList.submit();
}
</script>

<body>
<form name="frmBusinessPlanList" action="/SmartHome/BusinessPlan" method="post">
<div style="display:flex;">
	<div style="width:70%;">
		<div>
			<a href="./top.html" class="cp_link">
				<span data-text="メニュー画面へ">メニュー画面へ</span>
			</a>
			<input type="submit" value="更新">
			
		</div>
		<ul class="ul_square">
			<li id="li0" draggable="true">
				<div class="div_list_line">
					<div class="div_list_cell1">No.</div>
					<div class="div_list_cell2">事業計画</div>
					<div class="div_list_cell3">ペース</div>
					<div class="div_list_cell4">単位</div>
					<div class="div_list_cell5">間隔</div>
					<div class="div_list_cell6">進捗率</div>
					<div class="div_list_cell7">移動</div>
				</div>
			</li>
    		<%= beanBP.getBusinessPlanList() %>
		</ul>
		<input type="hidden" name="hid_listcount" value="<%= beanBP.getBusinessPlanCount()%>">
	</div>
	
	<div class="div_memostyle" style="width:30%;">
		<div>事業状況メモ</div><br/>
		<textarea rows="40" name="businessmemo"></textarea>
	</div>
</div>
</form>

</body>
</html>