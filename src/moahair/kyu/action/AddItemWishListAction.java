package moahair.kyu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class AddItemWishListAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String m_id = (String)session.getAttribute("memId");
		int i_num = Integer.parseInt(request.getParameter("w_i_num"));
		
		KyuDAO dao = KyuDAO.getInstance();
		int result = dao.InsertItemWishList(m_id, i_num);
		
		request.setAttribute("result", result);
		request.setAttribute("i_num", i_num);
		
		return "/itemPage/AddItemWishList.jsp"; 
	}
	
}
