package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import entity.store;

public class DatabaseStore extends Database{
	
	/**���ݹ���Ա��Ż�ȡ�����̵����Ϣ*/
	public List<store> GetAllStoreByRegulatorId(String regulatorId) {
		List<store> AllStore = new ArrayList<store>();
		String sql="select * from store where regulatorid="+regulatorId;
		try {
			//����ʵ��
			Statement GetAllStore=null;
			GetAllStore=createSta(GetAllStore);
			resuset=(Resultset) GetAllStore.executeQuery(sql);
			while(((ResultSet) resuset).next()) {
				store object=new store();
				String storeid=((ResultSet) resuset).getString("storeid");
				String storename=((ResultSet) resuset).getString("storename");
				String regulatorid=((ResultSet) resuset).getString("regulatorid");
				object.setStoreId(storeid);
				object.setStoreName(storename);
				object.setRegulatorId(regulatorid);
				AllStore.add(object);
			}
			//�ر�ʵ��
			 CloseStatement(GetAllStore);
		} catch (Exception e) {
			System.out.println("��ȡ�û���ɫ����ʧ��");
		}
		return AllStore;
	}
	/**��ȡ���е��̵���Ϣ*/
	public List<store> ListStore(){
		List<store> listStore=new ArrayList<store>();
		String sql="select * from store";
		try {
			//����ʵ��
			Statement GetAllStore=null;
			GetAllStore=createSta(GetAllStore);
			resuset= (Resultset) GetAllStore.executeQuery(sql);
			 while(((ResultSet) resuset).next()){
				store object=new store();
				String storeid=((ResultSet) resuset).getString("storeid");
				String storename=((ResultSet) resuset).getString("storename");
				String regulatorid=((ResultSet) resuset).getString("regulatorid");
				object.setStoreId(storeid);
				object.setStoreName(storename);
				object.setRegulatorId(regulatorid);
				listStore.add(object);
		      }
			//�ر�ʵ��
			 CloseStatement(GetAllStore);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listStore;
	}
	/**ͨ���̵��Ż�ȡ���̵���Ϣ*/
	public store getStoreForId(String storeId) {
		store getstore=new store();
		String sql="select * from store where storeid="+storeId;
		try {
			//����ʵ��
			Statement GetStore=null;
			GetStore=createSta(GetStore);
			resuset=(Resultset) GetStore.executeQuery(sql);
			while(((ResultSet) resuset).next()) {
				String storeid=((ResultSet) resuset).getString("storeid");
				String storename=((ResultSet) resuset).getString("storename");
				String regulatorid=((ResultSet) resuset).getString("regulatorid");
				getstore.setStoreId(storeid);
				getstore.setStoreName(storename);
				getstore.setRegulatorId(regulatorid);
			}
			System.out.println("��ȡ�̵���Ϣ�ɹ�");
			//�ر�ʵ��
			 CloseStatement(GetStore);
		} catch (Exception e) {
			System.out.println("��ȡ�̵���Ϣʧ��");
		}
		return getstore;
	}
	/**����̵���Ϣ*/
	public void insertStore(store addobject) {
		String ID=addobject.getStoreId();
		String Name=addobject.getStoreName();
		String RegulatorId=addobject.getRegulatorId();
		String sql="insert into store value("+ID+",'"+Name+"','"+RegulatorId+"')";
		try {
			//����ʵ��
			Statement addstore=null;
			addstore=createSta(addstore);
			addstore.execute(sql);
			System.out.println("����̵���Ϣ�ɹ�");
			//�ر�ʵ��
			CloseStatement(addstore);
		} catch (Exception e) {
			System.out.println("����̵���Ϣʧ��");
		}
	}
	/**ɾ���û���Ϣ*/
	public void deleteStore(String StoreId) {
		String sql="delete from store where storeid="+StoreId;
		try {
			//����ʵ��
			Statement delstore=null;
			delstore=createSta(delstore);
			delstore.execute(sql);
			//�ر�ʵ��
			CloseStatement(delstore);
			System.out.println("ɾ���̵���Ϣ�ɹ�");
		} catch (Exception e) {
			System.out.println("ɾ���̵���Ϣʧ��");
		}
	}
	/**�޸��û���Ϣ*/
	public void updateStore(store updatestore) {
		String ID=updatestore.getStoreId();
		String Name=updatestore.getStoreName();
		String RegulatorId=updatestore.getRegulatorId();
		String sql="update store set storeid='"+ID+"',storename='"+Name+"',regulatorid='"+RegulatorId+"' where storeid='"+ID+"'";
		try {
			//����ʵ��
			Statement Updatestore=null;
			Updatestore=createSta(Updatestore);
			Updatestore.execute(sql);
			System.out.println("�޸��̵���Ϣ�ɹ�");
			//�ر�ʵ��
			CloseStatement(Updatestore);
		} catch (Exception e) {
			System.out.println("�޸��̵���Ϣʧ��");
		}	
	}
}
