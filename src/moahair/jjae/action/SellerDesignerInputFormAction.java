package moahair.jjae.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.data.dto.TimeDTO;
import moahair.jjae.dao.JaeDAO;
import moahair.mvc.controller.SuperAction;
import moahair.yeon.dao.StaffDAO;

public class SellerDesignerInputFormAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("memId");
		
		int bs_num = Integer.parseInt(request.getParameter("bs_num"));
		
		
		request.setCharacterEncoding("UTF-8");
	
		ArrayList<TimeDTO> timetable = null;
		StaffDAO dao = StaffDAO.getInstance();
		
		JaeDAO jjdao=JaeDAO.getInstance();
		ArrayList<Integer> businessTime= jjdao.getTimeNum(bs_num);
		
		timetable = dao.getTime(businessTime.get(0), (businessTime.get(1) - businessTime.get(0) + 1));
		
		for(int i=0; i<timetable.size(); i++) {
			System.out.println(timetable.get(i).getAthirty());
		}
		request.setAttribute("timetable", timetable);
		request.setAttribute("bs_num", bs_num);
		
		return "/sellerPage/seller/sellerDesignerInputForm.jsp";
	}
	
}
