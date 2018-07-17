package moahair.ethan.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.data.dto.BusinessPlusAddressDTO;
import moahair.data.dto.ItemDTO;
import moahair.data.dto.My_timeDTO;
import moahair.data.dto.StaffDTO;
import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class SellerViewAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String m_id = (String) session.getAttribute("memId");
		
		int bs_num = Integer.parseInt(request.getParameter("bs_num"));
		
		KyuDAO dao = KyuDAO.getInstance();
		BusinessPlusAddressDTO bpa = new BusinessPlusAddressDTO();
		bpa = dao.SelectBsInfo(bs_num);

		My_timeDTO[] tdto = dao.bs_OpenClose(bpa.getBs_open(), bpa.getBs_close(), bs_num);

		ArrayList<ItemDTO> list = new ArrayList<ItemDTO>();
		list = dao.SelectBsItem(bs_num);
		
		ArrayList<StaffDTO> slist = new ArrayList<StaffDTO>();
		slist = dao.BusinessDesingerSelect(bpa.getBs_num());
		
		
		request.setAttribute("bs_num", bs_num);
		request.setAttribute("bpa", bpa);
		request.setAttribute("tdto", tdto);
		request.setAttribute("list", list);
		request.setAttribute("slist", slist);
		request.setAttribute("m_id", m_id);
		return "/mainPage/S_SellerView.jsp";
	}

}
