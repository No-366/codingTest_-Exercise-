import java.util.Arrays;
import java.util.Comparator;

public class _Basic__Arrays {
    static class Person{
        String name;
        int age;

        Person(String name, int age){
            this.name = name;
            this.age = age;
        }

        @Override // 자바의 모든 클래스는 자동으로 java.lang.Object를 상속받음
        public String toString(){
            return name + "(" + age + ")";
        }
    }

    public static void main(String[] args) {
        int [] arr = {5, 2, 9, 1, 3};
        String [] obj = {"banana","apple","cherry"};
        Integer [] numbers = {5, 2, 9, 1, 3};
        Person [] people = {
                new Person("Alice",30),
                new Person("Bob", 25),
                new Person("Charlie", 35),
        };
        int [][] twoD = {
                {3,2},
                {1,5},
                {4,0}
        };

        // 1) 기본 정렬
        int [] arr1 = arr.clone();
        Arrays.sort(arr1);
        System.out.println(Arrays.toString(arr1));

        // 2) 특정 구간만 정렬 (매개변수 주의 : 배열, 이상_idx, 미만_idx)
        int [] arr2 = arr.clone();
        Arrays.sort(arr2, 2, 5);
        System.out.println(Arrays.toString(arr2));

        // 3) 객체 배열 정렬 ( Comparable ) _ String, Integer, Double : 사전순, 숫자 오름차순
        String [] obj2 = obj.clone();
        Arrays.sort(obj2);
        System.out.println(Arrays.toString(obj2));

        // 4) 객체 배열 사용자 정의 정렬 ( Comparator )
        Integer [] numbers2 = numbers.clone();
        //Arrays.sort(numbers2,Comparator.reverseOrder());
        Arrays.sort(numbers2, 2,5, Comparator.reverseOrder());
        System.out.println(Arrays.toString(numbers2));

        // 5) 사용자 정의 클래스 정렬
        Person [] people2 = people.clone();

        // 5-1) 기본 방식 : Comparator 익명 클래스 사용
        Arrays.sort(people2, new Comparator<Person>(){
            @Override
            public int compare(Person p1, Person p2) {
                return p1.age - p2.age; // 매개변수 순서와 동일 -> 오름 차순
                //return p2.age - p1.age; // 매개변수 순서와 반대 -> 내림차순
            }
        });

        // 5-2) 람다 표현식
        Arrays.sort(people, (p1,p2) -> p2.age - p1.age); // 내림차순
        Arrays.sort(people, (p1,p2) -> p1.age - p2.age); // 오름차순

        // 5-3) Comparator의 유틸 메서드 + 람다 표현식 사용
        Arrays.sort(people2, Comparator.comparingInt(p->p.age));
        Arrays.sort(people2, Comparator.<Person>comparingInt(p->p.age).reversed());

        System.out.println(Arrays.toString(people2));

        // 6) 2차원 배열 정렬
        int [][] twoD2 = new int [3][2];
        for(int i = 0; i<3 ; i++){
                twoD2[i] = twoD[i];
        }
        for (int[] row : twoD2) {
            System.out.println(Arrays.toString(row));
        }
        Arrays.sort(twoD2, Comparator.comparingInt(a -> a[0]));

        for(int[] row : twoD2){
            System.out.println(Arrays.toString(row));
        }
    }
}
