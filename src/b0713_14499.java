import java.util.Scanner;

public class b0713_14499 {
    static int [] dice = new int[6];
    static int [] dx = {0,0,-1,1};//동서북남
    static int [] dy = {1,-1,0,0};//동서북남
    static int x,y;
    public static void main (String [] args) {
        Scanner sc = new Scanner (System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        x = sc.nextInt(); // 행
        y = sc.nextInt(); // 열
        int K = sc.nextInt();
        int [][] map = new int[N][M];
        int dir;


        for(int i = 0 ; i<N ; i++){
            for(int j = 0 ; j<M ; j++){
                map[i][j] = sc.nextInt();
            }
        }

        while(K>0){
            K--;
            dir = sc.nextInt();
            //System.out.println("dir1 : "+ dir);
            int nx = x + dx[dir-1];
            int ny = y + dy[dir-1];

            //System.out.println("y : "+ny+" x : "+nx);
            if ( nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

            x = nx;
            y = ny;
            //System.out.println("dir2 : "+ dir);
            diceConvert(dir);
            if(map[x][y] == 0){
                map[x][y] = dice[5];
            }else{
                dice[5] = map[x][y];
                map[x][y] = 0;
            }
//            for(int i = 0 ; i<N ; i++){
//                for(int j = 0 ; j<M ; j++){
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
//            for(int i = 0 ; i<6; i++){
//                System.out.print(dice[i] + " ");
//            }
//            System.out.println();
            System.out.println(dice[0]);

        }

    }

    static void diceConvert (int dir){
        int temp;
        switch (dir){
            case 1: // 동
                temp = dice[1];
                dice[1] = dice[0];
                dice[0] = dice[2];
                dice[2] = dice[5];
                dice[5] = temp;
                break;
            case 2: // 서
                temp = dice[2];
                dice[2] = dice[0];
                dice[0] = dice[1];
                dice[1] = dice[5];
                dice[5] = temp;
                break;
            case 3: // 북
                temp = dice[3];
                dice[3] = dice[0];
                dice[0] = dice[4];
                dice[4] = dice[5];
                dice[5] = temp;
                break;
            case 4: // 남
                temp = dice[5];
                dice[5] = dice[4];
                dice[4] = dice[0];
                dice[0] = dice[3];
                dice[3] = temp;
                break;
            default:
                break;
        }
    }


}
