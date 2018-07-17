package moahair.jjae.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.jjae.dao.JaeDAO;
import moahair.mvc.controller.SuperAction;

public class SellerDesignerListAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		int pageSize = 10;
		String pageNum = request.getParameter("pageNum");
		String bs_num = request.getParameter("bs_num");
		 
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int countStaff = 0;
		
		List staffList = null;
		JaeDAO dao = JaeDAO.getInstance();
		countStaff = dao.getStaffCount(Integer.parseInt(bs_num));
		System.out.println(bs_num);
		System.out.println(countStaff);
		
		if(countStaff > 0) {
			staffList = dao.getStaffs(startRow, pageSize, Integer.parseInt(bs_num));
			request.setAttribute("staffList", staffList);
		}
				
		//jsp로 request 정보보내기
		request.setAttribute("countStaff", countStaff);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("bs_num", bs_num);
		
		return "/sellerPage/seller/sellerDesignerList.jsp";
	}
	
}
