package moahair.jung.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.jung.dao.MoaHairDAO;
import moahair.mvc.controller.SuperAction;

public class MemDeleteAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		MoaHairDAO dao = MoaHairDAO.getInstance();
		dao.memDelete(id);

		session.invalidate();
		return "/servicePage/memDelete.jsp";
	}
}
