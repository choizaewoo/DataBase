package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class freeWifiDistance {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver"); // class.forName()메소드를 호출하여 mysql에서 제공하는 driver 클래스를 
		//JVM method area에 로딩시킨다.
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.110:33060/kopoctc", "root", "kopo42");	//데이터베이스 연결
		//localhost:3306와 스키마 이름을 지정한다.
		 //connection 객체를 만들어사용한다. 방법은 DriverManager클래스의 static 메소드인 getConnetion()메소드를 호출해서, mysql에 연결하기 위한
		//커넥션정보(url,user,passwd)를 입력한다. getConnection()메소드 수행 결과로 connection객체를 반환하는데 , 이객체를 통해 
		//쿼리를 날리는 statement를 작성할 수 있다.
		Statement stmt = conn.createStatement();// SQL를 위한 객체 생성
		
		double lat = 37.3860521; double lng=127.1214038;//현재 위도경도
		
		String QueryTxt; //쿼리문 
//		QueryTxt = String.format("select * from freewifi2 where  " + 
//							 	 "SQRT( POWER( latitude-%f,2) + POWER (longitude-%f,2 )  ) = " +
//							 	 "(select MIN( SQRT( POWER( latitude-%f,2) + POWER (longitude-%f,2)  )  )  from freewifi2);",
//							 	 lat, lng, lat, lng); //최단거리 
		QueryTxt = "select * from freewifi2"; //쿼리문으로 freewifi 실행
//		QueryTxt = "select * from freewifi2 where service_provider = 'SKT'"; //service_provider
//		QueryTxt = "select * from freewifi2 where inst_country = '수원시'";
		
		ResultSet rset = stmt.executeQuery(QueryTxt); //resultSet은 statement를 통해 값을 저장할 수 있다.
		int iCnt=0;
		while(rset.next()) {
			System.out.printf("*(%d)**************************************\n",iCnt++);
			System.out.printf("설치장소명			: %s\n",rset.getString(1));
			System.out.printf("설치장소상세			: %s\n",rset.getString(2));
			System.out.printf("설치시도명			: %s\n",rset.getString(3));
			System.out.printf("설치시군구명			: %s\n",rset.getString(4));
			System.out.printf("설치시설구분			: %s\n",rset.getString(5));
			System.out.printf("서비스제공자명			: %s\n",rset.getString(6));
			System.out.printf("와이파이SSID			: %s\n",rset.getString(7));
			System.out.printf("설치년월			: %s\n",rset.getString(8));
			System.out.printf("소재지도로명주소		: %s\n",rset.getString(9));
			System.out.printf("소재지지번주소			: %s\n",rset.getString(10));
			System.out.printf("관리기관명			: %s\n",rset.getString(11));
			System.out.printf("관리기관전화번호		: %s\n",rset.getString(12));
			System.out.printf("위도				: %f\n",rset.getFloat(13));
			System.out.printf("경도				: %f\n",rset.getFloat(14));
			System.out.printf("데이터기준일자			: %s\n",rset.getString(15));
			System.out.printf("********************************************\n");
			
		}
		rset.close();
		stmt.close(); //명령 실행 함수 종료, 프로그램의 오작동을 위해 꼭 종료할 것
	    conn.close(); //연결 종료, 프로그램의 오작동을 위해 꼭 종료할 것
		
	}

}
