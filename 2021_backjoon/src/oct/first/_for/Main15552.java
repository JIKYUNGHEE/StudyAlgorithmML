package oct.first._for;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main15552 {
    private static List<Integer> answers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        for(int i=0; i<testCase; i++) {
            String[] nums = br.readLine().split(" ");
            answers.add(Integer.parseInt(nums[0])+Integer.parseInt(nums[1]));
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < answers.size(); i++) {
            bw.write(answers.get(i));

            if(i != answers.size() -1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }
}
