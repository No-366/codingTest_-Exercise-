import java.util.*;

public class b0511_19236 {
    static Fish[] fishByNumber = new Fish[17]; // 물고기 번호로 물고기 찾기
    static int[][] grid = new int[4][4]; // 격자 위치에 있는 물고기 번호
    static int result = 0;

    public static class Fish {
        int x, y, dir;
        boolean alive;

        public Fish(int x, int y, int dir, boolean alive) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.alive = alive;
        }
    }

    public static class Shark {
        int x, y, dir;

        public Shark(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    // dx, dy는 1~8 방향에 대한 좌표 변화량
    static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};

    public static void DFS(Shark shark, Fish[] fishByNumber, int[][] grid) {
        // 물고기 이동
        moveFishes(shark, fishByNumber, grid);

        // 상어 이동
        int sx = shark.x;
        int sy = shark.y;
        int dir = shark.dir;

        // 1칸부터 최대 3칸까지 이동 가능
        for (int dist = 1; dist <= 3; dist++) {
            int nx = sx + dx[dir] * dist;
            int ny = sy + dy[dir] * dist;

            // 격자 범위 확인
            if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4) {
                int fishNum = grid[nx][ny];

                // 물고기가 없는 칸은 건너뜀
                if (fishNum == 0) continue;

                // 물고기가 있는 경우, 그 물고기를 먹고 DFS 진행
                // 상태를 복사하여 새로운 DFS 호출
                Fish[] newFishByNumber = copyFishArray(fishByNumber);
                int[][] newGrid = copyGrid(grid);

                // 물고기를 먹고 상어 위치 및 방향 업데이트
                newFishByNumber[fishNum].alive = false;
                newGrid[nx][ny] = 0; // 물고기를 먹었으므로 격자에서 제거

                Shark newShark = new Shark(nx, ny, newFishByNumber[fishNum].dir);

                // 다음 단계 진행
                DFS(newShark, newFishByNumber, newGrid);
            } else {
                break; // 격자 밖으로 나갔다면 중단
            }
        }

        // 현재 상태에서 먹은 물고기 번호의 합 계산
        int sum = 0;
        for (int i = 1; i <= 16; i++) {
            if (fishByNumber[i] != null && !fishByNumber[i].alive) {
                sum += i;
            }
        }

        // 최댓값 갱신
        result = Math.max(result, sum);
    }

    public static void moveFishes(Shark shark, Fish[] fishByNumber, int[][] grid) {
        // 물고기 번호 1부터 16까지 순서대로 이동
        for (int i = 1; i <= 16; i++) {
            Fish fish = fishByNumber[i];

            // 물고기가 살아있다면 이동
            if (fish != null && fish.alive) {
                int dir = fish.dir;
                int x = fish.x;
                int y = fish.y;

                // 최대 8방향 확인
                for (int d = 0; d < 8; d++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];

                    // 이동 가능한 경우 (격자 내부, 상어가 없는 위치)
                    if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4 && (nx != shark.x || ny != shark.y)) {
                        // 이동할 위치에 다른 물고기가 있는 경우
                        int targetFishNum = grid[nx][ny];

                        if (targetFishNum > 0) {
                            // 다른 물고기와 위치 교환
                            Fish targetFish = fishByNumber[targetFishNum];

                            // 위치 정보 업데이트
                            fishByNumber[i].x = nx;
                            fishByNumber[i].y = ny;
                            targetFish.x = x;
                            targetFish.y = y;

                            // 격자 정보 업데이트
                            grid[nx][ny] = i;
                            grid[x][y] = targetFishNum;
                        } else {
                            // 이동할 위치가 빈 칸인 경우
                            fishByNumber[i].x = nx;
                            fishByNumber[i].y = ny;

                            // 격자 정보 업데이트
                            grid[nx][ny] = i;
                            grid[x][y] = 0;
                        }
                        break;
                    }

                    // 이동할 수 없다면 45도 반시계 방향으로 회전
                    dir = (dir % 8) + 1;
                    fishByNumber[i].dir = dir;

                    // 모든 방향을 다 시도했다면 이동 불가
                    if (d == 7) break;
                }
            }
        }
    }

    // Fish 배열 복사
    public static Fish[] copyFishArray(Fish[] original) {
        Fish[] copy = new Fish[17];
        for (int i = 1; i <= 16; i++) {
            if (original[i] != null) {
                copy[i] = new Fish(original[i].x, original[i].y, original[i].dir, original[i].alive);
            }
        }
        return copy;
    }

    // 격자 복사
    public static int[][] copyGrid(int[][] original) {
        int[][] copy = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                copy[i][j] = original[i][j];
            }
        }
        return copy;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 4x4 격자 정보 입력 받기
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int num = sc.nextInt(); // 물고기 번호
                int dir = sc.nextInt(); // 물고기 방향

                // 물고기 정보 저장
                fishByNumber[num] = new Fish(i, j, dir, true);
                grid[i][j] = num;
            }
        }

        // (0,0)의 물고기를 먹고 상어 초기화
        int firstFishNum = grid[0][0];
        int firstFishDir = fishByNumber[firstFishNum].dir;

        fishByNumber[firstFishNum].alive = false;
        grid[0][0] = 0; // 상어가 먹은 자리는 0으로 표시

        Shark shark = new Shark(0, 0, firstFishDir);

        // 먹은 물고기 번호 더하기
        result = firstFishNum;

        // DFS 시작
        DFS(shark, fishByNumber, grid);

        // 결과 출력
        System.out.println(result);

        sc.close();
    }
}