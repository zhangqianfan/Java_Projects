<%@page import="java.io.File"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="dao.BookDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="entity.Book"%>
<%@page import="util.Tools"%>
<%
	request.setCharacterEncoding("utf-8");
	DiskFileItemFactory factory = new DiskFileItemFactory();
	ServletFileUpload upload = new ServletFileUpload(factory);
	Book book = new Book();
	BookDao bd = new BookDao();
	String imgurl = "";
	List<String> type = Arrays.asList("jpg", "png", "gif");
	if (upload.isMultipartContent(request)) {
		List<FileItem> items = upload.parseRequest(request);
		for (FileItem item : items) {
			//判断是否是普通组件
			if (item.isFormField()) {
				if ("bookid".equals(item.getFieldName())) {
					String bid = item.getString();
					book.setId(Integer.valueOf(bid));
				}
				if ("newname".equals(item.getFieldName())) {
					String bname = item.getString();
					book.setTitle(new String(bname
							.getBytes("iso8859-1"), "utf-8"));
				}
				if ("newprice".equals(item.getFieldName())) {
					String price = item.getString();
					book.setPrice(Double.valueOf(price));
				}
				if ("newinventory".equals(item.getFieldName())) {
					String inventory = item.getString();
					book.setInventory(Integer.parseInt(inventory));
				}
				if ("newdate".equals(item.getFieldName())) {
					String bdate = item.getString();
					book.setPublishDate(Tools.fromStringToDate(bdate));
				}
				if ("newauthor".equals(item.getFieldName())) {
					String author = item.getString();
					book.setAuthor(new String(author
							.getBytes("iso8859-1"), "utf-8"));
				}
			} else {
				String filename = item.getName();
				String filetype = filename.substring(filename
						.lastIndexOf(".") + 1);
				if (type.contains(filetype)) {
					String path = application.getRealPath("img") + "/"
							+ filename;
					File file = new File(path);
					item.write(file);
					imgurl = "/BookSystem/img/" + filename;
					book.setImgurl(imgurl);

				}
			}
		}
	}
	if (bd.update(book)) {
		response.sendRedirect("success.jsp");
	} else {
		response.sendRedirect("fail.jsp");
	}
%>