package DB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class scoreRandom {

   public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, NumberFormatException {
	   Class.forName("com.mysql.cj.jdbc.Driver");// class.forName()�޼ҵ带 ȣ���Ͽ� mysql���� �����ϴ� driver Ŭ������ 
		//JVM method area�� �ε���Ų��.
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.110:33060/kopoctc", "root", "kopo42");//�����ͺ��̽� ����
		//localhost:3306�� ��Ű�� �̸��� �����Ѵ�.
	    //connection ��ü�� ��������Ѵ�. ����� DriverManagerŬ������ static �޼ҵ��� getConnetion()�޼ҵ带 ȣ���ؼ�, mysql�� �����ϱ� ����
		//Ŀ�ؼ�����(url,user,passwd)�� �Է��Ѵ�. getConnection()�޼ҵ� ���� ����� connection��ü�� ��ȯ�ϴµ� , �̰�ü�� ���� 
		//������ ������ statement�� �ۼ��� �� �ִ�. 
		Statement stmt = conn.createStatement();// SQL�� ���� ��ü ����   
    //sql�� ����� �����ϰ� ������ִ� Statement
      
      for (int i = 0; i < 1000; i++) {
         String name = String.format("ȫ��%03d", i + 1); //�̸� ���� ��Ʈ��
         String studentid = String.valueOf(20220001 + i); //�й� ���� ��Ʈ��
         String kor = String.valueOf((int) (Math.random() * 100)); //�������� ���� ��Ʈ��
         String eng = String.valueOf((int) (Math.random() * 100)); //�������� ���� ��Ʈ��
         String mat = String.valueOf((int) (Math.random() * 100)); //�������� ���� ��Ʈ��
         //������� ���� ��Ʈ��
         String QueryTxt;  
         QueryTxt = String.format("insert into random_examtable ("
               + "studentid, name, kor, eng, mat) "
               + "values("
               + "'%s', '%s', '%s', '%s', '%s');", 
               studentid, name, kor, eng, mat);
         stmt.execute(QueryTxt);
         System.out.printf("%d��° �׸� insert OK\n\n", i + 1);
      }
      stmt.close(); //��� ���� �Լ� ����, ���α׷��� ���۵��� ���� �� ������ ��
      conn.close(); //���� ����, ���α׷��� ���۵��� ���� �� ������ ��
   }

}