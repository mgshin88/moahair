package moahair.kyu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.data.dto.CartDTO;
import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class CartProAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String m_id = (String)session.getAttribute("memId");
		int i_num = Integer.parseInt(request.getParameter("i_num"));
		
		CartDTO c_dto = new CartDTO();
		c_dto.setC_i_num(i_num);
		c_dto.setC_name(request.getParameter("i_name"));
		c_dto.setC_price(Integer.parseInt(request.getParameter("i_price")));
		c_dto.setC_thumnail(request.getParameter("i_thumbnail"));
		c_dto.setC_option(request.getParameter("i_option"));
		c_dto.setC_option_sel1(request.getParameter("i_option_sel1"));
		c_dto.setC_option_sel2(request.getParameter("i_option_sel2"));
		
		KyuDAO dao = KyuDAO.getInstance();
		int result = dao.InsertItemCart(m_id, c_dto); 
		
		request.setAttribute("i_num", i_num);
		request.setAttribute("result", result);
		return "/itemPage/cartPro.jsp";
		
	}
	
}
