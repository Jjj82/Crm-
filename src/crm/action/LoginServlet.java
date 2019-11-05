package crm.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import crm.model.Employee;
import crm.service.EmployeeService;
import crm.util.MD5util;

/**
 * 登陆Servlet
 * 
 * @author 15056
 *
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();

		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		// 登陆相关方法
		if (action.equals("login")) {
			String username = request.getParameter("username");
			//通过MD5加密密码
			String password = MD5util.crypt(request.getParameter("password"));
			//从session中获取验证码
			String checkcode = session.getAttribute("checkcode").toString().toLowerCase();
			//获取用户输入的验证码
			String check = request.getParameter("check").toLowerCase();

			EmployeeService empserv = new EmployeeService();
			Employee emp = empserv.selectByName(username, password);
			//检查验证码是否正确
			if (!check.equals(checkcode)) {
				map.put("code", -1);
				map.put("msg", "验证码错误");
			} else {
				//如果验证码正确,查询用户名和密码是否正确
				if (emp.getName() == null) {
					map.put("code", -1);
					map.put("msg", "用户名或密码错误");
				} else {
					//登陆成功,把用户相关信息存放到session中
					map.put("code", 0);
					session.setAttribute("employee", emp);
				}
			}
		}
		// 退出相关方法
		else if (action.equals("logout")) {
			map.put("code", 0);
			//把session中存放的数据设置为null
			session.setAttribute("employee", null);
		}
		// 查询用户名相关方法
		else if (action.equals("selusername")) {
			Employee employee = (Employee) request.getSession().getAttribute("employee");
			String username = employee.getName();
			map.put("code", 0);
			map.put("username", username);
		}
		map.put("count", 0);
		map.put("data", null);
		String empjson = gson.toJson(map);
		response.getWriter().write(empjson);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
