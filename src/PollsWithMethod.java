import polls.PollInitailArrays; //초기화 및 질문, 보기 불러오기 경로
import polls.PollScanners;  //답변 입력을 위한 경로
import polls.PollStatistics;

public class PollsWithMethod {
    public static void main(String[] args) {

        try {
            String[] anwsers;
            String[][] polls;
            PollInitailArrays pollInitailArrays = new PollInitailArrays();  //질문, 보기 출력
            polls = pollInitailArrays.initialArrays();
            PollScanners pollScanners = new PollScanners(); 
            anwsers = pollScanners.pollWithAnswers(polls);
            PollStatistics pollStatistics = new PollStatistics();
            pollStatistics.Total(anwsers);
            } catch (Exception e) {
            // TODO: handle exception
        }
        // return 0;
    }
}

