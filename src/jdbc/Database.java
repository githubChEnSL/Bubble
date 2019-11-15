package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;

public class Database {
	private final String DatabaseURL="jdbc:mysql://localhost:3306/sso?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT";
	private final String DatabaseNAME="root";
	private final String DatabasePWD="123";
	protected Connection connection=null;//���ݿ����ӵĶ���
	protected Resultset resuset=null; //���ݿ��ѯ���Ľ��
	public Database() {
	        try {
	    		//��������
				Class.forName("com.mysql.cj.jdbc.Driver");
				try {
					//�������ݿ�
					connection =  DriverManager.getConnection(DatabaseURL,DatabaseNAME,DatabasePWD);
					System.out.println("�������ݿ�-�ɹ�");
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("�������ݿ�-ʧ��");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("��������ʧ��");
			}
	}
	//�ر����ݿ�
	public void CloseDatabase(){
		//�ر����ݿ�
		try {
			if(connection!=null){
				connection.close();
				System.out.println("�ر����ݿ�-�ɹ�");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("�ر����ݿ�-ʧ��");
		}
	}
	//�ر�ʵ��
	public void CloseStatement(Statement sta) {
		try {
			if(sta!=null) {
				sta.close();
				//�ر�ʵ��ʧ��
			}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("�ر�ʵ��-ʧ��");
			}
		}
	//����ʵ��
	public Statement createSta(Statement sta) {
		try {
			sta=connection.createStatement();
			//����ʵ���ɹ�
		} catch (Exception e) {
			System.out.println("����ʵ��-ʧ��");
		}
		return sta;
	}
}
