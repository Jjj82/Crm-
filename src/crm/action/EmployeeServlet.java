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

import crm.model.Employee;
import crm.service.EmployeeService;

/**
 * 员工管理Servlet
 * @author 15056
 *
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmployeeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Gson gson = new Gson();
		EmployeeService empserv = new EmployeeService();
		String action = request.getParameter("action");
		Map<String, Object> map = new HashMap<String, Object>();
		//分页查询
		if (action.equals("sel")) {
			int curpage = Integer.parseInt(request.getParameter("page"));
			int pagesize = Integer.parseInt(request.getParameter("limit"));
			List<Employee> csc = empserv.selectAll(curpage, pagesize);
			int count = empserv.selectAllCount();
			
			map.put("code", 0);
			map.put("msg", "查询完成");
			map.put("count", count);
			map.put("data", csc);
		} 
		//根据id删除
		else if (action.equals("del")) {
			int id = Integer.parseInt(request.getParameter("id"));
			empserv.del(id);
			
			map.put("code", 0);
			map.put("msg", "删除完成");
			map.put("count", 1);
			map.put("data", "");
		} 
		//修改数据
		else if (action.equals("upd")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String telephone = request.getParameter("telephone");
			String date = request.getParameter("date");
			String address = request.getParameter("address");
			String password = request.getParameter("password");
			String role = request.getParameter("role");
			String remarks = request.getParameter("remarks");
			Employee employee = new Employee();
			employee.setId(id);
			employee.setName(name);
			employee.setGender(gender);
			employee.setTelephone(telephone);
			employee.setDate(date);
			employee.setAddress(address);
			employee.setPassword(password);
			employee.setRole(role);
			employee.setRemarks(remarks);
			empserv.update(employee);
			
			map.put("code", 0);
			map.put("msg", "修改完成");
			map.put("count", 1);
			map.put("data", employee);
			
		} 
		//插入数据
		else if (action.equals("ins")) {
			String name = request.getParameter("nameadd");
			String gender = request.getParameter("genderadd");
			String telephone = request.getParameter("telephoneadd");
			String date = request.getParameter("dateadd");
			String address = request.getParameter("addressadd");
			String password = request.getParameter("passwordadd");
			String role = request.getParameter("roleadd");
			String remarks = request.getParameter("remarksadd");

			Employee employee = new Employee();
			employee.setName(name);
			employee.setGender(gender);
			employee.setTelephone(telephone);
			employee.setDate(date);
			employee.setAddress(address);
			employee.setPassword(password);
			employee.setRole(role);
			employee.setRemarks(remarks);

			empserv.insert(employee);

			map.put("code", 0);
			map.put("msg", "添加完成");
			map.put("count", 1);
			map.put("data", employee);

		} 
		//根据条件查询
		else if (action.equals("selbycon")) {
			String condition = request.getParameter("condition");
			String key = new String(request.getParameter("key").getBytes("ISO8859-1"), "UTF-8");
			int curpage = Integer.parseInt(request.getParameter("page"));
			int pagesize = Integer.parseInt(request.getParameter("limit"));

			int count = empserv.selectAllCount(condition, key);
			List<Employee> list = empserv.selectAll(condition, key, curpage, pagesize);

			map.put("code", 0);
			map.put("msg", "查询完成");
			map.put("count", count);
			map.put("data", list);

		}
		String empjson = gson.toJson(map);
		response.getWriter().write(empjson);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
