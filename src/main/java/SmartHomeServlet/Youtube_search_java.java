package SmartHomeServlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import bean.beanYoutube;

/**
 * Servlet implementation class Youtube_search_java
 */
@WebServlet("/Youtube_search_java")
public class Youtube_search_java extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//リクエストの文字コード指定
		req.setCharacterEncoding("utf-8");
		
		//YoutubeからsnipetデータをJSON形式で取得
		String strURL = "https://www.googleapis.com/youtube/v3/videos?id=zvrsGCyPvF0&key=AIzaSyBUCK9gLjBJogSP7kK4CPR-By-0xnddK8s&part=snippet";
		String strTmp = "";
		String strJson = "";
		URL objURL = new URL(strURL);
		HttpURLConnection objConURL = (HttpURLConnection)objURL.openConnection();
		objConURL.connect();
		BufferedReader in = new BufferedReader(new InputStreamReader(objConURL.getInputStream()));
		while ((strTmp = in.readLine()) != null) {
	        strJson += strTmp;
	    }
		
		JsonNode objJsonNode = null;
		ObjectMapper mapper = new ObjectMapper();
		objJsonNode = mapper.readTree(strJson);
		in.close();
		objConURL.disconnect();
		
		
		String strKind = objJsonNode.get("kind").toString();
		String strTitle = objJsonNode.get("items").get(0).get("snippet").get("title").toString();
		
		beanYoutube beanYoutube = new beanYoutube();
		beanYoutube.setKind(strKind);
		beanYoutube.setTitle(strTitle);
		
		
		//register4.jspへフォワード
		req.setAttribute("beanYoutube", beanYoutube);
		RequestDispatcher rd = req.getRequestDispatcher("/youtube/search.jsp");
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
