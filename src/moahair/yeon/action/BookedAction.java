package moahair.yeon.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moahair.yeon.dao.StaffDAO;
import moahair.mvc.controller.SuperAction;

public class BookedAction  implements SuperAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name8");
		String today = request.getParameter("today");
		
		System.out.println(name+"===="+today);

		StaffDAO dbPro = StaffDAO.getInstance();
		
		String booked ="";
	    booked = dbPro.getBookedTime(name, today);
	    
	    request.setAttribute("booked", booked);
		
		return "/bookingPage/booked.jsp";
	}
}
