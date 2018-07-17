package moahair.ethan.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import moahair.data.dto.BookedDTO;
import moahair.jung.dao.MoaHairDAO;
import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class BookingListAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("memId");
		
		
		MoaHairDAO dao = MoaHairDAO.getInstance();
		KyuDAO k_dao = KyuDAO.getInstance();
		ArrayList<BookedDTO> list = dao.getBooking(id);
		ArrayList<String> athirtyTime = new ArrayList<String>();
		
		int[] bk_time = new int[list.size()];
		int dextime = 30;
		for(int i=0;i<list.size();i++) {
			String time[] = list.get(i).getBk_time().split(" ");
			athirtyTime.add(k_dao.getBookingTime(time[0]));
			
			for(int j=0;j<time.length;j++) {
				bk_time[i] += dextime;
			}
		}
		
		
		
		//My_timeDTO[] tdto = dao.staff_OpenClose(s_dto.getS_open(), s_dto.getS_close(), s_num);
		
		request.setAttribute("booked", list);
		request.setAttribute("bk_time", bk_time);
		request.setAttribute("athirtyTime", athirtyTime);
		
		
		return "/mainPage/bookingList.jsp";
	}

}
