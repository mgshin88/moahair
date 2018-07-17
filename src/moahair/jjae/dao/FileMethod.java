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
	
	//���丮�� ���ϸ���Ʈ�� �о���� �޼ҵ�
	public List<File> getDirFileList(String path){
		System.out.println("���ϸ���Ʈ �о���� �޼ҵ�");
		List<File> dirFileList = null;
		File dir = new File(path);	//���丮�� ���ϰ�ü ����
		
		if(dir.exists()) {			//���丮�� �����ϸ�
			File [] files = dir.listFiles();	//���ϸ���� ����
			dirFileList = Arrays.asList(files);	//���Ϲ迭�� ���ϸ���Ʈ ���·� ����
		}
		return dirFileList;
	}
	
	//�����̵�
	public void fileMove (String inFileName, String outFileName) {
		System.out.println("�����̵� �޼ҵ� ȣ��Ǿ���");
		try {
			FileInputStream fis = new FileInputStream(inFileName);
			FileOutputStream fos = new FileOutputStream(outFileName);
			
			int data = 0;
			while((data=fis.read())!=-1) {
				fos.write(data);
			}
			fis.close();
			fos.close();
			
			//�������� ����
			File file = new File(inFileName);
			file.delete();
			System.out.println("�̵� �� �����Ǵ� �����̸�"+inFileName);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
