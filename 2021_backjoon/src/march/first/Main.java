package march.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int n;
    private static int[] list = new int[1000000];
    private static int b;
    private static int c;

    private static long result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            list[i] = Integer.parseInt(st1.nextToken());
        }

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        b = Integer.parseInt(st2.nextToken());
        c = Integer.parseInt(st2.nextToken());

        for (int a : list) {
            int cal = (a - b) / c;
            int subNum = (a - b) % c == 0 ? cal : cal + 1;

            result += subNum + 1;
        }

        System.out.println(result);
    }
}