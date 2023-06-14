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
         String workKey = "A";
         Statement statement = connection.createStatement();
         String query = "";
         System.out.print("선택 입력 : ");
         // E를 제외한 나머지 경우
         while (!workKey.equals("E")) { // E를 누르면 설문 종료
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
               number = scanner.nextInt(); // 해당 설문자 번호 입력
               if (number > 4) // 최대 4명이니깐 4 이상 숫자 입력시 Error 메시지 출력
               {
                  System.out.println("-Error- 확인 후 입력 필요 ");
               } else {
                  System.out.println("-- 설문시작 -- ");
               }

               // S를 누른 경우
            } else if (workKey.equals("S")) {
               ResultSet resultSet = statement.executeQuery(query);
               int cnt = 1;
               // HashMap<String, String> memHashMap = new HashMap<>();
               // int cnt = 1;
               // query = "select `user`\n" + //
               // "from `user`";
               // ResultSet resultSet = statement.executeQuery(query);
               // String userString = resultSet.getString("USER");
               // String queString = resultSet.getString("QUESTION");
               // String ansString = resultSet.getString("answer");
               // while (resultSet.next()) {
               // memHashMap.put(String.valueOf(cnt), userString);

               // }

               // HashMap<String, String> surHashMap = new HashMap<>();
               // cnt = 1;
               // query = "select QUESTION\n" + //
               // "from question";
               // resultSet = statement.executeQuery(query);
               // while (resultSet.next()) {
               // surHashMap.put(String.valueOf(cnt), queString);
               // cnt = cnt + 1;
               // }

               // HashMap<String, String> ansHashMap = new HashMap<>();
               // cnt = 1;
               // query = "SELECT answer\n" + //
               // "from answer";
               // resultSet = statement.executeQuery(query);
               // while (resultSet.next()) {
               // ansHashMap.put(String.valueOf(cnt), ansString);
               // cnt = cnt + 1;
               // }
               // // 통계테이블 delete문
               // query = "delete from statistics";
               // statement.executeUpdate(query);
               // // 통계테이블 insert문
               // query = "INSERT INTO statistics(USER_ID, QUESTION_ID, ANSWER_ID)\n" + //
               // "VALUES ('" + userString + "', '" + queString + "', '" + ansString + "')";
               // statement.executeUpdate(query);

               System.out.println("설문 조사 통계");
               query = "select count(USER_ID) AS MEM\n" + // 총 유저의 숫자를 카운트한다.
                     "from statistics";
               resultSet = statement.executeQuery(query);
               while (resultSet.next()) {
                  System.out.println("- 총 설문자는 : " + resultSet.getInt("MEM"));

               }
               cnt = 1;
               System.out.println("--- 문항 내에서 최대 갯수 번호----");
               query = "select QUESTION\n" + // 문항을 보여줌
                     "from question ";
               resultSet = statement.executeQuery(query);
               while (resultSet.next()) {
                  System.out.println(resultSet.getString("QUESTION") + " : " + cnt);
                  cnt += 1;
               }

               System.out.println("--- 답항 중심 ---");
               query = "select ANSWER\n" + // 답항을 보여줌
                     "from answer ;\n" + //
                     "";
               resultSet = statement.executeQuery(query);
               while (resultSet.next()) {
                  System.out.println(resultSet.getString("ANSWER") + " : " + cnt);
                  cnt += 1;
               }

               // E를 눌러서 설문을 종료하는 경우
            } else {

            }
            System.out.println("----- 설문 종료 ------");
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