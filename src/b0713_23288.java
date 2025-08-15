import java.util.Scanner;

public class b0713_23288 {
    static int [][] board;
    public static int[] dice = {1, 2, 3, 4, 5, 6};
    static int dir = 0;
    static int dice_y = 0;
    static int dice_x = 0;
    static int score = 0;
    static int [][] visited;
    static int [] dx = {1,0,-1,0};
    static int [] dy = {0,1,0,-1};

    public static void main(String[] args) {
        int N,M,K;
        int A,B,C;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        board = new int[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                board[i][j] = sc.nextInt();
            }
        }

        while(K>0){
            visited = new int[N][M];
            // 방향, 위치 기반 방향 재설정
            //주사위 이동
            dice_y += dy[dir];
            dice_x += dx[dir];

            if(dice_y < 0 || dice_y >= N || dice_x < 0 || dice_x >= M){
                dice_y -= dy[dir];
                dice_x -= dx[dir];

                dir = (dir + 2)%4;

                dice_y += dy[dir];
                dice_x += dx[dir];
            }

            //주사위 상태 변경
            dice_status();

            A = dice[5];
            B = board[dice_y][dice_x];
            C = DFS(dice_y,dice_x, M, N);

            score += B*C;

            //주사위 방향 변경
            if(A>B){
                dir = (dir+1)%4;
            }
            if(A<B){
                dir = (dir+3)%4;
            }


            K--;
        }

        System.out.println(score);
    }

    static void dice_status() {
        if(dir == 0){
            int temp = dice[0];
            dice[0] = dice[3];
            dice[3] = dice[5];
            dice[5] = dice[2];
            dice[2] = temp;

        } else if(dir == 1){
            int temp = dice[0];
            dice[0] = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[4];
            dice[4] = temp;
        } else if(dir == 2){
            int temp = dice[0];
            dice[0] = dice[2];
            dice[2] = dice[5];
            dice[5] = dice[3];
            dice[3] = temp;
        } else if(dir == 3){
            int temp = dice[0];
            dice[0] = dice[4];
            dice[4] = dice[5];
            dice[5] = dice[1];
            dice[1] = temp;
        }
    }

    static int DFS (int y, int x, int M, int N){
        int count = 1;
        int value = board[y][x];

        if(visited[y][x] == 1) return 0;

        //방문 체크
        if(visited[y][x] == 0){

            visited[y][x] = 1;

            if(x>0 && board[y][x-1] == value){
                count += DFS(y,x-1, M, N);
            }
            if(x+1<M && board[y][x+1] == value){
                count += DFS(y,x+1, M, N);
            }
            if(y>0 && board[y-1][x] == value){
                count += DFS(y-1,x, M, N);
            }
            if(y+1<N && board[y+1][x] == value){
                count += DFS(y+1,x, M, N);
            }
        }

        return count;
    }
}
