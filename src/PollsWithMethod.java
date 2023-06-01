import polls.PollInitailArrays; //초기화 및 질문, 보기 불러오기 경로
import polls.PollScanners;  //답변 입력을 위한 경로
public class PollsWithMethod {
    public static void main(String[] args) {
        try {

            PollInitailArrays pollInitailArrays = new PollInitailArrays();  //질문, 보기 출력
            String[][] polls = pollInitailArrays.initialArrays();

            PollScanners pollScanners = new PollScanners();  //답변 스캐너로 출력
            int[] Answer = pollScanners.Arrays();
            // int count = 0;
            // System.out.print("답)" + "");
            // answer[count] = myObj.nextLine();
            // count = count + 1;
            
            
             } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

