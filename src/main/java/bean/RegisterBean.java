package bean;

import java.io.Serializable;
import java.util.List;

import DatabaseObject.dtoMemo;

public class RegisterBean implements Serializable {
	private String name;
	private String age;
	private Integer sqlcount;
	private String[][] SQLresult;
	private List<dtoMemo> listitem;
	
	public RegisterBean() {
		
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAge(String age) {
		if(age==null) {
			this.age = "child";
		}else {
			this.age = age;
		}
	}
	
	public void setSQLcount(Integer sqlcount) {
		this.sqlcount = sqlcount;
	}
	
	public void setId(String[][] SQLresult) {
		this.SQLresult=SQLresult;
	}
	
	public void setListItem(List<dtoMemo> listitem) {
		this.listitem = listitem;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAge() {
		return age;
	}
	
	public Integer getSQLcount() {
		return sqlcount;
	}
	
	public String[][] getSQLresult() {
		return SQLresult;
	}
	
	public String getJpnAge() {
		String jpnAge;
		if(age.equals("child")) {
			jpnAge="18歳未満";
		}else {
			jpnAge="18歳以上";
		}
		return jpnAge;
	}
	
	public String getStrResult() {
		String StgResult = "";
		for(int i = 0; i < SQLresult.length; i ++) {
			StgResult = StgResult
					+ "<tr>"
						+ "<td>" + SQLresult[i][0] + "</td>"
						+ "<td>"
							+ "<input type=\"text\" name=\"memo" + (i+1) +  "\" value=\"" + SQLresult[i][1] + "\">"
						+ "</td>"
					+ "</tr>";
		}
		return StgResult;
		
	}
	
	public String getListItem() {
		String StgResult = "";
		Integer i = 1;
		for(dtoMemo itemContents: listitem) {
			StgResult = StgResult
					+ "<tr>"
						+ "<td>" + itemContents.Id + "</td>"
						+ "<td>"
							+ "<input type=\"text\" name=\"memo" + (i+1) +  "\" value=\"" + itemContents.Memo + "\">"
						+ "</td>"
					+ "</tr>";
			i = i++;
		}
		return StgResult;
	}

}
