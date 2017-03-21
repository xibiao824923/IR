package com.image.recognition;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.lang.StringUtils;

public class RecognitionUtil {

	/**
	 * 图片具体格式或不是图片
	 * 
	 * @author win10
	 */
	public enum IamgeFormat {

		PNG, JPG, BMP, GIF, OTHER;
	}

	/**
	 * 获取图片的格式。
	 * 
	 * @param file
	 *            图片
	 * @return
	 * @throws IOException
	 */
	public static IamgeFormat getImageFormat(File file) throws IOException {
		//获取字节数组。
		FileInputStream fileInputStream = new FileInputStream(file);
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fileInputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		outStream.close();
		fileInputStream.close();
		byte[] fileBytes = outStream.toByteArray();
		
		byte[] startBytes = new byte[8];
		byte[] endBytes = new byte[8];
		System.arraycopy(fileBytes, 0, startBytes, 0, 8);
		System.arraycopy(fileBytes, fileBytes.length - 8, endBytes, 0, 8);
		//转hexstring进行判断
		String startStr = bytes2Hex(startBytes);
		String endStr = bytes2Hex(endBytes);
		
		if (startStr.startsWith("89504E470D0A1A0A")) {
			return IamgeFormat.PNG;
		} else if (startStr.startsWith("GIF89a") || startStr.startsWith("GIF87a")) {
			return IamgeFormat.GIF;
		} else if (startStr.startsWith("BM")) {
			return IamgeFormat.BMP;
		}else if (startStr.startsWith("FFD8")&&endStr.endsWith("FFD9")) {
			return IamgeFormat.JPG;
		} else {
			return IamgeFormat.OTHER;
		}
	}

	/**
	 * byte数组转hex字符串<br/>
	 * 一个byte转为2个hex字符
	 * 
	 * @param src
	 * @return
	 */
	public static String bytes2Hex(byte[] src) {
		char[] res = new char[src.length * 2];
		final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		for (int i = 0, j = 0; i < src.length; i++) {
			res[j++] = hexDigits[src[i] >>> 4 & 0x0f];
			res[j++] = hexDigits[src[i] & 0x0f];
		}
		return new String(res);
	}

	/**
	 * 判断图片格式是否被转换过，如jpg强转为png,
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static boolean isRightFormat(File file) throws IOException {
		String[] pathsplit = StringUtils.split(file.getName(), ".");
		IamgeFormat iamgeFormat = getImageFormat(file);
		switch (iamgeFormat) {
		case PNG:
			if (pathsplit[pathsplit.length - 1].equalsIgnoreCase("PNG")) {
				return true;
			} 
			break;
		case JPG:
			if (pathsplit[pathsplit.length - 1].equalsIgnoreCase("JPEG")
					|| pathsplit[pathsplit.length - 1].equalsIgnoreCase("JPG")) {
				return true;
			}
			break;

		case BMP:
			if (pathsplit[pathsplit.length - 1].equalsIgnoreCase("BMP")) {
				return true;
			}
			break;
		case GIF:
			if (pathsplit[pathsplit.length - 1].equalsIgnoreCase("GIF")) {
				return true;
			}
			break;

		default:
			break;
		}
		System.out.println(file.getName()+"**********"+iamgeFormat);
		return false;
	}
}
