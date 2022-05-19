package DB;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class publicParking {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver"); // class.forName()�޼ҵ带 ȣ���Ͽ� mysql���� �����ϴ� driver Ŭ������ 
		//JVM method area�� �ε���Ų��.
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.110:33060/kopoctc", "root", "kopo42");	//�����ͺ��̽� ����
		//localhost:3306�� ��Ű�� �̸��� �����Ѵ�.
		 //connection ��ü�� ��������Ѵ�. ����� DriverManagerŬ������ static �޼ҵ��� getConnetion()�޼ҵ带 ȣ���ؼ�, mysql�� �����ϱ� ����
		//Ŀ�ؼ�����(url,user,passwd)�� �Է��Ѵ�. getConnection()�޼ҵ� ���� ����� connection��ü�� ��ȯ�ϴµ� , �̰�ü�� ���� 
		//������ ������ statement�� �ۼ��� �� �ִ�.
		Statement stmt = conn.createStatement();// SQL�� ���� ��ü ����
	
		
		stmt.execute("create table publicParking("+ //���̺� 
				"parkingOwnNumber		varchar(50), " + //����â������ȣ
				"parkingName varchar(50)," + //�������
				"streetAddress 	varchar(50), " + //�����������ּ�
				"jibeonAddress  varchar(50), " + //�����������ּ�
				"parkingAvailable    varchar(100), " + //������ȹ��
				"part    varchar(50), " + //��������
				"operatingDay      varchar(200), " + // �����
				"operatingDayTimeStart      varchar(200), " + // ���Ͽ���۽ð�
				"operatingDayTimeEnd    varchar(50), " + //���Ͽ����ð�
				"latitude   varchar(50), " + //����
				"longitude  varchar(50), " + //�浵
				"PRIMARY KEY(parkingOwnNumber)" + //PK parkingOwnNumber 
				") DEFAULT CHARSET=utf8;"
				);

		 stmt.close(); //��� ���� �Լ� ����, ���α׷��� ���۵��� ���� �� ������ ��
	      conn.close(); //���� ����, ���α׷��� ���۵��� ���� �� ������ ��
	}

}