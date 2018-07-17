package moahair.ethan.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class PwCheckAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String m_id = request.getParameter("memId");
		String m_pw=request.getParameter("m_pw");
		request.setAttribute("m_pw", m_pw);
		//��������� �����Ȯ�� �Ϸ��� �Է� ���� �н�����
		
		KyuDAO dao = KyuDAO.getInstance();
		boolean result = dao.loginCheck(m_id, m_pw);
		
		request.setAttribute("result", result);
		request.setAttribute("memId", m_id);
		request.setAttribute("m_pw", m_pw);
		
		
		return "/mainPage/pwCheck.jsp";
		
		
	}

}
