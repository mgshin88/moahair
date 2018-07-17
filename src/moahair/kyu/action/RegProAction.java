package moahair.kyu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moahair.data.dto.MemberDTO;
import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class RegProAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		MemberDTO dto = new MemberDTO();
		dto.setM_id(request.getParameter("rg_id"));
		dto.setM_pw(request.getParameter("rg_pw"));
		dto.setM_name(request.getParameter("m_name"));
		dto.setM_address(request.getParameter("m_address"));
		dto.setM_phone(request.getParameter("m_phone"));
		dto.setM_email(request.getParameter("m_email"));
		
		KyuDAO manager = KyuDAO.getInstance();
		manager.insertMember(dto);
		
		return "/memberPage/RegPro.jsp";
	}

}
