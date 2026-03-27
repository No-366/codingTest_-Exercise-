import java.util.*;
import java.io.*;

public class b260315_11054 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int [] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int [] increase = new int[N];
        int [] decrease = new int[N];
        int max = 0;

        for(int i = 0; i < N; i++){
            int inc = 0;
            for(int j = 0; j < i; j++){
                if(arr[i] > arr[j]){
                    inc = Math.max(inc, increase[j]);
                }
            }
            increase[i] = inc + 1;
        }

        for(int i = N-1; i >= 0; i--){
            int dec = 0;
            for(int j = N-1; j > i; j--){
                if(arr[i] > arr[j]){
                    dec = Math.max(dec, decrease[j]);
                }
            }
            decrease[i] = dec + 1;
        }

        for(int i = 0; i < N; i++){
            max = Math.max(max, increase[i] + decrease[i] -1);
        }


        System.out.println(max);
    }
}
