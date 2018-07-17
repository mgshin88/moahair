package moahair.jjae.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.jjae.dao.JaeDAO;
import moahair.mvc.controller.SuperAction;

public class ItemSearchAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pageSize = 10;
		String pageNum = request.getParameter("pageNum");
		String bs_num = request.getParameter("bs_num");
		String itemSearch = request.getParameter("itemSearch");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		System.out.println(itemSearch);
		
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int searchCount = 0;
		int number = 0;
		
		List itemList = null;
		JaeDAO dao = JaeDAO.getInstance();
		searchCount = dao.getItemSearchCount(id, Integer.parseInt(bs_num), itemSearch);
		
		if(searchCount > 0) {
			itemList = dao.getSearchItems(startRow, pageSize, Integer.parseInt(bs_num), itemSearch);
		}
		
		number = searchCount - (currentPage - 1) * pageSize;
		
		List staffList = null;
		staffList = dao.getStaffName(Integer.parseInt(bs_num));
		
		//jsp�� request ����������
		
		request.setAttribute("searchCount", searchCount);
		request.setAttribute("number", number );
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("itemList", itemList );
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("bs_num", bs_num);
		request.setAttribute("staffList", staffList);
		return "/sellerPage/seller/sellerviewlist.jsp";
	}
	
}
