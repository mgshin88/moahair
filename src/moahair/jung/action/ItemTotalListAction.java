package moahair.jung.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.jung.dao.MoaHairDAO;
import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class ItemTotalListAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		String m_id = (String)session.getAttribute("memId");
		KyuDAO k_dao = KyuDAO.getInstance();
		
		int m_level = k_dao.memberLevel(m_id);
		
		if(m_level == 10) {
			MoaHairDAO dao = MoaHairDAO.getInstance();
			
			String time1 = request.getParameter("time1");
			String time2 = request.getParameter("time2");
			
			ArrayList item_total = dao.item_total(time1, time2);
			
			request.setAttribute("item_total", item_total);
			request.setAttribute("time1", time1);
			request.setAttribute("time2", time2);
		}
		
		request.setAttribute("m_level", m_level);
		
		

		
		return "/supervisor/itemTotalList.jsp";
	}
}
