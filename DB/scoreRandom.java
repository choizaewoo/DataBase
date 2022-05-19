package DB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class scoreRandom {

   public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, NumberFormatException {
	   Class.forName("com.mysql.cj.jdbc.Driver");// class.forName()메소드를 호출하여 mysql에서 제공하는 driver 클래스를 
		//JVM method area에 로딩시킨다.
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.110:33060/kopoctc", "root", "kopo42");//데이터베이스 연결
		//localhost:3306와 스키마 이름을 지정한다.
	    //connection 객체를 만들어사용한다. 방법은 DriverManager클래스의 static 메소드인 getConnetion()메소드를 호출해서, mysql에 연결하기 위한
		//커넥션정보(url,user,passwd)를 입력한다. getConnection()메소드 수행 결과로 connection객체를 반환하는데 , 이객체를 통해 
		//쿼리를 날리는 statement를 작성할 수 있다. 
		Statement stmt = conn.createStatement();// SQL를 위한 객체 생성   
    //sql의 명령을 실행하게 만들어주는 Statement
      
      for (int i = 0; i < 1000; i++) {
         String name = String.format("홍길%03d", i + 1); //이름 형식 스트링
         String studentid = String.valueOf(20220001 + i); //학번 형식 스트링
         String kor = String.valueOf((int) (Math.random() * 100)); //국어점수 형식 스트링
         String eng = String.valueOf((int) (Math.random() * 100)); //영어점수 형식 스트링
         String mat = String.valueOf((int) (Math.random() * 100)); //수학점수 형식 스트링
         //평균점수 형식 스트링
         String QueryTxt;  
         QueryTxt = String.format("insert into random_examtable ("
               + "studentid, name, kor, eng, mat) "
               + "values("
               + "'%s', '%s', '%s', '%s', '%s');", 
               studentid, name, kor, eng, mat);
         stmt.execute(QueryTxt);
         System.out.printf("%d번째 항목 insert OK\n\n", i + 1);
      }
      stmt.close(); //명령 실행 함수 종료, 프로그램의 오작동을 위해 꼭 종료할 것
      conn.close(); //연결 종료, 프로그램의 오작동을 위해 꼭 종료할 것
   }

}