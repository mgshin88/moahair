package moahair.jjae.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import moahair.data.dto.StaffDTO;
import moahair.jjae.dao.JaeDAO;
import moahair.mvc.controller.SuperAction;
import moahair.yeon.dao.StaffDAO;

public class SellerDesignerUpdateProAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("memId");

		String path = request.getSession().getServletContext().getRealPath("//img//seller//staff//");
		String enc = "UTF-8";
		int maxSize = 1024 * 1024 * 10;

		DefaultFileRenamePolicy dp = new DefaultFileRenamePolicy();
		MultipartRequest mr = new MultipartRequest(request, path, maxSize, enc, dp);
		
		int s_num = Integer.parseInt(mr.getParameter("s_num"));
		int bs_num = Integer.parseInt(mr.getParameter("bs_num"));
		String s_name = mr.getParameter("s_name");
		String s_title = mr.getParameter("s_title");
		String s_holiday = mr.getParameter("s_holiday");
		int s_open = Integer.parseInt(mr.getParameter("s_open"));
		int s_close = Integer.parseInt(mr.getParameter("s_close"));
		
		//image getParameter part
		String orgProSys = mr.getParameter("orgProSys"); 	//변경 전 s_profile
		String orgProOrg = mr.getParameter("orgProOrg");	//변경 전 s_profile_org
		String orgBgSys = mr.getParameter("orgBgSys");		//변경 전 s_background
		String orgBgOrg = mr.getParameter("orgBgOrg");		//변경 전 s_background_org
		
		String s_profile = mr.getFilesystemName("s_profile");				//변경 하려는 s_profile
		String s_profile_org = mr.getOriginalFileName("s_profile");			//변경 하려는 s_profile_org
		String s_background = mr.getFilesystemName("s_background");			//변경 하려는 s_background
		String s_background_org = mr.getOriginalFileName("s_background");	//변경 하려는 s_background_org
		
		if(s_profile == null) {
			s_profile = orgProSys;
			s_profile_org = orgProOrg;
		}
		
		if(s_background == null) {
			s_background = orgBgSys;
			s_background_org = orgBgOrg;
		}
		
		String[] list = mr.getParameterValues("s_annualleave");
		String day = "";
		for (String d : list) {
			day += (d + " ");
		}
		
		StaffDTO dto = new StaffDTO();
		dto.setS_name(s_name);
		dto.setS_title(s_title);
		dto.setS_profile(s_profile);
		dto.setS_profile_org(s_profile_org);
		dto.setS_background(s_background);
		dto.setS_background_org(s_background_org);
		dto.setS_holiday(s_holiday);
		dto.setS_annualleave(day);
		dto.setS_open(s_open);
		dto.setS_close(s_close);
		
		JaeDAO dao = JaeDAO.getInstance();
		dao.updateStaff(s_num, dto);

		StaffDAO sDAO = StaffDAO.getInstance();
		
		List<String> timetable2 = null;
		timetable2 = sDAO.getTime2(Integer.parseInt(mr.getParameter("s_open")),
				Integer.parseInt(mr.getParameter("s_close")) - Integer.parseInt(mr.getParameter("s_open")));

		request.setAttribute("timetable2", timetable2);
		request.setAttribute("dto", dto);
		request.setAttribute("bs_num", bs_num);
		
		
		return "/sellerPage/seller/sellerDesignerUpdatePro.jsp";
	}

}
