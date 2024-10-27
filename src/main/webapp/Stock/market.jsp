<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>株式市場分析</title>
	<link href="css/stock.css" rel="stylesheet">
</head>
<body>
 	<header>
 	</header>
 	<main>
 		<!-- 登録データ表 -->
 		<form action="" method="post">
	 		<div>
	 			<table>
	 				<thead>
	 					<tr>
	 						<td rowspan="2">日付</td>
	 						<td rowspan="2"><a href="https://nikkei225jp.com/data/vix.php" target="_blank">日経平均株価</a></td>
	 						<td rowspan="2">東証一部出来高（百万株）</td>
	 						<td rowspan="2">日経VI</td>
	 						<td rowspan="2">VIX</td>
	 						<td rowspan="2"><a href="https://s.kabutan.jp/futures/%E6%97%A5%E7%B5%8C225%E5%85%88%E7%89%A9/historical_prices/daily/" target="_blank">金先物</a></td>
	 						<td rowspan="2">日経平均先物</td>
	 						<td rowspan="2">ドル円</td>
	 						<td rowspan="2">FearAndGreed指数</td>
	 						<td colspan="2">10年国債利回り</td>
	 						<td colspan="2">政策金利</td>
	 						<td colspan="2">CPI</td>
	 						<td colspan="2">コアCPI</td>
	 					</tr>
	 					<tr>
	 						<td>日本</td>
	 						<td>米国</td>
	 						<td>日本</td>
	 						<td>米国</td>
	 						<td>日本</td>
	 						<td>米国</td>
	 						<td>日本</td>
	 						<td>米国</td>
	 					</tr>
	 				</thead>
	 				<tbody>
	 					<tr>
	 						<td>2024/04/11</td>
	 						<td>39442.63</td>
	 						<td>1,607</td>
	 						<td>20.73</td>
	 						<td>16.09</td>
	 						<td>2,350.05</td>
	 						<td>39,410</td>
	 						<td>155</td>
	 						<td>69</td>
	 						<td>0.796</td>
	 						<td>4.546</td>
	 						<td>0.1</td>
	 						<td>5.25</td>
	 						<td>2.8</td>
	 						<td>3.2</td>
	 						<td>2.8</td>
	 						<td>3.8</td>
	 					</tr>
	 				</tbody>
	 			</table>
	 		</div>
	 		<div>
	 			<input type="text">
	 			<input type="submit" value="登録">
	 		</div>
	 	</form>
 	</main>
 	<footer>
 	</footer>
</body>
</html>