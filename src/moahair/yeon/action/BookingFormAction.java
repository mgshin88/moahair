package moahair.yeon.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import moahair.yeon.dao.StaffDAO;
import moahair.data.dto.ItemDTO;
import moahair.data.dto.StaffDTO;
import moahair.mvc.controller.SuperAction;

public class BookingFormAction implements SuperAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		
		StaffDAO dao = StaffDAO.getInstance();
		
		
		StaffDTO dto = null;
		String name = request.getParameter("s_name");
		dto = dao.getSchedule(name);
		
		
		String opt = request.getParameter("i_option"); // 옵션이름/가격/소요시간 => // 기장추가있음,3000,150
		String opt1 = request.getParameter("i_option_sel1"); // 옵션이름/가격/소요시간
		String opt2 = request.getParameter("i_option_sel2"); // 옵션이름/가격/소요시간
	
		
        String i_option_name = opt.split(",")[0];
	    String i_option_price = opt.split(",")[1];
	    String i_option_du = opt.split(",")[2];
	    int dur = Integer.parseInt(i_option_du)/30;
	    int price = Integer.parseInt(i_option_price);

	    String i_option_sel1_name = opt1.split(",")[0];
	    String i_option_sel1_price = opt1.split(",")[1];
	    String i_option_sel1_du = opt1.split(",")[2];
	    int dur1 = Integer.parseInt(i_option_sel1_du)/30;
	    int price1 = Integer.parseInt(i_option_sel1_price);
	    
	    String i_option_sel2_name = opt2.split(",")[0];
	    String i_option_sel2_price = opt2.split(",")[1];
	    String i_option_sel2_du = opt2.split(",")[2];
	    int dur2 = Integer.parseInt(i_option_sel2_du)/30;
	    int price2 = Integer.parseInt(i_option_sel2_price);

		ItemDTO itemList = null;
		int i_num = Integer.parseInt(request.getParameter("i_num"));
		itemList = dao.getitem(i_num);
	
		int totaldu = dur +dur1 +dur2+ itemList.getI_duration();
		int totalprice = price +price1 +price2+ itemList.getI_price();

		
		int du = (itemList.getI_duration() + totaldu) * 30;
		String du2="";
		if(du/60==0) {
		du2 = du%60 + "분";
		}else {
			if(du%60==0) {
				du2 = du/60 + "시간";
				}else{
					du2 = du/60 + "시간" +du%60 + "분";
				}
			}

		
		
    	String begining=dto.getS_open()+"";
    	String closing=dto.getS_close()+"";	
		List<String> timetable2 = null;
		timetable2 = dao.getTime2(Integer.parseInt(begining), Integer.parseInt(closing)-Integer.parseInt(begining));
		String timetable3="";
		for(String d : timetable2){
			timetable3+=(d+" ");
		}

		
	
		request.setAttribute("begining", begining);
		request.setAttribute("closing", closing);
		request.setAttribute("bk_i_num", i_num);
	    request.setAttribute("timetable3", timetable3);
	    request.setAttribute("schedule", dto);  
	    request.setAttribute("name", name); 
	    request.setAttribute("itemList", itemList); 
	    request.setAttribute("dr", totaldu);
	    request.setAttribute("i_option", i_option_name); 
	    request.setAttribute("i_option_sel1", i_option_sel1_name); 
	    request.setAttribute("i_option_sel2", i_option_sel2_name); 
	    request.setAttribute("bk_price", totalprice); 

	    request.setAttribute("du2", du2); 

	    


		
		 return "/bookingPage/bookingForm.jsp";
	}

}
