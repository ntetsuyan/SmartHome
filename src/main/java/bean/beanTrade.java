package bean;

import java.io.Serializable;
import java.util.List;

import DatabaseObject.dtoEnvironment;
import DatabaseObject.dtoTradeJournal;

public class beanTrade implements Serializable {
	//メンバー変数定義
	private dtoEnvironment dtoEnvItem;
	private String strVerifymethod;
	private dtoTradeJournal dtoTjItem;
	private List<dtoTradeJournal> lst_dtoTjItem;
	
	//コンストラクタ定義
	public beanTrade(){
		
	}
	
	//メソッド定義	
	
	//◆◆◆環境認識用◆◆◆
	public void setEnvironment(dtoEnvironment arg_dtoEnvItem) {
		this.dtoEnvItem = arg_dtoEnvItem;
	}
	public String getTradedate() {
		return dtoEnvItem.getTradedate();
	}
	public String getEnvironment() {
		return dtoEnvItem.getEnvironment();
	}
	public String getScinario() {
		return dtoEnvItem.getScinario();
	}
	public String getResult() {
		return dtoEnvItem.getResult();
	}
	
	
	//◆◆◆検証手法用◆◆◆
	public void setVerifyMethod(String arg_strVerifymethod) {
		this.strVerifymethod = arg_strVerifymethod;
	}
	public String getVerifyMethod() {
		return strVerifymethod;
	}
	
	
	//◆◆◆トレード日誌用（画面左の単一レコード）◆◆◆
	public void setTradeJournalRecord(dtoTradeJournal arg_dtoTjItem) {
		this.dtoTjItem = arg_dtoTjItem;
	}
	public String getTradeTime() {
		if(!(dtoTjItem.tradedatetime.equals(""))){
			return dtoTjItem.tradedatetime.substring(11,16);
		}else {
			return "";
		}
	}
	public String getEntryGrounds() {
		return dtoTjItem.entrygrounds;
	}
	public Double getEntryPrice() {
		if(!(dtoTjItem.tradedatetime.equals(""))){
			return dtoTjItem.entryprice;
		}else {
			return Double.parseDouble("0.000");
		}
	}
	public String getCutlossGrounds() {
		return dtoTjItem.cutlossgrounds;
	}
	public Double getCutlossLine() {
		if(!(dtoTjItem.tradedatetime.equals(""))){
			return dtoTjItem.cutlossline;
		}else {
			return Double.parseDouble("0.000");
		}
	}
	public Integer getProfitloss_pips() {
		if(!(dtoTjItem.tradedatetime.equals(""))){
			return dtoTjItem.profitloss_pips;
		}else {
			return 0;
		}
	}
	public Integer getProfitloss_price() {
		if(!(dtoTjItem.tradedatetime.equals(""))){
			return dtoTjItem.profitloss_price;
		}else {
			return 0;
		}
	}
	public String getResultPerTrade() {
		return dtoTjItem.result;
	}
	
	
	
	//◆◆◆トレード日誌用（画面右の表）◆◆◆
	public void setTradeJournalList(List<dtoTradeJournal> arg_lst_dtoTjItem) {
		this.lst_dtoTjItem = arg_lst_dtoTjItem;
	}
	public String getTradeJounarlList() {
		String strTradeJounarlList = "";
		Integer i = 1;
		for(dtoTradeJournal item: lst_dtoTjItem) {
			strTradeJounarlList = strTradeJounarlList
					+ "\t\t\t<tr onClick=\"fncClickTradeJournalList(" + i + ")\" class=\"TradeJournalList\">\n"
						+ "\t\t\t\t<td>" + item.tradedatetime + "</td>\n"
						+ "\t\t\t\t<td>" + item.entryprice + "</td>\n"
						+ "\t\t\t\t<td>" + item.cutlossline + "</td>\n"
						+ "\t\t\t\t<td>" + item.profitloss_pips + "</td>\n"
						+ "\t\t\t\t<td>\n"
							+ "\t\t\t\t\t・・・\n"
						    + "\t\t\t\t\t<input type=\"hidden\" name=\"hd_entrydate_" + i + "\" value=\"" + item.tradedatetime.substring(0,10) + "\">\n"
							+ "\t\t\t\t\t<input type=\"hidden\" name=\"hd_entrytime_" + i + "\" value=\"" + item.tradedatetime.substring(11,16) + "\">\n"
						    + "\t\t\t\t\t<input type=\"hidden\" name=\"hd_entrygrounds_" + i + "\" value=\"" + item.entrygrounds + "\">\n"
						    + "\t\t\t\t\t<input type=\"hidden\" name=\"hd_entryprice_" + i + "\" value=\"" + item.entryprice + "\">\n"
						    + "\t\t\t\t\t<input type=\"hidden\" name=\"hd_cutlossgrounds_" + i + "\" value=\"" + item.cutlossgrounds + "\">\n"
						    + "\t\t\t\t\t<input type=\"hidden\" name=\"hd_cutlossline_" + i + "\" value=\"" + item.cutlossline + "\">\n"
						    + "\t\t\t\t\t<input type=\"hidden\" name=\"hd_profitloss_pips_" + i + "\" value=\"" + item.profitloss_pips + "\">\n"
						    + "\t\t\t\t\t<input type=\"hidden\" name=\"hd_profitlossprice_" + i + "\" value=\"" + item.profitloss_price + "\">\n"
						    + "\t\t\t\t\t<input type=\"hidden\" name=\"hd_result_" + i + "\" value=\"" + item.result + "\">\n"
						+ "\t\t\t</td>\n"
					+ "\t\t\t</tr>\n";
			i++;
		}
		return strTradeJounarlList;
	}
	

}
