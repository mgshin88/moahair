package moahair.jung.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.data.dto.BusinessPlusAddressDTO;
import moahair.data.dto.My_timeDTO;
import moahair.jung.dao.MoaHairDAO;
import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class ShopUpdateFormAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		String m_id = (String)session.getAttribute("memId");
		KyuDAO k_dao = KyuDAO.getInstance();
		
		int m_level = k_dao.memberLevel(m_id);
		
		if(m_level == 10) {
			int bs_num = Integer.parseInt(request.getParameter("bs_num"));
			
			MoaHairDAO dao = MoaHairDAO.getInstance();
			BusinessPlusAddressDTO dto = dao.getshopInfo(bs_num);
			ArrayList timelist = dao.getTime();
			
			
			request.setAttribute("dto", dto);
			request.setAttribute("timetable", timelist);
		}
		
		request.setAttribute("m_level", m_level);
		
		
		
		return "/supervisor/shopUpdateForm.jsp";
	}

}
