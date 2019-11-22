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

import entity.user;
import jdbc.DatabaseUser;

public class UserController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserController() {
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
		// ��ȡǰ̨������UserName
		String UserName = request.getParameter("UserName");
		// ��ȡǰ̨������RoleName
		String RoleName = request.getParameter("RoleName");
		// ��ȡ���е��ŵ���Ϣ
		List<Map<String, Object>> storeData = new ArrayList<Map<String, Object>>();
		// ��ȡǰ̨������UserNum
		String UserNum = request.getParameter("UserNum");
		// ���������ݿ⽻���Ķ���
		DatabaseUser UserData = new DatabaseUser();
		// ������ʾ��Ϣ
		String msgString = "";
		if ("add".equals(action)) {
			System.out.println("UserName:" + UserName + " RoleName:" + RoleName + "  action:" + action);
			/** ��װ��ӵĶ��� */
			user addobject = new user();
			try {
				// �ж������Ƿ����ظ�
				if ("".equals(UserData.getUserIdForName(UserName))) {
					// û���ظ���������ӣ�
					addobject.setUserName(UserName);
					addobject.setRoleId(UserData.GetUserRoleId(RoleName));
					UserData.insertUser(addobject);
					msgString = "��ӳɹ�";
				} else {
					// �����ظ�
					msgString = "�����ظ������ʧ��";
				}
			} catch (Exception e) {
				msgString = "���ʧ��";
			}
		} else if ("delete".equals(action)) {
			System.out.println("UserNum:" + UserNum + "  action:" + action);
			if (UserData.deleteUser(UserNum)) {
				msgString = "ɾ���ɹ�";
			} else {
				msgString = "ɾ��ʧ��";
			}
		} else if ("update".equals(action)) {
			System.out.println("UserNum:" + UserNum+ " UserName:" + UserName  + " RoleName:" + RoleName + "  action:" + action);
			// ��ID��ȡԭ���Ļ�Ա��Ϣ
			user oldUser = UserData.getUserForId(UserNum);
			// �����µĻ�Ա��Ϣ
			user newUser = new user();
			// д��ID
			newUser.setUserId(UserNum);
			// �ж������Ƿ��б仯
			if (UserName.equals(oldUser.getUserName())) {
				// ����û�䣨д�����ƣ�
				newUser.setUserName(oldUser.getUserName());
				//д���Ա��ɫ
				newUser.setRoleId(UserData.GetUserRoleId(RoleName));
				//�޸Ļ�Ա��Ϣ
				UserData.updateUser(newUser);
				msgString="�޸ĳɹ�";
			} else {
				// ���Ʊ��ˣ��ж��Ƿ�������������ظ���
				// �����ƻ�ȡ��Ϣ
				if ("".equals(UserData.getUserIdForName(UserName))) {
					// ����û�ظ���д�����ƣ�
					newUser.setUserName(UserName);
					//д���Ա��ɫ
					newUser.setRoleId(UserData.GetUserRoleId(RoleName));
					//�޸Ļ�Ա��Ϣ
					UserData.updateUser(newUser);
					msgString="�޸ĳɹ�";
				} else {
					// �����ظ�
					msgString = "�����ظ����޸�ʧ��";
				}
			}
		} else {
			System.out.println();
			System.out.println("��ѯ���еĻ�Ա��Ϣ");
			List<user> listStores = UserData.ListUser();
			for (int i = 0; i < listStores.size(); i++) {
				Map<String, Object> row = new HashMap<>();
				user entity = listStores.get(i);
				row.put("UserNum", entity.getUserId());
				row.put("UserName", entity.getUserName());
				row.put("UserRoleName", UserData.GetUserRoleName(entity.getRoleId()));
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
		UserData.CloseDatabase();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
	}
}
