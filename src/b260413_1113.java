import java.util.*;
import java.io.*;

public class b260413_1113 {
    static int N,M;
    static int [][] board;
    static Deque<int []> queue = new ArrayDeque<>();
    static int [] dx = {-1, +1, 0, 0}; // 상 하 좌 우
    static int [] dy = {0, 0, -1, +1};
    static int Max = Integer.MIN_VALUE;
    static int Min = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int [N][M];
        int result = 0;

        for(int i = 0; i<N; i++){
            String line = br.readLine();
            for(int j=0; j<M; j++){
                int n = line.charAt(j) - '0';
                board[i][j] = n;
                Max = Math.max(Max,n);
                Min = Math.min(Min,n);
            }
        }

        for(int i = Min; i<=Max; i++){
            for(int j = 1; j<N-1; j++){
                for(int k = 1; k<M-1; k++){
                    if(board[j][k] == i){
                        result += bfs(i,j,k);
                    }
                }
            }
        }

        System.out.println(result);
    }

    private static int bfs(int depth, int row, int col) {
        queue.clear();
        int cnt = 1;
        boolean isPossible = true;
        queue.addLast(new int [] {row, col});
        board[row][col] = depth + 1;

        while(!queue.isEmpty()){
            int c_x = queue.peek()[0];
            int c_y = queue.poll()[1];

            for(int i = 0; i<4; i++){
                int n_x = c_x + dx[i];
                int n_y = c_y + dy[i];
                if(!verify(n_x,n_y,depth)){
                    isPossible = false;
                    continue;
                }
                if(board[n_x][n_y] != depth){
                    continue;
                }
                board[n_x][n_y] ++;
                cnt++;
                queue.addLast(new int [] {n_x,n_y});
            }
        }
        if(isPossible) return cnt;
        return 0;
    }

    public static boolean verify(int x, int y, int depth){
        if(x<0 || x>=N) return false;
        if(y<0 || y>=M) return false;
        if(board[x][y] < depth) return false;
        return true;
    }
}



