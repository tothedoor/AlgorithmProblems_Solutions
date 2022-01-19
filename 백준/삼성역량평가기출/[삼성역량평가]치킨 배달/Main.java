import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static List<int[]> chicken;
	static List<int[]> house;
	static List<int[]> picked;
	static int answer = 1000000;
	public static void main(String[] args) throws Exception{
		getInput();
		
		pickChicken(-1, 0, M);
		
		System.out.println(answer);
		
	}
	
	private static void pickChicken(int chickenIdx, int pickCnt, int maxCnt) {
		if (pickCnt == maxCnt) {
			int sum = 0;
			// 가정집들과 picked 들 간의 거리를 구하여 sum을 구한다.
			for (int i = 0; i < house.size(); i++) {
				int houseX = house.get(i)[0];
				int houseY = house.get(i)[1];
				int min = 10000;
				for (int j = 0; j < picked.size(); j++) {
					int pickedX = picked.get(j)[0];
					int pickedY = picked.get(j)[1];
					int distance = Math.abs(houseX - pickedX) + Math.abs(houseY - pickedY);
					min = Math.min(min, distance);
				}
				sum += min;
			}
			answer = Math.min(answer, sum);
			return;
		}
		
		for (int i = chickenIdx+1; i < chicken.size(); i++) {
			int chickenX = chicken.get(i)[0];
			int chickenY = chicken.get(i)[1]; 
			picked.add(new int[] {chickenX, chickenY});
			pickChicken(i, pickCnt+1, maxCnt);
			picked.remove(pickCnt);
		}
	}
	private static void getInput() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		chicken = new ArrayList<>();
		house = new ArrayList<>();
		picked = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int type = Integer.parseInt(st.nextToken());
				if (type == 1)
					house.add(new int[] {i, j});
				else if (type == 2)
					chicken.add(new int[] {i, j});
			}
		}
			
	}

}
