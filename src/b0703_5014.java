import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b0703_5014 {

    static class Node{
        int floor;
        int btnCount;

        Node(int floor, int btnCount){
            this.floor = floor;
            this.btnCount = btnCount;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int F = sc.nextInt();// 총 층 수
        int S = sc.nextInt();// 현 위치
        int G = sc.nextInt();// 목적지
        int U = sc.nextInt();// 위층 버튼
        int D = sc.nextInt();// 아래층 버튼

        //출발지와 도착지가 동일한 경우
        if(S == G){
            System.out.println(0);
            return;
        }

        boolean[] visited = new boolean[F+1]; // 그냥 1~F까지 그대로 사용하기 위해
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(S,0));
        visited[S] = true;

        while(!queue.isEmpty()){

            Node current = queue.poll();
            int floor = current.floor;
            int count = current.btnCount;

            //위로 가기
            int up = floor + U;
            if(up == G){
                System.out.println(count+1);
                return;
            }
            if(isBounded(up,F) && !visited[up]){
                visited[up] = true;
                queue.offer(new Node(up,count+1));
            }

            //아래로 가기
            int down = floor - D;
            if(down == G){
                System.out.println(count+1);
                return;
            }
            if(isBounded(down,F) && !visited[down]){
                visited[down] = true;
                queue.offer(new Node(down,count+1));
            }


        }

        System.out.println("use the stairs");
    }

    private static boolean isBounded(int floor, int max){
        return floor >= 1 && floor <= max;
    }
}
