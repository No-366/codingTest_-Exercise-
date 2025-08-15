import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
        // 메인 메소드의 경우 어디서든 호출이 가능해야하므로 public으로 공개 범위 설정
        // static method : 클래스의 인스턴스와 관계없이 클래스 자체에서 호출할 수 있는 메소드
        // 매개 변수 String[] args : 자바의 main 메서드에서 형식적으로 고정된 매개변수 타입이기 때문에 다른 타입으로 변경 불가
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter your name:"); // 사용자에게 입력 요청
        String name = br.readLine(); // 입력 받기
        System.out.println("Hello, " + name + "!"); // 출력
        int a = Integer.parseInt(br.readLine());
        System.out.println("Input number is "+a);
    }
}
