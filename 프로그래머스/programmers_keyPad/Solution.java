package programmers_keyPad;

class Solution {
    public String solution(int[] numbers, String hand) {
        String answer = "";
        int left = 10;
        int right = 11;
        int[][] btnLocation = { { 4, 2 }, { 1, 1 }, { 1, 2 }, { 1, 3 }, { 2, 1 }, { 2, 2 }, { 2, 3 }, { 3, 1 },
                { 3, 2 }, { 3, 3 }, { 4, 1 }, { 4, 3 } }; // 10, 11 번째 elem은 각각 '*','#'의 위치

        for (int i = 0; i < numbers.length; i++) {
            int buttonNum = numbers[i];
            boolean isLeftBtn = (buttonNum == 1 || buttonNum == 4 || buttonNum == 7);
            boolean isRightBtn = (buttonNum == 3 || buttonNum == 6 || buttonNum == 9);
            if (isLeftBtn) {
                left = buttonNum;
                answer += "L";
            } else if (isRightBtn) {
                right = buttonNum;
                answer += "R";
            } else {
                int leftDistance = Math.abs(btnLocation[buttonNum][0] - btnLocation[left][0]) +
                        Math.abs(btnLocation[buttonNum][1] - btnLocation[left][1]);
                int rightDistance = Math.abs(btnLocation[buttonNum][0] - btnLocation[right][0]) +
                        Math.abs(btnLocation[buttonNum][1] - btnLocation[right][1]);
                if (leftDistance > rightDistance) {
                    right = buttonNum;
                    answer += "R";
                } else if (leftDistance < rightDistance) {
                    left = buttonNum;
                    answer += "L";
                } else {
                    if (leftDistance == rightDistance) {
                        if (hand.equals("right")) { // hand == "right"로 초기 작성. 때문에 정상 동작하지 않음
                            right = buttonNum;
                            answer += "R";
                        } else {
                            left = buttonNum;
                            answer += "L";
                        }

                    }
                }
            }
        }
        return answer;
    }
}
