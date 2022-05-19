package DB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class scoreTableMain {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver"); // class.forName()메소드를 호출하여 mysql에서 제공하는 driver 클래스를 
		//JVM method area에 로딩시킨다.
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.110:33060/kopoctc", "root", "kopo42");	//데이터베이스 연결
		//localhost:3306와 스키마 이름을 지정한다.
		 //connection 객체를 만들어사용한다. 방법은 DriverManager클래스의 static 메소드인 getConnetion()메소드를 호출해서, mysql에 연결하기 위한
		//커넥션정보(url,user,passwd)를 입력한다. getConnection()메소드 수행 결과로 connection객체를 반환하는데 , 이객체를 통해 
		//쿼리를 날리는 statement를 작성할 수 있다.
		Statement stmt = conn.createStatement();// SQL를 위한 객체 생성
		
		int iPerson = 1000;
		
		String[] field = {"studentId", "name", "kor", "eng", "mat"};
		
		for (int i = 1; i <= iPerson; i++) {
		 
			String StudentId = String.valueOf(20220000 + i);
			String name = String.format("홍길%02d", i);
			String kor = String.valueOf(Math.random() * 100);
			String eng = String.valueOf(Math.random() * 100);
			String mat = String.valueOf(Math.random() * 100);
			
			
			String QueryTxt;
			QueryTxt = String.format("insert into studentScore ("
	               + "studentId, name, kor, mat, eng, kor+mat+eng, (kor+mat+eng)/3)"
	               + "values("
	               + "'%s','%s','%s','%s','%s','%s','%s');",
	               StudentId, name, String.valueOf(kor), String.valueOf(eng), String.valueOf(mat), String.valueOf(kor+eng+mat), String.valueOf((kor+eng+mat)/3));
	        stmt.execute(QueryTxt);
	        System.out.printf("%d번째 항목 insert OK\n\n", i);
		}
	
	}

}