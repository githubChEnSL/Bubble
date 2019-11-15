package Tag;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ErrorMessageTag extends SimpleTagSupport{

		public void doTag() {
			JspWriter out=getJspContext().getOut();
			PageContext pageContext=(PageContext)getJspContext();
			HttpSession session= pageContext.getSession();
			String name = (String)session.getAttribute("name");
			String error=(String) session.getAttribute("error");
			if(name==null) {
				if(error!=null) {
					try {
						out.println("�˺Ż���������������ص�½");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else {
					try {
						out.println("��½״̬�쳣���������ص�½");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}else {
				try {
					out.println("δ֪�����������ص�½");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
}
