package DatabaseObject;

public class dtoTradeJournal {
	//フィールド変数定義
	public String tradedatetime;
	public String entrygrounds;
	public Double entryprice;
	public String cutlossgrounds;
	public Double cutlossline;
	public Integer profitloss_pips;
	public Integer profitloss_price;
	public String result;
	
	//コンストラクタ定義
	public void setTradeTime(String arg_strTradeDatetime) {
		
	}
	
	//メソッド定義
	public String getTradeTime() {
		return tradedatetime;
	}
	public String getEntryGrounds(){
		return entrygrounds;
	}
	public Double getEntryPrice(){
		return entryprice;
	}
	public String getCutlossGrounds(){
		return cutlossgrounds;
	}
	public Double getCutlossLine(){
		return cutlossline;
	}
	public Integer getProfitlossPips(){
		return profitloss_pips;
	}
	public Integer geProfitlossPrice(){
		return profitloss_price;
	}
	public String getResult(){
		return result;
	}
}
