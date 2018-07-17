package moahair.jjae.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.jjae.dao.JaeDAO;
import moahair.mvc.controller.SuperAction;

public class BusinessDeleteFormAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		
		int bs_num = Integer.parseInt(request.getParameter("bs_num"));
		JaeDAO dao = JaeDAO.getInstance();
		dao.deleteBusiness(bs_num);
		dao.deleteBusinessAddress(bs_num);
		
		System.out.println("businessdeleteaction½ÇÇà!!!!!");
		return "/sellerPage/seller/businessdeleteform.jsp";
	}
	
}
