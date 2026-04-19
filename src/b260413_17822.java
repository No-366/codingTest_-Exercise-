import java.util.*;
import java.io.*;

public class b260413_17822 {
    static int N,M,T;
    static int [][] board;
    static int [][] condition;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        board = new int [N+1][M];
        condition = new int[T][3];

        int sum = 0;

        for(int i = 1 ; i<N+1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i<T; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<3; j++){
                condition[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //x의 배수인 원판을 d 방향으로 k칸 회전 : d=0 : 시계, d=1 : 반시계

        for(int i = 0 ; i<T; i++){
            int x = condition[i][0];
            int d = condition[i][1];
            int k = condition[i][2];

            rotate(x, d, k);
            find_same();
        }

        for(int i = 1 ; i<N+1; i++){
            for(int j = 0; j<M; j++){
                if(board[i][j] != -1){
                    sum += board[i][j];
                }
            }
        }

        System.out.println(sum);
    }

    private static void find_same() {
        //하나의 원판에서 동일한 수가 인접하는 경우
        ArrayList<int[]> memory = new ArrayList<>();
        int sum = 0;
        int cnt = 0;
        for(int i = 1; i<N+1; i++){
            for(int j = 0; j<M-1; j++){
                if(board[i][j] != -1 && board[i][j] == board[i][j+1]){
                    memory.add(new int [] {i,j});
                    memory.add(new int [] {i,j+1});
                }
            }
            if(board[i][M-1] != -1 && board[i][M-1] == board[i][0]){
                memory.add(new int [] {i,M-1});
                memory.add(new int [] {i,0});
            }
        }

        //여러개의 원판에서 동일한 수가 인접하는 경우
        for(int i = 0; i<M; i++){
            for(int j = 1; j<N; j++){
                if(board[j][i] != -1 && board[j][i] == board[j+1][i]){
                    memory.add(new int [] {j,i});
                    memory.add(new int [] {j+1,i});
                }
            }
        }

        if(memory.isEmpty()){
            for(int i = 1 ; i<N+1; i++){
                for(int j = 0; j<M; j++){
                    if(board[i][j] != -1){
                        sum += board[i][j];
                        cnt ++;
                    }
                }
            }
            double avg = (double)sum/cnt;

            for(int i = 1 ; i<N+1; i++){
                for(int j = 0; j<M; j++){
                    if(board[i][j] != -1 && board[i][j] < avg){
                        board[i][j]++;
                    }else if (board[i][j] != -1 && board[i][j] > avg){
                        board[i][j]--;
                    }
                }
            }
            return;
        }

        for(int [] v : memory){
            if(board[v[0]][v[1]] != -1){
                board[v[0]][v[1]] = -1;
            }
        }
    }

    private static void rotate(int x, int d, int k) {
        if(d!=0) k = M-k;
        for(int i = x; i<N+1; i+=x){
            for(int j = 0; j<k; j++){
                int temp = board[i][M-1];
                for(int l = M-1; l>0; l--){
                    board[i][l] = board[i][l-1];
                }
                board[i][0] = temp;
            }
        }
    }
}
