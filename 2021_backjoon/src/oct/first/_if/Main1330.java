package oct.first._if;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main1330 {
    private static String OPERATOR_BIGGER_A_THAN_B = ">";
    private static String OPERATOR_EQUAL = "==";
    private static String OPERATOR_SMALLER_A_THAN_B = "<";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line1 = br.readLine().split(" ");

        int a = Integer.parseInt(line1[0]);
        int b = Integer.parseInt(line1[1]);

        System.out.print(compare(a, b));

    }

    private static String compare(int a, int b) {
        return a > b ? OPERATOR_BIGGER_A_THAN_B : (a < b ? OPERATOR_SMALLER_A_THAN_B : OPERATOR_EQUAL);
    }
}
