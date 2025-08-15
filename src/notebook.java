import java.io.*;
import java.util.*;

public class notebook {
    static int n;
    static int [][] map;
    static ArrayList<Teacher> teachers = new ArrayList<>();
    static boolean canAvoid = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int [n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                switch(st.nextToken()){
                    case "S":
                        map[i][j] = 1;
                        break;
                    case "T":
                        map[i][j] = -1;
                        teachers.add(new Teacher(i, j));
                        break;
                    case "X":
                        map[i][j] = 0;
                        break;
                    default:
                        break;

                }
            }
        }

        dfs(0,0);

        System.out.println(canAvoid?"YES":"NO");
    }

    static void dfs(int depth, int start){
        if(canAvoid) return;

        if(depth == 3){
            for(Teacher t : teachers){
                if(t.detect()) return;
            }
            canAvoid = true;
            return;
        }

        for(int pos = start; pos < n * n ; pos++){
            int row = pos/n;
            int col = pos%n;

            if(map[row][col] == 0){
                map[row][col] = 2;
                dfs(depth + 1, pos + 1);
                map[row][col] = 0;
            }
        }
    }

    static class Teacher {
        int x,y;
        int [] dx = {-1,1,0,0};
        int [] dy = {0,0,-1,1};

        public Teacher(int x, int y){
            this.x = x;
            this.y = y;
        }

        public boolean detect(){
            for(int dir = 0; dir<4 ; dir++){
                int look_x = x + dx[dir];
                int look_y = y + dy[dir];

                while(look_x>=0 && look_x<n && look_y>=0 && look_y<n){

                    if(map[look_x][look_y] == 2){
                        break;
                    }else if(map[look_x][look_y] == 1){
                        return true;
                    }

                    look_x += dx[dir];
                    look_y += dy[dir];
                }
            }
            return false;
        }
    }
}