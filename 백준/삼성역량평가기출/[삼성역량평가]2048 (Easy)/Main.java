import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int[][] board;
    static int[] nx = {0, 1, 0, -1}; // 오른쪽, 아래, 왼쪽, 위 순
    static int[] ny = {1, 0, -1, 0}; // 오른쪽, 아래, 왼쪽, 위 순
    static int max = -1;
    public static void main(String[] args) throws IOException {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        playGame(1);

        System.out.println(max);
    }

    static public void playGame(int moveNum) { // dfs로 모든 경우의 수 확인
        if (moveNum > 5) {
            findMax();
            return;
        }

        // 이동 이전의 모습 기억해둠
        int[][] boardCopy = new int[N][N];
        cloneBoard(boardCopy, board);

        moveRight();
        playGame(moveNum+1);
        cloneBoard(board, boardCopy); // 원상복구

        moveDown();
        playGame(moveNum+1);
        cloneBoard(board, boardCopy); // 원상복구

        moveLeft();
        playGame(moveNum+1);
        cloneBoard(board, boardCopy); // 원상복구

        moveUp();
        playGame(moveNum+1);
        cloneBoard(board, boardCopy); // 원상복구
    }

    static private void cloneBoard(int[][] boardCopy, int[][] boardOrigin) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                boardCopy[i][j] = boardOrigin[i][j];
        }
    }

    static private void findMax() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                if (board[i][j] > max)
                    max = board[i][j];
        }
    }

    static private void moveRight() {
        for (int r = 0; r < N; r++) { // 각  row에 대해 오른쪽으로
            int saveIndex = N-1;
            int beforeNum = 0;
            int cnt = 0;
            for (int c = N-1; c >= 0; c--) { // 맨 오른쪽부터 동작 시작
                int nowNum = board[r][c];
                if (nowNum == 0) continue;
                cnt++;
                if (cnt  < 2) {
                    beforeNum = nowNum;
                    board[r][c] = 0;
                } else {
                    if (beforeNum == nowNum) {
                        board[r][saveIndex] = nowNum * 2;
                        cnt = 0;
                    } else {
                        board[r][saveIndex] = beforeNum;
                        beforeNum = nowNum;
                        cnt = 1;
                    }
                    board[r][c] = 0;
                    saveIndex--;
                }
            }
            if (cnt == 1)
                board[r][saveIndex] = beforeNum;
        }
    }

    static private void moveDown() {
        for (int r = 0; r < N; r++) { // 각  col에 대해 아래로
            int saveIndex = N-1;
            int beforeNum = 0;
            int cnt = 0;
            for (int c = N-1; c >= 0; c--) { // 맨 위쪽부터 동작 시작
                int nowNum = board[c][r];
                if (nowNum == 0) continue;
                cnt++;
                if (cnt  < 2) {
                    beforeNum = nowNum;
                    board[c][r] = 0;
                } else {
                    if (beforeNum == nowNum) {
                        board[saveIndex][r] = nowNum * 2;
                        cnt = 0;
                    } else {
                        board[saveIndex][r] = beforeNum;
                        beforeNum = nowNum;
                        cnt = 1;
                    }
                    board[c][r] = 0;
                    saveIndex--;
                }
            }
            if (cnt == 1)
                board[saveIndex][r] = beforeNum;
        }
    }

    static private void moveLeft() {
        for (int r = 0; r < N; r++) { // 각  row에 대해 왼쪽으로
            int saveIndex = 0;
            int beforeNum = 0;
            int cnt = 0;
            for (int c = 0; c < N; c++) { // 맨 왼쪽부터 동작 시작
                int nowNum = board[r][c];
                if (nowNum == 0) continue;
                cnt++;
                if (cnt  < 2) {
                    beforeNum = nowNum;
                    board[r][c] = 0;
                } else {
                    if (beforeNum == nowNum) {
                        board[r][saveIndex] = nowNum * 2;
                        cnt = 0;
                    } else {
                        board[r][saveIndex] = beforeNum;
                        beforeNum = nowNum;
                        cnt = 1;
                    }
                    board[r][c] = 0;
                    saveIndex++;
                }
            }
            if (cnt == 1)
                board[r][saveIndex] = beforeNum;
        }
    }

    static private void moveUp() {
        for (int r = 0; r < N; r++) { // 각  col에 대해 위쪽으로
            int saveIndex = 0;
            int beforeNum = 0;
            int cnt = 0;
            for (int c = 0; c < N; c++) { // 맨 아래쪽부터 동작 시작
                int nowNum = board[c][r];
                if (nowNum == 0) continue;
                cnt++;
                if (cnt  < 2) {
                    beforeNum = nowNum;
                    board[c][r] = 0;
                } else {
                    if (beforeNum == nowNum) {
                        board[saveIndex][r] = nowNum * 2;
                        cnt = 0;
                    } else {
                        board[saveIndex][r] = beforeNum;
                        beforeNum = nowNum;
                        cnt = 1;
                    }
                    board[c][r] = 0;
                    saveIndex++;
                }
            }
            if (cnt == 1)
                board[saveIndex][r] = beforeNum;
        }
    }
}
