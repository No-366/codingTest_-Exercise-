import java.io.*;
import java.util.*;

public class b0812_1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        HashSet<String> set = new HashSet<>();
        List<String> commonList = new ArrayList<>();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            String name = br.readLine();
            set.add(name);
        }

        for(int i = 0; i < M; i++){
            String name = br.readLine();
            if(set.contains(name)){
               commonList.add(name);
            }
        }

        Collections.sort(commonList);

        bw.write(commonList.size() +"\n" + String.join("\n", commonList));
        bw.flush();
        bw.close();

    }
}
