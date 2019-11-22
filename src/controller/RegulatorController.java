package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;

import entity.regulator;
import jdbc.DatabaseRegulator;

public class RegulatorController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����ַ�����
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		// ��ȡǰ̨��action
		String action = request.getParameter("action");
		// ��ȡǰ̨������regulatorName
		String RegulatorName = request.getParameter("regulatorName");
		// ��ȡǰ̨������RegulatorRoleName
		String RegulatorRoleName = request.getParameter("RegulatorRoleName");
		// ��ȡ���е��ŵ���Ϣ
		List<Map<String, Object>> storeData = new ArrayList<Map<String, Object>>();
		// ��ȡǰ̨������RegulatorId
		String RegulatorId = request.getParameter("RegulatorId");
		// ���������ݿ⽻���Ķ���
		DatabaseRegulator ReguRegulatorData = new DatabaseRegulator();
		// ������ʾ��Ϣ
		String msgString = "";
		// �жϵ�½�ߵ����
		HttpSession session = request.getSession();
		regulator loginRegulator = new regulator();
		loginRegulator = ReguRegulatorData.GetRegulatorForId(session.getAttribute("name").toString());
		String RoleId = loginRegulator.getRegulatorRoleId();
		if ("add".equals(action)) {
			System.out.println("RegulatorName:" + RegulatorName + " RegulatorRoleName:" + RegulatorRoleName
					+ "  action:" + action);
			/** ��װ��ӵĶ��� */
			regulator addobject = new regulator();
			try {
				// �ж���ӵ������Ƿ����ظ�
				if ("".equals(ReguRegulatorData.GetIdForName(RegulatorName))) {
					// û���ظ���������ӣ�
					// �жϵ�½�ߵ�Ȩ��
					if (RoleId.equals("1")) {
						// ��������Ա�����������
						addobject.setRegulatorName(RegulatorName);
						addobject.setRegulatorRoleId(ReguRegulatorData.GetRegulatorRoleId(RegulatorRoleName));
						ReguRegulatorData.insertRegulator(addobject);
						msgString = "��ӳɹ�";
					} else if (RoleId.equals("2")) {
						// ����Ա���곤��ֻ�������ͨԱ��
						if (ReguRegulatorData.GetRegulatorRoleId(RegulatorRoleName).equals("3")) {
							addobject.setRegulatorName(RegulatorName);
							addobject.setRegulatorRoleId(ReguRegulatorData.GetRegulatorRoleId(RegulatorRoleName));
							ReguRegulatorData.insertRegulator(addobject);
							msgString = "��ӳɹ�";
						} else {
							msgString = "���ʧ�ܣ���û��Ȩ�����";
						}
					} else {
						// ���������������
						msgString = "���ʧ�ܣ���û��Ȩ�����";
					}
				} else {
					// �����ظ�
					msgString = "�����ظ������ʧ��";
				}
			} catch (Exception e) {
				msgString = "���ʧ��";
			}
		} else if ("delete".equals(action)) {
			System.out.println("RegulatorId:" + RegulatorId + "  action:" + action);
			if (ReguRegulatorData.deleteRegulator(RegulatorId)) {
				msgString = "ɾ���ɹ�";
			} else {
				msgString = "ɾ��ʧ��";
			}
		} else if ("update".equals(action)) {
			System.out.println("RegulatorId:" + RegulatorId + " RegulatorName:" + RegulatorName + " RegulatorRoleName:"
					+ RegulatorRoleName + "  action:" + action);
			// ��ID��ȡԭ�����ŵ���Ϣ
			regulator oldRegulator=ReguRegulatorData.GetRegulatorForId(RegulatorId);
			//��װ�޸���Ϣ
			regulator newRegulator=new regulator();
			//д��ID
			newRegulator.setRegulatorId(RegulatorId);
			//�ж��ܷ���Ĺ���Ա��ɫ
			if(RoleId.equals("1")) {
				//�����޸�
				//д������
				newRegulator.setRegulatorName(RegulatorName);
				//д�������
				newRegulator.setPassword(oldRegulator.getPassword());
				//д���ɫ���
				newRegulator.setRegulatorRoleId(ReguRegulatorData.GetRegulatorRoleId(RegulatorRoleName));
				ReguRegulatorData.updateRegulator(newRegulator);
				msgString="�޸ĳɹ�";
			}else if(RoleId.equals("2")) {
				//ֻ���޸�Ա����Ϣ
				if(ReguRegulatorData.GetRegulatorRoleId(RegulatorRoleName).equals("3")) {
					//д������
					newRegulator.setRegulatorName(RegulatorName);
					//д�������
					newRegulator.setPassword(oldRegulator.getPassword());
					//д���ɫ���
					newRegulator.setRegulatorRoleId(ReguRegulatorData.GetRegulatorRoleId(RegulatorRoleName));
					ReguRegulatorData.updateRegulator(newRegulator);
					msgString="�޸ĳɹ�";
				}else {
					msgString="�޸�ʧ�ܣ���û��Ȩ��";
				}
			}else {
				msgString="�޸�ʧ�ܣ���û���޸�Ȩ��";
			}
		} else {
			// ������ݷ�������
			// 1:��������Ա2.����Ա��������3.��ͨԱ��
			List<regulator> listregulator = new ArrayList<regulator>();
			if (RoleId.equals("1")) {
				System.err.println("��������Ա");
				// ��ȡ����Ա
				List<regulator> list1 = ReguRegulatorData.ListRegulator();
				// ��ȡ��ͨԱ��
				List<regulator> list2 = ReguRegulatorData.listOrdinaryRegulators();
				listregulator.addAll(list1);
				listregulator.addAll(list2);
			} else if (RoleId.equals("2")) {
				System.err.println("����Ա");
				listregulator = ReguRegulatorData.listOrdinaryRegulators();
			} else {
				;
			}
			if (listregulator.size() != 0) {
				for (int i = 0; i < listregulator.size(); i++) {
					Map<String, Object> row = new HashMap<>();
					regulator entity = listregulator.get(i);
					row.put("RegulatorNum", entity.getRegulatorId());
					row.put("RegulatorName", entity.getRegulatorName());
					row.put("RegulatorRole", ReguRegulatorData.GetRegulatorRoleName(entity.getRegulatorRoleId()));
					storeData.add(row);
				}
			} else {
				storeData = null;
			}
		}
		/** ��װ����ǰ�˵�Map */
		Map<String, Object> preparedata = new HashMap<String, Object>();
		preparedata.put("rows", storeData);// ����
		preparedata.put("msg", msgString);// ��ʾ��Ϣ
		// ��mapתΪjson
		JSONObject data = new JSONObject(preparedata);
		PrintWriter out = response.getWriter();
		out.print(data);
		out.flush();
		out.close();
		ReguRegulatorData.CloseDatabase();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
	}
}
