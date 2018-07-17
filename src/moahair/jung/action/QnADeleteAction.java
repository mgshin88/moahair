package moahair.jung.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.jung.dao.MoaHairDAO;
import moahair.mvc.controller.SuperAction;

public class QnADeleteAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String m_id = (String)session.getAttribute("memId");
		
		int bd_num = Integer.parseInt(request.getParameter("bd_num"));//??????????????????????????????????????????????
		int bs_num = Integer.parseInt(request.getParameter("bs_num"));
		int i_num = Integer.parseInt(request.getParameter("i_num"));
		
		MoaHairDAO dao = MoaHairDAO.getInstance();
		
		dao.QnADelete(bd_num);
		
		request.setAttribute("bd_num", bd_num);
		request.setAttribute("bs_num", bs_num);
		request.setAttribute("i_num", i_num);
		request.setAttribute("m_id", m_id);
		
		
		return "/servicePage/qnaDelete.jsp";
	}

}
