import java.util.ArrayList;

class Solution {
    public int[] solution(int[] answers) {
        int[] answersNum = { 0, 0, 0 };
        int[] supoja1_Answers = { 1, 2, 3, 4, 5 };
        int[] supoja2_Answers = { 2, 1, 2, 3, 2, 4, 2, 5 };
        int[] supoja3_Answers = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };

        for (int i = 0; i < answers.length; i++) {
            if (supoja1_Answers[i % 5] == answers[i])
                answersNum[0]++;
            if (supoja2_Answers[i % 8] == answers[i])
                answersNum[1]++;
            if (supoja3_Answers[i % 10] == answers[i])
                answersNum[2]++;
        }

        int max = -1;
        for (int num : answersNum)
            if (num > max)
                max = num;

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < answersNum.length; i++)
            if (answersNum[i] == max)
                list.add(i + 1);

        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}