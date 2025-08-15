////TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
//// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
//public class Main {
//    public static void main(String[] args) {
//        //TIP 캐럿을 강조 표시된 텍스트에 놓고 <shortcut actionId="ShowIntentionActions"/>을(를) 누르면
//        // IntelliJ IDEA이(가) 수정을 제안하는 것을 확인할 수 있습니다.
//        System.out.println("Hello and welcome!");
//
//        int num = 1;
//
//        switch(num){
//            case 1:
//                System.out.println("1번");
//                break;
//            case 2:
//                System.out.println("2번");
//                break;
//            case 3:
//                System.out.println("3번");
//                break;
//        }
//
//    }
//
//
//
//}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class q_1260 {
    public static int N;
    public static int M;
    public static int V;
    public static boolean[] visit;
    public static int[][] arr;
    public static StringBuilder sb = new StringBuilder();
    public static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        arr = new int[N+1][N+1];
        visit = new boolean[N+1];



        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[x][y] = arr[y][x] = 1;
        }

        dfs(V);
        Arrays.fill(visit, false);
        bfs(V);
        System.out.println(sb);
    }

    public static void dfs(int num) {
        visit[num] = true;
        sb.append(num).append(" ");
        for (int i = 1; i <= N; i++) {
            if(arr[num][i] == 1 && !visit[i]){
                dfs(i);
            }
        }
    }

    public static void bfs(int num) {
        q.add(num);
        visit[num] = true;
        sb.append('\n').append(num).append(" ");
        while (!q.isEmpty()) {
            int idx = q.poll();
            for (int i = 1; i <= N; i++) {
                if(arr[idx][i] == 1 && !visit[i]){
                    q.add(i);
                    visit[i] = true;
                    sb.append(i).append(" ");
                }

            }
        }


    }
}