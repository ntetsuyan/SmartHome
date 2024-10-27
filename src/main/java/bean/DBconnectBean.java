package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBconnectBean {

	private static final String DB_NAME = "smarthome";
	private static final String PROPATIES = "?characterEncoding=sjis";
	private static final String URL = "jdbc:mySQL://localhost/" + DB_NAME + PROPATIES;
			
	private static final String USER = "webuser";
	private static final String PASS = "webuser";
	
	public static Connection conn = null;
	public static ResultSet rs = null;
	
	//コンストラクタ定義
	public DBconnectBean() {
		try {
			//MySQL に接続する
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        //データベースに接
	        conn = DriverManager.getConnection(URL, USER, PASS);
	        
		 } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	            // getConnection()で例外発生
	     } catch (SQLException e) {
	            e.printStackTrace();
	     } 
	}
	
	public ResultSet getRS(String SQL) {
		try {
	        PreparedStatement stmt = conn.prepareStatement(SQL);
	        rs = stmt.executeQuery(); 
		 } catch (SQLException e) {
	            e.printStackTrace();
	     } 

		return rs;
		//多分今どきはconn.closeは不要
	}
}
