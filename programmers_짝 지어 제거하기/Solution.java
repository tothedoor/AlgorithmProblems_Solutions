import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = -1;
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (!st.isEmpty()) {
                if (st.peek() == s.charAt(i)) {
                    st.pop();
                } else
                    st.push(s.charAt(i));
            } else {
                st.push(s.charAt(i));
            }
        }

        answer = st.isEmpty() ? 1 : 0;

        return answer;
    }
}