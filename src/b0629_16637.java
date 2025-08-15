import java.util.*;

public class b0629_16637 {
    static int N;
    static int op;
    static char[] str;
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = Integer.parseInt(sc.nextLine()); //첫 줄 읽고
        str = sc.nextLine().toCharArray();
        op = N/2;

        //
        prnth(0, str[0]-'0');

        System.out.println(ans);
    }

    public static void prnth(int idx, int val){

        //1. 마지막(또는 그 이상)에 다다르면 탈출
        if(idx == N-1){
            if(val>ans){
                ans = val;
            }
            return;
        }

        //2. 다음 피연산자가 괄호에 속하지 않음
        // 현재 값과 다음 값 연산 후 prnth(현위치+2, 연산결과값1)
        if(idx+2 <= N-1){
            int val1 = opr(val, str[idx+2]-'0', str[idx+1]);
            prnth(idx+2, val1);
        }

        //3. 다음 피연산자가 괄호에 속함
        // 다음값과 다다음값을 연산 후에 현재 값과 연산 후 prnth(현위치+4, 연산결과값2)
        if(idx+4 <= N-1){
            int val_temp = opr(str[idx+2]-'0', str[idx+4]-'0', str[idx+3]);
            int val2 = opr(val, val_temp, str[idx+1]);
            prnth(idx+4, val2);
        }
    }

    public static int opr(int n1, int n2, char op){
        return switch (op) {
            case '+' -> n1+n2;
            case '-' -> n1-n2;
            case '*' -> n1*n2;
            default -> 0;
        };
    }


}
