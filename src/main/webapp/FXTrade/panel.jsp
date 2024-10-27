<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="beanTrade" scope="request" class="bean.beanTrade" />


<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="Cache-Control" content="no-cache">
	<link rel="stylesheet" href="./css/trade.css">
	<title>SmartHome</title>
	<style></style>
</head>

<script type="text/javascript">
function fncEnvironmentDisplay(){
	document.frmTrade.hid_display.value='display';
	document.frmTrade.tradetime.value = "";
	return true;
}
function fncClickTradeJournalList(i){
	document.frmTrade.tradedate.value = eval('document.frmTrade.hd_entrydate_' + i + '.value');
	document.frmTrade.environment.value = "";
	document.frmTrade.scinario.value = "";
	document.frmTrade.result.value = "";
	
	document.frmTrade.tradetime.value = eval('document.frmTrade.hd_entrytime_' + i + '.value');
	document.frmTrade.entrygrounds.value = eval('document.frmTrade.hd_entrygrounds_' + i + '.value');
	document.frmTrade.entryprice.value = eval('document.frmTrade.hd_entryprice_' + i + '.value');
	document.frmTrade.cutlossgrounds.value = eval('document.frmTrade.hd_cutlossgrounds_' + i + '.value');
	document.frmTrade.cutlossline.value = eval('document.frmTrade.hd_cutlossline_' + i + '.value');
	document.frmTrade.profitlosspips.value = eval('document.frmTrade.hd_profitloss_pips_' + i + '.value');
	document.frmTrade.profitlossprice.value = eval('document.frmTrade.hd_profitlossprice_' + i + '.value');
	document.frmTrade.resultpertrade.value = eval('document.frmTrade.hd_result_' + i + '.value');
	document.frmTrade.submit();
	
}
</script>

<body>

<form name="frmTrade" action="/SmartHome/FXtrade" method="post">
<div style="display:flex;">
	<div class="div_frame">
		<div style="display:flex;">
			<div class="h1">トレード日</div>
			<div>
				<input type="date" name="tradedate" value="<%= beanTrade.getTradedate() %>">
				<input type="submit" onClick="fncEnvironmentDisplay()" value="取得">
				<input type="hidden" name="hid_display" value="update">
			</div>
		</div>
		<div style="display:flex;">
			<div class="h1">環境認識</div>
			<div><textarea class="textarea1" rows="4" name="environment"><%= beanTrade.getEnvironment() %></textarea></div>
		</div>
		<div style="display:flex;">
			<div class="h1">トレード戦略</div>
			<div><textarea class="textarea1" rows="4" name="scinario"><%= beanTrade.getScinario() %></textarea></div>
		</div>
		<div style="display:flex;">
			<div class="h1">考察</div>
			<div><textarea class="textarea1" rows="4" name="result"><%= beanTrade.getResult() %></textarea></div>
		</div>
		<div>
			<input type="submit" value="更新">
			
		</div>
	</div>
	<div class="h2">
		<div>検証手法</div><br/>
		<textarea class="textarea4" rows="13" name="verifymethod"><%= beanTrade.getVerifyMethod() %></textarea>
	</div>
</div>
<div style="display:flex;">
	<div class="div_frame">
		<table>
			<tr>
				<td>トレード時刻</td>
				<td><input type="time" name="tradetime" value="<%= beanTrade.getTradeTime() %>"></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td>エントリ根拠</td>
				<td><textarea  name="entrygrounds" class="textarea2"><%= beanTrade.getEntryGrounds() %></textarea></td>
				<td>エントリ価格</td>
				<td><input type="number" name="entryprice" step="0.001" class="textarea3" value="<%= beanTrade.getEntryPrice() %>"/></td>
			</tr>
			<tr>
				<td>損切り根拠</td>
				<td><textarea  name="cutlossgrounds" class="textarea2"><%= beanTrade.getCutlossGrounds() %></textarea></td>
				<td>損切りライン</td>
				<td><input type="number" name="cutlossline" step="0.001" class="textarea3" value="<%= beanTrade.getCutlossLine() %>"/></td>
			</tr>
			<tr>
				<td>損益pips</td>
				<td><input type="number" name="profitlosspips" class="textarea3" value="<%= beanTrade.getProfitloss_pips() %>"/></td>
				<td>損益価格</td>
				<td><input type="number" name="profitlossprice" class="textarea3" value="<%= beanTrade.getProfitloss_price() %>"/></td>
			</tr>
			<tr>
				<td>考察</td>
				<td colspan="3"><textarea  name="resultpertrade" class="textarea1"><%= beanTrade.getResultPerTrade() %></textarea></td>
			</tr>
		</table>
	</div>
	
	
	
	<div>
		<div>
		トータル損益<input type="number" name="total_profitloss_price" size="10" /><br/>
		</div>
		<div style="height:20vh; overflow-y:auto;">
			<table border=1>
				<tr bgcolor="lightgreen">
					<td>取引日時</td>
					<td>エントリ価格</td>
					<td>損切りライン</td>
					<td>損益pips</td>
					<td></td>
				</tr>
				<%= beanTrade.getTradeJounarlList() %>
			</table>
		</div>
	</div>
</div>

</form>

</body>
</html>


