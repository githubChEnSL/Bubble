package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mysql.cj.protocol.Resultset;
import entity.user;

public class DatabaseUser extends Database{

	public DatabaseUser(){
		super();
	}
	/**�����û�Ա��ɫ��Ż�ȡ�û���ɫ����*/
	public String GetUserRoleName(String UserRoleid) {
		String RoleName = null;
		String sql="select * from user_role where roleid="+UserRoleid;
		try {
			//����ʵ��
			Statement GetUserRoleName=null;
			GetUserRoleName=createSta(GetUserRoleName);
			resuset=(Resultset) GetUserRoleName.executeQuery(sql);
			while(((ResultSet) resuset).next()) {
				RoleName=((ResultSet) resuset).getString("rolename");
			}
			//�ر�ʵ��
			 CloseStatement(GetUserRoleName);
		} catch (Exception e) {
			System.out.println("��ȡ�û���ɫ����ʧ��");
		}
		return RoleName;
	}
	/**�����û�Ա��ɫ���ƻ�ȡ�û���ɫ���*/
	public String GetUserRoleId(String UserRoleName) {
		String RoleId = null;
		String sql="select * from user_role where rolename='"+UserRoleName+"'";
		try {
			//����ʵ��
			Statement GetUserRoleName=null;
			GetUserRoleName=createSta(GetUserRoleName);
			resuset=(Resultset) GetUserRoleName.executeQuery(sql);
			while(((ResultSet) resuset).next()) {
				RoleId=((ResultSet) resuset).getString("roleid");
			}
			//�ر�ʵ��
			 CloseStatement(GetUserRoleName);
		} catch (Exception e) {
			System.out.println("��ȡ�û���ɫ���ʧ��");
		}
		return RoleId;
	}
	
