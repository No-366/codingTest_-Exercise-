import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class b0824_2606 {
    static int n;
    static int [] dp;
    static int [] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //컴퓨터 수
        n = Integer.parseInt(br.readLine());
        Computer [] computers = new Computer[n];
        for(int i = 0; i < n; i++){
            computers[i] = new Computer(i+1);
        }

         dp = new int[n+1];
         visited = new int[n+1];

        //시작 컴퓨터 번호
        int startNum= Integer.parseInt(br.readLine());

        for(int i=1;i<=n;i++){
            String [] Line = br.readLine().split(" ");
            int start = Integer.parseInt(Line[0]);
            int end = Integer.parseInt(Line[1]);

            computers[start-1].makeLink(end);
        }

        //bfs(startNum);

    }



    private void dfs(){

        //return dfs();
    }

    private static class Computer{
        int [] link = new int [n];
        int next = 0;
        int computerId;

        public Computer(int computerNum){
            this.computerId = computerNum;
        }

        public void makeLink (int linkComputerNum){
            link[next] = linkComputerNum;
            next++;
        }
    }
}
