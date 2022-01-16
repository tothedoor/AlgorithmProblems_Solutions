import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int[][] abilities;
    static boolean[] isPick;
    static int min = 2000000;
    public static void main(String[] args) throws IOException {
        getInput();

        makeTeamAndCheck(0,0);

        System.out.println(min);
    }

    private static void makeTeamAndCheck(int num, int teamNum) {
        if (teamNum > N/2) {
            // 5명에 대해 능력치합, 상대편 능력치 합 구하고 비교, 최소값과 비교
            ArrayList<Integer> teamA = new ArrayList<>();
            ArrayList<Integer> teamB = new ArrayList<>();
            // teamA와 B를 구분
            for (int i = 0; i < N; i++) {
                if (isPick[i])
                    teamA.add(i);
                else
                    teamB.add(i);
            }

            // 능력치 합 구하기
            int A_total = 0;
            int B_total = 0;
            for (int i = 0; i < N/2; i++) {
                for (int j = 0; j < N/2; j++) {
                    A_total += abilities[teamA.get(i)][teamA.get(j)];
                    B_total += abilities[teamB.get(i)][teamB.get(j)];
                }
            }

            // 차이의 절대값과 min을 비교
            int difference = Math.abs(A_total - B_total);
            min = Math.min(min, difference);
            return;
        }

        for (int i = num; i < N; i++) {
            if (isPick[i]) // 이미 선택된 선수라면 패스
                continue;
            // 선택되지 않은 경우라면
            if ((teamNum + 1) <= N/2)
                isPick[i] = true;
            makeTeamAndCheck(i,teamNum+1);
            isPick[i] = false;
        }

    }

    private static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        abilities = new int[N][N];
        isPick = new boolean[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                abilities[i][j] = Integer.parseInt(st.nextToken());
            }
        }

    }
}