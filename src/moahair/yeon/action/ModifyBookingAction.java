package moahair.yeon.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moahair.data.dto.BookedDTO;
import moahair.data.dto.ItemDTO;
import moahair.data.dto.StaffDTO;
import moahair.mvc.controller.SuperAction;
import moahair.yeon.dao.StaffDAO;

public class ModifyBookingAction implements SuperAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		String bk_num = request.getParameter("bk_num");
		StaffDAO dao = StaffDAO.getInstance();
		
		BookedDTO dto = null;

		dto = dao.getBookedInfo(Integer.parseInt(bk_num));
	    request.setAttribute("dto", dto);
	    request.setAttribute("bk_num", bk_num);

		StaffDTO dto2 = null;
		dto2 = dao.getSchedule(dto.getBk_s_name());
		
		
		
		ItemDTO itemList = null;
		itemList = dao.getitem(dto.getBk_i_num());
	

		
    	String begining=dto2.getS_open()+"";
    	String closing=dto2.getS_close()+"";

		List<String> timetable2 = null;
		timetable2 = dao.getTime2(Integer.parseInt(begining), Integer.parseInt(closing)-Integer.parseInt(begining));
		String timetable3="";
		
		for(String d : timetable2){
			timetable3+=(d+" ");
		}
	  

	
		request.setAttribute("begining", begining);
		request.setAttribute("closing", closing);

	    request.setAttribute("timetable3", timetable3);
	    request.setAttribute("schedule", dto2);  

	    request.setAttribute("itemList", itemList); 
	    request.setAttribute("dr", dto.getBk_i_duration());


		
		 return "/bookingPage/modifybooking.jsp";
	}
}
