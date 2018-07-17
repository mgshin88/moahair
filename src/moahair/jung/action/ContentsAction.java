package moahair.jung.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moahair.data.dto.BoardDTO;
import moahair.jung.dao.MoaHairDAO;
import moahair.mvc.controller.SuperAction;

public class ContentsAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
  
		int i_num = Integer.parseInt(request.getParameter("i_num"));//������ ��ȣ 
		
		
		
		MoaHairDAO dao = MoaHairDAO.getInstance();
		ArrayList list  = dao.getQnA(i_num);
		
		request.setAttribute("list", list);
		
		return "/servicePage/contents.jsp";
	}

}
