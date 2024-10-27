package SmartHomeServlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseObject.daoTrade;
import DatabaseObject.dtoEnvironment;
import DatabaseObject.dtoTradeJournal;
import bean.beanTrade;

/**
 * Servlet implementation class register4
 */
@WebServlet("/FXtrade")
public class FXtrade extends HttpServlet {
	//private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//リクエストとレスポンスの文字コードを指定
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		
		//◆◆◆　前処理　◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆
		//画面項目用の変数宣言（なし：Bean格納用／_req：リクエスト格納用／_db：DB値格納用）して、
		//リクエストを変数に格納
		String strHidDisplay_req = "";
		strHidDisplay_req = req.getParameter("hid_display");
		
		String strTradeDate_req = "";
		if(req.getParameter("tradedate") != null){
			strTradeDate_req = req.getParameter("tradedate");
		}
		
		String strEnvironment_req = "";
		if(req.getParameter("environment") != null){
			strEnvironment_req = req.getParameter("environment");
		}
		
		String strScinario_req = "";
		if(req.getParameter("scinario") != null){
			strScinario_req = req.getParameter("scinario");
		}
		
		String strResult_req = "";
		if(req.getParameter("result") != null){
			strResult_req = req.getParameter("result");
		}
		
		String strVerifymethod = "";
		String strVerifymethod_req = "";
		String strVerifymethod_db = "";
		if(req.getParameter("verifymethod") != null) {
			strVerifymethod_req = req.getParameter("verifymethod");
		}
		
		String strTradeTime_req = "";
		if(req.getParameter("tradetime") != null) {
			strTradeTime_req = req.getParameter("tradetime");
		}
		
		String strEntryGrounds_req = "";
		if(req.getParameter("entrygrounds") != null) {
			strEntryGrounds_req = req.getParameter("entrygrounds");
		}
		
		double fltEntryPrice_req = 0;
		if(req.getParameter("entryprice") != null) {
			fltEntryPrice_req = Double.parseDouble(req.getParameter("entryprice"));
		}
		
		String strCutlossGrounds_req = "";
		if(req.getParameter("cutlossgrounds") != null) {
			strCutlossGrounds_req = req.getParameter("cutlossgrounds");
		}
		
		double fltCutlossLine_req = 0;
		if(req.getParameter("cutlossline") != null) {
			fltCutlossLine_req = Double.parseDouble(req.getParameter("cutlossline"));
		}
		
		String strProfitlossPips_req = "";
		if(req.getParameter("profitlosspips") != null) {
			strProfitlossPips_req = req.getParameter("profitlosspips");
		}
		
		String strProfitlossPrice_req = "";
		if(req.getParameter("profitlossprice") != null) {
			strProfitlossPrice_req = req.getParameter("profitlossprice");
		}
		
		String strResultPerTrade_req = "";
		if(req.getParameter("resultpertrade") != null) {
			strResultPerTrade_req = req.getParameter("resultpertrade");
		}
		
		//DAOとBeanのオブジェクト化
		daoTrade daoTrade = new daoTrade();
		beanTrade beanTrade = new beanTrade();
		
		//現在日付格納用の変数宣言と値設定
		String strYMD_now;
		//String strHMS_now;
		SimpleDateFormat objSdfYMD = new SimpleDateFormat("yyyy-MM-dd");
		//SimpleDateFormat objSdfHMS = new SimpleDateFormat("HH:mm:ss");
		Calendar objCal = Calendar.getInstance();
		objCal.setTimeZone(TimeZone.getTimeZone("JST"));
		strYMD_now = objSdfYMD.format(objCal.getTime());
		//strHMS_now = objSdfHMS.format(objCal.getTime());
		
		//処理日付格納用の変数宣言と値設定（初期値は現在）
		String strYMD = "";
		//String strHMS = "";
		if(strTradeDate_req.equals("")) {
			strYMD = strYMD_now;
		} else {
			strYMD = strTradeDate_req;
		}
		//if(strTradeTime_req.equals("")) {
		//	strHMS = strHMS_now;
		//} else {
		//	strHMS = strTradeTime_req;
		//}
		
		
		//◆◆◆　主処理　「環境認識（左上）」の定義◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆
		//DAOから記録データを取得
		dtoEnvironment dtoEnvItem = new dtoEnvironment();
		dtoEnvironment dtoEnvItem_db = new dtoEnvironment();
		dtoEnvironment dtoEnvItem_insert = new dtoEnvironment();
		dtoEnvItem_db = daoTrade.getEnvironment(strYMD);

