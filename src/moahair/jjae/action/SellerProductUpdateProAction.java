package moahair.jjae.action;

import java.io.File;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import moahair.data.dto.ItemDTO;
import moahair.data.dto.ItemOptionsDTO;
import moahair.jjae.dao.FileMethod;
import moahair.jjae.dao.JaeDAO;
import moahair.mvc.controller.SuperAction;

public class SellerProductUpdateProAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// ��� ��ư�� ������ ����� �̹�����/img/item/thumbnail���Ͽ� ����. ���̹� ������ �̹��� ó���� �Ʒ�
		String path = request.getSession().getServletContext().getRealPath("//img//item//thumbnail//");
		int maxSize = 1024 * 1024 * 10;
		DefaultFileRenamePolicy dp = new DefaultFileRenamePolicy();
		MultipartRequest mr = new MultipartRequest(request, path, maxSize, "UTF-8", dp); // request���ΰ�ü�� �Ķ���ͷ� ���� ��������
																							// �⺻������ �� ����
		int i_num = Integer.parseInt(mr.getParameter("i_num"));
		String i_name = mr.getParameter("i_name");
		String i_s_num = mr.getParameter("i_s_num");
		String i_bs_num = mr.getParameter("i_bs_num");
		String i_contents = mr.getParameter("i_contents");
		System.out.println("���� ����" + i_contents);
		i_contents = i_contents.replace("/moahair/sellerPage/se_editor/tmp/", "/moahair/img/item/contents/");
		System.out.println("������" + i_contents);
		
		String i_price = mr.getParameter("i_price");
		String i_duration = mr.getParameter("i_duration");
		String i_type = mr.getParameter("i_type");
		String i_gender = mr.getParameter("i_gender");
		String sysName = mr.getFilesystemName("i_thumbnail");
		String orgName = mr.getOriginalFileName("i_thumbnail");

		String orgImage = mr.getParameter("orgImage");
		String sysOrgImage = mr.getParameter("orgImage");

		if (orgName == null) {
			orgName = orgImage;
			sysName = sysOrgImage;
		}

		File uploadFile = mr.getFile("i_thumbnail");
		String fileType = mr.getContentType("i_thumbnail"); // ������ ������ ���������� �̿��ϴ¾� //image/pjpeg

		// �̹��� ���ϸ� ���ε�ǵ���!
		if (fileType != null) {
			String[] ft = fileType.split("/");
			if (!ft[0].equals("image")) {
				uploadFile.delete(); // ������ ������ �˱����ؼ� ��·�� �ٿ�ε尡 �Ǿ����(multipartrequest��ü ������) ���� ��������
			}
		}

		ItemDTO dto = new ItemDTO();
		dto.setI_name(i_name);
		dto.setI_s_num(Integer.parseInt(i_s_num));
		dto.setI_bs_num(Integer.parseInt(i_bs_num));
		dto.setI_thumbnail(sysName);
		dto.setI_thumbnail_org(orgName);
		dto.setI_contents(i_contents);

		dto.setI_price(Integer.parseInt(i_price));
		dto.setI_gender(Integer.parseInt(i_gender));
		dto.setI_type(i_type);
		dto.setI_duration(Integer.parseInt(i_duration));

		JaeDAO dao = JaeDAO.getInstance();
		dao.updateItem(dto, i_num);
		FileMethod fm = FileMethod.getInstance();

		/*
		 * ���̹� ������ �̹��� tmp -> item �̵�
		 */
		// �ӽ����� tmp
		String inFolder = request.getSession().getServletContext().getRealPath("//sellerPage//se_editor//tmp//");

		// �������� ����Ǵ� ���� contents
		String outFolder = request.getSession().getServletContext().getRealPath("//img//item//contents//");

		List<File> dirList = fm.getDirFileList(inFolder);

		for (int i = 0; i < dirList.size(); i++) {
			String fileName = dirList.get(i).getName();
			fm.fileMove(inFolder + "//" + fileName, outFolder + "//" + fileName);
		}

		// ������ �ִ� �Ķ���� ���
		Enumeration enu = mr.getParameterNames();
		while (enu.hasMoreElements()) {
			String n = (String) enu.nextElement();
			String p = mr.getParameter(n);
			System.out.println(n + "=" + p);
		}

		//////////////////////////////// ItemOptionDTO ����///////////////////////////////////////////
		String op0_io_num = mr.getParameter("op0_io_num");
		String op1_io_num = mr.getParameter("op1_io_num");
		String op2_io_num = mr.getParameter("op2_io_num");

		String io_name = "";
		String io_price = "";
		String io_duration = "";

		ItemOptionsDTO idto = new ItemOptionsDTO();
		int io_num = 0;
		for (int i = 0; i < 3; i++) { // 0�⺻�ɼ�, 1�߰��ɼ�1, 2�߰��ɼ�2
			if (i == 0) {
				io_num = Integer.parseInt(op0_io_num);
			} else if (i == 1) {
				io_num = Integer.parseInt(op1_io_num);
			} else {
				io_num = Integer.parseInt(op2_io_num);
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
						
			dao.updateItemOptions(idto, io_num);
		}
		
		request.setAttribute("bs_num", i_bs_num);

		return "/sellerPage/seller/sellerproductupdatepro.jsp";
	}

}
