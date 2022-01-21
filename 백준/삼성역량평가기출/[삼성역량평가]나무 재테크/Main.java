import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static int boardSize, treeNum, maxYear;
    static int[][] s2d2Values;
    static int[][] nowEnergy;
    static List<Integer>[][] trees;
    static int[] r_dir = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] c_dir = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        getInput();

        for (int year = 1; year <= maxYear; year++) { // 한 해 씩 진행
            // 봄 및 여름
            for (int r = 1; r < boardSize+1; r++) {
                for (int c = 1; c < boardSize+1; c++) {
                    if (trees[r][c].isEmpty()) continue; // 해당 칸에 심어진 나무 없으면 패스
                    Collections.sort(trees[r][c]); // 나이 오름순으로 나무들 정렬
                    int nowE = nowEnergy[r][c]; // 현재 땅의 에너지
                    int additionalE = 0;
                    for (int i = 0; i < trees[r][c].size(); i++) { // 어린 나무들부터 탐색
                        if (trees[r][c].get(i) > nowE) { // 양분 먹을 수 없는 경우
                            additionalE += trees[r][c].get(i) / 2;

                            System.out.println("");
                            System.out.printf("year:%d\n", year);
                            System.out.println(trees[r][c].toString());
                            trees[r][c].remove(i);
                            System.out.println(trees[r][c].toString());

                            i--; // 다음 나무 탐색을 위하여
                        } else { // 양분 먹을 수 있는 경우
                            nowE -= trees[r][c].get(i); // 섭취한만큼 감소
                            trees[r][c].set(i, trees[r][c].get(i)+1); // 한 살 증가
                        }
                    }
                    int newE = nowE + additionalE;
                    nowEnergy[r][c] = newE; // 새로운 양분양으로 값을 변경
                }
            }

            // 가을
            for (int r = 1; r < boardSize+1; r++) {
                for (int c = 1; c < boardSize+1; c++) {
                    if (trees[r][c].isEmpty()) continue;
                    Collections.sort(trees[r][c]); // 나이 오름순으로 나무들 정렬
                    for (int i = 0; i < trees[r][c].size(); i++) {
                        if (trees[r][c].get(i) % 5 == 0) { // 나이가 5의 배수인 경우
                            for (int dir = 0; dir < 8; dir++) { // 8개 인접 토지에 나무 새로 심음
                                int newR = r + r_dir[dir];
                                int newC = c + c_dir[dir];
                                if (newR < 1 || newR > boardSize || newC < 1 || newC > boardSize)
                                    continue;
                                trees[newR][newC].add(1);
                            }
                        }
                    }
                }
            }

            // 겨울
            for (int r = 1; r < boardSize+1; r++) {
                for (int c = 1; c < boardSize+1; c++) {
                    nowEnergy[r][c] += s2d2Values[r][c];
                }
            }

        }

        int cnt = 0;
        // K년이 지나고 심어진 나무 개수 파악
        for (int r = 1; r < boardSize+1; r++) {
            for (int c = 1; c < boardSize+1; c++) {
                cnt += trees[r][c].size();
            }
        }

        System.out.println(cnt);
    }

    private static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        boardSize = Integer.parseInt(st.nextToken());
        treeNum = Integer.parseInt(st.nextToken());
        maxYear = Integer.parseInt(st.nextToken());

        s2d2Values = new int[boardSize+1][boardSize+1];
        nowEnergy = new int[boardSize+1][boardSize+1];
        for (int i = 1; i < boardSize+1; i++) { // s2d2가 제공하는 양분 정보 및 현재 토양 정보 초기화
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < boardSize+1; j++) {
                s2d2Values[i][j] = Integer.parseInt(st.nextToken());
                nowEnergy[i][j] = 5;
            }
        }

        // 나무들의 정보 저장할 2차원 배열 생성
        trees = new ArrayList[boardSize+1][boardSize+1];
        for (int i = 0; i < boardSize+1; i++) {
            for (int j = 0; j < boardSize+1; j++)
                trees[i][j] = new ArrayList<>();
        }

        for (int i = 1; i < treeNum + 1; i++) { // 처음에 심어지는 나무의 정보 저장
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            trees[x][y].add(age);
        }
    }

}