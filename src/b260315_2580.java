import java.io.*;
import java.util.*;

public class b260315_2580 {
    static int [][] board = new int[9][9];
    static Deque<int []> list = new ArrayDeque<>();
    static Boolean success = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int i = 0; i < 9; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 0) list.push(new int []{i,j});
            }
        }
        dfs();

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

    }

    private static void dfs () {
        if(list.isEmpty()) return;
        int [] cur = list.peek();
        int row = cur[0];
        int col = cur[1];
        for(int i = 1; i <= 9; i++){
            if(check(row, col, i)){
                board[row][col] = i;
                list.pop();
                if(list.isEmpty()) {
                    success = true;
                    return;
                }
                dfs();
                if(success) return;
                board[row][col] = 0;
                list.push(cur);
            }
        }

    }

    private static boolean check(int r, int c, int num) {
        for(int i = 0; i < 9; i++){
            if((board[r][i] == num) || board[i][c]==num) return false;
        }
        for(int i = (r/3)*3; i < (r/3)*3 + 3; i++){
            for(int j = (c/3)*3; j < (c/3)*3 + 3; j++){
                if(board[i][j] == num) return false;
            }
        }
        return true;
    }

}
