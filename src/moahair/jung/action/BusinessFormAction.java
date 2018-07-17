package moahair.jung.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.jung.dao.MoaHairDAO;
import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class BusinessFormAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		String m_id = (String)session.getAttribute("memId");
		KyuDAO k_dao = KyuDAO.getInstance();
		
		int m_level = k_dao.memberLevel(m_id);
		
		if(m_level == 10) {
			ArrayList timetable=null;
			MoaHairDAO dao = MoaHairDAO.getInstance();
			
			timetable = dao.getTime(0, 48);
			
			request.setAttribute("timetable", timetable );					
		}
		
		request.setAttribute("m_level", m_level);
		
		return "/servicePage/businessInputForm.jsp";
	}
	
	

}
