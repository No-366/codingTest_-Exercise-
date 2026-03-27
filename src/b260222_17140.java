import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

// public class b260222_17140 {
// 	public static void main(String[] args) {
// 		Scanner sc = new Scanner(System.in);
// 		int r = sc.nextInt();
// 		int c = sc.nextInt();
// 		int k = sc.nextInt();
// 		int [][] arr = new int [100][100];
// 		int answer = 0;
//
// 		for(int i = 0 ; i < 3 ; i++){
// 			for(int j = 0 ; j < 3 ; j++){
// 				arr[i][j] = sc.nextInt();
// 			}
// 		}
//
// 		int cnt = 0;
// 		int r_len = 3;
// 		int c_len = 3;
// 		while(arr[r][c] != k){
//
// 			if(cnt>100){
// 				answer = -1;
// 				break;
// 			}
//
//
//
//
// 			cnt++;
// 		}
//
// 		System.out.println(answer);
// 	}
// }

public class b260222_17140 {
	static private int r,c,k;
	static private int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken())-1;
		c = Integer.parseInt(st.nextToken())-1;
		k = Integer.parseInt(st.nextToken());
		map = new int[3][3];

		for(int i=0; i<3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}

		int time = 0;
		while(true) {
			//100초 보다 더걸리면 종료.
			if(time>100) {
				time = -1;
				break;
			}

			//k값 찾으면 종료.
			if(r<map.length && c<map[0].length) {
				if(map[r][c]==k) break;
			}

			if(map.length>=map[0].length) operationR();
			else operationC();

			time++;
		}

		System.out.println(time);
	}

	public static void operationR() {
		int size = Integer.MIN_VALUE;//최대 사이즈
		int[][] sub = new int[101][101];//임시 배열
		for(int i=0; i<map.length; i++) {
			int[] numArr = new int[101];
			List<Number> list = new ArrayList<>();

			//1. 1~n까지 숫자 카운트.
			for(int j=0; j<map[0].length; j++) {
				if(map[i][j]==0) continue;
				numArr[map[i][j]]++;
			}

			//2. 리스트에 숫자 & 등장횟수를 넣기.
			for(int j=1; j<numArr.length; j++) {
				if(numArr[j]==0) continue;
				list.add(new Number(j, numArr[j]));
			}

			//3. 정렬
			Collections.sort(list);

			//4. 정렬된 값을 임시배열에 넣고, 최대사이즈 구하기
			int k=0;
			for(int j=0; j<list.size(); j++) {
				sub[i][k] = list.get(j).n;
				sub[i][k+1] = list.get(j).cnt;
				k+=2;
			}
			size = Math.max(size, k);
		}
		if(size>100) size=100;

		//5. 기존 배열 재정의 및 임시배열의 값 넣기.
		map = new int[map.length][size];
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<size; j++) {
				map[i][j]=sub[i][j];
			}
		}
	}

	//R연산과 동일(인덱스만 바뀜)
	public static void operationC() {
		int size = Integer.MIN_VALUE;
		int[][] sub = new int[101][101];
		for(int j=0; j<map[0].length; j++) {
			int[] numArr = new int[101];
			List<Number> list = new ArrayList<>();
			for(int i=0; i<map.length; i++) {
				if(map[i][j]==0) continue;
				numArr[map[i][j]]++;
			}
			for(int i=1; i<numArr.length; i++) {
				if(numArr[i]==0) continue;
				list.add(new Number(i, numArr[i]));
			}
			Collections.sort(list);
			int k=0;
			for(int i=0; i<list.size(); i++) {
				sub[k][j] = list.get(i).n;
				sub[k+1][j] = list.get(i).cnt;
				k+=2;
			}
			size = Math.max(size, k);
		}
		if(size>100) size=100;
		map = new int[size][map[0].length];
		for(int i=0; i<size; i++) {
			for(int j=0; j<map[0].length; j++) {
				map[i][j]=sub[i][j];
			}
		}
	}

	static private class Number implements Comparable<Number>{
		int n;
		int cnt;
		public Number(int n, int cnt) {
			this.n = n;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Number o) {
			if(this.cnt>o.cnt) return 1;
			else if(this.cnt<o.cnt) return -1;
			else {
				if(this.n>o.n) return 1;
				else return -1;
			}
		}
	}
}
