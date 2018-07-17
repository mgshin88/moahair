package moahair.jjae.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.jjae.dao.JaeDAO;
import moahair.mvc.controller.SuperAction;

public class SellerProductAddFormAction implements SuperAction{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		String bs_num = request.getParameter("bs_num");
	
	
		
		JaeDAO dao = JaeDAO.getInstance();
		List staffNameList = dao.getStaffName(Integer.parseInt(bs_num));
		
		
		request.setAttribute("staffNameList", staffNameList);
		request.setAttribute("bs_num", bs_num);
		request.setAttribute("m_id", id);
		return "/sellerPage/seller/sellerproductaddform.jsp";
	}
}
