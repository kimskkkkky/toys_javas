import java.util.Scanner;

public class PollsWithoutMethodCopy {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String answer = "";
        String[] answers = {"", ""};
        String[][] polls = {
            {"1. 문항"}, 
            {"(1) 답항", "(2) 답항", "(3) 답항"},
            {"2. 문항"},
            {"(1) 답항", "(2) 답항", "(3) 답항"},
        };

        int count = 0;
        for (int second=0; second < polls.length; second=second+2){
            System.out.println(polls[second][0]);
            // 답항 출력 본인이 해 보기

            System.out.print("답하기 : ");
            answers[count] = myObj.nextLine();
            count = count + 1;
            System.out.println();
        }

        for (int first=0; first < answers.length; first=first+1) {
            System.out.print(answers[first]+", ");
        }
        System.out.println();
        // return 0;
    }
}
