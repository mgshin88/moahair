package moahair.jjae.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.jjae.dao.JaeDAO;
import moahair.mvc.controller.SuperAction;

public class PwCheckProAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		
		
		String pw = request.getParameter("pw");
		String bs_num = request.getParameter("bs_num");
		
		JaeDAO dao = JaeDAO.getInstance();
		
		
		
		if(request.getParameter("check") != null) {
			request.setAttribute("check", request.getParameter("check"));
		}else {
			int check = dao.userCheck(id, pw);
			request.setAttribute("check", check);
		}
		request.setAttribute("bs_num", bs_num);
		System.out.println("!!!!pwcheckproaction ¿€µø!!!!");
		return "/sellerPage/seller/pwcheckpro.jsp";
	}
	
}
