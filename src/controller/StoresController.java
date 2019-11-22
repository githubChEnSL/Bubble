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

import com.alibaba.fastjson.JSONObject;

import entity.regulator;
import entity.store;
import jdbc.DatabaseRegulator;
import jdbc.DatabaseStore;

public class StoresController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public StoresController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����ַ�����
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		// ��ȡǰ̨��action
		String action = request.getParameter("action");
		// ��ȡǰ̨������StoreName
		String StoreName = request.getParameter("StoreName");
		// ��ȡǰ̨������RegulatorName
		String RegulatorName = request.getParameter("RegulatorName");
		// ��ȡ���е��ŵ���Ϣ
		List<Map<String, Object>> storeData = new ArrayList<Map<String, Object>>();
		// ��ȡǰ̨������StoreId
		String StoreId = request.getParameter("StoreId");
		// ���������ݿ⽻���Ķ���
		DatabaseStore StoreDatabase = new DatabaseStore();
		DatabaseRegulator RegulatorDatabase = new DatabaseRegulator();
		// ������ʾ��Ϣ
		String msgString = "";
		if ("add".equals(action)) {
			System.out.println("StoreName:" + StoreName + " RegulatorName:" + RegulatorName + "  action:" + action);
			/** ��װ��ӵĶ��� */
			store addobject = new store();
			try {
				// �ж��ŵ������Ƿ����ظ�
				if ("".equals(StoreDatabase.getIdForName(StoreName))) {
					// û���ظ���������ӣ�
					// ��֤����Ա�Ƿ��Ѿ�����
					if ("".equals(RegulatorDatabase.GetIdForName(RegulatorName))) {
						/** �����ڹ���Ա */
						// ��ӹ���Ա
						regulator addreglRegulator = new regulator();
						addreglRegulator.setRegulatorName(RegulatorName);
						addreglRegulator.setRegulatorRoleId("2");
						RegulatorDatabase.insertRegulator(addreglRegulator);
						// ����ŵ���Ϣ
						addobject.setStoreName(StoreName);
						addobject.setRegulatorId(RegulatorDatabase.GetRegulatorRoleId(RegulatorName));
						StoreDatabase.insertStore(addobject);
						msgString = "��ӳɹ�";
					} else {
						/** ���ڹ���Ա */
						//����ŵ���Ϣ
						addobject.setStoreName(StoreName);
						addobject.setRegulatorId(RegulatorDatabase.GetIdForName(RegulatorName));
						StoreDatabase.insertStore(addobject);
						msgString = "��ӳɹ�";
					}
				} else {
					// �����ظ�
					msgString = "�����ظ������ʧ��";
				}
			} catch (Exception e) {
				msgString = "���ʧ��";
			}
		} else if ("delete".equals(action)) {
			System.out.println("StoreId:" + StoreId + "  action:" + action);
			if (StoreDatabase.deleteStore(StoreId)) {
				msgString = "ɾ���ɹ�";
			} else {
				msgString = "ɾ��ʧ��";
			}
		} else if ("update".equals(action)) {
			System.out.println("StoreId:" + StoreId + " StoreName:" + StoreName + " RegulatorName:" + RegulatorName
					+ "  action:" + action);
			// ��ID��ȡԭ�����ŵ���Ϣ
			store oldStore = StoreDatabase.getStoreForId(StoreId);
			// �����µ��ŵ���Ϣ
			store newStore = new store();
			// д��ID
			newStore.setStoreId(StoreId);
			// �ж������Ƿ��б仯
			if (StoreName.equals(oldStore.getStoreName())) {
				// ����û��
				// д������
				newStore.setStoreName(oldStore.getStoreName());
				// �жϹ���Ա�����Ƿ��б仯
				//����Ĺ���Ա�����Ƿ��Ѿ�����
				if(RegulatorDatabase.GetRegulatorRoleId(RegulatorName)!=null) {
					//����
					if (RegulatorDatabase.GetRegulatorRoleId(RegulatorName).equals(oldStore.getRegulatorId())) {
						// ����Աû�䣨д�����Ա��
						newStore.setRegulatorId(oldStore.getRegulatorId());
						msgString = "�޸ĳɹ�";
						StoreDatabase.updateStore(newStore);
					} else {
						// ����Ա���ˣ��жϹ���Ա�Ƿ��Ѵ��ڣ�
						if ("".equals(RegulatorDatabase.GetIdForName(RegulatorName))) {
							// ����Ա�����ڣ���ӹ���Ա��Ϣ��
							regulator addreglRegulator = new regulator();
							addreglRegulator.setRegulatorName(RegulatorName);
							// ��ɫĬ��Ϊ�곤������Ա��
							addreglRegulator.setRegulatorRoleId("2");
							RegulatorDatabase.insertRegulator(addreglRegulator);
							// д�������Ա
							newStore.setRegulatorId(RegulatorDatabase.GetIdForName(RegulatorName));
							StoreDatabase.updateStore(newStore);
							msgString = "�޸ĳɹ�";
						} else {
							// ����Ա���ڣ�д�����Ա��
							newStore.setRegulatorId(RegulatorDatabase.GetIdForName(RegulatorName));
							StoreDatabase.updateStore(newStore);
							msgString = "�޸ĳɹ�";
						}
					}
				}else {
					//������
					if ("".equals(RegulatorDatabase.GetIdForName(RegulatorName))) {
						// ����Ա�����ڣ���ӹ���Ա��Ϣ��
						regulator addreglRegulator = new regulator();
						addreglRegulator.setRegulatorName(RegulatorName);
						// ��ɫĬ��Ϊ�곤������Ա��
						addreglRegulator.setRegulatorRoleId("2");
						RegulatorDatabase.insertRegulator(addreglRegulator);
						// д�������Ա
						newStore.setRegulatorId(RegulatorDatabase.GetIdForName(RegulatorName));
						StoreDatabase.updateStore(newStore);
						msgString = "�޸ĳɹ�";
					} else {
						// ����Ա���ڣ�д�����Ա��
						newStore.setRegulatorId(RegulatorDatabase.GetIdForName(RegulatorName));
						StoreDatabase.updateStore(newStore);
						msgString = "�޸ĳɹ�";
					}
				}
			} else {
				// ���Ʊ��ˣ��ж��Ƿ�������������ظ���
				// �����ƻ�ȡ��Ϣ
				if ("".equals(StoreDatabase.getIdForName(StoreName))) {
					// ����û�ظ�
					// д������
					newStore.setStoreName(StoreName);
					// �жϹ���Ա�����Ƿ��б仯
					//����Ĺ���Ա�����Ƿ��Ѿ�����
					if(RegulatorDatabase.GetRegulatorRoleId(RegulatorName)!=null) {
						//����
						if (RegulatorDatabase.GetRegulatorRoleId(RegulatorName).equals(oldStore.getRegulatorId())) {
							// ����Աû�䣨д�����Ա��
							newStore.setRegulatorId(oldStore.getRegulatorId());
							msgString = "�޸ĳɹ�";
							StoreDatabase.updateStore(newStore);
							System.err.println("����Աû��");
						} else {
							// ����Ա���ˣ��жϹ���Ա�Ƿ��Ѵ��ڣ�
							System.err.println("����Ա����");
							if ("".equals(RegulatorDatabase.GetIdForName(RegulatorName))) {
								// ����Ա�����ڣ���ӹ���Ա��Ϣ��
								regulator addreglRegulator = new regulator();
								addreglRegulator.setRegulatorName(RegulatorName);
								// ��ɫĬ��Ϊ�곤������Ա��
								addreglRegulator.setRegulatorRoleId("2");
								RegulatorDatabase.insertRegulator(addreglRegulator);
								// д�������Ա
								newStore.setRegulatorId(RegulatorDatabase.GetIdForName(RegulatorName));
								StoreDatabase.updateStore(newStore);
								msgString = "�޸ĳɹ�";
							} else {
								// ����Ա���ڣ�д�����Ա��
								newStore.setRegulatorId(RegulatorDatabase.GetIdForName(RegulatorName));
								StoreDatabase.updateStore(newStore);
								msgString = "�޸ĳɹ�";
							}
						}
					}else {
						//������
						newStore.setStoreName(StoreName);
						if ("".equals(RegulatorDatabase.GetIdForName(RegulatorName))) {
							// ����Ա�����ڣ���ӹ���Ա��Ϣ��
							regulator addreglRegulator = new regulator();
							addreglRegulator.setRegulatorName(RegulatorName);
							// ��ɫĬ��Ϊ�곤������Ա��
							addreglRegulator.setRegulatorRoleId("2");
							RegulatorDatabase.insertRegulator(addreglRegulator);
							// д�������Ա
							newStore.setRegulatorId(RegulatorDatabase.GetIdForName(RegulatorName));
							StoreDatabase.updateStore(newStore);
							msgString = "�޸ĳɹ�";
						} else {
							// ����Ա���ڣ�д�����Ա��
							newStore.setRegulatorId(RegulatorDatabase.GetIdForName(RegulatorName));
							StoreDatabase.updateStore(newStore);
							msgString = "�޸ĳɹ�";
						}
					}
				} else {
					// �����ظ�
					msgString = "�����ظ����޸�ʧ��";
				}
			}
		} else {
			System.out.println();
			System.out.println("��ѯ���еĵ�����Ϣ");
			List<store> listStores = StoreDatabase.ListStore();
			for (int i = 0; i < listStores.size(); i++) {
				Map<String, Object> row = new HashMap<>();
				store entity = listStores.get(i);
				row.put("StoreNum", entity.getStoreId());
				row.put("StoreName", entity.getStoreName());
				row.put("RegulatorName", RegulatorDatabase.GetRegulatorForId(entity.getRegulatorId()).getRegulatorName());
				storeData.add(row);
			}
		}
		/** ��װ����ǰ�˵�Map */
		Map<String, Object> preparedata = new HashMap<String, Object>();
		preparedata.put("rows", storeData);
		preparedata.put("msg", msgString);
		// ��mapתΪjson
		JSONObject data = new JSONObject(preparedata);
		PrintWriter out = response.getWriter();
		/** ��װ������ʾ��Ϣ */
		out.print(data);
		out.flush();
		out.close();
		RegulatorDatabase.CloseDatabase();
		StoreDatabase.CloseDatabase();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
	}
}
