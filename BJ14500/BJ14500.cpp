#include <iostream>
#include <algorithm>
#include <cstring>

#define MAX_DEPTH 3
using namespace std;

int paper[501][501];
int x_way[4] = {-1, 1, 0, 0};
int y_way[4] = {0, 0, -1, 1};
int isVisited[501][501];
int N, M;
int answer = -11000;

// dfs가 가능한 모양의 테트로미노들에 대해서는 다음 function을 사용
void calculate_DFS(int x, int y, int temp_sum, int depth)
{
    if (depth == MAX_DEPTH)
    {
        answer = answer < temp_sum ? temp_sum : answer;
        return;
    }

    // 확장 가능한 영역들에 대해 확인!
    for (int i = 0; i < 4; i++)
    {
        int next_x = x + x_way[i];
        int next_y = y + y_way[i];

        bool validX = next_x >= 0 && next_x < N;
        bool validY = next_y >= 0 && next_y < M;
        bool isValidArea = validX && validY;

        if (!isValidArea)
            continue;
        if (isVisited[next_x][next_y])
            continue;

        isVisited[next_x][next_y] = true; // 방문할 것이므로 true로 설정
        calculate_DFS(next_x, next_y, temp_sum + paper[next_x][next_y], depth + 1);
        isVisited[next_x][next_y] = false; // 초기화 해준다.
    }
}

// 'ㅏ' 모양 테트로미노의 경우 dfs가 불가능하므로 다음 function으로 4가지 경우에 대해 확인
void calculate_nonDFS(int x, int y)
{
    int sum = 0;
    if (x > 0 && y > 0 && y + 1 < M)
    { // case 1
        sum = paper[x][y] + paper[x - 1][y - 1] + paper[x - 1][y] + paper[x - 1][y + 1];
        if (sum > answer)
            answer = sum;
    }
    if (x + 1 < N && y > 0 && y + 1 < M)
    { // case 2
        sum = paper[x][y] + paper[x + 1][y - 1] + paper[x + 1][y] + paper[x + 1][y + 1];
        if (sum > answer)
            answer = sum;
    }
    if (y > 0 && x > 0 && x + 1 < N)
    { // case 3
        sum = paper[x][y] + paper[x - 1][y - 1] + paper[x][y - 1] + paper[x + 1][y - 1];
        if (sum > answer)
            answer = sum;
    }
    if (y + 1 < M && x > 0 && x + 1 < N)
    { // case 4
        sum = paper[x][y] + paper[x - 1][y + 1] + paper[x][y + 1] + paper[x + 1][y + 1];
        if (sum > answer)
            answer = sum;
    }
}

int main()
{
    // 입력 받기
    cin >> N >> M;
    for (int i = 0; i < N; i++)
        for (int j = 0; j < M; j++)
            cin >> paper[i][j];

    // isVisited 배열 false로 초기화
    memset(isVisited, false, sizeof(isVisited));

    // 전체 elem을 시작점으로 탐색!
    for (int i = 0; i < N; i++)
        for (int j = 0; j < M; j++)
        {
            isVisited[i][j] = true;
            calculate_DFS(i, j, paper[i][j], 0); // 'ㅏ' 모양을 제외한 다른 테트로미노들의 경우 check
            isVisited[i][j] = false;
            calculate_nonDFS(i, j); // 'ㅏ'의 경우 4가지 모양에 대해서 check!
        }

    cout << answer << endl;
    return 0;
}