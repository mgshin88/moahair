package moahair.kyu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moahair.mvc.controller.SuperAction;

public class RegFormAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/memberPage/RegForm.jsp";
	}

}
