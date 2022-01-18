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
        getInput(); // �Է� �ޱ�
        min = 3000; // ���� �ּҰ� ����
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
        if (isSuccess()) { // ������ �����ϴ� ���
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
        } else if (pickCnt > 3) { // ���� �������� �ʴµ� �̹� M���� ���μ� ������ ���
            return;
        }

        // ������ ������ rowInfoOrign�� �����ص�
        int[][] rowInfoOrign = new int[N][H+1];
        copy(rowInfoOrign, rowInfo);

        for (int col = 1; col < N; col++) {
            for (int row = 1; row < H+1; row++) {
                if (rowInfo[col][row] == 1) continue; // �̹� ���õ� ���μ��� ���
                if (col != 1) {
                    if (beforeRow == row) continue; // 1 ���� ���μ��� �ƴ� ��쿡�� ���� ���� ���μ��� ���� ���̿��� �ȵȴ�.
                }
                rowInfo[col][row] = 1; // ��� ���� �����ϴ� ��� �ش� ���μ� �������ش�.
                pickRow(row, pickCnt+1); // ���μ� ���� �Ŀ� ���� ���μ� �����ϵ��� ��� ȣ��
                copy(rowInfo, rowInfoOrign); // ������ ���·� rowInfo�� ���ͽ�Ŵ
            }
        }
    }


    private static void copy(int[][] clone, int[][] origin) {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < H+1; j++)
                clone[i][j] = origin[i][j];
    }

    private static boolean isSuccess() {
        for (int col = 1; col <= N; col++) { // ��� ���μ��� ���ؼ� Ž��
            int nowCol = col;
            for (int row = 1; row < H+1; row++) { // �� ���μ��� ���� ������ Ȯ��
                if (nowCol == 1) { // 1�� ���μ��� ���
                    if (rowInfo[nowCol][row] == 1)
                        nowCol++; // 2�� ���μ����� �̵�
                } else if (nowCol == N) { // N�� ���μ��� ���
                    if (rowInfo[nowCol-1][row] == 1)
                        nowCol--; // N-1�� ���μ����� �̵�
                } else { // ���� �ִ� ���μ����� �ƴ� ���
                    if (rowInfo[nowCol][row] == 1) // �����ʰ� ����� ���
                        nowCol++; // ���������� �̵�
                    else if (rowInfo[nowCol-1][row] == 1) // ���ʰ� ����� ���
                        nowCol--; // �������� �̵�
                }
            }
            if (nowCol != col) // �������� �ڱ� �ڽ��� �ƴ� ��� false;
                return false;
        }
        return true; // ��� ���μ��� �������� �ڱ� �ڽ��� ��� true;
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
            rowInfo[b][a] = 1; // b�� ���μ����� b+1�� ���μ����� a�� ���μ����� ����
        }
    }

}