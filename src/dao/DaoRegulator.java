package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.mysql.cj.protocol.Resultset;

import entity.regulator;
import jdbc.Database;

public class DaoRegulator extends Database {

	/**��ȡ���еĹ���Ա��ɫ����*/
	public List<String> ListRegulatorRoleName() {
		List<String> ListRoleName = new ArrayList<String>();
		String sql = "select * from regulator_role";
		try {
			// ����ʵ��
			Statement seleectReguRoleName = null;
			seleectReguRoleName = createSta(seleectReguRoleName);
			resuset = (Resultset) seleectReguRoleName.executeQuery(sql);
			while (((ResultSet) resuset).next()) {
				String name=((ResultSet) resuset).getString("regulator_role_name");
				ListRoleName.add(name);
			}
			// �ر�ʵ��
			CloseStatement(seleectReguRoleName);
		} catch (Exception e) {
			System.out.println("��ȡ����Ա��ɫ����ʧ��");
		}
		return ListRoleName;
	}
	
	/** ���ݹ���Ա��ɫ��Ż�ȡ����Ա��ɫ���� */
	public String GetRegulatorRoleName(String regulatorRoleid) {
		String RoleName = null;
		String sql = "select * from regulator_role where regulator_role_id='" + regulatorRoleid+"'";
		try {
			// ����ʵ��
			Statement seleectReguRoleName = null;
			seleectReguRoleName = createSta(seleectReguRoleName);
			resuset = (Resultset) seleectReguRoleName.executeQuery(sql);
			while (((ResultSet) resuset).next()) {
				RoleName = ((ResultSet) resuset).getString("regulator_role_name");
			}
			// �ر�ʵ��
			CloseStatement(seleectReguRoleName);
		} catch (Exception e) {
			System.out.println("��ȡ����Ա��ɫ����ʧ��");
		}
		return RoleName;
	}
	/**���ݹ���Ա��ɫ���ƻ�ȡ����Ա��ɫ���*/
	public String GetRegulatorRoleId(String regulatorRoleName) {
		String RoleId = null;
		String sql = "select * from regulator_role where regulator_role_name='" + regulatorRoleName+"'";
		try {
			// ����ʵ��
			Statement seleectReguRoleName = null;
			seleectReguRoleName = createSta(seleectReguRoleName);
			resuset = (Resultset) seleectReguRoleName.executeQuery(sql);
			while (((ResultSet) resuset).next()) {
				RoleId = ((ResultSet) resuset).getString("regulator_role_id");
			}
			// �ر�ʵ��
			CloseStatement(seleectReguRoleName);
		} catch (Exception e) {
			System.out.println("��ȡ����Ա��ɫ����ʧ��");
		}
		return RoleId;
	}

