package July.naverWEBTOON;

import java.util.Arrays;

public class Solution1 {


    public int solution(int[] prices, int[] discounts) {
        int answer = 0;
        Arrays.sort(prices);
        Arrays.sort(discounts);

        int discountCnt = discounts.length;

        for (int i = prices.length - 1; i >= 0; i--) {
            if (discountCnt <= 0) {
                answer += prices[i];
            } else {
                answer += prices[i] * (100 - discounts[discountCnt - 1]) / 100;
                discountCnt--;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] priceA = {13000, 88000, 10000};
        int[] discountA = {30, 20};

        int[] priceB = {32000, 18000, 42500};
        int[] discountB = {50, 20, 65};


        Solution1 s1 = new Solution1();
        System.out.println(s1.solution(priceA, discountA));
        System.out.println(s1.solution(priceB, discountB));
    }
}
