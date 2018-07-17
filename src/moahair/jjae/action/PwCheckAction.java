package moahair.jjae.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.mvc.controller.SuperAction;

public class PwCheckAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		String bs_num = request.getParameter("bs_num");
		
		request.setAttribute("bs_num", bs_num);
		
		return "/sellerPage/seller/pwcheck.jsp";
	}
	
}
