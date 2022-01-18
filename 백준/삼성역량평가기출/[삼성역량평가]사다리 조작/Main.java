import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int M;
    static int H;
    static int[][] rowInfo;
    static int min;
    public static void main(String[] args) throws IOException {
        getInput(); // 입력 받기
        min = 3000; // 최초 최소값 설정
        if (M == 0)
            min = 0;
        else if (M == 1)
            if (H > 1)
                min = 1;
            else
                min = -1;
        else
            pickRow(0, 0);

        if (min > 3)
            min = -1;
        System.out.println(min);
    }

    private static void pickRow(int beforeRow, int pickCnt) {
        if (isSuccess()) { // 조건을 만족하는 경우
            //System.out.printf("pickCnt:%d\n", pickCnt);
            min = Math.min(min, pickCnt);
            /*
            for (int i = 1; i < H+1; i++) {
                for (int j = 1; j < N; j++) {
                    System.out.print(rowInfo[j][i] + " ");
                }
                System.out.println();
            }
            System.out.println();
            */
            return;
        } else if (pickCnt > 3) { // 조건 만족하지 않는데 이미 M개의 가로선 선택한 경우
            return;
        }

        // 원래의 정보를 rowInfoOrign에 저장해둠
        int[][] rowInfoOrign = new int[N][H+1];
        copy(rowInfoOrign, rowInfo);

        for (int col = 1; col < N; col++) {
            for (int row = 1; row < H+1; row++) {
                if (rowInfo[col][row] == 1) continue; // 이미 선택된 가로선인 경우
                if (col != 1) {
                    if (beforeRow == row) continue; // 1 번재 세로선이 아닌 경우에는 이전 선택 가로선과 같은 높이여선 안된다.
                }
                rowInfo[col][row] = 1; // 모든 조건 만족하는 경우 해당 가로선 선택해준다.
                pickRow(row, pickCnt+1); // 가로선 선택 후에 다음 가로선 선택하도록 재귀 호출
                copy(rowInfo, rowInfoOrign); // 원래의 상태로 rowInfo를 복귀시킴
            }
        }
    }


    private static void copy(int[][] clone, int[][] origin) {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < H+1; j++)
                clone[i][j] = origin[i][j];
    }

    private static boolean isSuccess() {
        for (int col = 1; col <= N; col++) { // 모든 세로선에 대해서 탐색
            int nowCol = col;
            for (int row = 1; row < H+1; row++) { // 각 세로선에 대해 종착지 확인
                if (nowCol == 1) { // 1번 세로선인 경우
                    if (rowInfo[nowCol][row] == 1)
                        nowCol++; // 2번 세로선으로 이동
                } else if (nowCol == N) { // N번 세로선인 경우
                    if (rowInfo[nowCol-1][row] == 1)
                        nowCol--; // N-1번 세로선으로 이동
                } else { // 끝에 있는 세로선들이 아닌 경우
                    if (rowInfo[nowCol][row] == 1) // 오른쪽과 연결된 경우
                        nowCol++; // 오른쪽으로 이동
                    else if (rowInfo[nowCol-1][row] == 1) // 왼쪽과 연결된 경우
                        nowCol--; // 왼쪽으로 이동
                }
            }
            if (nowCol != col) // 종착지가 자기 자신이 아닌 경우 false;
                return false;
        }
        return true; // 모든 세로선의 종착지가 자기 자신인 경우 true;
    }

    private static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        rowInfo = new int[N][H+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            rowInfo[b][a] = 1; // b번 세로선에서 b+1번 세로선으로 a번 가로선에서 연결
        }
    }

}