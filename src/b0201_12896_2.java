import java.util.*;
import java.io.*;

public class b0201_12896_2 {
    static int[][][] DP = new int[61][61][61]; // 최소 공격 횟수 저장
    static int[][] attack = {
            {9, 3, 1}, {9, 1, 3}, {3, 9, 1},
            {3, 1, 9}, {1, 9, 3}, {1, 3, 9}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] scv = new int[3];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < N; i++)
            scv[i] = Integer.parseInt(st.nextToken());

        System.out.println(bfs(scv));
    }

    static int bfs(int[] scv) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{scv[0], scv[1], scv[2], 0}); // {SCV1 체력, SCV2 체력, SCV3 체력, 공격 횟수}
        DP[scv[0]][scv[1]][scv[2]] = 0; // 초기 상태 방문 처리

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int hp1 = current[0], hp2 = current[1], hp3 = current[2], count = current[3];

            // 모든 SCV가 체력 0이면 정답 반환
            if (hp1 == 0 && hp2 == 0 && hp3 == 0)
                return count;

            // 6가지 공격 패턴 적용
            for (int[] atk : attack) {
                int nhp1 = Math.max(hp1 - atk[0], 0);
                int nhp2 = Math.max(hp2 - atk[1], 0);
                int nhp3 = Math.max(hp3 - atk[2], 0);

                // 방문하지 않은 상태라면 큐에 추가
                if (DP[nhp1][nhp2][nhp3] == 0) {
                    DP[nhp1][nhp2][nhp3] = count + 1;
                    queue.add(new int[]{nhp1, nhp2, nhp3, count + 1});
                }
            }
        }
        return -1; // 도달 불가능한 경우 (문제에서 이런 경우는 없음)
    }
}
