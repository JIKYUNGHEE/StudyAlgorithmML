package July.naverWEBTOON;

public class Solution3_stupid {
    public int solution(String s, String t) {
        int result = 0;

        String[] sArray = s.split("");
        String[] tArray = t.split("");

        while (true) {
            int findLastIndex = findOverlapStringIndex(sArray, tArray);
            if(findLastIndex == -1) {
                break;
            }
            result++;
            //삭제한다.
            int cnt = tArray.length;
        }


        return result;
    }

    private int findOverlapStringIndex(String[] sArray, String[] tArray) {
        int findLastIndex = -1;
        int tIndex = 0;

        for (int i = 0; i < sArray.length; i++) {
            String s1 = sArray[i];
            String t1 = tArray[tIndex];

            if (!s1.equals(t1)) {
                tIndex = 0;
                continue;
            } else {
                tIndex++;
            }

            if (tIndex == tArray.length) {
                findLastIndex = i;
                break;
            }
        }

        return findLastIndex;
    }

    public static void main(String[] args) {
        String testA = "aabcbcd";
        String toA = "abc";

        String testB = "aaaaabbbbb";
        String toB = "ab";

        Solution3_stupid s3 = new Solution3_stupid();
        System.out.println(s3.solution(testA, toA));
        System.out.println(s3.solution(testB, toB));
    }
}
