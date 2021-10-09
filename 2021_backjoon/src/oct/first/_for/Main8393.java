package oct.first._for;

import java.util.Scanner;

public class Main8393 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        int answer = 0;
        for (int i = 0; i < num; i++) {
            answer += (i + 1);
        }
        System.out.print(answer);
    }
}
