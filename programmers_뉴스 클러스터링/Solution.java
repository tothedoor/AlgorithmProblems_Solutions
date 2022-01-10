import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        ArrayList<String> str1_subs = new ArrayList<>();
        ArrayList<String> str2_subs = new ArrayList<>();
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        int union = 0;
        int inter = 0;

        for (int i = 1; i < str1.length(); i++) {
            char c_before = str1.charAt(i - 1);
            char c_current = str1.charAt(i);
            if (isAlphabets(c_before, c_current)) {
                String elem = str1.substring(i - 1, i + 1);
                str1_subs.add(elem);
            }
        }
        for (int i = 1; i < str2.length(); i++) {
            char c_before = str2.charAt(i - 1);
            char c_current = str2.charAt(i);
            if (isAlphabets(c_before, c_current)) {
                String elem = str2.substring(i - 1, i + 1);
                str2_subs.add(elem);
            }

        }
        Collections.sort(str1_subs);
        Collections.sort(str2_subs);
        for (String sub : str1_subs) {
            if (str2_subs.contains(sub)) {
                inter++;
                union++;
                str2_subs.remove(sub);
            } else
                union++;
        }
        for (String sub : str2_subs)
            union++;

        double sameRate;
        if (union == 0)
            sameRate = 1;
        else {
            sameRate = (double) inter / (double) union;
        }

        return (int) (sameRate * 65536);
    }

    public boolean isAlphabets(char c1, char c2) {
        boolean rtv = false;
        boolean isC1True = c1 >= 'a' && c1 <= 'z';
        boolean isC2True = c2 >= 'a' && c2 <= 'z';
        if (isC1True && isC2True)
            rtv = true;
        return rtv;
    }
}