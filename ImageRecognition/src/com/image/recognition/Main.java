package com.image.recognition;

import java.io.File;
import java.io.IOException;
/**
 * �ж������ļ����е��ļ��Ƿ���ͼƬ��BMP,JPG,JPEG,PNG����ͼƬ��ǿת������
 * @author win10
 *
 */
public class Main {

	// �����ļ��е�·��
	public static String imagePath = "C:\\Users\\win10\\Desktop\\ImageRecongnition";

	public static void main(String[] args) {
		File file = new File(imagePath);
		try {
			//�ж��Ƿ����ļ�����ǿת������
			File []files = file.listFiles();
			for(File f:files){
				RecognitionUtil.isRightFormat(f);
			}
		} catch (IOException e) {
			System.out.println("�ļ���ȡ�쳣");
			e.printStackTrace();
		}
	}
    
}
