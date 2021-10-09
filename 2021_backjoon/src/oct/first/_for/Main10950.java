package oct.first._for;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main10950 {
    private static List<Integer> answerList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCase; i++) {
            String[] testNums = br.readLine().split(" ");
            int a = Integer.parseInt(testNums[0]);
            int b = Integer.parseInt(testNums[1]);

            answerList.add(a+b);
        }

        int index = 0;
        for (Integer answer : answerList) {
            System.out.print(answer);

            if(index != answerList.size() - 1) {
                System.out.println();
            }

            index++;
        }
    }
}
