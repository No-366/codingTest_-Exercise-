import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b0706_9205 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int testCaseNum = sc.nextInt();
        for(int i = 0; i < testCaseNum; i++){
            int conNum = sc.nextInt(); //편의점 개수
            Node [] nodes = new Node[conNum+2];
            nodes[0] = new Node(sc.nextInt(), sc.nextInt(),0); //출발지 좌표
            for(int j = 0; j < conNum; j++){ //편의점 좌표
                int x = sc.nextInt();
                int y = sc.nextInt();
                nodes[j+1] = new Node(x,y,j+1);
            }
            nodes[conNum+1] = new Node(sc.nextInt(), sc.nextInt(),conNum+1); //목적지 좌표

            Queue <Node> queue = new LinkedList<>();
            queue.add(nodes[0]);

            boolean [] visited = new boolean[conNum+2];

            boolean success = false;
            while(!queue.isEmpty()){
                Node cur = queue.poll();
                visited[cur.num] = true;

                if(dist(cur,nodes[conNum+1])){
                    success = true;
                    break;
                }

                for(Node node : nodes){
                    if(dist(cur,node) && !visited[node.num]){
                        queue.add(node);
                    }
                }
            }
            if(success){
                System.out.println("happy");
            }else {
                System.out.println("sad");
            }
        }
    }

    public static class Node{
        int x;
        int y;
        int num;

        public Node(int x, int y, int num){
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    public static boolean dist(Node start, Node end){
        int distance = Math.abs(end.x - start.x) + Math.abs(end.y - start.y);
        return distance <= 1000;
    }
}
