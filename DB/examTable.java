package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class examTable {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver"); // class.forName()�޼ҵ带 ȣ���Ͽ� mysql���� �����ϴ� driver Ŭ������ 
		//JVM method area�� �ε���Ų��.
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.110:33060/kopoctc", "root", "kopo42");//�����ͺ��̽� ����
		//localhost:3306�� ��Ű�� �̸��� �����Ѵ�.
	    //connection ��ü�� ��������Ѵ�. ����� DriverManagerŬ������ static �޼ҵ��� getConnetion()�޼ҵ带 ȣ���ؼ�, mysql�� �����ϱ� ����
		//Ŀ�ؼ�����(url,user,passwd)�� �Է��Ѵ�. getConnection()�޼ҵ� ���� ����� connection��ü�� ��ȯ�ϴµ� , �̰�ü�� ���� 
		//������ ������ statement�� �ۼ��� �� �ִ�. 
		
		Statement stmt = conn.createStatement();// SQL�� ���� ��ü ����
		
		//���̺� ���� 
		//�̸� �й� ���� ���� ���� ���� ���̺�
		stmt.execute( "create table examtable3("+  
					  "name varchar(20),"+ 
				      "studentid int not null primary key,"+
					  "kor 		int,"+
					  "eng 		int,"+
					  "mat 		int)"+
					  "DEFAULT CHARSET=utf8;");
		
		stmt.close(); //close
		conn.close(); //close
				      
	}

}
