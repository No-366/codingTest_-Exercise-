import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class b260222_1253 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int [] arr = new int[n];
		int answer = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i = 0; i < n; i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		for(int i = 0; i < n; i++){
			int left = 0;
			int right = n-1;
			while(left<right){
				if(left==i){
					left++;
					continue;
				}
				if(right==i){
					right--;
					continue;
				}
				int sum = arr[left] + arr[right];
				if(sum > arr[i]){
					right--;
				}else if(sum < arr[i]){
					left++;
				}else{
					answer++;
					break;
				}
			}
		}
		System.out.println(answer);

	}
}
