package moahair.kyu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class LoginProAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("m_id");
		String pw  = request.getParameter("m_pw");
		
		KyuDAO manager = KyuDAO.getInstance();
	    boolean check= manager.loginCheck(id, pw);
		
	    if(check){
	    	HttpSession session = request.getSession();
			session.setAttribute("memId",id);
	    }
	    
	    request.setAttribute("check", check);
		return "/memberPage/loginPro.jsp";
	}

}
