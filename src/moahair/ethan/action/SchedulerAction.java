package moahair.ethan.action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moahair.data.dto.BookingListDTO;
import moahair.data.dto.ItemOptionsDTO;
import moahair.ethan.dao.MainDB;
import moahair.mvc.controller.SuperAction;

public class SchedulerAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<String> TimeList = null;
		ArrayList<BookingListDTO> BookingList = null;
		ArrayList ItemList = null;
		int io_option = 0;
		MainDB main = MainDB.getInstance();
		String s_name = request.getParameter("s_name");
		String i_name = request.getParameter("i_name");
		String bsnum = request.getParameter("bs_num");
		
		if(bsnum == null) {
			bsnum = "0";
		}
		int bs_num= Integer.parseInt(bsnum);
		int count = 0;
		
		if(request.getParameter("count")!=null) {
			count = Integer.parseInt(request.getParameter("count"));
		}
		System.out.println(count);
		
		// select 
		TimeList = main.getMyTIme();
		BookingList = main.getBookingList(bs_num);
		ItemList = main.getBookingItemList(bs_num, count, s_name, i_name, io_option);
		

		for (int i = 0; i < BookingList.size(); i++) {
			BookingListDTO dto = BookingList.get(i);
			String date = dto.getBk_date();
			String time = dto.getBk_time();
			String[] t2 = time.split(" ");
			int start = Integer.parseInt(t2[0]);
			String s = TimeList.get(start - 1);
			int end = Integer.parseInt(t2[t2.length - 1]);
			String e = TimeList.get(end - 1);
			dto.setStartTime(date + "T" + s + ":00");
			dto.setEndTime(date + "T" + e + ":00");
		}

//		insert
		if (count==2) {
				if (io_option==10) {
					ItemOptionsDTO dto = (ItemOptionsDTO) ItemList.get(0); 
					String name = dto.getIo_name();
					String price = dto.getIo_price();
					String duration = dto.getIo_duration();
					String [] i1 = name.split(",");
					String [] i2 = price.split(",");
					String [] i3 = duration.split(",");
					//for(int j =0 ; j < i1.length ; i++) {
					//	setXXX(i1[j]+","+i2[j]+","+i3[j]);
					//}
				
			}
			
		}

		request.setAttribute("BookingList", BookingList);
		request.setAttribute("bs_num", bs_num);
		request.setAttribute("count", count);
		request.setAttribute("itemList", ItemList);

		return "/sellerPage/seller/sellerBookingList.jsp";
	}

}
