package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class rsetTable {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.cj.jdbc.Driver");// class.forName()메소드를 호출하여 mysql에서 제공하는 driver 클래스를 
		//JVM method area에 로딩시킨다.
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.110:33060/kopoctc", "root", "kopo42");//데이터베이스 연결
		//localhost:3306와 스키마 이름을 지정한다.
	    //connection 객체를 만들어사용한다. 방법은 DriverManager클래스의 static 메소드인 getConnetion()메소드를 호출해서, mysql에 연결하기 위한
		//커넥션정보(url,user,passwd)를 입력한다. getConnection()메소드 수행 결과로 connection객체를 반환하는데 , 이객체를 통해 
		//쿼리를 날리는 statement를 작성할 수 있다.
		Statement stmt = conn.createStatement();// SQL를 위한 객체 생성
		ResultSet rset = stmt.executeQuery("select * from examtable;"); //select 문장 
		//Select문장을 실행하기 위해 statement메소드 executeQuery를 사용한다.
		//메소드 executeQuery()는 결과로 테이블형태의 결과를 반환하는데 , 이 반환형이 인터페이스 ResultSet이다. 
		
		System.out.printf(" 이름 학번 국어 영어 수학\n");
		while(rset.next()) {//ResultSet 인터페이스에는 결과의 현태 행을 가리키는 커서라는 개념이 있다. 그리고 이 커서를 다음 행으로 
			//이동시키는 메소드가 next()이다.
			System.out.printf("%4s %6d %3d %3d %3d\n", rset.getString(1), rset.getInt(2), rset.getInt(3), rset.getInt(4), rset.getInt(5));
		}//getString getInt -> ResultSet의 메소드.
		//ResultSet의 커서가 있는 행에서 column자료를 참조하기 위해 ResultSet이 제공하는 메소드 getString()을 이용한다.
		//getString() 인자는 컬럼을 문자열로 직접 쓰거나 또는 칼럼 번호를 이용할 수 있다. 외에도. getInt() getDouble() getDate()
		
		rset.close(); //close
		stmt.close(); //close
		conn.close(); //close
	}
}