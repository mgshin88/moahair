package moahair.jung.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.jung.dao.MoaHairDAO;
import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class ItemSearchResultAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		String m_id = (String)session.getAttribute("memId");
		KyuDAO k_dao = KyuDAO.getInstance();
		
		int m_level = k_dao.memberLevel(m_id);
		
		if(m_level == 10) {
			String item_search = request.getParameter("item_search");
			
			MoaHairDAO dao = MoaHairDAO.getInstance();
			ArrayList list = dao.item_search(item_search);
			
			request.setAttribute("item_search", list);		
		}
		
		request.setAttribute("m_level", m_level);
		
		
		
		return "/supervisor/itemSearchResult.jsp";
	}

	
}
