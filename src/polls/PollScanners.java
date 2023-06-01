package polls;

import java.util.Scanner;

public class PollScanners {
    public String[] pollWithAnswers(String[][] polls){
            String[] Answers = new String[4];
        try {
            int count = 0;

            Scanner myObj = new Scanner(System.in);

            for (int num = 0;  polls.length >= num; num = num+2) {
                System.out.println(polls[num][0]); 
                System.out.println(polls[num+1][0]);
                System.out.print("답)" + "");
                Answers[count] = myObj.nextLine();
                count = count + 1;
                System.out.println();    
                
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        return Answers;
    }       
}
