package moahair.jung.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.data.dto.BusinessPlusAddressDTO;
import moahair.data.dto.MemberDTO;
import moahair.jung.dao.MoaHairDAO;
import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class SellerSearchAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		String m_id = (String)session.getAttribute("memId");
		KyuDAO k_dao = KyuDAO.getInstance();
		
		int m_level = k_dao.memberLevel(m_id);
		
		if(m_level == 10) {
			MoaHairDAO dao = MoaHairDAO.getInstance();
			String bs_name = request.getParameter("bs_name");
			String pageNum = request.getParameter("pageNum");
			
			String o = null;
			String c = null;
			
			int count = 0;
			count = dao.getBusinessCount();
			ArrayList<BusinessPlusAddressDTO> list = null;
			if(count > 0) {
				list =  dao.getBusiness2(bs_name);
				
			}else {
				list = (ArrayList<BusinessPlusAddressDTO>) Collections.EMPTY_LIST;
			}
			
			if(list.size() > 0) {
				o = dao.getTime3(list.get(0).getBs_open());
				c = dao.getTime3(list.get(0).getBs_close());
			}
			
			request.setAttribute("o", o);
			request.setAttribute("c", c);
			request.setAttribute("list", list);
			
			request.setAttribute("pageNum", pageNum);
		}
		
		request.setAttribute("m_level", m_level);
		
		return "/supervisor/sellerSearch.jsp";
	}

}
