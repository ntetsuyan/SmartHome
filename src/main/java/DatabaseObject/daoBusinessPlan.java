package DatabaseObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class daoBusinessPlan {
	//◆◆フィールド定義◆◆
	private static final String DB_NAME = "smarthome";
	private static final String PROPATIES = "?characterEncoding=sjis";
	private static final String URL = "jdbc:mySQL://localhost/" + DB_NAME + PROPATIES;
	private static final String USER = "webuser";
	private static final String PASS = "webuser";
	private static Connection conn = null;
	
	
	//◆◆コンストラクタ定義◆◆
	public daoBusinessPlan() {
		try {
			//MySQL に接続する
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection(URL, USER, PASS);
		 } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	     } catch (SQLException e) {
	            e.printStackTrace();
	     } 
	}
	
	//◆◆メソッド定義(get)◆◆
	public List<dtoBusinessPlan> getBusinessPlan() {
		List<dtoBusinessPlan> listitem = new ArrayList<dtoBusinessPlan>();
		String SQL = "SELECT id,businessplan,pace_num,pace_unit,pace_term,progress,idea,display_order,start_date,end_date FROM smarthome.businessplan";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(SQL);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				dtoBusinessPlan dtoItem = new dtoBusinessPlan();
				dtoItem.id = rs.getInt("id");
				dtoItem.businessplan = rs.getString("businessplan");
				dtoItem.pace_num = rs.getInt("pace_num");
				dtoItem.pace_unit = rs.getInt("pace_unit");
				dtoItem.pace_term = rs.getInt("pace_term");
				dtoItem.progress = rs.getInt("progress");
				dtoItem.idea = rs.getString("idea");
				dtoItem.display_order = rs.getInt("display_order");
				dtoItem.start_date = rs.getString("start_date");
				dtoItem.end_date = rs.getString("end_date");
				listitem.add(dtoItem);
			}
		}
		catch (SQLException e) {
         e.printStackTrace();
        } 
		
		return listitem;
	}
	
	public List<dtoSelectMaster> getSelectMaster(String arg_strName) {
		List<dtoSelectMaster> listitem = new ArrayList<dtoSelectMaster>();
		String SQL = "SELECT tagname,branch,display_value FROM smarthome.selectmaster where display = ? order by tagname,branch asc";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(SQL);
			stmt.setString(1, arg_strName);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				dtoSelectMaster dtoItem = new dtoSelectMaster();
				dtoItem.tagname = rs.getString("tagname");
				dtoItem.branch = rs.getInt("branch");
				dtoItem.display_value = rs.getString("display_value");
				listitem.add(dtoItem);
			}
		}
		catch (SQLException e) {
         e.printStackTrace();
        } 		
		
		return listitem;
	}
	
	
	//◆◆メソッド定義(set)◆◆
	public void setBusinessPlanList(dtoBusinessPlan argDto) {
		String SQL_UI = "insert into businessplan values(?,?,?,?,?,?,?,?,?,?) on duplicate key "
				    + "update businessplan=?, pace_num=?, pace_unit=?, pace_term=?, progress=?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(SQL_UI);
			
			//Insert文の?設定
			if(argDto.id == null) {
				stmt.setString(1, null);
			} else {
				stmt.setInt(1, argDto.id);
			}
			stmt.setString(2, argDto.businessplan);
			stmt.setInt(3, argDto.pace_num);
			stmt.setInt(4, argDto.pace_unit);
			stmt.setInt(5, argDto.pace_term);
			stmt.setInt(6, argDto.progress);
			stmt.setString(7, null);
			stmt.setInt(8, 9);
			stmt.setString(9, null);
			stmt.setString(10, null);
			
			//Update文の?設定
			stmt.setString(11, argDto.businessplan);
			stmt.setInt(12, argDto.pace_num);
			stmt.setInt(13, argDto.pace_unit);
			stmt.setInt(14, argDto.pace_term);
			stmt.setInt(15, argDto.progress);
			
			//SQL実行
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delBusinessPlanList(Integer arg_id) {
		String SQL_D = "delete from businessplan where id=?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(SQL_D);
			stmt.setInt(1, arg_id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
