package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;
import entity.user;

public class DatabaseUser extends Database{

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
	/**����û���Ϣ*/
	public void insertUser(user addobject) {
		String ID=addobject.getUserId();
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
			System.out.println("����û���Ϣ�ɹ�");
			//�ر�ʵ��
			CloseStatement(adduser);
		} catch (Exception e) {
			System.out.println("����û���Ϣʧ��");
		}
	}
	/**ɾ���û���Ϣ*/
	public void deleteUser(String userid) {
		String sql="delete from user where userid="+userid;
		try {
			//����ʵ��
			Statement deluser=null;
			deluser=createSta(deluser);
			deluser.execute(sql);
			//�ر�ʵ��
			CloseStatement(deluser);
			System.out.println("ɾ���û���Ϣ�ɹ�");
		} catch (Exception e) {
			System.out.println("ɾ���û���Ϣʧ��");
		}
	}
	/**�޸��û���Ϣ*/
	public void updateUser(user updateuser) {
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
			//�ر�ʵ��
			CloseStatement(Updateuser);
		} catch (Exception e) {
			System.out.println("�޸��û���Ϣʧ��");
		}	
	}
}
