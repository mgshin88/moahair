package moahair.kyu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class CartDeleteAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int c_num = Integer.parseInt(request.getParameter("c_num"));

		KyuDAO dao = KyuDAO.getInstance();
		dao.cartDelete(c_num);
		
		return "/servicePage/cartDelete.jsp";
	}

}
