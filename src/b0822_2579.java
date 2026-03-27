import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b0822_2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int [] stairs = new int [n+1];
        int [] dp = new int [n+1];

        //계단 배열
        for(int i = 0 ; i < n ; i++){
            int x = Integer.parseInt(br.readLine());
            stairs[i+1] = x;
        }
        dp[0] = stairs[0] = 0;
        dp[1] = stairs[1];
        if(n>=2) dp[2] = stairs[1] + stairs[2];

        for(int i = 3 ; i < n+1 ; i++){
            int path1,path2;

            path1 = dp[i-3] + stairs[i-1] + stairs[i];
            path2 = dp[i-2] + stairs[i];

            dp[i] = Math.max(path1,path2);
        }

        System.out.println(dp[n]);
    }
}

/*
* 현위치까지 도달 할 수 있는 경로들을 파악하고 각 코스트를 비교하여 최대값을 dp에 저장한다.
* 경로는 총 2가지로 제한한다. ( 1이 두번 연속 나오면 연속된 3개의 계단을 밟게 된다 )
*   1. 2,1
*   2. 2
*
* 이 제한을 통해 1이 세번 반복되는 경우를 피한다.
*
* */