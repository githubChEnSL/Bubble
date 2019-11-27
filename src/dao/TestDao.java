package dao;

import java.util.List;

import org.junit.Test;

import entity.regulator;
import entity.store;
import entity.user;

/**
 * TestDao类 测试Dao的数据访问操作
 * 
 * @author chenshaolei 2019年11月27日 上午11:38:40
 */
public class TestDao {

	/**
	 * 测试TestRegulator
	 */
	@Test
	public void TestRegulator() {
		try {
			DaoRegulator Database = new DaoRegulator();
//			System.out.println();
//			/**根据管理员角色编号获取管理员角色名称*/
//			System.out.println("根据管理员角色编号获取管理员角色名称");
//			System.out.println(Database.GetRegulatorRoleName("2"));
//			System.out.println();
//			/**根据管理员角色名称获取管理员角色编号*/
//			System.out.println("根据管理员角色名称获取管理员角色编号");
//			System.out.println(Database.GetRegulatorRoleId("超级管理员"));
//			System.out.println();
//			/**添加管理员信息*/
			System.out.println("添加管理员信息");
			regulator addobject = new regulator();
			addobject.setRegulatorId("10003");
			addobject.setRegulatorName("西瓜儿");
			addobject.setRegulatorRoleId("1");
			addobject.setStoreId("1");
			Database.insertRegulator(addobject);
			System.out.println();
			/** 获取所有的管理员信息 */
			System.out.println("获取所有的管理员信息");
			List<regulator> list = Database.ListRegulator();
			for (int i = 0; i < list.size(); i++) {
				regulator object = list.get(i);
				System.out.println(" ID:" + object.getRegulatorId() + " Name:" + object.getRegulatorName()
						+ " Password:" + object.getPassword() + " RegulatorRoleId:" + object.getRegulatorRoleId()
						+ " StoreId:" + object.getStoreId());
			}
			System.out.println();
//			/** 根据门店编号获取管理员信息 */
//			List<regulator> list2=Database.listRegulatorByStoreId("2");
//			for (int i = 0; i < list2.size(); i++) {
//				regulator object = list2.get(i);
//				System.out.println(" ID:" + object.getRegulatorId() + " Name:" + object.getRegulatorName()
//						+ " Password:" + object.getPassword() + " RegulatorRoleId:" + object.getRegulatorRoleId()
//						+ " StoreId:" + object.getStoreId());
//			}
//			System.out.println();
//			/**修改管理员信息*/
//			System.out.println("修改管理员信息");
//			regulator updateRegulator=new regulator();
//			updateRegulator.setRegulatorId("10003");
//			updateRegulator.setRegulatorName("西瓜嘿");
//			updateRegulator.setPassword("520-96");
//			updateRegulator.setRegulatorRoleId("1");
//			Database.updateRegulator(updateRegulator);
//			System.out.println();
//			/**通过管理员编号获取管理员信息*/
//			System.out.println("通过管理员编号获取管理员信息");
//			regulator getRegu=Database.GetRegulatorForId("10003");
//			System.out.println(" ID:"+getRegu.getRegulatorId()+" Name:"+getRegu.getRegulatorName()+" Password:"+getRegu.getPassword()+" RegulatorRoleId:"+getRegu.getRegulatorRoleId());
//			System.out.println();
//			/**通过管理员名称获取管理员信息*/
//			System.out.println("通过管理员名称获取管理员信息");
//			System.out.println(Database.GetIdForName("陈少磊"));
//			System.out.println();
//			/**删除管理员信息*/
//			System.out.println("删除管理员信息");
//			Database.deleteRegulator("10003");
//			System.out.println();
//			/**获取最大管理员编号*/
//			System.out.println("获取最大管理员编号");
//			System.out.println(Database.GetMaxId());
//			System.out.println();
//			Database.CloseDatabase();
		} catch (Exception e) {
			System.out.println("测试发生异常");
		}
	}

