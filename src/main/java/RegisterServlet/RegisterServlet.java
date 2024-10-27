package RegisterServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	//private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//DB接続
		String DB_NAME = "smarthome";
		//String PROPATIES = "?characterEncoding=sjis&serverTimezone=SYSTEM";
		String PROPATIES = "?characterEncoding=sjis";
		String URL = "jdbc:mySQL://localhost/" + DB_NAME + PROPATIES;
		String USER = "webuser";
		String PASS = "webuser";
		String SQL = "select * from mymemo;";
		
		try {
            //MySQL に接続する
            Class.forName("com.mysql.cj.jdbc.Driver");
            //データベースに接続
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();

    		//HTML記述
    		req.setCharacterEncoding("utf-8");
    		res.setContentType("text/html;charset=utf-8");
    		PrintWriter out =res.getWriter();
    		out.println("<html>");
    		out.println("<head>");
    		out.println("<title>確認画面</title>");
    		out.println("</head>");
    		out.println("<body>");
    		out.println("<h2>入力情報を確認して登録ボタンを押してくださいjava</h2>");
    		out.println("氏名：<strong>" + req.getParameter("name") + "</strong><br/>");
    		out.println("パスワード：<strong>" + req.getParameter("pass") + "</strong><br/>");
    		out.println("サーブレットで生成<br/>");
    		
    		while (rs.next()) {
                String id = rs.getString("id");
                String memo = rs.getString("memo");

                out.println(id + ":" + memo + "<br />");
            }
    		
    		out.println("</body>");
    		out.println("</html>");	

        } catch (SQLException  e) {
            e.printStackTrace();
            System.out.println("JDBCドライバのロードでエラーが発生しました");
        } catch (ClassNotFoundException e) {
            System.out.println("データベースへのアクセスでエラーが発生しました。");
        }

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
