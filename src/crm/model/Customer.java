package crm.model;

/**
 * 客户实体类
 * @author 15056
 *
 */
public class Customer {

	private int id;//客户id
	private int ygid;//员工id
	private String name;//客户姓名
	private String telephone;//联系电话
	private String date;//日期
	private String state;//状态
	private String remarks;//备注

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getYgid() {
		return ygid;
	}

	public void setYgid(int ygid) {
		this.ygid = ygid;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", ygid=" + ygid + ", name=" + name + ", telephone=" + telephone + ", date="
				+ date + ", state=" + state + ", remarks=" + remarks + "]";
	}
}