	/** ��ȡ���еĹ���Ա��Ϣ */
	public List<regulator> ListRegulator() {
		List<regulator> listregulators = new ArrayList<regulator>();
		String sql = "select * from regulator";
		try {
			// ����ʵ��
			Statement SelectStd = null;
			SelectStd = createSta(SelectStd);
			resuset = (Resultset) SelectStd.executeQuery(sql);
			while (((ResultSet) resuset).next()) {
				String regulatorid = ((ResultSet) resuset).getString("regulatorid");
				String regulatorname = ((ResultSet) resuset).getString("regulatorname");
				String password = ((ResultSet) resuset).getString("password");
				String regulatorRoleId = ((ResultSet) resuset).getString("regulator_role_id");
				String storeId= ((ResultSet) resuset).getString("storeid");
				regulator regu = new regulator();
				regu.setRegulatorId(regulatorid);
				regu.setRegulatorName(regulatorname);
				regu.setPassword(password);
				regu.setRegulatorRoleId(regulatorRoleId);
				regu.setStoreId(storeId);
				listregulators.add(regu);
			}
			// �ر�ʵ��
			CloseStatement(SelectStd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listregulators;
	}
	/**�����ŵ��Ż�ȡԱ����Ϣ*/
	public List<regulator> listRegulatorByStoreId(String StoreId){
		List<regulator> list=new ArrayList<regulator>();
		String sql = "select * from regulator where storeid='"+StoreId+"'";
		try {
			// ����ʵ��
			Statement SelectStd = null;
			SelectStd = createSta(SelectStd);
			resuset = (Resultset) SelectStd.executeQuery(sql);
			while (((ResultSet) resuset).next()) {
				String regulatorid = ((ResultSet) resuset).getString("regulatorid");
				String regulatorname = ((ResultSet) resuset).getString("regulatorname");
				String password = ((ResultSet) resuset).getString("password");
				String regulatorRoleId = ((ResultSet) resuset).getString("regulator_role_id");
				String storeId= ((ResultSet) resuset).getString("storeid");
				regulator regu = new regulator();
				regu.setRegulatorId(regulatorid);
				regu.setRegulatorName(regulatorname);
				regu.setPassword(password);
				regu.setRegulatorRoleId(regulatorRoleId);
				regu.setStoreId(storeId);
				list.add(regu);
			}
			// �ر�ʵ��
			CloseStatement(SelectStd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**��ȡ���е���ͨԱ������Ϣ*/
	public List<regulator> listOrdinaryRegulators(){
		List<regulator> list=new ArrayList<regulator>();
		String sql = "select * from regulator where regulator_role_id='3'";
		try {
			// ����ʵ��
			Statement SelectStd = null;
			SelectStd = createSta(SelectStd);
			resuset = (Resultset) SelectStd.executeQuery(sql);
			while (((ResultSet) resuset).next()) {
				String regulatorid = ((ResultSet) resuset).getString("regulatorid");
				String regulatorname = ((ResultSet) resuset).getString("regulatorname");
				String password = ((ResultSet) resuset).getString("password");
				String regulatorRoleId = ((ResultSet) resuset).getString("regulator_role_id");
				String storeId= ((ResultSet) resuset).getString("storeid");
				regulator regu = new regulator();
				regu.setRegulatorId(regulatorid);
				regu.setRegulatorName(regulatorname);
				regu.setPassword(password);
				regu.setRegulatorRoleId(regulatorRoleId);
				regu.setStoreId(storeId);
				list.add(regu);
			}
			// �ر�ʵ��
			CloseStatement(SelectStd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/** ͨ������Ա��Ż�ȡ����Ա��Ϣ */
	public regulator GetRegulatorForId(String regulatorId) {
		regulator regu = new regulator();
		String sql = "select * from regulator where regulatorid='" + regulatorId + "'";
		try {
			// ����ʵ��
			Statement GetRegulator = null;
			GetRegulator = createSta(GetRegulator);
			resuset = (Resultset) GetRegulator.executeQuery(sql);
			while (((ResultSet) resuset).next()) {
				String ID = ((ResultSet) resuset).getString("regulatorid");
				String Name = ((ResultSet) resuset).getString("regulatorname");
				String password = ((ResultSet) resuset).getString("password");
				String regulatorRoleId = ((ResultSet) resuset).getString("regulator_role_id");
				String storeId= ((ResultSet) resuset).getString("storeid");
				regu.setRegulatorId(ID);
				regu.setRegulatorName(Name);
				regu.setPassword(password);
				regu.setRegulatorRoleId(regulatorRoleId);
				regu.setStoreId(storeId);
			}
			System.out.println("��ȡ����Ա��Ϣ�ɹ�");
			// �ر�ʵ��
			CloseStatement(GetRegulator);
		} catch (Exception e) {
			System.out.println("��ȡ����Ա��Ϣʧ��");
		}
		return regu;
	}

	/** ͨ������Ա���ƻ�ȡ����Ա��� */
	public String GetIdForName(String regulatorName) {
		String regulatorId = "";
		String sql = "select *  from regulator where regulatorname='" + regulatorName + "'";
		try {
			// ����ʵ��
			Statement GetRegulator = null;
			GetRegulator = createSta(GetRegulator);
			resuset = (Resultset) GetRegulator.executeQuery(sql);
			while (((ResultSet) resuset).next()) {
				regulatorId = ((ResultSet) resuset).getString("regulatorid");
			}
			System.out.println("��ȡ����Ա��Ϣ�ɹ�");
			// �ر�ʵ��
			CloseStatement(GetRegulator);
		} catch (Exception e) {
			System.out.println("��ȡ����Ա��Ϣʧ��");
		}
		return regulatorId;
	}

	/** ��ӹ���Ա��Ϣ */
	public boolean insertRegulator(regulator regulator) {
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
		String Name = regulator.getRegulatorName();
		// Ĭ������Ϊ172056236
		String Password = "172056236";
		String Roleid = regulator.getRegulatorRoleId();
		String Storeid=regulator.getStoreId();
		String sql = "insert into regulator value('" + ID + "','" + Name + "','" + Password + "','" + Roleid +",'"+Storeid+"')";
		try {
			// ����ʵ��
			Statement AddRegu = null;
			AddRegu = createSta(AddRegu);
			AddRegu.execute(sql);
			flag = true;
			System.out.println("��ӹ���Ա��Ϣ�ɹ�");
			// �ر�ʵ��
			CloseStatement(AddRegu);
		} catch (Exception e) {
			System.out.println("��ӹ���Ա��Ϣʧ��");
			flag = false;
		}
		return flag;
	}

	/** ɾ������Ա��Ϣ */
	public boolean deleteRegulator(String regulatorId) {
		boolean flag = false;
		String sql = "delete from regulator where regulatorid=" + regulatorId;
		try {
			// ����ʵ��
			Statement delStatement = null;
			delStatement = createSta(delStatement);
			delStatement.execute(sql);
			// �ر�ʵ��
			CloseStatement(delStatement);
			flag = true;
			System.out.println("ɾ������Ա��Ϣ�ɹ�");
		} catch (Exception e) {
			flag = false;
			System.out.println("ɾ������Ա��Ϣʧ��");
		}
		return flag;
	}

	/** �޸Ĺ���Ա��Ϣ */
	public boolean updateRegulator(regulator regulator) {
		boolean flag = false;
		String ID = regulator.getRegulatorId();
		String Name = regulator.getRegulatorName();
		String Password = regulator.getPassword();
		String Roleid = regulator.getRegulatorRoleId();
		String Storeid=regulator.getStoreId();
		
		String sql = "update regulator set regulatorid='" + ID + "',regulatorname='" + Name + "',password='" + Password
				+ "',regulator_role_id='" + Roleid + "',storeid='"+Storeid+"' where regulatorid='" + ID + "'";
		try {
			// ����ʵ��
			Statement UpdateRegu = null;
			UpdateRegu = createSta(UpdateRegu);
			UpdateRegu.execute(sql);
			flag = true;
			System.out.println("�޸Ĺ���Ա��Ϣ�ɹ�");
			// �ر�ʵ��
			CloseStatement(UpdateRegu);
		} catch (Exception e) {
			flag = false;
			System.out.println("�޸Ĺ���Ա��Ϣʧ��");
		}
		return flag;
	}

	/** ��ȡ����Ա��ŵ����ֵ */
	public String GetMaxId() {
		List<Integer> listId = new ArrayList<Integer>();
		String sql = "select * from regulator";
		try {
			// ����ʵ��
			Statement GetRegulator = null;
			GetRegulator = createSta(GetRegulator);
			resuset = (Resultset) GetRegulator.executeQuery(sql);
			while (((ResultSet) resuset).next()) {
				listId.add(Integer.parseInt(((ResultSet) resuset).getString("regulatorid")));
			}
			System.out.println("��ȡ��Ϣ�ɹ�");
			// �ر�ʵ��
			CloseStatement(GetRegulator);
		} catch (Exception e) {
			System.out.println("��ȡ��Ϣʧ��");
		}
		return Collections.max(listId).toString();
	}
}
