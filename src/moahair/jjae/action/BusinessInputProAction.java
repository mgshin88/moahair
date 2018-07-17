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
		
		
		//��� �Է��ϱ� 
		BusinessDTO dto = new BusinessDTO();
		BusinessAddressDTO adto = new BusinessAddressDTO();
		
		JaeDAO dao = JaeDAO.getInstance();
		
		
		String path = request.getSession().getServletContext().getRealPath("//jjae//se2//upload//");
		String enc = "UTF-8";
		int maxSize = 1024*1024*10;
		
		DefaultFileRenamePolicy dp = new DefaultFileRenamePolicy();  
		MultipartRequest mr = new MultipartRequest(request,path,maxSize,enc,dp);//���ε尡 �̷���� 
		
		String bs_num = mr.getParameter("bs_num");
		System.out.println("inputpro bs_num"+bs_num);
		String bs_profile_sys = mr.getFilesystemName("bs_profile"); 
		String bs_profile_org = mr.getOriginalFileName("bs_profile");
		
		String bs_background_sys = mr.getFilesystemName("bs_background"); 
		String bs_background_org = mr.getOriginalFileName("bs_background");
		
		
		//�����ʿ� ���� if ��
		
		if (bs_profile_sys != null) {

			File uploadFile = mr.getFile("bs_profile");
			String fileType = mr.getContentType("bs_profile");

			String[] ft = fileType.split("/");
			if (!ft[0].equals("image")) {
				//�� ����� �Ѱ�� �̹� ������ �ް� ���� ������ �Ѵٴ°�! 
				//�׷��� ���ε��Ѱ� ������Ű�� ������� �ؾ���
				uploadFile.delete();
				//������ �����̵Ǹ� ���� ������ ������ �Ǵ� ������ �վ���� 
			}
		}
		
		//��׶��忡 ���� if ��
		
		if (bs_background_sys != null) {

			File uploadFile = mr.getFile("bs_background");
			String fileType = mr.getContentType("bs_background");

			String[] ft = fileType.split("/");
			if (!ft[0].equals("image")) {
				//�� ����� �Ѱ�� �̹� ������ �ް� ���� ������ �Ѵٴ°�! 
				//�׷��� ���ε��Ѱ� ������Ű�� ������� �ؾ���
				uploadFile.delete();
				//������ �����̵Ǹ� ���� ������ ������ �Ǵ� ������ �վ���� 

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
