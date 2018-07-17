package moahair.jung.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class SupervisorMainAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		String m_id = (String)session.getAttribute("memId");
		KyuDAO k_dao = KyuDAO.getInstance();
		int m_level = k_dao.memberLevel(m_id);
		
		request.setAttribute("type", request.getParameter("type"));
		request.setAttribute("m_level", m_level);
		request.setAttribute("m_id", m_id);
		
		return "/supervisor/supervisorMain.jsp";
	}

}
