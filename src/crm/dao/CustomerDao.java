package crm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import crm.model.Customer;
import crm.util.DBUtil;

public class CustomerDao {

	/**
	 * 插入数据
	 * 
	 * @param cus Ҫ��ӵĿͻ�����
	 * 
	 */
	public void insert(Customer cus) {

		Connection con = DBUtil.getConnection();
		String sql = "insert into customer(ygid,name,telephone,date,state,remarks) values(" + "?,?,?,?,?,?) ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cus.getYgid());
			ps.setString(2, cus.getName());
			ps.setString(3, cus.getTelephone());
			ps.setString(4, cus.getDate());
			ps.setString(5, cus.getState());
			ps.setString(6, cus.getRemarks());
			ps.execute();
			DBUtil.close(con);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	/**
	 * 根据id删除
	 * 
	 * @param id Ҫɾ����id
	 */

	public void del(int id) {

		String sql = "delete from Customer where id=?";
		Connection con = DBUtil.getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			DBUtil.close(con);
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}

	}

	/**
	 * �޸�
	 * 
	 * @param cs
	 */

	public void update(Customer cs) {
		String sql = "update customer set ygid=?,name=?,telephone=?,date=?,state=?,remarks=? where id=?";
		Connection con = DBUtil.getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);

			ps.setInt(1, cs.getYgid());
			ps.setString(2, cs.getName());
			ps.setString(3, cs.getTelephone());
			ps.setString(4, cs.getDate());
			ps.setString(5, cs.getState());
			ps.setString(6, cs.getRemarks());
			ps.setInt(7, cs.getId());
			ps.execute();
			DBUtil.close(con);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * ��id��ѯ
	 * 
	 * @param id
	 * @return ���ط���������
	 */
	public Customer selectid(int id) {
		Customer cus = new Customer();
		String sql = "select* from customer where id=?";
		Connection con = DBUtil.getConnection();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			rs.next();
			cus.setId(rs.getInt("id"));
			cus.setYgid(rs.getInt("ygid"));
			cus.setName(rs.getString("name"));
			cus.setTelephone(rs.getString("telephone"));
			cus.setDate(rs.getString("date"));
			cus.setState(rs.getString("state"));
			cus.setRemarks(rs.getString("remarks"));
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
			if (con != null)
				DBUtil.close(con);

		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cus;

	}

	/**
	 * 查询所有的记录数
	 * 
	 * @return 条数
	 */

	public int selectAllCount() {

		String sql = "select count(*) as c from customer";
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
		String sql = "select count(*) as c from customer where "+condition+" = ?";
		 
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
	 * ��ҳ��ѯ
	 * 
	 * @return list���м�¼�ļ���
	 */

	public List<Customer> selectAll(int curpage, int pagesize) {
		List<Customer> list = new ArrayList<Customer>();
		String sql = "select * from customer order by id limit ?,?";
		Connection con = DBUtil.getConnection();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, (curpage - 1) * pagesize);
			stmt.setInt(2, pagesize);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Customer cus = new Customer();
				cus.setId(rs.getInt("id"));
				cus.setYgid(rs.getInt("ygid"));
				cus.setName(rs.getString("name"));
				cus.setTelephone(rs.getString("telephone"));
				cus.setDate(rs.getString("date"));
				cus.setState(rs.getString("state"));
				cus.setRemarks(rs.getString("remarks"));
				list.add(cus);
			}
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
			if (con != null)
				DBUtil.close(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据条件分页查询数据
	 * 
	 * @return list���м�¼�ļ���
	 */
	public List<Customer> selectAll( String condition,String key,int curpage, int pagesize) {
		List<Customer> list = new ArrayList<Customer>();
		String sql = "select * from customer where "+condition+" = ? order by id limit ?,?";
		Connection con = DBUtil.getConnection();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, key);
			stmt.setInt(2, (curpage - 1) * pagesize);
			stmt.setInt(3, pagesize);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Customer cus = new Customer();
				cus.setId(rs.getInt("id"));
				cus.setYgid(rs.getInt("ygid"));
				cus.setName(rs.getString("name"));
				cus.setTelephone(rs.getString("telephone"));
				cus.setDate(rs.getString("date"));
				cus.setState(rs.getString("state"));
				cus.setRemarks(rs.getString("remarks"));
				list.add(cus);

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

}