package moahair.jjae.action;

import java.io.File;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import moahair.data.dto.ItemDTO;
import moahair.data.dto.ItemOptionsDTO;
import moahair.jjae.dao.FileMethod;
import moahair.jjae.dao.JaeDAO;
import moahair.mvc.controller.SuperAction;

//상품등록페이지에서 등록버튼을 누르면 작동되는 action
public class SellerProductAddProAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("memId");

		// 등록 버튼이 눌리면 썸네일 이미지를 upload파일로 저장. 네이버 에디터 이미지 처리는 아래
		String path = request.getSession().getServletContext().getRealPath("//img//item//thumbnail//");
		int maxSize = 1024 * 1024 * 10;
		DefaultFileRenamePolicy dp = new DefaultFileRenamePolicy();
		MultipartRequest mr = new MultipartRequest(request, path, maxSize, "UTF-8", dp); // request내부객체는 파라미터로 값을 못받을뿐
																							// 기본정보는 다 있음

		String i_name = mr.getParameter("i_name");
		String i_s_num = mr.getParameter("i_s_num");
		String i_bs_num = mr.getParameter("i_bs_num");
		String i_contents = mr.getParameter("i_contents");
		System.out.println("원본 내용" + i_contents);
		i_contents = i_contents.replace("/moahair/sellerPage/se_editor/tmp/", "/moahair/img/item/contents/");
		System.out.println("변경후" + i_contents);

		String i_option = mr.getParameter("i_option");
		String i_option_sel1 = mr.getParameter("i_option_sel1");
		String i_option_sel2 = mr.getParameter("i_option_sel2");
		String i_price = mr.getParameter("i_price");
		String i_duration = mr.getParameter("i_duration");
		String i_type = mr.getParameter("i_type");
		String i_gender = mr.getParameter("i_gender");
		String sysName = mr.getFilesystemName("i_thumbnail");
		String orgName = mr.getOriginalFileName("i_thumbnail");

		File uploadFile = mr.getFile("i_thumbnail");
		String fileType = mr.getContentType("i_thumbnail"); // 파일의 종류를 한정지을때 이용하는애 //image/pjpeg

		// 이미지 파일만 업로드되도록!
		String[] ft = fileType.split("/");
		if (!ft[0].equals("image")) {
			uploadFile.delete(); // 파일의 종류를 알기위해서 어쨌든 다운로드가 되어야함(multipartrequest객체 생성됨) 따라서 지워야함
		}

		// ItemDTO에 설정
		ItemDTO dto = new ItemDTO();
		dto.setI_name(i_name);
		dto.setI_s_num(Integer.parseInt(i_s_num));
		dto.setI_bs_num(Integer.parseInt(i_bs_num));
		dto.setI_thumbnail(sysName);
		dto.setI_thumbnail_org(orgName);
		dto.setI_contents(i_contents);

		dto.setI_option("10");
		dto.setI_option_sel1("20");
		dto.setI_option_sel2("30");
		dto.setI_price(Integer.parseInt(i_price));
		dto.setI_gender(Integer.parseInt(i_gender));
		dto.setI_type(i_type);
		dto.setI_duration(Integer.parseInt(i_duration));

		JaeDAO dao = JaeDAO.getInstance();
		dao.insertItem(dto);
		FileMethod fm = FileMethod.getInstance();

		/*
		 * 네이버 에디터 이미지 tmp -> upload 이동
		 */

		// 임시폴더 tmp
		String inFolder = request.getSession().getServletContext().getRealPath("//sellerPage//se_editor//tmp//");
		System.out.println(inFolder);
		// 최종으로 저장되는 파일 upload
		String outFolder = request.getSession().getServletContext().getRealPath("//img//item//contents//");
		System.out.println(outFolder);

		if(i_contents.contains("img")) {
			List<File> dirList = fm.getDirFileList(inFolder);
			for (int i = 0; i < dirList.size(); i++) {
				String fileName = dirList.get(i).getName();
				System.out.println(fileName);
				fm.fileMove(inFolder + "//" + fileName, outFolder + "//" + fileName);
				System.out.println("파일이동 메소드 호출");
			}
		}

		// ItemOptionsDTO 설정
		int io_i_num = dao.getInum(dto);

		String io_option = "";
		String io_name = "";
		String io_price = "";
		String io_duration = "";

		ItemOptionsDTO idto = new ItemOptionsDTO();

		Enumeration enu = mr.getParameterNames();
		while (enu.hasMoreElements()) {
			String n = (String) enu.nextElement();
			String p = mr.getParameter(n);
			System.out.println(n + "=" + p);
		}

		for (int i = 0; i < 3; i++) { // 0기본옵션, 1추가옵션1, 2추가옵션2
			if (i == 0) {
				io_option = "10";
			} else if (i == 1) {
				io_option = "20";
			} else {
				io_option = "30";
			}

			io_name = "";
			io_price = "";
			io_duration = "";

			for (int h = 1; h <= 3; h++) { // 1이름, 2가격, 3시간
				for (int j = 1; j <= 5; j++) { // 선택지 다섯개
					if (h == 1) { // 이름
						if (mr.getParameter("op" + i + "_" + j + "_" + h) != null
								&& !mr.getParameter("op" + i + "_" + j + "_" + h).equals("")) { // 폼에서 값이 있을때 파라미터 값을
																								// 넘겨받는다
							if (j == 1) { // 선택지 일번일때
								io_name += mr.getParameter("op" + i + "_" + j + "_" + h);
							} else { // 선택지가 일번이 아닐때 콤마를 같이 붙여 넣는다
								io_name += "," + mr.getParameter("op" + i + "_" + j + "_" + h);
							}
						} else { // 폼에서 값이 없을때 null문자열을 넣어준다
							if (j == 1) {
								io_name += "*"; // 일번일때는 그냥 null
							} else {
								io_name += ",*"; // 그다움 부터 , null
							}
						}
					} else if (h == 2) { // 가격
						if (mr.getParameter("op" + i + "_" + j + "_" + h) != null
								&& !mr.getParameter("op" + i + "_" + j + "_" + h).equals("")) {
							if (j == 1) {
								System.out.println("op" + i + "_" + j + "_" + h);
								io_price += "" + mr.getParameter("op" + i + "_" + j + "_" + h);
							} else {
								io_price += "," + mr.getParameter("op" + i + "_" + j + "_" + h);
							}
						} else {
							if (j == 1) {
								io_price += "*";
							} else {
								io_price += ",*";
							}
						}
					} else { // 시간
						if (mr.getParameter("op" + i + "_" + j + "_" + h) != null
								&& !mr.getParameter("op" + i + "_" + j + "_" + h).equals("")) {
							if (j == 1) {
								io_duration += mr.getParameter("op" + i + "_" + j + "_" + h);
							} else {
								io_duration += "," + mr.getParameter("op" + i + "_" + j + "_" + h);
							}
						} else {
							if (j == 1) {
								io_duration += "*";
							} else {
								io_duration += ",*";
							}
						}
					}
				}
			}
			idto.setIo_name(io_name);
			idto.setIo_price(io_price);
			idto.setIo_duration(io_duration);
			idto.setIo_option(io_option);
			idto.setIo_i_num(io_i_num);

			System.out.println("for문안에서 io_i_num 출력 ::::::::::::" + io_i_num);
			dao.insertItemOptions(idto);
		}

		request.setAttribute("bs_num", i_bs_num);
		request.setAttribute("m_id", id);
		return "/sellerPage/seller/sellerproductaddpro.jsp";
	}

}
