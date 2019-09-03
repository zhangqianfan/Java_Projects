package com.train.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Tsst1 {

	public static void main(String[] args) {
		
		String urlString = "https://kyfw.12306.cn/otn/leftTicket/queryX";
		
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put("leftTicketDTO.train_date", "2017-10-04");
		parameter.put("leftTicketDTO.from_station", "TSJ");
		parameter.put("leftTicketDTO.to_station", "WHN");
		parameter.put("purpose_codes", "ADULT");
		String[] paramKeySet = { "leftTicketDTO.train_date", "leftTicketDTO.from_station", "leftTicketDTO.to_station", "purpose_codes" };
		String paramString = "";
		String fileName = "D:\\java\\json\\queryX.json";
		HttpURLConnection conn = null;
		
		try {
			for (int i = 0; i < paramKeySet.length; i++) {
				if (i != paramKeySet.length - 1) {
					paramString = paramString + paramKeySet[i] + "=" + parameter.get(paramKeySet[i]) + "&";
				}
				else {
					paramString = paramString + paramKeySet[i] + "=" + parameter.get(paramKeySet[i]);
				}
			}
			urlString = urlString + "?" + paramString;
			URL url = new URL(urlString);
			conn = (HttpURLConnection) url.openConnection();
			//conn.setRequestMethod("P");
			//conn.setRequestProperty("Accept-Charset", "UTF-8");
			//conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			//conn.setRequestProperty("Content-Length", String.valueOf(paramString.length()));
			conn.setDoOutput(true);
			
			conn.connect();
			
			System.out.println("Request URL: " + url.toString());
			System.out.println("RequestMethod: " + conn.getRequestMethod());
			System.out.println("HTTP Status Code: " + conn.getResponseCode());
			System.out.println("HTTP Response Message: " + conn.getResponseMessage());
			
			//https://kyfw.12306.cn/otn/leftTicket/queryX?leftTicketDTO.train_date=2017-10-03&leftTicketDTO.from_station=TSJ&leftTicketDTO.to_station=WHN&purpose_codes=ADULT
			InputStream in = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader reader = new BufferedReader(isr);
			File file = new File(fileName);
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			String s; byte[] b;
			while ((s = reader.readLine()) != null) {
				b = s.getBytes("UTF-8");
				raf.write(b);
			}
			raf.close();
			reader.close();
			isr.close();
			in.close();
			if (file.exists()) {
				System.out.println("File 'queryX.json' is successfully created and correctly written!");
			} else {
				System.err.println("Operation Failed!");
			}
		} catch (MalformedURLException mue) {
			System.err.println("Operation Failed! The following message is the exception stack trace:");
			mue.printStackTrace(System.out);
		} catch (IOException ioe) {
			System.err.println("Operation Failed! The following message is the exception stack trace:");
			ioe.printStackTrace(System.out);
		}
		
		
	}
}
