package service;

import java.util.List;

import entity.regulator;

public interface RegulatorService {

	/** ��ȡ���еĹ���Ա��ɫ���� */
	public List<String> ListRegulatorRoleName();

	/** ���ݹ���Ա��ɫ��Ż�ȡ����Ա��ɫ���� */
	public String GetRegulatorRoleName(String regulatorRoleid);

	/** ���ݹ���Ա��ɫ���ƻ�ȡ����Ա��ɫ��� */
	public String GetRegulatorRoleId(String regulatorRoleName);

	/** ��ȡ���еĹ���Ա��Ϣ */
	public List<regulator> ListRegulator();

	/** �����ŵ��Ż�ȡԱ����Ϣ */
	public List<regulator> listRegulatorByStoreId(String StoreId);

	/** ��ȡ���е���ͨԱ������Ϣ */
	public List<regulator> listOrdinaryRegulators();

	/** ͨ������Ա��Ż�ȡ����Ա��Ϣ */
	public regulator GetRegulatorForId(String regulatorId);

	/** ͨ������Ա���ƻ�ȡ����Ա��� */
	public String GetIdForName(String regulatorName);

	/** ��ӹ���Ա��Ϣ */
	public boolean insertRegulator(regulator regulator);

	/** ɾ������Ա��Ϣ */
	public boolean deleteRegulator(String regulatorId);

	/** �޸Ĺ���Ա��Ϣ */
	public boolean updateRegulator(regulator regulator);

	/** ��ȡ����Ա��ŵ����ֵ */
	public String GetMaxId();

	/** �ر�ҵ���߼� */
	public void CloseRegulatorService();
}
