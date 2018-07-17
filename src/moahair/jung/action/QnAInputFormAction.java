package moahair.jung.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.jung.dao.MoaHairDAO;
import moahair.mvc.controller.SuperAction;

public class QnAInputFormAction implements SuperAction{


	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		HttpSession session = request.getSession();
		
		MoaHairDAO dao = MoaHairDAO.getInstance();
		
		//get占쏙옙占쏙옙占쏙옙占� 占쏙옙占쏙옙占쌍댐옙 占쏙옙 占쏙옙占쏙옙占쏙옙호  占쌨깍옙 
		//int i_num= Integer.parseInt(request.getParameter("i_num"));
		//int bd_bs_num= Integer.parseInt(request.getParameter("bs_num"));
		
		String id = (String) session.getAttribute("memId");
		
		
		int i_num = 47;//占쏙옙품占쏙옙호 占쌨아와억옙占쏙옙  
		int bs_num = 2;//占쏙옙占싼뱄옙 占쌨아와듸옙 占쏙옙占쏙옙,, 
		
		
		String shopname = dao.selectShopName(bs_num);
		request.setAttribute("shopname", shopname);
		
		String name = dao.getMemName(id);
		request.setAttribute("name", name);
		
		
		request.setAttribute("m_condition", dao.getCondition(id));
		request.setAttribute("memId", id);
		System.out.print(dao.getCondition(id));
		
		

		
	
		request.setAttribute("bs_num", bs_num);
		request.setAttribute("i_num", i_num);
	
		
		return "/servicePage/qnaInputForm.jsp";
	}

}
