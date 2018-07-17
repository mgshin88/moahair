package moahair.jjae.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.jjae.dao.JaeDAO;
import moahair.mvc.controller.SuperAction;

public class SellerListAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		JaeDAO dao = JaeDAO.getInstance();
		
		//{사업자번호, 상호명} 한아이디로 등록된 샵정보들임
		HashMap<Integer, String> businesses = dao.getBusinesses(id);
		
		request.setAttribute("businesses", businesses);
		
		return "/sellerPage/seller/sellerList.jsp";
	}

}
