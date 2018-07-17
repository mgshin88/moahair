package moahair.jung.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moahair.data.dto.BoardDTO;
import moahair.jung.dao.MoaHairDAO;
import moahair.mvc.controller.SuperAction;

public class QnAInputProAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		//占쏙옙占� 占쌍억옙占쌍깍옙 
		BoardDTO dto = new BoardDTO();
		
		dto.setBd_subject(request.getParameter("bd_subject"));
		dto.setBd_contents(request.getParameter("bd_contents"));
		dto.setBd_date(new Timestamp(System.currentTimeMillis()));
		dto.setBd_writer(request.getParameter("m_id"));//占쏙옙占쏙옙占� 占쏙옙占싱듸옙 占쏙옙占쏙옙占쏙옙 
		dto.setBd_i_num(Integer.parseInt(request.getParameter("i_num")));
		dto.setBd_bs_num(Integer.parseInt(request.getParameter("bs_num")));
	
		MoaHairDAO dao = MoaHairDAO.getInstance();
		dao.insertQnA(dto);
		
		
		
		return "/servicePage/qnaInputPro.jsp";
	}

}
