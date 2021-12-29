class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int max = 0;
        int min = 0;
        for (int i = 0; i < 6; i++) {
            boolean isZero = lottos[i] == 0;
            if (isZero)
                max++;
            else {
                for (int j = 0; j < 6; j++) {
                    boolean isSameNo = lottos[i] == win_nums[j];
                    if (isSameNo) {
                        min++;
                        max++;
                    }

                }
            }
        }

        if (max > 1)
            answer[0] = 7 - max;
        else
            answer[0] = 6;

        if (min > 1)
            answer[1] = 7 - min;
        else
            answer[1] = 6;

        return answer;
    }
}