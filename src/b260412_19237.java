import java.io.*;
import java.util.*;

public class b260412_19237 {
    static int N, M, K; // N : 격자 크기, M : 상어 마리수, K : 냄새 유효 기간
    static int [] init_dir; // 상어별 초기 방향
    static Shark[] sharks; // 상어 객체들
    static Smell_board smell_board = new Smell_board();
    static int [][] grid;

    public static void main(String [] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        init_dir = new int [M+1]; //이전 이동 방향 1: 위, 2: 아래, 3: 왼쪽, 4: 오른쪽
        sharks = new Shark [M+1]; //1번 상어 ~ M번 상어
        smell_board = new Smell_board();
        smell_board.smells_init();
        grid = new int [N][N];

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                int n = Integer.parseInt(st.nextToken());
                grid[i][j] = n;
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<M+1; i++){
            init_dir[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                int n = grid[i][j];
                if( n != 0){
                    sharks[n] = new Shark(n, i, j, init_dir[n]);
                }
            }
        }

        for(int i = 1; i<M+1; i++){
            for(int j = 1; j<5; j++){
                st = new StringTokenizer(br.readLine());
                for(int k = 1 ; k<5; k++){
                    int d = Integer.parseInt(st.nextToken());
                    sharks[i].setDir(j,k,d);
                }
            }
        }

        int time = start();

        System.out.println(time);
    }

    private static int start() throws Exception {
        int time = 0;

        while(time < 1000){
            if(stage()) return time;
            // 격자 상어 정리
            smell_board.smell_lifecycle();

            time++;
        }
        return -1;
    }

    private static boolean stage() throws Exception {
        boolean is_end = true;

        smell_board.create_smell(); // 각 상어별로 현 위치에 냄새 남김

        is_end = Shark.move_all();

        return is_end;
    }

    public static class Shark {
        int num;
        boolean is_alive;
        int x;
        int y;
        int pre_dir;
        int [][] dir = new int [5][5];

        public Shark(int num, int x, int y, int pre_dir){
            this.num = num;
            this.is_alive = true;
            this.x = x;
            this.y = y;
            this.pre_dir = pre_dir;
        }

        public void setDir(int i, int j, int d){
            this.dir[i][j] = d;
        }

        public void updateXY(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.pre_dir = d;
        }

        public void die(){
            this.is_alive = false;
        }

        public int [] find_direction() throws Exception {
            int [] dx = {0,-1,+1,0,0};
            int [] dy = {0,0,0,-1,+1};

            int next_dir;

            for(int i=1; i<5; i++){
                next_dir = dir[this.pre_dir][i];

                int next_x = this.x + dx[next_dir];
                if(next_x<0 || next_x>=N) continue;

                int next_y = this.y + dy[next_dir];
                if(next_y<0 || next_y>=N) continue;

                if(smell_board.smells[next_x][next_y].num != -1){
                    continue;
                }

                return new int [] {next_x,next_y,next_dir};
            }

            for(int i=1; i<5; i++){
                next_dir = dir[this.pre_dir][i];

                int next_x = this.x + dx[next_dir];
                if(next_x<0 || next_x>=N) continue;

                int next_y = this.y + dy[next_dir];
                if(next_y<0 || next_y>=N) continue;

                if(smell_board.smells[next_x][next_y].num == this.num) {
                    return new int [] {next_x,next_y,next_dir};
                }
            }
            String errorMessage = "방향 설정 에러 : "+ this.num + ", " +this.x+ ", "+ this.y;
            throw new Exception(errorMessage);
        }

        private static boolean move_all() throws Exception {
            boolean is_end = true;
            int[][] nextPos = new int[M+1][3]; // [x, y, dir]

            // 1단계: 현재 grid 기준으로 모든 상어 다음 위치 계산
            for(int i = 1; i <= M; i++){
                if(sharks[i].is_alive){
                    if(i>1) is_end = false;
                    nextPos[i] = sharks[i].find_direction(); // grid 건드리지 않음
                }
            }

            // 2단계: 같은 칸으로 이동하는 상어들 중 번호 큰 것 제거
            for(int i = 1; i <= M; i++){
                if(!sharks[i].is_alive) continue;
                for(int j = i+1; j <= M; j++){
                    if(!sharks[j].is_alive) continue;
                    if(nextPos[i][0] == nextPos[j][0] && nextPos[i][1] == nextPos[j][1]){
                        sharks[j].die();
                    }
                }
            }

            // 3단계: grid 초기화 후 살아있는 상어 위치 반영
            for(int i = 0; i < N; i++) Arrays.fill(grid[i], 0);

            for(int i = 1; i <= M; i++){
                if(sharks[i].is_alive){
                    sharks[i].updateXY(nextPos[i][0], nextPos[i][1], nextPos[i][2]);
                    grid[nextPos[i][0]][nextPos[i][1]] = i;
                }
            }

            return is_end;
        }
    }

    public static class Smell_board{
        public Smell[][] smells = new Smell[N][N];

        public void smells_init(){
            for(int i = 0 ; i<N ; i++){
                for(int j = 0; j<N; j++){
                    smells[i][j] = new Smell(-1,-1); // 냄새 보드 초기 세팅
                }
            }
        }

        public void smell_lifecycle(){
            for(int i = 0; i<N; i++){
                for(int j = 0; j<N; j++){
                    if(smells[i][j].exp > 0){
                        smells[i][j].exp--;
                        if(smells[i][j].exp == 0){
                            smells[i][j].num = -1;
                        }
                    }
                }
            }
        }

        public void create_smell(){
            for(int i = 1 ; i<M+1; i++){
                Shark s = sharks[i];
                if(s.is_alive){
                    smells[s.x][s.y] = new Smell(s.num);
                }
            }
        }
    }

    public static class Smell {
        int exp = K;
        int num;

        public Smell(int num){
            this.num = num;
        }

        public Smell(int num, int exp){
            this.num = num;
            this.exp = exp;
        }
    }
}
