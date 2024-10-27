package bean;

public class beanYoutube {
	//メンバ変数定義
	private String strKind;
	private String strTitle;
	
	//メソッド定義	
	
	//◆◆◆環境認識用◆◆◆
	public void setKind(String arg_strKind) {
		this.strKind = arg_strKind;
	}
	public void setTitle(String arg_strTitle) {
		this.strTitle = arg_strTitle;
	}
		

	public String getKind() {
		return strKind;
	}
	
	public String getTitle() {
		return strTitle;
	}


}
