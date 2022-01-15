/*
[문제 출처] https://www.acmicpc.net/problem/14501
[문제 유형] DFS + 완전탐색
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int[][] scedule;
    static int max = 0;
    public static void main(String[] args) throws Exception {
        getInput();

        check(1, 1,0, "");

        System.out.println(max);
    }

    private static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        scedule = new int[N+1][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            scedule[i+1][0] = Integer.parseInt(st.nextToken());
            scedule[i+1][1] = Integer.parseInt(st.nextToken());
        }
    }

    static private void check(int day, int nextDay, int sum, String d) {
        if (day > N || nextDay > N + 1) {// day로 받은 값이 기간을 초과한 경우
            max = Math.max(max, sum);
            return;
        }
        if (day != nextDay) {
            sum += scedule[day][1];
            d += Integer.toString(day);
        }
        max = Math.max(max, sum);

        for (int i = nextDay; i <= N; i++) {
            int newNextDay = i + scedule[i][0];
            check(i, newNextDay, sum, d);
        }
    }
}