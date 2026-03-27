import java.util.*;
import java.io.*;

public class b260307_1244 {
    static int [] switches;
    static int N;
    public static void main(String [] args) throws IOException{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        switches = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N+1; i++){
            switches[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            action(s,num);
        }

        for(int i = 1; i < N+1; i++){
            if(i%20 == 0){
                sb.append(switches[i]+"\n");
            }else{
                sb.append(switches[i]+" ");
            }

        }
        String answer = sb.toString().trim();
        System.out.println(answer);

    }

    private static void action(int s, int num) {
        if(s == 1){
            for(int i = num; i<N+1 ; i += num){
                switches[i] = (switches[i] == 0) ? 1 : 0;
            }
        }else if(s == 2){
            switches[num] = (switches[num] == 0) ? 1 : 0;
            if((num > 1 && num < N && switches[num-1] == switches[num+1])){
                int left = num-1;
                int right = num+1;
                while(left>=1 && right<N+1){

                    if(switches[left] != switches[right]) break;

                    switches[left] = (switches[left] == 0) ? 1 : 0;
                    switches[right] = (switches[right] == 0) ? 1 : 0;

                    left --;
                    right ++;
                }
            }
        }
    }
}
