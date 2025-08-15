import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class pro_43163 {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        int edge = 0;
        boolean found = Arrays.asList(words).contains(target);
        int[][] arr = new int[words.length+1][words.length+1];

        if(!found){
            return 0;
        }

        //arr[0] == begin
        for(int i = 0 ; i<words.length ; i++){

            int diff = 0;
            for(int j = 0; j<begin.length();j++){
                if(begin.charAt(j)!=words[i].charAt(j)){
                    diff ++;
                }
            }
            if(diff == 1 ){
                edge++;
                arr[0][i+1] = arr[i+1][0] = 1;
            }

        }
        //arr[1~] == words의 요소들
        for(int i = 0 ; i < words.length ; i++){
            for(int k = i+1 ; k<words.length ; k++){
                int diff = 0;

                for(int j = 0 ; j<begin.length(); j++){
                    if(words[i].charAt(j)!=words[k].charAt(j)){
                        diff ++;
                    }
                }

                if(diff == 1){
                    edge++;
                    arr[i+1][k+1] = arr[k+1][i+1] = 1;
                }

            }
        }
        //인접행렬 출력
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }


        //bfs 결과 반환
        return bfs(arr, words, target);
    }

    // BFS 탐색 함수
    private int bfs(int[][] adjMatrix, String[] words, String target) {
        Queue<Integer> queue = new LinkedList<>();// 정수형을 담는 큐를 링크드리스트로 생성
        boolean[] visited = new boolean[adjMatrix.length];  // 방문 체크 배열[수 = begin + words]
        queue.add(0);  // begin에서 출발 (인덱스 0)
        visited[0] = true;  // begin 방문 처리
        int level = 0;  // 단계 추적

        while (!queue.isEmpty()) {
            int size = queue.size();  // 현재 단계에서 탐색할 노드 수
            for (int i = 0; i < size; i++) {
                int current = queue.poll();  // 현재 탐색 중인 노드

                // 현재 탐색 노드와 타겟이 동일한 경우 거리를 반환하며 종료
                if (current > 0 && words[current - 1].equals(target)) {
                    return level;
                }

                // 인접 행렬을 탐색하여 변환 가능한 단어들을 큐에 추가
                for (int next = 0; next < adjMatrix.length; next++) {
                    if (adjMatrix[current][next] == 1 && !visited[next]) {
                        visited[next] = true;  // 방문 처리
                        queue.add(next);  // 큐에 추가
                    }
                }
            }
            level++;  // 한 단계가 끝나면 level 증가
        }

        return 0;  // target에 도달할 수 없는 경우
    }
}

