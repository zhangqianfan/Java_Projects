package com.train.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TrainUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	JPanel stationToStationPanel = new JPanel(); // 站站查询面板
	JPanel transferPanel = new JPanel(); // 中转查询面板
	JPanel pricePanel = new JPanel(); // 票价查询面板
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP); // 选项卡
	Font font1 = new Font("Microsoft YaHei", Font.PLAIN, 16); // 微软雅黑16号字体
	Font font2 = new Font(null, Font.PLAIN, 16); // （宋体+Arial）16号字体
	DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 默认单元格渲染器
	Calendar ca = Calendar.getInstance(); // 日历对象
	
	// 站站查询界面所有组件
	JLabel fromStationLabel1 = new JLabel("出发站："); // 出发站标签
	JLabel toStationLabel1 = new JLabel("到达站："); // 到达站标签
	JLabel dateLabel1 = new JLabel("查询日期："); // 查询日期标签
	JLabel dateValueLabel1 = new JLabel(Utility.dateToString(ca)); // 日期标签
	JTextField fromStationText1 = new JTextField(7); // 出发站文本框
	JTextField toStationText1 = new JTextField(7); // 到达站文本框
	JButton returnbackButton1 = new JButton("返程"); // 返程按钮
	JButton loginButton = new JButton("登录12306账号"); // 登录按钮
	// 预售期第1~30天
	JButton dates1 = new JButton();
	JButton dates2 = new JButton();
	JButton dates3 = new JButton();
	JButton dates4 = new JButton();
	JButton dates5 = new JButton();
	JButton dates6 = new JButton();
	JButton dates7 = new JButton();
	JButton dates8 = new JButton();
	JButton dates9 = new JButton();
	JButton dates10 = new JButton();
	JButton dates11 = new JButton();
	JButton dates12 = new JButton();
	JButton dates13 = new JButton();
	JButton dates14 = new JButton();
	JButton dates15 = new JButton();
	JButton dates16 = new JButton();
	JButton dates17 = new JButton();
	JButton dates18 = new JButton();
	JButton dates19 = new JButton();
	JButton dates20 = new JButton();
	JButton dates21 = new JButton();
	JButton dates22 = new JButton();
	JButton dates23 = new JButton();
	JButton dates24 = new JButton();
	JButton dates25 = new JButton();
	JButton dates26 = new JButton();
	JButton dates27 = new JButton();
	JButton dates28 = new JButton();
	JButton dates29 = new JButton();
	JButton dates30 = new JButton();
	JLabel queryInfo = new JLabel("提示：在查询结果中点击车次可查看经停站信息。", JLabel.CENTER); // 查询信息标签(显示有多少趟车)
	JCheckBox gdc = new JCheckBox("G/D/C-高铁/动车/城际", true);
	JCheckBox kuaiSu = new JCheckBox("K-快速", true);
	JCheckBox teKuai = new JCheckBox("T-特快", true);
	JCheckBox zhiDa = new JCheckBox("Z-直达", true);
	JCheckBox qiTa = new JCheckBox("其它", true);
	JLabel tips1 = new JLabel("说明：点击上方各个日期按钮可直接查询对应日期的车次信息。", JLabel.CENTER);
	String[][] trainInfo1 = null; // 结果信息
	// 站站查询结果表格列名
	String[] columnName1 = { "车次", "出发站", "出发时间", "到达站", "到达时间", "历时", "运行区间", "商务", "特等", "一等", "二等", "高软", "软卧", "硬卧", "软座", "硬座", "无座", "操作" };
	DefaultTableModel tableModel1 = new DefaultTableModel(trainInfo1, columnName1) {
		// 站站查询表模型
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row, int column) {
			return false; // 设置所有单元格不可修改，下同
		}
	};
	JTable resultTable1 = new JTable(tableModel1); // 站站查询结果表格
	JScrollPane scrollPanel1 = new JScrollPane(resultTable1); // 滚动面板
	
	// 中转查询界面所有组件
	JLabel fromStationLabel2 = new JLabel("出发站：");
	JLabel transferStationLabel2 = new JLabel("中转站：");
	JLabel toStationLabel2 = new JLabel("目的站：");
	JLabel dateLabel2 = new JLabel("选择日期：");
	JLabel dateValueLabel2 = new JLabel(Utility.dateToString(ca));
	JTextField fromStationText2 = new JTextField(7);
	JTextField transferStationText2 = new JTextField(7);
	JTextField toStationText2 = new JTextField(7);
	JButton queryButton2 = new JButton("查询");
	JLabel tipInfo = new JLabel("提示：在查询结果中点击车次可查看经停站信息。", JLabel.CENTER);
	JLabel jlb2 = new JLabel("出发站到中转站车次信息", JLabel.CENTER);
	JLabel jlb3 = new JLabel("中转站到目的站车次信息", JLabel.CENTER);
	String[][] trainInfo2 = null;
	String[][] trainInfo3 = null;
	String[] columnName2 = { "车次", "出发站", "出发时间", "中转站", "到达时间", "历时", "运行区间", "商务", "特等", "一等", "二等", "高软", "软卧", "硬卧", "软座", "硬座", "无座", "备注" };
	String[] columnName3 = { "车次", "中转站", "出发时间", "目的站", "到达时间", "历时", "运行区间", "商务", "特等", "一等", "二等", "高软", "软卧", "硬卧", "软座", "硬座", "无座", "备注" };
	DefaultTableModel tableModel2 = new DefaultTableModel(trainInfo2, columnName2) {
		// 出发站到中转站车次信息表模型
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	DefaultTableModel tableModel3 = new DefaultTableModel(trainInfo3, columnName3) {
		// 中转站到目的站车次信息表模型
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	JTable resultTable2 = new JTable(tableModel2);
	JScrollPane scrollPanel2 = new JScrollPane(resultTable2);
	JTable resultTable3 = new JTable(tableModel3);
	JScrollPane scrollPanel3 = new JScrollPane(resultTable3);
	
	// 选择日期对话框
	JDialog dateSelectDialog = new JDialog(TrainUI.this, true); // 选择日期对话框
	JLabel selectTips = new JLabel("选择日期："); // 选择日期标签
	JComboBoxPlus<String> dateSelect = new JComboBoxPlus<String>(); // 日期选择下拉列表
	JButton okButton = new JButton("确定"); // 确定按钮(隶属于选择日期对话框)
	
	// 错误提示框
	JDialog errorDialog = new JDialog(TrainUI.this, true); // 错误提示框(模态)
	JLabel errorMsg = new JLabel(); // 错误信息
	JButton errorOkButton = new JButton("确定"); // 确定按钮(隶属于错误提示框)
	
	// 车次详情对话框
	JDialog trainDetailDialog = new JDialog(TrainUI.this, false); // 非模态
	String[][] trainDetailInfo = null;
	String[] trainDetailCol = { "站序", "站名", "到达时间", "出发时间", "停留时长", "正晚点信息" };
	DefaultTableModel trainDetailTableModel = new DefaultTableModel(trainDetailInfo, trainDetailCol) {
		// 经停站信息表模型
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	JTable trainDetailTable = new JTable(trainDetailTableModel); // 经停站信息表格
	JScrollPane trainDetailScrollPanel = new JScrollPane(trainDetailTable); // 滚动面板
	
	// 登录框
	JDialog loginDialog = new JDialog(TrainUI.this, true); // 登录框(模态)
	JLabel yongHuMing = new JLabel("用户名：");
	JLabel miMa = new JLabel("密码：");
	JLabel yanZhengMa = new JLabel("验证码：");
	JTextField username = new JTextField(); // 用户名
	JPasswordField password = new JPasswordField(); // 密码
	JLabel verifyCodeImg = null; // 图形验证码
	JButton loginOkButton = new JButton("登录");
	JButton refreshVerifyCodeButton = new JButton("刷新验证码");
	JLabel[] marks = new JLabel[8]; // 鼠标在验证码图片上点击后留下的标记
	String captchaStr = ""; // 登录接口URL中的验证码参数字符串（点击的鼠标坐标）
	
	// 主窗口组件集合
	// components1：站站查询组件
	Component[] components1 = { fromStationLabel1, fromStationText1, toStationLabel1, toStationText1, returnbackButton1, loginButton, queryInfo, dates1, dates2, dates3, dates4, dates5, dates6, dates7, dates8, dates9, dates10, dates11, dates12, dates13, dates14, dates15, dates16, dates17, dates18, dates19, dates20, dates21, dates22, dates23, dates24, dates25, dates26, dates27, dates28, dates29, dates30, gdc, kuaiSu, teKuai, zhiDa, qiTa, tips1, scrollPanel1 };
	// components2：中转查询组件
	Component[] components2 = { fromStationLabel2, fromStationText2, transferStationLabel2, transferStationText2, toStationLabel2, toStationText2, dateLabel2, dateValueLabel2, queryButton2, tipInfo, jlb2, scrollPanel2, jlb3, scrollPanel3 };
	
	// 构造函数
	public TrainUI() {
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		setTitle("列车时刻表查询系统");
		setSize(1300, 700);
		setLocation(15, 15);
		setResizable(false); // 禁用缩放
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addstationToStationPanel(); // 为站站查询界面添加组件
		addTransferPanel(); // 为中转查询界面添加组件
		// 为车次详情表格添加事件
		trainDetailTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent ev) {
				int col = trainDetailTable.getSelectedColumn(); // 获取表格被选中单元格的列标
				if (col == 5) { // 如果被选中单元格的列标为5（说明点击的是正晚点信息）
					int row = trainDetailTable.getSelectedRow(); // 获取被选中单元格的行标
					if (row > 0) {
						// 限定行标大于0，防止点击始发站导致始发站正晚点信息一列的文字改变
						trainDetailTable.setValueAt("查询中，请稍候……", row, col);
					}
				}
			}
			public void mouseClicked(MouseEvent ev) {
				showDelayInfo();
			}
		});
		tabbedPane.add(stationToStationPanel, "站站查询");
		tabbedPane.add(transferPanel, "中转查询");
		this.add(tabbedPane);
		setVisible(true);
	}
	
	// 向站站查询面板添加必要的按钮和文本框
	public void addstationToStationPanel() {
		resultTable1.setDefaultRenderer(Object.class, dtcr); // 设置表格内容居中对齐
		resultTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // 关闭自动根据内容缩放
		resultTable1.getTableHeader().setFont(font1);// 设置表头字体
		resultTable1.setFont(font2); // 设置单元格字体
		resultTable1.setRowHeight(50); // 设置行高
		resultTable1.getTableHeader().setResizingAllowed(false); // 设置列宽不可变动
		resultTable1.getTableHeader().setReorderingAllowed(false); // 设置列的顺序不可变动
		/*
		 * column index - column name
		 * 0-车次, 1-出发站, 2-出发时间, 3-到达站, 4-到达时间, 5-历时
		 * 6-始发站, 7-终点站, 8-商务座, 9-特等座, 10-一等座, 11-二等座
		 * 12-高级软卧, 13-软卧, 14-硬卧, 15-软座, 16-硬座, 17-无座
		 */
		setColumnWidthsForSTSQuery(); // 设置表格每列的列宽
		
		// 禁用布局管理器！
		stationToStationPanel.setLayout(null);
		
		// 给每个组件自己定位！
		components1[0].setBounds(10, 5, 65, 30); // 出发站标签
		components1[1].setBounds(75, 5, 90, 30); // 出发站文本框
		components1[2].setBounds(180, 5, 65, 30); // 到达站标签
		components1[3].setBounds(245, 5, 90, 30); // 到达站文本框
		components1[4].setBounds(350, 5, 70, 30); // 返程按钮
		components1[5].setBounds(435, 5, 180, 30); // 登录按钮
		components1[6].setBounds(635, 5, 560, 30); // 信息标签
		// 预售期1~30(components1[7]~components1[36])
		components1[7].setBounds(8, 40, 85, 60);
		components1[8].setBounds(93, 40, 85, 60);
		components1[9].setBounds(178, 40, 85, 60);
		components1[10].setBounds(263, 40, 85, 60);
		components1[11].setBounds(348, 40, 85, 60);
		components1[12].setBounds(433, 40, 85, 60);
		components1[13].setBounds(518, 40, 85, 60);
		components1[14].setBounds(603, 40, 85, 60);
		components1[15].setBounds(688, 40, 85, 60);
		components1[16].setBounds(773, 40, 85, 60);
		components1[17].setBounds(858, 40, 85, 60);
		components1[18].setBounds(943, 40, 85, 60);
		components1[19].setBounds(1028, 40, 85, 60);
		components1[20].setBounds(1113, 40, 85, 60);
		components1[21].setBounds(1198, 40, 85, 60);
		components1[22].setBounds(8, 100, 85, 60);
		components1[23].setBounds(93, 100, 85, 60);
		components1[24].setBounds(178, 100, 85, 60);
		components1[25].setBounds(263, 100, 85, 60);
		components1[26].setBounds(348, 100, 85, 60);
		components1[27].setBounds(433, 100, 85, 60);
		components1[28].setBounds(518, 100, 85, 60);
		components1[29].setBounds(603, 100, 85, 60);
		components1[30].setBounds(688, 100, 85, 60);
		components1[31].setBounds(773, 100, 85, 60);
		components1[32].setBounds(858, 100, 85, 60);
		components1[33].setBounds(943, 100, 85, 60);
		components1[34].setBounds(1028, 100, 85, 60);
		components1[35].setBounds(1113, 100, 85, 60);
		components1[36].setBounds(1198, 100, 85, 60);
		// 5个复选框(components1[37]~components1[41])
		components1[37].setBounds(20, 160, 210, 30); // G/D/C
		components1[38].setBounds(230, 160, 100, 30); // K
		components1[39].setBounds(330, 160, 100, 30); // T
		components1[40].setBounds(430, 160, 100, 30); // Z
		components1[41].setBounds(530, 160, 100, 30); // 其它
		components1[42].setBounds(680, 160, 450, 30); // 提示信息
		components1[43].setBounds(5, 190, 1281, 447); // 表格所在滚动面板
		
		// 组件设置
		String monthdate = null;
		String[] weeks0 = { "日", "一", "二", "三", "四", "五", "六" };
		for (int x = 7; x < 37; x++) {
			components1[x].setFont(font1);
			monthdate = Utility.dateToString(ca).substring(5);
			((JButton) components1[x]).setName(Utility.dateToString(ca));
			((JButton) components1[x]).setText("<html>" + monthdate + "<br>(周" + weeks0[ca.get(Calendar.DAY_OF_WEEK) - 1] + ")</html>");
			((JButton) components1[x]).setMargin(new Insets(0, 0, 0, 0));
			ca.add(Calendar.DATE, 1);
		}
		for (int k = 37; k < 42; k++) {
			((JCheckBox) components1[k]).setEnabled(false);
		}
		
		// 正式添加组件
		for (int i = 0; i < components1.length; i++) {
			components1[i].setFont(font1);
			stationToStationPanel.add(components1[i]);
		}
		
		// 为返程按钮添加事件
		((JButton) components1[4]).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String departureSt = ((JTextField) components1[1]).getText();
				String arrivalSt = ((JTextField) components1[3]).getText();
				((JTextField) components1[1]).setText(arrivalSt);
				((JTextField) components1[3]).setText(departureSt);
			}
		});
		
		// 为登录按钮添加事件
		((JButton) components1[5]).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showLoginDialog();
			}
		});
		
		// 为30个预售期按钮添加事件
		for (int n = 7; n < 37; n++) {
			((JButton) components1[n]).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// 一次性将车次详情对话框位置和大小设置好，避免每次位置都变化
					// 如果执行新的一次查询前车次详情对话框处于打开状态，则先将其关闭
					trainDetailDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					trainDetailDialog.dispose();
					trainDetailDialog.setSize(620, 400);
					trainDetailDialog.setLocation(355, 223);
					trainDetailDialog.setResizable(false); // 禁用缩放
					errorDialog.setTitle("错误");
					errorDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					errorDialog.setSize(400, 150);
					errorDialog.setLocation(500, 300);
					errorDialog.setResizable(false); // 禁用缩放
					// 将5个复选框全都设置为选中状态
					for (int x = 37; x < 42; x++) {
						((JCheckBox) components1[x]).setSelected(true);
					}
					// 为错误提示框的确定按钮添加事件
					errorOkButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							errorDialog.dispose();
						}
					});
					String fromSta = ((JTextField) components1[1]).getText();
					String toSta = ((JTextField) components1[3]).getText();
					String fromStaCode = StationDictionary.stationNameToCode(fromSta);
					String toStaCode = StationDictionary.stationNameToCode(toSta);
					if (fromSta.equals("") || toSta.equals("")) {
						errorMsg.setText("出发站和到达站均不能为空!");
						showErrorTips();
					}
					else if (fromStaCode.equals("null") || toStaCode.equals("null")) {
						errorMsg.setText("出发站或到达站输入非法，请重新输入!");
						showErrorTips();
						((JTextField) components1[1]).setText("");
						((JTextField) components1[3]).setText("");
					}
					else if (fromSta.equals(toSta)) {
						errorMsg.setText("出发站和到达站不能相同!");
						showErrorTips();
					}
					else {
						JButton buttonClicked = (JButton) e.getSource();
						String dateStr = buttonClicked.getName();
						String requestURL = "https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=" + dateStr
							+ "&leftTicketDTO.from_station=" + fromStaCode
							+ "&leftTicketDTO.to_station=" + toStaCode
							+ "&purpose_codes=ADULT";
						try {
							URL url = new URL(requestURL);
							// 先以给定的URL查询，若查询不到结果则自动改变URL直至能查询到结果为止
							String jsonData = Utility.readUrlAsString(url, "UTF-8");
							JSONParser jsonParser = new JSONParser();
							JSONObject parsedContent = (JSONObject) jsonParser.parse(jsonData);
							String c_url = (String) parsedContent.get("c_url");
							if (c_url != null) {
								requestURL = "https://kyfw.12306.cn/otn/" + c_url + "?leftTicketDTO.train_date=" + dateStr
										+ "&leftTicketDTO.from_station=" + fromStaCode
										+ "&leftTicketDTO.to_station=" + toStaCode
										+ "&purpose_codes=ADULT";
								url = new URL(requestURL);
								jsonData = Utility.readUrlAsString(url, "UTF-8");
							}
							String[][] trainData = Utility.jsonToTrainArray(jsonData);
							tableModel1.setDataVector(trainData, columnName1);
							// 为防止列宽变化，此处应再次设置一下列宽
							setColumnWidthsForSTSQuery();
							// 更改信息标签内容
							String queryMsg = "";
							if (trainData == null) {
								queryMsg = "从 " + fromSta + " 到 " + toSta + " 未查询到车次信息!";
								((JLabel) components1[6]).setText(queryMsg);
								((JLabel) components1[42]).setText("筛选后有 0 个车次");
								((JLabel) components1[6]).setForeground(new Color(255, 0, 0));
								errorMsg.setText("未查询到车次信息！请更改查询日期或尝试中转查询！");
								showErrorTips();
							}
							else {
								Calendar calendar0 = Utility.parseToCalendar(dateStr); // 解析成日期
								// 获取年月日及星期
								int yearA = calendar0.get(Calendar.YEAR);
								int monthA = calendar0.get(Calendar.MONTH) + 1;
								int dayA = calendar0.get(Calendar.DATE);
								int weekA = calendar0.get(Calendar.DAY_OF_WEEK) - 1;
								String[] weeks = { "日", "一", "二", "三", "四", "五", "六" };
								// 拼接成查询信息
								queryMsg = yearA + "年" + monthA + "月" + dayA + "日（周" + weeks[weekA]
										+ "），从 " + fromSta + " 到 " + toSta + " 共查询出 " + trainData.length + " 个车次";
								((JLabel) components1[6]).setText(queryMsg);
								((JLabel) components1[42]).setText("筛选后有 "+ trainData.length +" 个车次");
								((JLabel) components1[6]).setForeground(new Color(30, 150, 80));
								((JLabel) components1[6]).setName(dateStr);
								for (int k = 37; k < 42; k++) {
									((JCheckBox) components1[k]).setEnabled(true);
								}
								trainInfo1 = new String[tableModel1.getRowCount()][tableModel1.getColumnCount()];
								for (int i = 0; i < tableModel1.getRowCount(); i++) {
									for (int j = 0; j < tableModel1.getColumnCount(); j++) {
										trainInfo1[i][j] = trainData[i][j];
									}
								}
							}
						} catch (Exception e1) {
							e1.printStackTrace(System.out);
						}
					}
				}
			});
		}
		
		// 为5个复选框添加事件(components1[37]~components1[41])
		((JCheckBox) components1[37]).addItemListener(new ItemListener() {
			// G/D/C
			@Override
			public void itemStateChanged(ItemEvent e) {
				JCheckBox checkBox = (JCheckBox) e.getItem();
				// 每次复选框状态发生改变时都要获取一次表格中的数据
				if (checkBox.isSelected()) { // 在原数据中添加高铁/动车/城际列车信息
					int cnt = 0;
					String[][] resultGDC = Utility.getTrainDataByType(trainInfo1, "GDC");
					// 必须加入如下的if判断，否则当表格为空时无法插入新数据
					if (tableModel1.getRowCount() > 0) {
						for (int k = 0; k < tableModel1.getRowCount(); k++) {
							if (cnt >= resultGDC.length) break;
							// 保证插入后结果仍按出发时间顺序排列
							if (resultGDC[cnt][2].compareTo((String) tableModel1.getValueAt(k, 2)) < 0) {
								tableModel1.insertRow(k, resultGDC[cnt]);
								cnt++;
							}
						}
						for (int p = cnt; p < resultGDC.length; p++) {
							tableModel1.insertRow(tableModel1.getRowCount(), resultGDC[p]);
						}
					}
					else {
						for (int k = 0; k < resultGDC.length; k++) {
							tableModel1.insertRow(k, resultGDC[k]);
						}
					}
					// 这里必须执行一次删除重复行的操作，否则弹出错误提示后将在表格中出现重复行
					for (int x = tableModel1.getRowCount() - 1; x >= 1; x--) {
						if (tableModel1.getValueAt(x, 0).equals(tableModel1.getValueAt(x - 1, 0))) {
							tableModel1.removeRow(x);
						}
					}
				}
				else if (!checkBox.isSelected()) {
					boolean flag = !((JCheckBox) components1[38]).isSelected()
							&& !((JCheckBox) components1[39]).isSelected()
							&& !((JCheckBox) components1[40]).isSelected()
							&& !((JCheckBox) components1[41]).isSelected();
					if (flag) { // 保证至少要有一个复选框处于选中状态
						errorMsg.setText("请至少选择一个车型！");
						showErrorTips();
					}
					else {
						// 删除原数据中的高铁/动车/城际列车信息
						// 注意：删除表格行时行标k必须是递减的！
						for (int k = tableModel1.getRowCount() - 1; k >= 0; k--) {
							if (((String) tableModel1.getValueAt(k, 0)).charAt(9) == 'G'
							|| ((String) tableModel1.getValueAt(k, 0)).charAt(9) == 'D'
							|| ((String) tableModel1.getValueAt(k, 0)).charAt(9) == 'C') {
								tableModel1.removeRow(k);
							}
						}
					}	
				}
				((JLabel) components1[42]).setText("筛选后有 " + tableModel1.getRowCount() + " 个车次");
			}
		});
		
		((JCheckBox) components1[38]).addItemListener(new ItemListener() {
			// K
			@Override
			public void itemStateChanged(ItemEvent e) {
				JCheckBox checkBox = (JCheckBox) e.getItem();
				// 每次复选框状态发生改变时都要获取一次表格中的数据
				if (checkBox.isSelected()) { // 在原数据中添加快速列车信息
					int cnt = 0;
					String[][] resultK = Utility.getTrainDataByType(trainInfo1, "K");
					// 必须加入如下的if判断，否则当表格为空时无法插入新数据
					if (tableModel1.getRowCount() > 0) {
						for (int k = 0; k < tableModel1.getRowCount(); k++) {
							if (cnt >= resultK.length) break;
							if (resultK[cnt][2].compareTo((String) tableModel1.getValueAt(k, 2)) < 0) {
								tableModel1.insertRow(k, resultK[cnt]);
								cnt++;
							}
						}
						for (int p = cnt; p < resultK.length; p++) {
							tableModel1.insertRow(tableModel1.getRowCount(), resultK[p]);
						}
					}
					else {
						for (int k = 0; k < resultK.length; k++) {
							tableModel1.insertRow(k, resultK[k]);
						}
					}
					// 这里必须执行一次删除重复行的操作，否则弹出错误提示后将在表格中出现重复行
					for (int x = tableModel1.getRowCount() - 1; x >= 1; x--) {
						if (tableModel1.getValueAt(x, 0).equals(tableModel1.getValueAt(x - 1, 0))) {
							tableModel1.removeRow(x);
						}
					}
				}
				else if (!checkBox.isSelected()) {
					boolean flag = !((JCheckBox) components1[37]).isSelected()
							&& !((JCheckBox) components1[39]).isSelected()
							&& !((JCheckBox) components1[40]).isSelected()
							&& !((JCheckBox) components1[41]).isSelected();
					if (flag) { // 保证至少要有一个复选框处于选中状态
						errorMsg.setText("请至少选择一个车型！");
						showErrorTips();
					}
					else {
						// 删除原数据中的快速列车信息
						// 注意：删除表格行时行标k必须是递减的！
						for (int k = tableModel1.getRowCount() - 1; k >= 0; k--) {
							if (((String) tableModel1.getValueAt(k, 0)).charAt(9) == 'K') {
								tableModel1.removeRow(k);
							}
						}
					}
				}
				((JLabel) components1[42]).setText("筛选后有 " + tableModel1.getRowCount() + " 个车次");
			}
		});
		((JCheckBox) components1[39]).addItemListener(new ItemListener() {
			// T
			@Override
			public void itemStateChanged(ItemEvent e) {
				JCheckBox checkBox = (JCheckBox) e.getItem();
				// 每次复选框状态发生改变时都要获取一次表格中的数据
				if (checkBox.isSelected()) { // 在原数据中添加特快列车信息
					int cnt = 0;
					String[][] resultT = Utility.getTrainDataByType(trainInfo1, "T");
					// 必须加入如下的if判断，否则当表格为空时无法插入新数据
					if (tableModel1.getRowCount() > 0) {
						for (int k = 0; k < tableModel1.getRowCount(); k++) {
							if (cnt >= resultT.length) break;
							if (resultT[cnt][2].compareTo((String) tableModel1.getValueAt(k, 2)) < 0) {
								tableModel1.insertRow(k, resultT[cnt]);
								cnt++;
							}
						}
						for (int p = cnt; p < resultT.length; p++) {
							tableModel1.insertRow(tableModel1.getRowCount(), resultT[p]);
						}
					}
					else {
						for (int k = 0; k < resultT.length; k++) {
							tableModel1.insertRow(k, resultT[k]);
						}
					}
					// 这里必须执行一次删除重复行的操作，否则弹出错误提示后将在表格中出现重复行
					for (int x = tableModel1.getRowCount() - 1; x >= 1; x--) {
						if (tableModel1.getValueAt(x, 0).equals(tableModel1.getValueAt(x - 1, 0))) {
							tableModel1.removeRow(x);
						}
					}
				}
				else if (!checkBox.isSelected()) {
					boolean flag = !((JCheckBox) components1[37]).isSelected()
							&& !((JCheckBox) components1[38]).isSelected()
							&& !((JCheckBox) components1[40]).isSelected()
							&& !((JCheckBox) components1[41]).isSelected();
					if (flag) { // 保证至少要有一个复选框处于选中状态
						errorMsg.setText("请至少选择一个车型！");
						showErrorTips();
					}
					else {
						// 删除原数据中的特快列车信息
						// 注意：删除表格行时行标k必须是递减的！
						for (int k = tableModel1.getRowCount() - 1; k >= 0; k--) {
							if (((String) tableModel1.getValueAt(k, 0)).charAt(9) == 'T') {
								tableModel1.removeRow(k);
							}
						}
					}
				}
				((JLabel) components1[42]).setText("筛选后有 " + tableModel1.getRowCount() + " 个车次");
			}
		});
		((JCheckBox) components1[40]).addItemListener(new ItemListener() {
			// Z
			@Override
			public void itemStateChanged(ItemEvent e) {
				JCheckBox checkBox = (JCheckBox) e.getItem();
				// 每次复选框状态发生改变时都要获取一次表格中的数据
				if (checkBox.isSelected()) { // 在原数据中添加直达列车信息
					int cnt = 0;
					// 必须加入如下的if判断，否则当表格为空时无法插入新数据
					String[][] resultZ = Utility.getTrainDataByType(trainInfo1, "Z");
					if (tableModel1.getRowCount() > 0) {
						for (int k = 0; k < tableModel1.getRowCount(); k++) {
							if (cnt >= resultZ.length) break;
							if (resultZ[cnt][2].compareTo((String) tableModel1.getValueAt(k, 2)) < 0) {
								tableModel1.insertRow(k, resultZ[cnt]);
								cnt++;
							}
						}
						for (int p = cnt; p < resultZ.length; p++) {
							tableModel1.insertRow(tableModel1.getRowCount(), resultZ[p]);
						}
					}
					else {
						for (int k = 0; k < resultZ.length; k++) {
							tableModel1.insertRow(k, resultZ[k]);
						}
					}
					// 这里必须执行一次删除重复行的操作，否则弹出错误提示后将在表格中出现重复行
					for (int x = tableModel1.getRowCount() - 1; x >= 1; x--) {
						if (tableModel1.getValueAt(x, 0).equals(tableModel1.getValueAt(x - 1, 0))) {
							tableModel1.removeRow(x);
						}
					}
				}
				else if (!checkBox.isSelected()) {
					boolean flag = !((JCheckBox) components1[37]).isSelected()
							&& !((JCheckBox) components1[38]).isSelected()
							&& !((JCheckBox) components1[39]).isSelected()
							&& !((JCheckBox) components1[41]).isSelected();
					if (flag) { // 保证至少要有一个复选框处于选中状态
						errorMsg.setText("请至少选择一个车型！");
						showErrorTips();
					}
					else {
						// 删除原数据中的直达列车信息
						// 注意：删除表格行时行标k必须是递减的！
						for (int k = tableModel1.getRowCount() - 1; k >= 0; k--) {
							if (((String) tableModel1.getValueAt(k, 0)).charAt(9) == 'Z') {
								tableModel1.removeRow(k);
							}
						}
					}
				}
				((JLabel) components1[42]).setText("筛选后有 " + tableModel1.getRowCount() + " 个车次");
			}
		});
		((JCheckBox) components1[41]).addItemListener(new ItemListener() {
			// 其它车型
			@Override
			public void itemStateChanged(ItemEvent e) {
				JCheckBox checkBox = (JCheckBox) e.getItem();
				// 每次复选框状态发生改变时都要获取一次表格中的数据
				if (checkBox.isSelected()) { // 在原数据中添加其它车型列车信息
					int cnt = 0;
					String[][] resultOther = Utility.getTrainDataByType(trainInfo1, "other");
					// 必须加入如下的if判断，否则当表格为空时无法插入新数据
					if (tableModel1.getRowCount() > 0) {
						for (int k = 0; k < tableModel1.getRowCount(); k++) {
							if (cnt >= resultOther.length) break;
							if (resultOther[cnt][2].compareTo((String) tableModel1.getValueAt(k, 2)) < 0) {
								tableModel1.insertRow(k, resultOther[cnt]);
								cnt++;
							}
						}
						for (int p = cnt; p < resultOther.length; p++) {
							tableModel1.insertRow(tableModel1.getRowCount(), resultOther[p]);
						}
					}
					else {
						for (int k = 0; k < resultOther.length; k++) {
							tableModel1.insertRow(k, resultOther[k]);
						}
					}
					// 这里必须执行一次删除重复行的操作，否则弹出错误提示后将在表格中出现重复行
					for (int x = tableModel1.getRowCount() - 1; x >= 1; x--) {
						if (tableModel1.getValueAt(x, 0).equals(tableModel1.getValueAt(x - 1, 0))) {
							tableModel1.removeRow(x);
						}
					}
				}
				else if (!checkBox.isSelected()) {
					boolean flag = !((JCheckBox) components1[37]).isSelected()
							&& !((JCheckBox) components1[38]).isSelected()
							&& !((JCheckBox) components1[39]).isSelected()
							&& !((JCheckBox) components1[40]).isSelected();
					if (flag) { // 保证至少要有一个复选框处于选中状态
						errorMsg.setText("请至少选择一个车型！");
						showErrorTips();
					}
					else {
						// 删除原数据中的其它车型列车信息
						// 注意：删除表格行时行标k必须是递减的！
						for (int k = tableModel1.getRowCount() - 1; k >= 0; k--) {
							if (((String) tableModel1.getValueAt(k, 0)).charAt(9) != 'G'
							&& ((String) tableModel1.getValueAt(k, 0)).charAt(9) != 'D'
							&& ((String) tableModel1.getValueAt(k, 0)).charAt(9) != 'C'
							&& ((String) tableModel1.getValueAt(k, 0)).charAt(9) != 'K'
							&& ((String) tableModel1.getValueAt(k, 0)).charAt(9) != 'T'
							&& ((String) tableModel1.getValueAt(k, 0)).charAt(9) != 'Z') {
								tableModel1.removeRow(k);
							}
						}
					}
				}
				((JLabel) components1[42]).setText("筛选后有 " + tableModel1.getRowCount() + " 个车次");
			}
		});
		
		// 为查询结果表格添加事件
		resultTable1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ev) {
				int col = resultTable1.getSelectedColumn(); // 获取表格被选中单元格的列标
				if (col == 0) { // 如果被选中单元格的列标为0（说明点击的是车次）
					int row = resultTable1.getSelectedRow(); // 获取被选中单元格的行标
					String trainNo = (String) resultTable1.getValueAt(row, col);
					displayTrainDetail(Utility.deleteSpecificHtmlTags(trainNo), ev);
				}
			}
		});
		
	}
	
	// 向中转查询面板添加必要的按钮和文本框
	public void addTransferPanel() {
		
		// 设置始发站到中转站车次信息表格
		resultTable2.setDefaultRenderer(Object.class, dtcr);
		resultTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resultTable2.getTableHeader().setFont(font1);
		resultTable2.setFont(font2);
		resultTable2.setRowHeight(50);
		resultTable2.getTableHeader().setResizingAllowed(false);
		resultTable2.getTableHeader().setReorderingAllowed(false);
		
		// 设置中转站到目的站车次信息表格
		resultTable3.setDefaultRenderer(Object.class, dtcr);
		resultTable3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resultTable3.getTableHeader().setFont(font1);
		resultTable3.setFont(font2);
		resultTable3.setRowHeight(50);
		resultTable3.getTableHeader().setResizingAllowed(false);
		resultTable3.getTableHeader().setReorderingAllowed(false);
		
		// 禁用中转查询面板的布局管理器
		transferPanel.setLayout(null);
		
		// 设置两个表格的列宽
		setColumnWidthsForTRQuery();
		
		// 定位各个组件
		components2[0].setBounds(5, 5, 65, 30); // 出发站标签
		components2[1].setBounds(70, 5, 90, 30); // 出发站文本框
		components2[2].setBounds(175, 5, 65, 30); // 中转站标签
		components2[3].setBounds(240, 5, 90, 30); // 中转站文本框
		components2[4].setBounds(345, 5, 65, 30); // 到达站标签
		components2[5].setBounds(410, 5, 90, 30); // 到达站文本框
		components2[6].setBounds(515, 5, 80, 30); // 选择日期标签
		components2[7].setBounds(600, 5, 120, 30); // 日期标签
		components2[8].setBounds(705, 5, 70, 30); // 查询按钮
		components2[9].setBounds(795, 5, 390, 30); // 信息标签
		components2[10].setBounds(5, 40, 1180, 24); // 出发站->中转站标签
		components2[11].setBounds(5, 65, 1280, 274); // 出发站->中转站表格滚动面板
		components2[12].setBounds(5, 340, 1180, 24); // 中转站->到达站标签
		components2[13].setBounds(5, 365, 1280, 274); // 中转站->到达站表格滚动面板
		
		// 设置组件样式
		components2[7].setForeground(new Color(0, 0, 255)); // 把日期标签的文字颜色设为蓝色
		components2[10].setForeground(new Color(128, 160, 64)); // 设置出发站->中转站标签文字颜色
		components2[12].setForeground(new Color(128, 160, 64)); // 设置中转站->到达站标签文字颜色
		
		// 为中转查询面板添加组件
		for (int i = 0; i < components2.length; i++) {
			components2[i].setFont(font1);
			transferPanel.add(components2[i]);
		}
		
		// 为日期标签(不是选择日期标签)添加事件
		((JLabel) components2[7]).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ev) {
				showDateSelectDialog(ev);
			}
		});
		
		// 为查询按钮添加事件
		((JButton) components2[8]).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 一次性将车次详情对话框位置和大小设置好，避免每次位置都变化
				// 如果执行新的一次查询前车次详情对话框处于打开状态，则先将其关闭
				trainDetailDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				trainDetailDialog.setVisible(false);
				trainDetailDialog.setSize(620, 400);
				trainDetailDialog.setLocation(355, 223);
				trainDetailDialog.setResizable(false); // 禁用缩放
				errorDialog.setTitle("错误");
				errorDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				errorDialog.setSize(400, 150);
				errorDialog.setLocation(500, 300);
				errorDialog.setResizable(false); // 禁用缩放
				// 为错误提示框的确定按钮添加事件
				errorOkButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						errorDialog.dispose();
					}
				});
				String fromSta = ((JTextField) components2[1]).getText();
				String tranSta = ((JTextField) components2[3]).getText();
				String toSta = ((JTextField) components2[5]).getText();
				String fromStaCode = StationDictionary.stationNameToCode(fromSta);
				String tranStaCode = StationDictionary.stationNameToCode(tranSta);
				String toStaCode = StationDictionary.stationNameToCode(toSta);
				if (fromSta.equals("") || tranSta.equals("") || toSta.equals("")) {
					errorMsg.setText("出发站、中转站、目的站均不能为空!");
					showErrorTips();
				}
				else if (fromStaCode.equals("null") || tranStaCode.equals("null") || toStaCode.equals("null")) {
					errorMsg.setText("出发站、中转站或目的站输入非法，请重新输入!");
					showErrorTips();
					((JTextField) components2[1]).setText("");
					((JTextField) components2[3]).setText("");
					((JTextField) components2[5]).setText("");
				}
				else if (fromSta.equals(toSta)) {
					errorMsg.setText("出发站和目的站不能相同!");
					showErrorTips();
				}
				else if (fromSta.equals(tranSta)) {
					errorMsg.setText("出发站和中转站不能相同!");
					showErrorTips();
				}
				else if (tranSta.equals(toSta)) {
					errorMsg.setText("中转站和目的站不能相同!");
					showErrorTips();
				}
				else if (fromSta.equals(toSta)) {
					errorMsg.setText("出发站和到达站不能相同!");
					showErrorTips();
				}
				else {
					String dateStr = ((JLabel) components2[7]).getText();
					String requestURL1 = "https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=" + dateStr
						+ "&leftTicketDTO.from_station=" + fromStaCode
						+ "&leftTicketDTO.to_station=" + tranStaCode
						+ "&purpose_codes=ADULT";
					String requestURL2 = "https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=" + dateStr
							+ "&leftTicketDTO.from_station=" + tranStaCode
							+ "&leftTicketDTO.to_station=" + toStaCode
							+ "&purpose_codes=ADULT";
					try {
						URL url1 = new URL(requestURL1);
						URL url2 = new URL(requestURL2);
						String jsonData1 = Utility.readUrlAsString(url1, "UTF-8");
						String jsonData2 = Utility.readUrlAsString(url2, "UTF-8");
						String[][] trainData1 = Utility.jsonToTrainArray(jsonData1);
						String[][] trainData2 = Utility.jsonToTrainArray(jsonData2);
						if (trainData1 != null && trainData2 != null) {
							((JLabel) components2[10]).setText("1. " + fromSta + "->" + tranSta + " （" + trainData1.length +"个车次）");
							((JLabel) components2[12]).setText("2. " + tranSta + "->" + toSta + " （" + trainData2.length +"个车次）");
							tableModel2.setDataVector(trainData1, columnName2);
							tableModel3.setDataVector(trainData2, columnName3);
						}
						// 为防止列宽变化，此处应再次设置一下列宽
						setColumnWidthsForTRQuery();
						// 更改信息标签内容
						String queryMsg = "";
						if (trainData1 == null && trainData2 != null) {
							queryMsg = "从 " + fromSta + " 到 " + tranSta + " 未查询到车次信息!";
							((JLabel) components2[9]).setText(queryMsg);
							((JLabel) components2[9]).setForeground(new Color(255, 0, 0));
							errorMsg.setText("未查询到车次信息！请更改查询日期或更换中转站后再试！");
							showErrorTips();
						}
						else if (trainData1 != null && trainData2 == null) {
							queryMsg = "从 " + tranSta + " 到 " + toSta + " 未查询到车次信息!";
							((JLabel) components2[9]).setText(queryMsg);
							((JLabel) components2[9]).setForeground(new Color(255, 0, 0));
							errorMsg.setText("未查询到车次信息！请更改查询日期或更换中转站后再试！");
							showErrorTips();
						}
						else if (trainData1 == null && trainData2 == null) {
							queryMsg = "从 " + fromSta + " 到 " + toSta + " 未查询到中转信息!";
							((JLabel) components2[9]).setText(queryMsg);
							((JLabel) components2[9]).setForeground(new Color(255, 0, 0));
							errorMsg.setText("未查询到车次信息！请更改查询日期或更换中转站后再试！");
							showErrorTips();
						}
						else {
							String dt = ((JLabel) components2[7]).getText(); // 获取日期标签的文本
							Calendar calendar0 = Utility.parseToCalendar(dt); // 解析成日期
							// 获取年月日及星期
							int yearA = calendar0.get(Calendar.YEAR);
							int monthA = calendar0.get(Calendar.MONTH) + 1;
							int dayA = calendar0.get(Calendar.DATE);
							int weekA = calendar0.get(Calendar.DAY_OF_WEEK) - 1;
							String[] weeks = { "日", "一", "二", "三", "四", "五", "六" };
							// 拼接成查询信息
							queryMsg = "查询日期：" + yearA + "年" + monthA + "月" + dayA + "日（周" + weeks[weekA] + "）";
							((JLabel) components2[9]).setText(queryMsg);
							((JLabel) components2[9]).setForeground(new Color(30, 150, 80));
						}
						
					} catch (MalformedURLException e1) {
						System.out.println("Ooops! An unexpected MalformedURLException has occured!");
					}
				}
				
			}
		});
		
		// 为出发站到中转站查询结果表格添加事件
		resultTable2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ev) {
				int col = resultTable2.getSelectedColumn(); // 获取表格被选中单元格的列标
				if (col == 0) { // 如果被选中单元格的列标为0（说明点击的是车次）
					int row = resultTable2.getSelectedRow(); // 获取被选中单元格的行标
					String trainNo = (String) resultTable2.getValueAt(row, col);
					displayTrainDetail(Utility.deleteSpecificHtmlTags(trainNo), ev);
				}
			}
		});
		
		// 为中转站到目的站查询结果表格添加事件
		resultTable3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ev) {
				int col = resultTable3.getSelectedColumn(); // 获取表格被选中单元格的列标
				if (col == 0) { // 如果被选中单元格的列标为0（说明点击的是车次）
					int row = resultTable3.getSelectedRow(); // 获取被选中单元格的行标
					String trainNo = (String) resultTable3.getValueAt(row, col);
					displayTrainDetail(Utility.deleteSpecificHtmlTags(trainNo), ev);
				}
			}
		});
	}
	
	// 显示登录对话框
	public void showLoginDialog() {
		loginDialog.setTitle("登录12306账号");
		loginDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // 设置默认关闭行为
		loginDialog.setResizable(false); // 禁用缩放
		loginDialog.setBounds(450, 150, 450, 400);
		loginDialog.setLayout(null);
		yongHuMing.setBounds(30, 20, 100, 30);
		username.setBounds(105, 20, 293, 30);
		miMa.setBounds(30, 60, 100, 30);
		password.setBounds(105, 60, 293, 30);
		yanZhengMa.setBounds(30, 100, 100, 30);
		for (int i = 0; i < 8; i++) {
			marks[i] = new JLabel(new ImageIcon("D:\\Math Files\\Eclipse\\Workspace\\TrainGUI3\\src\\trainlogo.png"));
			marks[i].setVisible(false);
		}
		try {
			URL verifyCodeImgUrl = new URL("https://kyfw.12306.cn/passport/captcha/captcha-image?login_site=E&module=login&rand=sjrand&" + Math.random());
			verifyCodeImg = new JLabel(new ImageIcon(verifyCodeImgUrl));
		} catch (MalformedURLException e) {
			System.out.println("Verify Code Image Url Error!");
		}
		verifyCodeImg.setBounds(105, 100, 293, 190);
		loginOkButton.setBounds(90, 305, 80, 30);
		refreshVerifyCodeButton.setBounds(210, 305, 140, 30);
		// 为图形验证码框添加鼠标事件
		verifyCodeImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				int xPosition = ev.getX();
				int yPosition = ev.getY();
				if (xPosition >= 2 && xPosition <= 289 && yPosition >= 38 && yPosition <= 181) {
					int count = 0;
					for (count = 0; count < 8; count++) {
						// 检查当前点击的是第几张图片
						if (Utility.isCooldinateInCaptcha(xPosition, yPosition, count + 1)) break;
					}
					marks[count].setBounds(xPosition + 94, yPosition + 85, 24, 24);
					marks[count].setVisible(true);
				}
			}
		});
		// 为验证码点击标记添加鼠标事件
		for (int k = 0; k < marks.length; k++) {
			marks[k].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent ev) {
					JLabel markClicked = (JLabel) ev.getSource();
					if (markClicked.isVisible()) {
						((JLabel) ev.getSource()).setVisible(false);
					}
				}
			});
		}
		// 为登录按钮添加点击事件
		loginOkButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				for (int i = 0; i < marks.length; i++) {
					if (marks[i].isVisible()) {
						int xi = marks[i].getX() - 94;
						int yi = marks[i].getY() - 116;
						// 验证码字符串
						captchaStr = captchaStr + xi + "%2C" + yi + "%2C";
					}
				}
				captchaStr = captchaStr.substring(0, captchaStr.length() - 3);
				String loginVerifyUrlStr = "https://kyfw.12306.cn/passport/web/login";
				String captchaUrlStr = "https://kyfw.12306.cn/passport/captcha/captcha-check";
				try {
					URL loginVerifyUrl = new URL(loginVerifyUrlStr);
					URL captchaUrl = new URL(captchaUrlStr);
					/*String verifyResult = Utility.readUrlAsString(loginVerifyUrl, "UTF-8");
					System.out.println("verifyResult = " + verifyResult);*/
					
					Map<String, String> userInfo = new HashMap<String, String>();
					userInfo.put("username", username.getText());
					userInfo.put("password", password.getPassword().toString());
					userInfo.put("appid", "otn");
					
					Map<String, String> captchaInfo = new HashMap<String, String>();
					captchaInfo.put("answer", captchaStr);
					captchaInfo.put("login_site", "E");
					captchaInfo.put("rand", "sjrand");
					
					System.out.println(new HttpRequest().doPost(captchaUrl, captchaInfo));
					System.out.println(new HttpRequest().doPost(loginVerifyUrl, userInfo));
					
					loginDialog.dispose();
					
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		});
		// 为刷新验证码按钮添加事件
		refreshVerifyCodeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					for (int i = 0; i < marks.length; i++) {
						marks[i].setVisible(false);
					}
					verifyCodeImg.setIcon(null);
					URL captchaURL = new URL("https://kyfw.12306.cn/passport/captcha/captcha-image?login_site=E&module=login&rand=sjrand&" + Math.random());
					verifyCodeImg.setIcon(new ImageIcon(captchaURL));
				} catch (MalformedURLException ex) {
					ex.printStackTrace();
				}
			}
		});
		verifyCodeImg.setVisible(true);
		loginDialog.add(yongHuMing);
		loginDialog.add(username);
		loginDialog.add(miMa);
		loginDialog.add(password);
		loginDialog.add(yanZhengMa);
		for (int i = 0; i < marks.length; i++) {
			loginDialog.add(marks[i]);
		}
		loginDialog.add(verifyCodeImg);
		loginDialog.add(loginOkButton);
		loginDialog.add(refreshVerifyCodeButton);
		loginDialog.setVisible(true);
	}
	
	// 显示选择日期对话框
	public void showDateSelectDialog(MouseEvent ev) {
		dateSelectDialog.setTitle("选择日期");
		dateSelectDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		dateSelectDialog.setSize(300, 150);
		dateSelectDialog.setLocation(550, 300);
		dateSelectDialog.setResizable(false);
		// 从当天开始添加后29天的日期
		Calendar todayCalendar = Calendar.getInstance();
		String today = Utility.dateToString(todayCalendar); // 当天日期
		// 如果没有当天日期，则添加当天日期，避免条目重复
		if (!dateSelect.isContainsItem(today)) {
			dateSelect.addItem(today);
		}
		for (int i = 0; i < 29; i++) {
			todayCalendar.add(Calendar.DATE, 1);
			String dates = Utility.dateToString(todayCalendar);
			// 如果没有指定日期，则添加指定日期，避免条目重复
			if (!dateSelect.isContainsItem(dates)) {
				dateSelect.addItem(dates);
			}
		}
		// 为确定按钮绑定事件
		if (ev.getSource() == components2[7]) {  // 点击的是中转查询面板的日期标签
			// 获取日期标签的日期
			String labelDate = ((JLabel) components2[7]).getText();
			// 将从日期标签处获得的日期设置为已选中
			for (int j = 0; j < 30; j++) {
				if (dateSelect.getItemAt(j).equals(labelDate)) {
					dateSelect.setSelectedIndex(j);
					break;
				}
			}
			okButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String selectedDate = (String) dateSelect.getSelectedItem();
					((JLabel) components2[7]).setText(selectedDate);
					dateSelectDialog.dispose();
				}
			});
		}
		// 将选择日期标签、确定按钮和下拉列表框添加到对话框，并设置各自的大小、位置和字体
		dateSelectDialog.setLayout(null);
		dateSelect.setBounds(121, 20, 120, 30);
		okButton.setBounds(105, 70, 80, 30);
		selectTips.setBounds(41, 20, 80, 30);
		dateSelect.setFont(font1);
		okButton.setFont(font1);
		selectTips.setFont(font1);
		dateSelectDialog.add(selectTips);
		dateSelectDialog.add(dateSelect);
		dateSelectDialog.add(okButton);
		dateSelectDialog.setVisible(true);
	}
	
	// 显示错误提示框
	public void showErrorTips() {
		errorMsg.setBounds(1, 20, 345, 30);
		errorMsg.setHorizontalAlignment(JLabel.CENTER);
		errorOkButton.setBounds(158, 70, 80, 30);
		errorDialog.setLayout(null);
		errorDialog.add(errorMsg);
		errorDialog.add(errorOkButton);
		errorDialog.setVisible(true);
	}
	
	// 显示车次经停站信息
	public void displayTrainDetail(String trainNo, MouseEvent ev) {
		String fromSta = "";
		String toSta = "";
		String dateStr = "";
		if (ev.getSource() == resultTable1) {
			fromSta = ((JTextField) components1[1]).getText();
			toSta = ((JTextField) components1[3]).getText();
			dateStr = ((JLabel) components1[6]).getName();
		}
		else if (ev.getSource() == resultTable2) {
			fromSta = ((JTextField) components2[1]).getText();
			toSta = ((JTextField) components2[3]).getText();
			dateStr = ((JLabel) components2[7]).getText();
		}
		else if (ev.getSource() == resultTable3) {
			fromSta = ((JTextField) components2[3]).getText();
			toSta = ((JTextField) components2[5]).getText();
			dateStr = ((JLabel) components2[7]).getText();
		}
		String fromStaCode = StationDictionary.stationNameToCode(fromSta);
		String toStaCode = StationDictionary.stationNameToCode(toSta);
		String stsQueryURL = "https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=" + dateStr
			+ "&leftTicketDTO.from_station=" + fromStaCode
			+ "&leftTicketDTO.to_station=" + toStaCode
			+ "&purpose_codes=ADULT"; // 站站查询请求URL
		try {
			// 这里同样需要自动适配URL
			String jsonStr = Utility.readUrlAsString(new URL(stsQueryURL), "UTF-8");
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(jsonStr);
			String c_url = (String) jsonObj.get("c_url");
			if (c_url == null) c_url = "leftTicket/query";
			stsQueryURL = "https://kyfw.12306.cn/otn/" + c_url + "?leftTicketDTO.train_date=" + dateStr
					+ "&leftTicketDTO.from_station=" + fromStaCode
					+ "&leftTicketDTO.to_station=" + toStaCode
					+ "&purpose_codes=ADULT";
			String[][] queryResult = Utility.jsonToTrainDetail(stsQueryURL, trainNo);
			trainDetailTableModel.setDataVector(queryResult, trainDetailCol); // 将数据替换为新的查询结果
			String firstSta = queryResult[0][1]; // 始发站
			String lastSta = queryResult[queryResult.length - 1][1]; // 终点站
			trainDetailTable.setDefaultRenderer(Object.class, dtcr); // 设置表格内容居中对齐
			trainDetailTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // 关闭自动根据内容缩放
			trainDetailTable.getTableHeader().setFont(font1);// 设置表头字体
			trainDetailTable.setFont(font2); // 设置单元格字体
			trainDetailTable.setRowHeight(25); // 设置行高
			trainDetailTable.getTableHeader().setResizingAllowed(false); // 设置列宽不可变动
			trainDetailTable.getTableHeader().setReorderingAllowed(false); // 设置列的顺序不可变动
			setColumnWidthsForTrainDetail(); // 设置列宽
			trainDetailScrollPanel.setBounds(5, 5, 590, 390); // 设置滚动面板大小和位置
			trainDetailScrollPanel.getVerticalScrollBar().setValue(0);
			trainDetailDialog.add(trainDetailScrollPanel);
			trainDetailDialog.setTitle(trainNo + "次（" + firstSta + "—" + lastSta + "） " + Utility.trainType(trainNo));
			trainDetailDialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
	
	// 显示正晚点信息
	public void showDelayInfo() {
		int col = trainDetailTable.getSelectedColumn(); // 获取表格被选中单元格的列标
		if (col == 5) { // 如果被选中单元格的列标为5（说明点击的是正晚点信息）
			int row = trainDetailTable.getSelectedRow(); // 获取被选中单元格的行标
			if (row > 0) {
				// 限定行标大于0，防止点击始发站导致始发站正晚点信息一列的文字改变
				trainDetailTable.setValueAt("查询中，请稍候……", row, col);
				String[] dialogTitle = trainDetailDialog.getTitle().split("次");
				String trainNo = dialogTitle[0];
				String stationName = (String) trainDetailTable.getValueAt(row, 1);
				String punctualTime = (String) trainDetailTable.getValueAt(row, 2);
				String delayInfo = Utility.getDelayInfo(trainNo, stationName, punctualTime);
				trainDetailTable.setValueAt(delayInfo, row, col);
			}
		}
	}
	
	// 设置站站查询表格每列的列宽
	public void setColumnWidthsForSTSQuery() {
		int[] columnWidths = { 65, 90, 75, 90, 75, 60, 175 };
		for (int i = 0; i < 7; i++) {
			resultTable1.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
		}
		for (int i = 7; i < 17; i++) {
			resultTable1.getColumnModel().getColumn(i).setPreferredWidth(52);
		}
		resultTable1.getColumnModel().getColumn(17).setPreferredWidth(113);
	}
	
	// 设置中转查询各表格每列的列宽
	public void setColumnWidthsForTRQuery() {
		int[] columnWidths = { 65, 90, 75, 90, 75, 60, 175 };
		for (int i = 0; i < 7; i++) {
			resultTable2.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
			resultTable3.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
		}
		for (int i = 7; i < 17; i++) {
			resultTable2.getColumnModel().getColumn(i).setPreferredWidth(52);
			resultTable3.getColumnModel().getColumn(i).setPreferredWidth(52);
		}
		resultTable2.getColumnModel().getColumn(17).setPreferredWidth(112);
		resultTable3.getColumnModel().getColumn(17).setPreferredWidth(112);
	}
	
	// 设置经停站信息表格每列的列宽
	public void setColumnWidthsForTrainDetail() {
		int[] columnWidths = { 65, 90, 80, 80, 80, 200 };
		for (int i = 0; i < 6; i++) {
			trainDetailTable.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
		}
	}

}
