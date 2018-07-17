package moahair.jjae.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import moahair.data.dto.ItemDTO;
import moahair.jjae.dao.JaeDAO;
import moahair.mvc.controller.SuperAction;

public class SellerProductAddCelAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*
		 * ���̹� ������ �̹��� tmp ����Ǿ������Ƿ� ������� ����
		 */

		// tmp path
		String folder = request.getSession().getServletContext().getRealPath("//sellerPage//se_editor//tmp//");

		// ���丮 ���� ���� ����

		List<File> dirList = getDirFileList(folder);
		if (dirList != null) {
			for (int i = 0; i < dirList.size(); i++) {
				String fileName = dirList.get(i).getName();

				File file = new File(folder + "//" + fileName);
				file.delete();
				System.out.println("�����Ǵ� �����̸�" + fileName);
			}
		}

		return "/seller/sellerproductaddform.do";
	}

	// ���丮�� ���ϸ���Ʈ�� �о���� �޼ҵ�
	public List<File> getDirFileList(String path) {
		System.out.println("���ϸ���Ʈ �о���� �޼ҵ�");
		List<File> dirFileList = null;
		File dir = new File(path); // ���丮�� ���ϰ�ü ����

		if (dir.exists()) { // ���丮�� �����ϸ�
			File[] files = dir.listFiles(); // ���ϸ���� ����
			dirFileList = Arrays.asList(files); // ���Ϲ迭�� ���ϸ���Ʈ ���·� ����
		}
		return dirFileList;
	}
}
