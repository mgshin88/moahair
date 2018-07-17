package moahair.kyu.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.data.dto.ItemDTO;
import moahair.data.dto.My_timeDTO;
import moahair.data.dto.StaffDTO;
import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class DesignerViewAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String m_id = (String) session.getAttribute("memId");
		
		int s_num = Integer.parseInt(request.getParameter("s_num"));
		
		KyuDAO dao = KyuDAO.getInstance();
		StaffDTO s_dto = new StaffDTO();
		s_dto = dao.SelectStaffInfo(s_num);
		
		My_timeDTO[] tdto = dao.staff_OpenClose(s_dto.getS_open(), s_dto.getS_close(), s_num);
		
		ArrayList<ItemDTO> list = new ArrayList<ItemDTO>();
		list = dao.SelectStaffItem(s_num);
		
		int sdwl = dao.SelectStaffWishList(m_id, s_num);
		
		request.setAttribute("s_num", s_num);
		request.setAttribute("s_dto", s_dto);
		request.setAttribute("tdto", tdto);
		request.setAttribute("list", list);
		request.setAttribute("m_id", m_id);
		request.setAttribute("sdwl", sdwl);
		
		return "/itemPage/DesignerView.jsp";
	}

}
