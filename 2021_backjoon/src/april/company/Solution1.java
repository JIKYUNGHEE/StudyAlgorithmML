package april.company;

import java.util.*;

/**
 * lottos	win_nums	result
 * [44, 1, 0, 0, 31, 25]	[31, 10, 45, 1, 6, 19]	[3, 5]
 * [0, 0, 0, 0, 0, 0]	[38, 19, 20, 40, 15, 25]	[1, 6]
 * [45, 4, 35, 20, 3, 9]	[20, 9, 3, 45, 4, 35]
 */
class Solution1 {
    private  Map<Integer, Integer> rankingMap = new HashMap<>();

    // 민우가 구매한 로또 번호를 담은 배열 // 당첨 번
    public int[] solution(int[] lottos, int[] win_nums) {
        initRankingMap();

        int minNum = 0;
        int maxNum = 0;
        int zeroNum = 0;

        List<Integer> winNumsList = new ArrayList<>();
        for (int win_num : win_nums) {
            winNumsList.add(win_num);
        }

        //1. lottos 를 순회하면서, win_nums 와 똑같은 번호 개수를 구한다.
        for (int lotto : lottos) {
            if (lotto == 0) {
                zeroNum++;
                continue;
            }

            for (int win_num : win_nums) {
                if (lotto == win_num) {
                    minNum++;
                    if (!winNumsList.isEmpty()) {
                        winNumsList.remove((Integer) win_num);//똑같은 번호는 배열에서 지운다.
                    }
                }
            }
        }

        maxNum = minNum;
        if (!winNumsList.isEmpty()) {
            maxNum += (zeroNum <= winNumsList.size()) ? zeroNum : winNumsList.size();
        }

        maxNum = rankingMap.get(maxNum);
        minNum = rankingMap.get(minNum);

        int[] answer = {maxNum, minNum};
        return answer;
    }

    private void initRankingMap() {
        rankingMap.put(6, 1);
        rankingMap.put(5, 2);
        rankingMap.put(4, 3);
        rankingMap.put(3, 4);
        rankingMap.put(2, 5);
        rankingMap.put(1, 6);
        rankingMap.put(0, 6);
    }


//    public static void main(String[] args) {
//        int[] lottos = {44, 1, 0, 0, 31, 25};
//        int[] win_nums = {31, 10, 45, 1, 6, 19};
//        int[] answer = solution(lottos, win_nums);
//        System.out.println(answer[0] + ": " + answer[1]);
//    }
}