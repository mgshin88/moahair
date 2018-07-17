package moahair.jung.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import moahair.data.dto.BusinessAddressDTO;
import moahair.data.dto.BusinessDTO;
import moahair.data.dto.BusinessPlusAddressDTO;
import moahair.jjae.dao.JaeDAO;
import moahair.jung.dao.MoaHairDAO;
import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class ShopUpdateProAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		String m_id = (String)session.getAttribute("memId");
		KyuDAO k_dao = KyuDAO.getInstance();
		
		int m_level = k_dao.memberLevel(m_id);
		
		if(m_level == 10) {
			String path = request.getSession().getServletContext().getRealPath("//img//seller//business//");
			int maxSize = 1024 * 1024 * 10;
			DefaultFileRenamePolicy dp = new DefaultFileRenamePolicy();
			MultipartRequest mr = new MultipartRequest(request, path, maxSize, "UTF-8", dp); // request내부객체는 파라미터로 값을 못받을뿐
			
			// 기본정보는 다 있음
			int ba_num = Integer.parseInt(mr.getParameter("ba_num"));
			int bs_num = Integer.parseInt(mr.getParameter("bs_num"));
			String bs_name = mr.getParameter("bs_name");
			String bs_number = mr.getParameter("bs_number");
			String bs_open = mr.getParameter("bs_open");
			String bs_close = mr.getParameter("bs_close");
			String ba_state = mr.getParameter("ba_state");
			String ba_city = mr.getParameter("ba_city");
			String ba_surburb = mr.getParameter("ba_surburb");
			String ba_street = mr.getParameter("ba_street");
			String ba_rest = mr.getParameter("ba_rest");
			int bs_condition = Integer.parseInt(mr.getParameter("shop_condition"));
			
			String bs_profile_sys = mr.getFilesystemName("bs_profile");
			String bs_profile_org = mr.getOriginalFileName("bs_profile");
			String bs_background_sys = mr.getFilesystemName("bs_background");
			String bs_background_org = mr.getOriginalFileName("bs_background");
			
			String orgProfile = mr.getParameter("orgProfile");				//변경 전 프로필이미지 이름
			String sysOrgProfile = mr.getParameter("orgProfileSys");			//변경 전 프로필이미지 이름 sysOrgProfile을 받아야함 변경!!!
			String orgBackground = mr.getParameter("orgBackground");		//변경 전 배경이미지 이름
			String sysOrgBackground = mr.getParameter("orgBackgroundSys");		//변경 전 배경이미지 이름 sysOrgBackground를 받아야함 변경!!!!!
			
			if(bs_profile_org == null) {
				bs_profile_sys = orgProfile;
				bs_profile_org = sysOrgProfile;
			}
			
			if(bs_background_org == null) {
				bs_background_sys = orgBackground;
				bs_background_org = sysOrgBackground;
			}
			
			
			//프로필사진이 이미지 파일인지 아닌지 검사
			File uploadFile = mr.getFile("bs_profile");
			String fileType = mr.getContentType("bs_profile"); // 파일의 종류를 한정지을때 이용하는애 //image/pjpeg
			if (fileType != null) {
				String[] ft = fileType.split("/");
				if (!ft[0].equals("image")) {
					uploadFile.delete(); // 파일의 종류를 알기위해서 어쨌든 다운로드가 되어야함(multipartrequest객체 생성됨) 따라서 지워야함
				}
			}
			
			//배경이미지가 이미지 파일인지 아닌지 검사
			uploadFile = mr.getFile("bs_background");
			fileType = mr.getContentType("bs_background");
			if (fileType != null) {
				String[] ft = fileType.split("/");
				if (!ft[0].equals("image")) {
					uploadFile.delete(); // 파일의 종류를 알기위해서 어쨌든 다운로드가 되어야함(multipartrequest객체 생성됨) 따라서 지워야함
				}
			}
			
			BusinessDTO dto = new BusinessDTO();
			BusinessAddressDTO adto = new BusinessAddressDTO();
			
			/////요기서 입력받은 수정된 정보를 저장하기위해 dto와 adto를 set해야하함!!!!!!!
			dto.setBs_name(bs_name);
			dto.setBs_number(bs_number);
			dto.setBs_profile(bs_profile_sys);
			dto.setBs_profile_org(bs_profile_org);
			dto.setBs_background(bs_background_sys);
			dto.setBs_background_org(bs_background_org);
			dto.setBs_open(Integer.parseInt(bs_open));
			dto.setBs_close(Integer.parseInt(bs_close));
			dto.setBs_condition(bs_condition);
			adto.setBa_state(ba_state);
			adto.setBa_city(ba_city);
			adto.setBa_surburb(ba_surburb);
			adto.setBa_street(ba_street);
			adto.setBa_rest(ba_rest);
			
			JaeDAO dao = JaeDAO.getInstance();
			MoaHairDAO daoh = MoaHairDAO.getInstance();
			daoh.updateBusinessInfo(dto, bs_num);
			dao.updateBusinessAddress(adto, ba_num);
		}
		
		request.setAttribute("m_level", m_level);
		
		
		
		return "/supervisor/shopUpdatePro.jsp";
	}

}
