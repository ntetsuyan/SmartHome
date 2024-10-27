package bean;

import java.io.Serializable;


public class beanStock implements Serializable {
	//◆◆◆メンバー変数定義◆◆◆
	private String strCode;
	private String strDate;
	private String strCloseValue;
	
	//◆◆◆コンストラクタ定義◆◆◆
	public beanStock(){
	}
	
	//◆◆◆メソッド定義◆◆◆	
	//値受け用
	public void setCode(String arg_strCode) {
		this.strCode = arg_strCode;
	}
	public void setDate(String arg_strDate) {
		this.strDate = arg_strDate;
	}
	public void setCloseValue(String arg_strCloseValue) {
		this.strCloseValue = arg_strCloseValue;
	}
	
	//値渡し用
	public String getCode() {
		return this.strCode;
	}
	public String getDate() {
		return this.strDate;
	}
	public String getCloseValue() {
		return this.strCloseValue;
	}
}
