import java.io.IOException;
import java.util.Scanner;

public class b260214_11729 {
	static int cnt = 0;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		//int n = 3;


		hanoi(n, 1, 3, 2);
		System.out.println(cnt);
		System.out.println(sb);
	}

	private static void hanoi(int n, int start, int end, int rest){
		cnt++;
		if(n>1){
			hanoi(n-1, start, rest, end);
			sb.append(start).append(" ").append(end).append("\n");
			hanoi(n-1, rest, end, start);
		}else{
			sb.append(start).append(" ").append(end).append("\n");
		}
	}
}
