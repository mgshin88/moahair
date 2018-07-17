package moahair.jung.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.data.dto.BusinessPlusAddressDTO;
import moahair.jung.dao.MoaHairDAO;
import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class SellerAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String m_id = (String)session.getAttribute("memId");
		KyuDAO k_dao = KyuDAO.getInstance();
		
		int m_level = k_dao.memberLevel(m_id);
		
		if(m_level == 10) {
			MoaHairDAO dao = MoaHairDAO.getInstance();
			
			int pageSize = 10;
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null) {
				pageNum = "1";
			}
			
			int currentPage = Integer.parseInt(pageNum);
			int startRow = (currentPage - 1) * pageSize + 1;
			int endRow = currentPage * pageSize;
			int count = 0;
			
			count = dao.getBusinessCount();
			
			ArrayList<BusinessPlusAddressDTO> list = null;
			if(count > 0) {
				list =  dao.getBusiness(startRow, pageSize);
			}else {
				list = (ArrayList<BusinessPlusAddressDTO>) Collections.EMPTY_LIST;
			}
			
			List opening = new ArrayList();
			for(int i=0;i<list.size();i++) {
				
				String o = dao.getTime3(list.get(i).getBs_open());
				opening.add(o);
			}
			List closing = new ArrayList();
			for(int i=0;i<list.size();i++) {
				String c = dao.getTime3(list.get(i).getBs_close());
				closing.add(c);
			}
			
			
			
			request.setAttribute("opening", opening);
			request.setAttribute("closing", closing);
			request.setAttribute("list", list);
			
			
			
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startRow", startRow);
			request.setAttribute("endRow", endRow);
			request.setAttribute("count", count);
		}
		
		request.setAttribute("m_level", m_level);
		
		
		return "/supervisor/seller.jsp";
	}

}
