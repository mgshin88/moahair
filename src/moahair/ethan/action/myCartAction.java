package moahair.ethan.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import moahair.data.dto.CartDTO;
import moahair.jung.dao.MoaHairDAO;
import moahair.mvc.controller.SuperAction;

public class myCartAction implements SuperAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("memId");
		
		MoaHairDAO dao = MoaHairDAO.getInstance();
		ArrayList<CartDTO> cart = null;
		
		cart = dao.getCart(id);
		int clc = cart.size();
		request.setAttribute("cart", cart);
		request.setAttribute("clc", clc);
		return "/mainPage/mycartPage.jsp";
	}
}
