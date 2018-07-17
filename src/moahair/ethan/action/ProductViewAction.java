package moahair.ethan.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.data.dto.ItemBusinessDTO;
import moahair.data.dto.My_timeDTO;
import moahair.data.dto.StaffDTO;
import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class ProductViewAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String m_id = (String)session.getAttribute("memId");
		int i_num = Integer.parseInt(request.getParameter("i_num"));
		
		KyuDAO dao = KyuDAO.getInstance();
		
		ItemBusinessDTO dto = dao.productSelect(i_num);
		
		String dtooption = dto.getI_option();
		String[] option = dtooption.split(",");
		
		String dtoaddoption1 = dto.getI_option_sel1();
		String[] dtooption1 = null;
		if(dtoaddoption1 != null) {
			dtooption1 = dtoaddoption1.split(",");
		}
		
		String dtoaddoption2 = dto.getI_option_sel2();
		String[] dtooption2 = null;
		if(dtoaddoption2 != null) {
			dtooption2 = dtoaddoption2.split(",");
		}
		
		ArrayList<StaffDTO> slist = new ArrayList<StaffDTO>();
		slist = dao.productDesingerSelect(dto.getBs_num(), dto.getI_num());
		
		My_timeDTO[] tdto = dao.bs_OpenClose(dto.getBs_open(), dto.getBs_close(), dto.getBs_num());
		
		int level = dao.memberLevel(m_id);
		
		request.setAttribute("dto", dto);
		request.setAttribute("tdto", tdto);
		request.setAttribute("slist", slist);
		request.setAttribute("m_id", m_id);
		request.setAttribute("i_num", i_num);
		request.setAttribute("option", option);
		request.setAttribute("dtooption1", dtooption1);
		request.setAttribute("dtooption2", dtooption2);
		request.setAttribute("level", level);
		
		return "/mainPage/S_ProductView.jsp";
	}

}
