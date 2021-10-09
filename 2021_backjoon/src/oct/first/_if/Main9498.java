package oct.first._if;

import java.util.Scanner;

/**
 * 시험성적
 * <p>
 * 시험 점수를 입력받아 90 ~ 100점은 A, 80 ~ 89점은 B, 70 ~ 79점은 C, 60 ~ 69점은 D, 나머지 점수는 F를 출력하는 프로그램을 작성하시오.
 */
public class Main9498 {
    private static String A = "A";
    private static String B = "B";
    private static String C = "C";
    private static String D = "D";
    private static String F = "F";

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int score = sc.nextInt();
        System.out.print(evaluateScore(score));
    }

    private static String evaluateScore(int score) throws Exception {
        if (score > 100) {
            throw new Exception("this score is lie");
        }
        if (score >= 90) {
            return A;
        } else if (score >= 80) {
            return B;
        } else if (score >= 70) {
            return C;
        } else if (score >= 60) {
            return D;
        } else {
            return F;
        }
    }
}
