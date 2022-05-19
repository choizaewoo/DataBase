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

public class freeWifi2 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver"); // class.forName()�޼ҵ带 ȣ���Ͽ� mysql���� �����ϴ� driver Ŭ������ 
		//JVM method area�� �ε���Ų��.
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.110:33060/kopoctc", "root", "kopo42");	//�����ͺ��̽� ����
		//localhost:3306�� ��Ű�� �̸��� �����Ѵ�.
		 //connection ��ü�� ��������Ѵ�. ����� DriverManagerŬ������ static �޼ҵ��� getConnetion()�޼ҵ带 ȣ���ؼ�, mysql�� �����ϱ� ����
		//Ŀ�ؼ�����(url,user,passwd)�� �Է��Ѵ�. getConnection()�޼ҵ� ���� ����� connection��ü�� ��ȯ�ϴµ� , �̰�ü�� ���� 
		//������ ������ statement�� �ۼ��� �� �ִ�.
		Statement stmt = conn.createStatement();// SQL�� ���� ��ü ����
		
		File f = new File("C:\\Users\\kopo\\folder\\���������������ǥ�ص�����.txt"); //
		BufferedReader br = new BufferedReader(new FileReader(f));
		
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
		         String[] field1 = readtxt.split("\t"); //readtxt ���ڿ��� ������ �ɰ��� �����ϳ�.
		         //StringBuffer�� ���� ���ڿ��� �߰� �Ǵ� ����. 
		         StringBuffer sb = new StringBuffer();
		         //���� ���� �۾�
		         for(int i = 0; i < field1.length; i++) {
		            field1[i] = field1[i].replace(" ", ""); //������ ���ְ� ����.
		            
		            if(i == field1.length - 1) {  
		               sb.append(field1[i]);//append�Ѵ�.
		            } else {
		               sb.append(field1[i] + "\t"); //append�ϰ� ���Ѵ�.
		            }
		         }
		         hashSet.add(sb.toString());
		      }
		      
		      List hsList = new ArrayList(hashSet); //�޸𸮰� ����ϴ� �� ����ؼ� �ߺ��� ���ŵ� ���� �߰��Ѵ�.
		      
		      for (int i = 0; i < hsList.size(); i++) { //for�� 
		    	  //���� i�� 0���� hsList�� ũ�⸸ŭ �ݺ��Ѵ�. 
		         String[] field = hsList.get(i).toString().split("\t"); // �ε��� i�� ���� toString���� ���ڿ��� �ٲ��ְ� ������ �ɰ��� �����Ѵ�.
		         System.out.println(hsList.get(i)); //�ε���i�� ���.		       
		         String QueryTxt; //���ڿ� ���� ���� 
		         QueryTxt = String.format("insert into freewifi2 (" //�������� �����õ� ���ڿ��� �����Ѵ�. ���̺� column�� insert�Ѵ�.
		               + "inst_place, inst_place_detail, inst_city, inst_country, inst_place_flag,"
		               + "service_provider, wifi_ssid, inst_date, place_addr_road, place_addr_land,"
		               + "manage_office, manage_office_phone, latitude, longitude, write_date)"
		               + "values("
		               + "'%s','%s','%s','%s','%s',"
		               + "'%s','%s','%s','%s','%s',"
		               + "'%s','%s','%s','%s','%s');",
		               field[0], field[1], field[2],  field[3], field[4],
		               field[5], field[6], field[7], field[8], field[9],
		               field[10], field[11], field[12], field[13], field[14]);
		         stmt.execute(QueryTxt); //������ �����Ѵ�.
		       //Linecnt ���
		         System.out.printf("%d��° �׸� insert OK\n\n", LineCnt);
		         
		         LineCnt++;//Linecnt ����
		               
		      }
		      br.close();
		      stmt.close(); //��� ���� �Լ� ����, ���α׷��� ���۵��� ���� �� ������ ��
		      conn.close(); //���� ����, ���α׷��� ���۵��� ���� �� ������ ��
		   }

		}