package org.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

public class ex135 {
    public static void main(String[] args){
        var sol= new Solution();
        //int[] gas = new int[]{5,1,2,5,3,5,5,5,4,1,1,1,6,5,6,2};
        int[] gas = new int[]{1,2,2};
        System.out.println(sol.candy(gas));
    } //модульный тест
    private static class Solution {
        public int candy(int[] ratings) {
            var candyArray=new int[ratings.length];
            if(ratings.length==1)return 1;
            var prevIsMin=false;
            var prevExtr=0;
            if(ratings[0]<=ratings[1]){
                candyArray[0]=1;
                prevIsMin=true;
            }
            for(int i=1;i<ratings.length-1;++i){
                if(ratings[i]<=ratings[i-1] && ratings[i]<=ratings[i+1]){//min
                    candyArray[i]=1;
                    if(!prevIsMin){
                        var candies=2;
                        for(int j=i-1;j>prevExtr;--j){
                            candyArray[j]=candies++;
                        }
                        if(candyArray[prevExtr]<candies)candyArray[prevExtr]=candies;
                        prevIsMin=true;
                    }
                    prevExtr=i;
                }
                else if(ratings[i]>ratings[i-1] && ratings[i]>ratings[i+1] ||
                        ratings[i]==ratings[i-1] && ratings[i]>ratings[i+1] ||
                        ratings[i]>ratings[i-1] && ratings[i]==ratings[i+1]){//max
                    if(prevIsMin){
                        var candies=2;
                        for(int j=prevExtr+1;j<i;++j){
                            candyArray[j]=candies++;
                        }
                        candyArray[i]=candies;
                        prevIsMin=false;
                    }
                    prevExtr=i;
                }
            }
            if(ratings[ratings.length-1]<=ratings[ratings.length-2]){//min
                candyArray[candyArray.length-1]=1;
                if(!prevIsMin){
                    var candies=2;
                    for(int j=candyArray.length-2;j>prevExtr;--j){
                        candyArray[j]=candies++;
                    }
                    if(candyArray[prevExtr]<candies)candyArray[prevExtr]=candies;
                }
            }
            else{//max
                if(prevIsMin){
                    var candies=2;
                    for(int j=prevExtr+1;j<candyArray.length-1;++j){
                        candyArray[j]=candies++;
                    }
                    candyArray[candyArray.length-1]=candies;
                }
            }
            System.out.println(Arrays.toString(candyArray));
            return Arrays.stream(candyArray).sum();
        }
    }
}
