import java.io.*;
import java.util.*;

public class Main {
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i<n ; i++){
            String s = br.readLine();
            cnt = 0;
            int result = isPalindrome(s);
            System.out.println(result + " " + (cnt+1));
        }


    }

    static int isPalindrome(String s){
      return recursion(s, 0, s.length()-1);
    }

    static int recursion(String s , int l, int r){
        if(l>=r) return 1;
        else if(s.charAt(l)!=s.charAt(r)) return 0;
        else {
            cnt++;
            return recursion(s, l+1, r-1);
        }
    }

}