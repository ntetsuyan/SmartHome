package DatabaseObject;

public class dtoBusinessPlan {
	//フィールド変数定義
	public Integer id;
	public String businessplan;
	public Integer pace_num;
	public Integer pace_unit;
	public Integer pace_term;
	public Integer progress;
	public String idea;
	public Integer display_order;
	public String start_date;
	public String end_date;

		
	//コンストラクタ定義
	public dtoBusinessPlan() {
		
	}
		
	//メソッド定義
	public Integer getId() {
		return id;
	}
	public String getBusinessPlan() {
		return businessplan;
	}
	public Integer getPace_Num() {
		return pace_num;
	}
	public Integer getPace_Unit() {
		return pace_unit;
	}
	public Integer getPace_Term() {
		return pace_term;
	}
	public Integer getProgress() {
		return progress;
	}
	public String getIdea() {
		return idea;
	}
	public String getStart_Date() {
		return start_date;
	}
	public String getEnd_Date() {
		return end_date;
	}
}
