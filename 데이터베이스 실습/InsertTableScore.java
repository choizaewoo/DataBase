package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertTableScore{

   public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");// class.forName()메소드를 호출하여 mysql에서 제공하는 driver 클래스를 
		//JVM method area에 로딩시킨다.
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.110:33060/kopoctc", "root", "kopo42");//데이터베이스 연결
		//localhost:3306와 스키마 이름을 지정한다.
	    //connection 객체를 만들어사용한다. 방법은 DriverManager클래스의 static 메소드인 getConnetion()메소드를 호출해서, mysql에 연결하기 위한
		//커넥션정보(url,user,passwd)를 입력한다. getConnection()메소드 수행 결과로 connection객체를 반환하는데 , 이객체를 통해 
		//쿼리를 날리는 statement를 작성할 수 있다. 
		Statement stmt = conn.createStatement();// SQL를 위한 객체 생성
      
      String QueryTxt;
      
      for(int i = 0; i < 1000; i=i+30) { //0부터 999번까지의 성적을 추출하기 위해 반복문 실행
         QueryTxt = String.format(" select *, kor+eng+mat, (kor+eng+mat)/3 from random_examtable limit %d, 30;", i); //쿼리 문 작성 전체와 총합과 평균값을 30까지 제한을 두고 출력
         
          ResultSet rset = stmt.executeQuery(QueryTxt); // 첫번째 결과 값 출력 
          //30개씩 페이지 나누기
          System.out.printf("*******************************************************\n");   //구분선 출력
           System.out.printf("%s %7s %6s %4s %4s %4s %8s\n", "학번", "이름", "국어", "영어", "수학", "합계", "평균");
          // 명령을 실행한 결과를 출력하기 위한 while문 선언, 빈값이 들어오면 while문 종료
           while (rset.next()) {//첫번째 결과 출력하기 위한 구문 작성
              System.out.printf("%04d %6s %5d %6d %6d %6d  %9.2f \n", rset.getInt(1),rset.getString(2),rset.getInt(3),rset.getInt(4),rset.getInt(5),rset.getInt(6),rset.getDouble(7));   
          }
          System.out.printf("*******************************************************\n");   //구분선 출력
          
          
          //현재 페이지 과목별 총합과 평균값 출력
          System.out.printf("현재 페이지\n");   //구분선 출력
          QueryTxt = String.format(" select sum(kor), sum(eng), sum(mat), sum(kor+eng+mat), sum((kor+eng+mat)/3), " //
                + "avg(kor), avg(eng), avg(mat), avg(kor+eng+mat), avg((kor+eng+mat)/3) from (select kor,eng,mat from random_examtable limit %d,30)as sum;", i);
          
          ResultSet rset2 = stmt.executeQuery(QueryTxt);
          //현재페이지의 결과값을 2번으로 받아서 출력한다.
          while (rset2.next()) {
              System.out.printf("                     %5d %6d %6d %6d  %6.1f \n", rset2.getInt(1),rset2.getInt(2),rset2.getInt(3),rset2.getInt(4),rset2.getDouble(5));
              System.out.printf("                     %5.2f %6.2f %6.2f %6.2f  %6.2f \n", rset2.getDouble(6),rset2.getDouble(7),rset2.getDouble(8),rset2.getDouble(9),rset2.getDouble(10));   
          }
          
          System.out.printf("누적 페이지\n");   //구분선 출력
          //누적 페이지의 과목별 총합과 평균값 출력
          if (i == 990) {
             i=970;
          }
          QueryTxt = String.format(" select sum(kor), sum(eng), sum(mat), sum(kor+eng+mat), sum((kor+eng+mat)/3), "
                + "avg(kor), avg(eng), avg(mat), avg(kor+eng+mat), avg((kor+eng+mat)/3) from (select kor,eng,mat from random_examtable limit 0, %d)as avg;", (i+30));
          //누적 페이지의 결과값을 3번으로 받아서 출력한다.
          ResultSet rset3 = stmt.executeQuery(QueryTxt);
          
          while (rset3.next()) {
              System.out.printf("                    %5d %6d %6d %6d  %6.1f \n", rset3.getInt(1),rset3.getInt(2),rset3.getInt(3),rset3.getInt(4),rset3.getDouble(5));
              System.out.printf("                     %5.2f %6.2f %6.2f %6.2f  %6.2f \n", rset3.getDouble(6),rset3.getDouble(7),rset3.getDouble(8),rset3.getDouble(9),rset3.getDouble(10));   
          }
              
          rset.close(); //ResultSet1 종료 
          rset2.close();//ResultSet2 종료 
          rset3.close();//ResultSet3 종료 
      }
       

        stmt.close(); //Statement 종료
        conn.close(); //Connection 종료
      }
   }