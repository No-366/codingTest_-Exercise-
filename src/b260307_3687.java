import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class b260307_3687 {
    //static int [] numbers = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6}; //0 ~ 9
    static String [] big = {"0","0","1","7","4","5","9","8"};//2,3,4,5,6,7
    static String [] small = {"0","0","1","7","4","2","6","8"};//2,3,4,5,6,7
    static String [] dp1 = new String [101];
    static String [] dp2 = new String [101];
    public static void main(String[] args) throws IOException {
        /*
        1개 : 조건 부적합 -> 0
        2개 -> 1
        3개 -> 7
        4개 -> 4, 11
        5개 -> 2, 3, 5
            2+3 -> 17
            3+2 -> 71

        6개 -> 0(x), 6, 9
            2+4 -> 14, 111
            4+2 -> 41, 111

        7개 ->
              7 -> 8
            2+5 -> 12, 13, 15, 117, 171
            3+4 -> 74, 711
            4+3 -> 47, 117
            5+2 -> 21, 31, 51, 171, 711

        8개 ->  2 + 6 : 10, 16, 19, 114, 111, 141, 1111
                3 + 5 : 72, 73, 75...
                4 + 4 :
                5 + 3 :
                6 + 2 :
       */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int i = 2; i < 101; i++){
            if(i<8){
                dp1[i] = big[i];
                dp2[i] = small[i];
            }else{
                dp1[i] = "1";
                dp2[i] = dp2[2] + ((dp2[i-2].equals("6")) ? "0" : dp2[i-2]);
            }
            for(int j = 2; i-j>1 ; j++){
                String b1 = dp1[i];
                String b2 = dp1[j] + dp1[i-j];
                if(b1.length() < b2.length() || (b1.length() == b2.length() && b1.compareTo(b2) < 0)){
                    dp1[i] = b2;
                }
                String s1 = dp2[i];
                String s2 = dp2[j] + ((dp2[i-j].equals("6")) ? "0" : dp2[i-j]);
                if(s1.length() > s2.length() || (s1.length() == s2.length() && s1.compareTo(s2) > 0)){
                    dp2[i] = s2;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            int target = Integer.parseInt(br.readLine());
            sb.append(dp2[target] + " " + dp1[target] + "\n");
        }
        System.out.println(sb.toString().trim());

    }

}
