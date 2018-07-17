package moahair.jjae.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.data.dto.BusinessAddressDTO;
import moahair.data.dto.BusinessDTO;
import moahair.jjae.dao.JaeDAO;
import moahair.mvc.controller.SuperAction;

public class BusinessUpdateFormAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		String bs_num = request.getParameter("bs_num");
		
		JaeDAO dao = JaeDAO.getInstance();
		
		BusinessDTO dto = dao.getBusinessInfo(Integer.parseInt(bs_num));
		System.out.println(dto);
		BusinessAddressDTO adto = dao.getBusinessAdderss(Integer.parseInt(bs_num));
		System.out.println(adto);
		ArrayList timetable = dao.getTime(1, 48);
		
		
		request.setAttribute("timetable", timetable );	
		request.setAttribute("info", dto);
		request.setAttribute("addInfo", adto);
		request.setAttribute("bs_num", bs_num);
		
		return "/sellerPage/seller/businessupdateform.jsp";
	}
	
}
