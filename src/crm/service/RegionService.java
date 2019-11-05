package crm.service;

import java.util.List;
import crm.dao.RegionDao;
import crm.model.Region;

public class RegionService {

	RegionDao as = new RegionDao();

	/**
	 * 添加区域
	 * 
	 * @param cus
	 */
	public void insert(Region cus) {
		as.insert(cus);
	}

	/**
	 * 根据id删除区域
	 * 
	 * @param id
	 */
	public void del(int id) {
		as.del(id);
	}

	/**
	 * 编辑
	 * 
	 * @param cs
	 */
	public void update(Region cs) {
		as.update(cs);
	}

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public Region selectid(int id) {
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
	 * @param curpage
	 * @param pagesize
	 * @return
	 */
	public List<Region> selectAll(int curpage, int pagesize) {
		return as.selectAll(curpage, pagesize);
	}

	/**
	 * 根据条件查询数据
	 * @param condition
	 * @param key
	 * @param curpage
	 * @param pagesize
	 * @return
	 */
	public List<Region> selectAll(String condition, String key, int curpage, int pagesize) {
		return as.selectAll(condition, key, curpage, pagesize);
	}

}
