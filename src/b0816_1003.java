import java.io.*;
import java.util.Scanner;

public class b0816_1003 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int [] zeroCnt = new int [41];
        int [] oneCnt = new int [41];

        int N = Integer.parseInt(br.readLine());

        zeroCnt[0] = 1;
        zeroCnt[1] = 0;

        oneCnt[0] = 0;
        oneCnt[1] = 1;

        for(int i = 2; i < 41; i++){
            zeroCnt[i] = zeroCnt[i-1] + zeroCnt[i-2];
            oneCnt[i] = oneCnt[i-1] + oneCnt[i-2];
        }

        for(int i = 0; i < N; i++){
            int n = Integer.parseInt(br.readLine());
            bw.write(zeroCnt[n] + " " + oneCnt[n]);
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}
