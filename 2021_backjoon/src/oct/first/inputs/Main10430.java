package oct.first.inputs;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main10430 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");

        int a = Integer.parseInt(inputs[0]);
        int b = Integer.parseInt(inputs[1]);
        int c = Integer.parseInt(inputs[2]);

        System.out.println(calculate1(a, b, c));
        System.out.println(calculate2(a, b, c));
        System.out.println(calculate3(a, b, c));
        System.out.print(calculate4(a, b, c));
    }

    private static int calculate1(int a, int b, int c) {
        return (a + b) % c;
    }

    private static int calculate2(int a, int b, int c) {
        return ((a % c) + (b % c)) % c;
    }

    private static int calculate3(int a, int b, int c) {
        return (a * b) % c;
    }

    private static int calculate4(int a, int b, int c) {
        return ((a % c) * (b % c)) % c;
    }
}
