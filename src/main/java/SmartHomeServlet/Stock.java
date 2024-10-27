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

import bean.beanStock;

/**
 * Servlet implementation class Stock
 */
@WebServlet("/Stock")
public class Stock extends HttpServlet {
	//private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//◆◆◆　前処理　◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆
		//リクエストとレスポンスの文字コードを指定
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
				
		//◆◆◆　主処理　◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆
		
		//変数宣言＆初期値設定
		String strCode = "8016";
		String strDate = "2023-02-10";
		String strToJson = "";
		String strCloseValue = "";
		String YOUR_API_KEY = "Bearer eyJraWQiOiJHQXNvU2xxUzMyUktLT2lVYm1xcjU3ekdYNE1TVFhsWFBrbDNJTmhWKzNzPSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiI3OTNiYWY4NS04OWI3LTQ2NjktYjMxNC01Y2NiZTRiMjc1NTciLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLmFwLW5vcnRoZWFzdC0xLmFtYXpvbmF3cy5jb21cL2FwLW5vcnRoZWFzdC0xX0FGNjByeXJ2NCIsImNvZ25pdG86dXNlcm5hbWUiOiI3OTNiYWY4NS04OWI3LTQ2NjktYjMxNC01Y2NiZTRiMjc1NTciLCJvcmlnaW5fanRpIjoiMTk2YzM3ZmItYjRmZC00OWEzLTg1ZjEtZjVmYmFlOGYzNTkxIiwiYXVkIjoiNXZyN2xiOGppdThvZmhvZmJmYWxmbm1waWkiLCJldmVudF9pZCI6ImEzMWIyMjBmLWE4NGEtNDY3Zi04NTA2LWRmMzY4ZDJiOWRhYiIsInRva2VuX3VzZSI6ImlkIiwiYXV0aF90aW1lIjoxNjgzMzU5NTc0LCJleHAiOjE2ODM0NDYwOTYsImlhdCI6MTY4MzM1OTY5NiwianRpIjoiNTU5ZDViNjUtZjUzNS00ZjVmLWJlNGEtZmQyMTE5Mjc3M2FkIiwiZW1haWwiOiJudGV0c3V5YW4uanBAZ21haWwuY29tIn0.TzqurJ-rHZoxFPNshlz03j8VMxcnAWAZ6fBlfoHUIlDbo7vEyT7YRl8Ct8J718bd9x5dT98_-NlUUuhP6XbadGQGVbWHKVAsgPbpqWsH8Vcle_QCmLO8_2oAUNmgpKf6Tl5xF6k9cXGPY5BMLdKkfW9AQskreZFkhjv-Tf-B1tPWT9UCajta-X3cdR0zvh_4PHT3abtLuQVHwEaQKPPbtMjdCleq7LlxDh5oFvygci5DJsxCuloZGoN_4_9SJQTPYTM1vs04GDIeK5hYGiO_Wx5xeXYSJFdzFVJGv2EPn4MeDdqse_RBPxL-RQdzwg1zbszOSCbICgNcdncK8ZDWJg";
		URL url = new URL("https://api.jquants.com/v1/prices/daily_quotes?code=" + strCode);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Authorization", YOUR_API_KEY);

		//読み込んだテキストを一旦Stringに格納
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		//StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			strToJson += inputLine;
        }
		
		//メモリ解放
		in.close();
		con.disconnect();
		 
        //Stringに格納した文字列をJSONに変換
		ObjectMapper mapper = new ObjectMapper();
		JsonNode objJsonNode = mapper.readTree(strToJson);
        
        //JSONから色々取り出してみる
		String strDailyQuotes = objJsonNode.get("daily_quotes").toString();
		
		
        
        strCloseValue = strToJson;
	
		
		//◆◆◆　後処理　◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆
        //オブジェクト化
		beanStock beanStock = new beanStock();
		
		//Beanに値をセット
		beanStock.setCode(strCode);
		beanStock.setDate(strDate);
		beanStock.setCloseValue(strCloseValue);
		
		//Beanをリクエストに格納して、次画面にフォワード
		req.setAttribute("beanStock", beanStock);
		RequestDispatcher rd = req.getRequestDispatcher("/Stock/panel.jsp");
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
