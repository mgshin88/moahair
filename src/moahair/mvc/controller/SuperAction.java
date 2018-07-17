package moahair.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SuperAction {
	public String requestPro(HttpServletRequest request, 
			HttpServletResponse response) throws Exception;
}
