package oct.first.inputs;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main10869 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        System.out.println(add(inputs[0], inputs[1]));
        System.out.println(minus(inputs[0], inputs[1]));
        System.out.println(multiply(inputs[0], inputs[1]));
        System.out.println(divide(inputs[0], inputs[1]));
        System.out.print(remain(inputs[0], inputs[1]));
    }

    private static int add(String a, String b) {
        return Integer.parseInt(a) + Integer.parseInt(b);
    }

    private static int minus(String a, String b) {
        return Integer.parseInt(a) - Integer.parseInt(b);
    }

    private static int multiply(String a, String b) {
        return Integer.parseInt(a) * Integer.parseInt(b);
    }

    private static int divide(String a, String b) {
        return (Integer.parseInt(a) / Integer.parseInt(b));
    }

    private static int remain(String a, String b) {
        return (Integer.parseInt(a) % Integer.parseInt(b));
    }
}
