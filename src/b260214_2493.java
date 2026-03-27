import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class b260214_2493 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		Stack<Integer> stack = new Stack<>();
		int[] heights = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 1; i < N+1; i++) {
			heights[i] = Integer.parseInt(st.nextToken());

			while(!stack.isEmpty()){
				int prev = stack.peek();
				if(heights[prev] <= heights[i]){
					stack.pop();
				}else{
					break;
				}
			}

			if(stack.isEmpty()){
				sb.append(0).append(" ");
			}else{
				int prev = stack.peek();
				sb.append(prev).append(" ");
			}

			stack.push(i);
		}

		System.out.println(sb);
	}
}

