import java.util.*;
import java.io.*;
public class b260301_1260{
    static StringBuilder sb1 = new StringBuilder();
    static StringBuilder sb2 = new StringBuilder();
    static int [][] board;
    static int [] visited;
    static int N,M,V;
    static Deque<Integer> queue = new ArrayDeque();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        board = new int [N+1][N+1];
        visited = new int [N+1];

        int i = 1;
        while(i<M+1){
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            board[first][second] = 1;
            board[second][first] = 1;
            i++;
        }

        dfs(V);
        visited = new int [N+1];
        bfs(V);

        System.out.println(sb1+"\n"+sb2);
    }

    public static void dfs(int start){
        sb1.append(start).append(" ");
        visited[start] = 1;

        for(int i = 1; i<N+1 ; i++){
            if(board[start][i]==1 && visited[i] == 0){
                dfs(i);
            }
        }
    }

    public static void bfs(int start){
        queue.addLast(start);
        visited[start] = 1;
        while(!queue.isEmpty()){
            int curr = queue.removeFirst();
            sb2.append(curr).append(" ");
            for(int i = 1 ; i<N+1 ; i++){
                if(board[curr][i]==1 && visited[i] == 0){
                    queue.addLast(i);
                    visited[i] = 1;
                }
            }
        }

    }
}