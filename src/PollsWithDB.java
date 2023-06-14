import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

import polls.question;

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

         while (!workKey.equals("E")) { // E를 누르면 설문 종료

            System.out.println("선택 입력 : ");
            workKey = scanner.nextLine();

            // P를 누른 경우
            if (workKey.equals("P")) { // P를 입력하면 설문가능 명단 띄우기
               System.out.println("- 설문자 가능 명단(가입 완료)");
               int number = 1; // 설문자 입력 번호
               query = "SELECT `user`.`USER`\n" + //
                     "FROM `user`\n";
               ResultSet resultSet = statement.executeQuery(query);
               while (resultSet.next()) {
                  System.out.println(number + "." + resultSet.getString("USER"));
                  number = number + 1;
               }

               System.out.print("- 설문자 번호 입력 : "); // 설문자 번호 입력
               int number2 = scanner.nextInt(); // 해당 설문자 번호 입력
               // while (number2 >= 4){
               while (number2 > 4 || number2 < 1) // 최대 4명이니깐 4 이상 숫자 입력시 Error 메시지 출력
               {

                  System.out.println("-Error- 확인 후 입력 필요 ");
                  System.out.print("- 설문자 번호 입력 : ");

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
               // ResultSet resultSet = statement.executeQuery(query);

               int cnt = 1;
               HashMap<String, String> memHashMap = new HashMap<>();

               query = "select user_id\n" + //
                     "from `user`";
               ResultSet resultSet = statement.executeQuery(query);

               while (resultSet.next()) {
                  memHashMap.put(String.valueOf(cnt), resultSet.getString("USER_ID"));
                  cnt = cnt + 1;
               }
               System.out.println(memHashMap.toString());

               HashMap<String, String> surHashMap = new HashMap<>();
               cnt = 1;
               query = "select QUESTION_id\n" + //
                     "from question";
               resultSet = statement.executeQuery(query);

               while (resultSet.next()) {
                  surHashMap.put(String.valueOf(cnt), resultSet.getString("QUESTION_ID"));
                  cnt = cnt + 1;
               }
               System.out.println(surHashMap.toString());

               HashMap<String, String> ansHashMap = new HashMap<>();
               cnt = 1;
               query = "SELECT answer_id\n" + //
                     "from answer";
               resultSet = statement.executeQuery(query);

               while (resultSet.next()) {
                  ansHashMap.put(String.valueOf(cnt), resultSet.getString("answer_ID"));
                  cnt = cnt + 1;
               }
               System.out.println(ansHashMap.toString());

               // 통계테이블 delete문
               // query = "delete from statistics";
               // statement.executeUpdate(query);
               // 통계테이블 insert문
               // String userscanner = scanner.nextLine();
               // String qursscanner = scanner.nextLine();
               // String ansscanner = scanner.nextLine();
               // // 해쉬 맵에서 확인한 키값을 통계테이블에 인서트 시킨다.
               // query = "INSERT INTO statistics(USER_ID, QUESTION_ID, ANSWER_ID)\n" + //
               //       "VALUES ('" + memHashMap.get(userscanner) + "','" + surHashMap.get(qursscanner) + "','"
               //       + ansHashMap.get(ansscanner) + "')";
               // int number3 = statement.executeUpdate(query);
               // System.out.println(number3);

               System.out.println("설문 조사 통계");
               query = "select count(USER_ID) AS MEM\n" + // 총 유저의 숫자를 카운트한다.
                     "from statistics";
               resultSet = statement.executeQuery(query);
               while (resultSet.next()) {
                  System.out.println("- 총 설문자는 : " + resultSet.getInt("MEM"));

               }
              
               System.out.println("--- 문항 내에서 최대 갯수 번호----");
               query = "select question.QUESTION, count(answer.ANSWER_ID) as MAX\n" + //
                     "from statistics\n" + //
                     "inner join answer\n" + //
                     "on statistics.ANSWER_ID = answer.ANSWER_ID\n" + //
                     "inner join question\n" + //
                     "on statistics.QUESTION_ID = question.QUESTION_ID\n" + //
                     "group by question.QUESTION_ID";
               resultSet = statement.executeQuery(query);
               while (resultSet.next()) {
                  System.out.println(resultSet.getString("question.QUESTION") + " : " + resultSet.getString("MAX"));
                  
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