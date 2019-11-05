package crm.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import crm.model.Employee;

/**
 * 登陆过滤器
 * 
 * @author 15056
 *
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {

	public LoginFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		HttpSession session = httpRequest.getSession();

		Employee emp = (Employee) session.getAttribute("employee");
		String contextpath = httpRequest.getServletPath();// 获取请求的url
		String path = httpRequest.getContextPath();

		if (emp != null) {
			chain.doFilter(request, response);
		} else if (contextpath.indexOf("/LoginServlet") >= 0 || contextpath.indexOf("login") >= 0
				|| contextpath.indexOf("/css/") >= 0 || contextpath.indexOf("/images/") >= 0
				|| contextpath.indexOf("/layui/") >= 0 || contextpath.indexOf("/plugins/") >= 0
				|| contextpath.indexOf("/BufferimageServlet") >= 0) {
			chain.doFilter(request, response);
		} else {
			httpResponse.sendRedirect(path + "/static/login.html");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
