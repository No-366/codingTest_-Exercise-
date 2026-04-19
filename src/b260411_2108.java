import java.util.*;
import java.io.*;

public class b260411_2108 {
    static int Max = Integer.MIN_VALUE;
    static int Min = Integer.MAX_VALUE;
    static int sum = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        HashMap<Integer, Integer> freq = new HashMap<>();
        ArrayList<Integer> middle = new ArrayList<>();


        for(int i = 0; i<N; i++){
            int num = Integer.parseInt(br.readLine());
            Max = Math.max(Max, num);
            Min = Math.min(Min, num);
            sum += num;
            freq.put(num, freq.getOrDefault(num,0)+1);
            middle.add(num);
        }
        Collections.sort(middle);

        //해시맵을 리스트로 만든다
        //리스트의 각 요소의 값을 기준으로 정렬하고 같으면 키를 기준으로 정렬한다
        LinkedList<Map.Entry<Integer, Integer>> list = new LinkedList<>(freq.entrySet());
        list.sort((o1, o2) -> {
            if(o1.getValue().equals(o2.getValue())){
                return o1.getKey() - o2.getKey();
            }
            return o2.getValue() - o1.getValue();
        });

        int freq_result = list.get(0).getKey();
        if(freq.size() > 1 && list.get(0).getValue().equals(list.get(1).getValue())){
            freq_result = list.get(1).getKey();
        }


        System.out.println(Math.round((double)sum/N));
        System.out.println(middle.get(N/2));
        System.out.println(freq_result);
        System.out.println(Max-Min);
    }
}
