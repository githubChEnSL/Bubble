package Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
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
		HttpServletRequest request = (HttpServletRequest) arg0;// ��ȡrequest����
		HttpServletResponse response = (HttpServletResponse) arg1;
		try {
			HttpSession session = request.getSession();// ��ȡsession����
			if (session.getAttribute("id") != null) {
				System.out.println("�����֤-----������,�û�Ϊ:" + session.getAttribute("id"));
				arg2.doFilter(arg0, arg1);
			} else {
				request.getSession().setAttribute("LoginMsg", "��½״̬�쳣,�����µ�½");
				response.sendRedirect("login.jsp");
				System.out.println("�����֤-----������,�����µ�½");
			}
		} catch (Exception e) {
			request.getSession().setAttribute("LoginMsg", "��½״̬�쳣,�����µ�½");
			response.sendRedirect("login.jsp");
			System.out.println("�����֤-----������,�����µ�½");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
}
