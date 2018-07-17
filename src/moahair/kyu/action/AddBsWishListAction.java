package moahair.kyu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class AddBsWishListAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String m_id = (String)session.getAttribute("memId");
		int bs_num = Integer.parseInt(request.getParameter("w_bs_num"));
		
		KyuDAO dao = KyuDAO.getInstance();
		int result = dao.InsertBsWishList(m_id, bs_num);

		request.setAttribute("result", result);
		request.setAttribute("bs_num", bs_num);
		return "/itemPage/AddBsWishList.jsp";
	}

}
