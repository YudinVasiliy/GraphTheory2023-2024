package org.leetcode;

import java.util.Arrays;

public class ex134 {
    public static void main(String[] args){
        var sol= new Solution();
        int[] gas = new int[]{1,2,3,4,5};
        int[] cost = new int[]{3,4,5,1,2};
        System.out.println(sol.canCompleteCircuit(gas,cost));
    } //модульный тест
    private static class Solution {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            var sum =0;
            var result=0;
            var currentSum=0;
            for(int i=0;i<gas.length;++i) {
                sum+=gas[i]-cost[i];
                currentSum+=gas[i]-cost[i];
                if(currentSum<0){
                    currentSum=0;
                    result=i+1;
                }
            }
            return sum<0?-1:result;
        }
    }
}
