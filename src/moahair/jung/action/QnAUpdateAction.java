package moahair.jung.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.data.dto.BoardDTO;
import moahair.jung.dao.MoaHairDAO;
import moahair.mvc.controller.SuperAction;

public class QnAUpdateAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MoaHairDAO dao = MoaHairDAO.getInstance();
		HttpSession session = request.getSession();
		String m_id = (String)session.getAttribute("memId");
		int bd_num = Integer.parseInt(request.getParameter("bd_num"));
		int bs_num= Integer.parseInt(request.getParameter("bs_num"));
		int i_num = Integer.parseInt(request.getParameter("i_num"));
		int count = Integer.parseInt(request.getParameter("count"));
		
		BoardDTO dto = dao.getQnA_one(bd_num);
		
		request.setAttribute("m_condition", dao.getCondition(m_id));
		request.setAttribute("dto", dto);
		
		String shopname = dao.selectShopName(bs_num);
		request.setAttribute("shopname", shopname);
		
		String name = dao.getMemName(m_id);
		request.setAttribute("name", name);
		
		
		request.setAttribute("bd_num", bd_num);
		request.setAttribute("bs_num", bs_num);
		request.setAttribute("i_num", i_num);
		request.setAttribute("count", count);
		
		return "/servicePage/qnaUpdateForm.jsp";
	}

}
