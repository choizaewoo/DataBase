package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class freeWifiDistance {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver"); // class.forName()�޼ҵ带 ȣ���Ͽ� mysql���� �����ϴ� driver Ŭ������ 
		//JVM method area�� �ε���Ų��.
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.110:33060/kopoctc", "root", "kopo42");	//�����ͺ��̽� ����
		//localhost:3306�� ��Ű�� �̸��� �����Ѵ�.
		 //connection ��ü�� ��������Ѵ�. ����� DriverManagerŬ������ static �޼ҵ��� getConnetion()�޼ҵ带 ȣ���ؼ�, mysql�� �����ϱ� ����
		//Ŀ�ؼ�����(url,user,passwd)�� �Է��Ѵ�. getConnection()�޼ҵ� ���� ����� connection��ü�� ��ȯ�ϴµ� , �̰�ü�� ���� 
		//������ ������ statement�� �ۼ��� �� �ִ�.
		Statement stmt = conn.createStatement();// SQL�� ���� ��ü ����
		
		double lat = 37.3860521; double lng=127.1214038;//���� �����浵
		
		String QueryTxt; //������ 
//		QueryTxt = String.format("select * from freewifi2 where  " + 
//							 	 "SQRT( POWER( latitude-%f,2) + POWER (longitude-%f,2 )  ) = " +
//							 	 "(select MIN( SQRT( POWER( latitude-%f,2) + POWER (longitude-%f,2)  )  )  from freewifi2);",
//							 	 lat, lng, lat, lng); //�ִܰŸ� 
		QueryTxt = "select * from freewifi2"; //���������� freewifi ����
//		QueryTxt = "select * from freewifi2 where service_provider = 'SKT'"; //service_provider
//		QueryTxt = "select * from freewifi2 where inst_country = '������'";
		
		ResultSet rset = stmt.executeQuery(QueryTxt); //resultSet�� statement�� ���� ���� ������ �� �ִ�.
		int iCnt=0;
		while(rset.next()) {
			System.out.printf("*(%d)**************************************\n",iCnt++);
			System.out.printf("��ġ��Ҹ�			: %s\n",rset.getString(1));
			System.out.printf("��ġ��һ�			: %s\n",rset.getString(2));
			System.out.printf("��ġ�õ���			: %s\n",rset.getString(3));
			System.out.printf("��ġ�ñ�����			: %s\n",rset.getString(4));
			System.out.printf("��ġ�ü�����			: %s\n",rset.getString(5));
			System.out.printf("���������ڸ�			: %s\n",rset.getString(6));
			System.out.printf("��������SSID			: %s\n",rset.getString(7));
			System.out.printf("��ġ���			: %s\n",rset.getString(8));
			System.out.printf("���������θ��ּ�		: %s\n",rset.getString(9));
			System.out.printf("�����������ּ�			: %s\n",rset.getString(10));
			System.out.printf("���������			: %s\n",rset.getString(11));
			System.out.printf("���������ȭ��ȣ		: %s\n",rset.getString(12));
			System.out.printf("����				: %f\n",rset.getFloat(13));
			System.out.printf("�浵				: %f\n",rset.getFloat(14));
			System.out.printf("�����ͱ�������			: %s\n",rset.getString(15));
			System.out.printf("********************************************\n");
			
		}
		rset.close();
		stmt.close(); //��� ���� �Լ� ����, ���α׷��� ���۵��� ���� �� ������ ��
	    conn.close(); //���� ����, ���α׷��� ���۵��� ���� �� ������ ��
		
	}

}
