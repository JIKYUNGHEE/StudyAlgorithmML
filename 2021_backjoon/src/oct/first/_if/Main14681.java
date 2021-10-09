package oct.first._if;

import java.util.Scanner;

/**
 * 사분면고르기
 */
public class Main14681 {
    private static final int quadrant1 = 1;
    private static final int quadrant2 = 2;
    private static final int quadrant3 = 3;
    private static final int quadrant4 = 4;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        System.out.println(getQuadrant(a, b));
    }

    private static int getQuadrant(int a, int b) {
        if (a > 0 && b > 0) {
            return quadrant1;
        }
        if (a < 0 && b > 0) {
            return quadrant2;
        }

        if (a < 0 && b < 0) {
            return quadrant3;
        }

        if (a > 0 && b < 0) {
            return quadrant4;
        }

        return quadrant1;
    }
}
