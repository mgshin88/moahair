package moahair.jung.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.data.dto.BoardDTO;
import moahair.jung.dao.MoaHairDAO;
import moahair.mvc.controller.SuperAction;

public class QnARelyProAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		BoardDTO dto = new BoardDTO();
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		dto.setBd_subject(request.getParameter("bd_subject"));
		dto.setBd_contents(request.getParameter("bd_contents"));
		dto.setBd_date(new Timestamp(System.currentTimeMillis()));
		dto.setBd_writer(id);//����� ���̵� ������ 
		dto.setBd_i_num(Integer.parseInt(request.getParameter("i_num")));
		dto.setBd_bs_num(Integer.parseInt(request.getParameter("bd_num")));
		dto.setBd_ref(Integer.parseInt(request.getParameter("bd_ref")));
	
		MoaHairDAO dao = MoaHairDAO.getInstance();
		dao.insertQnARef(dto);

		
		return "/servicePage/qnaRelyPro.jsp";
	}

}
