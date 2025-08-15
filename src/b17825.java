import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b17825 {
    public static int[] dice;
    public static int ans;

    public static int[] connect;
    public static int[] score;
    public static boolean[] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dice = new int[10];
        String[] data = br.readLine().split(" ");
        for(int i=0;i<10;i++) {
            dice[i] = Integer.parseInt(data[i]);
        }

        connect = new int[33];
        score = new int[33];

        // 외곽
        for(int i=0;i<21;i++) {
            connect[i] = i+1;
            score[i] = i*2;
        }
        // 도착
        connect[21] = 21;
        score[21] = 0;

        // 10 내선  (22 ~ 24)
        connect[22] = 23;
        connect[23] = 24;
        connect[24] = 30;
        score[22] = 13;
        score[23] = 16;
        score[24] = 19;

        // 20 내선 (25 ~ 26)
        connect[25] = 26;
        connect[26] = 30;
        score[25] = 22;
        score[26] = 24;

        // 30 내선 (27 ~ 32)
        connect[27] = 28;
        connect[28] = 29;
        connect[29] = 30;

        score[27] = 28;
        score[28] = 27;
        score[29] = 26;

        // 나머지
        connect[30] = 31;
        connect[31] = 32;
        connect[32] = 20;

        score[30] = 25;
        score[31] = 30;
        score[32] = 35;

        ans = 0;
        check = new boolean[33]; //해당 위치에 이미 말이 있는지 확인용
        dfs(new int[5], 0, 0);
        System.out.println(ans);
    }

    public static void dfs(int[] obj, int depth, int sum) {
        if(depth == 10) {

            ans = Math.max(ans, sum);
            return;
        }

        for(int i=0;i<5;i++) {
            int x = obj[i]; //말의 위치(이동하는 과정에서의 위치)
            int now = obj[i]; //현재 말의 위치(이동 전)
            int move = dice[depth];

            if(x == 5) {
                x = 22;
                move--;
            } else if(x==10) {
                x = 25;
                move--;
            } else if(x==15) {
                x = 27;
                move--;
            }

            if(x+move <= 21) {
                x += move;
            } else {
                for(int m=0;m<move;m++) {
                    x = connect[x];
                }
            }

            if(check[x] &&  x != 21) {
                continue;
            }

            check[x] = true; //이동 후 위치
            check[now] = false; // 이동 전 위치
            obj[i] = x; //말의 현재 위치 == 이동 후 위치

            dfs(obj, depth+1, sum+score[x]);

            check[x] = false; //백트래킹
            check[now] = true;
            obj[i] = now;


        }
    }


}