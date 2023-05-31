import polls.PollInitailArrays; //초기화 및 질문, 보기 불러오기 경로
import polls.PollScanners;  //답변 입력을 위한 경로
public class PollsWithMethod {
    public static void main(String[] args) {
        try {
            Scanner myObj = new Scanner(System.in);
            PollInitailArrays pollInitailArrays = new PollInitailArrays();  //질문, 보기 출력
            String[][] survey = pollInitailArrays.initialArrays();
            for (int num = 0;  survey.length > num; num = num+1) {
            System.out.println(survey[num][0]);        
        }
            PollScanners pollScanners = new PollScanners();  //답변 스캐너로 출력
            int [] answer = pollScanners.Arrays();
            int count = 0;
            System.out.print("답)" + "");
            answer[count] = myObj.nextLine();
            count = count + 1;
            
            
             } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

