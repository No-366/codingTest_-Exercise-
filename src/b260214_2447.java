import java.io.IOException;
import java.util.Scanner;

public class b260214_2447{
    static int [][] board;
        public static void main(String args[]) throws IOException {
            Scanner sc = new Scanner(System.in);
            int N = sc.nextInt();
            //int N = 81;
            board = new int [N][N];

        recursion(N,0,0);

            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    sb.append(board[i][j] == 1 ? "*" : " ");
                }
                sb.append("\n");
            }
            System.out.print(sb);
        }

    public static void recursion(int N, int row, int col){
        for(int i = 0; i<3 ; i++){
            for(int j= 0; j<3 ; j++){
                if(i==1 && j==1) continue;
                if(N==3){
                    board[row+i][col+j] = 1;
                }else{
                    recursion(N/3,(row+i)*3,(col+j)*3);
                }
            }
        }
    }
}