package service;

import java.util.List;

import entity.user;

public interface UserService {
	/** �����û�Ա��ɫ��Ż�ȡ�û���ɫ���� */
	public String GetUserRoleName(String UserRoleid);

	/** �����û�Ա��ɫ���ƻ�ȡ�û���ɫ��� */
	public String GetUserRoleId(String UserRoleName);

	/** ��ȡ���е��û���Ϣ */
	public List<user> ListUser();

	/** ͨ���û���Ż�ȡ�û���Ϣ */
	public user getUserForId(String userid);

	/** ͨ���û����ƻ�ȡ�û���� */
	public String getUserIdForName(String username);

	/** ����û���Ϣ */
	public boolean insertUser(user addobject);

	/** ɾ���û���Ϣ */
	public boolean deleteUser(String userid);

	/** �޸��û���Ϣ */
	public boolean updateUser(user updateuser);

	/** ��ȡ���Ŀͻ���� */
	public String GetMaxId();
	
	/**�ر�daoUser*/
	public void CloseService();
}
