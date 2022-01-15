import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
    static int N, K, L;
    static int[][] board;
    static char[] directions;
    static int[] x_dir = {0, 1, 0, -1}; // 오른쪽 아래 왼쪽 위쪽 순이다.
    static int[] y_dir = {1, 0, -1, 0};
    static LinkedList<int[]> snake;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            board[r-1][c-1] = 1;
        }
        L = Integer.parseInt(br.readLine());
        directions = new char[100000];
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            directions[time] = dir;
        }
        // input 받는 부분 끝

        snake = new LinkedList<>();
        snake.add(new int[] {0,0}); // 시작하는 곳 정보 넣음
        int time = 0;
        int dirNum = 0; // 오른쪽 방향으로 시작
        int x = 0, y = 0; // 뱀의 머리의 시작 위치
        while (true) {
            time++;
            x += x_dir[dirNum];
            y += y_dir[dirNum];

            // 게임 종료되는지 확인
            if (isFinish(x, y))
                break;

            if (board[x][y] == 1) { // 사과를 먹는 경우
                board[x][y] = 0;
                snake.add(new int[] {x, y});
            } else { // 먹지 않은 경우
                snake.add(new int[] {x, y});
                snake.remove(0);
            }

            // 다음 방향 결정
            dirNum = getNewDirection(dirNum, directions[time]);
        }
        System.out.println(time);

    }

    private static int getNewDirection(int dirNum, char d) {
        if (d == 'D') {
            dirNum = (dirNum+1) % 4;
        } else if (d == 'L') {
            if (dirNum == 0)
                dirNum = 3;
            else if (dirNum == 1)
                dirNum = 0;
            else if (dirNum == 2)
                dirNum = 1;
            else
                dirNum = 2;
        }
        return dirNum;
    }

    private static boolean isFinish(int x, int y) {
        if (!isInBoard(x, y))
            return true;

        for (int i = 0; i < snake.size(); i++) {
            if (snake.get(i)[0] == x && snake.get(i)[1] == y)
                return true;
        }

        return false;
    }

    private static boolean isInBoard(int x, int y) {
        if ((x >= 0 && x < N) && (y >= 0 && y < N))
            return true;
        return false;
    }

}