		//if 初期表示（リクエストのtradedateが空）の場合、DAOの値を設定（現在日付）
		//   or リクエストのtradedateに値があり、Enviroment/Scinario/Resultが空の場合、DAOの値を設定（リクエスト日付）
		//   or リクエストのhidden_displayの値がdisplayの場合、DAOの値を設定（リクエスト日付）
		//if 更新がある場合、DBをリクエストで反映してから再度DAOから取得して設定
		if(strTradeDate_req.equals("") || (!(strTradeDate_req.equals("")) && strEnvironment_req.equals("") && strScinario_req.equals("") && strResult_req.equals("")) || strHidDisplay_req.equals("display")) {
			//DBから取得した値を設定
			dtoEnvItem = dtoEnvItem_db;
		} else if(strEnvironment_req != dtoEnvItem_db.getEnvironment() || strScinario_req != dtoEnvItem_db.getScinario() || strResult_req != dtoEnvItem_db.getResult()) {
			//DBをリクエストの値で更新
			dtoEnvItem_insert.setTradedate(strTradeDate_req);
			dtoEnvItem_insert.setEnvironment(strEnvironment_req);
			dtoEnvItem_insert.setScinario(strScinario_req);
			dtoEnvItem_insert.setResult(strResult_req);
			daoTrade.setEnvironment(dtoEnvItem_insert);
			
			//DBから取得した値を設定（リクエスト日付）
			dtoEnvItem = daoTrade.getEnvironment(strTradeDate_req);
		}
		
		
		//◆◆◆　主処理　「検証方法（右上）」の定義◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆
		//DAOから記録データを取得
		strVerifymethod_db = daoTrade.getVerifyMethod();
		
		//if 初期表示（リクエストが空）もしくはデータ変更がない場合、DAOの値を設定
		//if 更新がある場合、DBをリクエストに反映してから再度DAOから取得して設定
		if(strVerifymethod_req.equals("") || strVerifymethod_req == strVerifymethod_db) {
			strVerifymethod = strVerifymethod_db;
		} else if(strVerifymethod_req != strVerifymethod_db) {
			daoTrade.setVerifyMethod(strVerifymethod_req);
			strVerifymethod = daoTrade.getVerifyMethod();
		}
		
		
		//◆◆◆　主処理　「トレード日誌（中央左）」の定義◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆
		//リクエストデータを再度フォワード（中央左） ※初期表示時はトレード時刻が空を表示させるために空のDTOを渡す
		//DBデータはトレード日誌（中央右）で保持しているから画面項目を再度返すのみでOK（↓）
		dtoTradeJournal dtoTjItem_req = new dtoTradeJournal();
		if(!(strTradeTime_req.equals(""))) {
			dtoTjItem_req.tradedatetime = strTradeDate_req + " " + strTradeTime_req;
			dtoTjItem_req.entrygrounds = strEntryGrounds_req;
			dtoTjItem_req.entryprice = fltEntryPrice_req;
			dtoTjItem_req.cutlossgrounds = strCutlossGrounds_req;
			dtoTjItem_req.cutlossline = fltCutlossLine_req;
			dtoTjItem_req.profitloss_pips = Integer.parseInt(strProfitlossPips_req);
			dtoTjItem_req.profitloss_price = Integer.parseInt(strProfitlossPrice_req);
			dtoTjItem_req.result = strResultPerTrade_req;
		} else {
			dtoTjItem_req.tradedatetime = "";
			dtoTjItem_req.entrygrounds = "";
			dtoTjItem_req.entryprice = null;
			dtoTjItem_req.cutlossgrounds = "";
			dtoTjItem_req.cutlossline = null;
			dtoTjItem_req.profitloss_pips = null;
			dtoTjItem_req.profitloss_price = null;
			dtoTjItem_req.result = "";
		}
		
		//DBをリクエストの値で更新（リクエストに値があるときのみ）
		dtoTradeJournal dtoTjItem_insert = new dtoTradeJournal();
		if(!(strTradeTime_req.equals(""))) {
			dtoTjItem_insert.tradedatetime = strTradeDate_req + " " + strTradeTime_req;
			dtoTjItem_insert.entrygrounds = strEntryGrounds_req;
			dtoTjItem_insert.entryprice = fltEntryPrice_req;
			dtoTjItem_insert.cutlossgrounds = strCutlossGrounds_req;
			dtoTjItem_insert.cutlossline = fltCutlossLine_req;
			dtoTjItem_insert.profitloss_pips = Integer.parseInt(strProfitlossPips_req);
			dtoTjItem_insert.profitloss_price = Integer.parseInt(strProfitlossPrice_req);
			dtoTjItem_insert.result = strResultPerTrade_req;
			daoTrade.setTradeJournalRecord(dtoTjItem_insert);
		}
		
		
		//◆◆◆　主処理　「トレード日誌（中央右）」の定義◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆
		//DAOから記録データを取得してトレード日誌を表示（中央右）
		List<dtoTradeJournal> lst_dtoTjItem = new ArrayList<dtoTradeJournal>();
		lst_dtoTjItem = daoTrade.getTradeJournalAll();
	
		
		//◆◆◆　後処理　◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆
		//Beanに値をセット
		beanTrade.setEnvironment(dtoEnvItem);
		beanTrade.setVerifyMethod(strVerifymethod);
		beanTrade.setTradeJournalRecord(dtoTjItem_req);
		beanTrade.setTradeJournalList(lst_dtoTjItem);
		
		
		//Beanをリクエストに格納して、次画面にフォワード
		req.setAttribute("beanTrade", beanTrade);
		RequestDispatcher rd = req.getRequestDispatcher("/FXTrade/panel.jsp");
		rd.forward(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
