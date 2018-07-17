package moahair.kyu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class ConfirmIdAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
	    String rg_id = request.getParameter("rg_id");
		KyuDAO manager = KyuDAO.getInstance();
	    boolean check= manager.confirmId(rg_id);
	 
	    request.setAttribute("rg_id", rg_id);
	    request.setAttribute("check", check);
		
		return "/memberPage/confirmId.jsp";
	}

}
