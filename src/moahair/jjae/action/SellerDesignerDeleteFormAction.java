package moahair.jjae.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.jjae.dao.JaeDAO;
import moahair.mvc.controller.SuperAction;

public class SellerDesignerDeleteFormAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
	    String id = (String)session.getAttribute("memId");
	     
	    int s_num = Integer.parseInt(request.getParameter("s_num"));
	    JaeDAO dao = JaeDAO.getInstance();
	    dao.deleteStaff(s_num);
	    		
		
		return "/sellerPage/seller/sellerDesignerDeleteForm.jsp";
	}
	
}
