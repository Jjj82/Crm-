package crm.service;

import java.util.List;

import crm.dao.CustomerDao;
import crm.model.Customer;

/**
 * 客户管理service层
 * 
 * @author 15056
 *
 */
public class CustomerService {

	CustomerDao as = new CustomerDao();

	/**
	 * 添加客户
	 * 
	 * @param cus
	 */
	public void insert(Customer cus) {
		as.insert(cus);
	}

	/**
	 * 根据id删除
	 * 
	 * @param id
	 */
	public void del(int id) {
		as.del(id);
	}

	/**
	 * 编辑数据
	 * 
	 * @param cs
	 */
	public void update(Customer cs) {
		as.update(cs);
	}

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public Customer selectid(int id) {
		return as.selectid(id);
	}

	/**
	 * 查询所有条数
	 * 
	 * @return
	 */
	public int selectAllCount() {
		return as.selectAllCount();
	}

	/**
	 * 根据条件查询记录数
	 * 
	 * @return 条数
	 */
	public int selectAllCount(String condition, String key) {
		return as.selectAllCount(condition, key);
	}

	/**
	 * 分页查询所有数据
	 * 
	 * @param curpage
	 * @param pagesize
	 * @return
	 */
	public List<Customer> selectAll(int curpage, int pagesize) {
		return as.selectAll(curpage, pagesize);
	}

	/**
	 * 根据条件分页查询数据
	 * 
	 * @param condition
	 * @param key
	 * @param curpage
	 * @param pagesize
	 * @return
	 */
	public List<Customer> selectAll(String condition, String key, int curpage, int pagesize) {
		return as.selectAll(condition, key, curpage, pagesize);
	}

}
