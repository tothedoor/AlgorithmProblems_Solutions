import java.util.Arrays;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int[] stayNum = new int[N];

        for (int i = 0; i < stages.length; i++) {
            if (stages[i] < N + 1)
                stayNum[stages[i] - 1]++;
        }

        int sum = 0;
        Pair[] failRates = new Pair[N];
        for (int i = 0; i < N; i++) {
            sum += stayNum[i];
            // 실패율 계산
            double failRate = (double) stayNum[i] / (double) (stages.length - sum + stayNum[i]);
            failRates[i] = new Pair(i + 1, failRate);
        }

        Arrays.sort(failRates, (p1, p2) -> { // Lamda 형태로 비교함수 인자 전달
            if (p2.getValue() > p1.getValue())
                return 1;
            else if (p2.getValue() < p1.getValue())
                return -1;
            else {
                if (p1.getKey() > p2.getKey())
                    return 1;
                else
                    return -1;
            }
        });

        for (int i = 0; i < failRates.length; i++) {
            answer[i] = failRates[i].getKey();
        }

        return answer;
    }

    // Java에는 pair가 없으므로 직접 만들어서 구현
    class Pair {
        private int key;
        private double value;

        public Pair(int key, double value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public double getValue() {
            return value;
        }
    }
}