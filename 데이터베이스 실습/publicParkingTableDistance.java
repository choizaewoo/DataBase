package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class publicParkingTableDistance {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver"); // class.forName()�޼ҵ带 ȣ���Ͽ� mysql���� �����ϴ� driver Ŭ������ 
		//JVM method area�� �ε���Ų��.
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.110:33060/kopoctc", "root", "kopo42");	//�����ͺ��̽� ����
		//localhost:3306�� ��Ű�� �̸��� �����Ѵ�.
		 //connection ��ü�� ��������Ѵ�. ����� DriverManagerŬ������ static �޼ҵ��� getConnetion()�޼ҵ带 ȣ���ؼ�, mysql�� �����ϱ� ����
		//Ŀ�ؼ�����(url,user,passwd)�� �Է��Ѵ�. getConnection()�޼ҵ� ���� ����� connection��ü�� ��ȯ�ϴµ� , �̰�ü�� ���� 
		//������ ������ statement�� �ۼ��� �� �ִ�.
		Statement stmt = conn.createStatement();// SQL�� ���� ��ü ����
		double lat = 37.3860521; double lng=127.1214038;//���� ��ġ ���� �浵 ����
		
		String QueryTxt; //���ڿ� ����
		QueryTxt = String.format("select * from publicParking where  " +
							 	 "SQRT( POWER( latitude-%f,2) + POWER (longitude-%f,2 )  ) = " +
							 	 "(select MIN( SQRT( POWER( latitude-%f,2) + POWER (longitude-%f,2)  )  )  from publicParking);",
							 	 lat, lng, lat, lng);  //select������ �������� ���ڿ��� ���ڿ��� ����.
		
		ResultSet rset = stmt.executeQuery(QueryTxt); //resultSet�� statement�� ���� ���� ������ �� �ִ�.
		//executeQuery(String sql) �޼ҵ带 ���� ������ �� �ִ�.
		int iCnt=0;//�ʱ�ȭ
		while(rset.next()) { // ���๮�ڸ� �����ϰ� �Է� �޴´�.
			//���ڿ��� �ޱ� 
			System.out.printf("*****************************************************************************************************\n");
			System.out.printf("�������ȣ					: %s\n",rset.getString(1));
			System.out.printf("�������					: %s\n",rset.getString(2));
			System.out.printf("����						: %s\n",rset.getString(3));
			System.out.printf("�浵						: %s\n",rset.getString(4));
			System.out.printf("������ȹ����					: %s\n",rset.getString(5));
			System.out.printf("����					: %s\n",rset.getString(6));
			System.out.printf("���������θ��ּ�				: %s\n",rset.getString(7));
			System.out.printf("�����������ּ�					: %s\n",rset.getString(8));
			System.out.printf("����ΰ��ɱ���					: %s\n",rset.getString(9));
			System.out.printf("���						: %s\n",rset.getString(10));
			System.out.printf("*****************************************************************************************************\n");
			
		}
		  rset.close(); // select����� ���� �����ϱ�
	      stmt.close(); //��� ���� �Լ� ����, ���α׷��� ���۵��� ���� �� ������ ��
	      conn.close(); //���� ����, ���α׷��� ���۵��� ���� �� ������ ��
		
	}

}
