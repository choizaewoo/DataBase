package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertTableScore{

   public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");// class.forName()�޼ҵ带 ȣ���Ͽ� mysql���� �����ϴ� driver Ŭ������ 
		//JVM method area�� �ε���Ų��.
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.110:33060/kopoctc", "root", "kopo42");//�����ͺ��̽� ����
		//localhost:3306�� ��Ű�� �̸��� �����Ѵ�.
	    //connection ��ü�� ��������Ѵ�. ����� DriverManagerŬ������ static �޼ҵ��� getConnetion()�޼ҵ带 ȣ���ؼ�, mysql�� �����ϱ� ����
		//Ŀ�ؼ�����(url,user,passwd)�� �Է��Ѵ�. getConnection()�޼ҵ� ���� ����� connection��ü�� ��ȯ�ϴµ� , �̰�ü�� ���� 
		//������ ������ statement�� �ۼ��� �� �ִ�. 
		Statement stmt = conn.createStatement();// SQL�� ���� ��ü ����
      
      String QueryTxt;
      
      for(int i = 0; i < 1000; i=i+30) { //0���� 999�������� ������ �����ϱ� ���� �ݺ��� ����
         QueryTxt = String.format(" select *, kor+eng+mat, (kor+eng+mat)/3 from random_examtable limit %d, 30;", i); //���� �� �ۼ� ��ü�� ���հ� ��հ��� 30���� ������ �ΰ� ���
         
          ResultSet rset = stmt.executeQuery(QueryTxt); // ù��° ��� �� ��� 
          //30���� ������ ������
          System.out.printf("*******************************************************\n");   //���м� ���
           System.out.printf("%s %7s %6s %4s %4s %4s %8s\n", "�й�", "�̸�", "����", "����", "����", "�հ�", "���");
          // ����� ������ ����� ����ϱ� ���� while�� ����, ���� ������ while�� ����
           while (rset.next()) {//ù��° ��� ����ϱ� ���� ���� �ۼ�
              System.out.printf("%04d %6s %5d %6d %6d %6d  %9.2f \n", rset.getInt(1),rset.getString(2),rset.getInt(3),rset.getInt(4),rset.getInt(5),rset.getInt(6),rset.getDouble(7));   
          }
          System.out.printf("*******************************************************\n");   //���м� ���
          
          
          //���� ������ ���� ���հ� ��հ� ���
          System.out.printf("���� ������\n");   //���м� ���
          QueryTxt = String.format(" select sum(kor), sum(eng), sum(mat), sum(kor+eng+mat), sum((kor+eng+mat)/3), " //
                + "avg(kor), avg(eng), avg(mat), avg(kor+eng+mat), avg((kor+eng+mat)/3) from (select kor,eng,mat from random_examtable limit %d,30)as sum;", i);
          
          ResultSet rset2 = stmt.executeQuery(QueryTxt);
          //������������ ������� 2������ �޾Ƽ� ����Ѵ�.
          while (rset2.next()) {
              System.out.printf("                     %5d %6d %6d %6d  %6.1f \n", rset2.getInt(1),rset2.getInt(2),rset2.getInt(3),rset2.getInt(4),rset2.getDouble(5));
              System.out.printf("                     %5.2f %6.2f %6.2f %6.2f  %6.2f \n", rset2.getDouble(6),rset2.getDouble(7),rset2.getDouble(8),rset2.getDouble(9),rset2.getDouble(10));   
          }
          
          System.out.printf("���� ������\n");   //���м� ���
          //���� �������� ���� ���հ� ��հ� ���
          if (i == 990) {
             i=970;
          }
          QueryTxt = String.format(" select sum(kor), sum(eng), sum(mat), sum(kor+eng+mat), sum((kor+eng+mat)/3), "
                + "avg(kor), avg(eng), avg(mat), avg(kor+eng+mat), avg((kor+eng+mat)/3) from (select kor,eng,mat from random_examtable limit 0, %d)as avg;", (i+30));
          //���� �������� ������� 3������ �޾Ƽ� ����Ѵ�.
          ResultSet rset3 = stmt.executeQuery(QueryTxt);
          
          while (rset3.next()) {
              System.out.printf("                    %5d %6d %6d %6d  %6.1f \n", rset3.getInt(1),rset3.getInt(2),rset3.getInt(3),rset3.getInt(4),rset3.getDouble(5));
              System.out.printf("                     %5.2f %6.2f %6.2f %6.2f  %6.2f \n", rset3.getDouble(6),rset3.getDouble(7),rset3.getDouble(8),rset3.getDouble(9),rset3.getDouble(10));   
          }
              
          rset.close(); //ResultSet1 ���� 
          rset2.close();//ResultSet2 ���� 
          rset3.close();//ResultSet3 ���� 
      }
       

        stmt.close(); //Statement ����
        conn.close(); //Connection ����
      }
   }