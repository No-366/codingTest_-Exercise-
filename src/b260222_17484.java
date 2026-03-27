import java.io.IOException;
import java.util.Scanner;

public class b260222_17484 {
	static int N;
	static int M;
	static int [][] map;
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		int answer;
		map = new int[N][M];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < M; j++){
				map[i][j] = sc.nextInt();
			}
		}

		for(int i = 0; i<M ; i++){
			for(int d = 0; d<3 ; d++){
				dfs(0,i,map[0][i],d);
			}
		}

		answer = result;

		System.out.println(answer);

	}

	static int [] dy = {-1,0,1};
	static int result = Integer.MAX_VALUE;

	public static void dfs(int x, int y, int sum, int dir){
		if(x==N-1){
			result = Math.min(result, sum);
			return;
		}

		for(int d = 0 ; d < 3 ; d++){
			if(d == dir) continue;
			int newX = x+1;
			int newY = y+dy[d];
			if(newY<0 || newY>M-1) continue;
			int newSum = sum + map[newX][newY];
			dfs(newX,newY,newSum,d);
		}
	}
}
