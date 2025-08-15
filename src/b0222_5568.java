import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class b0222_5568 {
    static int n,k;
    static int[] arr;
    static boolean[] visited;
    static HashSet<String> map = new HashSet<>(); // 해시맵을 이용해서 중복 제거
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        arr = new int[n];
        visited = new boolean[n];
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        dfs("",0);
        System.out.println(map.size());

    }
    static void dfs(String s, int d){ // 초기 문자열과
        if(d==k){
            map.add(s); // 해시맵을 이용한 중복 방지
            return;
        }

        for(int i=0;i<n;i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(s + arr[i],d+1);
                visited[i] = false;
            }
        }
    }
}