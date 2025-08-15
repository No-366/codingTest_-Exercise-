import java.util.*;

class Solution {
    public class Node {
        int num;    // 노드 번호
        int cost;   // 1부터 이 노드까지 걸리는 비용

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }

    public int solution(int n, int[][] edge) {
        // 인접 리스트 생성
        List<Integer>[] graph = new ArrayList[n+1];
        for(int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 간선 정보 저장 (양방향)
        for(int[] e : edge) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        // 방문 여부 체크 배열
        boolean[] visited = new boolean[n+1];

        // BFS 탐색을 위한 큐
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(1, 0));
        visited[1] = true;

        int maxCost = 0;    // 최대 거리
        int count = 0;      // 최대 거리에 있는 노드 개수

        // BFS 탐색
        while(!queue.isEmpty()) {
            Node current = queue.poll();
            int currentNum = current.num;
            int currentCost = current.cost;

            // 최대 거리 갱신 및 카운트
            if(maxCost < currentCost) {
                maxCost = currentCost;
                count = 1;
            } else if(maxCost == currentCost) {
                count++;
            }

            // 인접 노드 탐색
            for(int nextNum : graph[currentNum]) {
                if(!visited[nextNum]) {
                    visited[nextNum] = true;
                    queue.add(new Node(nextNum, currentCost + 1));
                }
            }
        }

        return count;
    }
}