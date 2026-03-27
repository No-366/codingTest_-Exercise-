import java.util.*;
import java.io.*;

public class b260307_1976 {
    static int N;
    static int M;
    static int [][] map;
    static boolean [] visited;
    static int [] plan;
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        map = new int [N+1][N+1];
        visited = new boolean [N+1];
        plan = new int [M];
        String answer = "YES";

        for(int i = 1 ; i < N+1 ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1 ; j < N+1 ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < M ; i++){
            plan[i] = Integer.parseInt(st.nextToken());
        }
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(plan[0]);
        visited[plan[0]] = true;
        while(!stack.isEmpty()){
            int start = stack.pop();

                for(int j = 1 ; j < N+1 ; j++){
                    if(map[start][j] == 1 && !visited[j]){
                        stack.push(j);
                        visited[j] = true;
                    }
                }
        }

        for(int p : plan){
            if(!visited[p]){
                answer = "NO";
                break;
            }
        }

        System.out.println(answer);
    }
}
