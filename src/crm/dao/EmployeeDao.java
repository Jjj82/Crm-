package crm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm.model.Employee;
import crm.util.DBUtil;
import crm.util.MD5util;

public class EmployeeDao {
	
	
	/**
	 * 添加
	 * @param cus
	 */
	public void insert(Employee emp) {
		Connection con = DBUtil.getConnection();
		String sql = "insert into employee(name,gender,telephone,date,address,password,role,remarks) values(" + "?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, emp.getName());
			ps.setString(2, emp.getGender());
			ps.setString(3, emp.getTelephone());
			ps.setString(4, emp.getDate());
			ps.setString(5, emp.getAddress());
			ps.setString(6,MD5util.crypt(emp.getPassword()));
			ps.setString(7, emp.getRole());
			ps.setString(8, emp.getRemarks());
			ps.execute();
			DBUtil.close(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除
	 * @param id
	 */
	
	public void del(int id) {
		String sql = "delete from employee where id=?";
		Connection con = DBUtil.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			DBUtil.close(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 根据id号修改
	 * @param cs
	 */

	public void update(Employee emp) {
		String sql = "update employee set name=?,gender=?,telephone=?,date=?,address=?,password=?,role=?,remarks=? where id=?";
		Connection con = DBUtil.getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);

			ps.setString(1, emp.getName());
			ps.setString(2, emp.getGender());
			ps.setString(3, emp.getTelephone());
			ps.setString(4, emp.getDate());
			ps.setString(5, emp.getAddress());
			ps.setString(6, MD5util.crypt(emp.getPassword()));
			ps.setString(7, emp.getRole());
			ps.setString(8, emp.getRemarks());
			ps.setInt(9, emp.getId());
			ps.execute();
			DBUtil.close(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 分页查询全部数据
	 * @param curpage
	 * @param pagesize
	 * @return
	 */
	public List<Employee> selectAll(int curpage, int pagesize) {
		List<Employee> list = new ArrayList<Employee>();
		String sql = "select * from employee order by id limit ?,?";
		Connection con = DBUtil.getConnection();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, (curpage - 1) * pagesize);
			stmt.setInt(2, pagesize);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setId(rs.getInt("id"));
				employee.setName(rs.getString("name"));
				employee.setGender(rs.getString("gender"));
				employee.setTelephone(rs.getString("telephone"));
				employee.setDate(rs.getString("date"));
				employee.setPassword(rs.getString("password"));
				employee.setRole(rs.getString("role"));
				employee.setAddress(rs.getString("address"));
				employee.setRemarks(rs.getString("remarks"));
				list.add(employee);
			}
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
			if (con != null)
				DBUtil.close(con);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 查询全部数据
	 * @param curpage
	 * @param pagesize
	 * @return
	 */
	public List<Employee> select() {
		List<Employee> list = new ArrayList<Employee>();
		String sql = "select * from employee";
		Connection con = DBUtil.getConnection();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setId(rs.getInt("id"));
				employee.setName(rs.getString("name"));
				employee.setGender(rs.getString("gender"));
				employee.setTelephone(rs.getString("telephone"));
				employee.setDate(rs.getString("date"));
				employee.setPassword(rs.getString("password"));
				employee.setRole(rs.getString("role"));
				employee.setAddress(rs.getString("address"));
				employee.setRemarks(rs.getString("remarks"));
				list.add(employee);
			}
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
			if (con != null)
				DBUtil.close(con);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
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
		List<Employee> list = new ArrayList<Employee>();
		String sql = "select * from employee where "+condition+" = ? order by id limit ?,?";
		Connection con = DBUtil.getConnection();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, key);
			stmt.setInt(2, (curpage - 1) * pagesize);
			stmt.setInt(3, pagesize);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setId(rs.getInt("id"));
				employee.setName(rs.getString("name"));
				employee.setGender(rs.getString("gender"));
				employee.setTelephone(rs.getString("telephone"));
				employee.setDate(rs.getString("date"));
				employee.setAddress(rs.getString("address"));
				employee.setPassword(rs.getString("password"));
				employee.setRole(rs.getString("role"));
				employee.setRemarks(rs.getString("remarks"));
				
				list.add(employee);
			}
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
			if (con != null)
				DBUtil.close(con);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * 查询所有的记录数
	 * 
	 * @return 条数
	 */
	public int selectAllCount() {
		String sql = "select count(*) as c from Employee";
		Connection con = DBUtil.getConnection();
		PreparedStatement stmt;
		int count = 0;
		try {
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("c");
			}
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
			if (con != null)
				DBUtil.close(con);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 根据条件查询记录数
	 * 
	 * @return 条数
	 */
	public int selectAllCount(String condition,String key) {
		String sql = "select count(*) as c from employee where "+condition+" = ?";
		Connection con = DBUtil.getConnection();
		PreparedStatement stmt;
		int count = 0;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, key);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("c");
			}
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
			if (con != null)
				DBUtil.close(con);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	
	/**
	 * 根据用户名查找
	 * @param name
	 * @return
	 */
	public Employee selectByName( String name,String password) {
		String sql = "select * from employee where name = ? and password=?";
		Connection con = DBUtil.getConnection();
		Employee employee = new Employee();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			employee.setId(rs.getInt("id"));
			employee.setName(rs.getString("name"));
			employee.setGender(rs.getString("gender"));
			employee.setTelephone(rs.getString("telephone"));
			employee.setDate(rs.getString("date"));
			employee.setAddress(rs.getString("address"));
			employee.setPassword(MD5util.crypt(rs.getString("password")));
			employee.setRole(rs.getString("role"));
			employee.setRemarks(rs.getString("remarks"));
			}
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
			if (con != null)
				DBUtil.close(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employee;
	}
	
	
}
 