import java.util.*;
import java.io.*;

public class b260301_10026 {
    static int [][] visited1;
    static int [][] visited2;
    static int cnt1 = 0;
    static int cnt2 = 0;
    static int [] dx = {-1, 0, 1, 0}; // 상,우,하,좌
    static int [] dy = {0, +1, 0, -1};// 상,우,하,좌
    static char [][] arr;
    static char [][] arr2;
    static int N;

    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new char [N][N];
        arr2 = new char [N][N];
        visited1 = new int[N][N];
        visited2 = new int [N][N];

        for(int i = 0; i<N ; i++){
            String line = br.readLine();
            for(int j = 0; j<N ; j++){
                char ch = line.charAt(j);
                arr[i][j] = ch;
                if(ch == 'G'){
                    arr2[i][j] = 'R';
                }else{
                    arr2[i][j] = ch;
                }
            }
        }

        for(int i = 0 ; i<N ; i++){
            for(int j = 0 ; j<N ; j++){
                if(visited1[i][j] == 0){
                    char target1 = arr[i][j];
                    normal(target1,i,j);
                    cnt1++;
                }
                if(visited2[i][j] == 0){
                    char target2 = arr2[i][j];
                    enormal(target2,i,j);
                    cnt2++;
                }
            }
        }

        System.out.println(cnt1);
        System.out.println(cnt2);

    }

    static void normal(char rgb, int row, int col){
        visited1[row][col] = 1;
        for(int i = 0; i<4 ; i++){
            int next_row = row + dx[i];
            int next_col = col + dy[i];
            if(next_row>=0&&next_row<N&&next_col>=0&&next_col<N){
                if(visited1[next_row][next_col] == 0 && arr[next_row][next_col] == rgb){
                    visited1[row][col] = 1;
                    normal(rgb,next_row,next_col);
                }
            }
        }
    }

    static void enormal(char rgb, int row, int col){
        visited2[row][col] = 1;
        for(int i = 0; i<4 ; i++){
            int next_row = row + dx[i];
            int next_col = col + dy[i];
            if(next_row>=0&&next_row<N&&next_col>=0&&next_col<N){
                if(visited2[next_row][next_col] == 0 && arr2[next_row][next_col] == rgb){
                    visited2[row][col] = 1;
                    enormal(rgb,next_row,next_col);
                }
            }
        }
    }
}