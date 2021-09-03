#include <iostream>

using namespace std;

string W[8] = {
    "WBWBWBWB",
    "BWBWBWBW",
    "WBWBWBWB",
    "BWBWBWBW",
    "WBWBWBWB",
    "BWBWBWBW",
    "WBWBWBWB",
    "BWBWBWBW"};
string B[8] = {
    "BWBWBWBW",
    "WBWBWBWB",
    "BWBWBWBW",
    "WBWBWBWB",
    "BWBWBWBW",
    "WBWBWBWB",
    "BWBWBWBW",
    "WBWBWBWB"};

// 왼쪽 끝 시작이 W가 되게 할 때 칠하는 횟수 세는 함수
int W_count(string *board, int x, int y)
{
    int cnt = 0;
    for (int i = 0; i < 8; i++)
        for (int j = 0; j < 8; j++)
            if (board[i + x][j + y] != W[i][j])
                cnt++;
    return cnt;
}
// 왼쪽 끝 시작이 B가 되게 할 때 칠하는 횟수 세는 함수
int B_count(string *board, int x, int y)
{
    int cnt = 0;
    for (int i = 0; i < 8; i++)
        for (int j = 0; j < 8; j++)
            if (board[i + x][j + y] != B[i][j])
                cnt++;
    return cnt;
}
// x, y 중 최소값을 return 하는 함수
int min(int x, int y)
{
    if (x < y)
        return x;
    else
        return y;
}

int main()
{
    // 보드의 크기를 입력 받는다.
    int n, m;
    int min_val = 1000;
    cin >> n >> m;
    // 보드 정보 저장할 2차원 배열 생성
    string board[n];
    // 보드 정보 입력 받음
    for (int i = 0; i < n; i++)
        cin >> board[i];

    // 체크 보드로 선택 될 수 있는 모든 경우의 수를 탐색한다
    for (int i = 0; i < n - 7; i++)
        for (int j = 0; j < m - 7; j++)
        {
            int cnt = min(W_count(board, i, j), B_count(board, i, j));
            min_val = min(min_val, cnt);
        }

    cout << min_val;
}