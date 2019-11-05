package crm.model;

/**
 * 区域实体类
 * 
 * @author 15056
 *
 */
public class Region {

	private int id;// 区域id
	private int customerid;// 客户id
	private String name;// 区域名
	private String charge;// 校区负责人
	private String telephone;// 联系电话
	private String date;// 日期
	private String remarks;// 备注

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	@Override
	public String toString() {
		return "Region [id=" + id + ", customerid=" + customerid + ", name=" + name + ", charge=" + charge
				+ ", telephone=" + telephone + ", date=" + date + ", remarks=" + remarks + "]";
	}

}
