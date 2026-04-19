import java.util.*;
import java.io.*;

public class b260419_17144 {
    static int R, C, T;
    static int [][] board;
    static int [][] snapshot;
    static int [] dx = {-1, +1, 0, 0}; // 상하좌우
    static int [] dy = {0, 0, -1, +1};
    static int [] cleaner = new int [2];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        board = new int[R][C];
        snapshot = new int[R][C];

        int k = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == -1) {
                    cleaner[k] = i;
                    k++;
                }
                board[i][j] = num;
            }
        }

        int time = 0;
        while (time < T){
            for (int i = 0; i < R; i++) {
                System.arraycopy(board[i], 0, snapshot[i], 0, board[0].length);
            }


            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (board[i][j] != 0 && board[i][j] != -1) {
                        int cnt = 0;
                        int dust = board[i][j];
                        for (int m = 0; m < 4; m++) {
                            int n_x = i + dx[m];
                            int n_y = j + dy[m];
                            if (n_x < 0 || n_x >= R || n_y < 0 || n_y >= C)
                                continue;
                            if (!(n_x == cleaner[0] && n_y == 0) && !(n_x == cleaner[1] && n_y == 0)) {
                                snapshot[n_x][n_y] += dust / 5;
                                cnt++;
                            }
                        }
                        snapshot[i][j] -= (dust / 5) * cnt;
                    }
                }
            }

            for (int i = 0; i < R; i++) {
                System.arraycopy( snapshot[i], 0, board[i], 0, board[0].length);
            }
            move();
            time ++;
        }

        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(board[i][j] == -1 ) continue;
                sum += board[i][j];
            }
        }

        System.out.println(sum);
    }

    public static void move () {
        int u_x = cleaner[0];
        int d_x = cleaner[1];
        // 위쪽 순환
        for (int i = 0; u_x - i - 1 >= 0; i++) {
            board[u_x - i][0] = board[u_x - i - 1][0];
        }
        for (int i = 0; i + 1 < C; i++) {
            board[0][i] = board[0][i + 1];
        }

        for (int i = 0; i + 1 <= u_x; i++) {
            board[i][C - 1] = board[i + 1][C - 1];
        }

        for (int i = C - 1; i - 1 >= 0; i--) {
            board[u_x][i] = board[u_x][i - 1];
        }

        //아래쪽 순환
        for (int i = d_x; i + 1 < R; i++) {
            board[i][0] = board[i + 1][0];
        }

        for (int i = 0; i + 1 < C; i++) {
            board[R - 1][i] = board[R - 1][i + 1];
        }

        for (int i = 0; R - 1 - i - 1 >= d_x; i++) {
            board[R - 1 - i][C - 1] = board[R - 1 - i - 1][C - 1];
        }

        for (int i = C - 1; i - 1 >= 0; i--) {
            board[d_x][i] = board[d_x][i - 1];
        }

        board[u_x][0] = -1;
        board[u_x][1] = 0;
        board[d_x][0] = -1;
        board[d_x][1] = 0;
    }
}