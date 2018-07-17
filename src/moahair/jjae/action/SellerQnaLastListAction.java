package moahair.jjae.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.jjae.dao.JaeDAO;
import moahair.mvc.controller.SuperAction;

public class SellerQnaLastListAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pageSize = 10;
		String pageNum = request.getParameter("pageNum");
		String bs_num = request.getParameter("bs_num");
		
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int countArticle = 0;
		int number = 0;
		
		List articleList = null;
		JaeDAO dao = JaeDAO.getInstance();
		countArticle = dao.getLastBoardCount(Integer.parseInt(bs_num));
		
		if(countArticle > 0) {
			articleList = dao.getLastArticles(startRow, pageSize, Integer.parseInt(bs_num));
		}
		
		number = countArticle - (currentPage - 1) * pageSize;
		
		HashMap<Integer, String> map = dao.getItemName(Integer.parseInt(bs_num));
		
		//jsp로 request 정보보내기
		request.setAttribute("count", countArticle);
		request.setAttribute("number", number );
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("bs_num", bs_num);
		request.setAttribute("articleList", articleList);
		request.setAttribute("itemNameMap", map);
		
		return "/sellerPage/seller/sellerQnaLastList.jsp";
	}
	
}
