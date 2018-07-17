package moahair.jung.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.data.dto.MemberDTO;
import moahair.jung.dao.MoaHairDAO;
import moahair.mvc.controller.SuperAction;

public class MyInformationFormAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		String m_id = (String)session.getAttribute("memId");
		MoaHairDAO dao = MoaHairDAO.getInstance();
		MemberDTO dto = null;
		
		dto = dao.getMember(m_id);
		
		request.setAttribute("dto", dto);
		
		
		return "/servicePage/myInformationForm.jsp";
	}

}
