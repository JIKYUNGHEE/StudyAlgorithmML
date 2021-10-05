package jun.thrid;

import java.util.*;

public class BoostCamp {

    static class Solution {
        public String[] solution(String[] param0) {
            List<String> fileNameList = new LinkedList<>();
            Map<String, Integer> countOverlapMap = new HashMap<>();
            for (String s : param0) {
                String[] dirs = s.split("/");
                String totalFileName = dirs[dirs.length - 1];
                if (totalFileName.contains("_")) {
                    String[] fileNamesWithSuffix = totalFileName.split("\\.");
                    String[] fileNames = fileNamesWithSuffix[0].split("_");
                    fileNameList.add(fileNames[0] + "." + fileNamesWithSuffix[fileNamesWithSuffix.length - 1]);
                } else {
                    fileNameList.add(totalFileName);
                }
            }

            for (String s : fileNameList) {
                int count = 0;
                if (countOverlapMap.containsKey(s)) {
                    count = countOverlapMap.get(s);
                }
                countOverlapMap.put(s, ++count);
            }

            List<String> answerList = new ArrayList<>();
            for (String s : fileNameList) {
                if(countOverlapMap.containsKey(s)) {
                    int overlapCnt = countOverlapMap.get(s);
                    if(overlapCnt > 1) {
                        answerList.add(s);
                        answerList.add(Integer.toString(overlapCnt));
                        countOverlapMap.remove(s);
                    }
                }
            }

            int size = answerList.size();
            String[] answer = new String[size];
            int index = 0;
            for (String s : answerList) {
                answer[index] = s;
                index++;
            }

            for (int i=0; i<answer.length; i++) {
                System.out.print(answer[i]+" ");
            }

            return answer;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] testCaseString = new String[6];
        testCaseString[0] = "/a/a_v2.x";
        testCaseString[1] = "/b/a.x";
        testCaseString[2] = "/c/t.z";
        testCaseString[3] = "/d/a/t.x";
        testCaseString[4] = "/e/z/t_v1.z";
        testCaseString[5] = "/k/k/k/a_v9.x";
        solution.solution(testCaseString);
    }

}
