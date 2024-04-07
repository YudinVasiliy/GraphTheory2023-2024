package org.leetcode;

import java.util.*;

public class ex209 {
    public static void main(String[] args){
        var sol= new Solution();
        System.out.println(sol.minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
    } //модульный тест
    private static class Solution {
        public int minSubArrayLen(int target, int[] nums) {
            var leftPointer=0;
            var rightPointer=0;
            var currentSum=nums[0];
            var minSubLength=nums.length;
            while(true){
                if(currentSum<target){
                    if(rightPointer == nums.length-1)break;
                    currentSum+=nums[++rightPointer];
                }
                else{
                    while(currentSum-nums[leftPointer]>=target &&
                            leftPointer<rightPointer){
                        currentSum-=nums[leftPointer++];
                    }
                    if(leftPointer==rightPointer)return 1;
                    if(rightPointer-leftPointer+1<minSubLength)
                        minSubLength=rightPointer-leftPointer+1;
                    currentSum-=nums[leftPointer++];
                }
            }
            if(minSubLength== nums.length && currentSum<target)return 0;
            return minSubLength;
        }
    }
}
