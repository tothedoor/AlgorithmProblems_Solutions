#include <iostream>
#include <math.h>

using namespace std;

int main()
{
    // 몇 번째 입력인지 N에 입력 받아 할당한다.
    int N, result;
    cin >> N;

    int num = 665, cnt = 0;
    string s; // num의 string형을 할당할 변수

    while (num++)
    {
        s = to_string(num);
        // s에서 "666"이라는 substr를 찾으면 cnt를 1 증가시킨다.
        if (s.find("666") != string::npos)
            cnt++;
        // while loop를 돌면서 N번째 영화제목을 찾게 되면 break
        if (cnt == N)
            break;
    }
    cout << s; // 결과 출력
}