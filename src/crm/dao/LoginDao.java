package crm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import crm.model.Employee;
import crm.util.DBUtil;

public class LoginDao {

	/**
	 * 根据用户名查找
	 * @param name
	 * @return
	 */
	public Employee selectByName( String name) {
		String sql = "select * from employee where name = ?";
		Connection con = DBUtil.getConnection();
		Employee employee = new Employee();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			employee.setId(rs.getInt("id"));
			employee.setName(rs.getString("name"));
			employee.setPassword(rs.getString("password"));
			employee.setRole(rs.getString("role"));
			DBUtil.close(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}
}
