package bean;

import java.io.Serializable;
import java.util.List;

import DatabaseObject.dtoBusinessPlan;
import DatabaseObject.dtoSelectMaster;

public class beanBusinessPlan implements Serializable {
	//メンバー変数定義
	private Integer intBPitemCount;
	private List<dtoBusinessPlan> dtoBPitemlist;
	private List<dtoSelectMaster> dtoSMitemlist;
		
	//コンストラクタ定義
	public beanBusinessPlan(){
		
	}
		
	//メソッド定義
	//◆◆Selectタグのリスト◆◆
	public void setSelectMasterList(List<dtoSelectMaster> arg_dtoSMitemlist) {
		this.dtoSMitemlist = arg_dtoSMitemlist;
	}
	
	//◆◆事業計画のリストの項目数◆◆
	public void setBusinessPlanCount(Integer arg_intBPitemCount) {
		this.intBPitemCount = arg_intBPitemCount + 1;
	}
	public Integer getBusinessPlanCount() {
		return intBPitemCount;
	}
	
	
	//◆◆事業計画のリスト◆◆
	public void setBusinessPlanList(List<dtoBusinessPlan> arg_dtoBPitemlist) {
		this.dtoBPitemlist = arg_dtoBPitemlist;
	}
	public String getBusinessPlanList() {
		String strBusinessPlanList="";
		String strSelect_unit="";
		String strSelect_term="";
		String strSelect_progress="";
		Integer i = 1;
		
		if(dtoBPitemlist != null) {
			for(dtoBusinessPlan itemBP: dtoBPitemlist) {
				//事業計画リストに埋め込むプルダウン項目を作成
				strSelect_unit = "";
				strSelect_term = "";
				strSelect_progress = "";
				for(dtoSelectMaster itemSM: dtoSMitemlist) {
					switch(itemSM.tagname) {
					case "unit":
						strSelect_unit = strSelect_unit + "\t\t\t\t\t\t\t<option value=\"" + itemSM.branch + "\"";
						if(itemBP.pace_unit == itemSM.branch) {
							strSelect_unit = strSelect_unit + " selected ";
						}
						strSelect_unit = strSelect_unit + ">" + itemSM.display_value + "</option>\n";
						break;
					case "term":
						strSelect_term = strSelect_term + "\t\t\t\t\t\t\t<option value=\"" + itemSM.branch + "\"";
						if(itemBP.pace_term == itemSM.branch) {
							strSelect_term = strSelect_term + " selected ";
						}
						strSelect_term = strSelect_term + ">" + itemSM.display_value + "</option>\n";
						break;
					case "progress":
						strSelect_progress = strSelect_progress + "\t\t\t\t\t\t\t<option value=\"" + itemSM.branch + "\"";
						if(itemBP.progress == itemSM.branch) {
							strSelect_progress = strSelect_progress + " selected ";
						}
						strSelect_progress = strSelect_progress + ">" + itemSM.display_value + "</option>\n";
						break;
					default:
					}
				}
				
				//事業計画リストを作成
				strBusinessPlanList = strBusinessPlanList
						+ "<li id=\"li\"" + i + "\" draggable=\"true\">\n"
						+ "		<div class=\"div_list_line\" onClick=\"fncSingleClickList(" + itemBP.id + ")\" ondblclick=\\\"fncDoubleClickList(\" + itemBP.id + \")\\\">\n"
						+ "			<div class=\"div_list_cell1\">\n" 
						+ "				" + i + "\n"
						+ "				<input type=\"hidden\" name=\"hid_id_" + i + "\" value=\"" + itemBP.id + "\">\n"
						+ "			</div>\n"
						+ "			<div class=\"div_list_cell2\">\n"
						+ "				<textarea name=\"businessplan_" + i + "\" class=\"businessplan\" rows=\"5\">" + itemBP.businessplan + "</textarea>\n"
						+ "				<input type=\"hidden\" name=\"hd_businessplan_" + i + "\" value=\"" + itemBP.businessplan + "\">\n"
						+ "			</div>\n"
						+ "			<div class=\"div_list_cell3\">\n"
						+ "				<input type=\"text\" name=\"pace_" + i + "\" class=\"pace\" value=\"" + itemBP.pace_num + "\" />\n"
						+ "				<input type=\"hidden\" name=\"hd_pace_" + i + "\" value=\"" + itemBP.pace_num + "\">\n"
						+ "			</div>\n"
						+ "			<div class=\"div_list_cell4\">\n"
						+ "				<select name=\"unit_" + i + "\" class=\"select_short\">\n"
						+ 					strSelect_unit
						+ "				</select>\n"
						+ "				<input type=\"hidden\" name=\"hd_unit_" + i + "\" value=\"" + itemBP.pace_unit + "\">\n"
						+ "			</div>\n"
						+ "			<div class=\"div_list_cell5\">\n"
						+ "				<select name=\"term_" + i + "\" class=\"select_short\">\n"
						+ 					strSelect_term
						+ "				</select>\n"
						+ "				<input type=\"hidden\" name=\"hd_term_" + i + "\" value=\"" + itemBP.pace_term + "\">\n"
						+ "  		</div>\n"
						+ "			<div class=\"div_list_cell6\">\n"
						+ "				<select name=\"progress_" + i + "\" class=\"select_middle\">\n"
						+ 					strSelect_progress
						+ "				</select>\n"
						+ "				<input type=\"hidden\" name=\"hd_progress_" + i + "\" value=\"" + itemBP.progress + "\">\n"
						+ "  		</div>\n"
						+ "			<div class=\"div_list_cell7\">\n"
						+ "				<input type=\"button\" value=\"↑\" />\n"
						+ "				<input type=\"button\" value=\"↓\" />\n"
						+ "				<input type=\"button\" onClick=\"fncClickBusinessPlanListDelete(" + i + ")\" value=\"削除\" />\n"
						+ "				<input type=\"hidden\" name=\"hd_delete_" + i + "\" id=\"hd_delete_" + i + "\" value=\"0\" />\n"
						+ "			</div>\n"
						+ "		</div>\n"
						+ "</li>\n";
				i++;
			}
		}
		
		//リストの最後に空白行を挿入
		strSelect_unit="";
		strSelect_term="";
		strSelect_progress="";
		
		for(dtoSelectMaster itemSM: dtoSMitemlist) {
			switch(itemSM.tagname) {
			case "unit":
				strSelect_unit = strSelect_unit + "\t\t\t\t\t\t\t<option value=\"" + itemSM.branch + "\"" + ">" + itemSM.display_value + "</option>\n";
				break;
			case "term":
				strSelect_term = strSelect_term + "\t\t\t\t\t\t\t<option value=\"" + itemSM.branch + "\"" + ">" + itemSM.display_value + "</option>\n";
				break;
			case "progress":
				strSelect_progress = strSelect_progress + "\t\t\t\t\t\t\t<option value=\"" + itemSM.branch + "\"" + ">" + itemSM.display_value + "</option>\n";
				break;
			default:
			}
		}
		strBusinessPlanList = strBusinessPlanList
				+ "<li id=\"li\"" + i + "\" draggable=\"true\">\n"
				+ "		<div class=\"div_list_line\">\n"
				+ "			<div class=\"div_list_cell1\">"
				+ "				" + i + "\n"
				+ "				<input type=\"hidden\" name=\"hid_id_" + i + "\" value=\"newrec\">\n"
				+ "			</div>\n"
				+ "			<div class=\"div_list_cell2\">\n"
				+ "				<textarea name=\"businessplan_" + i + "\" class=\"businessplan\" rows=\"5\"></textarea>\n"
				+ "			</div>\n"
				+ "			<div class=\"div_list_cell3\">\n"
				+ "				<input type=\"text\" name=\"pace_" + i + "\" class=\"pace\" value=\"\" />\n"
				+ "			</div>\n"
				+ "			<div class=\"div_list_cell4\">\n"
				+ "				<select name=\"unit_" + i + "\" class=\"select_short\">\n"
				+ 					strSelect_unit
				+ "				</select>\n"
				+ "			</div>\n"
				+ "			<div class=\"div_list_cell5\">\n"
				+ "				<select name=\"term_" + i + "\" class=\"select_short\">\n"
				+ 					strSelect_term
				+ "				</select>\n"
				+ "  		</div>\n"
				+ "			<div class=\"div_list_cell6\">\n"
				+ "				<select name=\"progress_" + i + "\" class=\"select_middle\">\n"
				+ 					strSelect_progress
				+ "				</select>\n"
				+ "  		</div>\n"
				+ "			<div class=\"div_list_cell7\">\n"
				+ "			</div>\n"
				+ "		</div>\n"
				+ "</li>\n";
		
		return strBusinessPlanList;
	}
	
	
}
