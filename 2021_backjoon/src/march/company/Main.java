package march.company;

import java.io.*;

class Main {
    static int n;
    static int[] pathArr;
    static int[] sumArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        n = Integer.parseInt(input);
        pathArr = new int[n];
        sumArr = new int[n];

        String[] paths = br.readLine().split("");
        for (int i = 0; i < n; i++) {
            pathArr[i] = Integer.parseInt(paths[i]);
        }

        sumArr[0] = pathArr[0];
        sumArr[1] = pathArr[1];

        for (int i = 2; i < n; i++) {
            if (pathArr[i] == 0) {
                sumArr[i] = 0;
            } else {
                sumArr[i] = sumArr[i - 2] + sumArr[i - 1];
            }
        }

        System.out.println(sumArr[n - 1]);
    }
}