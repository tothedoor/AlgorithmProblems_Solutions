class Solution {
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        int[][] picture_copy = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                picture_copy[i][j] = picture[i][j];
        }

        DFS dfs = new DFS(picture_copy, m, n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int color = picture_copy[i][j];
                if (color == 0)
                    continue;
                else {
                    dfs.dfs(i, j, color);
                    if (dfs.area > maxSizeOfOneArea)
                        maxSizeOfOneArea = dfs.area;
                    dfs.area = 0;
                    numberOfArea++;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    public class DFS {
        public int[][] picture;
        public int m, n;
        public int area;

        public DFS(int[][] picture, int m, int n) {
            this.picture = picture;
            this.m = m;
            this.n = n;
            this.area = 0;
        }

        public void dfs(int x, int y, int color) {
            if (picture[x][y] != color)
                return;

            area++;
            picture[x][y] = 0;

            if (x > 0) { // up
                dfs(x - 1, y, color);
            }
            if (x < m - 1) { // down
                dfs(x + 1, y, color);
            }
            if (y > 0) { // left
                dfs(x, y - 1, color);
            }
            if (y < n - 1) { // right
                dfs(x, y + 1, color);
            }
        }
    }

}