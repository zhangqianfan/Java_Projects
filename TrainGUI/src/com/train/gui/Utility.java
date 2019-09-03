package com.train.gui;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.table.DefaultTableModel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * 实用工具类
 * @author MrZhang
 */
public class Utility {
	
	/**
	 * 将Calendar对象表示的日期转为字符串
	 * @param ca Calendar对象表示的日期
	 * @return 日期的字符串形式
	 */
	public static String dateToString(Calendar ca) {
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH) + 1;
		int day = ca.get(Calendar.DATE);
		String yearStr = year + "";
		String monthStr = (month < 10) ? ("0" + month) : ("" + month);
		String dayStr = (day < 10) ? ("0" + day) : ("" + day);
		String dateString = yearStr + "-" + monthStr + "-" + dayStr;
		return dateString;
	}
	
	/**
	 * 将网页内容以enc指定的编码格式保存为字符串
	 * @param url 网页URL
	 * @param enc 编码格式
	 * @return 保存后的字符串
	 */
	public static String readUrlAsString(URL url, String enc) {
		String result = "";
		InputStream in = null;
		InputStreamReader isr = null;
		BufferedReader reader = null;
		try {
			in = url.openStream();
			isr = new InputStreamReader(in, enc);
			reader = new BufferedReader(isr);
			String line;
			while ((line = reader.readLine()) != null) {
				result += line;
			}
		} catch (Exception e1) {
			result = "error";
			//e1.printStackTrace(System.out);
		} finally {
			try {
				if (reader != null) reader.close();
				if (isr != null) isr.close();
				if (in != null) in.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 将JSON格式字符串转换为二维字符串数组并提取所需信息
	 * @param jsonString 要转换的JSON格式字符串
	 * @return 提取后的信息(以二维字符串数组形式保存)
	 */
	public static String[][] jsonToTrainArray(String jsonString) {
		String[][] result = null;
		JSONParser parser = new JSONParser();
		try {
			Object parsedCont = parser.parse(jsonString);
			JSONObject map1 = (JSONObject) parsedCont;
			JSONObject map2 = (JSONObject) map1.get("data");
			JSONArray array = (JSONArray) map2.get("result");
			if (array.size() > 0) {
				int maxValidLen = 0; // 最大有效数据的组数
				for (int i = 0; i < array.size(); i++) {
					String[] trainInfo = ((String) array.get(i)).split("\\|");
					if (trainInfo[10].equals("99:59")) {
						continue;
					}
					maxValidLen++;
				}
				result = new String[maxValidLen][18];
				int ind = 0;
				for (int i = 0; i < array.size(); i++) {
					String[] trainInfo = ((String) array.get(i)).split("\\|");
					// 将查询结果中历时为99:59的结果过滤掉
					if (trainInfo[10].equals("99:59")) {
						continue;
					}
					result[ind][0] = "<html><b>" + trainInfo[3] + "</b></html>"; // 车次
					result[ind][1] = StationDictionary.stationCodeToName(trainInfo[6]); // 出发站
					result[ind][2] = trainInfo[8]; // 出发时间
					result[ind][3] = StationDictionary.stationCodeToName(trainInfo[7]); // 到达站
					result[ind][4] = trainInfo[9]; // 到达时间
					result[ind][5] = trainInfo[10]; // 历时
					result[ind][6] = StationDictionary.stationCodeToName(trainInfo[4])
							+ "→" + StationDictionary.stationCodeToName(trainInfo[5]); // 始发站-终到站
					result[ind][7] = displayLeftTicket(trainInfo[32]); // 商务座
					result[ind][8] = displayLeftTicket(trainInfo[25]); // 特等座
					result[ind][9] = displayLeftTicket(trainInfo[31]); // 一等座
					result[ind][10] = displayLeftTicket(trainInfo[30]); // 二等座
					result[ind][11] = displayLeftTicket(trainInfo[21]); // 高级软卧
					result[ind][12] = displayLeftTicket(trainInfo[23]); // 软卧
					result[ind][13] = displayLeftTicket(trainInfo[28]); // 硬卧
					result[ind][14] = displayLeftTicket(trainInfo[24]); // 软座
					result[ind][15] = displayLeftTicket(trainInfo[29]); // 硬座
					result[ind][16] = displayLeftTicket(trainInfo[26]); // 无座
					if (trainInfo[11].equals("N")) {
						result[ind][17] = "不可预订";
					}
					else {
						result[ind][17] = "<html>" + trainInfo[1] + "</html>"; // 选项
					}
					ind++;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace(System.out);
		}
		return result;
	}
	
	/**
	 * 利用站站查询请求URL和车次号查询当前车次的经停站信息
	 * @param stsQueryURL 站站查询请求URL
	 * @param trainNo 车次号
	 * @return 返回当前车次的经停站信息
	 */
	public static String[][] jsonToTrainDetail(String stsQueryURL, String trainNo) {
		String[][] result = null;
		JSONParser parser = new JSONParser();
		Object parsedCont1, parsedCont2;
		try {
			URL reqUrl1 = new URL(stsQueryURL);
			String jsonData1 = readUrlAsString(reqUrl1, "UTF-8");
			parsedCont1 = parser.parse(jsonData1);
			JSONObject map1 = (JSONObject) parsedCont1;
			JSONObject map2 = (JSONObject) map1.get("data");
			JSONArray array1 = (JSONArray) map2.get("result");
			if (array1.size() > 0) {
				int i;
				String[] trainInfo = null;
				for (i = 0; i < array1.size(); i++) {
					trainInfo = ((String) array1.get(i)).split("\\|");
					if (trainInfo[3].equals(trainNo)) break;
				}
				String tnQueryURL = "https://kyfw.12306.cn/otn/czxx/queryByTrainNo?train_no=" + trainInfo[2] 
						+ "&from_station_telecode=" + trainInfo[6]
						+ "&to_station_telecode=" + trainInfo[7]
						+ "&depart_date=" + dateFormatConvert(trainInfo[13]);
				URL reqUrl2 = new URL(tnQueryURL);
				String jsonData2 = Utility.readUrlAsString(reqUrl2, "UTF-8");
				parsedCont2 = parser.parse(jsonData2);
				JSONObject map3 = (JSONObject) parsedCont2;
				JSONObject map4 = (JSONObject) map3.get("data");
				JSONArray array2 = (JSONArray) map4.get("data");
				if (array2.size() > 0) {
					result = new String[array2.size()][6];
					for (int j = 0; j < array2.size(); j++) {
						JSONObject map5 = (JSONObject) array2.get(j);
						result[j][0] = String.valueOf(j + 1); // 站序
						result[j][1] = (String) map5.get("station_name"); // 站名
						result[j][2] = (String) map5.get("arrive_time"); // 到站时间
						result[j][3] = (String) map5.get("start_time"); // 出发时间
						result[j][4] = (String) map5.get("stopover_time"); // 停留时长
						result[j][5] = (j == 0) ? "始发站无正晚点信息" : "点击查询";
					}
					result[0][2] = "----"; // 始发站没有到站时间
					result[array2.size() - 1][3] = "----"; // 终点站没有出发时间
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
		
	}
	
	/**
	 * 根据车次号和站名查询列车正晚点信息
	 * @param trainCode 车次号
	 * @param stationName 站名
	 * @param punctualTime 正点到站时间
	 * @return 返回正晚点信息
	 */
	public static String getDelayInfo(String trainCode, String stationName, String punctualTime) {
		String delayInfo = "Null";
		String todayDate = dateToString(Calendar.getInstance());
		try {
			String stationEncode = URLEncoder.encode(stationName, "UTF-8").replace('%', '-');
			String timeStamp = String.valueOf(System.currentTimeMillis());
			String delayQueryURL = "http://dynamic.12306.cn/mapping/kfxt/zwdcx/LCZWD/cx.jsp?cz=" + stationName 
					+ "&cc=" + trainCode + "&cxlx=0&rq=" + todayDate 
					+ "&czEn=" + stationEncode + "&tp=" + timeStamp;
			String sourceDelayInfo = readUrlAsString(new URL(delayQueryURL), "GB2312");
			if (sourceDelayInfo.length() > 1000) {
				delayInfo = "暂无（点击刷新）";
			}
			else if (sourceDelayInfo.contains("尚未开通")) {
				delayInfo = "该站未开通正晚点查询服务";
			}
			else if (sourceDelayInfo.contains("暂无")) {
				delayInfo = "暂无（点击刷新）";
			}
			else if (sourceDelayInfo.contains("预计")) {
				String arrivalTime = sourceDelayInfo.substring(sourceDelayInfo.length() - 5, sourceDelayInfo.length());
				delayInfo = "预计" + arrivalTime + "到";
			}
			else if (sourceDelayInfo.contains("的时间")) {
				String arrivalTime = sourceDelayInfo.substring(sourceDelayInfo.length() - 5, sourceDelayInfo.length());
				delayInfo = (arrivalTime.equals(punctualTime)) ? (arrivalTime + "到，正点") : (arrivalTime + "到");
			}
			else if (sourceDelayInfo.contains("的信息")) {
				delayInfo = "无当日该车次信息";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return delayInfo;
	}
	
	// 将yyyy-MM-dd格式的日期解析为Calendar对象
	public static Calendar parseToCalendar(String dateStr) {
		Calendar ca = null;
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		@SuppressWarnings("unused")
		Date d = null;
		try {
			d = df.parse(dateStr);
			String[] darr = dateStr.split("-");
			int arg1 = Integer.parseInt(darr[0]);
			int arg2 = Integer.parseInt(darr[1]) - 1;
			int arg3 = Integer.parseInt(darr[2]);
			ca = Calendar.getInstance();
			ca.set(arg1, arg2, arg3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ca;
	}
	
	// 按指定格式显示余票信息
	public static String displayLeftTicket(String leftTicket) {
		String dispResult = "";
		if (leftTicket.equals("")) dispResult = "—";
		else if (leftTicket.equals("*")) dispResult = "未起售";
		else if (leftTicket.equals("有")) dispResult = ">20张";
		else if (leftTicket.equals("无")) dispResult = "无";
		else dispResult = leftTicket + "张";
		return dispResult;
	}
	
	// 根据车次号判断列车类型
	public static String trainType(String trainNo) {
		String type = "其它";
		switch (trainNo.charAt(0)) {
		case 'K':
			type = "快速";
			break;
		case 'T':
			type = "特快";
			break;
		case 'Z':
			type = "直达";
			break;
		case 'G':
			type = "高铁";
			break;
		case 'D':
			type = "动车";
			break;
		case 'C':
			type = "城际";
			break;
		case 'Y':
			type = "旅游";
			break;
		default:
			if (trainNo.charAt(0) == 'S' || trainNo.charAt(0) == '1' 
			|| trainNo.charAt(0) == '2' || trainNo.charAt(0) == '3' 
			|| trainNo.charAt(0) == '4' || trainNo.charAt(0) == '5' 
			|| trainNo.charAt(0) == '6' || trainNo.charAt(0) == '7' 
			|| trainNo.charAt(0) == '8' || trainNo.charAt(0) == '9') {
				type = "普快";
			}
			break;
		}
		return type;
	}
	
	// 将yyyyMMdd格式日期转为yyyy-MM-dd格式
	public static String dateFormatConvert(String dateStr) {
		String convertedDate = "null";
		if (dateStr.length() == 8) {
			String year = dateStr.substring(0, 4);
			String month = dateStr.substring(4, 6);
			String day = dateStr.substring(6, 8);
			convertedDate = year + "-" + month + "-" + day;
		}
		return convertedDate;
	}
	
	// 去掉HTML标签<html><b>和</b></html>
	public static String deleteSpecificHtmlTags(String str) {
		String str1 = str;
		if (str1.contains("<html><b>") && str1.contains("</b></html>")) {
			String str2 = str1.substring(9);
			String str3 = str2.substring(0, str2.length() - 11);
			return str3;
		}
		return str1;
	}
	
	// 判断鼠标点击位置是否在图形验证码的第N（1<=N<=8）张图中
	public static boolean isCooldinateInCaptcha(int x, int y, int n) {
		if (n >= 1 && n <= 8) {
			if (x >= 2 && x <= 289 && y >= 38 && y <= 181) {
				if (x >= 2 && x <= 73 && y >= 38 && y <= 109 && n == 1) {
					return true;
				}
				else if (x >= 74 && x <= 145 && y >= 38 && y <= 109 && n == 2) {
					return true;
				}
				else if (x >= 146 && x <= 217 && y >= 38 && y <= 109 && n == 3) {
					return true;
				}
				else if (x >= 218 && x <= 289 && y >= 38 && y <= 109 && n == 4) {
					return true;
				}
				else if (x >= 2 && x <= 73 && y >= 110 && y <= 181 && n == 5) {
					return true;
				}
				else if (x >= 74 && x <= 145 && y >= 110 && y <= 181 && n == 6) {
					return true;
				}
				else if (x >= 146 && x <= 217 && y >= 110 && y <= 181 && n == 7) {
					return true;
				}
				else if (x >= 218 && x <= 289 && y >= 110 && y <= 181 && n == 8) {
					return true;
				}
				else return false;
			}
			else {
				throw new IllegalArgumentException("Argument x or y is illegal! x should be an integer between 2 and 289, and y should be an integer between 38 and 181!");
			}
		}
		else {
			throw new IllegalArgumentException("Argument n is illegal! It should be an integer between 1 and 8!");
		}
	}
	
	// 获取TableModel中的数据
	public static String[][] getTextDataFromTableModel(DefaultTableModel tableModel) {
		int numOfRows = tableModel.getRowCount();
		int numOfColumns = tableModel.getColumnCount();
		String[][] data = new String[numOfRows][numOfColumns];
		for (int i = 0; i < numOfRows; i++) {
			for (int j = 0; j < numOfColumns; j++) {
				data[i][j] = (String) tableModel.getValueAt(i, j);
			}
		}
		return data;
	}
	
	// 按指定车型提取车次信息
	public static String[][] getTrainDataByType(String[][] sourceData, String type) {
		String[][] result = null;
		int rows = 0;
		int columns = getColumnsOfArray2(sourceData);
		if (type.equals("GDC")) {
			for (int i = 0; i < sourceData.length; i++) {
				/* 因为车次被HTML标签<html><b></b></html>所包围，
				 * 所以下面的sourceData[i][0]的charAt(0)是字符'<'，不是车次的第一个字母，
				 * 故要取到车次的第一个字母就必须写成charAt(9)。
				 * 即字符串sourceData[i][0]的索引(下标)为9的字符才是车次的第一个字母，代表车次类型。
				 * 下同。
				 */
				if (sourceData[i][0].charAt(9) == 'G'
				|| sourceData[i][0].charAt(9) == 'D'
				|| sourceData[i][0].charAt(9) == 'C') {
					rows++;
				}
			}
			result = new String[rows][columns];
			int x1 = 0;
			int x2 = 0;
			while (x2 < sourceData.length) {
				if (sourceData[x2][0].charAt(9) == 'G'
				|| sourceData[x2][0].charAt(9) == 'D'
				|| sourceData[x2][0].charAt(9) == 'C') {
					for (int y = 0; y < columns; y++) {
						result[x1][y] = sourceData[x2][y];
					}
					x1++;
				}
				x2++;
			}
		}
		else if (type.equals("K")) {
			for (int i = 0; i < sourceData.length; i++) {
				if (sourceData[i][0].charAt(9) == 'K') {
					rows++;
				}
			}
			result = new String[rows][columns];
			int x1 = 0;
			int x2 = 0;
			while (x2 < sourceData.length) {
				if (sourceData[x2][0].charAt(9) == 'K') {
					for (int y = 0; y < columns; y++) {
						result[x1][y] = sourceData[x2][y];
					}
					x1++;
				}
				x2++;
			}
		}
		else if (type.equals("T")) {
			for (int i = 0; i < sourceData.length; i++) {
				if (sourceData[i][0].charAt(9) == 'T') {
					rows++;
				}
			}
			result = new String[rows][columns];
			int x1 = 0;
			int x2 = 0;
			while (x2 < sourceData.length) {
				if (sourceData[x2][0].charAt(9) == 'T') {
					for (int y = 0; y < columns; y++) {
						result[x1][y] = sourceData[x2][y];
					}
					x1++;
				}
				x2++;
			}
		}
		else if (type.equals("Z")) {
			for (int i = 0; i < sourceData.length; i++) {
				if (sourceData[i][0].charAt(9) == 'Z') {
					rows++;
				}
			}
			result = new String[rows][columns];
			int x1 = 0;
			int x2 = 0;
			while (x2 < sourceData.length) {
				if (sourceData[x2][0].charAt(9) == 'Z') {
					for (int y = 0; y < columns; y++) {
						result[x1][y] = sourceData[x2][y];
					}
					x1++;
				}
				x2++;
			}
		}
		else if (type.equals("other")) {
			for (int i = 0; i < sourceData.length; i++) {
				if (sourceData[i][0].charAt(9) != 'G'
				&& sourceData[i][0].charAt(9) != 'D'
				&& sourceData[i][0].charAt(9) != 'C'
				&& sourceData[i][0].charAt(9) != 'K'
				&& sourceData[i][0].charAt(9) != 'T'
				&& sourceData[i][0].charAt(9) != 'Z') {
					rows++;
				}
			}
			result = new String[rows][columns];
			int x1 = 0;
			int x2 = 0;
			while (x2 < sourceData.length) {
				if (sourceData[x2][0].charAt(9) != 'G'
				&& sourceData[x2][0].charAt(9) != 'D'
				&& sourceData[x2][0].charAt(9) != 'C'
				&& sourceData[x2][0].charAt(9) != 'K'
				&& sourceData[x2][0].charAt(9) != 'T'
				&& sourceData[x2][0].charAt(9) != 'Z') {
					for (int y = 0; y < columns; y++) {
						result[x1][y] = sourceData[x2][y];
					}
					x1++;
				}
				x2++;
			}
		}
		return result;
	}
	
	// 打印字符串数组内容
	public static void printStringArray2(String[][] array) {
		int rows = array.length;
		int columns = getColumnsOfArray2(array);
		if (rows * columns == 0) {
			System.out.println("Empty Array.");
		}
		else {
			System.out.println("Array Size: " + rows + "*" + columns);
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns - 1; j++) {
					System.out.print(array[i][j] + ", ");
				}
				System.out.println(array[i][columns - 1]);
			}
		}
	}
	
	// 获取二维字符串数组的列数
	public static int getColumnsOfArray2(String[][] sourceArray) {
		int result = 0;
		int size = 0;
		if (sourceArray.length != 0) {
			for (int i = 0; i < sourceArray.length; i++) {
				int j = 0;
				try {
					while (sourceArray[i][j++] != null) {
						size++;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					if (j == 0) size = 0;
				}
			}
			result = size / sourceArray.length;
		}
		return result;
	}
	
	// 模拟鼠标在指定位置的单击操作
	public static void mouseClick(int mouseKey, int x, int y) throws IllegalArgumentException {
		try {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int xMax = screenSize.width;
			int yMax = screenSize.height;
			if (mouseKey != InputEvent.BUTTON1_MASK && mouseKey != InputEvent.BUTTON2_MASK && mouseKey != InputEvent.BUTTON3_MASK) {
				throw new IllegalArgumentException("Invalid mouse key code!");
			}
			else if (x < 1 || x > xMax || y < 1 || y > yMax) {
				throw new IllegalArgumentException("Illegal x or y coodinate (x should be: 1 <= x <= " + xMax + ", and y should be: 1 <= y <= " + yMax + ")!");
			}
			else {
				Robot robot = new Robot();
				robot.mouseMove(x, y);
				robot.delay(100);
				robot.mousePress(mouseKey);
				robot.delay(100);
				robot.mouseRelease(mouseKey);
			}
		} catch (AWTException ae) {
			ae.printStackTrace();
		}
	}
}
