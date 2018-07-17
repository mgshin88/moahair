package moahair.yeon.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.yeon.dao.StaffDAO;
import moahair.yeon.dao.StaffDAO;
import moahair.data.dto.BookedDTO;
import moahair.data.dto.ItemDTO;
import moahair.data.dto.MemberDTO;
import moahair.data.dto.StaffDTO;
import moahair.mvc.controller.SuperAction;

public class BankAction  implements SuperAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String bk_m_id = (String) session.getAttribute("memId");
		MemberDTO member = null;
		StaffDAO dao = StaffDAO.getInstance();
		member = dao.getMember(bk_m_id);
		
		
		BookedDTO dto = new BookedDTO();
		

		String bk_s_name= request.getParameter("bk_s_name");
		String bk_s_num = request.getParameter("bk_s_num");
		String bk_i_num = request.getParameter("bk_i_num");
		String bk_i_name = request.getParameter("bk_i_name");
		String bk_bs_num = request.getParameter("bk_bs_num");
		
		String bk_date = request.getParameter("bk_date");
		String bk_time2 = request.getParameter("bk_time");
		String du = request.getParameter("bk_i_duration");
		int x = dao.getTime1(bk_time2);
		String bk_time = "";
		for (int i = 0; i < Integer.parseInt(du); i++) {
			bk_time += (x + i) + " ";
		}
		String bk_price = request.getParameter("bk_price");
		String bk_i_option = request.getParameter("bk_i_option");
		String bk_i_option_sel1 = request.getParameter("bk_i_option_sel1");
		String bk_i_option_sel2 = request.getParameter("bk_i_option_sel2");
	
		
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy.MM.dd", Locale.KOREA );
		Date currentTime = new Date ();
		String mTime = mSimpleDateFormat.format (currentTime);

		
		dto.setBk_s_num(Integer.parseInt(bk_s_num));
		dto.setBk_s_name(bk_s_name);
		dto.setBk_i_num(Integer.parseInt(bk_i_num));
		dto.setBk_i_name(bk_i_name);
		dto.setBk_bs_num(Integer.parseInt(bk_bs_num));
		dto.setBk_m_id(bk_m_id);
		dto.setBk_m_num(member.getM_num());
		dto.setBk_date(bk_date);
		dto.setBk_time(bk_time);
		dto.setBk_price(Integer.parseInt(bk_price));
		dto.setBk_i_option(bk_i_option);
		dto.setBk_i_option_sel1(bk_i_option_sel1);
		dto.setBk_i_option_sel2(bk_i_option_sel2);
		dto.setBk_pay_date(mTime);
		dto.setBk_i_duration(Integer.parseInt(du));
	
		dao.insertBooked(dto);



		request.setAttribute("bk_date", bk_date);   
		request.setAttribute("bk_time", bk_time2); 
		request.setAttribute("i_name", bk_i_name);
		request.setAttribute("member", member); 
		
		

		return "/bookingPage/bank.jsp";
	}

}
