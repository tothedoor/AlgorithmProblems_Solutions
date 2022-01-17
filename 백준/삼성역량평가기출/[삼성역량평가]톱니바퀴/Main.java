import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {
    static int K;
    static int[][] rotationInfos;
    static ArrayList<ArrayList<Integer>> gearInfos;
    static HashMap<String,Integer> touchedInfo;
    static boolean[] isChecked;
    public static void main(String[] args) throws IOException {
        getInput();


        for (int i = 0; i < K; i++) {
            isChecked = new boolean[4];
            int gearNum = rotationInfos[i][0] - 1;
            int rotationDirection = rotationInfos[i][1];
            setTouchedInfo();
            rotateGear(gearNum, rotationDirection);

            // 디버깅
            System.out.println(gearInfos.get(0).toString());
            System.out.println(gearInfos.get(1).toString());
            System.out.println(gearInfos.get(2).toString());
            System.out.println(gearInfos.get(3).toString());
            System.out.println(touchedInfo.toString());
            System.out.println();
        }

        int answer = getSum();

        System.out.println(answer);
    }

    private static int getSum() {
        int sum = 0;
        int gear0Top = gearInfos.get(0).get(0);
        int gear1Top = gearInfos.get(1).get(0);
        int gear2Top = gearInfos.get(2).get(0);
        int gear3Top = gearInfos.get(3).get(0);

        if (gear0Top == 1)
            sum += 1;
        if (gear1Top == 1)
            sum += 2;
        if (gear2Top == 1)
            sum += 4;
        if (gear3Top == 1)
            sum += 8;

        return sum;
    }

    private static void setTouchedInfo() {
        for (int i = 0; i < 4; i++) { // 4개의 톱니에 대해서
            String leftName = "gear"+i+"Left";
            String rightName = "gear"+i+"Right";
            touchedInfo.put(leftName, gearInfos.get(i).get(6));
            touchedInfo.put(rightName, gearInfos.get(i).get(2));
        }
    }


    private static void rotateGear(int gearNum, int rotateDirection) {
        if (isChecked[gearNum])
            return;

        isChecked[gearNum] = true;
        int newRotateDirection = rotateDirection==1? 0:1;

        // gearNum에 해당하는 기어 회전시킴
        if (rotateDirection == 1) { // 시계방향 회전
            int last = gearInfos.get(gearNum).get(7);
            gearInfos.get(gearNum).remove(7);
            gearInfos.get(gearNum).add(0, last);
        } else { // 반시계방향 회전
            int first = gearInfos.get(gearNum).get(0);
            gearInfos.get(gearNum).remove(0);
            gearInfos.get(gearNum).add(first);
        }

        // 맞물려있는 기어들에 대해 확인 후 회전
        if (gearNum - 1 > 0) { // 왼쪽과의 확인
            if (!isSame(gearNum - 1, gearNum))
                rotateGear(gearNum - 1, newRotateDirection);
        }
        if (gearNum + 1 < 4) { // 오른쪽과의 확인
            if (!isSame(gearNum, gearNum + 1))
                rotateGear(gearNum + 1, newRotateDirection);
        }
    }

    private static boolean isSame(int gear1, int gear2) {
        int gear1Right = touchedInfo.get("gear"+gear1+"Right");
        int gear2Left = touchedInfo.get("gear"+gear2+"Left");

        if (gear1Right == gear2Left)
            return true;
        else
            return false;
    }



    private static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        gearInfos = new ArrayList<>();
        touchedInfo = new HashMap<>();

        for (int i = 0; i < 4; i++) {
            String info = br.readLine();
            gearInfos.add(new ArrayList<>());
            for (int j = 0; j < 8; j++) {
                gearInfos.get(i).add(Integer.parseInt(info.charAt(j)+""));
            }
        }

        K = Integer.parseInt(br.readLine());
        rotationInfos = new int[K][2];
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            rotationInfos[i][0] = Integer.parseInt(st.nextToken());
            rotationInfos[i][1] = Integer.parseInt(st.nextToken());
        }
    }


}
