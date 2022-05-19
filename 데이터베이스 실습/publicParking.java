package DB;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class publicParking {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver"); // class.forName()메소드를 호출하여 mysql에서 제공하는 driver 클래스를 
		//JVM method area에 로딩시킨다.
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.110:33060/kopoctc", "root", "kopo42");	//데이터베이스 연결
		//localhost:3306와 스키마 이름을 지정한다.
		 //connection 객체를 만들어사용한다. 방법은 DriverManager클래스의 static 메소드인 getConnetion()메소드를 호출해서, mysql에 연결하기 위한
		//커넥션정보(url,user,passwd)를 입력한다. getConnection()메소드 수행 결과로 connection객체를 반환하는데 , 이객체를 통해 
		//쿼리를 날리는 statement를 작성할 수 있다.
		Statement stmt = conn.createStatement();// SQL를 위한 객체 생성
	
		
		stmt.execute("create table publicParking("+ //테이블 
				"parkingOwnNumber		varchar(50), " + //주자창관리번호
				"parkingName varchar(50)," + //주차장명
				"streetAddress 	varchar(50), " + //소재지도로주소
				"jibeonAddress  varchar(50), " + //소재지지번주소
				"parkingAvailable    varchar(100), " + //주차구획수
				"part    varchar(50), " + //급지구분
				"operatingDay      varchar(200), " + // 운영요일
				"operatingDayTimeStart      varchar(200), " + // 평일운영시작시간
				"operatingDayTimeEnd    varchar(50), " + //평일운영종료시간
				"latitude   varchar(50), " + //위도
				"longitude  varchar(50), " + //경도
				"PRIMARY KEY(parkingOwnNumber)" + //PK parkingOwnNumber 
				") DEFAULT CHARSET=utf8;"
				);

		 stmt.close(); //명령 실행 함수 종료, 프로그램의 오작동을 위해 꼭 종료할 것
	      conn.close(); //연결 종료, 프로그램의 오작동을 위해 꼭 종료할 것
	}

}