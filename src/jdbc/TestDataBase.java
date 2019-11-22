package jdbc;

import java.util.List;

import org.junit.Test;

import entity.regulator;
import entity.store;
import entity.user;


public class TestDataBase {
	/******************************************************DatabaseRegulator*/
	@Test
	public void TestRegulator() {
		try {
			DatabaseRegulator Database=new DatabaseRegulator();
			System.out.println();
			/**���ݹ���Ա��ɫ��Ż�ȡ����Ա��ɫ����*/
			System.out.println("���ݹ���Ա��ɫ��Ż�ȡ����Ա��ɫ����");
			System.out.println(Database.GetRegulatorRoleName("2"));
			System.out.println();
			/**���ݹ���Ա��ɫ���ƻ�ȡ����Ա��ɫ���*/
			System.out.println("���ݹ���Ա��ɫ���ƻ�ȡ����Ա��ɫ���");
			System.out.println(Database.GetRegulatorRoleId("��������Ա"));
			System.out.println();
			/**��ӹ���Ա��Ϣ*/
			System.out.println("��ӹ���Ա��Ϣ");
			regulator addobject=new regulator();
			addobject.setRegulatorId("10003");
			addobject.setRegulatorName("���϶�");
			addobject.setRegulatorRoleId("1");
			Database.insertRegulator(addobject);
			System.out.println();
			/**��ȡ���еĹ���Ա��Ϣ*/
			System.out.println("��ȡ���еĹ���Ա��Ϣ");
			List<regulator> list=Database.ListRegulator();
			for(int i=0;i<list.size();i++)
			{
				regulator object=list.get(i);
				System.out.println(" ID:"+object.getRegulatorId()+" Name:"+object.getRegulatorName()+" Password:"+object.getPassword()+" RegulatorRoleId:"+object.getRegulatorRoleId());
			}
			System.out.println();
			/**�޸Ĺ���Ա��Ϣ*/
			System.out.println("�޸Ĺ���Ա��Ϣ");
			regulator updateRegulator=new regulator();
			updateRegulator.setRegulatorId("10003");
			updateRegulator.setRegulatorName("���Ϻ�");
			updateRegulator.setPassword("520-96");
			updateRegulator.setRegulatorRoleId("1");
			Database.updateRegulator(updateRegulator);
			System.out.println();
			/**ͨ������Ա��Ż�ȡ����Ա��Ϣ*/
			System.out.println("ͨ������Ա��Ż�ȡ����Ա��Ϣ");
			regulator getRegu=Database.GetRegulatorForId("10003");
			System.out.println(" ID:"+getRegu.getRegulatorId()+" Name:"+getRegu.getRegulatorName()+" Password:"+getRegu.getPassword()+" RegulatorRoleId:"+getRegu.getRegulatorRoleId());
			System.out.println();
			/**ͨ������Ա���ƻ�ȡ����Ա��Ϣ*/
			System.out.println("ͨ������Ա���ƻ�ȡ����Ա��Ϣ");
			System.out.println(Database.GetIdForName("������"));
			System.out.println();
			/**ɾ������Ա��Ϣ*/
			System.out.println("ɾ������Ա��Ϣ");
			Database.deleteRegulator("10003");
			System.out.println();
			/**��ȡ������Ա���*/
			System.out.println("��ȡ������Ա���");
			System.out.println(Database.GetMaxId());
			System.out.println();
			Database.CloseDatabase();
		} catch (Exception e) {
			System.out.println("���Է����쳣");
		}
	}
	/******************************************************DatabaseUser*/
	@Test
	public void TestUser() {
		try {
			DatabaseUser Database=new DatabaseUser();
			System.out.println();
			/**�����û�Ա��ɫ��Ż�ȡ�û���ɫ����*/
			System.out.println("�����û�Ա��ɫ��Ż�ȡ�û���ɫ����");
			System.out.println(Database.GetUserRoleName("1"));
			System.out.println();
			/**����û���Ϣ*/
			System.out.println("����û���Ϣ");
			user addobject=new user();
			addobject.setUserId("1720562005");
			addobject.setUserName("ǧ��");
			addobject.setRoleId("1");
			Database.insertUser(addobject);
			System.out.println();
			/**��ȡ���е��û���Ϣ*/
			System.out.println("��ȡ���е��û���Ϣ");
			List<user> list=Database.ListUser();
			for(int i=0;i<list.size();i++)
			{
				user object=list.get(i);
				System.out.println(" ID:"+object.getUserId()+" Name:"+object.getUserName()+" Password:"+object.getPassword()+" RoleId:"+object.getRoleId());
			}
			System.out.println();
			/**�޸��û���Ϣ*/
			System.out.println("�޸��û���Ϣ");
			user updateUser=new user();
			updateUser.setUserId("1720562005");
			updateUser.setUserName("ǧ��");
			updateUser.setPassword("520-96");
			updateUser.setRoleId("1");
			Database.updateUser(updateUser);
			System.out.println();
			/**ͨ���û���Ż�ȡ�û���Ϣ*/
			System.out.println("ͨ���û���Ż�ȡ�û���Ϣ");
			user getuser=Database.getUserForId("1720562005");
			System.out.println(" ID:"+getuser.getUserId()+" Name:"+getuser.getUserName()+" Password:"+getuser.getPassword()+" RegulatorRoleId:"+getuser.getRoleId());
			System.out.println();
			/**ɾ���û���Ϣ*/
			System.out.println("ɾ���û���Ϣ");
			Database.deleteUser("1720562005");
			System.out.println();
			Database.CloseDatabase();
		} catch (Exception e) {
			System.out.println("���Է����쳣");
		}
	}
	
