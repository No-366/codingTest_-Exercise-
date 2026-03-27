import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class b260301_7576 {
    static int [][] boxes;
    static int [][] visited;
    static int cnt = 0;
    static int [] dx = {-1, 0, 1, 0};
    static int [] dy = {0, +1, 0, -1};
    static Deque<Node> queue = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boxes = new int[M][N];
        visited = new int[M][N];

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int init = Integer.parseInt(st.nextToken());
                boxes[i][j] = init;
                if(init==1){
                    queue.addLast(new Node(i,j,0));
                }
            }
        }

        while(!queue.isEmpty()){
            Node cur = queue.poll();
            boxes[cur.r][cur.c] = 1;
            for(int i = 0; i<4; i++){
                int next_r = cur.r+ dx[i];
                int next_c = cur.c+ dy[i];
                int next_day = cur.day + 1;
                if(next_r>=0 && next_c>=0 && next_r<M && next_c<N
                        && visited[next_r][next_c]==0 && boxes[next_r][next_c] == 0){
                    queue.addLast(new Node(next_r,next_c,next_day));
                    visited[next_r][next_c] = 1;
                }
            }
            cnt = cur.day;
        }
        for(int i = 0; i<M; i++){
            for(int j = 0; j<N; j++){
                if(boxes[i][j]==0){
                    cnt = -1;
                    break;
                }
            }
        }
        System.out.println(cnt);
    }

    public static class Node{
        int r;
        int c;
        int day;
        public Node(int r, int c, int day) {
            this.r = r;
            this.c = c;
            this.day = day;
        }
    }
}
