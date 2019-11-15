package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.mysql.cj.protocol.Resultset;

import entity.regulator;

public class DatabaseRegulator extends Database{
	
	/**���ݹ���Ա��ɫ��Ż�ȡ����Ա��ɫ����*/
	public String GetRegulatorRoleName(String regulatorRoleid) {
		String RoleName = null;
		String sql="select * from regulator_role where regulator_role_id="+regulatorRoleid;
		try {
			//����ʵ��
			Statement seleectReguRoleName=null;
			seleectReguRoleName=createSta(seleectReguRoleName);
			resuset=(Resultset) seleectReguRoleName.executeQuery(sql);
			while(((ResultSet) resuset).next()) {
				RoleName=((ResultSet) resuset).getString("regulator_role_name");
			}
			//�ر�ʵ��
			 CloseStatement(seleectReguRoleName);
		} catch (Exception e) {
			System.out.println("��ȡ����Ա��ɫ����ʧ��");
		}
		return RoleName;
	}
	/**��ȡ���еĹ���Ա��Ϣ*/
	public List<regulator> ListRegulator(){
		List<regulator> listregulators=new ArrayList<regulator>();
		String sql="select * from regulator";
		try {
			//����ʵ��
			Statement SelectStd=null;
			SelectStd=createSta(SelectStd);
			resuset= (Resultset) SelectStd.executeQuery(sql);
			 while(((ResultSet) resuset).next()){
		         String regulatorid  = ((ResultSet) resuset).getString("regulatorid");
		         String regulatorname = ((ResultSet) resuset).getString("regulatorname");
		         String password= ((ResultSet) resuset).getString("password");
		         String regulatorRoleId = ((ResultSet) resuset).getString("regulator_role_id");
		         regulator regu=new regulator();
		         regu.setRegulatorId(regulatorid);
		         regu.setRegulatorName(regulatorname);
		         regu.setPassword(password);
		         regu.setRegulatorRoleId(regulatorRoleId);
		         listregulators.add(regu);
		      }
			//�ر�ʵ��
			 CloseStatement(SelectStd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listregulators;
	}
	/**ͨ������Ա��Ż�ȡ����Ա��Ϣ*/
	public regulator GetRegulatorForId(String regulatorId) {
		regulator regu=new regulator();
		String sql="select * from regulator where regulatorid="+regulatorId;
		try {
			//����ʵ��
			Statement GetRegulator=null;
			GetRegulator=createSta(GetRegulator);
			resuset=(Resultset) GetRegulator.executeQuery(sql);
			while(((ResultSet) resuset).next()) {
				String ID=((ResultSet) resuset).getString("regulatorid");
				String Name=((ResultSet) resuset).getString("regulatorname");
				String password= ((ResultSet) resuset).getString("password");
				String regulatorRoleId = ((ResultSet) resuset).getString("regulator_role_id");
			    regu.setRegulatorId(ID);
			    regu.setRegulatorName(Name);
			    regu.setPassword(password);
			    regu.setRegulatorRoleId(regulatorRoleId);
			}
			System.out.println("��ȡ����Ա��Ϣ�ɹ�");
			//�ر�ʵ��
			 CloseStatement(GetRegulator);
		} catch (Exception e) {
			System.out.println("��ȡ����Ա��Ϣʧ��");
		}
		return regu;
	}
	/**��ӹ���Ա��Ϣ*/
	public void insertRegulator(regulator regulator) {
		String ID=regulator.getRegulatorId();
		String Name=regulator.getRegulatorName();
		//Ĭ������Ϊ172056236
		String Password="172056236";
		String Roleid=regulator.getRegulatorRoleId();
		
		String sql="insert into regulator value("+ID+",'"+Name+"','"+Password+"','"+Roleid+"')";
		try {
			//����ʵ��
			Statement AddRegu=null;
			AddRegu=createSta(AddRegu);
			AddRegu.execute(sql);
			System.out.println("��ӹ���Ա��Ϣ�ɹ�");
			//�ر�ʵ��
			CloseStatement(AddRegu);
		} catch (Exception e) {
			System.out.println("��ӹ���Ա��Ϣʧ��");
		}
	}
	/**ɾ������Ա��Ϣ*/
	public void deleteRegulator(String regulatorId) {
		String sql="delete from regulator where regulatorid="+regulatorId;
		try {
			//����ʵ��
			Statement delStatement=null;
			delStatement=createSta(delStatement);
			delStatement.execute(sql);
			//�ر�ʵ��
			CloseStatement(delStatement);
			System.out.println("ɾ������Ա��Ϣ�ɹ�");
		} catch (Exception e) {
			System.out.println("ɾ������Ա��Ϣʧ��");
		}
	}
	/**�޸Ĺ���Ա��Ϣ*/
	public void updateRegulator(regulator regulator) {
		String ID=regulator.getRegulatorId();
		String Name=regulator.getRegulatorName();
		String Password=regulator.getPassword();
		String Roleid=regulator.getRegulatorRoleId();
		
		String sql="update regulator set regulatorid='"+ID+"',regulatorname='"+Name+"',password='"+Password+"',regulator_role_id='"+Roleid+"' where regulatorid='"+ID+"'";
		try {
			//����ʵ��
			Statement UpdateRegu=null;
			UpdateRegu=createSta(UpdateRegu);
			UpdateRegu.execute(sql);
			System.out.println("�޸Ĺ���Ա��Ϣ�ɹ�");
			//�ر�ʵ��
			CloseStatement(UpdateRegu);
		} catch (Exception e) {
			System.out.println("�޸Ĺ���Ա��Ϣʧ��");
		}	
	}
}
