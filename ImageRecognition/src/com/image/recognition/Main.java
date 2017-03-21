package com.image.recognition;

import java.io.File;
import java.io.IOException;
/**
 * 判断所有文件夹中的文件是否是图片（BMP,JPG,JPEG,PNG）或图片被强转过类型
 * @author win10
 *
 */
public class Main {

	// 输入文件夹的路徑
	public static String imagePath = "C:\\Users\\win10\\Desktop\\ImageRecongnition";

	public static void main(String[] args) {
		File file = new File(imagePath);
		try {
			//判断是否有文件类型强转的现象
			File []files = file.listFiles();
			for(File f:files){
				RecognitionUtil.isRightFormat(f);
			}
		} catch (IOException e) {
			System.out.println("文件读取异常");
			e.printStackTrace();
		}
	}
    
}
