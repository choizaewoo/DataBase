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
      //java build path�� add external jar�� ����� mysql ���� jar�� �����ϴ� ���
      Class.forName("com.mysql.cj.jdbc.Driver");
      //ip�ּҴ� mysql�� �����ϴ� ������ ip�� port ��ȣ, /kopoctc�� �����Ϸ��� �����ͺ��̽�, root = �����, kopo26 = ��й�ȣ
      Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.110:33060/kopoctc", "root", "kopo42");
      String QueryTxt;  //���ڿ���������
      QueryTxt = "insert into stockDailyPrice(code, date, open, high, low, closeprice, volume, transactionPrice) values(?,?,?,?,?,?,?,?)"; //������ //insert�ϱ�
      
      PreparedStatement pstmt = conn.prepareStatement(QueryTxt); //statement : sql������ �����ϴ� ����, ���޿���, sql ���� ����. 
      //preparedStatementŬ���� statementŬ������ ����� ��� ��Ŵ. 
      //�ؽ�Ʈ sql�� ȣ���Ѵ�.
      File f = new File("C:\\Users\\kopo\\day_data\\StockDailyPrice.csv"); // �ش� ��ġ�� ������ ����
      BufferedReader br = new BufferedReader (new FileReader(f));   // ���� ������ ���� �� �б�
      
      
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // �ð� ���� �����ϱ�
      long startTime = System.currentTimeMillis(); // �ڵ� ���� ���� �ð� �޾ƿ��� (���� �ð�)
      Date startInDate = new Date(startTime); // ���� ��¥�� ��ȯ
      String startFormat = sdf.format(startInDate); // ���ڿ� ������ ���� ��ȯ
      
      
      int count = 0; //���� �ʱ�ȭ
      conn.setAutoCommit(false); //�������� �ϳ��� false�� insert���� �ʴ´�.
      long startTime1 = System.currentTimeMillis();//�ð� ���� ���. �ð� ���̸� long������ ��ȯ�Ѵ�.
      
      
      
      String readtxt; //���ڿ�
      while ((readtxt = br.readLine()) != null) {
         String[] field = readtxt.split(",");
         
         pstmt.setString(1, field[2]); //����Ʈ
         pstmt.setString(2, field[1]); //�ڵ�
         pstmt.setInt(3, Integer.valueOf(field[4])); //�ð�
         pstmt.setInt(4, Integer.valueOf(field[3])); //��
         pstmt.setInt(5, Integer.valueOf(field[5])); //����
         pstmt.setInt(6, Integer.valueOf(field[6])); //����
         pstmt.setLong(7, Long.valueOf(field[11])); //�ŷ���
         pstmt.setLong(8, Long.valueOf(field[12])); //�ŷ����
         pstmt.addBatch(); //addBatch�޼ҵ�� ������ �޸𸮿� �ø���.
         System.out.println(count + " ��° �׸� batch �Ϸ�");
         pstmt.clearParameters(); //�Ķ���� ���� �� �Ķ���� ����
         count++; //����
         
         try {
            if(count % 10000 == 0) { //ī��Ʈ�� 10000������ �Ǹ� 
               pstmt.executeBatch();  //������ �����Ѵ�.
               conn.commit(); //Ŀ���Ѵ�. //���� ��������ʴ� �����͸� �����ͺ��̽��� �����ϰ� Ʈ������� ����.
            }
         } catch (Exception e) {//����ó��
            e.printStackTrace(); //������ �߻��ٿ����� ã�Ƽ� �ܰ躰�� ������ ���
         }
      }
      
      try {
         pstmt.executeBatch();  //������ �����Ѵ�.
      } catch (Exception e) { //����ó��
         e.printStackTrace(); //������ �߻��ٿ����� ã�Ƽ� �ܰ躰�� ������ ���
      }
      conn.commit();//Ŀ���Ѵ�. //���� ��������ʴ� �����͸� �����ͺ��̽��� �����ϰ� Ʈ������� ����.
      conn.setAutoCommit(true);//������ ��ΰ� true�� insert�Ѵ�.
      long finishTime = System.currentTimeMillis(); // �ڵ� ���� �Ŀ� �ð� �޾ƿ���
      Date finishInDate = new Date(finishTime); // ���� ��¥�� ��ȯ
      String finishFormat = sdf.format(finishInDate); // ���ڿ� ������ ���� ��ȯ

      long diffTime = (finishTime - startTime); // �ð� ���� ���
      long diffTimeMin = (diffTime / 1000 / 60); // �� ���ϱ�
      long diffTimeSec = (diffTime / 1000 % 60); // �� ���ϱ�
      
      br.close();
      pstmt.close();  //�������� �ݾ���� �� ���ư���
      conn.close(); //�������� �ݾ���� �� ���ư���
      System.out.println("���� �ð� : " + startFormat); // ���� �ð� ���
      System.out.println("���� �ð� : " + finishFormat); // ���� �ð� ���
      System.out.println("�ҿ� �ð� : " + diffTimeMin + "�� " + diffTimeSec + "��"); // �ҿ� �ð� ���
      System.out.println("�� �׸� ���� : " + count); // �� �׸� ���� ���
   }

}