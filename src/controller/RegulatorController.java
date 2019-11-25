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
import service.RegulatorService;
import service.StoreService;
import service.impl.RegulatorServiceImpl;
import service.impl.StoreServiceImpl;

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
		// ������serviceʵ����Ķ���
		RegulatorService RegulatorService = new RegulatorServiceImpl();
		StoreService StoreService = new StoreServiceImpl();
		// ��ȡ���е��ŵ�����
		List<String> listStoreName = StoreService.ListStoresName();
		// ��ȡ���еĹ����߽�ɫ����
		List<String> listRoleName = RegulatorService.ListRegulatorRoleName();
		// ������ʾ��Ϣ
		String msgString = "";
		// ��װ���ص�����
		List<regulator> listregulator = new ArrayList<regulator>();
		// �жϵ�½�ߵ����
		HttpSession session = request.getSession();
		regulator loginRegulator = new regulator();
		// ��֤�û��Ƿ��¼
		try {
			// �����ȡ��null����catch
			loginRegulator = RegulatorService.GetRegulatorForId(session.getAttribute("id").toString());
			String RoleId = loginRegulator.getRegulatorRoleId();
			if ("add".equals(action)) {
				System.out.println("RegulatorName:" + RegulatorName + " RegulatorRoleName:" + RegulatorRoleName
						+ " StoreName:" + StoreName + "  action:" + action);
				/** ��װ��ӵĶ��� */
				regulator addobject = new regulator();
				try {
					// �ж���ӵ������Ƿ����ظ�
					if ("".equals(RegulatorService.GetIdForName(RegulatorName))) {
						// û���ظ���������ӣ�
						// �жϵ�½�ߵ�Ȩ��
						if (RoleId.equals("1")) {
							// ��������Ա�����������
							addobject.setRegulatorName(RegulatorName);
							addobject.setRegulatorRoleId(RegulatorService.GetRegulatorRoleId(RegulatorRoleName));
							RegulatorService.insertRegulator(addobject);
							addobject.setStoreId(StoreService.getIdForName(StoreName));
							msgString = "��ӳɹ�";
						} else if (RoleId.equals("2")) {
							// ����Ա���곤��ֻ�������ͨԱ��
							if (RegulatorService.GetRegulatorRoleId(RegulatorRoleName).equals("3")) {
								// �Ƿ�Ϊ���ŵ�
								if (StoreService.getIdForName(StoreName).equals(loginRegulator.getStoreId())) {
									addobject.setRegulatorName(RegulatorName);
									addobject
											.setRegulatorRoleId(RegulatorService.GetRegulatorRoleId(RegulatorRoleName));
									addobject.setStoreId(StoreService.getIdForName(StoreName));
									RegulatorService.insertRegulator(addobject);
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
				if (RegulatorService.deleteRegulator(RegulatorId)) {
					msgString = "ɾ���ɹ�";
				} else {
					msgString = "ɾ��ʧ��";
				}
			} else if ("update".equals(action)) {
				System.out.println("RegulatorId:" + RegulatorId + " RegulatorName:" + RegulatorName
						+ " RegulatorRoleName:" + RegulatorRoleName + " StoreName:" + StoreName + "  action:" + action);
				// ��ID��ȡԭ�����ŵ���Ϣ
				regulator oldRegulator = RegulatorService.GetRegulatorForId(RegulatorId);
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
					newRegulator.setRegulatorRoleId(RegulatorService.GetRegulatorRoleId(RegulatorRoleName));
					// д���ŵ���
					newRegulator.setStoreId(StoreService.getIdForName(StoreName));
					RegulatorService.updateRegulator(newRegulator);
					msgString = "�޸ĳɹ�";
				} else if (RoleId.equals("2")) {
					// ֻ���޸�Ա����Ϣ
					if (RegulatorService.GetRegulatorRoleId(RegulatorRoleName).equals("3")) {
						// д������
						newRegulator.setRegulatorName(RegulatorName);
						// д�������
						newRegulator.setPassword(oldRegulator.getPassword());
						// д���ɫ���
						newRegulator.setRegulatorRoleId(RegulatorService.GetRegulatorRoleId(RegulatorRoleName));
						// д���ŵ���
						newRegulator.setStoreId(StoreService.getIdForName(StoreName));
						RegulatorService.updateRegulator(newRegulator);
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
							listregulator = RegulatorService.ListRegulator();
						} else {
							// �����ŵ����ƻ�ȡ�ŵ���
							String StoreId = StoreService.getIdForName(StoreName);
							listregulator = RegulatorService.listRegulatorByStoreId(StoreId);
						}
					} else if (RoleId.equals("2")) {
						System.err.println("����Ա");
						// ����Ա���
						// ��ȡ���ŵ������Ա����Ϣ
						String StoreId = loginRegulator.getStoreId();
						listregulator = RegulatorService.listRegulatorByStoreId(StoreId);
					} else {
						listregulator = null;
					}
				} else {
					// ����Ա���
					// ��ȡ���ŵ������Ա����Ϣ
					String StoreId = loginRegulator.getStoreId();
					listregulator = RegulatorService.listRegulatorByStoreId(StoreId);
				}
			}
			if (listregulator.size() != 0) {
				for (int i = 0; i < listregulator.size(); i++) {
					Map<String, Object> row = new HashMap<>();
					regulator entity = listregulator.get(i);
					if (entity.getRegulatorRoleId().equals("1")) {
						if (RoleId.equals("1")) {
							row.put("RegulatorNum", entity.getRegulatorId());
							row.put("RegulatorName", entity.getRegulatorName());
							row.put("RegulatorRole",
									RegulatorService.GetRegulatorRoleName(entity.getRegulatorRoleId()));
							if ("".equals(entity.getStoreId())) {
								row.put("StoreName", "��");
							} else {
								row.put("StoreName", StoreService.getStoreForId(entity.getStoreId()).getStoreName());
							}
							RegulatorData.add(row);
						} else {

						}
					} else {
						row.put("RegulatorNum", entity.getRegulatorId());
						row.put("RegulatorName", entity.getRegulatorName());
						row.put("RegulatorRole", RegulatorService.GetRegulatorRoleName(entity.getRegulatorRoleId()));
						if ("".equals(entity.getStoreId())) {
							row.put("StoreName", "��");
						} else {
							row.put("StoreName", StoreService.getStoreForId(entity.getStoreId()).getStoreName());
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
		StoreService.CloseStoreService();
		RegulatorService.CloseRegulatorService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
	}
}
