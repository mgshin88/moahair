package moahair.jjae.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.jjae.dao.*;
import moahair.data.dto.*;
import moahair.mvc.controller.SuperAction;

public class BusinessInputFormAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		String bs_num = request.getParameter("bs_num");
		
		JaeDAO dao = JaeDAO.getInstance();
		
		ArrayList timetable = dao.getTime(1, 48);
				
		request.setAttribute("timetable", timetable );		
		request.setAttribute("bs_num", bs_num);
		
		return "/sellerPage/seller/businessinputform.jsp";
	}
	
	

}
