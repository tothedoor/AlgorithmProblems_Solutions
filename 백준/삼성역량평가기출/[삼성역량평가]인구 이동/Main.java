
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Time;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int N, L, R;
	static int[][] map;
	static boolean[][] isVisited;
	static Queue<int[]> que;
	static List<int[]> connecteds;
	static int sum;
	static boolean isMoved;
	
	static int[] x_dir = {-1, 0, 1, 0};
	static int[] y_dir = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		getInput();
		int days = 0;
		
		while (true) {
			if (isAllSame()) // �����̵��� �� �̻� �ʿ���� ��� 
				break;
			
			isVisited = new boolean[N][N];
			isMoved = false;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (isVisited[i][j]) continue; // �̹� Ȯ�ε� �����̸� �н�
					sum = 0;
					que = new LinkedList<>();
					connecteds = new ArrayList<>();
					que.add(new int[] {i, j}); // ���� ����
					isVisited[i][j] = true;
					move();
				}
			}
			
			if (!isMoved)
				break;
			days++;
		}
		
		System.out.println(days);
	}
	
	private static void move() throws InterruptedException {	
		if (que.isEmpty()) { // ��� ����� �������� Ž���� ���
			for (int i = 0; i < connecteds.size(); i++) {
				int x = connecteds.get(i)[0];
				int y = connecteds.get(i)[1];
				sum += map[x][y];
			}
			
			int avg = sum / connecteds.size();
			for (int i = 0; i < connecteds.size(); i++) {
				int x = connecteds.get(i)[0];
				int y = connecteds.get(i)[1];
				map[x][y] = avg;
			}
			
			
			return;
		}
		
		int[] currCountry = que.poll();
		int currX = currCountry[0];
		int currY = currCountry[1];
		connecteds.add(new int[] {currX, currY}); // ����� �������� �����ϴ� �����̳ʿ� ����
		
		// ���� ��ġ�� �� ������ ������ ���ؼ�(������ ���ϴ�)
		for (int i = 0; i < 4; i++) {
			int nextX = currX + x_dir[i];
			int nextY = currY + y_dir[i];
			
			if (nextX >= N || nextX < 0 || nextY >= N || nextY < 0) continue;
			if (isVisited[nextX][nextY]) continue; // �̹� Ȯ���� ������ ��� �н�
			int diff = Math.abs(map[nextX][nextY] - map[currX][currY]);
			if (diff > R || diff < L) continue; // ���� ���Ǹ���x�� ���
			
			isMoved = true; // ����� �������� �ϳ��� ������ true�� �ٲ�
			isVisited[nextX][nextY] = true; // �湮ó��
			que.add(new int[] {nextX, nextY});
		}
		
		move();
		
	}
	
	private static boolean isAllSame() {
		int firstNum = map[0][0];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != firstNum)
					return false;
			}
		}
		
		return true;
	}
	
	private static void getInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
}