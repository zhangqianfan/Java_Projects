package entity;

public class Order {
	private Integer orderid;//订单号
	private Double totalpr;//订单总金额
	private Double paid;//实付金额
	private Integer userid;//所属用户ID
	private String receivername;//收件人姓名
	private String phone;//收件人电话
	private String address;//收件人地址
	private String paymethod;//支付方式
	private String status;//状态
	private String logistics;//物流信息
	private String exchanged;//退换货信息
	private String ordertime;//下单时间
	
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
