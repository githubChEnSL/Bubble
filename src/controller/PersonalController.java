package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;

import entity.regulator;
import service.RegulatorService;
import service.StoreService;

public class PersonalController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public PersonalController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����ַ�����
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		//����Service����
		RegulatorService regulaService = new RegulatorService();
		StoreService storeService = new StoreService();
		//���b������ʾ��Ϣ
		String msgString="";
		// ��ȡsession���е�id(��Ϊ�޸ĵ��Ǹ�����Ϣ����¼�ߵ���Ϣ)
		HttpSession session = request.getSession();
		String loginid = (String) session.getAttribute("id");
		// ��ȡǰ̨��Type
		String Type = request.getParameter("Type");
		// ��ȡǰ̨��Type
		String updateName = request.getParameter("updateName");
		//��ȡǰ̨��oldpassword
		String oldpassword=request.getParameter("oldpassword");
		//��ȡǰ̨��truepassword
		String truepassword=request.getParameter("truepassword");
		//��id��ȡ�ɶ���
		regulator oldRegulator=regulaService.GetRegulatorForId(loginid);
		/**��װ�޸ĵ�����*/
		regulator newRegulator=new regulator();
		if("updatename".equals(Type)) {
			System.err.println("�޸�����"+updateName);
			if("".equals(updateName)) {
				msgString="���Ʋ���Ϊ��";
			}else {
				newRegulator.setRegulatorId(oldRegulator.getRegulatorId());
				newRegulator.setRegulatorName(updateName);
				newRegulator.setPassword(oldRegulator.getPassword());
				newRegulator.setRegulatorRoleId(oldRegulator.getRegulatorRoleId());
				newRegulator.setStoreId(oldRegulator.getStoreId());
				regulaService.updateRegulator(newRegulator);
				msgString="�޸����Ƴɹ�";
			}
		}else if("updatepass".equals(Type)) {
			//�жϾ������Ƿ���ȷ
			if(oldpassword.equals(regulaService.GetRegulatorForId(loginid).getPassword())) {
				newRegulator.setRegulatorId(oldRegulator.getRegulatorId());
				newRegulator.setRegulatorName(oldRegulator.getPassword());
				newRegulator.setPassword(truepassword);
				newRegulator.setRegulatorRoleId(oldRegulator.getRegulatorRoleId());
				newRegulator.setStoreId(oldRegulator.getStoreId());
				regulaService.updateRegulator(newRegulator);
				msgString="�޸�����ɹ�";
			}else {
				msgString="�����벻��ȷ���޸�ʧ��";
			}
		}else {
			msgString="ɶ��û��";
		}
		
		regulator object = regulaService.GetRegulatorForId(loginid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("LoginId", object.getRegulatorId());
		map.put("LoginName", object.getRegulatorName());
		map.put("LoginStoreName", storeService.getStoreForId(object.getStoreId()).getStoreName());
		map.put("LoginRoleName", regulaService.GetRegulatorRoleName(object.getRegulatorRoleId()));
		map.put("msg", msgString);
		PrintWriter out = response.getWriter();
		// ��mapתΪjson
		JSONObject data = new JSONObject(map);
		out.print(data);
		out.flush();
		out.close();
		storeService.closeStoreService();
		regulaService.CloseRegulatorService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
	}
}
