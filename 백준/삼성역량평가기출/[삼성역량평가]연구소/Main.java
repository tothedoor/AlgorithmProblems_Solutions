/*
[문제 출처] https://www.acmicpc.net/problem/14502
[문제 유형] 브루트포스 + DFS
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static int[][] map;
    static int[][] map_origin;
    static int safeCnt = 0;
    static int virusCnt = 0;
    static ArrayList<int[]> safeZones;
    static ArrayList<int[]> virusZones;
    static boolean[][] isVisted;
    static int[] x_dir = {1, -1, 0, 0};
    static int[] y_dir = {0, 0, -1, 1};
    static int newVirusCnt = 0;
    static int max = -1;
    public static void main(String[] args) throws Exception{
        getInput(); // 입력 받기


        int[] z1, z2, z3; // 벽을 세울 공간 (safe zone들 중에서)
        for (int i = 0; i < safeCnt; i++) {
            z1 = safeZones.get(i);
            for (int j = i+1; j < safeCnt; j++) {
                z2 = safeZones.get(j);
                for (int k = j+1; k < safeCnt; k++) {
                    // 사용 변수 초기화
                    isVisted = new boolean[N][M];
                    newVirusCnt = 0;
                    copyMap(map, map_origin);

                    z3 = safeZones.get(k); // 세 개의 zone 선택 완료

                    setMap(z1, z2, z3, 1); // 선택한 세 존에 벽을 세움
                    for (int v = 0; v < virusCnt; v++) {
                        int[] viruszone = virusZones.get(v);
                        spreadVirus(viruszone[0], viruszone[1]);
                    }
                    int newSafeCnt = safeCnt - (newVirusCnt - virusCnt) - 3;
                    max = Math.max(max, newSafeCnt);

                    /*
                    System.out.printf("(%d,%d), (%d,%d), (%d,%d) / ", z1[0], z1[1], z2[0], z2[1], z3[0], z3[1]);
                    System.out.printf("max: %d\n", max);
                    */

                    /*
                    for (int r = 0; r < N; r++) {
                        for (int c = 0; c < M; c++) {
                            char C = map[r][c] == 2? '*' : map[r][c] == 1? '#': ' ';
                            System.out.printf("%c", C);
                        }

                        System.out.println();
                    }
                    System.out.println();

                     */

                }
            }
        }

        System.out.println(max);
    }

    private static void copyMap(int[][] target, int[][] origin) {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                target[i][j] = origin[i][j];
    }

    private static void spreadVirus(int x, int y) { // dfs 방식으로 바이러스 퍼트림
        if (!canSpreadZone(x, y)) // 퍼트릴 수 없는 공간인 경우 return
            return;
        newVirusCnt++;
        isVisted[x][y] = true;
        map[x][y] = 2;

        int nx, ny;
        for (int i = 0; i < 4; i++) {
            nx = x + x_dir[i];
            ny = y + y_dir[i];
            if (!canSpreadZone(nx, ny))
                continue;
            spreadVirus(nx, ny);
        }
    }

    private static boolean canSpreadZone(int nx, int ny) {
        if (nx >= N || nx < 0 || ny >= M || ny < 0 || isVisted[nx][ny])
            return false;
        if (map[nx][ny] == 1)
            return false;
        return true;
    }

    private static void setMap(int[] z1, int[] z2, int[] z3, int kind) {
        map[z1[0]][z1[1]] = kind;
        map[z2[0]][z2[1]] = kind;
        map[z3[0]][z3[1]] = kind;
    }

    private static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        map_origin= new int[N][M];
        isVisted = new boolean[N][M];
        safeZones = new ArrayList<>();
        virusZones = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int zoneValue = Integer.parseInt(st.nextToken());
                map_origin[i][j] = zoneValue;
                if (zoneValue == 0) {
                    safeCnt++;
                    safeZones.add(new int[] {i, j});
                } else if (zoneValue == 2) {
                    virusCnt++;
                    virusZones.add(new int[] {i, j});
                }
            }
        }
    }
}