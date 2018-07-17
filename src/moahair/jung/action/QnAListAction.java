package moahair.jung.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.jung.dao.MoaHairDAO;
import moahair.mvc.controller.SuperAction;

public class QnAListAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
//		int i_num = Integer.parseInt(request.getParameter("i_num"));//������ ��ȣ 

		
		String id = (String)session.getAttribute("memId");
		int bs_num = 3;
		int i_num = 47;
		
		List list=null;
		
		
		MoaHairDAO dao = MoaHairDAO.getInstance();
		int count = dao.getArticleCount(i_num);
		
		if(count > 0) {
			list = dao.getQnA(i_num);
		}else {
			list = Collections.EMPTY_LIST;
		}
		
		request.setAttribute("list",list );
		request.setAttribute("count", count);
		
		request.setAttribute("check", dao.checkSeller(bs_num, id) );
		
		return "/servicePage/qnaList.jsp";
	}

}
