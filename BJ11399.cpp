#include <iostream>
#include <algorithm>

#define MAX 1000
using namespace std;

int N; // 은행 앞에서 기다리는 사람의 수
int arr[MAX]; // 기다리는 사람들이 인출에 필요한 시간에 대한 정보
int solution = 0; // 정답

int main() {
    int temp = 0; // 정답 유도를 위해 사용되는 임시 저장 변수
    cin >> N;

    for (int i = 0; i < N; i++) // arr에 정보 입력 받음
        cin >> arr[i];

    sort(arr, arr + N); // std::sort() 를 사용하여 sort (sort는 내부적으로 quick sort)

    for (int i = 0; i < N; i++) { // 사람들이 기다리는 총시간을 구함
        temp += arr[i];
        solution += temp;
    }

    cout << solution; // 정답 출력
    return 0;
}