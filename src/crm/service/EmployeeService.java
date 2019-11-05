package crm.service;

import java.util.List;

import crm.dao.EmployeeDao;
import crm.model.Employee;

public class EmployeeService {
	EmployeeDao empdao=new EmployeeDao();
	
	/**
	 * 添加
	 * @param cus
	 */
	public void insert(Employee emp) {
		empdao.insert(emp);
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void del(int id) {
		empdao.del(id);
	}
	
	/**
	 * 根据id号修改
	 * @param cs
	 */
	public void update(Employee emp) {
		empdao.update(emp);
	}
	
	/**
	 * 查询全部数据
	 * @param curpage
	 * @param pagesize
	 * @return
	 */
	public List<Employee> select() {
		return empdao.select();
	}
	
	
	/**
	 * 分页查询全部数据
	 * @param curpage
	 * @param pagesize
	 * @return
	 */
	public List<Employee> selectAll(int curpage, int pagesize) {
		return empdao.selectAll(curpage, pagesize);
	}
	
	/**
	 * 按条件分页查询数据
	 * @param condition
	 * @param key
	 * @param curpage
	 * @param pagesize
	 * @return
	 */
	public List<Employee> selectAll( String condition,String key,int curpage, int pagesize) {
		return empdao.selectAll(condition, key, curpage, pagesize);
	}
	
	/**
	 * 查询所有的记录数
	 * 
	 * @return 条数
	 */
	public int selectAllCount() {
		return empdao.selectAllCount();
	}
	
	/**
	 * 根据条件查询记录数
	 * 
	 * @return 条数
	 */
	public int selectAllCount(String condition,String key) {
		return empdao.selectAllCount(condition, key);
	}
	
	
	
	/**
	 * 根据用户名查找
	 * @param name
	 * @return
	 */
	public Employee selectByName( String name,String password) {
		return empdao.selectByName(name,password);
	}
	
}
