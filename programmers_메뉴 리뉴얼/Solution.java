import java.util.*;

class Solution {
    HashMap<String, Integer> map;
    boolean[] isVisited;

    public ArrayList<String> solution(String[] orders, int[] course) {
        map = new HashMap<>();
        ArrayList<String> answer = new ArrayList<>();

        for (int i = 0; i < orders.length; i++) { // 모든 주문에 대해 탐색
            isVisited = new boolean[orders[i].length()];
            for (int j = 0; j < course.length; j++) { // 각 주문의 모든 경우의 수 탐색
                if (course[j] <= orders[i].length()) {
                    dfs(0, "", course[j], 0, orders[i]);
                }
            }
        }
        System.out.println(map.toString());
        for (int i = 0; i < course.length; i++) {
            int max = 2;
            for (Entry<String, Integer> entrySet : map.entrySet()) {
                if (entrySet.getKey().length() == course[i]) {
                    if (entrySet.getValue() >= max) {
                        max = entrySet.getValue();
                    }
                }
            }
            for (Entry<String, Integer> entrySet : map.entrySet()) {
                if (entrySet.getKey().length() == course[i]) {
                    if (entrySet.getValue() == max) {
                        answer.add(entrySet.getKey());
                    }
                }
            }
        }

        Collections.sort(answer);

        System.out.println(answer.toString());

        return answer;
    }

    public void dfs(int idx, String menu, int maxDepth, int depth, String order) {
        if (maxDepth == depth) {
            menu = sortStr(menu);
            map.put(menu, map.getOrDefault(menu, 0) + 1);
            return;
        }

        for (int i = idx; i < order.length(); i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                dfs(i, menu + order.charAt(i), maxDepth, depth + 1, order);
                isVisited[i] = false;
            }
        }

    }

    public String sortStr(String str) {
        char[] m = str.toCharArray();
        Arrays.sort(m);
        return new String(m);
    }
}