/*
문제 출처 : https://programmers.co.kr/learn/courses/30/lessons/42883#
문제 유형 : Greedy
*/


class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder(number);
        int len = number.length();
        
        
        for (int i = 0; i < k; i++) {
            int idx = 0;
            while (idx < len - 1) {
                if (answer.charAt(idx) < answer.charAt(idx+1)) {
                    break;
                }
                idx++;
            }
            answer.deleteCharAt(idx);
        }
        
        return answer.toString();
    }
}