package oct.first._for;

import java.util.Scanner;

/**
 * 구구단
 */
public class Main2739 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();

        printMultiplyTable(number);
    }

    private static void printMultiplyTable(int number) {
        for (int i = 0; i < 9; i++) {
            System.out.print(number + " * " + (i + 1) + " = " + number * (i + 1));

            if (i != 8) {
                System.out.println();
            }
        }
    }
}
