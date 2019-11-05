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
import crm.model.Customer;
import crm.service.CustomerService;

/**
 * 客户管理Servlet
 * 
 * @author 15056
 *
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Gson gson = new Gson();
		//创建CustomerSercvice对象
		CustomerService cs = new CustomerService();
		//根据ajax获取的action执行对应的方法
		String action = request.getParameter("action");
		Map<String, Object> map = new HashMap<String, Object>();
		// 分页查询相关的方法
		if (action.equals("sel")) {
			// 获取ajax传过来的分页条件
			int curpage = Integer.parseInt(request.getParameter("page"));
			int pagesize = Integer.parseInt(request.getParameter("limit"));
			// 调用service层分页查询的方法
			List<Customer> list = cs.selectAll(curpage, pagesize);
			// 调用service层查询数据的总条数
			int count = cs.selectAllCount();
			//把数据放到map中
			map.put("code", 0);
			map.put("msg", "查询完成");
			map.put("count", count);
			map.put("data", list);
		} 
		//根据id删除相关的方法
		else if (action.equals("del")) {
			//获取id
			int id = Integer.parseInt(request.getParameter("id"));
			//把数据放到map中
			cs.del(id);
			map.put("code", 0);
			map.put("msg", "删除成功");
			map.put("count", 1);
			map.put("data", "");
		} 
		//更新相关的方法
		else if (action.equals("upd")) {
			//获取前端传过来的数据s
			int id = Integer.parseInt(request.getParameter("id"));
			int ygid = Integer.parseInt(request.getParameter("ygid"));
			String name = request.getParameter("name");
			String telephone = request.getParameter("telephone");
			String date = request.getParameter("date");
			String state = request.getParameter("state");
			String remarks = request.getParameter("remarks");
			//创建customer对象
			Customer customer = new Customer();
			customer.setId(id);
			customer.setYgid(ygid);
			customer.setName(name);
			customer.setTelephone(telephone);
			customer.setDate(date);
			customer.setState(state);
			customer.setRemarks(remarks);
			//调用更新方法
			cs.update(customer);

			map.put("code", 0);
			map.put("msg", "修改完成");
			map.put("count", 1);
			map.put("data", customer);

		} 
		//插入相关的方法
		else if (action.equals("ins")) {
			//获取前端传过来的数据
			int ygid = Integer.parseInt(request.getParameter("ygidadd"));
			String name = request.getParameter("nameadd");
			String telephone = request.getParameter("telephoneadd");
			String date = request.getParameter("dateadd");
			String state = request.getParameter("stateadd");
			String remarks = request.getParameter("remarksadd");
			//创建customer对象
			Customer customer = new Customer();
			customer.setYgid(ygid);
			customer.setName(name);
			customer.setTelephone(telephone);
			customer.setDate(date);
			customer.setState(state);
			customer.setRemarks(remarks);
			//调用添加方法
			cs.insert(customer);

			map.put("code", 0);
			map.put("msg", "添加完成");
			map.put("count", 1);
			map.put("data", customer);
		} 
		//根据条件查询的方法
		else if (action.equals("selbycon")) {
			//获取按条件查询的条件
			String condition = request.getParameter("condition");
			//获取条件的值
			String key = new String(request.getParameter("key").getBytes("ISO8859-1"), "UTF-8");
			//获取分页
			int curpage = Integer.parseInt(request.getParameter("page"));
			int pagesize = Integer.parseInt(request.getParameter("limit"));
			//获取数据条数
			int count = cs.selectAllCount(condition, key);
			List<Customer> list = cs.selectAll(condition, key, curpage, pagesize);

			map.put("code", 0);
			map.put("msg", "查询完成");
			map.put("count", count);
			map.put("data", list);
		}
		String json = gson.toJson(map);
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
