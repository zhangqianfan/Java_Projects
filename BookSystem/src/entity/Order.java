package entity;

public class Order {
	private Integer orderid;//������
	private Double totalpr;//�����ܽ��
	private Double paid;//ʵ�����
	private Integer userid;//�����û�ID
	private String receivername;//�ռ�������
	private String phone;//�ռ��˵绰
	private String address;//�ռ��˵�ַ
	private String paymethod;//֧����ʽ
	private String status;//״̬
	private String logistics;//������Ϣ
	private String exchanged;//�˻�����Ϣ
	private String ordertime;//�µ�ʱ��
	
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public Double getTotalpr() {
		return totalpr;
	}
	public void setTotalpr(Double totalpr) {
		this.totalpr = totalpr;
	}
	public Double getPaid() {
		return paid;
	}
	public void setPaid(Double paid) {
		this.paid = paid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getReceivername() {
		return receivername;
	}
	public void setReceivername(String receivername) {
		this.receivername = receivername;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPaymethod() {
		return paymethod;
	}
	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLogistics() {
		return logistics;
	}
	public void setLogistics(String logistics) {
		this.logistics = logistics;
	}
	public String getExchanged() {
		return exchanged;
	}
	public void setExchanged(String exchanged) {
		this.exchanged = exchanged;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	
}
