package moahair.yeon.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import moahair.yeon.dao.StaffDAO;
import moahair.data.dto.StaffDTO;
import moahair.mvc.controller.SuperAction;


public class ScheduleProAction  implements SuperAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
	      HttpSession session = request.getSession();
	      
	      
	      StaffDTO dto = new StaffDTO();
	      StaffDAO dao = StaffDAO.getInstance();
	      
	      
	      String id = (String)session.getAttribute("memId");
	      
	      
	      String path = request.getRealPath("saveImg");
	      String enc = "UTF-8";
	      int maxSize = 1024*1024*10;
	      
	      DefaultFileRenamePolicy dp = new DefaultFileRenamePolicy();  
	      MultipartRequest mr = new MultipartRequest(request,path,maxSize,enc,dp);
	      
	      String s_profile_sys = mr.getFilesystemName("s_profile"); 
	      String s_profile_org = mr.getOriginalFileName("s_profile");
	      
	      String s_background_sys = mr.getFilesystemName("s_background"); 
	      String s_background_org = mr.getOriginalFileName("s_background");
	      
	      
	      
	      
	      //�봽濡쒗븘�뿉 愿��븳 if 臾�
	      
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

String [] list = mr.getParameterValues("s_annualleave");
String day="";
for(String d : list){
	day+=(d+" ");
}

	      
	      
	      dto.setS_profile(s_profile_sys);
	      dto.setS_background(s_background_sys);
	//      dto.setS_background_org(s_background_org);
	//      dto.setS_profile_org(s_profile_org);

	      dto.setS_name(mr.getParameter("s_name"));
	      dto.setS_title(mr.getParameter("s_title"));
	      dto.setS_holiday(mr.getParameter("s_holiday"));
	      dto.setS_annualleave(day);
	      dto.setS_open(Integer.parseInt(mr.getParameter("s_open")));
	      dto.setS_close(Integer.parseInt(mr.getParameter("s_close")));
	      
	      dao.insertSchedule(dto);
	      
	      List<String> timetable2 = null;
	      timetable2 = dao.getTime2(Integer.parseInt(mr.getParameter("s_open")), 
	    		  Integer.parseInt(mr.getParameter("s_close"))-Integer.parseInt(mr.getParameter("s_open")));
		  
		  request.setAttribute("timetable2", timetable2);
	      request.setAttribute("dto", dto);  
	      
	      return "/bookingPage/schedulePro.jsp";
	   }
	   
	}

