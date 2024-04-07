package org.leetcode;

import java.util.*;

public class ex238 {
    public static void main(String[] args){
        var sol= new Solution();
        System.out.print(
                Arrays.toString(
                        sol.productExceptSelf( new int[]{1,2,3,4} )
                )
        );
    } //модульный тест
    private static class Solution {
        public int[] productExceptSelf(int[] nums) {
            var size=nums.length;
            int nextLeft=1;
            int nextRight=1;
            int[] result= new int[size];
            Arrays.fill(result,1);
            for(int i=1;i<size;++i){
                nextLeft *= nums[i-1];
                nextRight*=nums[size-i];
                result[i]*=nextLeft;
                result[size-i-1]*=nextRight;
            }
            return result;
        }
    }
}
