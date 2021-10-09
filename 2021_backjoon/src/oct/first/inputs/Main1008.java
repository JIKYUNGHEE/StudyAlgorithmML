package oct.first.inputs;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main1008 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        System.out.println(divide(inputs[0], inputs[1]));
    }

    private static double divide(String a, String b) {
        return (Integer.parseInt(a) / (double) Integer.parseInt(b));
    }
}
