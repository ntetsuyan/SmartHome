package DatabaseObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class daoMemo {
	
	//フィールド定義
	private static final String DB_NAME = "smarthome";
	private static final String PROPATIES = "?characterEncoding=sjis";
	private static final String URL = "jdbc:mySQL://localhost/" + DB_NAME + PROPATIES;
	private static final String USER = "webuser";
	private static final String PASS = "webuser";
	public static Connection conn = null;
	
	
	//コンストラクタ定義
	public daoMemo() {
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
	
	//メソッド定義
	public List<dtoMemo> getMemoList() {
		String SQL = "select id,memo from mymemo;";
		List<dtoMemo> listitem = new ArrayList<dtoMemo>();
		
		
		try {
	        PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	dtoMemo item = new dtoMemo();
            	item.Id =  rs.getInt("id");
            	item.Memo = rs.getString("memo");
            	listitem.add(item);
            }
            
		 } catch (SQLException e) {
	            e.printStackTrace();
	     } 
		return listitem;
	}
	
	public Integer getMemoCount() {
		String SQL = "select count(*) as cnt from mymemo;";
		Integer MemoCount = 0;
		
		try {
	        PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	MemoCount = rs.getInt("cnt");
            }
            
		 } catch (SQLException e) {
	            e.printStackTrace();
	     } 
		return MemoCount;
		
	}
	
}
