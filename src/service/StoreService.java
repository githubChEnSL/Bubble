package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import entity.store;
import jdbc.DatabaseStore;

public class StoreService extends DatabaseStore{
	@Autowired
	DatabaseStore dataStore;
	/**���ݹ���Ա��Ż�ȡ�����̵����Ϣ*/
	public List<store> GetAllStoreByRegulatorId(String regulatorId) {
		return dataStore.GetAllStoreByRegulatorId(regulatorId);
	}
	/**��ȡ���е��̵���Ϣ*/
	public List<store> ListStore(){
		return dataStore.ListStore();
	}
	/**ͨ���̵��Ż�ȡ���̵���Ϣ*/
	public store getStoreForId(String storeId) {
		return dataStore.getStoreForId(storeId);
	}
	/**����̵���Ϣ*/
	public boolean insertStore(store addobject) {
		return dataStore.insertStore(addobject);
	}
	/**ɾ���û���Ϣ*/
	public boolean deleteStore(String StoreId) {
		return dataStore.deleteStore(StoreId);
	}
	/**�޸��û���Ϣ*/
	public boolean updateStore(store updatestore) {
		return dataStore.updateStore(updatestore);
	}
	/**�ر�*/
	public void close() {
		dataStore.CloseDatabase();
	}
	
}
