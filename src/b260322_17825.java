import java.io.*;
import java.util.*;

public class b260322_17825 {
    //0번(출발) ~ 32번(도착)
    static int [] map = {0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,13,16,19,22,24,28,27,26,25,30,35,40,0};
    static int [] visit = new int [33]; // 0~32
    static int max = 0;
    static int [] unit = {0,0,0,0,0}; // 1번 ~ 4번 , 0번은 없음을 의미 : 말 4개 // 조건 1 달성
    static int [] dice;
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        dice = new int [10];
        for(int i = 0; i < 10 ; i++){
            dice[i] = Integer.parseInt(st.nextToken());
        }

        game(0, 1, 0);

        System.out.println(max);
    }

    private static void game(int round, int unitNum, int sum) {

        // 10개의 턴 _ 조건 4 달성
        if(round > 9) {
            max = Math.max(max, sum); // 점수의 최댓값을 max에 저장 _ 조건 8 달성

            return;
        }

        int diceNum = dice[round];
        int cur = unit[unitNum];
        int next = getNext(cur, diceNum);

        if(cur == 32) return; // 도착칸에 있지 않은 말을 골라야 함 _ 조건 5 달성

        if(visit[next] != 0 && next < 32) return; // 이동할 칸에 다른 말 있으면 스킵 _ 조건 6, 7 달성

        unit[unitNum] = next; // 말 : 칸 이동
        visit[next] = unitNum; //말 : 칸 이동
        visit[cur] = 0; // 말 : 칸 이동
        sum += map[next]; // 점수 추가 _ 조건 8 달성


        //다음 라운드
        for(int i = 1 ; i<5; i++){
            int nextRound = round + 1;
            game(nextRound, i, sum);
        }

        //이전 상태 복구
        visit[next] = 0;
        visit[cur] = unitNum;
        unit[unitNum] = cur;

    }

    //말의 이동 _ 조건 2 달성
    private static int getNext(int cur, int diceNum) {
        int next = 0;

        if(cur ==5){
            if(diceNum <4){
                next = 19 + diceNum;
            }else{
                next = 27 + diceNum - 3;
            }
        }else if(cur == 10){
            if(diceNum <3){
                next = 22 + diceNum;
            }else{
                next = 27 + diceNum - 2;
            }

        }else if(cur == 15){
            next = 24 + diceNum;
        }else if((cur < 20) && (cur > 15) && (cur + diceNum > 19)){
            next = 30 + diceNum - (19 - cur);
        }else if((cur < 23) && (cur>19) && (cur + diceNum > 22)){
            next = 27 + diceNum - (22 - cur);
        }else if((cur < 25) && (cur > 22) && (cur + diceNum > 24)){
            next = 27 + diceNum - (24 - cur);
        }else{
            next = cur + diceNum;
        }

        // 도착칸에서 멈춤 _ 조건 3 달성
        if(next >= 32){
            next = 32;
        }
        return next;
    }


}
