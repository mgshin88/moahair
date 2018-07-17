package moahair.kyu.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.jung.dao.MoaHairDAO;
import moahair.mvc.controller.SuperAction;

public class QnAListAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String m_id = (String)session.getAttribute("memId");
		int i_num = Integer.parseInt(request.getParameter("i_num"));
		int bs_num = Integer.parseInt(request.getParameter("bs_num"));
		
		int pageSize = 10;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		
		MoaHairDAO m_dao = MoaHairDAO.getInstance();
		count = m_dao.getArticleCount(i_num);
		
		List b_list = null;
		
		if(count > 0) {
			b_list = m_dao.getItemQnA(i_num, startRow, endRow);
		}else {
			b_list = Collections.EMPTY_LIST;
		}

		
		request.setAttribute("b_list", b_list);
		request.setAttribute("count", count);
		request.setAttribute("check", m_dao.checkSeller(bs_num, m_id));
		request.setAttribute("i_num", i_num);
		request.setAttribute("bs_num", bs_num);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", startRow);
		request.setAttribute("endRow", endRow);
		
		return "/itemPage/QnAView.jsp";
	}

}
