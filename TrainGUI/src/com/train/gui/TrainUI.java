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
	
	JPanel stationToStationPanel = new JPanel(); // վվ��ѯ���
	JPanel transferPanel = new JPanel(); // ��ת��ѯ���
	JPanel pricePanel = new JPanel(); // Ʊ�۲�ѯ���
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP); // ѡ�
	Font font1 = new Font("Microsoft YaHei", Font.PLAIN, 16); // ΢���ź�16������
	Font font2 = new Font(null, Font.PLAIN, 16); // ������+Arial��16������
	DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // Ĭ�ϵ�Ԫ����Ⱦ��
	Calendar ca = Calendar.getInstance(); // ��������
	
	// վվ��ѯ�����������
	JLabel fromStationLabel1 = new JLabel("����վ��"); // ����վ��ǩ
	JLabel toStationLabel1 = new JLabel("����վ��"); // ����վ��ǩ
	JLabel dateLabel1 = new JLabel("��ѯ���ڣ�"); // ��ѯ���ڱ�ǩ
	JLabel dateValueLabel1 = new JLabel(Utility.dateToString(ca)); // ���ڱ�ǩ
	JTextField fromStationText1 = new JTextField(7); // ����վ�ı���
	JTextField toStationText1 = new JTextField(7); // ����վ�ı���
	JButton returnbackButton1 = new JButton("����"); // ���̰�ť
	JButton loginButton = new JButton("��¼12306�˺�"); // ��¼��ť
	// Ԥ���ڵ�1~30��
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
	JLabel queryInfo = new JLabel("��ʾ���ڲ�ѯ����е�����οɲ鿴��ͣվ��Ϣ��", JLabel.CENTER); // ��ѯ��Ϣ��ǩ(��ʾ�ж����˳�)
	JCheckBox gdc = new JCheckBox("G/D/C-����/����/�Ǽ�", true);
	JCheckBox kuaiSu = new JCheckBox("K-����", true);
	JCheckBox teKuai = new JCheckBox("T-�ؿ�", true);
	JCheckBox zhiDa = new JCheckBox("Z-ֱ��", true);
	JCheckBox qiTa = new JCheckBox("����", true);
	JLabel tips1 = new JLabel("˵��������Ϸ��������ڰ�ť��ֱ�Ӳ�ѯ��Ӧ���ڵĳ�����Ϣ��", JLabel.CENTER);
	String[][] trainInfo1 = null; // �����Ϣ
	// վվ��ѯ����������
	String[] columnName1 = { "����", "����վ", "����ʱ��", "����վ", "����ʱ��", "��ʱ", "��������", "����", "�ص�", "һ��", "����", "����", "����", "Ӳ��", "����", "Ӳ��", "����", "����" };
	DefaultTableModel tableModel1 = new DefaultTableModel(trainInfo1, columnName1) {
		// վվ��ѯ��ģ��
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row, int column) {
			return false; // �������е�Ԫ�񲻿��޸ģ���ͬ
		}
	};
	JTable resultTable1 = new JTable(tableModel1); // վվ��ѯ������
	JScrollPane scrollPanel1 = new JScrollPane(resultTable1); // �������
	
	// ��ת��ѯ�����������
	JLabel fromStationLabel2 = new JLabel("����վ��");
	JLabel transferStationLabel2 = new JLabel("��תվ��");
	JLabel toStationLabel2 = new JLabel("Ŀ��վ��");
	JLabel dateLabel2 = new JLabel("ѡ�����ڣ�");
	JLabel dateValueLabel2 = new JLabel(Utility.dateToString(ca));
	JTextField fromStationText2 = new JTextField(7);
	JTextField transferStationText2 = new JTextField(7);
	JTextField toStationText2 = new JTextField(7);
	JButton queryButton2 = new JButton("��ѯ");
	JLabel tipInfo = new JLabel("��ʾ���ڲ�ѯ����е�����οɲ鿴��ͣվ��Ϣ��", JLabel.CENTER);
	JLabel jlb2 = new JLabel("����վ����תվ������Ϣ", JLabel.CENTER);
	JLabel jlb3 = new JLabel("��תվ��Ŀ��վ������Ϣ", JLabel.CENTER);
	String[][] trainInfo2 = null;
	String[][] trainInfo3 = null;
	String[] columnName2 = { "����", "����վ", "����ʱ��", "��תվ", "����ʱ��", "��ʱ", "��������", "����", "�ص�", "һ��", "����", "����", "����", "Ӳ��", "����", "Ӳ��", "����", "��ע" };
	String[] columnName3 = { "����", "��תվ", "����ʱ��", "Ŀ��վ", "����ʱ��", "��ʱ", "��������", "����", "�ص�", "һ��", "����", "����", "����", "Ӳ��", "����", "Ӳ��", "����", "��ע" };
	DefaultTableModel tableModel2 = new DefaultTableModel(trainInfo2, columnName2) {
		// ����վ����תվ������Ϣ��ģ��
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	DefaultTableModel tableModel3 = new DefaultTableModel(trainInfo3, columnName3) {
		// ��תվ��Ŀ��վ������Ϣ��ģ��
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	JTable resultTable2 = new JTable(tableModel2);
	JScrollPane scrollPanel2 = new JScrollPane(resultTable2);
	JTable resultTable3 = new JTable(tableModel3);
	JScrollPane scrollPanel3 = new JScrollPane(resultTable3);
	
	// ѡ�����ڶԻ���
	JDialog dateSelectDialog = new JDialog(TrainUI.this, true); // ѡ�����ڶԻ���
	JLabel selectTips = new JLabel("ѡ�����ڣ�"); // ѡ�����ڱ�ǩ
	JComboBoxPlus<String> dateSelect = new JComboBoxPlus<String>(); // ����ѡ�������б�
	JButton okButton = new JButton("ȷ��"); // ȷ����ť(������ѡ�����ڶԻ���)
	
	// ������ʾ��
	JDialog errorDialog = new JDialog(TrainUI.this, true); // ������ʾ��(ģ̬)
	JLabel errorMsg = new JLabel(); // ������Ϣ
	JButton errorOkButton = new JButton("ȷ��"); // ȷ����ť(�����ڴ�����ʾ��)
	
	// ��������Ի���
	JDialog trainDetailDialog = new JDialog(TrainUI.this, false); // ��ģ̬
	String[][] trainDetailInfo = null;
	String[] trainDetailCol = { "վ��", "վ��", "����ʱ��", "����ʱ��", "ͣ��ʱ��", "�������Ϣ" };
	DefaultTableModel trainDetailTableModel = new DefaultTableModel(trainDetailInfo, trainDetailCol) {
		// ��ͣվ��Ϣ��ģ��
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	JTable trainDetailTable = new JTable(trainDetailTableModel); // ��ͣվ��Ϣ���
	JScrollPane trainDetailScrollPanel = new JScrollPane(trainDetailTable); // �������
	
	// ��¼��
	JDialog loginDialog = new JDialog(TrainUI.this, true); // ��¼��(ģ̬)
	JLabel yongHuMing = new JLabel("�û�����");
	JLabel miMa = new JLabel("���룺");
	JLabel yanZhengMa = new JLabel("��֤�룺");
	JTextField username = new JTextField(); // �û���
	JPasswordField password = new JPasswordField(); // ����
	JLabel verifyCodeImg = null; // ͼ����֤��
	JButton loginOkButton = new JButton("��¼");
	JButton refreshVerifyCodeButton = new JButton("ˢ����֤��");
	JLabel[] marks = new JLabel[8]; // �������֤��ͼƬ�ϵ�������µı��
	String captchaStr = ""; // ��¼�ӿ�URL�е���֤������ַ����������������꣩
	
	// �������������
	// components1��վվ��ѯ���
	Component[] components1 = { fromStationLabel1, fromStationText1, toStationLabel1, toStationText1, returnbackButton1, loginButton, queryInfo, dates1, dates2, dates3, dates4, dates5, dates6, dates7, dates8, dates9, dates10, dates11, dates12, dates13, dates14, dates15, dates16, dates17, dates18, dates19, dates20, dates21, dates22, dates23, dates24, dates25, dates26, dates27, dates28, dates29, dates30, gdc, kuaiSu, teKuai, zhiDa, qiTa, tips1, scrollPanel1 };
	// components2����ת��ѯ���
	Component[] components2 = { fromStationLabel2, fromStationText2, transferStationLabel2, transferStationText2, toStationLabel2, toStationText2, dateLabel2, dateValueLabel2, queryButton2, tipInfo, jlb2, scrollPanel2, jlb3, scrollPanel3 };
	
	// ���캯��
	public TrainUI() {
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		setTitle("�г�ʱ�̱��ѯϵͳ");
		setSize(1300, 700);
		setLocation(15, 15);
		setResizable(false); // ��������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addstationToStationPanel(); // Ϊվվ��ѯ����������
		addTransferPanel(); // Ϊ��ת��ѯ����������
		// Ϊ��������������¼�
		trainDetailTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent ev) {
				int col = trainDetailTable.getSelectedColumn(); // ��ȡ���ѡ�е�Ԫ����б�
				if (col == 5) { // �����ѡ�е�Ԫ����б�Ϊ5��˵����������������Ϣ��
					int row = trainDetailTable.getSelectedRow(); // ��ȡ��ѡ�е�Ԫ����б�
					if (row > 0) {
						// �޶��б����0����ֹ���ʼ��վ����ʼ��վ�������Ϣһ�е����ָı�
						trainDetailTable.setValueAt("��ѯ�У����Ժ򡭡�", row, col);
					}
				}
			}
			public void mouseClicked(MouseEvent ev) {
				showDelayInfo();
			}
		});
		tabbedPane.add(stationToStationPanel, "վվ��ѯ");
		tabbedPane.add(transferPanel, "��ת��ѯ");
		this.add(tabbedPane);
		setVisible(true);
	}
	
	// ��վվ��ѯ�����ӱ�Ҫ�İ�ť���ı���
	public void addstationToStationPanel() {
		resultTable1.setDefaultRenderer(Object.class, dtcr); // ���ñ�����ݾ��ж���
		resultTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // �ر��Զ�������������
		resultTable1.getTableHeader().setFont(font1);// ���ñ�ͷ����
		resultTable1.setFont(font2); // ���õ�Ԫ������
		resultTable1.setRowHeight(50); // �����и�
		resultTable1.getTableHeader().setResizingAllowed(false); // �����п��ɱ䶯
		resultTable1.getTableHeader().setReorderingAllowed(false); // �����е�˳�򲻿ɱ䶯
		/*
		 * column index - column name
		 * 0-����, 1-����վ, 2-����ʱ��, 3-����վ, 4-����ʱ��, 5-��ʱ
		 * 6-ʼ��վ, 7-�յ�վ, 8-������, 9-�ص���, 10-һ����, 11-������
		 * 12-�߼�����, 13-����, 14-Ӳ��, 15-����, 16-Ӳ��, 17-����
		 */
		setColumnWidthsForSTSQuery(); // ���ñ��ÿ�е��п�
		
		// ���ò��ֹ�������
		stationToStationPanel.setLayout(null);
		
		// ��ÿ������Լ���λ��
		components1[0].setBounds(10, 5, 65, 30); // ����վ��ǩ
		components1[1].setBounds(75, 5, 90, 30); // ����վ�ı���
		components1[2].setBounds(180, 5, 65, 30); // ����վ��ǩ
		components1[3].setBounds(245, 5, 90, 30); // ����վ�ı���
		components1[4].setBounds(350, 5, 70, 30); // ���̰�ť
		components1[5].setBounds(435, 5, 180, 30); // ��¼��ť
		components1[6].setBounds(635, 5, 560, 30); // ��Ϣ��ǩ
		// Ԥ����1~30(components1[7]~components1[36])
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
		// 5����ѡ��(components1[37]~components1[41])
		components1[37].setBounds(20, 160, 210, 30); // G/D/C
		components1[38].setBounds(230, 160, 100, 30); // K
		components1[39].setBounds(330, 160, 100, 30); // T
		components1[40].setBounds(430, 160, 100, 30); // Z
		components1[41].setBounds(530, 160, 100, 30); // ����
		components1[42].setBounds(680, 160, 450, 30); // ��ʾ��Ϣ
		components1[43].setBounds(5, 190, 1281, 447); // ������ڹ������
		
		// �������
		String monthdate = null;
		String[] weeks0 = { "��", "һ", "��", "��", "��", "��", "��" };
		for (int x = 7; x < 37; x++) {
			components1[x].setFont(font1);
			monthdate = Utility.dateToString(ca).substring(5);
			((JButton) components1[x]).setName(Utility.dateToString(ca));
			((JButton) components1[x]).setText("<html>" + monthdate + "<br>(��" + weeks0[ca.get(Calendar.DAY_OF_WEEK) - 1] + ")</html>");
			((JButton) components1[x]).setMargin(new Insets(0, 0, 0, 0));
			ca.add(Calendar.DATE, 1);
		}
		for (int k = 37; k < 42; k++) {
			((JCheckBox) components1[k]).setEnabled(false);
		}
		
		// ��ʽ������
		for (int i = 0; i < components1.length; i++) {
			components1[i].setFont(font1);
			stationToStationPanel.add(components1[i]);
		}
		
		// Ϊ���̰�ť����¼�
		((JButton) components1[4]).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String departureSt = ((JTextField) components1[1]).getText();
				String arrivalSt = ((JTextField) components1[3]).getText();
				((JTextField) components1[1]).setText(arrivalSt);
				((JTextField) components1[3]).setText(departureSt);
			}
		});
		
		// Ϊ��¼��ť����¼�
		((JButton) components1[5]).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showLoginDialog();
			}
		});
		
		// Ϊ30��Ԥ���ڰ�ť����¼�
		for (int n = 7; n < 37; n++) {
			((JButton) components1[n]).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// һ���Խ���������Ի���λ�úʹ�С���úã�����ÿ��λ�ö��仯
					// ���ִ���µ�һ�β�ѯǰ��������Ի����ڴ�״̬�����Ƚ���ر�
					trainDetailDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					trainDetailDialog.dispose();
					trainDetailDialog.setSize(620, 400);
					trainDetailDialog.setLocation(355, 223);
					trainDetailDialog.setResizable(false); // ��������
					errorDialog.setTitle("����");
					errorDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					errorDialog.setSize(400, 150);
					errorDialog.setLocation(500, 300);
					errorDialog.setResizable(false); // ��������
					// ��5����ѡ��ȫ������Ϊѡ��״̬
					for (int x = 37; x < 42; x++) {
						((JCheckBox) components1[x]).setSelected(true);
					}
					// Ϊ������ʾ���ȷ����ť����¼�
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
						errorMsg.setText("����վ�͵���վ������Ϊ��!");
						showErrorTips();
					}
					else if (fromStaCode.equals("null") || toStaCode.equals("null")) {
						errorMsg.setText("����վ�򵽴�վ����Ƿ�������������!");
						showErrorTips();
						((JTextField) components1[1]).setText("");
						((JTextField) components1[3]).setText("");
					}
					else if (fromSta.equals(toSta)) {
						errorMsg.setText("����վ�͵���վ������ͬ!");
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
							// ���Ը�����URL��ѯ������ѯ����������Զ��ı�URLֱ���ܲ�ѯ�����Ϊֹ
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
							// Ϊ��ֹ�п�仯���˴�Ӧ�ٴ�����һ���п�
							setColumnWidthsForSTSQuery();
							// ������Ϣ��ǩ����
							String queryMsg = "";
							if (trainData == null) {
								queryMsg = "�� " + fromSta + " �� " + toSta + " δ��ѯ��������Ϣ!";
								((JLabel) components1[6]).setText(queryMsg);
								((JLabel) components1[42]).setText("ɸѡ���� 0 ������");
								((JLabel) components1[6]).setForeground(new Color(255, 0, 0));
								errorMsg.setText("δ��ѯ��������Ϣ������Ĳ�ѯ���ڻ�����ת��ѯ��");
								showErrorTips();
							}
							else {
								Calendar calendar0 = Utility.parseToCalendar(dateStr); // ����������
								// ��ȡ�����ռ�����
								int yearA = calendar0.get(Calendar.YEAR);
								int monthA = calendar0.get(Calendar.MONTH) + 1;
								int dayA = calendar0.get(Calendar.DATE);
								int weekA = calendar0.get(Calendar.DAY_OF_WEEK) - 1;
								String[] weeks = { "��", "һ", "��", "��", "��", "��", "��" };
								// ƴ�ӳɲ�ѯ��Ϣ
								queryMsg = yearA + "��" + monthA + "��" + dayA + "�գ���" + weeks[weekA]
										+ "������ " + fromSta + " �� " + toSta + " ����ѯ�� " + trainData.length + " ������";
								((JLabel) components1[6]).setText(queryMsg);
								((JLabel) components1[42]).setText("ɸѡ���� "+ trainData.length +" ������");
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
		
		// Ϊ5����ѡ������¼�(components1[37]~components1[41])
		((JCheckBox) components1[37]).addItemListener(new ItemListener() {
			// G/D/C
			@Override
			public void itemStateChanged(ItemEvent e) {
				JCheckBox checkBox = (JCheckBox) e.getItem();
				// ÿ�θ�ѡ��״̬�����ı�ʱ��Ҫ��ȡһ�α���е�����
				if (checkBox.isSelected()) { // ��ԭ��������Ӹ���/����/�Ǽ��г���Ϣ
					int cnt = 0;
					String[][] resultGDC = Utility.getTrainDataByType(trainInfo1, "GDC");
					// ����������µ�if�жϣ����򵱱��Ϊ��ʱ�޷�����������
					if (tableModel1.getRowCount() > 0) {
						for (int k = 0; k < tableModel1.getRowCount(); k++) {
							if (cnt >= resultGDC.length) break;
							// ��֤��������԰�����ʱ��˳������
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
					// �������ִ��һ��ɾ���ظ��еĲ��������򵯳�������ʾ���ڱ���г����ظ���
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
					if (flag) { // ��֤����Ҫ��һ����ѡ����ѡ��״̬
						errorMsg.setText("������ѡ��һ�����ͣ�");
						showErrorTips();
					}
					else {
						// ɾ��ԭ�����еĸ���/����/�Ǽ��г���Ϣ
						// ע�⣺ɾ�������ʱ�б�k�����ǵݼ��ģ�
						for (int k = tableModel1.getRowCount() - 1; k >= 0; k--) {
							if (((String) tableModel1.getValueAt(k, 0)).charAt(9) == 'G'
							|| ((String) tableModel1.getValueAt(k, 0)).charAt(9) == 'D'
							|| ((String) tableModel1.getValueAt(k, 0)).charAt(9) == 'C') {
								tableModel1.removeRow(k);
							}
						}
					}	
				}
				((JLabel) components1[42]).setText("ɸѡ���� " + tableModel1.getRowCount() + " ������");
			}
		});
		
		((JCheckBox) components1[38]).addItemListener(new ItemListener() {
			// K
			@Override
			public void itemStateChanged(ItemEvent e) {
				JCheckBox checkBox = (JCheckBox) e.getItem();
				// ÿ�θ�ѡ��״̬�����ı�ʱ��Ҫ��ȡһ�α���е�����
				if (checkBox.isSelected()) { // ��ԭ��������ӿ����г���Ϣ
					int cnt = 0;
					String[][] resultK = Utility.getTrainDataByType(trainInfo1, "K");
					// ����������µ�if�жϣ����򵱱��Ϊ��ʱ�޷�����������
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
					// �������ִ��һ��ɾ���ظ��еĲ��������򵯳�������ʾ���ڱ���г����ظ���
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
					if (flag) { // ��֤����Ҫ��һ����ѡ����ѡ��״̬
						errorMsg.setText("������ѡ��һ�����ͣ�");
						showErrorTips();
					}
					else {
						// ɾ��ԭ�����еĿ����г���Ϣ
						// ע�⣺ɾ�������ʱ�б�k�����ǵݼ��ģ�
						for (int k = tableModel1.getRowCount() - 1; k >= 0; k--) {
							if (((String) tableModel1.getValueAt(k, 0)).charAt(9) == 'K') {
								tableModel1.removeRow(k);
							}
						}
					}
				}
				((JLabel) components1[42]).setText("ɸѡ���� " + tableModel1.getRowCount() + " ������");
			}
		});
		((JCheckBox) components1[39]).addItemListener(new ItemListener() {
			// T
			@Override
			public void itemStateChanged(ItemEvent e) {
				JCheckBox checkBox = (JCheckBox) e.getItem();
				// ÿ�θ�ѡ��״̬�����ı�ʱ��Ҫ��ȡһ�α���е�����
				if (checkBox.isSelected()) { // ��ԭ����������ؿ��г���Ϣ
					int cnt = 0;
					String[][] resultT = Utility.getTrainDataByType(trainInfo1, "T");
					// ����������µ�if�жϣ����򵱱��Ϊ��ʱ�޷�����������
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
					// �������ִ��һ��ɾ���ظ��еĲ��������򵯳�������ʾ���ڱ���г����ظ���
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
					if (flag) { // ��֤����Ҫ��һ����ѡ����ѡ��״̬
						errorMsg.setText("������ѡ��һ�����ͣ�");
						showErrorTips();
					}
					else {
						// ɾ��ԭ�����е��ؿ��г���Ϣ
						// ע�⣺ɾ�������ʱ�б�k�����ǵݼ��ģ�
						for (int k = tableModel1.getRowCount() - 1; k >= 0; k--) {
							if (((String) tableModel1.getValueAt(k, 0)).charAt(9) == 'T') {
								tableModel1.removeRow(k);
							}
						}
					}
				}
				((JLabel) components1[42]).setText("ɸѡ���� " + tableModel1.getRowCount() + " ������");
			}
		});
		((JCheckBox) components1[40]).addItemListener(new ItemListener() {
			// Z
			@Override
			public void itemStateChanged(ItemEvent e) {
				JCheckBox checkBox = (JCheckBox) e.getItem();
				// ÿ�θ�ѡ��״̬�����ı�ʱ��Ҫ��ȡһ�α���е�����
				if (checkBox.isSelected()) { // ��ԭ���������ֱ���г���Ϣ
					int cnt = 0;
					// ����������µ�if�жϣ����򵱱��Ϊ��ʱ�޷�����������
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
					// �������ִ��һ��ɾ���ظ��еĲ��������򵯳�������ʾ���ڱ���г����ظ���
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
					if (flag) { // ��֤����Ҫ��һ����ѡ����ѡ��״̬
						errorMsg.setText("������ѡ��һ�����ͣ�");
						showErrorTips();
					}
					else {
						// ɾ��ԭ�����е�ֱ���г���Ϣ
						// ע�⣺ɾ�������ʱ�б�k�����ǵݼ��ģ�
						for (int k = tableModel1.getRowCount() - 1; k >= 0; k--) {
							if (((String) tableModel1.getValueAt(k, 0)).charAt(9) == 'Z') {
								tableModel1.removeRow(k);
							}
						}
					}
				}
				((JLabel) components1[42]).setText("ɸѡ���� " + tableModel1.getRowCount() + " ������");
			}
		});
		((JCheckBox) components1[41]).addItemListener(new ItemListener() {
			// ��������
			@Override
			public void itemStateChanged(ItemEvent e) {
				JCheckBox checkBox = (JCheckBox) e.getItem();
				// ÿ�θ�ѡ��״̬�����ı�ʱ��Ҫ��ȡһ�α���е�����
				if (checkBox.isSelected()) { // ��ԭ������������������г���Ϣ
					int cnt = 0;
					String[][] resultOther = Utility.getTrainDataByType(trainInfo1, "other");
					// ����������µ�if�жϣ����򵱱��Ϊ��ʱ�޷�����������
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
					// �������ִ��һ��ɾ���ظ��еĲ��������򵯳�������ʾ���ڱ���г����ظ���
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
					if (flag) { // ��֤����Ҫ��һ����ѡ����ѡ��״̬
						errorMsg.setText("������ѡ��һ�����ͣ�");
						showErrorTips();
					}
					else {
						// ɾ��ԭ�����е����������г���Ϣ
						// ע�⣺ɾ�������ʱ�б�k�����ǵݼ��ģ�
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
				((JLabel) components1[42]).setText("ɸѡ���� " + tableModel1.getRowCount() + " ������");
			}
		});
		
		// Ϊ��ѯ����������¼�
		resultTable1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ev) {
				int col = resultTable1.getSelectedColumn(); // ��ȡ���ѡ�е�Ԫ����б�
				if (col == 0) { // �����ѡ�е�Ԫ����б�Ϊ0��˵��������ǳ��Σ�
					int row = resultTable1.getSelectedRow(); // ��ȡ��ѡ�е�Ԫ����б�
					String trainNo = (String) resultTable1.getValueAt(row, col);
					displayTrainDetail(Utility.deleteSpecificHtmlTags(trainNo), ev);
				}
			}
		});
		
	}
	
	// ����ת��ѯ�����ӱ�Ҫ�İ�ť���ı���
	public void addTransferPanel() {
		
		// ����ʼ��վ����תվ������Ϣ���
		resultTable2.setDefaultRenderer(Object.class, dtcr);
		resultTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resultTable2.getTableHeader().setFont(font1);
		resultTable2.setFont(font2);
		resultTable2.setRowHeight(50);
		resultTable2.getTableHeader().setResizingAllowed(false);
		resultTable2.getTableHeader().setReorderingAllowed(false);
		
		// ������תվ��Ŀ��վ������Ϣ���
		resultTable3.setDefaultRenderer(Object.class, dtcr);
		resultTable3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resultTable3.getTableHeader().setFont(font1);
		resultTable3.setFont(font2);
		resultTable3.setRowHeight(50);
		resultTable3.getTableHeader().setResizingAllowed(false);
		resultTable3.getTableHeader().setReorderingAllowed(false);
		
		// ������ת��ѯ���Ĳ��ֹ�����
		transferPanel.setLayout(null);
		
		// �������������п�
		setColumnWidthsForTRQuery();
		
		// ��λ�������
		components2[0].setBounds(5, 5, 65, 30); // ����վ��ǩ
		components2[1].setBounds(70, 5, 90, 30); // ����վ�ı���
		components2[2].setBounds(175, 5, 65, 30); // ��תվ��ǩ
		components2[3].setBounds(240, 5, 90, 30); // ��תվ�ı���
		components2[4].setBounds(345, 5, 65, 30); // ����վ��ǩ
		components2[5].setBounds(410, 5, 90, 30); // ����վ�ı���
		components2[6].setBounds(515, 5, 80, 30); // ѡ�����ڱ�ǩ
		components2[7].setBounds(600, 5, 120, 30); // ���ڱ�ǩ
		components2[8].setBounds(705, 5, 70, 30); // ��ѯ��ť
		components2[9].setBounds(795, 5, 390, 30); // ��Ϣ��ǩ
		components2[10].setBounds(5, 40, 1180, 24); // ����վ->��תվ��ǩ
		components2[11].setBounds(5, 65, 1280, 274); // ����վ->��תվ���������
		components2[12].setBounds(5, 340, 1180, 24); // ��תվ->����վ��ǩ
		components2[13].setBounds(5, 365, 1280, 274); // ��תվ->����վ���������
		
		// ���������ʽ
		components2[7].setForeground(new Color(0, 0, 255)); // �����ڱ�ǩ��������ɫ��Ϊ��ɫ
		components2[10].setForeground(new Color(128, 160, 64)); // ���ó���վ->��תվ��ǩ������ɫ
		components2[12].setForeground(new Color(128, 160, 64)); // ������תվ->����վ��ǩ������ɫ
		
		// Ϊ��ת��ѯ���������
		for (int i = 0; i < components2.length; i++) {
			components2[i].setFont(font1);
			transferPanel.add(components2[i]);
		}
		
		// Ϊ���ڱ�ǩ(����ѡ�����ڱ�ǩ)����¼�
		((JLabel) components2[7]).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ev) {
				showDateSelectDialog(ev);
			}
		});
		
		// Ϊ��ѯ��ť����¼�
		((JButton) components2[8]).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// һ���Խ���������Ի���λ�úʹ�С���úã�����ÿ��λ�ö��仯
				// ���ִ���µ�һ�β�ѯǰ��������Ի����ڴ�״̬�����Ƚ���ر�
				trainDetailDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				trainDetailDialog.setVisible(false);
				trainDetailDialog.setSize(620, 400);
				trainDetailDialog.setLocation(355, 223);
				trainDetailDialog.setResizable(false); // ��������
				errorDialog.setTitle("����");
				errorDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				errorDialog.setSize(400, 150);
				errorDialog.setLocation(500, 300);
				errorDialog.setResizable(false); // ��������
				// Ϊ������ʾ���ȷ����ť����¼�
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
					errorMsg.setText("����վ����תվ��Ŀ��վ������Ϊ��!");
					showErrorTips();
				}
				else if (fromStaCode.equals("null") || tranStaCode.equals("null") || toStaCode.equals("null")) {
					errorMsg.setText("����վ����תվ��Ŀ��վ����Ƿ�������������!");
					showErrorTips();
					((JTextField) components2[1]).setText("");
					((JTextField) components2[3]).setText("");
					((JTextField) components2[5]).setText("");
				}
				else if (fromSta.equals(toSta)) {
					errorMsg.setText("����վ��Ŀ��վ������ͬ!");
					showErrorTips();
				}
				else if (fromSta.equals(tranSta)) {
					errorMsg.setText("����վ����תվ������ͬ!");
					showErrorTips();
				}
				else if (tranSta.equals(toSta)) {
					errorMsg.setText("��תվ��Ŀ��վ������ͬ!");
					showErrorTips();
				}
				else if (fromSta.equals(toSta)) {
					errorMsg.setText("����վ�͵���վ������ͬ!");
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
							((JLabel) components2[10]).setText("1. " + fromSta + "->" + tranSta + " ��" + trainData1.length +"�����Σ�");
							((JLabel) components2[12]).setText("2. " + tranSta + "->" + toSta + " ��" + trainData2.length +"�����Σ�");
							tableModel2.setDataVector(trainData1, columnName2);
							tableModel3.setDataVector(trainData2, columnName3);
						}
						// Ϊ��ֹ�п�仯���˴�Ӧ�ٴ�����һ���п�
						setColumnWidthsForTRQuery();
						// ������Ϣ��ǩ����
						String queryMsg = "";
						if (trainData1 == null && trainData2 != null) {
							queryMsg = "�� " + fromSta + " �� " + tranSta + " δ��ѯ��������Ϣ!";
							((JLabel) components2[9]).setText(queryMsg);
							((JLabel) components2[9]).setForeground(new Color(255, 0, 0));
							errorMsg.setText("δ��ѯ��������Ϣ������Ĳ�ѯ���ڻ������תվ�����ԣ�");
							showErrorTips();
						}
						else if (trainData1 != null && trainData2 == null) {
							queryMsg = "�� " + tranSta + " �� " + toSta + " δ��ѯ��������Ϣ!";
							((JLabel) components2[9]).setText(queryMsg);
							((JLabel) components2[9]).setForeground(new Color(255, 0, 0));
							errorMsg.setText("δ��ѯ��������Ϣ������Ĳ�ѯ���ڻ������תվ�����ԣ�");
							showErrorTips();
						}
						else if (trainData1 == null && trainData2 == null) {
							queryMsg = "�� " + fromSta + " �� " + toSta + " δ��ѯ����ת��Ϣ!";
							((JLabel) components2[9]).setText(queryMsg);
							((JLabel) components2[9]).setForeground(new Color(255, 0, 0));
							errorMsg.setText("δ��ѯ��������Ϣ������Ĳ�ѯ���ڻ������תվ�����ԣ�");
							showErrorTips();
						}
						else {
							String dt = ((JLabel) components2[7]).getText(); // ��ȡ���ڱ�ǩ���ı�
							Calendar calendar0 = Utility.parseToCalendar(dt); // ����������
							// ��ȡ�����ռ�����
							int yearA = calendar0.get(Calendar.YEAR);
							int monthA = calendar0.get(Calendar.MONTH) + 1;
							int dayA = calendar0.get(Calendar.DATE);
							int weekA = calendar0.get(Calendar.DAY_OF_WEEK) - 1;
							String[] weeks = { "��", "һ", "��", "��", "��", "��", "��" };
							// ƴ�ӳɲ�ѯ��Ϣ
							queryMsg = "��ѯ���ڣ�" + yearA + "��" + monthA + "��" + dayA + "�գ���" + weeks[weekA] + "��";
							((JLabel) components2[9]).setText(queryMsg);
							((JLabel) components2[9]).setForeground(new Color(30, 150, 80));
						}
						
					} catch (MalformedURLException e1) {
						System.out.println("Ooops! An unexpected MalformedURLException has occured!");
					}
				}
				
			}
		});
		
		// Ϊ����վ����תվ��ѯ����������¼�
		resultTable2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ev) {
				int col = resultTable2.getSelectedColumn(); // ��ȡ���ѡ�е�Ԫ����б�
				if (col == 0) { // �����ѡ�е�Ԫ����б�Ϊ0��˵��������ǳ��Σ�
					int row = resultTable2.getSelectedRow(); // ��ȡ��ѡ�е�Ԫ����б�
					String trainNo = (String) resultTable2.getValueAt(row, col);
					displayTrainDetail(Utility.deleteSpecificHtmlTags(trainNo), ev);
				}
			}
		});
		
		// Ϊ��תվ��Ŀ��վ��ѯ����������¼�
		resultTable3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ev) {
				int col = resultTable3.getSelectedColumn(); // ��ȡ���ѡ�е�Ԫ����б�
				if (col == 0) { // �����ѡ�е�Ԫ����б�Ϊ0��˵��������ǳ��Σ�
					int row = resultTable3.getSelectedRow(); // ��ȡ��ѡ�е�Ԫ����б�
					String trainNo = (String) resultTable3.getValueAt(row, col);
					displayTrainDetail(Utility.deleteSpecificHtmlTags(trainNo), ev);
				}
			}
		});
	}
	
	// ��ʾ��¼�Ի���
	public void showLoginDialog() {
		loginDialog.setTitle("��¼12306�˺�");
		loginDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // ����Ĭ�Ϲر���Ϊ
		loginDialog.setResizable(false); // ��������
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
		// Ϊͼ����֤����������¼�
		verifyCodeImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				int xPosition = ev.getX();
				int yPosition = ev.getY();
				if (xPosition >= 2 && xPosition <= 289 && yPosition >= 38 && yPosition <= 181) {
					int count = 0;
					for (count = 0; count < 8; count++) {
						// ��鵱ǰ������ǵڼ���ͼƬ
						if (Utility.isCooldinateInCaptcha(xPosition, yPosition, count + 1)) break;
					}
					marks[count].setBounds(xPosition + 94, yPosition + 85, 24, 24);
					marks[count].setVisible(true);
				}
			}
		});
		// Ϊ��֤��������������¼�
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
		// Ϊ��¼��ť��ӵ���¼�
		loginOkButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				for (int i = 0; i < marks.length; i++) {
					if (marks[i].isVisible()) {
						int xi = marks[i].getX() - 94;
						int yi = marks[i].getY() - 116;
						// ��֤���ַ���
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
		// Ϊˢ����֤�밴ť����¼�
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
	
	// ��ʾѡ�����ڶԻ���
	public void showDateSelectDialog(MouseEvent ev) {
		dateSelectDialog.setTitle("ѡ������");
		dateSelectDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		dateSelectDialog.setSize(300, 150);
		dateSelectDialog.setLocation(550, 300);
		dateSelectDialog.setResizable(false);
		// �ӵ��쿪ʼ��Ӻ�29�������
		Calendar todayCalendar = Calendar.getInstance();
		String today = Utility.dateToString(todayCalendar); // ��������
		// ���û�е������ڣ�����ӵ������ڣ�������Ŀ�ظ�
		if (!dateSelect.isContainsItem(today)) {
			dateSelect.addItem(today);
		}
		for (int i = 0; i < 29; i++) {
			todayCalendar.add(Calendar.DATE, 1);
			String dates = Utility.dateToString(todayCalendar);
			// ���û��ָ�����ڣ������ָ�����ڣ�������Ŀ�ظ�
			if (!dateSelect.isContainsItem(dates)) {
				dateSelect.addItem(dates);
			}
		}
		// Ϊȷ����ť���¼�
		if (ev.getSource() == components2[7]) {  // ���������ת��ѯ�������ڱ�ǩ
			// ��ȡ���ڱ�ǩ������
			String labelDate = ((JLabel) components2[7]).getText();
			// �������ڱ�ǩ����õ���������Ϊ��ѡ��
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
		// ��ѡ�����ڱ�ǩ��ȷ����ť�������б����ӵ��Ի��򣬲����ø��ԵĴ�С��λ�ú�����
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
	
	// ��ʾ������ʾ��
	public void showErrorTips() {
		errorMsg.setBounds(1, 20, 345, 30);
		errorMsg.setHorizontalAlignment(JLabel.CENTER);
		errorOkButton.setBounds(158, 70, 80, 30);
		errorDialog.setLayout(null);
		errorDialog.add(errorMsg);
		errorDialog.add(errorOkButton);
		errorDialog.setVisible(true);
	}
	
	// ��ʾ���ξ�ͣվ��Ϣ
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
			+ "&purpose_codes=ADULT"; // վվ��ѯ����URL
		try {
			// ����ͬ����Ҫ�Զ�����URL
			String jsonStr = Utility.readUrlAsString(new URL(stsQueryURL), "UTF-8");
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(jsonStr);
			String c_url = (String) jsonObj.get("c_url");
			if (c_url == null) c_url = "leftTicket/query";
			stsQueryURL = "https://kyfw.12306.cn/otn/" + c_url + "?leftTicketDTO.train_date=" + dateStr
					+ "&leftTicketDTO.from_station=" + fromStaCode
					+ "&leftTicketDTO.to_station=" + toStaCode
					+ "&purpose_codes=ADULT";
			String[][] queryResult = Utility.jsonToTrainDetail(stsQueryURL, trainNo);
			trainDetailTableModel.setDataVector(queryResult, trainDetailCol); // �������滻Ϊ�µĲ�ѯ���
			String firstSta = queryResult[0][1]; // ʼ��վ
			String lastSta = queryResult[queryResult.length - 1][1]; // �յ�վ
			trainDetailTable.setDefaultRenderer(Object.class, dtcr); // ���ñ�����ݾ��ж���
			trainDetailTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // �ر��Զ�������������
			trainDetailTable.getTableHeader().setFont(font1);// ���ñ�ͷ����
			trainDetailTable.setFont(font2); // ���õ�Ԫ������
			trainDetailTable.setRowHeight(25); // �����и�
			trainDetailTable.getTableHeader().setResizingAllowed(false); // �����п��ɱ䶯
			trainDetailTable.getTableHeader().setReorderingAllowed(false); // �����е�˳�򲻿ɱ䶯
			setColumnWidthsForTrainDetail(); // �����п�
			trainDetailScrollPanel.setBounds(5, 5, 590, 390); // ���ù�������С��λ��
			trainDetailScrollPanel.getVerticalScrollBar().setValue(0);
			trainDetailDialog.add(trainDetailScrollPanel);
			trainDetailDialog.setTitle(trainNo + "�Σ�" + firstSta + "��" + lastSta + "�� " + Utility.trainType(trainNo));
			trainDetailDialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
	
	// ��ʾ�������Ϣ
	public void showDelayInfo() {
		int col = trainDetailTable.getSelectedColumn(); // ��ȡ���ѡ�е�Ԫ����б�
		if (col == 5) { // �����ѡ�е�Ԫ����б�Ϊ5��˵����������������Ϣ��
			int row = trainDetailTable.getSelectedRow(); // ��ȡ��ѡ�е�Ԫ����б�
			if (row > 0) {
				// �޶��б����0����ֹ���ʼ��վ����ʼ��վ�������Ϣһ�е����ָı�
				trainDetailTable.setValueAt("��ѯ�У����Ժ򡭡�", row, col);
				String[] dialogTitle = trainDetailDialog.getTitle().split("��");
				String trainNo = dialogTitle[0];
				String stationName = (String) trainDetailTable.getValueAt(row, 1);
				String punctualTime = (String) trainDetailTable.getValueAt(row, 2);
				String delayInfo = Utility.getDelayInfo(trainNo, stationName, punctualTime);
				trainDetailTable.setValueAt(delayInfo, row, col);
			}
		}
	}
	
	// ����վվ��ѯ���ÿ�е��п�
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
	
	// ������ת��ѯ�����ÿ�е��п�
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
	
	// ���þ�ͣվ��Ϣ���ÿ�е��п�
	public void setColumnWidthsForTrainDetail() {
		int[] columnWidths = { 65, 90, 80, 80, 80, 200 };
		for (int i = 0; i < 6; i++) {
			trainDetailTable.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
		}
	}

}
