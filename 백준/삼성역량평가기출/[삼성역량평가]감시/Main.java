import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static ArrayList<int[]> cctvs;
    static int[][] rooms_origin;
    static int[][] rooms_copy;
    static int min;
    public static void main(String[] args) throws IOException {
        getInput();
        if (cctvs.size() != 0)
            check(0, rooms_origin);
        System.out.println(min);
    }

    private static void check(int cctvNum, int[][] rooms) {

        int[][] roomsTemp = new int[N][M];
        copy(rooms, roomsTemp);
        int x = cctvs.get(cctvNum)[0];
        int y = cctvs.get(cctvNum)[1];
        int cctvType = rooms_origin[x][y];
        for (int i = 0; i < 4; i++) { // ������ �� ������ case�� ���ؼ� Ž��
            fillRooms(roomsTemp, cctvType, i, x, y);
            if (cctvNum == cctvs.size() -1) { // �ִ� depth, �� ��ġ�� cctv �� ������ ���� ���
                countArea(roomsTemp);
            }
            for (int j = cctvNum+1; j < cctvs.size(); j++) {
                check(j, roomsTemp);
            }
            copy(rooms, roomsTemp); // ���� (backtracking)
        }
    }

    private static void countArea(int[][] room) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (room[i][j] == 0)
                    cnt++;
            }
        }
        min = Math.min(min, cnt);
    }

    private static void right(int[][] room, int x, int y) {
        for (int C = y + 1; C < M; C++)
            if (room[x][C] == 6) return;
            else if (room[x][C] != 0) continue;
            else room[x][C] = -1;
    }

    private static void down(int[][] room, int x, int y) {
        for (int R = x + 1; R < N; R++)
            if (room[R][y] == 6) return;
            else if (room[R][y] != 0) continue;
            else room[R][y] = -1;
    }

    private static void left(int[][] room, int x, int y) {
        for (int C = y - 1; C >= 0; C--)
            if (room[x][C] == 6) return;
            else if (room[x][C] != 0) continue;
            else room[x][C] = -1;
    }

    private static void up(int[][] room, int x, int y) {
        for (int R = x - 1; R >= 0; R--)
            if (room[R][y] == 6) return;
            else if (room[R][y] != 0) continue;
            else room[R][y] = -1;
    }

    private static void fillRooms(int[][] room, int cctvType, int direction, int x, int y) {
        switch (cctvType) {
            case 1: {
                if (direction == 0) {
                    right(room, x, y);
                } else if (direction == 1) {
                    down(room, x, y);
                } else if (direction == 2) {
                    left(room, x, y);
                } else {
                    up(room, x, y);
                }
                break;
            }
            case 2: {
                if (direction == 0 || direction == 2) {
                    right(room, x, y);
                    left(room, x, y);
                } else {
                    up(room, x, y);
                    down(room, x, y);
                }
                break;
            }
            case 3: {
                if (direction == 0) {
                    right(room, x, y);
                    down(room, x, y);
                } else if (direction == 1) {
                    down(room, x, y);
                    left(room, x, y);
                } else if (direction == 2) {
                    left(room, x, y);
                    up(room, x, y);
                } else {
                    up(room, x, y);
                    right(room, x, y);
                }
                break;
            }
            case 4: {
                if (direction == 0) {
                    right(room, x, y);
                    up(room, x, y);
                    down(room, x, y);
                } else if (direction == 1) {
                    right(room, x, y);
                    left(room, x, y);
                    down(room, x, y);
                } else if (direction == 2) {
                    left(room, x, y);
                    up(room, x, y);
                    down(room, x, y);
                } else {
                    right(room, x, y);
                    left(room, x, y);
                    up(room, x, y);
                }
                break;
            }
            case 5: {
                right(room, x, y);
                left(room, x, y);
                up(room, x, y);
                down(room, x, y);
                break;
            }
            default: break;
        }
    }


    private static void copy(int[][] origin, int[][] copy) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                copy[i][j] = origin[i][j];
        }
    }

    private static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cctvs = new ArrayList<>();
        rooms_origin = new int[N][M];
        min = N*M;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int locationType = Integer.parseInt(st.nextToken());
                rooms_origin[i][j] = locationType;
                boolean isCCTVArea = locationType != 0 && locationType != 6;
                if (isCCTVArea) {
                    cctvs.add(new int[] {i, j}); // cctv�� ��ǥ������ cctvs�� ����
                }
            }
        }

        countArea(rooms_origin); // cctv�� �ϳ��� ���� ��쿡�� counting�� ���־�� �ϴµ�
                                 // �׳� N*M�� min���� �����ϴ� �Ǽ��� ���ߴ�. �� ������ �߰��Ͽ� �ذ��Ͽ���.
    }
}