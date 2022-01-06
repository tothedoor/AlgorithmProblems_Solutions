import java.util.Arrays;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int[] info = new int[n];
        Arrays.fill(info, 1);
        for (int lostIndex : lost)
            info[lostIndex - 1]--;
        for (int reserveIndex : reserve)
            info[reserveIndex - 1]++;

        for (int num : info)
            System.out.println(num);

        if (info[0] == 0) {
            if (info[1] == 2) {
                answer++;
                info[1] = 1;
            }

        } else {
            answer++;
            info[0]--;
        }
        for (int i = 1; i < n - 1; i++) {
            if (info[i] != 0) {
                answer++;
                info[i]--;
            } else {
                if (info[i - 1] != 0) {
                    answer++;
                    continue;
                } else if (info[i + 1] == 2) {
                    answer++;
                    info[i + 1]--;
                    continue;
                }
            }
        }

        if (info[n - 1] == 0) {
            if (info[n - 2] != 0)
                answer++;
        } else {
            answer++;
        }
        return answer;
    }
}