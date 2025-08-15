import java.io.*;
import java.util.*;

public class b0504_14502 {

    static final int dx[] = {0,0,1,-1};  //상하좌우 방향 설정
    static final int dy[] = {1,-1,0,0};  //상화좌우 방향 설정
    static int originalMap[][];
    static int n,m;
    static int maxSafeZone = Integer.MIN_VALUE; //안전영역 최대값을 찾기 위한 최소값 설정

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        originalMap = new int[n][m]; // 연구소 초기 상태 저장용 맵

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                originalMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);

        System.out.println(maxSafeZone);
    }

    static void dfs(int wallCnt) {
        //벽이 3개가 설치 된 경우 bfs 탐색 시작
        if(wallCnt == 3) {
            bfs();
            return;
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(originalMap[i][j] == 0) {
                    originalMap[i][j] = 1; // 벽 설치
                    dfs(wallCnt+1);
                    originalMap[i][j] = 0; // 벽 제거 --> 백트래킹
                }
            }
        }
    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();

        //모든 바이러스 위치를 큐에 추가
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(originalMap[i][j] == 2) {
                    q.add(new Node(i,j));
                }
            }
        }

        //원본 연구소를 바꾸지 않기 위한 카피맵 사용
        int copyMap[][] = new int[n][m];

        for (int i = 0; i < n; i++) {
            copyMap[i] = originalMap[i].clone();
        }

        //BFS 탐색 시작
        while(!q.isEmpty()) {
            Node now = q.poll();
            int x = now.x; // 현재 값
            int y = now.y; //

            //상하좌우 바이러스 전염
            for(int k=0; k<4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                //주어진 범위내에서 빈칸이면 전염
                if(0<=nx && nx<n && 0<=ny && ny<m) {
                    if(copyMap[nx][ny] == 0) {
                        q.add(new Node(nx,ny));
                        copyMap[nx][ny] = 2; // 바이러스 표시
                    }
                }
            }
        }

        //SafeZone 확인
        funcSafeZone(copyMap);
    }

    //마지막 안전 구역 개수 구하기_0인 부분 개수 세기
    private static void funcSafeZone(int[][] copyMap) {
        int safeZone =0;
        for(int i=0; i<n ; i++) {
            for(int j=0; j<m; j++) {
                if(copyMap[i][j] == 0) {
                    safeZone++;
                }
            }
        }
        if (maxSafeZone < safeZone) {
            maxSafeZone = safeZone;
        }
    }

    //Queue에 좌표값 x,y를 넣기 위함.
    static class Node {
        int x;
        int y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}