package com.train.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;

public class HttpRequest {
	
	private final String charset = "UTF-8";
	private Integer connectTimeout = null;
	private Integer socketTimeout = null;
	private String proxyHost = null;
	private Integer proxyPort = null;
	
	public String doGet(URL url) {
		
		InputStream inputStream = null;
		InputStreamReader isr = null;
		BufferedReader buffederReader = null;
		StringBuffer resultBuffer = new StringBuffer();
		String tempLine = null;
		
		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // 打开连接
			connection.setRequestProperty("Accept-Charset", charset);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			if (connection.getResponseCode() >= 300) {
				throw new Exception("HTTP Request failed with a status of " + connection.getResponseCode());
			}
			
			inputStream = connection.getInputStream();
			isr = new InputStreamReader(inputStream);
			buffederReader = new BufferedReader(isr);
			while ((tempLine = buffederReader.readLine()) != null) {
				resultBuffer.append(tempLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (buffederReader != null) buffederReader.close();
				if (isr != null) isr.close();
				if (inputStream != null) inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultBuffer.toString();
	}
	
	public String doPost(URL url, Map<String, String> param) {
		
		StringBuffer paramBuffer = new StringBuffer();
		String key = null;
		String value = null;
		
		if (param != null) {
			Iterator<String> iterator = param.keySet().iterator();
			while (iterator.hasNext()) {
				key = (String) iterator.next();
				if (param.get(key) != null) {
					value = (String) param.get(key);
				}
				else value = "";
				paramBuffer.append(key).append("=").append(value);
				if (iterator.hasNext()) {
					paramBuffer.append("&");
				}
			}
		}
		
		InputStream inputStream = null;
		InputStreamReader isr = null;
		BufferedReader buffederReader = null;
		OutputStream outputStream = null;
		OutputStreamWriter osw = null;
		StringBuffer resultBuffer = new StringBuffer();
		String tempLine = null;
		
		try {
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // 打开连接
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.addRequestProperty("Accept-Charset", charset);
			connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.addRequestProperty("Content-Length", String.valueOf(paramBuffer.length()));
			
			if (connection.getResponseCode() >= 300) {
				throw new HttpStatusException("HTTP Request failed with a status of " + connection.getResponseCode());
			}
			
			outputStream = connection.getOutputStream();
			osw = new OutputStreamWriter(outputStream);
			osw.write(paramBuffer.toString());
			osw.flush();
			
			inputStream = connection.getInputStream();
			isr = new InputStreamReader(inputStream);
			buffederReader = new BufferedReader(isr);
			while ((tempLine = buffederReader.readLine()) != null) {
				resultBuffer.append(tempLine);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (buffederReader != null) buffederReader.close();
				if (isr != null) isr.close();
				if (inputStream != null) inputStream.close();
				if (osw != null) osw.close();
				if (outputStream != null) outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultBuffer.toString();
	}
	
	public URLConnection getConnection(URL localURL) {
		URLConnection connection = null;
		try {
			if (proxyHost != null && proxyPort != null) {
				Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
				connection = localURL.openConnection(proxy);
			}
			else {
				connection = localURL.openConnection();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public void renderRequest(URLConnection connection) {
		if (connectTimeout != null) {
			connection.setConnectTimeout(connectTimeout);
		}
		if (socketTimeout != null) {
			connection.setReadTimeout(socketTimeout);
		}
	}

	public Integer getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(Integer connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public Integer getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(Integer socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public String getProxyHost() {
		return proxyHost;
	}

	public void setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
	}

	public Integer getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(Integer proxyPort) {
		this.proxyPort = proxyPort;
	}

	public String getCharset() {
		return charset;
	}
	
}
