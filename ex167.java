package org.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ex167 {
    public static void main(String[] args){
        var sol= new Solution();
        System.out.println(Arrays.toString(sol.twoSum(new int[]{5,25,75},100)));
    } //модульный тест
    private static class Solution {
        public int[] twoSum(int[] numbers, int target) {
            var centralVal=target/2;
            var centralInd=Arrays.binarySearch(numbers,centralVal);
            centralInd=centralInd<0?-centralInd-1:centralInd;
            centralInd=numbers[centralInd]*2==target?centralInd:centralInd-1;
            var leftInd=0;
            var rightInd= numbers.length-1;
            System.out.println(leftInd+", "+centralInd+", "+rightInd);
            while(numbers[leftInd]+numbers[rightInd]!=target &&
                    leftInd<=centralInd && rightInd>centralInd){
                        System.out.println(leftInd+", "+centralInd+", "+rightInd);
                while(numbers[leftInd]+numbers[rightInd]<target)++leftInd;
                while (numbers[leftInd]+numbers[rightInd]>target )--rightInd;
            }

            return new int[]{leftInd+1,rightInd+1};
        }
    }
}
