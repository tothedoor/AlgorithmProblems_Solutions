// 문제출처 : https://programmers.co.kr/learn/courses/30/lessons/42842

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int y_half = yellow / 2;
        
        for (int i = 1; i <= y_half + 1; i++) {
            if (yellow % i != 0) continue; // 약수가 아닌 경우
            
            // yellow칸의 가로, 세로 추정치 and 추정치인 경우에 brown 칸의 개수 추정치
            int width = yellow / i;
            int height = i;
            int calBrown = (width * 2) + (height * 2) + 4;
            
            if (calBrown == brown) { // 계산한 값이 실제 값과 같은 경우
                answer[0] = width + 2;
                answer[1] = height + 2;
                break;
            }
        }
        
        return answer;
    }
}