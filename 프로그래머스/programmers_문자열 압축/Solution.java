import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 100000000;
        int s_len = s.length();
        if (s_len == 1)
            answer = 1;
        
        for (int i = 1; i <= s_len/2; i++) {
            int length = s_len % i;
            Stack<String> st = new Stack<>();
            for (int j = 0; j + i - 1 < s_len; j += i) {
                String target = s.substring(j,j+i);
                if (j == 0) {
                    st.push(target);
                    continue;
                }
                else {
                    if (target.equals(st.peek())) {
                        st.push(target);
                    }
                    else {
                        int stSize = st.size();
                        if (stSize > 1) {
                            st.clear();
                            st.push(target);
                            length += Math.log10(stSize) + 1 + i;
                        }
                        else {
                            length += i;
                            st.clear();
                            st.push(target);
                        }
                    }
                }
            }
            if (st.size() > 1)
                length += Math.log10(st.size()) + 1 + i;
            else if (!st.empty())
                length += i;
                if (length < answer)
                    answer = length;
        }
        
        return answer;
    }
}