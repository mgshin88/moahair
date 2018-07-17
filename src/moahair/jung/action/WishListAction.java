package moahair.jung.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.data.dto.MemWishListDTO;
import moahair.jung.dao.MoaHairDAO;
import moahair.mvc.controller.SuperAction;

public class WishListAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//占쏙옙澍【占� dto占쏙옙 占쏙옙占쏙옙占싶억옙占싹놂옙..?
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		MoaHairDAO dao = MoaHairDAO.getInstance();
		
		ArrayList<MemWishListDTO> mdto = dao.getwishs(id);
		
		request.setAttribute("mdto",mdto);
		
		
		return "/servicePage/wishlist_main.jsp";
	}

	
}
