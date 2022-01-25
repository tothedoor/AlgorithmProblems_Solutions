class Solution {
    int[][] computers;
    boolean[] isVisited;
    int answer;
    int n;
    public int solution(int n, int[][] computers) {
        answer = 0;
        isVisited = new boolean[n];
        this.computers = computers;
        this.n = n;
        
        for (int i = 0; i < n; i++) {
            dfs(i,0);
        }
            
        return answer;
    }
    
    public void dfs(int x, int depth) {
        if (isVisited[x]) // 방문한 경우이거나 자기 자신에 대한 확인일 경우 return;
            return;
        
        isVisited[x] = true; // 방문 처리함
        for (int i = 0; i < this.n; i++) {
            if (i == x)
                continue;
            if (this.computers[x][i] == 1)
                dfs(i, depth+1);
        }
        
        if (depth == 0)
            answer++;
    }
}