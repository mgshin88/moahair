package moahair.kyu.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.data.dto.BoardDTO;
import moahair.jung.dao.MoaHairDAO;
import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class QnASelecterAction implements SuperAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String m_id = (String) session.getAttribute("memId");
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		KyuDAO dao = KyuDAO.getInstance();
		MoaHairDAO mdao = MoaHairDAO.getInstance();
		
		ArrayList<BoardDTO> mdto = null;
		
		if(num == 0) {
			mdto = mdao.getQnA_Mine(m_id);
		} else {
			mdto = dao.getQnA_MineSelecter(m_id, num);
		}
		
		request.setAttribute("mdto", mdto);
		request.setAttribute("num", num);
		
		return "/servicePage/qnaSelecter.jsp";
	}
}
