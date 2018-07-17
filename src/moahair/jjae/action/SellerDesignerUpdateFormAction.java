package moahair.jjae.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.data.dto.StaffDTO;
import moahair.data.dto.TimeDTO;
import moahair.jjae.dao.JaeDAO;
import moahair.mvc.controller.SuperAction;

public class SellerDesignerUpdateFormAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		String bs_num = request.getParameter("bs_num");
		String s_num = request.getParameter("s_num");
		
		JaeDAO dao = JaeDAO.getInstance();
		
		StaffDTO dto = dao.getStaffInfo(Integer.parseInt(s_num));
		
		ArrayList<Integer> businessTime = dao.getTimeNum(Integer.parseInt(bs_num));
		ArrayList<TimeDTO> timetable = dao.getTime(businessTime.get(0), (businessTime.get(1) - businessTime.get(0) + 1));
		
		
		request.setAttribute("timetable", timetable);
		request.setAttribute("dto", dto);
		request.setAttribute("bs_num", bs_num);
		request.setAttribute("s_num", s_num);
		
		return "/sellerPage/seller/sellerDesignerUpdateForm.jsp";
	}
	
}
