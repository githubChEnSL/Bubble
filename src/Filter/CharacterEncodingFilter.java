package Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class CharacterEncodingFilter
 */
public class CharacterEncodingFilter implements Filter {

	private static String encoding; // ����������ճ�ʼ����ֵ
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			request.setCharacterEncoding(encoding);
			response.setCharacterEncoding(encoding);
			//System.out.println("�����ַ�����--�ɹ�");
		} catch (Exception e) {
			System.out.println("�����ַ�����--ʧ��");
		}
		chain.doFilter(request, response);
	}
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		encoding=fConfig.getInitParameter("encoding");
	}

}
