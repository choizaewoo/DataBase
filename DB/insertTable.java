package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class insertTable {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.cj.jdbc.Driver");// class.forName()�޼ҵ带 ȣ���Ͽ� mysql���� �����ϴ� driver Ŭ������ 
		//JVM method area�� �ε���Ų��.
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.110:33060/kopoctc", "root", "kopo42");//�����ͺ��̽� ����
		//localhost:3306�� ��Ű�� �̸��� �����Ѵ�.
	    //connection ��ü�� ��������Ѵ�. ����� DriverManagerŬ������ static �޼ҵ��� getConnetion()�޼ҵ带 ȣ���ؼ�, mysql�� �����ϱ� ����
		//Ŀ�ؼ�����(url,user,passwd)�� �Է��Ѵ�. getConnection()�޼ҵ� ���� ����� connection��ü�� ��ȯ�ϴµ� , �̰�ü�� ���� 
		//������ ������ statement�� �ۼ��� �� �ִ�. 
		Statement stmt = conn.createStatement();// SQL�� ���� ��ü ����
		
		//execute ���̺� ����
		stmt.execute("insert into examtable2 (name, studentid, kor, eng, mat) values ('����', 209901, 95, 100, 75);");
		stmt.execute("insert into examtable2 (name, studentid, kor, eng, mat) values ('����', 209902, 95, 80, 45);");
		stmt.execute("insert into examtable2 (name, studentid, kor, eng, mat) values ('���', 209903, 15, 50, 35);");
		stmt.execute("insert into examtable2 (name, studentid, kor, eng, mat) values ('�糪', 209904, 25, 30, 45);");
		stmt.execute("insert into examtable2 (name, studentid, kor, eng, mat) values ('��ȿ', 209905, 35, 10, 55);");
		stmt.execute("insert into examtable2 (name, studentid, kor, eng, mat) values ('�̳�', 209906, 45, 70, 75);");
		stmt.execute("insert into examtable2 (name, studentid, kor, eng, mat) values ('����', 209907, 65, 80, 95);");
		stmt.execute("insert into examtable2 (name, studentid, kor, eng, mat) values ('ä��', 209908, 95, 50, 85);");
		stmt.execute("insert into examtable2 (name, studentid, kor, eng, mat) values ('����', 209909, 95, 80, 95);");

		stmt.close();
		conn.close();
	}

}