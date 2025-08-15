import java.util.*;

public class programers_43164 {
    // DFS 탐색을 위한 메소드
    private boolean dfs(String current, Map<String, List<String>> routes, List<String> path, int totalTickets) {
        path.add(current);  // 현재 공항을 경로에 추가 // 재귀시에도 같은 객체를 참조하므로 next들이 경로상에 추가된다.

        // 모든 항공권을 사용했을 때 경로가 완성되면 true 반환
        if (path.size() == totalTickets) {
            return true;
        }

        // 현재 공항에서 출발할 수 있는 도착지 리스트가 있는 경우
        if (routes.containsKey(current)) {
            List<String> destinations = routes.get(current);

            // 가능한 도착지들을 순차적으로 탐색
            for (int i = 0; i < destinations.size(); i++) {
                String next = destinations.get(i);

                // 해당 도착지를 사용하고 리스트에서 제거
                destinations.remove(i); // routes맵에서 참조된 리스트 객체이기 때문에 동일한 객체를 참조하므로, 도착지 리스트에서 제거하면 맵에서도 제거된다.
                if (dfs(next, routes, path, totalTickets)) {
                    return true;  // 경로를 완성하면 종료
                }
                // 실패하면 다시 도착지를 복원 (백트래킹)
                destinations.add(i, next);
            }
        }

        // 경로가 잘못되었을 경우 마지막 공항을 경로에서 제거 (백트래킹)
        path.remove(path.size() - 1);
        return false;
    }

    public String[] solution(String[][] tickets) {
        // 경로 저장을 위한 맵 (출발지 -> 도착지 리스트)
        Map<String, List<String>> routes = new HashMap<>();

        // 티켓 정보를 맵에 저장
        for (String[] ticket : tickets) {
            String from = ticket[0];
            String to = ticket[1];
            routes.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
        }

        // 각 출발지의 도착지 리스트를 알파벳 순으로 정렬
        for (List<String> destList : routes.values()) {
            Collections.sort(destList);
        }

        // 최종 경로를 저장할 리스트
        List<String> path = new ArrayList<>();
        int totalTickets = tickets.length + 1;  // 총 경로의 길이는 티켓 수 + 1 ("ICN"에서 시작)

        // DFS 탐색을 통해 경로 찾기 시작
        dfs("ICN", routes, path, totalTickets);

        // 리스트를 배열로 변환하여 반환
        return path.toArray(new String[0]);
    }

    public static void main(String[] args) {
        programers_43164 sol = new programers_43164();

        // 테스트 케이스 1
        String[][] tickets1 = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        System.out.println(Arrays.toString(sol.solution(tickets1)));
        // 출력: ["ICN", "JFK", "HND", "IAD"]

        // 테스트 케이스 2
        String[][] tickets2 = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
        System.out.println(Arrays.toString(sol.solution(tickets2)));
        // 출력: ["ICN", "ATL", "ICN", "SFO", "ATL", "SFO"]
    }
}
