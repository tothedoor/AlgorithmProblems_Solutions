class Solution {
    public int getRank(int sameNumCnt) {
        int rank;
        if (sameNumCnt < 2)
            rank = 6;
        else
            rank = 7 - sameNumCnt;
        return rank;
    }
    
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
        
        answer[0] = getRank(max);
        answer[1] = getRank(min);
        
        return answer;
    }
}