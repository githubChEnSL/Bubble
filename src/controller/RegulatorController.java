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
import jdbc.DatabaseStore;

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
		// ��ȡǰ̨������StoreName
		String StoreName = request.getParameter("StoreName");
		// ��ȡ���еĹ���Ա��Ϣ
		List<Map<String, Object>> RegulatorData = new ArrayList<Map<String, Object>>();
		// ��ȡǰ̨������RegulatorId
		String RegulatorId = request.getParameter("RegulatorId");
		// ���������ݿ⽻���Ķ���
		DatabaseRegulator RegulatorDatabase = new DatabaseRegulator();
		DatabaseStore StoreDatabase = new DatabaseStore();
		// ��ȡ���е��ŵ�����
		List<String> listStoreName = StoreDatabase.ListStoresName();
		// ��ȡ���еĹ����߽�ɫ����
		List<String> listRoleName = RegulatorDatabase.ListRegulatorRoleName();
		// ������ʾ��Ϣ
		String msgString = "";
		// ��װ���ص�����
		List<regulator> listregulator = new ArrayList<regulator>();
		// �жϵ�½�ߵ����
		HttpSession session = request.getSession();
		regulator loginRegulator = new regulator();
		//��֤�û��Ƿ��¼
		try {
			//�����ȡ��null����catch
			loginRegulator = RegulatorDatabase.GetRegulatorForId(session.getAttribute("id").toString());
			String RoleId = loginRegulator.getRegulatorRoleId();
			if ("add".equals(action)) {
				System.out.println("RegulatorName:" + RegulatorName + " RegulatorRoleName:" + RegulatorRoleName
						+ " StoreName:" + StoreName + "  action:" + action);
				/** ��װ��ӵĶ��� */
				regulator addobject = new regulator();
				try {
					// �ж���ӵ������Ƿ����ظ�
					if ("".equals(RegulatorDatabase.GetIdForName(RegulatorName))) {
						// û���ظ���������ӣ�
						// �жϵ�½�ߵ�Ȩ��
						if (RoleId.equals("1")) {
							// ��������Ա�����������
							addobject.setRegulatorName(RegulatorName);
							addobject.setRegulatorRoleId(RegulatorDatabase.GetRegulatorRoleId(RegulatorRoleName));
							RegulatorDatabase.insertRegulator(addobject);
							addobject.setStoreId(StoreDatabase.getIdForName(StoreName));
							msgString = "��ӳɹ�";
						} else if (RoleId.equals("2")) {
							// ����Ա���곤��ֻ�������ͨԱ��
							if (RegulatorDatabase.GetRegulatorRoleId(RegulatorRoleName).equals("3")) {
								// �Ƿ�Ϊ���ŵ�
								if (StoreDatabase.getIdForName(StoreName).equals(loginRegulator.getStoreId())) {
									addobject.setRegulatorName(RegulatorName);
									addobject.setRegulatorRoleId(RegulatorDatabase.GetRegulatorRoleId(RegulatorRoleName));
									addobject.setStoreId(StoreDatabase.getIdForName(StoreName));
									RegulatorDatabase.insertRegulator(addobject);
									msgString = "��ӳɹ�";
								} else {
									msgString = "���ʧ�ܣ����޷���������ŵ��Ա��";
								}
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
				if (RegulatorDatabase.deleteRegulator(RegulatorId)) {
					msgString = "ɾ���ɹ�";
				} else {
					msgString = "ɾ��ʧ��";
				}
			} else if ("update".equals(action)) {
				System.out.println("RegulatorId:" + RegulatorId + " RegulatorName:" + RegulatorName + " RegulatorRoleName:"
						+ RegulatorRoleName + " StoreName:" + StoreName+ "  action:" + action);
				// ��ID��ȡԭ�����ŵ���Ϣ
				regulator oldRegulator = RegulatorDatabase.GetRegulatorForId(RegulatorId);
				// ��װ�޸���Ϣ
				regulator newRegulator = new regulator();
				// д��ID
				newRegulator.setRegulatorId(RegulatorId);
				// �ж��ܷ���Ĺ���Ա��ɫ
				if (RoleId.equals("1")) {
					// �����޸�
					// д������
					newRegulator.setRegulatorName(RegulatorName);
					// д�������
					newRegulator.setPassword(oldRegulator.getPassword());
					// д���ɫ���
					newRegulator.setRegulatorRoleId(RegulatorDatabase.GetRegulatorRoleId(RegulatorRoleName));
					//д���ŵ���
					newRegulator.setStoreId(StoreDatabase.getIdForName(StoreName));
					RegulatorDatabase.updateRegulator(newRegulator);
					msgString = "�޸ĳɹ�";
				} else if (RoleId.equals("2")) {
					// ֻ���޸�Ա����Ϣ
					if (RegulatorDatabase.GetRegulatorRoleId(RegulatorRoleName).equals("3")) {
						// д������
						newRegulator.setRegulatorName(RegulatorName);
						// д�������
						newRegulator.setPassword(oldRegulator.getPassword());
						// д���ɫ���
						newRegulator.setRegulatorRoleId(RegulatorDatabase.GetRegulatorRoleId(RegulatorRoleName));
						//д���ŵ���
						newRegulator.setStoreId(StoreDatabase.getIdForName(StoreName));
						RegulatorDatabase.updateRegulator(newRegulator);
						msgString = "�޸ĳɹ�";
					} else {
						msgString = "�޸�ʧ�ܣ���û��Ȩ��";
					}
				} else {
					msgString = "�޸�ʧ�ܣ���û���޸�Ȩ��";
				}
			} else {
				if ("search".equals(action)) {
					// ��������Ա���
					System.err.println("action:" + action + " StoreName:" + StoreName);
					if (RoleId.equals("1")) {
						System.err.println("����");
						if ("����".equals(StoreName)) {
							listregulator = RegulatorDatabase.ListRegulator();
						} else {
							// �����ŵ����ƻ�ȡ�ŵ���
							String StoreId = StoreDatabase.getIdForName(StoreName);
							listregulator = RegulatorDatabase.listRegulatorByStoreId(StoreId);
						}
					} else if (RoleId.equals("2")) {
						System.err.println("����Ա");
						// ����Ա���
						// ��ȡ���ŵ������Ա����Ϣ
						String StoreId = loginRegulator.getStoreId();
						listregulator = RegulatorDatabase.listRegulatorByStoreId(StoreId);
					} else {
						listregulator = null;
					}
				} else {
					// ����Ա���
					// ��ȡ���ŵ������Ա����Ϣ
					String StoreId = loginRegulator.getStoreId();
					listregulator = RegulatorDatabase.listRegulatorByStoreId(StoreId);
				}
			}
			if (listregulator.size() != 0) {
				for (int i = 0; i < listregulator.size(); i++) {
					Map<String, Object> row = new HashMap<>();
					regulator entity = listregulator.get(i);
					if(entity.getRegulatorRoleId().equals("1")) {
						if(RoleId.equals("1")) {
							row.put("RegulatorNum", entity.getRegulatorId());
							row.put("RegulatorName", entity.getRegulatorName());
							row.put("RegulatorRole", RegulatorDatabase.GetRegulatorRoleName(entity.getRegulatorRoleId()));
							if ("".equals(entity.getStoreId())) {
								row.put("StoreName", "��");
							} else {
								row.put("StoreName", StoreDatabase.getStoreForId(entity.getStoreId()).getStoreName());
							}
							RegulatorData.add(row);
						}else {
							
						}
					}else {
						row.put("RegulatorNum", entity.getRegulatorId());
						row.put("RegulatorName", entity.getRegulatorName());
						row.put("RegulatorRole", RegulatorDatabase.GetRegulatorRoleName(entity.getRegulatorRoleId()));
						if ("".equals(entity.getStoreId())) {
							row.put("StoreName", "��");
						} else {
							row.put("StoreName", StoreDatabase.getStoreForId(entity.getStoreId()).getStoreName());
						}
						RegulatorData.add(row);
					}
				}
			} else {
				System.out.println("nullllllll..............");
			}
		} catch (Exception e) {
			response.sendRedirect("Exit.jsp");
		}
		/** ��װ����ǰ�˵�Map */
		Map<String, Object> preparedata = new HashMap<String, Object>();
		preparedata.put("liststorename", listStoreName);// �ŵ���Ϣ
		preparedata.put("rows", RegulatorData);// ����
		preparedata.put("msg", msgString);// ��ʾ��Ϣ
		preparedata.put("listRoleName", listRoleName);// ��ɫ����
		// ��mapתΪjson
		JSONObject data = new JSONObject(preparedata);
		PrintWriter out = response.getWriter();
		out.print(data);
		out.flush();
		out.close();
		StoreDatabase.CloseDatabase();
		RegulatorDatabase.CloseDatabase();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
	}
}
