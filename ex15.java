package org.leetcode;

import java.util.*;

public class ex15 {
    public static void main(String[] args){
        var sol= new Solution();
        System.out.println(sol.threeSum(new int[]{-2,0,0,2,}));
    } //модульный тест
    private static class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            var leftInd=0;
            var rightInd=nums.length-1;
            Set<List<Integer>> result= new HashSet<>();
            Arrays.sort(nums);
            //System.out.println(Arrays.toString(nums));
            for(int i=1;i< nums.length-1;++i){
                leftInd=0;
                rightInd=nums.length-1;
                //System.out.println("i: "+i);
                while(leftInd<i&&rightInd>i){
                    var startLeftInd=leftInd;
                    var startRightInd=rightInd;
                    //System.out.println("1: "+leftInd+", "+rightInd);
                    while(nums[leftInd]+nums[i]+nums[rightInd]<0 &&
                            leftInd+1<i)++leftInd;
                    //System.out.println("2: "+leftInd+", "+rightInd);
                    while(nums[leftInd]+nums[i]+nums[rightInd]>0 &&
                            rightInd-1>i)--rightInd;
                    //System.out.println("3: "+leftInd+", "+rightInd);
                    if(nums[leftInd]+nums[i]+nums[rightInd]==0){
                        result.add(new ArrayList<Integer>(
                                List.of(nums[leftInd],nums[i],nums[rightInd])
                        ));
                        ++leftInd;
                        --rightInd;
                    }
                    else if(startLeftInd==leftInd && startRightInd==rightInd)break;

                    //System.out.println("4: "+leftInd+", "+rightInd);
                }
                /*if(leftInd==i-1 && rightInd==i+1&&nums[leftInd]+nums[i]+nums[rightInd]==0){
                    result.add(new ArrayList<Integer>(
                            List.of(nums[leftInd],nums[i],nums[rightInd])
                    ));
                }*/
            }
            return result.stream().toList();
        }
    }
}
