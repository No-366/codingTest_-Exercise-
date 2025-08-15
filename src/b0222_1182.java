import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b0222_1182 {
    static int N, S;
    static int count = 0;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0,0);

        // S가 0인 경우, 공집합 빼줌.
        System.out.println(S == 0 ? count-1 : count);

    }

    public static void dfs(int idx, int sum){
        if(idx == N){
            if(sum == S){
                count++;
            }
            return;
        }

        dfs(idx+1, sum + arr[idx]);
        dfs(idx+1, sum);
    }
}
