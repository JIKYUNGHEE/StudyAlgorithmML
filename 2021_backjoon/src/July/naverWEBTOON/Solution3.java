package July.naverWEBTOON;

public class Solution3 {
    public int solution(String s, String t) {
        int result = 0;
        while (s.contains(t)) {
            s = s.replaceFirst(t, "");
            result++;
        }
        return result;
    }

    public static void main(String[] args) {
        String testA = "aabcbcd";
        String toA = "abc";

        String testB = "aaaaabbbbb";
        String toB = "ab";

        Solution3 s3 = new Solution3();
        System.out.println(s3.solution(testA, toA));
        System.out.println(s3.solution(testB, toB));
    }
}
