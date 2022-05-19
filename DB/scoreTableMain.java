package DB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class scoreTableMain {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver"); // class.forName()�޼ҵ带 ȣ���Ͽ� mysql���� �����ϴ� driver Ŭ������ 
		//JVM method area�� �ε���Ų��.
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.110:33060/kopoctc", "root", "kopo42");	//�����ͺ��̽� ����
		//localhost:3306�� ��Ű�� �̸��� �����Ѵ�.
		 //connection ��ü�� ��������Ѵ�. ����� DriverManagerŬ������ static �޼ҵ��� getConnetion()�޼ҵ带 ȣ���ؼ�, mysql�� �����ϱ� ����
		//Ŀ�ؼ�����(url,user,passwd)�� �Է��Ѵ�. getConnection()�޼ҵ� ���� ����� connection��ü�� ��ȯ�ϴµ� , �̰�ü�� ���� 
		//������ ������ statement�� �ۼ��� �� �ִ�.
		Statement stmt = conn.createStatement();// SQL�� ���� ��ü ����
		
		int iPerson = 1000;
		
		String[] field = {"studentId", "name", "kor", "eng", "mat"};
		
		for (int i = 1; i <= iPerson; i++) {
		 
			String StudentId = String.valueOf(20220000 + i);
			String name = String.format("ȫ��%02d", i);
			String kor = String.valueOf(Math.random() * 100);
			String eng = String.valueOf(Math.random() * 100);
			String mat = String.valueOf(Math.random() * 100);
			
			
			String QueryTxt;
			QueryTxt = String.format("insert into studentScore ("
	               + "studentId, name, kor, mat, eng, kor+mat+eng, (kor+mat+eng)/3)"
	               + "values("
	               + "'%s','%s','%s','%s','%s','%s','%s');",
	               StudentId, name, String.valueOf(kor), String.valueOf(eng), String.valueOf(mat), String.valueOf(kor+eng+mat), String.valueOf((kor+eng+mat)/3));
	        stmt.execute(QueryTxt);
	        System.out.printf("%d��° �׸� insert OK\n\n", i);
		}
	
	}

}