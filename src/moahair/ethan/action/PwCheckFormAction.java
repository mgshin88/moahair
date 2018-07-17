package moahair.ethan.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moahair.mvc.controller.SuperAction;

public class PwCheckFormAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("memId");
		String pw = request.getParameter("m_pw");
		
		
		request.setAttribute("memId", id);
		request.setAttribute("m_pw", pw);
		request.setCharacterEncoding("UTF-8");
		return "/mainPage/pwCheckForm.jsp";
	}

}
