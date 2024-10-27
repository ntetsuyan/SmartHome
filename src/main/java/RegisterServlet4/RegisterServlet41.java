package RegisterServlet4;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseObject.daoMemo;
import DatabaseObject.dtoMemo;
import bean.RegisterBean;


/**
 * Servlet implementation class register4
 */
@WebServlet("/register41")
public class RegisterServlet41 extends HttpServlet {
	//private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		//リクエストの文字コード指定
		req.setCharacterEncoding("utf-8");
		
		//入力情報の取得
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		//String memo = req.getParameter("memo");
		res.setContentType("text/html;charset=utf-8");
		
		//DBconnectBeanの作成
		daoMemo daomemo = new daoMemo();
		

		//データを取得
		List<dtoMemo> listitem = daomemo.getMemoList();
		//取得予定データの件数を取得
		Integer sqlcount = daomemo.getMemoCount();
		
		
		//Beanの作成
		RegisterBean rb = new RegisterBean();
		rb.setName(name);
		rb.setAge(age);
		rb.setSQLcount(sqlcount);
		rb.setListItem(listitem);
		
		
		//Beanをリクエストに格納
		req.setAttribute("rb", rb);
		
		//register4.jspへフォワード
		RequestDispatcher rd = req.getRequestDispatcher("/register4.jsp");
		rd.forward(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}