import polls.PollInitailArrays;
public class PollsWithMethod {
    public static void main(String[] args) {
        try {
            PollInitailArrays pollInitailArrays = new PollInitailArrays();
            String[][] survey = pollInitailArrays.initialArrays();
            for (int num = 0;  polls.length > num; num = num+1) {
            System.out.println(polls[num]);        
        }
            
            
            
             } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
