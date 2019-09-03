package com.train.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class StationDictionary {
	
	private static File stationsTxt = new File("D:\\Math Files\\Eclipse\\Workspace\\TrainGUI3\\src\\station_dictionary.txt");
	private static FileInputStream fis1 = null;
	private static InputStreamReader isr1 = null;
	private static FileInputStream fis2 = null;
	private static InputStreamReader isr2 = null;
	
	// 站名转换为3位站码
	public static String stationNameToCode(String stationName) {
		String stationCode = "null";
		if (stationName.length() <= 5) {
			try {
				fis1 = new FileInputStream(stationsTxt);
				isr1 = new InputStreamReader(fis1);
				char[] ch = new char[(int) stationsTxt.length()];
				int len = isr1.read(ch);
				String str = new String(ch, 0, len);
				String[] allStationsStr = str.split("@");
				int index = 0;
				for (index = 1; index < allStationsStr.length; index++) {
					String[] curSta = allStationsStr[index].split("\\|");
					if (curSta[1].equals(stationName)) {
						stationCode = curSta[2];
						break;
					}
				}
				isr1.close();
				fis1.close();
			} catch (Exception e) {
				e.printStackTrace(System.out);
				return "null";
			}
		}
		return stationCode;
	}
	
	// 3位站码转换为站名
	public static String stationCodeToName(String stationCode) {
		String stationName = "null";
		if (stationCode.length() == 3) {
			try {
				fis2 = new FileInputStream(stationsTxt);
				isr2 = new InputStreamReader(fis2);
				char[] ch = new char[(int) stationsTxt.length()];
				int len = isr2.read(ch);
				String str = new String(ch, 0, len);
				String[] allStationsStr = str.split("@");
				int index = 0;
				for (index = 0; index < allStationsStr.length; index++) {
					String[] curSta = allStationsStr[index].split("\\|");
					if (curSta[2].equals(stationCode)) {
						stationName = curSta[1];
						break;
					}
				}
				isr2.close();
				fis2.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return "null";
			}
		}
		return stationName;
	}
	
}
