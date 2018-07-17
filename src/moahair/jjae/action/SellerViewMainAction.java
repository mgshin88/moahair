package moahair.jjae.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.jjae.dao.JaeDAO;
import moahair.mvc.controller.SuperAction;

public class SellerViewMainAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		String projectPath=request.getContextPath();
		String bs_num = request.getParameter("bs_num");
		
		JaeDAO dao = JaeDAO.getInstance();
		System.out.println("mainAction"+request.getParameter("sel"));
		System.out.println("mainAction"+request.getParameter("type"));
		request.setAttribute("projectPath", projectPath);
		request.setAttribute("bs_num", bs_num);
		request.setAttribute("m_id", id);
		request.setAttribute("type", request.getParameter("type"));
		request.setAttribute("sel", request.getParameter("sel"));
		
		
		//{사업자번호, 상호명} 한아이디로 등록된 샵정보들임
		HashMap<Integer, String> businesses = dao.getBusinesses(id);
		
		request.setAttribute("businesses", businesses);
		return "/sellerPage/seller/sellerviewmain.jsp";
	}
	
}
