package moahair.jjae.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.mvc.controller.SuperAction;

public class SellerDateSelAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("memId");
		String bs_num = request.getParameter("bs_num");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1 = Calendar.getInstance();
		c1.getTime();
		String strToday = sdf.format(c1.getTime());
		c1.add(Calendar.DATE, -1);
		String strYesterday = sdf.format(c1.getTime());
		System.out.println(strToday+""+strYesterday);
		request.setAttribute("today", strToday);
		request.setAttribute("yesterday", strYesterday);
		request.setAttribute("bs_num", bs_num);
		return "/sellerPage/seller/sellerDateSel.jsp";
	}

}
