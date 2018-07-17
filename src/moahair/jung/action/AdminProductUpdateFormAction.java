package moahair.jung.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.data.dto.ItemDTO;
import moahair.data.dto.ItemOptionsDTO;
import moahair.jjae.dao.JaeDAO;
import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class AdminProductUpdateFormAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String m_id = (String) session.getAttribute("memId");
		
		KyuDAO k_dao = KyuDAO.getInstance();
		int m_level = k_dao.memberLevel(m_id);
		
		if(m_level == 10) {

			int i_num = Integer.parseInt(request.getParameter("i_num"));
			String pageNum = request.getParameter("pageNum");
			String bs_num = request.getParameter("bs_num");
			
			JaeDAO dao = JaeDAO.getInstance();
			ItemDTO item = dao.getItem(i_num);
			ItemOptionsDTO op0 = dao.getItemOptions(i_num, "10");   //item필수옵션 데이터 가져오기
			ItemOptionsDTO op1 = dao.getItemOptions(i_num, "20");   //item추가옵션1 데이터 가져오기
			ItemOptionsDTO op2 = dao.getItemOptions(i_num, "30");   //item추가옵션2 데이터 가져오기
			System.out.println(op0.getIo_duration());
			
			
			//필수옵션
			String op0_dur[] = op0.getIo_duration().split(",");
			for(int i=0; i<op0_dur.length;i++) {
				if(op0_dur[i].equals("*")) {
					op0_dur[i] = "";                  
				}
			}
			String op0_name[] = op0.getIo_name().split(",");
			for(int i=0; i<op0_name.length;i++) {
				if(op0_name[i].equals("*")) {
					op0_name[i] = "";                  
				}
			}
			String op0_price[] = op0.getIo_price().split(",");
			for(int i=0; i<op0_price.length;i++) {
				if(op0_price[i].equals("*")) {
					op0_price[i] = "";                  
				}
			}

			System.out.println("기본옵션의 io_duration : "+op0.getIo_duration());
			//추가옵션1
			String op1_dur[] = op1.getIo_duration().split(",");
			for(int i=0; i<op1_dur.length;i++) {
				if(op1_dur[i].equals("*")) {
					op1_dur[i] = "";                  
				}
			}
			String op1_name[] = op1.getIo_name().split(",");
			for(int i=0; i<op1_name.length;i++) {
				if(op1_name[i].equals("*")) {
					op1_name[i] = "";                  
				}
			}
			String op1_price[] = op1.getIo_price().split(",");
			for(int i=0; i<op1_price.length;i++) {
				if(op1_price[i].equals("*")) {
					op1_price[i] = "";                  
				}
			}
			//추가옵션2
			String op2_dur[] = op2.getIo_duration().split(",");
			for(int i=0; i<op2_dur.length;i++) {
				if(op2_dur[i].equals("*")) {
					op2_dur[i] = "";                  
				}
			}
			String op2_name[] = op2.getIo_name().split(",");
			for(int i=0; i<op2_name.length;i++) {
				if(op2_name[i].equals("*")) {
					op2_name[i] = "";                  
				}
			}
			String op2_price[] = op2.getIo_price().split(",");
			for(int i=0; i<op2_price.length;i++) {
				if(op2_price[i].equals("*")) {
					op2_price[i] = "";                  
				}
			}



			List staffNameList = dao.getStaffName(Integer.parseInt(bs_num));

			request.setAttribute("bs_num", bs_num);
			request.setAttribute("item", item);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("staffNameList", staffNameList);
			request.setAttribute("i_num", i_num+"");
			request.setAttribute("op0", op0);
			request.setAttribute("op1", op1);
			request.setAttribute("op2", op2);
			request.setAttribute("op0_dur", op0_dur);
			request.setAttribute("op1_dur", op1_dur);
			request.setAttribute("op2_dur", op2_dur);
			request.setAttribute("op0_name", op0_name);
			request.setAttribute("op1_name", op1_name);
			request.setAttribute("op2_name", op2_name);
			request.setAttribute("op0_price", op0_price);
			request.setAttribute("op1_price", op1_price);
			request.setAttribute("op2_price", op2_price);
			
		}
		
		request.setAttribute("m_level", m_level);
		return "/supervisor/adminProductUpdateForm.jsp";
	}

}
