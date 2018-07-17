package moahair.jjae.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import moahair.data.dto.BusinessAddressDTO;
import moahair.data.dto.BusinessDTO;
import moahair.jjae.dao.*;
import moahair.mvc.controller.SuperAction;

public class BusinessInputProAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		
		//디비에 입력하기 
		BusinessDTO dto = new BusinessDTO();
		BusinessAddressDTO adto = new BusinessAddressDTO();
		
		JaeDAO dao = JaeDAO.getInstance();
		
		
		String path = request.getSession().getServletContext().getRealPath("//jjae//se2//upload//");
		String enc = "UTF-8";
		int maxSize = 1024*1024*10;
		
		DefaultFileRenamePolicy dp = new DefaultFileRenamePolicy();  
		MultipartRequest mr = new MultipartRequest(request,path,maxSize,enc,dp);//업로드가 이루어짐 
		
		String bs_num = mr.getParameter("bs_num");
		System.out.println("inputpro bs_num"+bs_num);
		String bs_profile_sys = mr.getFilesystemName("bs_profile"); 
		String bs_profile_org = mr.getOriginalFileName("bs_profile");
		
		String bs_background_sys = mr.getFilesystemName("bs_background"); 
		String bs_background_org = mr.getOriginalFileName("bs_background");
		
		
		//프로필에 관한 if 문
		
		if (bs_profile_sys != null) {

			File uploadFile = mr.getFile("bs_profile");
			String fileType = mr.getContentType("bs_profile");

			String[] ft = fileType.split("/");
			if (!ft[0].equals("image")) {
				//이 방법의 한계는 이미 파일을 받고 나서 검증을 한다는것! 
				//그래서 업로드한걸 삭제시키는 방법으로 해야함
				uploadFile.delete();
				//파일이 변경이되면 이전 파일은 삭제가 되는 파일이 잇어야함 
			}
		}
		
		//백그라운드에 관한 if 문
		
		if (bs_background_sys != null) {

			File uploadFile = mr.getFile("bs_background");
			String fileType = mr.getContentType("bs_background");

			String[] ft = fileType.split("/");
			if (!ft[0].equals("image")) {
				//이 방법의 한계는 이미 파일을 받고 나서 검증을 한다는것! 
				//그래서 업로드한걸 삭제시키는 방법으로 해야함
				uploadFile.delete();
				//파일이 변경이되면 이전 파일은 삭제가 되는 파일이 잇어야함 

			}
		}
		dto.setBs_profile(bs_profile_sys);
		dto.setBs_background(bs_background_sys);
		dto.setBs_background_org(bs_background_org);
		dto.setBs_profile_org(bs_profile_org);

		dto.setBs_name(mr.getParameter("bs_name"));
		dto.setBs_number(mr.getParameter("bs_number"));
		dto.setBs_open(Integer.parseInt(mr.getParameter("bs_open")));
		dto.setBs_close(Integer.parseInt(mr.getParameter("bs_close")));
		
		dao.insertBusinessInfo(Integer.parseInt(bs_num), dto );
		
		
		adto.setBa_state(mr.getParameter("ba_state"));
		adto.setBa_city(mr.getParameter("ba_city"));
		adto.setBa_surburb(mr.getParameter("ba_surburb"));
		adto.setBa_street(mr.getParameter("ba_street"));
		adto.setBa_rest(mr.getParameter("ba_rest"));
		
		dao.insertBusinessAddress(Integer.parseInt(bs_num), adto);

		return "/sellerPage/seller/businessinputpro.jsp";
	}

	
	
	
}
