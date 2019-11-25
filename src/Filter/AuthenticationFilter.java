package Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) arg0;//获取request对象
		HttpServletResponse response=(HttpServletResponse) arg1;
		try {
			HttpSession session=request.getSession();//获取session对象
			if(session.getAttribute("name")!=null) {
				System.out.println("身份验证-----在线中,用户为:"+session.getAttribute("name"));
				arg2.doFilter(arg0, arg1);
			}else {
				request.getSession().setAttribute("LoginMsg", "登陆状态异常,请重新登陆");
				response.sendRedirect("login.jsp");
				System.out.println("身份验证-----离线中,请重新登陆");
			}
		} catch (Exception e) {
			request.getSession().setAttribute("LoginMsg", "登陆状态异常,请重新登陆");
			response.sendRedirect("login.jsp");
			System.out.println("身份验证-----离线中,请重新登陆");
		}
		
	}
}
