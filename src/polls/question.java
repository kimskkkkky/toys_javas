package polls;

import java.util.HashMap;
import java.util.Scanner;
import java.sql.*;

public class question {
   public static void main(String[] args) {

      try {
         // - MySQL workbench 실행 : JDBC
         // - User/password와 접속 IP:port 접속
         String url = "jdbc:mysql://127.0.0.1:3306/db_cars";
         String user = "root";
         String password = "!yojulab*";
         Connection connection = DriverManager.getConnection(url, user, password); // network 자원사용
         System.out.println("DB연결 성공\n");

         Statement statement = connection.createStatement();
         Scanner scanner = new Scanner(System.in);
         String workKey = "A";

         // E를 제외한 나머지 경우
         while (!workKey.equals("E")) {
            System.out.println("선택 입력 : ");
            workKey = scanner.nextLine();
            // P를 누른 경우
            if (workKey.equals("P")) {
               System.out.println("- 설문자 가능 명단(가입 완료)");
               int number = 1;
               HashMap<String, String> AnswerInfo = new HashMap<>();
               String query = "";
               query = "SELECT QUESTION\n" + //
                     "FROM question;";
               System.out.println("(1) 전혀 아니다. (2) 아니다. (3) 그렇다. (4) 매우 그렇다.");
               String Answernumber = scanner.nextLine();
               ResultSet resultSet = statement.executeQuery(query);
               while (resultSet.next()) {
                  number = number + 1;
                  System.out.println(resultSet.getString("QUESTION"));
                  AnswerInfo.put(String.valueOf(number), resultSet.getString("QUESTION_ID"));
               }
               String AnswerList = AnswerInfo.get(Answernumber);

               // S를 누른 경우
            } else if (workKey.equals("S")) {
               System.out.println("설문 조사 통계");
               // E를 눌러서 설문을 종료하는 경우
            } else {
               System.out.println("----- 설문 종료 ------");
            }

         }

         // - query Edit
         // Statement statement = connection.createStatement(); // DB자원

         // 명단 P 누르면 명단

         // 설문자 번호 입력, 범주에 없을 시 오류 메세지 및 원점, 설문시작 메세지

         // 답변 맵
         // 질문 - 답변 입력 함.
      } catch (Exception e) {
         // TODO: handle exception
      }
   }
}