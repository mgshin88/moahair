package moahair.jung.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.data.dto.BoardDTO;
import moahair.data.dto.ItemBusinessDTO;
import moahair.jung.dao.MoaHairDAO;
import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class MyQnAAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		int count = 0;
		
		KyuDAO k_dao = KyuDAO.getInstance();
		count = k_dao.getQnA_MineCount(id);
		
		MoaHairDAO dao = MoaHairDAO.getInstance();
		ArrayList<BoardDTO> b_list = new ArrayList<BoardDTO>();
		
		if(count > 0) {
			b_list = dao.getQnA_Mine(id);
			
		}
		
		ArrayList<ItemBusinessDTO> bdi_list = k_dao.getQnA_ItemName(id);

		request.setAttribute("b_list", b_list);
		request.setAttribute("count", count);
		request.setAttribute("bdi_list", bdi_list);
		
		
		return "/servicePage/myQnA.jsp";
	}

}
