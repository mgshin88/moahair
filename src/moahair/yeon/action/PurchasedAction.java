package moahair.yeon.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import moahair.data.dto.StaffDTO;
import moahair.mvc.controller.SuperAction;
import moahair.yeon.dao.StaffDAO;

public class PurchasedAction implements SuperAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		String bk_id = (String) session.getAttribute("memId");
		String bk_date = request.getParameter("bk_date");
		String bk_time = request.getParameter("bk_time");
		
		System.out.println(request.getRealPath("bookingPage"));
		

		request.setAttribute("today", bk_date);
		request.setAttribute("time", bk_time);

		return "/bookingPage/purchased.jsp";
	}
}
