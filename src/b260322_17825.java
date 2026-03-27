import java.io.*;
import java.util.*;

public class b260322_17825 {
    static int [] root1 = {2,4,6,8,10,12,14,16,18,20,22,25,26,28,30,32,34,36,38,40};
    static int [] root2 = {13,16,19,25,30,35,40};
    static int [] root3 = {22,24,25,30,35,40};
    static int [] root4 = {28,27,26,25,30,35,40};
    static int max = 0;
    static int [] unit = {0,0,0,0};
    static int [] dice;
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        dice = new int [10];
        for(int i = 0; i < 10 ; i++){
            dice[i] = Integer.parseInt(st.nextToken());
        }

        int i = 0;
        while(true){
            int num = dice[i];
            for(int j = 0 ; j<4; j++){
                unit[j] += num;
                
            }

            if(){
                i--;
                continue;
            }

            i++;
        }
    }


}
