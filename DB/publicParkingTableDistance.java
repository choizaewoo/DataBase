package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class publicParkingTableDistance {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver"); // class.forName()메소드를 호출하여 mysql에서 제공하는 driver 클래스를 
		//JVM method area에 로딩시킨다.
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.110:33060/kopoctc", "root", "kopo42");	//데이터베이스 연결
		//localhost:3306와 스키마 이름을 지정한다.
		 //connection 객체를 만들어사용한다. 방법은 DriverManager클래스의 static 메소드인 getConnetion()메소드를 호출해서, mysql에 연결하기 위한
		//커넥션정보(url,user,passwd)를 입력한다. getConnection()메소드 수행 결과로 connection객체를 반환하는데 , 이객체를 통해 
		//쿼리를 날리는 statement를 작성할 수 있다.
		Statement stmt = conn.createStatement();// SQL를 위한 객체 생성
		double lat = 37.3860521; double lng=127.1214038;//현재 위치 위도 경도 설정
		
		String QueryTxt; //문자열 선언
		QueryTxt = String.format("select * from publicParking where  " +
							 	 "SQRT( POWER( latitude-%f,2) + POWER (longitude-%f,2 )  ) = " +
							 	 "(select MIN( SQRT( POWER( latitude-%f,2) + POWER (longitude-%f,2)  )  )  from publicParking);",
							 	 lat, lng, lat, lng);  //select문으로 포맷팅한 문자열을 문자열에 저장.
		
		ResultSet rset = stmt.executeQuery(QueryTxt); //resultSet은 statement를 통해 값을 저장할 수 있다.
		//executeQuery(String sql) 메소드를 통해 저장할 수 있다.
		int iCnt=0;//초기화
		while(rset.next()) { // 개행문자를 무시하고 입력 받는다.
			//문자열로 받기 
			System.out.printf("*****************************************************************************************************\n");
			System.out.printf("주차장번호					: %s\n",rset.getString(1));
			System.out.printf("주차장명					: %s\n",rset.getString(2));
			System.out.printf("위도						: %s\n",rset.getString(3));
			System.out.printf("경도						: %s\n",rset.getString(4));
			System.out.printf("주차구획종류					: %s\n",rset.getString(5));
			System.out.printf("운영방법					: %s\n",rset.getString(6));
			System.out.printf("소재지도로명주소				: %s\n",rset.getString(7));
			System.out.printf("소재지지번주소					: %s\n",rset.getString(8));
			System.out.printf("장애인가능구역					: %s\n",rset.getString(9));
			System.out.printf("운영일						: %s\n",rset.getString(10));
			System.out.printf("*****************************************************************************************************\n");
			
		}
		  rset.close(); // select결과값 저장 종료하기
	      stmt.close(); //명령 실행 함수 종료, 프로그램의 오작동을 위해 꼭 종료할 것
	      conn.close(); //연결 종료, 프로그램의 오작동을 위해 꼭 종료할 것
		
	}

}
