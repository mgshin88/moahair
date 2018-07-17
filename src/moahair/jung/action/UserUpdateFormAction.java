package moahair.jung.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.data.dto.MemberDTO;
import moahair.jung.dao.MoaHairDAO;
import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class UserUpdateFormAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		String m_id = (String)session.getAttribute("memId");
		KyuDAO k_dao = KyuDAO.getInstance();
		
		int m_level = k_dao.memberLevel(m_id);
		
		if(m_level == 10) {
			String mem_id = request.getParameter("m_id");
			MoaHairDAO dao = MoaHairDAO.getInstance();
			MemberDTO dto = null;
			
			dto = dao.getMember(mem_id);
			
			request.setAttribute("dto", dto);
		}
		
		request.setAttribute("m_level", m_level);
		
		
		return "/supervisor/userupdateForm.jsp";
	}

}
