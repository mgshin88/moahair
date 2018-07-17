package moahair.jjae.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import moahair.data.dto.StaffDTO;
import moahair.mvc.controller.SuperAction;
import moahair.yeon.dao.StaffDAO;

public class SellerDesignerInputProAction implements SuperAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("memId");

		StaffDTO dto = new StaffDTO();
		StaffDAO dao = StaffDAO.getInstance();

		String path = request.getSession().getServletContext().getRealPath("//img//seller//staff//");
		String enc = "UTF-8";
		int maxSize = 1024 * 1024 * 10;

		DefaultFileRenamePolicy dp = new DefaultFileRenamePolicy();
		MultipartRequest mr = new MultipartRequest(request, path, maxSize, enc, dp);

		String s_profile_sys = mr.getFilesystemName("s_profile");
		String s_profile_org = mr.getOriginalFileName("s_profile");

		String s_background_sys = mr.getFilesystemName("s_background");
		String s_background_org = mr.getOriginalFileName("s_background");

		String bs_num = mr.getParameter("bs_num");

		// ï¿½ë´½æ¿¡ì’—ë¸˜ï¿½ë¿??¿ï¿½ï¿½ë¸³ if ?¾ï¿½

		if (s_profile_sys != null) {

			File uploadFile = mr.getFile("s_profile");
			String fileType = mr.getContentType("s_profile");

			String[] ft = fileType.split("/");
			if (!ft[0].equals("image")) {

				uploadFile.delete();
			}
		}
		if (s_background_sys != null) {

			File uploadFile = mr.getFile("s_background");
			String fileType = mr.getContentType("s_background");

			String[] ft = fileType.split("/");
			if (!ft[0].equals("image")) {

				uploadFile.delete();
			}
		}


		dto.setS_profile(s_profile_sys);
		dto.setS_background(s_background_sys);
		dto.setS_background_org(s_background_org);
		dto.setS_profile_org(s_profile_org);

		dto.setS_bs_num(Integer.parseInt(bs_num));
		dto.setS_name(mr.getParameter("s_name"));
		dto.setS_title(mr.getParameter("s_title"));
		dto.setS_holiday(mr.getParameter("s_holiday"));
		
		dto.setS_open(Integer.parseInt(mr.getParameter("s_open")));
		dto.setS_close(Integer.parseInt(mr.getParameter("s_close")));

		dao.insertDesigner(dto);

		List<String> timetable2 = null;
		timetable2 = dao.getTime2(Integer.parseInt(mr.getParameter("s_open")),
				Integer.parseInt(mr.getParameter("s_close")) - Integer.parseInt(mr.getParameter("s_open")));

		request.setAttribute("dto", dto);
		return "/sellerPage/seller/sellerDesignerInputPro.jsp";
	}
}
