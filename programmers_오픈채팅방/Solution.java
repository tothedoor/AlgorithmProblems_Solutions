import java.util.*;

class Solution {
    public ArrayList<String> solution(String[] record) {
        HashMap<String,String> map = new HashMap<>();
        int recordLen = record.length;
        String[][] userInfos = new String[recordLen][3];
        ArrayList<String> answer = new ArrayList<>();
        
        for (int i = 0; i < recordLen; i++) {
            userInfos[i] = record[i].split(" ");
            if (userInfos[i][0].equals("Enter")) {
                if (map.containsKey(userInfos[i][1])) {
                    map.replace(userInfos[i][1], userInfos[i][2]);
                }
                else {
                    map.put(userInfos[i][1], userInfos[i][2]);
                }
            } else if (userInfos[i][0].equals("Change")) {
                map.replace(userInfos[i][1], userInfos[i][2]);
            }   
        }
        
        for (int i = 0; i < recordLen; i++) {
            String op = userInfos[i][0];
            String uid = userInfos[i][1];
            if (op.equals("Enter")) {
                answer.add(map.get(uid) + "님이 들어왔습니다.");
            } else if (op.equals("Leave")) {
                answer.add(map.get(uid) + "님이 나갔습니다.");
            }
        }
        return answer;
    }
}