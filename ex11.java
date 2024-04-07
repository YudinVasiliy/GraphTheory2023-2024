package org.leetcode;

public class ex11 {
    public static void main(String[] args){
        var sol= new Solution();
        System.out.println(sol.maxArea(new int[]{1,3,2,5,25,24,5}));
    } //модульный тест
    private static class Solution {
        public int maxArea(int[] height) {
            var leftInd=0;
            var rightInd=height.length-1;
            var maxS=-1;
            var newS=0;
            while(leftInd<rightInd){
                //System.out.print(leftInd+", "+rightInd+"|");
                while(height[leftInd]<height[rightInd] && leftInd<rightInd){
                    ++leftInd;
                    newS=(rightInd-leftInd)*Math.min(height[leftInd],height[rightInd]);
                    if(newS>maxS)maxS=newS;
                }
                while(height[leftInd]>=height[rightInd] && leftInd<rightInd){
                    --rightInd;
                    newS=(rightInd-leftInd)*Math.min(height[leftInd],height[rightInd]);
                    if(newS>maxS)maxS=newS;
                }
            }
            return maxS;
        }
    }
}
