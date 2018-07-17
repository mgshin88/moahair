package moahair.jung.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moahair.data.dto.BoardDTO;
import moahair.jung.dao.MoaHairDAO;
import moahair.mvc.controller.SuperAction;

public class QnARelyFormAction implements SuperAction{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//int bs_num= Integer.parseInt(request.getParameter("bs_num"));

		int bs_num = 3;//������ ��ȣ ���Ƿ� �ֱ� 
		int bd_num = Integer.parseInt(request.getParameter("bd_num"));
		
		
		MoaHairDAO dao = MoaHairDAO.getInstance();
		String shopname = dao.selectShopName(bs_num);
		BoardDTO dto = dao.getQnA_one(bd_num);
		
		int bd_ref = dto.getBd_ref();
		//int bd_bs_num = dto.getBd_bs_num();
		int i_num = dto.getBd_i_num();
		
		
		request.setAttribute("i_num", i_num);
		request.setAttribute("shopname", shopname);
		request.setAttribute("bd_num", bd_num);
		request.setAttribute("bd_ref", bd_ref);
		request.setAttribute("dto", dto);
		
		return "/servicePage/qnaRelyForm.jsp";
	}
}
