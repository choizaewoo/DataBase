package DB;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StockDailyPriceTable {

   public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
      //java build path에 add external jar로 등록한 mysql 연결 jar를 실행하는 명령
      Class.forName("com.mysql.cj.jdbc.Driver");
      //ip주소는 mysql이 존재하는 서버의 ip와 port 번호, /kopoctc는 접근하려는 데이터베이스, root = 사용자, kopo26 = 비밀번호
      Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.110:33060/kopoctc", "root", "kopo42");
      String QueryTxt;  //문자열변수선언
      QueryTxt = "insert into stockDailyPrice(code, date, open, high, low, closeprice, volume, transactionPrice) values(?,?,?,?,?,?,?,?)"; //쿼리문 //insert하기
      
      PreparedStatement pstmt = conn.prepareStatement(QueryTxt); //statement : sql구문을 실행하는 역할, 전달역할, sql 관리 가능. 
      //preparedStatement클래스 statement클래스의 기능을 향상 시킴. 
      //텍스트 sql을 호출한다.
      File f = new File("C:\\Users\\kopo\\day_data\\StockDailyPrice.csv"); // 해당 위치의 파일을 열기
      BufferedReader br = new BufferedReader (new FileReader(f));   // 파일 내용을 한줄 씩 읽기
      
      
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 시간 형태 지정하기
      long startTime = System.currentTimeMillis(); // 코드 실행 전에 시간 받아오기 (시작 시간)
      Date startInDate = new Date(startTime); // 형태 날짜로 변환
      String startFormat = sdf.format(startInDate); // 문자열 변수로 형태 변환
      
      
      int count = 0; //변수 초기화
      conn.setAutoCommit(false); //쿼리문이 하나라도 false면 insert되지 않는다.
      long startTime1 = System.currentTimeMillis();//시간 차이 계산. 시간 차이를 long형으로 반환한다.
      
      
      
      String readtxt; //문자열
      while ((readtxt = br.readLine()) != null) {
         String[] field = readtxt.split(",");
         
         pstmt.setString(1, field[2]); //데이트
         pstmt.setString(2, field[1]); //코드
         pstmt.setInt(3, Integer.valueOf(field[4])); //시가
         pstmt.setInt(4, Integer.valueOf(field[3])); //고가
         pstmt.setInt(5, Integer.valueOf(field[5])); //저가
         pstmt.setInt(6, Integer.valueOf(field[6])); //종가
         pstmt.setLong(7, Long.valueOf(field[11])); //거래량
         pstmt.setLong(8, Long.valueOf(field[12])); //거래대금
         pstmt.addBatch(); //addBatch메소드로 쿼리를 메모리에 올린다.
         System.out.println(count + " 번째 항목 batch 완료");
         pstmt.clearParameters(); //파라미터 실행 후 파라미터 종료
         count++; //증가
         
         try {
            if(count % 10000 == 0) { //카운트가 10000단위가 되면 
               pstmt.executeBatch();  //쿼리를 전송한다.
               conn.commit(); //커밋한다. //아직 저장되지않는 데이터를 데이터베이스에 저장하고 트랜잭션을 종료.
            }
         } catch (Exception e) {//예외처리
            e.printStackTrace(); //에러의 발생근원지를 찾아서 단계별로 에러를 출력
         }
      }
      
      try {
         pstmt.executeBatch();  //쿼리를 전송한다.
      } catch (Exception e) { //예외처리
         e.printStackTrace(); //에러의 발생근원지를 찾아서 단계별로 에러를 출력
      }
      conn.commit();//커밋한다. //아직 저장되지않는 데이터를 데이터베이스에 저장하고 트랜잭션을 종료.
      conn.setAutoCommit(true);//쿼리문 모두가 true면 insert한다.
      long finishTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
      Date finishInDate = new Date(finishTime); // 형태 날짜로 변환
      String finishFormat = sdf.format(finishInDate); // 문자열 변수로 형태 변환

      long diffTime = (finishTime - startTime); // 시간 차이 계산
      long diffTimeMin = (diffTime / 1000 / 60); // 분 구하기
      long diffTimeSec = (diffTime / 1000 % 60); // 초 구하기
      
      br.close();
      pstmt.close();  //열었으면 닫아줘야 잘 돌아간다
      conn.close(); //열었으면 닫아줘야 잘 돌아간다
      System.out.println("시작 시간 : " + startFormat); // 시작 시간 출력
      System.out.println("종료 시간 : " + finishFormat); // 종료 시간 출력
      System.out.println("소요 시간 : " + diffTimeMin + "분 " + diffTimeSec + "초"); // 소요 시간 출력
      System.out.println("총 항목 개수 : " + count); // 총 항목 개수 출력
   }

}