	/**
	 * 测试TestUser
	 */
	@Test
	public void TestUser() {
		try {
			DaoUser Database = new DaoUser();
			System.out.println();
			/** 根据用户员角色编号获取用户角色名称 */
			System.out.println("根据用户员角色编号获取用户角色名称");
			System.out.println(Database.GetUserRoleName("1"));
			System.out.println();
			/** 根据用户员角色名称获取用户角色编号 */
			System.out.println("根据用户员角色名称获取用户角色编号");
			System.out.println(Database.GetUserRoleId("高级会员"));
			System.out.println();
			/** 添加用户信息 */
			System.out.println("添加用户信息");
			user addobject = new user();
			addobject.setUserName("千晚");
			addobject.setRoleId("1");
			Database.insertUser(addobject);
			System.out.println();
			/** 获取所有的用户信息 */
			System.out.println("获取所有的用户信息");
			List<user> list = Database.ListUser();
			for (int i = 0; i < list.size(); i++) {
				user object = list.get(i);
				System.out.println(" ID:" + object.getUserId() + " Name:" + object.getUserName() + " Password:"
						+ object.getPassword() + " RoleId:" + object.getRoleId());
			}
			System.out.println();
			/** 修改用户信息 */
			System.out.println("修改用户信息");
			user updateUser = new user();
			updateUser.setUserId("172056237");
			updateUser.setUserName("千晚");
			updateUser.setPassword("520-96");
			updateUser.setRoleId("1");
			Database.updateUser(updateUser);
			System.out.println();
			/** 通过用户编号获取用户信息 */
			System.out.println("通过用户编号获取用户信息");
			user getuser = Database.getUserForId("172056236");
			System.out.println(" ID:" + getuser.getUserId() + " Name:" + getuser.getUserName() + " Password:"
					+ getuser.getPassword() + " RegulatorRoleId:" + getuser.getRoleId());
			System.out.println();
			/** 通过用户名称获取用户id */
			System.out.println("通过用户编号获取用户信息");
			System.out.println(Database.getUserIdForName("小杨"));
			System.out.println();
			/** 删除用户信息 */
			System.out.println("删除用户信息");
			Database.deleteUser("1720562005");
			System.out.println();
			/** 获取最大客户编号 */
			System.out.println("获取最大客户编号");
			System.out.println(Database.GetMaxId());
			System.out.println();
			Database.CloseDatabase();
		} catch (Exception e) {
			System.out.println("测试发生异常");
		}
	}

	/**
	 * 测试TestStore
	 */
	@Test
	public void TestStore() {
		try {
			DaoStore Database = new DaoStore();
			System.out.println();
			/** 获取所有的商店名称 */
			System.out.println("获取所有的商店名称");
			List<String> listname = Database.ListStoresName();
			for (int i = 0; i < listname.size(); i++) {
				System.out.println(listname.get(i));
			}
			System.out.println();
			/** 根据管理员编号获取所有商店的信息 */
			System.out.println("根据管理员编号获取所有商店的信息");
			List<store> allstore = Database.GetAllStoreByRegulatorId("10001");
			for (int i = 0; i < allstore.size(); i++) {
				store entity = allstore.get(i);
				System.out.println("StoreId:" + entity.getStoreId() + " StoreName:" + entity.getStoreName()
						+ " RegulatorId:" + entity.getRegulatorId());
			}
			System.out.println();
			/** 添加商店信息 */
//			System.out.println("添加商店信息");
//			store addobject = new store();
//			addobject.setStoreId("4");
//			addobject.setStoreName("八所后安粉店");
//			addobject.setRegulatorId("10001");
//			Database.insertStore(addobject);
			System.out.println();
			/** 获取所有的商店信息 */
			System.out.println("获取所有的商店信息");
			List<store> list = Database.ListStore();
			for (int i = 0; i < list.size(); i++) {
				store object = list.get(i);
				System.out.println("StoreID:" + object.getStoreId() + " StoreName:" + object.getStoreName()
						+ " RegulatorId:" + object.getRegulatorId());
			}
			System.out.println();
			/** 修改商店信息 */
			System.out.println("修改商店信息");
			store updateStore = new store();
			updateStore.setStoreId("4");
			updateStore.setStoreName("铁中大铁门店");
			updateStore.setRegulatorId("10001");
			Database.updateStore(updateStore);
			System.out.println();
			/** 通过商店编号获取商店信息 */
			System.out.println("通过商店编号获取商店信息");
			store getstore = Database.getStoreForId("4");
			System.out.println("StoreID:" + getstore.getStoreId() + " StoreName:" + getstore.getStoreName()
					+ " RegulatorId:" + getstore.getRegulatorId());
			System.out.println();
			/** 删除商店信息 */
			System.out.println("删除商店信息");
			Database.deleteStore("4");
			System.out.println();
			/** 获取最大商店编号 */
			System.out.println("获取最大商店编号");
			System.out.println(Database.GetMaxId());
			System.out.println();
			Database.CloseDatabase();
		} catch (Exception e) {
			System.out.println("测试发生异常");
		}
	}
}