	/******************************************************DatabaseStore*/
	@Test
	public void TestStore() {
		try {
			DatabaseStore Database=new DatabaseStore();
			System.out.println();
			/**���ݹ���Ա��Ż�ȡ�����̵����Ϣ*/
			System.out.println("���ݹ���Ա��Ż�ȡ�����̵����Ϣ");
			List<store> allstore=Database.GetAllStoreByRegulatorId("10001");
			for(int i=0;i<allstore.size();i++) {
				store entity=allstore.get(i);
				System.out.println("StoreId:"+entity.getStoreId()+" StoreName:"+entity.getStoreName()+" RegulatorId:"+entity.getRegulatorId());
			}
			System.out.println();
			/**����̵���Ϣ*/
			System.out.println("����̵���Ϣ");
			store addobject=new store();
			addobject.setStoreId("4");
			addobject.setStoreName("�����󰲷۵�");
			addobject.setRegulatorId("10001");
			Database.insertStore(addobject);
			System.out.println();
			/**��ȡ���е��̵���Ϣ*/
			System.out.println("��ȡ���е��̵���Ϣ");
			List<store> list=Database.ListStore();
			for(int i=0;i<list.size();i++)
			{
				store object=list.get(i);
				System.out.println("StoreID:"+object.getStoreId()+" StoreName:"+object.getStoreName()+" RegulatorId:"+object.getRegulatorId());
			}
			System.out.println();
			/**�޸��̵���Ϣ*/
			System.out.println("�޸��̵���Ϣ");
			store updateStore=new store();
			updateStore.setStoreId("4");
			updateStore.setStoreName("���д����ŵ�");
			updateStore.setRegulatorId("10001");
			Database.updateStore(updateStore);
			System.out.println();
			/**ͨ���̵��Ż�ȡ�̵���Ϣ*/
			System.out.println("ͨ���̵��Ż�ȡ�̵���Ϣ");
			store getstore=Database.getStoreForId("4");
			System.out.println("StoreID:"+getstore.getStoreId()+" StoreName:"+getstore.getStoreName()+" RegulatorId:"+getstore.getRegulatorId());
			System.out.println();
			/**ɾ���̵���Ϣ*/
			System.out.println("ɾ���̵���Ϣ");
			Database.deleteStore("4");
			System.out.println();
			/**��ȡ����̵���*/
			System.out.println("��ȡ����̵���");
			System.out.println(Database.GetMaxId());
			System.out.println();
			Database.CloseDatabase();
		} catch (Exception e) {
			System.out.println("���Է����쳣");
		}
	}
}
