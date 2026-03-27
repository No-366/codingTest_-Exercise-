import java.util.*;
import java.io.*;

public class b260315_1149 {
    static int N;
    static int [][] costs;
    static int [][] board;
    static int min = Integer.MAX_VALUE;
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        costs = new int [N + 1][3];
        board = new int[3][N+1];

        for(int i = 1; i<N+1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<3; j++){
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        board[0][1] = costs[1][0];
        board[1][1] = costs[1][1];
        board[2][1] = costs[1][2];

        int answer = dp();
        System.out.println(answer);
    }

    private static int dp() {
        int depth = 2;
        while(true){
            if(depth > N){
                min = Math.min(Math.min(board[0][N], board[1][N]), board[2][N]);
                break;
            }else{
                board[0][depth] = Math.min(board[1][depth-1], board[2][depth-1]) + costs[depth][0];
                board[1][depth] = Math.min(board[0][depth-1], board[2][depth-1]) + costs[depth][1];
                board[2][depth] = Math.min(board[0][depth-1], board[1][depth-1]) + costs[depth][2];
            }
            depth++;
        }

        return min;
    }
}

