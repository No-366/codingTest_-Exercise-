import java.util.*;

public class b0629_17135 {

    static int N,M,D;
    static int maxKill = 0;
    static int [][] map;

    static class Point{
        int x,y;
        public Point(int x, int y){ //x = 열, y = 행
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Point)) return false;
            Point p = (Point) obj;
            return this.x == p.x && this.y == p.y;
        }
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args ){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        D = sc.nextInt();
        map = new int[N][M];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                map[i][j] = sc.nextInt();
            }
        }

        for(int i = 0; i<M-2; i++){
            for(int j = i+1; j<M-1; j++){
                for(int k = j+1; k<M; k++){
                    int [] arch = {i,j,k};
                    maxKill = Math.max(simulate(arch), maxKill);
                }
            }
        }
        System.out.println(maxKill);
    }

    static int simulate(int [] arch){
        int killCount = 0;
        int [][] map_temp = new int[N][M];


        for(int i = 0; i<N;i++){
            map_temp[i] = Arrays.copyOf(map[i],M);
        }

        while(hasEnemy(map_temp)){
            Set<Point> targets = new HashSet<>();
            //타겟 찾아서 HashSet에 넣기
            for(int i = 0; i<3; i++){
                Point targetP = findTarget(arch[i],map_temp);
                if(targetP != null){
                    targets.add(targetP);
                }
            }

            //맵_적 상태 갱신, 제거 카운팅
            for(Point targetP : targets){
                if(map_temp[targetP.y][targetP.x] == 1) {
                    map_temp[targetP.y][targetP.x] = 0;
                    killCount++;
                }
            }


            //맵 재설정 (적 1행 전진)
            for(int i = N-1; i>0; i--){
                map_temp[i] = Arrays.copyOf(map_temp[i-1],M);
            }
            Arrays.fill(map_temp[0],0);

        }

        return killCount;
    }

    static Point findTarget(int archX, int [][] map_temp){
        boolean [][] visited = new boolean[N][M];
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(archX,N));

        int [] dx = {-1, 0, 1};
        int [] dy = {0, -1, 0};

        while(!queue.isEmpty()){
            Point currP = queue.poll();
            for(int d = 0 ; d<3; d++){
                int nx = currP.x+dx[d];
                int ny = currP.y+dy[d];
                if(inBounds(nx,ny)&&!visited[ny][nx]){
                    int dist = Math.abs(archX - nx) + Math.abs(N - ny);
                    if(dist>D) continue;
                    visited[ny][nx] = true;
                    if(map_temp[ny][nx] == 1) return new Point(nx,ny);
                    queue.offer(new Point(nx,ny));
                }
            }
        }
        return null;
    }

    static boolean hasEnemy(int [][] map_temp){
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(map_temp[i][j] == 1){
                    return true;
                }
            }
        }
        return false;
    }

    static boolean inBounds(int x , int y){
        return x >= 0 && x < M && y >= 0 && y < N;
    }

}
