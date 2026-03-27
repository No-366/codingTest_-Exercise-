import java.io.*;
import java.util.*;

public class b260322_16918 {
    static int [][] board;
    static int R;
    static int C;
    static Deque<int[]> stack = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken()); // 총 시간
        board = new int [R][C];

        //1. 격자판 기본 상태 설정 2초 전
        for(int i = 0; i < R; i++){
            String line = br.readLine();
            for(int j = 0; j < C; j++){
                char token = line.charAt(j);
                if(token == '.'){
                    board[i][j] = -1; // 빈칸
                }else{
                    board[i][j] = 0 + 1; // 폭탄
                }
            }
        }
        //2. 2초부터 시작
        int time = 1;
        while(true){
            // 추가 설치단계
            time++;
            if(time>N) break;
            for(int i = 0; i<R; i++){
                for(int j = 0 ; j<C; j++){
                    board[i][j] ++; // -1 -> 0 (새로 폭탄 설치(0)) , 1->2 (설치된 폭탄 (2))
                }
            }

            // 폭파단계 (1)
            time++;
            if(time>N) break;
            for(int i = 0; i<R; i++){
                for(int j = 0 ; j<C; j++){
                    board[i][j]++; // 0 -> 1 (새로 설치된 폭탄(1))
                    if(board[i][j] == 3){
                        stack.push(new int [] {i,j});
                    }
                }
            }
            //폭파단계(2)
            while(!stack.isEmpty()){
                int [] target = stack.pop();
                bomb(target[0],target[1]);
            }

        }


        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < R; i++){
            for(int j = 0; j<C; j++){
                if(board[i][j] == -1){
                    sb.append(".");
                }else{
                    sb.append("0");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }
    static void bomb(int r, int c){
        int [] dx = {-1, 0, +1, 0};
        int [] dy = {0, +1, 0, -1};

        board[r][c] = -1;

        for(int i = 0 ; i<4; i++){
            int x = r+dx[i];
            int y = c+dy[i];
            if(x<R && x>=0 && y<C && y>=0){
                board[x][y] = -1;
            }
        }
    }

}
