package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class freeWifi {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver"); // class.forName()�޼ҵ带 ȣ���Ͽ� mysql���� �����ϴ� driver Ŭ������ 
		//JVM method area�� �ε���Ų��.
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.110:33060/kopoctc", "root", "kopo42");	//�����ͺ��̽� ����
		//localhost:3306�� ��Ű�� �̸��� �����Ѵ�.
		 //connection ��ü�� ��������Ѵ�. ����� DriverManagerŬ������ static �޼ҵ��� getConnetion()�޼ҵ带 ȣ���ؼ�, mysql�� �����ϱ� ����
		//Ŀ�ؼ�����(url,user,passwd)�� �Է��Ѵ�. getConnection()�޼ҵ� ���� ����� connection��ü�� ��ȯ�ϴµ� , �̰�ü�� ���� 
		//������ ������ statement�� �ۼ��� �� �ִ�.
		Statement stmt = conn.createStatement();// SQL�� ���� ��ü ����
		
		stmt.execute("create table freewifi2(" + // ���̺� ����
				"inst_place		varchar(50), " + // ���
				"inst_place_detail varchar(50)," + //���ּ�
				"inst_city 		varchar(50), " + //��
				"inst_country   varchar(50), " + //��
				"inst_place_flag 	varchar(50), " +  //�����
				"service_provider  varchar(50), " + //����������
				"wifi_ssid    varchar(100), " + //�����Ͱ� �ʹ� �� ������ ����. 50byte�� 100byte�� ����
				"inst_date    varchar(50), " + //��¥
				"place_addr_road      varchar(200), " + //���θ��ּ�
				"place_addr_land      varchar(200), " + //�����������֤Ǥ�
				"manage_office    varchar(50), " + //������
				"manage_office_phone varchar(50), " + //�����ڹ�ȣ
				"latitude   double, " + //����
				"longitude  double, " + //�浵
				"write_date      date, " + //������¥
				"PRIMARY KEY(inst_place,inst_place_detail,service_provider,latitude,longitude,write_date) " +//PK����
				") DEFAULT CHARSET=utf8;"
				);
				
		stmt.close();
		conn.close();
	}

}
