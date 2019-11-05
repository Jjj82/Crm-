package crm.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import crm.model.Region;
import crm.util.DBUtil;

public class RegionDao {

	/**
	 * region������
	 * 
	 * @param cus
	 */
	public void insert(Region cus) {
		Connection con = DBUtil.getConnection();
		String sql = "insert into region(customerid,name,charge,telephone,date,remarks) values(" + "?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cus.getCustomerid());
			ps.setString(2, cus.getName());
			ps.setString(3, cus.getCharge());
			ps.setString(4, cus.getTelephone());
			ps.setDate(5, Date.valueOf(cus.getDate()));
			ps.setString(6, cus.getRemarks());
			ps.execute();
			DBUtil.close(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * region��ɾ������
	 * 
	 * @param id��Ҫɾ����id
	 */
	public void del(int id) {
		String sql = "delete from Region where id=?";
		Connection con = DBUtil.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			DBUtil.close(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * �޸ģ�
	 * 
	 * @param as���е�
	 */
	public void update(Region cs) {
		String sql = "update Region set customerid=?,name=?,charge=?,telephone=?,date=?,remarks=? where id=?";
		Connection con = DBUtil.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cs.getCustomerid());
			ps.setString(2, cs.getName());
			ps.setString(3, cs.getCharge());
			ps.setString(4, cs.getTelephone());
			ps.setDate(5, Date.valueOf(cs.getDate()));
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
	 * 根据id查询
	 * 
	 * @param id
	 * @return ��ѯ����
	 */
	public Region selectid(int id) {
		Region cus = new Region();
		String sql = "select * from Region where id=?";
		Connection con = DBUtil.getConnection();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			cus.setId(rs.getInt("id"));
			cus.setCustomerid(rs.getInt("customerid"));
			cus.setName(rs.getString("name"));
			cus.setTelephone(rs.getString("telephone"));
			cus.setDate(rs.getString("date"));
			cus.setCharge(rs.getString("charge"));
			cus.setRemarks(rs.getString("remarks"));
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
		return cus;
	}

	/**
	 * 查询所有的记录数
	 * 
	 * @return 条数
	 */
	public int selectAllCount() {
		String sql = "select count(*) as c from Region";
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
			// TODO Auto-generated catch block
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
		String sql = "select count(*) as c from Region where "+condition+" = ?";
		 
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	
	
	/**
	 * 分页查询全部数据
	 * 
	 * @return list���м�¼�ļ���
	 */

	public List<Region> selectAll(int curpage, int pagesize) {
		List<Region> list = new ArrayList<Region>();
		String sql = "select* from Region order by id limit ?,?";
		Connection con = DBUtil.getConnection();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, (curpage - 1) * pagesize);
			stmt.setInt(2, pagesize);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Region cus = new Region();
				cus.setId(rs.getInt("id"));
				cus.setCustomerid(rs.getInt("customerid"));
				cus.setName(rs.getString("name"));
				cus.setTelephone(rs.getString("telephone"));
				cus.setDate(rs.getString("date"));
				cus.setCharge(rs.getString("charge"));
				cus.setRemarks(rs.getString("remarks"));
				list.add(cus);

			}
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
		return list;

	}
	
	
	/**
	 * 根据条件分页查询数据
	 * 
	 * @return list���м�¼�ļ���
	 */
	public List<Region> selectAll( String condition,String key,int curpage, int pagesize) {
		List<Region> list = new ArrayList<Region>();
		String sql = "select * from Region where "+condition+" = ? order by customerid limit ?,?";
		Connection con = DBUtil.getConnection();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, key);
			stmt.setInt(2, (curpage - 1) * pagesize);
			stmt.setInt(3, pagesize);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Region cus = new Region();
				cus.setId(rs.getInt("id"));
				cus.setCustomerid(rs.getInt("customerid"));
				cus.setName(rs.getString("name"));
				cus.setTelephone(rs.getString("telephone"));
				cus.setDate(rs.getString("date"));
				cus.setCharge(rs.getString("charge"));
				cus.setRemarks(rs.getString("remarks"));
				list.add(cus);

			}
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
		return list;
	}
	

	
	
	

}
