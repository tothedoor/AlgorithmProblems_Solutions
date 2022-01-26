/*
문제 출처 : https://programmers.co.kr/learn/courses/30/lessons/42860
문제 유형 : Greedy
*/

import java.util.*;

class Solution {
    public int solution(String name) {
        int answer = 0;
        int targetCnt = 0;
        int nameLen = name.length();
        int movement = nameLen-1;
        
        for (int i = 0; i < nameLen; i++) {
            char c = name.charAt(i);
            answer += getMovementCnt(c);
            
            int nextIdx = i + 1;
            
            // 현 위치에서 다음 번 'A'가 아닌 위치의 index를 찾음
            while (nextIdx < nameLen && name.charAt(nextIdx) == 'A') {
                nextIdx++;
            }
            
            int distanceA = i;
            int distanceB = nameLen - nextIdx;
            movement = Math.min(movement, Math.min(2*distanceA + distanceB, 2*distanceB + distanceA));
            
        }
        
        return answer + movement;
    }
    
    private int getMovementCnt(char c) {
        if (c == 'A') return 0;
        if (c > 'N') { // O ~ Z 까지
            return 'Z' - c + 1;
        } else { // A ~ N 까지
            return c - 'A';
        }
    }
}