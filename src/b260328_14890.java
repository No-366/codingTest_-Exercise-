import java.util.*;
import java.io.*;

public class b260328_14890 {
    static int [][] board;
    static int validRoutNum = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        board = new int [N][N];


        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //가로 길 찾기
        for(int i = 0 ; i < N ; i++){
            int sameCnt = 1;
            int cur = board[i][0];
            int pre;
            int mustSameCnt = 0;
            boolean invalidRout = false;
            for(int j = 1 ; j < N ; j++){
                pre = cur;
                cur = board[i][j];
                if(cur == pre){
                    sameCnt ++;
                    if(mustSameCnt > 0){
                        mustSameCnt --;
                        if(mustSameCnt == 0) sameCnt = 0;
                    }
                }else if(cur == pre + 1){
                    if(mustSameCnt > 0){
                        invalidRout = true;
                        break;
                    }
                    if(sameCnt >= L){
                        sameCnt = 1;
                    }else{
                        invalidRout = true;
                        break;
                    }
                }else if(cur == pre -1){
                    if(mustSameCnt > 0){
                        invalidRout = true;
                        break;
                    }
                    sameCnt = 1;
                    mustSameCnt = L-1;
                    if(mustSameCnt == 0) sameCnt = 0;
                }else{
                    invalidRout = true;
                    break;
                }
            }
            if(mustSameCnt != 0) invalidRout = true;
            if(!invalidRout) {
                System.out.println("가로 길 : " + i);
                validRoutNum++;
            }
        }

        //세로 길 찾기
        for(int i = 0 ; i < N ; i++){
            int sameCnt = 1;
            int cur = board[0][i];
            int pre;
            int mustSameCnt = 0;
            boolean invalidRout = false;
            for(int j = 1 ; j < N ; j++){
                pre = cur;
                cur = board[j][i];
                if(cur == pre){
                    sameCnt ++;
                    if(mustSameCnt > 0){
                        mustSameCnt --;
                        if(mustSameCnt == 0) sameCnt = 0;
                    }
                }else if(cur == pre + 1){
                    if(mustSameCnt > 0){
                        invalidRout = true;
                        break;
                    }
                    if(sameCnt >= L){
                        sameCnt = 1;
                    }else{
                        invalidRout = true;
                        break;
                    }
                }else if(cur == pre -1){
                    if(mustSameCnt > 0){
                        invalidRout = true;
                        break;
                    }
                    sameCnt = 1;
                    mustSameCnt = L-1;
                }else{
                    invalidRout = true;
                    break;
                }
            }
            if(mustSameCnt != 0) invalidRout = true;
            if(!invalidRout) {
                validRoutNum++;
            }
        }



        System.out.println(validRoutNum);
    }

}

