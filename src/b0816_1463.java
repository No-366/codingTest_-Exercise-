import java.util.Arrays;
import java.util.Scanner;

public class b0816_1463 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int [] arr = new int[n+1];
        Arrays.fill(arr,Integer.MAX_VALUE);

        arr[0] = 0;
        arr[1] = 0;
        for(int i = 1; i < n; i++){

            int add = arr[i] + 1;

            arr[i+1] = Math.min(arr[i+1], add);
            if(i*2 <= n) arr[i*2] = Math.min(arr[i*2], add);
            if(i*3 <= n) arr[i*3] = Math.min(arr[i*3], add);

        }

        System.out.println(arr[n]);

    }
}
