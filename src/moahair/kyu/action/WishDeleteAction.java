package moahair.kyu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class WishDeleteAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int w_num = Integer.parseInt(request.getParameter("w_num"));

		KyuDAO dao = KyuDAO.getInstance();
		dao.wishDelete(w_num);
		return "/servicePage/wishdelete.jsp";
	}

}
