package moahair.yeon.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moahair.data.dto.BookedDTO;
import moahair.mvc.controller.SuperAction;
import moahair.yeon.dao.StaffDAO;

public class BkUpdateAction implements SuperAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		String bk_num = request.getParameter("bk_num");
		StaffDAO dao = StaffDAO.getInstance();
		
		BookedDTO dto = null;

		dto = dao.getBookedInfo(Integer.parseInt(bk_num));
		
		int re = dao.bkUpdate(Integer.parseInt(bk_num));
	
		request.setAttribute("re", re);
	    request.setAttribute("dto", dto);
	    request.setAttribute("bk_num", bk_num);
	    
	    return "/bookingPage/bkUpdate.jsp";
	   	}
}
