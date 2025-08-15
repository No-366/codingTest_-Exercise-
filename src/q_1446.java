import java.util.*;

public class q_1446 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력: 지름길의 개수와 고속도로의 길이
        int n = sc.nextInt();
        int d = sc.nextInt();

        // 그래프 초기화: 각 위치에 연결된 경로 저장
        List<int[]>[] graph = new ArrayList[d + 1];
        for (int i = 0; i <= d; i++) {
            graph[i] = new ArrayList<>();
        }

        // 지름길 정보 입력
        for (int i = 0; i < n; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int cost = sc.nextInt();
            if (end <= d) { // 고속도로를 넘는 경로는 무시
                graph[start].add(new int[]{end, cost});
            }
        }

        // 최단 거리 배열 초기화
        int[] dist = new int[d + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        // 우선순위 큐 (최소 비용 기준)
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{0, 0}); // {현재 위치, 현재 비용}

        // 다익스트라 알고리즘
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int pos = cur[0];
            int cost = cur[1];

            // 더 적은 비용으로 이미 처리된 경우 무시
            if (cost > dist[pos]) continue;

            // 일반 도로 이동
            if (pos + 1 <= d && cost + 1 < dist[pos + 1]) {
                dist[pos + 1] = cost + 1;
                pq.add(new int[]{pos + 1, cost + 1});
            }

            // 지름길 이동
            for (int[] edge : graph[pos]) {
                int nextPos = edge[0];
                int nextCost = cost + edge[1];
                if (nextCost < dist[nextPos]) {
                    dist[nextPos] = nextCost;
                    pq.add(new int[]{nextPos, nextCost});
                }
            }
        }

        // 결과 출력
        System.out.println(dist[d]);
    }
}
