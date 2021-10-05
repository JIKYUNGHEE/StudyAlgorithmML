package July.naverWEBTOON;

import java.util.LinkedList;
import java.util.List;

public class Solution2 {
    private List<String> answerHeadArray = new LinkedList<>();

    public String[] solution(String s) {
        while (!s.isEmpty()) {
            String[] stringArray = s.split("");
            int indexHead = 0;
            int indexTail = s.length() - 1;
            int deleteNum = 0;

            String head = stringArray[indexHead];
            int indexLast = s.lastIndexOf(head);
            String sameWord = "";

            if (indexLast == indexTail) {  // 맨 마지막 문자와 동일 할 때
                deleteNum = 1;
                sameWord = head;
            } else {                     //동일하지 않을 때
                deleteNum = indexTail - indexLast + 1;  //동일한지 비교할 문자 개수
                String s1 = s.substring(indexHead, indexHead + deleteNum);
                String s2 = s.substring(indexLast, indexLast + deleteNum);
                if (s1.equals(s2)) {
                    sameWord = s1;
                } else {    //동일한 문자가 없음
                    break;
                }
            }

            answerHeadArray.add(sameWord);
            indexHead += deleteNum;
            indexTail = indexLast;
            if (indexHead > indexTail) {
                break;
            }

            s = s.substring(indexHead, indexTail);
        }

        int aSize = answerHeadArray.size();
        int len = (s.length() > 0)? 1 : 0;
        String[] answer = new String[aSize * 2 + len];
        if (aSize > 0) {
            for (int i = 0; i < answer.length; i++) {
                if (i < aSize) {
                    answer[i] = answerHeadArray.get(i);
                } else if (i > aSize + s.length()) {
                    answer[i] = answerHeadArray.get(answer.length - i + 1);
                } else {
                    answer[i] = s;
                }
            }
        } else {
            answer[0] = s;
        }

        return answer;
    }

    public static void main(String[] args) {
        String test1 = "abcxyasdfasdfxyabc";
        String test2 = "abcxyqwertyxyabc";
        String test3 = "abcabcabcabc";
        String test4 = "llttaattll";
        String test5 = "zzzzzz";
        String test6 = "abcdef";

        Solution2 s2 = new Solution2();

        System.out.println(s2.solution(test1).toString());
        System.out.println(s2.solution(test2).toString());
        System.out.println(s2.solution(test3).toString());
        System.out.println(s2.solution(test4));
        System.out.println(s2.solution(test5));
        System.out.println(s2.solution(test6));
    }
}
