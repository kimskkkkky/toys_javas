import polls.PollInitailArrays;
public class PollsWithMethod {
    public static void main(String[] args) {
        try {
            PollInitailArrays pollInitailArrays = new PollInitailArrays();
            String[][] survey = pollInitailArrays.initialArrays();
            for (int num = 0;  survey.length > num; num = num+1) {
            System.out.println(survey[num][0]);        
        }
            
            
            
             } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

