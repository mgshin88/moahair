package moahair.ethan.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moahair.mvc.controller.SuperAction;

public class SchedulerInputAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String BookingList = request.getParameter("BookingList");
		
		
		return "/sellerPage/seller/sellerBookingInsert";
	}

}
