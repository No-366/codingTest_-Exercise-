import java.util.*;
import java.io.*;

public class b260315_11053 {
    static int N;
    static int [] num;
    static int [] lenBoard;
    static int max = Integer.MIN_VALUE;
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        num = new int[N+1];
        for(int i = 1; i < N+1; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }
        lenBoard = new int[N+1];

        for(int i = 1; i < N+1; i++){
            int cur = num[i];
            for(int j =1; j< i; j++){
                if(cur > num[j]){
                    lenBoard[i] = Math.max(lenBoard[i], lenBoard[j]);
                }
            }
            lenBoard[i] ++;
            max = Math.max(max, lenBoard[i]);
        }

        System.out.println(max);
    }
}
