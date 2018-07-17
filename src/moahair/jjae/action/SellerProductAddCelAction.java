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
		 * 네이버 에디터 이미지 tmp 저장되어있으므로 모든파일 삭제
		 */

		// tmp path
		String folder = request.getSession().getServletContext().getRealPath("//sellerPage//se_editor//tmp//");

		// 디렉토리 내부 파일 삭제

		List<File> dirList = getDirFileList(folder);
		if (dirList != null) {
			for (int i = 0; i < dirList.size(); i++) {
				String fileName = dirList.get(i).getName();

				File file = new File(folder + "//" + fileName);
				file.delete();
				System.out.println("삭제되는 파일이름" + fileName);
			}
		}

		return "/seller/sellerproductaddform.do";
	}

	// 디렉토리의 파일리스트를 읽어오는 메소드
	public List<File> getDirFileList(String path) {
		System.out.println("파일리스트 읽어오는 메소드");
		List<File> dirFileList = null;
		File dir = new File(path); // 디렉토리의 파일객체 생생

		if (dir.exists()) { // 디렉토리가 존재하면
			File[] files = dir.listFiles(); // 파일목록을 구함
			dirFileList = Arrays.asList(files); // 파일배열을 파일리스트 형태로 변경
		}
		return dirFileList;
	}
}
