package moahair.yeon.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moahair.data.dto.BookedDTO;
import moahair.mvc.controller.SuperAction;
import moahair.yeon.dao.StaffDAO;

public class MypageAction implements SuperAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		StaffDAO dao = StaffDAO.getInstance();
		BookedDTO dto = null;
		int bk_num=3;
		dto = dao.getBookedInfo(bk_num);
	    request.setAttribute("dto", dto);
	    request.setAttribute("bk_num", bk_num); 
	    
	    int result = dao.bkUpdate(bk_num);
	    request.setAttribute("result", result);



		
		return "/bookingPage/mypage.jsp";
		 
	 }
}
