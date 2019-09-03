package com.train.test;

import java.net.URL;

import com.train.gui.Utility;

public class Tsst2 {

	public static void main(String[] args) {
		Runtime run = Runtime.getRuntime();
		String urlStr = "https://kyfw.12306.cn/otn/leftTicket/queryX?leftTicketDTO.train_date=2017-09-28&leftTicketDTO.from_station=TSJ&leftTicketDTO.to_station=WHN&purpose_codes=ADULT";
		try {
			String str1 = Utility.readUrlAsString(new URL(urlStr), "UTF-8");
			if (str1.equals("error")) {
				String command = "C:\\Program Files\\Internet Explorer\\iexplore.exe " + urlStr;
				//String command = "C:\\Windows\\SystemApps\\Microsoft.MicrosoftEdge_8wekyb3d8bbwe\\MicrosoftEdge.exe";
				run.exec(command);
			}
			else {
				System.out.println("str1 = " + str1);
				System.out.println("The network may exist an error! Please try again!");
			}
		} catch (Exception e1) {
			e1.printStackTrace(System.out);
		}
	}

}
