package moahair.yeon.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moahair.yeon.dao.StaffDAO;
import moahair.data.dto.TimeDTO;
import moahair.mvc.controller.SuperAction;


public class ScheduleFormAction implements SuperAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		ArrayList<TimeDTO> timetable = null;
		StaffDAO dao = StaffDAO.getInstance();
		timetable = dao.getTime(1, 48);
		request.setAttribute("timetable", timetable);
		
		
		
		
		
		return "/bookingPage/scheduleForm.jsp";
	}
	 
}
