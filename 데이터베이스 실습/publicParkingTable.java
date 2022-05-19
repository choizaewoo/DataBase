package DB;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class publicParkingTable {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver"); // class.forName()메소드를 호출하여 mysql에서 제공하는 driver 클래스를 
		//JVM method area에 로딩시킨다.
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.110:33060/kopoctc", "root", "kopo42");	//데이터베이스 연결
		//localhost:3306와 스키마 이름을 지정한다.
		 //connection 객체를 만들어사용한다. 방법은 DriverManager클래스의 static 메소드인 getConnetion()메소드를 호출해서, mysql에 연결하기 위한
		//커넥션정보(url,user,passwd)를 입력한다. getConnection()메소드 수행 결과로 connection객체를 반환하는데 , 이객체를 통해 
		//쿼리를 날리는 statement를 작성할 수 있다.
		Statement stmt = conn.createStatement();// SQL를 위한 객체 생성
		
		File f = new File("C:\\Users\\kopo\\folder\\전국공영주차장정보.txt"); //파일불러오기
		BufferedReader br = new BufferedReader(new FileReader(f));//bufferReader로 파일 읽기
		
		String readtxt;
		
		  if((readtxt = br.readLine()) == null) { //bufferReader로 라인마다 읽어온 파일을 readtxt에 저장이 된다면
		         System.out.println("빈 파일입니다");//빈파일입니다.
		         return; //값 반환
		      } 
		      
		      String[] field_name = readtxt.split("\t"); //readtxt을 탭으로 쪼개서 배열에 저장한다.
		      
		      HashSet<String> hashSet = new HashSet<String>(); //중복된 값을 제거하기 위해 set의 인터페이스 hashset 클래스를 사용한다.
		      
		      int LineCnt = 0; //linecnt를 초기화 
		      while((readtxt = br.readLine()) != null) { 
		    	  //버퍼리더로 읽어온 파일이 공백을 허용하지 않는다면 
		         hashSet.add(readtxt); //readtxt 값을 추가한다. 
		      }
		      
		      List hsList = new ArrayList(hashSet); //메모리가 허용하는 한 계속해서 중복이 제거된 값을 추가한다.
		      
		      for (int i = 0; i < hsList.size(); i++) { //for문 
		    	  //변수 i가 0부터 hsList의 크기만큼 반복한다. 
		         String[] field = hsList.get(i).toString().split("\t"); // 인덱스 i의 값을 toString으로 문자열로 바꿔주고 탭으로 쪼개어 저장한다.
		         System.out.println(hsList.get(i)); //인덱스i값 출력.		       
		         String QueryTxt; //문자열 변수 선언 
		         QueryTxt = String.format("insert into publicParking ("  //쿼리문에 포맷팅된 문자열을 저장한다. 테이블 column에 insert한다.
		               + "parkingOwnNumber, parkingName, streetAddress," 
		               + "jibeonAddress,parkingAvailable, part, operatingDay, operatingDayTimeStart,"
		               + "operatingDayTimeEnd, latitude, longitude)"
		               + "values("
		               + "'%s','%s','%s','%s','%s',"
		               + "'%s','%s','%s','%s','%s',"
		               + "'%s');", //따옴표가 빠진 부분 보충
		               field[0], field[1], field[2], field[3], field[4],
		               field[5], field[6], field[7], field[8], field[9],
		               field[10]);
		         stmt.execute(QueryTxt); //쿼리문 실행한다.
		         System.out.printf("%d번째 항목 insert OK\n\n", LineCnt); //Linecnt 출력
		         
		         LineCnt++; //Linecnt 증가
		               
		      }
		      br.close(); //bufferReader 종료.
		      stmt.close(); //명령 실행 함수 종료, 프로그램의 오작동을 위해 꼭 종료할 것
		      conn.close(); //연결 종료, 프로그램의 오작동을 위해 꼭 종료할 것
		   }

}