package SmartHomeServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseObject.daoBusinessPlan;
import DatabaseObject.dtoBusinessPlan;
import DatabaseObject.dtoSelectMaster;
import bean.beanBusinessPlan;

/**
 * Servlet implementation class BusinessPlan
 */
@WebServlet("/BusinessPlan")
public class BusinessPlan extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//リクエストとレスポンスの文字コードを指定
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		
		//◆◆◆　前処理　◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆
		
		//DAOとDTOとbeanのオブジェクト定義
		daoBusinessPlan daoBusinessPlan = new daoBusinessPlan();
		List<dtoBusinessPlan> dtoBPitemlist = new ArrayList<dtoBusinessPlan>();
		List<dtoSelectMaster> dtoSMitemlist = new ArrayList<dtoSelectMaster>();
		beanBusinessPlan beanBP = new beanBusinessPlan();
		
		//画面表示項目とプルダウン項目をDAOから取得
		dtoBPitemlist = daoBusinessPlan.getBusinessPlan();
		dtoSMitemlist = daoBusinessPlan.getSelectMaster("businessplan");		
		
		
		//◆◆◆　主処理　◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆
		//画面表示項目をDBに反映
		String strId, strBusinessPlan_req, strPace_req, strUnit_req, strTerm_req, strProgress_req, strDelete_req,strIdea_req;
		Boolean blChange = false;
		
		//◆レコード毎に変更有無を確認し、１つでも変更があればbreakしてDBをupdate
		//※hid_listcountがnullなら初回表示と判断して更新処理は行わない
		if(req.getParameter("hid_listcount") != null) {
			//◆レコード数を確認
			Integer intListCount = Integer.parseInt(req.getParameter("hid_listcount"));
			
			//◆レコード毎に処理
			for(int i=1; i<=intListCount; i++) {
				//reqを項目毎に変数に取り込み
				strId = req.getParameter("hid_id_" + String.valueOf(i));
				strBusinessPlan_req = req.getParameter("businessplan_" + String.valueOf(i));
				strPace_req = req.getParameter("pace_" + String.valueOf(i));
				strUnit_req = req.getParameter("unit_" + String.valueOf(i));
				strTerm_req = req.getParameter("term_" + String.valueOf(i));
				strProgress_req = req.getParameter("progress_" + String.valueOf(i));
				strDelete_req = req.getParameter("hd_delete_" + String.valueOf(i));
				strIdea_req = req.getParameter("idea_" + String.valueOf(i));
				//データ補正
				if(strPace_req.equals("")) {
					strPace_req = "0";
				}
				
				//変更有無を確認し、１つでも変更があればbreak（最終行はDBにない行だから別処理）
				if(!strId.equals("newrec")) {
					for(dtoBusinessPlan item: dtoBPitemlist) {
						if(item.id == Integer.parseInt(strId)) {
							if(strDelete_req.equals("1")) {
								daoBusinessPlan.delBusinessPlanList(Integer.parseInt(strId));
								break;
							}
							if(!item.businessplan.equals(strBusinessPlan_req)) {
								blChange = true;
								break;
							}
							if(!item.pace_num.equals(Integer.parseInt(strPace_req))) {
								blChange = true;
								break;
							}
							if(!item.pace_unit.equals(Integer.parseInt(strUnit_req))) {
								blChange = true;
								break;
							}
							if(!item.pace_term.equals(Integer.parseInt(strTerm_req))) {
								blChange = true;
								break;
							}
							if(!item.progress.equals(Integer.parseInt(strProgress_req))) {
								blChange = true;
								break;
							}
							break;
						}
					}
				} else {
					if(strBusinessPlan_req != "") {
						blChange = true;
					}
				}
				//DBをupdate
				if(blChange.equals(true)) {
					dtoBusinessPlan dtoBPitem = new dtoBusinessPlan();
					if(!strId.equals("newrec")) {
						dtoBPitem.id = Integer.parseInt(strId);
					} else {
						dtoBPitem.id = null;
					}
						
					dtoBPitem.businessplan = strBusinessPlan_req;
					dtoBPitem.pace_num = Integer.parseInt(strPace_req);
					dtoBPitem.pace_unit = Integer.parseInt(strUnit_req);
					dtoBPitem.pace_term = Integer.parseInt(strTerm_req);
					dtoBPitem.progress = Integer.parseInt(strProgress_req);
					
					daoBusinessPlan.setBusinessPlanList(dtoBPitem);
					
					blChange = false;
				}
				
			}
			//画面表示項目をDAOから再取得
			dtoBPitemlist = daoBusinessPlan.getBusinessPlan();
		}
		
		
		
		//◆◆◆　後処理　◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆
		//Beanに値をセット
		beanBP.setBusinessPlanList(dtoBPitemlist);
		beanBP.setBusinessPlanCount(dtoBPitemlist.size());
		beanBP.setSelectMasterList(dtoSMitemlist);
		
		//Beanをリクエストに格納して、次画面にフォワード
		req.setAttribute("beanBP", beanBP);
		RequestDispatcher rd = req.getRequestDispatcher("/businessplan/list.jsp");
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
