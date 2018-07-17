package moahair.kyu.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class IdSearchProAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		String m_name = request.getParameter("si_name");
		String m_email = request.getParameter("si_email");
		String si_cerfity = request.getParameter("si_cerfity");
		Cookie[] cookies = request.getCookies();
		String ranNum = null;
		if(cookies[0].getName().equals("ranNumber")) {
			ranNum = cookies[0].getValue();
		}
		KyuDAO dao = KyuDAO.getInstance();
		String m_id = dao.getIdCheckAllValue(m_name, m_email, si_cerfity, ranNum);
		
		request.setAttribute("m_id", m_id);
		return "/memberPage/idSearchPro.jsp";
	}

}
