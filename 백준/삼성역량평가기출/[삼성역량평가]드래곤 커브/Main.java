import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int[][] curveInfo;
    static int direction;
    static ArrayList<Integer> curvedirection;
    static int[] x_dir = {1, 0, -1, 0};
    static int[] y_dir = {0, -1, 0, 1};
    static int[][] map;
    static int x, y;
    public static void main(String[] args) throws IOException {
        getInput();
        map = new int[101][101];
        for (int i = 0; i < N; i++) {
            curvedirection = new ArrayList<>();
            curvedirection.add(curveInfo[i][2]);
            x = curveInfo[i][0];
            y = curveInfo[i][1];
            map[y][x] = 1;
            makeCurve(0, curveInfo[i][3]);
        }

        int answer = countSquare();

        System.out.println(answer);
    }

    private static int countSquare() {
        int rtv = 0;
        for (int i = 0; i <= 99; i++) {
            for (int j = 0; j <= 99; j++) {
                if (map[i][j] == 0) continue;
                if (map[i][j+1] == 0) continue;
                if (map[i+1][j] == 0) continue;
                if (map[i+1][j+1] == 0) continue;
                rtv++;
            }
        }
        return rtv;
    }

    private static void makeCurve(int generation, int maxGen) {
        if (generation >= maxGen) {
            for (Integer i:curvedirection) {
                x += x_dir[i];
                y += y_dir[i];
                map[y][x] = 1;
            }
        } else {
            int lastIdx = curvedirection.size()-1;
            for (int i = lastIdx; i >= 0; i--) {
                int newDirection = getNewDirection(curvedirection.get(i));
                curvedirection.add(newDirection);
            }
            makeCurve(generation + 1, maxGen);
        }
    }

    private static int getNewDirection(int direction) {
        if (direction == 0)
            return 1;
        else if (direction == 1)
            return 2;
        else if (direction == 2)
            return 3;
        else
            return 0;
    }

    private static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        curveInfo = new int[N][4];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                curveInfo[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }


}
