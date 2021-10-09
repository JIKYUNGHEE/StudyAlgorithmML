package oct.first.inputs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2588 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());

        System.out.println(multiply1(a, b));
        System.out.println(multiply2(a, b));
        System.out.println(multiply3(a, b));
        System.out.println(multiply(a, b));
    }

    private static int multiply1(int a, int b) {
        return a * (b % 10);
    }

    private static int multiply2(int a, int b) {
        return a * ((b % 100) / 10);
    }

    private static int multiply3(int a, int b) {
        return a * (b / 100);
    }

    private static int multiply(int a, int b) {
        return a * b;
    }
}
