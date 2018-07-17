package moahair.kyu.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.data.dto.BoardDTO;
import moahair.jung.dao.MoaHairDAO;
import moahair.mvc.controller.SuperAction;

public class QnAReplyProAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		int bs_num = Integer.parseInt(request.getParameter("bs_num"));
		int i_num = Integer.parseInt(request.getParameter("i_num"));
		
		BoardDTO dto = new BoardDTO();
		
		HttpSession session = request.getSession();
		String m_id = (String)session.getAttribute("memId");
		
		dto.setBd_subject(request.getParameter("bd_subject"));
		dto.setBd_contents(request.getParameter("bd_contents"));
		dto.setBd_date(new Timestamp(System.currentTimeMillis()));
		dto.setBd_writer(m_id);//ï¿½ï¿½ï¿½ï¿½ï¿? ï¿½ï¿½ï¿½Ìµï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ 
		dto.setBd_i_num(Integer.parseInt(request.getParameter("i_num")));
		dto.setBd_bs_num(Integer.parseInt(request.getParameter("bd_num")));
		dto.setBd_ref(Integer.parseInt(request.getParameter("bd_ref")));
	
		MoaHairDAO dao = MoaHairDAO.getInstance();
		dao.insertQnARef(dto);

		request.setAttribute("bs_num", bs_num);
		request.setAttribute("i_num", i_num);
		
		
		return "/itemPage/QnAReplyPro.jsp";
	}

}
