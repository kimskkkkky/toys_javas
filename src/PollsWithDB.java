import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

import java.sql.Statement;

public class PollsWithDB {
   public static void main(String[] args) {

      try {
         // - MySQL workbench 실행 : JDBC
         // - User/password와 접속 IP:port 접속
         String url = "jdbc:mysql://127.0.0.1:3306/db_poll";
         String user = "root";
         String password = "!yojulab*";

         Connection connection = DriverManager.getConnection(url, user, password); // network 자원사용
         System.out.println("DB연결 성공\n");
         Scanner scanner = new Scanner(System.in);
         String workKey = "";
         Statement statement = connection.createStatement();
         String query = "";
         // E를 제외한 나머지 경우
         HashMap<Integer, String> userMap = new HashMap<>();
         while (!workKey.equals("E")) { // E를 누르면 설문 종료

            System.out.print("선택 입력 : ");
            workKey = scanner.nextLine();

            // P를 누른 경우
            if (workKey.equals("P")) { // P를 입력하면 설문가능 명단 띄우기
               System.out.println("- 설문자 가능 명단(가입 완료)");
               int number = 1; // 설문자 입력 번호
               query = "SELECT `user`.`USER` , `user`.`USER_ID`\n" + //
                     "FROM `user`\n";
               ResultSet resultSet = statement.executeQuery(query);

               while (resultSet.next()) {
                  System.out.println(number + "." + resultSet.getString("USER"));
                  userMap.put(number, resultSet.getString("USER_ID"));
                  number = number + 1;
               }
               int number2 = scanner.nextInt(); // 해당 설문자 번호 입력
               System.out.print("- 설문자 번호 입력 : "); // 설문자 번호 입력

               while (number2 < 1 || number2 > 4) // 최대 4명이니깐 4 이상 숫자 입력시 Error 메시지 출력
               {
                  statement = connection.createStatement();
                  String query3 = "INSERT INTO statistics\n" + //
                        "(ANSWER_ID, QUESTION_ID, USER_ID)\n" + //
                        "VALUES\n" + //
                        "('ANSWER_01','QUESTION_01', '" + userMap.get(number2) + "')";
                  int count = statement.executeUpdate(query3);
                  System.out.println(count);
                  System.out.println("-Error- 확인 후 입력 필요 ");
                  System.out.print("- 설문자 번호 입력 : ");

                  // private static String getParticipantUserId(String participantId) {
                  // 메인 파일의 USER 쿼리에서 해당 participantId에 해당하는 USER_ID를 추출하는 로직 구현
                  // 추출한 USER_ID를 반환
                  number2 = scanner.nextInt();
               }

               System.out.println("-- 설문시작 -- ");
               query = "select QUESTION, ANSWER\n" + //
                     "from statistics\n" + //
                     "inner join question\n" + //
                     "on statistics.QUESTION_ID = QUESTION.question_id\n" + //
                     "inner join answer\n" + //
                     "on statistics.ANSWER_ID = answer.ANSWER_ID";
               resultSet = statement.executeQuery(query);
               while (resultSet.next()) {
                  System.out.println(resultSet.getString("question"));
                  while (resultSet.next()) {
                     System.out.print(resultSet.getString("answer") + " ");

                  }
               }

               // 여기까지 복사

               // S를 누른 경우
            } else if (workKey.equals("S")) {

               System.out.println("설문 조사 통계");
               query = "select count(*) as users\n" + //
                     "from \n" + //
                     "(select user_id\n" + //
                     "from statistics\n" + //
                     "group by user_id) as T_user";
               ResultSet resultSet = statement.executeQuery(query);
               while (resultSet.next()) {
                  System.out.println("- 총 설문자는 : " + resultSet.getInt("users"));

                  System.out.println("--- 답항 중심 ---");
                  query = "select  answer.answer, count(statistics.ANSWER_ID) as CNT\n" + //
                        "from statistics\n" + //
                        "inner join answer\n" + //
                        "on statistics.ANSWER_ID = answer.ANSWER_ID\n" + //
                        "group by statistics.ANSWER_ID";
                  resultSet = statement.executeQuery(query);

                  while (resultSet.next()) {
                     System.out.println(resultSet.getString("answer.ANSWER") + " : " + resultSet.getString("CNT"));

                  }

                  // E를 눌러서 설문을 종료하는 경우
               }

               System.out.println("--- 문항 내에서 최대 갯수 번호----");
               query = "select question.QUESTION\n" + //
                     "from statistics\n" + //
                     "inner join question\n" + //
                     "on statistics.QUESTION_ID = question.QUESTION_ID\n" + //
                     "group by question.QUESTION";
               resultSet = statement.executeQuery(query);

               while (resultSet.next()) {
                  System.out.println(resultSet.getString("question.QUESTION") + " : ");
                  // String query2 = "";
                  // query2 = "select count(statistics.ANSWER_ID) as counting\n" + //
                  // "from statistics\n" + //
                  // "inner join answer\n" + //
                  // "on statistics.ANSWER_ID = answer.ANSWER_ID\n" + //
                  // "group by statistics.ANSWER_ID";
                  // resultSet = statement.executeQuery(query2);
                  // while (resultSet.next()) {
                  // System.out.println(resultSet.getInt("counting"));

                  // } 추가 적이 맥스값을 못 집어 넣음.

               }

               System.out.println("--- 답항 중심 ---");
               query = "select  answer.answer, count(statistics.ANSWER_ID) as CNT\n" + //
                     "from statistics\n" + //
                     "inner join answer\n" + //
                     "on statistics.ANSWER_ID = answer.ANSWER_ID\n" + //
                     "group by statistics.ANSWER_ID";
               resultSet = statement.executeQuery(query);

               while (resultSet.next()) {
                  System.out.println(resultSet.getString("answer.ANSWER") + " : " + resultSet.getString("CNT"));

               }

               // E를 눌러서 설문을 종료하는 경우

            } else if (workKey.equals("E")) {
               System.out.println("----- 설문 종료 ------");
            } else {

               System.out.println("--- 입력 값을 확인 " + workKey);

            }

         }

         // - query Edit
         // Statement statement = connection.createStatement(); // DB자원

         // 명단 P 누르면 명단

         // 설문자 번호 입력, 범주에 없을 시 오류 메세지 및 원점, 설문시작 메세지

         // 답변 맵
         // 질문 - 답변 입력
         // scanner.close();

      } catch (

      Exception e) {
         System.out.println(e.getMessage());
      }
   }
}
