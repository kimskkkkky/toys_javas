
package polls;

public class PollStatistics {
    public int Total( String[] Answers)  {
            try {
                for (int first=0; first < Answers.length; first = first+1) {
                    System.out.print(Answers[first] + ", ");
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
            return 1 ;
        } 

    }
   
