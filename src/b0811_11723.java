import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;
import java.util.HashSet;


public class b0811_11723 {

    static HashSet<Integer> set = new HashSet<>();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i<N ; i++){
            String [] inst = br.readLine().split(" ");
            String opt = inst[0];
            int opd = -1;
            if(inst.length > 1) {
                opd = Integer.parseInt(inst[1]);
            }

            compute(opt, opd);

        }

        bw.flush();
        bw.close();

    }

    private static void compute(String opt, int opd) throws IOException {
        if(opt.equals("add")){
            set.add(opd);
        } else if (opt.equals("remove")) {
            set.remove(opd);

        } else if (opt.equals("check")) {
            int result = set.contains(opd) ? 1 : 0;
            bw.write(result + "");
            bw.newLine();

        } else if (opt.equals("toggle")) {
            if(!set.add(opd)){
                set.remove(opd);
            }

        } else if (opt.equals("all")) {
            for(int i = 1 ; i<=20 ; i++){
                set.add(i);
            }

        } else if (opt.equals("empty")) {
            set.clear();
        }
    }
}
