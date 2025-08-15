import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class b0316_4485 {

    //격자 위치, 해당 위치 까지의 비용 저장
    static class Node implements Comparable<Node> {
        int x, y, cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int test_case = 1;
        while (true) {
            String input = br.readLine();
            if (input == null || input.equals("0")) break;

            int N = Integer.parseInt(input);
            int[][] map = new int[N][N];
            int[][] dist = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }

            dist[0][0] = map[0][0];

            int[] dx = {1, 0, -1, 0};
            int[] dy = {0, 1, 0, -1};

            // 우선순위 큐 초기화 및 시작점 추가
            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(new Node(0, 0, map[0][0]));

            //다익스트라 루프
            while (!pq.isEmpty()) {
                Node node = pq.poll(); // 가장 비용이 적은 노드 추출 (우선 순위 큐 사용시 자동으로 우선 추출)
                int x = node.x;
                int y = node.y;
                int cost = node.cost;

                // 이미 더 적은 비용으로 방문 했다면 넘어감
                if (dist[x][y] < cost) continue;

                for (int i = 0; i < 4; i++) { // 네 방향 이동 시도
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    // 격자 범위 제한
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    // 새로운 비용 계산 및 갱신
                    int newCost = cost + map[nx][ny];
                    if (newCost < dist[nx][ny]) {
                        dist[nx][ny] = newCost;
                        pq.offer(new Node(nx, ny, newCost));
                    }
                }
            }

            sb.append("Problem ").append(test_case++).append(": ").append(dist[N - 1][N - 1]).append('\n');
        }

        System.out.print(sb);
    }
}
