package moahair.yeon.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.data.dto.BookedDTO;
import moahair.mvc.controller.SuperAction;
import moahair.yeon.dao.StaffDAO;

public class ModifyProAction implements SuperAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		StaffDAO dao = StaffDAO.getInstance();
		
		int bk_num =Integer.parseInt(request.getParameter("bk_num"));
	
		
		String bk_date = request.getParameter("bk_date");
		String bk_time = request.getParameter("bk_time");
		
		String du = request.getParameter("du");
		int x = dao.getTime1(bk_time);
		String bk_time3 = "";
		for (int i = 0; i < Integer.parseInt(du); i++) {
			bk_time3 += (x + i) + " ";
		}

		dao.modifyBooking(bk_num, bk_date, bk_time3);

		request.setAttribute("today", bk_date);
		request.setAttribute("time", bk_time);

		
		return "/bookingPage/modifyPro.jsp";
	
	}

}
