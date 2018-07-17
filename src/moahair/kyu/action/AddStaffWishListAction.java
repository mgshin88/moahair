package moahair.kyu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class AddStaffWishListAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String m_id = (String)session.getAttribute("memId");
		int s_num = Integer.parseInt(request.getParameter("w_s_num"));
		
		KyuDAO dao = KyuDAO.getInstance();
		int result = dao.InsertStaffWishList(m_id, s_num);
		
		request.setAttribute("result", result);
		request.setAttribute("s_num", s_num);
		
		return "/itemPage/AddStaffWishList.jsp";
	}

}
