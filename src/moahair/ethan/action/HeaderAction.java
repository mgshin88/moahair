package moahair.ethan.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.data.dto.MemWishListDTO;
import moahair.jung.dao.MoaHairDAO;
import moahair.mvc.controller.SuperAction;

public class HeaderAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("memId");
		
		MoaHairDAO dao = MoaHairDAO.getInstance();
		
		ArrayList<MemWishListDTO> mdto = dao.getwishs(id);
		request.setAttribute("mdto", mdto);
		request.setAttribute("memId", id);
		
		return "mainPage/header.jsp";
	}

}
