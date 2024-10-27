package DatabaseObject;

public class dtoSelectMaster {
	//フィールド変数定義
	public String tagname;
	public Integer branch;
	public String display_value;
		
	//コンストラクタ定義
	public dtoSelectMaster() {
		
	}
		
	//メソッド定義
	public String getTagName() {
		return tagname;
	}
	public Integer getBranch() {
		return branch;
	}
	public String getDisplay_value() {
		return display_value;
	}
}
