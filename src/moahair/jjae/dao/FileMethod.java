package moahair.jjae.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileMethod {
	private static FileMethod fm = new FileMethod();

	public FileMethod() {
	}

	public static FileMethod getInstance() {
		return fm;
	}
	
	//디렉토리의 파일리스트를 읽어오는 메소드
	public List<File> getDirFileList(String path){
		System.out.println("파일리스트 읽어오는 메소드");
		List<File> dirFileList = null;
		File dir = new File(path);	//디렉토리의 파일객체 생생
		
		if(dir.exists()) {			//디렉토리가 존재하면
			File [] files = dir.listFiles();	//파일목록을 구함
			dirFileList = Arrays.asList(files);	//파일배열을 파일리스트 형태로 변경
		}
		return dirFileList;
	}
	
	//파일이동
	public void fileMove (String inFileName, String outFileName) {
		System.out.println("파일이동 메소드 호출되었다");
		try {
			FileInputStream fis = new FileInputStream(inFileName);
			FileOutputStream fos = new FileOutputStream(outFileName);
			
			int data = 0;
			while((data=fis.read())!=-1) {
				fos.write(data);
			}
			fis.close();
			fos.close();
			
			//원래파일 삭제
			File file = new File(inFileName);
			file.delete();
			System.out.println("이동 후 삭제되는 파일이름"+inFileName);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
