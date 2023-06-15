package polls;

import java.util.HashMap;
import java.util.Scanner;

import javax.management.Query;

import java.sql.*;

public class question {
   public static void main(String[] args) {

      try {
         // - MySQL workbench 실행 : JDBC
         // - User/password와 접속 IP:port 접속
         String url = "jdbc:mysql://127.0.0.1:3306/db_polls";
         String user = "root";
         String password = "!yojulab*";
         Connection connection = DriverManager.getConnection(url, user, password); // network 자원사용
         System.out.println("DB연결 성공\n");

         Statement statement = connection.createStatement();
         Scanner scanner = new Scanner(System.in);
         String workKey = "A";
         String usernumber = "";
         int order = 0;

         // HashMAp을 이용한 DB INSERT - 타 파일과 충돌이 있어서 주석 처리
         // while (!workKey.equals("E")) { // E를 누르면 설문 종료

         // System.out.print("선택 입력 : ");
         // workKey = scanner.nextLine();

         // // P를 누른 경우
         // if (workKey.equals("P")) { // P를 입력하면 설문가능 명단 띄우기
         // System.out.println("- 설문자 가능 명단(가입 완료)");
         // int number = 1; // 설문자 입력 번호
         // query = "SELECT `user`.`USER` , `user`.`USER_ID`\n" + //
         // "FROM `user`\n";
         // ResultSet resultSet = statement.executeQuery(query);

         // while (resultSet.next()) {
         // System.out.println(number + "." + resultSet.getString("USER"));
         // userMap.put(number, resultSet.getString("USER_ID"));
         // number = number + 1;
         // }
         // int number2 = scanner.nextInt(); // 해당 설문자 번호 입력
         // System.out.print("- 설문자 번호 입력 : "); // 설문자 번호 입력

         // E를 제외한 나머지 경우

         while (!workKey.equals("E")) {
            System.out.println("선택 입력 : ");

            workKey = scanner.nextLine();
            // P를 누른 경우
            if (workKey.equals("P")) {
               System.out.println("- 설문자 가능 명단(가입 완료)");
               int num = 1;
               // 오류 부분
               String query = "SELECT USER\n" + //
                     "FROM user";
               ResultSet resultSet = statement.executeQuery(query);
               while (resultSet.next()) {
                  System.out.println(num + "." + resultSet.getString("USER"));
                  num = num + 1;
               }
               System.out.print("- 설문자 번호 입력 : "); // 설문자 번호 입력
               int number = scanner.nextInt(); // 해당 설문자 번호 입력

               if (number >= 1 && number <= 4) {
                  scanner.nextLine();
                  usernumber = "USER_0" + number;
                  System.out.println("Log In Successful");
               } else {
                  System.out.println("Load Failure, Try Again");
                  break;
               }
               HashMap<String, String> AnswerInfo = new HashMap<>();
               String query2 = "SELECT QUESTION\n" + //
                     "FROM question;";
               Statement statement2 = connection.createStatement();
               ResultSet resultSet2 = statement2.executeQuery(query2);
               while (resultSet2.next()) {

                  order = order + 1;
                  System.out.println(resultSet2.getString("QUESTION"));
                  System.out.println();
                  System.out.println("(1) 전혀 아니다. (2) 아니다. (3) 그렇다. (4) 매우 그렇다.");
                  String reply = scanner.nextLine();
                  String Answernumber = "ANSWER_0" + reply;
                  String Questionnumber = "QUESTION_0" + order;
                  System.out.println();
                  /// 결과 INSERT

                  String query3 = "INSERT INTO statistics\n" + //
                        "(ANSWER_ID, QUESTION_ID, USER_ID)\n" + //
                        "VALUES\n" + //
                        "('" + Answernumber + "', '" + Questionnumber + "', '" + usernumber + "')";
                  Statement statement3 = connection.createStatement();
                  // ResultSet resultSet3 = statement3.executeUpdate(query3);
                  int count = statement3.executeUpdate(query3);
                  if (count > 0) {
                     System.out.println("Insert successful");
                  } else {
                     System.out.println("Insert failed, Try again");
                  }
               }
               // S를 누른 경우

            } else if (workKey.equals("S")) {
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

            } else {
               System.out.println("----- 설문 종료 ------");
               Statement delete_statement = connection.createStatement();
               String delete = "DELETE FROM statistics;";
               int delete_count = delete_statement.executeUpdate(delete);
               System.out.println();
            }
         }

         // - query Edit
         // Statement statement = connection.createStatement(); // DB자원

         // 명단 P 누르면 명단

         // 설문자 번호 입력, 범주에 없을 시 오류 메세지 및 원점, 설문시작 메세지

         // 답변 맵
         // 질문 - 답변 입력 함.
      } catch (

      Exception e) {

         System.out.println(e.getMessage());
         // TODO: handle exception
      }
   }
}