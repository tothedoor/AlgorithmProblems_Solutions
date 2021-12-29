# **[백준] 14500번 테트로미노**

문제 : 문제는 [이 링크](https://www.acmicpc.net/problem/14500)를 참조!

## **풀이 아이디어**

---

처음에는 각 도형들이 회전, 대칭으로 가질 수 있는 모든 형태에 대해서 N\*M개의  
모든 정점을 기준으로 모든 case들을 파악하는 방법을 떠올렸다. 하지만 이와 같은  
은 방법은 모든 모양에 대한 조건을 직접 작성한다는 점이 문제출제 의도에서  
벗어난다고 생각했다.

그래서 다른 방법을 생각해보고자 테트로미노의 각 종류들의 공통점을 찾아내려고 하였다.  
찾아낸 공통점을 바탕으로 가질 수 있는 형태들을 탐색하는 부분을 공통된 코드로 사용할  
수 있을 것이라고 생각했기 때문이었다. 찾아낸 공통점은 각 모양들은 4개의 작은 영역들로  
구성되어있다는 것이다. 이는 테트로미노의 조건중 하나였다. 조금 더 생각해보니 다음과  
같은 특징을 찾아냈다.

<br/>

![common feature](./img/commonFeature.jpg)

위의 이미지를 보면 'ㅏ' 모양을 제외한 테트로미노들은 변을 마주보고 이웃한 구역으로  
총 4칸을 확장하여 모양을 이룸을 확인했다. 때문에 한 정점에서 확장 가능한 영역(주어진 종이의 영역 내)  
으로 랜덤하게 확장하여 모든 경우의 수를 확인한다면 해당 정점에서 4개의 테트로미노에 대해  
모든 경우의 수를 확인 할 수 있을 것이라고 생각했다. 그래서 *재귀함수를 활용한 DFS*를 통해  
알고리즘을 구현하기로 했다.

<br/>

## **코드 및 설명**

---

사용한 전역 변수들이다.

```cpp
int paper[501][501];
int x_way[4] = {-1, 1, 0, 0};
int y_way[4] = {0, 0, -1, 1};
int isVisited[501][501];
int N, M;
int answer = -11000;
```

한 정점에서 DFS가 일어날 때 종이 위에서 영역의 확장은 위, 아래 왼쪽 오른쪽으로만 이루어진다.  
이를 위해 x_way, y_way 배열을 통해 이동 방향을 지정하였다.

<br/>

다음은 DFS 방식으로 searching 하는 함수이다.

```cpp
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
```

`if (depth == MAX_DEPTH)`를 통해 4 번째 영역인 경우 return 하도록 했다.  
for문 안을 보자. next_x와 y는 다음으로 확장할 영역의 x,y 좌표이다.
isValidArea는 확장할 영역이 종이 위의 valid한 영역인지를 알수 있는 변수이다.
`isvalidArea`와 `isVisted[next_x][next_y]`를 조건문으로 확인하여 valid하고  
방문하지 않은 영역인지 확인한다. 조건을 만족하는 경우 for문 내의 나머지  
코드들이 실행되어 재귀적으로 `calculate_DFS`를 call 하게 된다.

<br/>

다음은 'ㅏ' 모양의 4가지 경우에 대해 확인하는 함수이다.

```cpp
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
```

'ㅏ'는  
ㅏ, ㅓ, ㅗ, ㅜ 이렇게 4가지 case를 가진다. 총 4개의 if문들은 각 case들이 해당 영역에서
위치할 수 있는지를 확인한다.

위의 두 함수가 `main` 안에서 N\*M의 모든 영역에 대해 호출되어 테트로미노를 위치시켜 얻을 수  
있는 최대합을 구하게 된다.

<br/>

## **마무리**

---

N\*M개의 종이 위의 모든 영역에 대해 두 함수가 call 되므로 브루투 포스 방식이 사용되었다.  
또한 각 영역에 대해 4개의 테트로미노의 가능한 모든 경우를 확인하기 위해 DFS가 사용되었다.  
두 가지 방법론이 동시에 사용된 것이다. N과 M의 최대가 500이라는 조건 때문에 현재 내가  
구현한 알고리즘으로는 제한시간(2초) 내에 실행이 완료된다.

처음에 떠올린 방법으로도 제한시간 내에 실행을 할 수 있었겠지만 코드의 길이 및 가독성은 매우
떨어졌을 것이다. 각 테트로미노들이 가질 수 있는 모든 형태의 경우의 수를 계산해보니 19개(더 있을 수도 있음)가 나왔기 때문이다.

**비슷하거나 동일한 성능(실행 시간 측면)을 가지는 알고리즘이더라도 사용한 방법론에 따라서  
코드의 길이, 가독성은 차이가 있을 수 있음을 다시 한 번 확인할 수 있었다.**
