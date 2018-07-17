package moahair.jjae.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.jjae.dao.JaeDAO;
import moahair.mvc.controller.*;

public class SellerProductDeleteFormAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		
		int i_num = Integer.parseInt(request.getParameter("i_num"));
		JaeDAO dao = JaeDAO.getInstance();
		dao.deleteItem(i_num);
		
		return "/sellerPage/seller/sellerproductdeleteform.jsp";
	}

}
