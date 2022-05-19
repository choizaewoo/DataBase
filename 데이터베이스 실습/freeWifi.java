package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class freeWifi {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver"); // class.forName()메소드를 호출하여 mysql에서 제공하는 driver 클래스를 
		//JVM method area에 로딩시킨다.
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.110:33060/kopoctc", "root", "kopo42");	//데이터베이스 연결
		//localhost:3306와 스키마 이름을 지정한다.
		 //connection 객체를 만들어사용한다. 방법은 DriverManager클래스의 static 메소드인 getConnetion()메소드를 호출해서, mysql에 연결하기 위한
		//커넥션정보(url,user,passwd)를 입력한다. getConnection()메소드 수행 결과로 connection객체를 반환하는데 , 이객체를 통해 
		//쿼리를 날리는 statement를 작성할 수 있다.
		Statement stmt = conn.createStatement();// SQL를 위한 객체 생성
		
		stmt.execute("create table freewifi2(" + // 테이블 생성
				"inst_place		varchar(50), " + // 장소
				"inst_place_detail varchar(50)," + //상세주소
				"inst_city 		varchar(50), " + //시
				"inst_country   varchar(50), " + //도
				"inst_place_flag 	varchar(50), " +  //기관명
				"service_provider  varchar(50), " + //서비스제공자
				"wifi_ssid    varchar(100), " + //데이터가 너무 길어서 에러가 났음. 50byte를 100byte로 조정
				"inst_date    varchar(50), " + //날짜
				"place_addr_road      varchar(200), " + //도로명주소
				"place_addr_land      varchar(200), " + //소재지지번주ㅗㅅ
				"manage_office    varchar(50), " + //관리자
				"manage_office_phone varchar(50), " + //관리자번호
				"latitude   double, " + //위도
				"longitude  double, " + //경도
				"write_date      date, " + //제공날짜
				"PRIMARY KEY(inst_place,inst_place_detail,service_provider,latitude,longitude,write_date) " +//PK설정
				") DEFAULT CHARSET=utf8;"
				);
				
		stmt.close();
		conn.close();
	}

}
