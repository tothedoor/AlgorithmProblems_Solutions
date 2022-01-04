package programmers_dollGame;

import java.util.Stack;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> dollContainer = new Stack<> ();
        for (int i = 0; i < moves.length; i++) {
            int line = moves[i] - 1;
            for (int j = 0; j < board.length; j++) {
                int doll = board[j][line];
                boolean isCatched = doll != 0;
                if (isCatched) {
                    board[j][line] = 0;
                    if (!dollContainer.isEmpty() && dollContainer.peek() == doll) {
                        System.out.println("is Same!");
                        dollContainer.pop();
                        answer += 2;
                    }
                    else {
                        dollContainer.push(doll);
                    }
                    break;
                }
               
            }
        
        }
        return answer;
    }
}