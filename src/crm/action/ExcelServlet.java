package crm.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm.model.Employee;
import crm.service.EmployeeService;
import crm.util.ExportExcel;

/**
 * Servlet implementation class ExcelServlet
 */
@WebServlet("/ExcelServlet")
public class ExcelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExcelServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 操作导出excel

		// excel标题
		String title = "员工信息表";
		// excel列头信息
		String[] rowsName = new String[] { "ID", "姓名", "性别", "电话", "日期", "地址", "密码", "角色", "备注" };
		//创建员工Service对象
		EmployeeService es = new EmployeeService();
		//查询全部数据
		List<Employee> list = es.select();

		List<Object[]> dataList = new ArrayList<Object[]>();
		//把查询出来的数据通过Object[]的形式放到List中
		for (Employee em : list) {
			Object[] objs = new Object[rowsName.length];
			objs[0] = em.getId();
			objs[1] = em.getName();
			objs[2] = em.getGender();
			objs[3] = em.getTelephone();
			objs[4] = em.getDate();
			objs[5] = em.getAddress();
			objs[8] = em.getRemarks();
			objs[6] = em.getPassword();
			objs[7] = em.getRole();
			dataList.add(objs);
		}

		// 调用poi的工具类
		ExportExcel ex = new ExportExcel(title, rowsName, dataList);
		// 给文件命名。随机命名
		String fileName = "Excel-" + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls";
		// 告诉浏览器数据格式，将头和数据传到前台
		String headStr = "attachment; filename=\"" + fileName + "\"";
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", headStr);
		OutputStream out = response.getOutputStream();
		//通过poi工具类传输到前台
		try {
			ex.export(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.flush();
		out.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
