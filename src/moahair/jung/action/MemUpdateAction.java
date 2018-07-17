package moahair.jung.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.data.dto.MemberDTO;
import moahair.jung.dao.MoaHairDAO;
import moahair.mvc.controller.SuperAction;

public class MemUpdateAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String m_id = (String)session.getAttribute("memId");
		
		MemberDTO dto = new MemberDTO();
		dto.setM_id(m_id);
		dto.setM_address(request.getParameter("m_address"));
		dto.setM_name(request.getParameter("m_name"));
		dto.setM_phone(request.getParameter("m_phone"));
		dto.setM_pw(request.getParameter("m_pw"));
		
		MoaHairDAO dao = MoaHairDAO.getInstance();
		dao.memUpdate(dto);
		
		
		return "/servicePage/memUpdate.jsp";
	}

	
	
}
