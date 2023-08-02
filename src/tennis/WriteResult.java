package tennis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class WriteResult {
	private static final String path = ".\\src\\tennis\\tennisResult.txt"; // 결과내용 담는 txt
	//파일입출력
	public void writeTennisResult(String list1, String list2, String list201, String list3, String list4, String list5,
			String list6, String list7) {
		File file = new File(path); //file 객체생성
		try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			oos.writeUTF(list1);
			oos.writeUTF(list2);
			oos.writeUTF(list201);
			oos.writeUTF(list3);
			oos.writeUTF(list4);
			oos.writeUTF(list5);
			oos.writeUTF(list6);
			oos.writeUTF(list7);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
} //class
