import java.io.*;
import java.util.*;

public class b0720_17136 {
    static BufferedReader br;
    static StringTokenizer st;
    static boolean [][] graph = new boolean [10][10];
    static ArrayList<Point> candidate = new ArrayList<>();
    static ArrayList<Paper> paper = new ArrayList<>();
    static int limit;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        input();

        DFS(0,0);

        if(answer == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(answer);
        }
    }

    public static void DFS(int depth, int count){

        if(depth == limit){
            answer = Math.min(answer, count);
            return;
        }

        if(count >= answer){
            return;
        }

        Point now = candidate.get(depth);

        if(!graph[now.x][now.y]){
            DFS(depth+1, count);
        }else{
            for(int i = 4; i >= 0 ; i--){
                if(paper.get(i).amount==0 || checkProblem(paper.get(i).size, now.x, now.y)){
                    continue;
                }
                paper.get(i).amount--;
                doPaper(paper.get(i).size, now.x, now.y);
                DFS(depth+1, count+1);
                paper.get(i).amount++;
                doPaper(paper.get(i).size, now.x, now.y);
            }
        }


    }

    public static void doPaper(int size, int row, int col){
        for(int i = row; i < row+size; i++){
            for(int j = col; j < col+size; j++){
                graph[i][j] = !graph[i][j];
            }
        }
    }

    public static boolean checkProblem(int size, int row, int col){
        for(int i = row; i < row+size; i++){
            for(int j = col; j < col+size; j++){
                if(i>=10||j>=10||!graph[i][j]){
                    return true;
                }
            }
        }
        return false;
    }

    public static void input() throws IOException{
        int num;
        for(int i = 0; i<10 ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<10 ; j++){
                num = Integer.parseInt(st.nextToken());
                if(num == 0){
                    graph[i][j] = false;
                }else{
                    graph[i][j] = true;
                    candidate.add(new Point(i,j));
                }
            }
        }
        limit = candidate.size();

        paper = new ArrayList<Paper>();
        for(int i = 0; i<5 ; i++){
            paper.add(new Paper(i+1));
        }

    }

    static class Point{
        int x;
        int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static class Paper{
        int size;
        int amount = 5;

        public Paper(int size){
            this.size = size;
        }
    }

}
