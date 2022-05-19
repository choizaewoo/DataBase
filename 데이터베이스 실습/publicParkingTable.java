package DB;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class publicParkingTable {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver"); // class.forName()�޼ҵ带 ȣ���Ͽ� mysql���� �����ϴ� driver Ŭ������ 
		//JVM method area�� �ε���Ų��.
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.110:33060/kopoctc", "root", "kopo42");	//�����ͺ��̽� ����
		//localhost:3306�� ��Ű�� �̸��� �����Ѵ�.
		 //connection ��ü�� ��������Ѵ�. ����� DriverManagerŬ������ static �޼ҵ��� getConnetion()�޼ҵ带 ȣ���ؼ�, mysql�� �����ϱ� ����
		//Ŀ�ؼ�����(url,user,passwd)�� �Է��Ѵ�. getConnection()�޼ҵ� ���� ����� connection��ü�� ��ȯ�ϴµ� , �̰�ü�� ���� 
		//������ ������ statement�� �ۼ��� �� �ִ�.
		Statement stmt = conn.createStatement();// SQL�� ���� ��ü ����
		
		File f = new File("C:\\Users\\kopo\\folder\\������������������.txt"); //���Ϻҷ�����
		BufferedReader br = new BufferedReader(new FileReader(f));//bufferReader�� ���� �б�
		
		String readtxt;
		
		  if((readtxt = br.readLine()) == null) { //bufferReader�� ���θ��� �о�� ������ readtxt�� ������ �ȴٸ�
		         System.out.println("�� �����Դϴ�");//�������Դϴ�.
		         return; //�� ��ȯ
		      } 
		      
		      String[] field_name = readtxt.split("\t"); //readtxt�� ������ �ɰ��� �迭�� �����Ѵ�.
		      
		      HashSet<String> hashSet = new HashSet<String>(); //�ߺ��� ���� �����ϱ� ���� set�� �������̽� hashset Ŭ������ ����Ѵ�.
		      
		      int LineCnt = 0; //linecnt�� �ʱ�ȭ 
		      while((readtxt = br.readLine()) != null) { 
		    	  //���۸����� �о�� ������ ������ ������� �ʴ´ٸ� 
		         hashSet.add(readtxt); //readtxt ���� �߰��Ѵ�. 
		      }
		      
		      List hsList = new ArrayList(hashSet); //�޸𸮰� ����ϴ� �� ����ؼ� �ߺ��� ���ŵ� ���� �߰��Ѵ�.
		      
		      for (int i = 0; i < hsList.size(); i++) { //for�� 
		    	  //���� i�� 0���� hsList�� ũ�⸸ŭ �ݺ��Ѵ�. 
		         String[] field = hsList.get(i).toString().split("\t"); // �ε��� i�� ���� toString���� ���ڿ��� �ٲ��ְ� ������ �ɰ��� �����Ѵ�.
		         System.out.println(hsList.get(i)); //�ε���i�� ���.		       
		         String QueryTxt; //���ڿ� ���� ���� 
		         QueryTxt = String.format("insert into publicParking ("  //�������� �����õ� ���ڿ��� �����Ѵ�. ���̺� column�� insert�Ѵ�.
		               + "parkingOwnNumber, parkingName, streetAddress," 
		               + "jibeonAddress,parkingAvailable, part, operatingDay, operatingDayTimeStart,"
		               + "operatingDayTimeEnd, latitude, longitude)"
		               + "values("
		               + "'%s','%s','%s','%s','%s',"
		               + "'%s','%s','%s','%s','%s',"
		               + "'%s');", //����ǥ�� ���� �κ� ����
		               field[0], field[1], field[2], field[3], field[4],
		               field[5], field[6], field[7], field[8], field[9],
		               field[10]);
		         stmt.execute(QueryTxt); //������ �����Ѵ�.
		         System.out.printf("%d��° �׸� insert OK\n\n", LineCnt); //Linecnt ���
		         
		         LineCnt++; //Linecnt ����
		               
		      }
		      br.close(); //bufferReader ����.
		      stmt.close(); //��� ���� �Լ� ����, ���α׷��� ���۵��� ���� �� ������ ��
		      conn.close(); //���� ����, ���α׷��� ���۵��� ���� �� ������ ��
		   }

}