	/**��ȡ���е��û���Ϣ*/
	public List<user> ListUser(){
		List<user> listUser=new ArrayList<user>();
		String sql="select * from user";
		try {
			//����ʵ��
			Statement SelectStd=null;
			SelectStd=createSta(SelectStd);
			resuset= (Resultset) SelectStd.executeQuery(sql);
			 while(((ResultSet) resuset).next()){
		         String userid  = ((ResultSet) resuset).getString("userid");
		         String username = ((ResultSet) resuset).getString("username");
		         String password= ((ResultSet) resuset).getString("password");
		         String roleid = ((ResultSet) resuset).getString("roleid");
		         user object=new user();
		         object.setUserId(userid);
		         object.setUserName(username);
		         object.setPassword(password);
		         object.setRoleId(roleid);
		         listUser.add(object);
		      }
			//�ر�ʵ��
			 CloseStatement(SelectStd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listUser;
	}
	/**ͨ���û���Ż�ȡ�û���Ϣ*/
	public user getUserForId(String userid) {
		user getuser=new user();
		String sql="select * from user where userid="+userid;
		try {
			//����ʵ��
			Statement GetUser=null;
			GetUser=createSta(GetUser);
			resuset=(Resultset) GetUser.executeQuery(sql);
			while(((ResultSet) resuset).next()) {
				String ID=((ResultSet) resuset).getString("userid");
				String username=((ResultSet) resuset).getString("username");
				String password= ((ResultSet) resuset).getString("password");
				String roleid = ((ResultSet) resuset).getString("roleid");
				getuser.setUserId(ID);
				getuser.setUserName(username);
				getuser.setPassword(password);
				getuser.setRoleId(roleid);
			}
			System.out.println("��ȡ�û���Ϣ�ɹ�");
			//�ر�ʵ��
			 CloseStatement(GetUser);
		} catch (Exception e) {
			System.out.println("��ȡ�û���Ϣʧ��");
		}
		return getuser;
	}
	/**ͨ���û����ƻ�ȡ�û����*/
	public String getUserIdForName(String username) {
		String getId="";
		String sql="select * from user where username='"+username+"'";
		try {
			//����ʵ��
			Statement GetUser=null;
			GetUser=createSta(GetUser);
			resuset=(Resultset) GetUser.executeQuery(sql);
			while(((ResultSet) resuset).next()) {
				getId=((ResultSet) resuset).getString("userid");
			}
			System.out.println("��ȡ�û���Ϣ�ɹ�");
			//�ر�ʵ��
			 CloseStatement(GetUser);
		} catch (Exception e) {
			System.out.println("��ȡ�û���Ϣʧ��");
		}
		return getId;
	}
	/**����û���Ϣ*/
	public boolean insertUser(user addobject) {
		boolean flag = false;
		String Number = "";
		// �жϱ����Ƿ�������ֵ�����Ƿ�Ϊ�գ�
		try {
			if ("".equals(this.GetMaxId())) {
				Number = "0";
			} else {
				Number = this.GetMaxId();
			}
		} catch (Exception e) {
			Number = "0";
		}
		String ID = String.valueOf(Integer.parseInt(Number)+1);
		String Name=addobject.getUserName();
		//Ĭ������Ϊ172056236
		String Password="172056236";
		String Roleid=addobject.getRoleId();
		String sql="insert into user value("+ID+",'"+Name+"','"+Password+"','"+Roleid+"')";
		try {
			//����ʵ��
			Statement adduser=null;
			adduser=createSta(adduser);
			adduser.execute(sql);
			flag = true;
			System.out.println("����û���Ϣ�ɹ�");
			//�ر�ʵ��
			CloseStatement(adduser);
		} catch (Exception e) {
			System.err.println("����û���Ϣʧ��");
			flag = false;
		}
		return flag;
	}
	/**ɾ���û���Ϣ*/
	public boolean deleteUser(String userid) {
		boolean flag = false;
		String sql="delete from user where userid="+userid;
		try {
			//����ʵ��
			Statement deluser=null;
			deluser=createSta(deluser);
			deluser.execute(sql);
			System.out.println("ɾ���û���Ϣ�ɹ�");
			//�ر�ʵ��
			CloseStatement(deluser);
			flag = true;
		} catch (Exception e) {
			System.err.println("ɾ���û���Ϣʧ��");
			flag = false;
		}
		return flag;
	}
	/**�޸��û���Ϣ*/
	public boolean updateUser(user updateuser) {
		boolean flag = false;
		String ID=updateuser.getUserId();
		String Name=updateuser.getUserName();
		String Password=updateuser.getPassword();
		String Roleid=updateuser.getRoleId();
		String sql="update user set userid='"+ID+"',username='"+Name+"',password='"+Password+"',roleid='"+Roleid+"' where userid='"+ID+"'";
		try {
			//����ʵ��
			Statement Updateuser=null;
			Updateuser=createSta(Updateuser);
			Updateuser.execute(sql);
			System.out.println("�޸��û���Ϣ�ɹ�");
			flag = true;
			//�ر�ʵ��
			CloseStatement(Updateuser);
		} catch (Exception e) {
			flag = false;
			System.err.println("�޸��û���Ϣʧ��");
		}
		return flag;
	}
	/** ��ȡ���Ŀͻ���� */
	public String GetMaxId() {
		List<Integer> listId = new ArrayList<Integer>();
		String sql = "select * from user";
		try {
			// ����ʵ��
			Statement GetStore = null;
			GetStore = createSta(GetStore);
			resuset = (Resultset) GetStore.executeQuery(sql);
			while (((ResultSet) resuset).next()) {
				listId.add(Integer.parseInt(((ResultSet) resuset).getString("userid")));
			}
			System.out.println("��ȡ��Ϣ�ɹ�");
			// �ر�ʵ��
			CloseStatement(GetStore);
		} catch (Exception e) {
			System.out.println("��ȡ��Ϣʧ��");
		}
		return Collections.max(listId).toString();
	}
}
