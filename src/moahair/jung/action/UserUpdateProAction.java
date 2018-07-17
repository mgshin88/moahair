package moahair.jung.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.data.dto.MemberDTO;
import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class UserUpdateProAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		String m_id = (String)session.getAttribute("memId");
		KyuDAO k_dao = KyuDAO.getInstance();
		
		int m_level = k_dao.memberLevel(m_id);
		
		if(m_level == 10) {
			String mem_id = request.getParameter("m_id");
			
			MemberDTO dto = new MemberDTO();
			dto.setM_id(mem_id);
			dto.setM_address(request.getParameter("m_address"));
			dto.setM_name(request.getParameter("m_name"));
			dto.setM_phone(request.getParameter("m_phone"));
			dto.setM_pw(request.getParameter("m_pw"));
			dto.setM_condition(Integer.parseInt(request.getParameter("m_condition")));
			
			KyuDAO dao = KyuDAO.getInstance();
			dao.adminMemUpdate(dto);
		}
		
		request.setAttribute("m_level", m_level);
		
		
		return "/supervisor/userupdatePro.jsp";
	}

}
