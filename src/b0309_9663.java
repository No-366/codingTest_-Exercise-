import java.io.*;
import java.util.*;

public class b0309_9663 {
    static int N;
    static int[] arr;
    static int count = 0;

    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];

        dfs(0);
        System.out.println(count);

        br.close();
    }

    public static void dfs(int depth) {
        if (depth == N) {
            //depth가 N까지 도착하면 return
            count++;
            return;
        }
        for (int i = 0; i < N; i++) {
            arr[depth] = i;
            if (possible(depth)) {
                // 모든 조건에 통과하면 다음 depth탐색
                dfs(depth + 1);
            }
        }
    }

    public static boolean possible(int col) {
        for (int i = 0; i < col; i++) {
            // 같은 행인지
            if (arr[i] == arr[col]) {
                return false;
            }
            //대각선방향에 존재하는지
            else if (Math.abs(col - i) == Math.abs(arr[col] - arr[i])) {
                return false;
            }
        }
        // 같은행에도 없고 대각선방향에도 없으면 true
        return true;
    }
}