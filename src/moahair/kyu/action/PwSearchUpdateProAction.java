package moahair.kyu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class PwSearchUpdateProAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		String m_id = request.getParameter("si_id");
		String m_pw = request.getParameter("si_pw");
		KyuDAO dao = KyuDAO.getInstance();
		dao.memPwUpdate(m_id, m_pw);
		
		return "/memberPage/PwSearchUpdatePro.jsp";
	}

}
