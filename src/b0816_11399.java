import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class b0816_11399 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //List<Integer> arr = new ArrayList<Integer>();
        int [] arr;

        StringTokenizer st ;

        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        int sum;

        for (int i = 0; i < N; i++) {
          arr[i] = (Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(arr);

        sum = arr[0];
        for (int i = 0; i < N-1; i++) {
            arr[i+1] = arr[i] + arr[i+1];
            sum += arr[i+1];
        }

        System.out.println(sum);

    }
}
