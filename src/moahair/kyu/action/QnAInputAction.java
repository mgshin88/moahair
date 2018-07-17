package moahair.kyu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.jung.dao.MoaHairDAO;
import moahair.mvc.controller.SuperAction;

public class QnAInputAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String m_id = (String)session.getAttribute("memId");
		
		int i_num = Integer.parseInt(request.getParameter("i_num"));
		int bs_num = Integer.parseInt(request.getParameter("bs_num"));
		
		MoaHairDAO m_dao = MoaHairDAO.getInstance();
		
		// QnA Input
		String shopname = m_dao.selectShopName(bs_num);
		String name = m_dao.getMemName(m_id);
				
		request.setAttribute("shopname", shopname);
		request.setAttribute("name", name);
		request.setAttribute("i_num", i_num);
		request.setAttribute("bs_num", bs_num);
		request.setAttribute("memId", m_id);
		
		return "/itemPage/QnAInput.jsp";
	}

}
