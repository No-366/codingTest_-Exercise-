import java.util.*;
import java.io.*;

public class b260315_1931 {
    static int [][] time;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        time = new int[N][2];

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i][0] = Integer.parseInt(st.nextToken());
            time[i][1] = Integer.parseInt(st.nextToken());
        }

        // 정렬 기준: 1) 종료 시간, 2) 시작시간
        Arrays.sort(time, (a,b) -> a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]);
        System.out.println(Arrays.deepToString(time));

        /*유효 조건
        * 전 회의 종료 시간 <= 다음 회의 시작 시간
        */

        int cnt = 1;
        int preIdx = 0;
        int curIdx = 1;
        while(curIdx < N){

            if(time[curIdx][0] >= time[preIdx][1]){
                cnt++;
                preIdx = curIdx;
                curIdx++;
                continue;
            }

            curIdx++;
        }

        System.out.println(cnt);
    }
}
