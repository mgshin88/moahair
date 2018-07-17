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

//��ǰ������������� ��Ϲ�ư�� ������ �۵��Ǵ� action
public class SellerProductAddProAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("memId");

		// ��� ��ư�� ������ ����� �̹����� upload���Ϸ� ����. ���̹� ������ �̹��� ó���� �Ʒ�
		String path = request.getSession().getServletContext().getRealPath("//img//item//thumbnail//");
		int maxSize = 1024 * 1024 * 10;
		DefaultFileRenamePolicy dp = new DefaultFileRenamePolicy();
		MultipartRequest mr = new MultipartRequest(request, path, maxSize, "UTF-8", dp); // request���ΰ�ü�� �Ķ���ͷ� ���� ��������
																							// �⺻������ �� ����

		String i_name = mr.getParameter("i_name");
		String i_s_num = mr.getParameter("i_s_num");
		String i_bs_num = mr.getParameter("i_bs_num");
		String i_contents = mr.getParameter("i_contents");
		System.out.println("���� ����" + i_contents);
		i_contents = i_contents.replace("/moahair/sellerPage/se_editor/tmp/", "/moahair/img/item/contents/");
		System.out.println("������" + i_contents);

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
		String fileType = mr.getContentType("i_thumbnail"); // ������ ������ ���������� �̿��ϴ¾� //image/pjpeg

		// �̹��� ���ϸ� ���ε�ǵ���!
		String[] ft = fileType.split("/");
		if (!ft[0].equals("image")) {
			uploadFile.delete(); // ������ ������ �˱����ؼ� ��·�� �ٿ�ε尡 �Ǿ����(multipartrequest��ü ������) ���� ��������
		}

		// ItemDTO�� ����
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
		 * ���̹� ������ �̹��� tmp -> upload �̵�
		 */

		// �ӽ����� tmp
		String inFolder = request.getSession().getServletContext().getRealPath("//sellerPage//se_editor//tmp//");
		System.out.println(inFolder);
		// �������� ����Ǵ� ���� upload
		String outFolder = request.getSession().getServletContext().getRealPath("//img//item//contents//");
		System.out.println(outFolder);

		if(i_contents.contains("img")) {
			List<File> dirList = fm.getDirFileList(inFolder);
			for (int i = 0; i < dirList.size(); i++) {
				String fileName = dirList.get(i).getName();
				System.out.println(fileName);
				fm.fileMove(inFolder + "//" + fileName, outFolder + "//" + fileName);
				System.out.println("�����̵� �޼ҵ� ȣ��");
			}
		}

		// ItemOptionsDTO ����
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

		for (int i = 0; i < 3; i++) { // 0�⺻�ɼ�, 1�߰��ɼ�1, 2�߰��ɼ�2
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

			for (int h = 1; h <= 3; h++) { // 1�̸�, 2����, 3�ð�
				for (int j = 1; j <= 5; j++) { // ������ �ټ���
					if (h == 1) { // �̸�
						if (mr.getParameter("op" + i + "_" + j + "_" + h) != null
								&& !mr.getParameter("op" + i + "_" + j + "_" + h).equals("")) { // ������ ���� ������ �Ķ���� ����
																								// �Ѱܹ޴´�
							if (j == 1) { // ������ �Ϲ��϶�
								io_name += mr.getParameter("op" + i + "_" + j + "_" + h);
							} else { // �������� �Ϲ��� �ƴҶ� �޸��� ���� �ٿ� �ִ´�
								io_name += "," + mr.getParameter("op" + i + "_" + j + "_" + h);
							}
						} else { // ������ ���� ������ null���ڿ��� �־��ش�
							if (j == 1) {
								io_name += "*"; // �Ϲ��϶��� �׳� null
							} else {
								io_name += ",*"; // �״ٿ� ���� , null
							}
						}
					} else if (h == 2) { // ����
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
					} else { // �ð�
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

			System.out.println("for���ȿ��� io_i_num ��� ::::::::::::" + io_i_num);
			dao.insertItemOptions(idto);
		}

		request.setAttribute("bs_num", i_bs_num);
		request.setAttribute("m_id", id);
		return "/sellerPage/seller/sellerproductaddpro.jsp";
	}

}
