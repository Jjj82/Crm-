package crm.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import crm.model.Region;
import crm.service.RegionService;

/**
 * 区域管理相关方法
 * 
 * @author 15056
 *
 */
@WebServlet("/RegionServlet")
public class RegionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegionServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Gson kson = new Gson();
		RegionService rs = new RegionService();
		String action = request.getParameter("action");
		Map<String, Object> map = new HashMap<String, Object>();
		//分页查询方法
		if (action.equals("sel")) {
			int curpage = Integer.parseInt(request.getParameter("page"));
			int pagesize = Integer.parseInt(request.getParameter("limit"));
			List<Region> csc = rs.selectAll(curpage, pagesize);
			int count = rs.selectAllCount();
			map.put("code", 0);
			map.put("msg", "查询完成");
			map.put("count", count);
			map.put("data", csc);
		} 
		//根据id删除的方法
		else if (action.equals("del")) {
			int id = Integer.parseInt(request.getParameter("id"));
			rs.del(id);
			map.put("code", 0);
			map.put("msg", "删除完成");
			map.put("count", 1);
			map.put("data", "");
		} 
		//更新数据方法
		else if (action.equals("upd")) {
			int id = Integer.parseInt(request.getParameter("id"));
			int customerid = Integer.parseInt(request.getParameter("customerid"));
			String name = request.getParameter("name");
			String charge = request.getParameter("charge");
			String telephone = request.getParameter("telephone");
			String date = request.getParameter("date");
			String remarks = request.getParameter("remarks");
			Region region = new Region();
			region.setId(id);
			region.setCustomerid(customerid);
			region.setName(name);
			region.setCharge(charge);
			region.setTelephone(telephone);
			region.setDate(date);
			region.setRemarks(remarks);
			rs.update(region);
			map.put("code", 0);
			map.put("msg", "修改完成");
			map.put("count", 1);
			map.put("data", "");
		} 
		//添加数据方法
		else if (action.equals("ins")) {
			int customerid = Integer.parseInt(request.getParameter("customerid"));
			String name = request.getParameter("name");
			String charge = request.getParameter("charge");
			String telephone = request.getParameter("telephone");
			String date = request.getParameter("date");
			String remarks = request.getParameter("remarks");
			Region region = new Region();
			region.setCustomerid(customerid);
			region.setName(name);
			region.setCharge(charge);
			region.setTelephone(telephone);
			region.setDate(date);
			region.setRemarks(remarks);
			rs.insert(region);
			map.put("code", 0);
			map.put("msg", "修改完成");
			map.put("count", 1);
			map.put("data", "");
		} 
		//根据条件查询
		else if (action.equals("selbyid")) {
			String condition = request.getParameter("condition");
			String key = new String(request.getParameter("key").getBytes("ISO8859-1"), "UTF-8");
			int curpage = Integer.parseInt(request.getParameter("page"));
			int pagesize = Integer.parseInt(request.getParameter("limit"));
			List<Region> region = rs.selectAll(condition, key, curpage, pagesize);
			int count = rs.selectAllCount(condition, key);
			map.put("code", 0);
			map.put("msg", "查询完成");
			map.put("count", count);
			map.put("data", region);
		}
		String wsx = kson.toJson(map);
		response.getWriter().write(wsx);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
