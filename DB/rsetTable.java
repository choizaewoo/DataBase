package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class rsetTable {

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
		ResultSet rset = stmt.executeQuery("select * from examtable;"); //select ���� 
		//Select������ �����ϱ� ���� statement�޼ҵ� executeQuery�� ����Ѵ�.
		//�޼ҵ� executeQuery()�� ����� ���̺������� ����� ��ȯ�ϴµ� , �� ��ȯ���� �������̽� ResultSet�̴�. 
		
		System.out.printf(" �̸� �й� ���� ���� ����\n");
		while(rset.next()) {//ResultSet �������̽����� ����� ���� ���� ����Ű�� Ŀ����� ������ �ִ�. �׸��� �� Ŀ���� ���� ������ 
			//�̵���Ű�� �޼ҵ尡 next()�̴�.
			System.out.printf("%4s %6d %3d %3d %3d\n", rset.getString(1), rset.getInt(2), rset.getInt(3), rset.getInt(4), rset.getInt(5));
		}//getString getInt -> ResultSet�� �޼ҵ�.
		//ResultSet�� Ŀ���� �ִ� �࿡�� column�ڷḦ �����ϱ� ���� ResultSet�� �����ϴ� �޼ҵ� getString()�� �̿��Ѵ�.
		//getString() ���ڴ� �÷��� ���ڿ��� ���� ���ų� �Ǵ� Į�� ��ȣ�� �̿��� �� �ִ�. �ܿ���. getInt() getDouble() getDate()
		
		rset.close(); //close
		stmt.close(); //close
		conn.close(); //close
	}
}