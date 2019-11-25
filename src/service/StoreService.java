package service;

import java.util.List;

import entity.store;

public interface StoreService {

	/** ��ȡ���е��ŵ����� */
	public List<String> ListStoresName();

	/** ���ݹ���Ա��Ż�ȡ�����̵����Ϣ */
	public List<store> GetAllStoreByRegulatorId(String regulatorId);

	/** ��ȡ���е��̵���Ϣ */
	public List<store> ListStore();

	/** ͨ���̵��Ż�ȡ�̵���Ϣ */
	public store getStoreForId(String storeId);

	/** ͨ���ŵ����ƻ�ȡ�̵��� */
	public String getIdForName(String storeName);

	/** ����̵���Ϣ */
	public boolean insertStore(store addobject);

	/** ɾ���̵���Ϣ */
	public boolean deleteStore(String StoreId);

	/** �޸��û���Ϣ */
	public boolean updateStore(store updatestore);

	/** ��ȡ�����̵��� */
	public String GetMaxId();

	/** �ر�StoreService */
	public void CloseStoreService();
}
