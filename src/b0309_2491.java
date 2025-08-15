import java.io.*;
import java.util.*;

class b0309_2491 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] dpSmall = new int[N];
        int[] dpBig = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long max = 1;
        dpSmall[0] = 1;
        dpBig[0] = 1;
        for (int i = 1; i < N; i++) {
            // 증가하는 경우
            if (arr[i] >= arr[i - 1]) {
                dpBig[i] = dpBig[i - 1] + 1;
            } else {
                dpBig[i] = 1;
            }

            // 감소하는 경우
            if (arr[i] <= arr[i - 1]) {
                dpSmall[i] = dpSmall[i - 1] + 1;
            } else {
                dpSmall[i] = 1;
            }

            max = Math.max(max, Math.max(dpSmall[i], dpBig[i]));
        }

        System.out.println(max);
    }
}