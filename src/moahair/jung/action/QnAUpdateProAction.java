package moahair.jung.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moahair.data.dto.BoardDTO;
import moahair.jung.dao.MoaHairDAO;
import moahair.mvc.controller.SuperAction;

public class QnAUpdateProAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
request.setCharacterEncoding("UTF-8");
		
		int bd_num = Integer.parseInt(request.getParameter("bd_num"));
		int bs_num = Integer.parseInt(request.getParameter("bs_num"));
		int i_num = Integer.parseInt(request.getParameter("i_num"));
		
		BoardDTO dto = new BoardDTO();
		
		dto.setBd_subject(request.getParameter("bd_subject"));
		dto.setBd_contents(request.getParameter("bd_contents"));
		
		MoaHairDAO dao = MoaHairDAO.getInstance();
		dao.updateQnA(dto, bd_num);
		
		request.setAttribute("bd_num", bd_num);
		request.setAttribute("bs_num", bs_num);
		request.setAttribute("i_num", i_num);
		
		return "/servicePage/qnaUpdatePro.jsp";
	}

}
