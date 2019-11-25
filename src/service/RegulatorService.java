package service;

import java.util.List;


import entity.regulator;
import jdbc.DatabaseRegulator;
/**ҵ���߼���*/
public class RegulatorService extends DatabaseRegulator{

	DatabaseRegulator DataRegulator;
	public RegulatorService() {
		DataRegulator=new DatabaseRegulator();
	}
	/**���ݹ���Ա��ɫ��Ż�ȡ����Ա��ɫ����*/
	public String GetRegulatorRoleName(String regulatorRoleid) {
		return DataRegulator.GetRegulatorRoleName(regulatorRoleid);
	}
	/**��ȡ���еĹ���Ա��Ϣ*/
	public List<regulator> ListRegulator(){
		return DataRegulator.ListRegulator();
	}
	/**ͨ������Ա��Ż�ȡ����Ա��Ϣ*/
	public regulator GetRegulatorForId(String regulatorId) {
		return DataRegulator.GetRegulatorForId(regulatorId);
	}
	/**��ӹ���Ա��Ϣ*/
	public boolean insertRegulator(regulator regulator) {
		return DataRegulator.insertRegulator(regulator);
	}
	/**ɾ������Ա��Ϣ*/
	public boolean deleteRegulator(String regulatorId) {
		return DataRegulator.deleteRegulator(regulatorId);
	}
	/**�޸Ĺ���Ա��Ϣ*/
	public boolean updateRegulator(regulator regulator) {
		return DataRegulator.updateRegulator(regulator);
	}
	/**�ر�ҵ���߼�*/
	public void CloseRegulatorService() {
		DataRegulator.CloseDatabase();
	}
}
