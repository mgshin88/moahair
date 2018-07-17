package moahair.kyu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class BookingDeleteAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int bk_num = Integer.parseInt(request.getParameter("bk_num"));
		
		KyuDAO dao = KyuDAO.getInstance();
		int result = dao.bkDelete(bk_num);
		
		request.setAttribute("result", result);
		
		return "/servicePage/bookingDelete.jsp";
	}

}
