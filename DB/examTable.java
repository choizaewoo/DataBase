package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class examTable {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver"); // class.forName()메소드를 호출하여 mysql에서 제공하는 driver 클래스를 
		//JVM method area에 로딩시킨다.
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.110:33060/kopoctc", "root", "kopo42");//데이터베이스 연결
		//localhost:3306와 스키마 이름을 지정한다.
	    //connection 객체를 만들어사용한다. 방법은 DriverManager클래스의 static 메소드인 getConnetion()메소드를 호출해서, mysql에 연결하기 위한
		//커넥션정보(url,user,passwd)를 입력한다. getConnection()메소드 수행 결과로 connection객체를 반환하는데 , 이객체를 통해 
		//쿼리를 날리는 statement를 작성할 수 있다. 
		
		Statement stmt = conn.createStatement();// SQL를 위한 객체 생성
		
		//테이블 생성 
		//이름 학번 국어 영어 수학 점수 테이블
		stmt.execute( "create table examtable3("+  
					  "name varchar(20),"+ 
				      "studentid int not null primary key,"+
					  "kor 		int,"+
					  "eng 		int,"+
					  "mat 		int)"+
					  "DEFAULT CHARSET=utf8;");
		
		stmt.close(); //close
		conn.close(); //close
				      
	}

}
