package DatabaseObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class daoTrade {

	//フィールド定義
	private static final String DB_NAME = "smarthome";
	private static final String PROPATIES = "?characterEncoding=sjis";
	private static final String URL = "jdbc:mySQL://localhost/" + DB_NAME + PROPATIES;
	private static final String USER = "webuser";
	private static final String PASS = "webuser";
	public static Connection conn = null;
	
	
	//コンストラクタ定義
	public daoTrade() {
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
	
	//「環境認識」environmentテーブルから日付指定でデータを取得
	public dtoEnvironment getEnvironment(String arg_tradedate){
		String SQL = "select tradedate,environment,scinario,result from environment where tradedate=?;";
		dtoEnvironment dtoEnvItem = new dtoEnvironment();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(SQL);
			stmt.setString(1, arg_tradedate);
			ResultSet rs = stmt.executeQuery();
			dtoEnvItem.tradedate = arg_tradedate;
			
			while(rs.next()) {
				dtoEnvItem.environment = rs.getString("environment");
				dtoEnvItem.scinario = rs.getString("scinario");
				dtoEnvItem.result = rs.getString("result");	
			}
		}
		catch (SQLException e) {
         e.printStackTrace();
        } 
		
		return dtoEnvItem;
	}
	//「環境認識」environmentテーブルにデータをセット
	public Integer setEnvironment(dtoEnvironment argDto) {
		String SQL_UI = "insert into environment values(?,?,?,?) on duplicate key update environment=?, scinario=?, result=?;";
		Integer exe_count = 0;
		try {
			PreparedStatement stmt = conn.prepareStatement(SQL_UI);
			stmt.setString(1, argDto.getTradedate());
			stmt.setString(2, argDto.getEnvironment());
			stmt.setString(3, argDto.getScinario());
			stmt.setString(4, argDto.getResult());
			stmt.setString(5, argDto.getEnvironment());
			stmt.setString(6, argDto.getScinario());
			stmt.setString(7, argDto.getResult());
			exe_count = stmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
		} 
		
		return exe_count;
	}
	
	
	//「検証手法」verifymethodテーブルからデータを取得
	public String getVerifyMethod() {
		String SQL = "select verifymethod from verifymethod;";
		String verifymethod = "";
		
		try {
	        PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	verifymethod = rs.getString("verifymethod");
            }
            
		 } catch (SQLException e) {
	            e.printStackTrace();
	     } 
		return verifymethod;
		
	}
	//「検証手法」verifymethodテーブルにデータをセット
	public Integer setVerifyMethod(String verifymethod) {
		String SQL_count = "select count(*) as cnt from verifymethod;";
		String SQL_insert = "insert into verifymethod values(?);";
		String SQL_update ="update verifymethod set verifymethod=?";
		//String SQL_UI= "insert into verifymethod values(?) on duplicate key update verifymethod=?";
		Integer record_count = 0;
		Integer exe_count = 0;

		try {
			PreparedStatement stmt = conn.prepareStatement(SQL_count);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				record_count = rs.getInt("cnt");
			}
			
			//0件の場合はInsert文、1件以上の場合はUpdate文
			if(record_count == 0) {
				PreparedStatement stmt2 = conn.prepareStatement(SQL_insert);
				stmt2.setString(1, verifymethod);
				exe_count = stmt2.executeUpdate();
			} else {
				PreparedStatement stmt2 = conn.prepareStatement(SQL_update);
				stmt2.setString(1, verifymethod);
				exe_count = stmt2.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exe_count;
	}
	
	
	//「トレード日誌」TradeJournalテーブルから全データを取得
	public List<dtoTradeJournal> getTradeJournalAll(){
		String SQL = "SELECT tradedatetime, entrygrounds, entryprice, cutlossgrounds, cutlossline, profitloss_pips, profitloss_price, result "
					  + "FROM smarthome.tradejournal order by tradedatetime desc;";
		List<dtoTradeJournal> listitem = new ArrayList<dtoTradeJournal>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(SQL);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				dtoTradeJournal dtoTjItem = new dtoTradeJournal();
				
				dtoTjItem.tradedatetime = rs.getString("tradedatetime");
				dtoTjItem.entrygrounds = rs.getString("entrygrounds");
				dtoTjItem.entryprice = rs.getDouble("entryprice");
				dtoTjItem.cutlossgrounds = rs.getString("cutlossgrounds");
				dtoTjItem.cutlossline = rs.getDouble("cutlossline");
				dtoTjItem.profitloss_pips = rs.getInt("profitloss_pips");
				dtoTjItem.profitloss_price = rs.getInt("profitloss_price");
				dtoTjItem.result = rs.getString("result");
				listitem.add(dtoTjItem);
			}
		}
		catch (SQLException e) {
         e.printStackTrace();
        } 
		
		return listitem;
	}
	//「トレード日誌」TradeJournalテーブルにデータをセット
	public Integer setTradeJournalRecord(dtoTradeJournal argDto) {
		String SQL_UI = "insert into tradejournal values(?,?,?,?,?,?,?,?) on duplicate key "
					   + "update entrygrounds=?, entryprice=?, cutlossgrounds=?, cutlossline=?, profitloss_pips=?, profitloss_price=?, result=?;";
		Integer exe_count = 0;
		try {
			PreparedStatement stmt = conn.prepareStatement(SQL_UI);
			stmt.setString(1, argDto.tradedatetime);
			stmt.setString(2, argDto.entrygrounds);
			stmt.setDouble(3, argDto.entryprice);
			stmt.setString(4, argDto.cutlossgrounds);
			stmt.setDouble(5, argDto.cutlossline);
			stmt.setInt(6, argDto.profitloss_pips);
			stmt.setInt(7, argDto.profitloss_price);
			stmt.setString(8, argDto.result);
			stmt.setString(9, argDto.entrygrounds);
			stmt.setDouble(10, argDto.entryprice);
			stmt.setString(11, argDto.cutlossgrounds);
			stmt.setDouble(12, argDto.cutlossline);
			stmt.setInt(13, argDto.profitloss_pips);
			stmt.setInt(14, argDto.profitloss_price);
			stmt.setString(15, argDto.result);
			exe_count = stmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
		} 
		
		return exe_count;
	}
	
}
