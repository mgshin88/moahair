package moahair.jung.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.data.dto.CartDTO;
import moahair.jung.dao.MoaHairDAO;
import moahair.mvc.controller.SuperAction;

public class myCartAction implements SuperAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		MoaHairDAO dao = MoaHairDAO.getInstance();
		ArrayList<CartDTO> cart = null;
		
		cart = dao.getCart(id);
		int clc = cart.size();
		request.setAttribute("cart", cart);
		request.setAttribute("clc", clc);
		return "/servicePage/mycartPage.jsp";
	}
}
