package org.leetcode;

import java.util.*;

public class ex76 {
    public static void main(String[] args){
        var sol= new Solution();
        System.out.println(sol.minWindow("aaaaaaaaaaaabbbbbcdd",
                "abcdd"));
    } //модульный тест
    private static class Solution {
        public String minWindow(String s, String t) {
            if (s == null || t == null || s.isEmpty() || t.isEmpty()||s.length() < t.length())
                return "";
            var characterCapacity=new int[128];
            for(char sym: t.toCharArray()){
                ++characterCapacity[sym];
            }
            var leftBoard=0;
            var rightBoard=0;
            var startOfMinSubstr=-1;
            var count=t.length();//count of characters to get in window
            char[] sArr=s.toCharArray();
            var minLength=sArr.length+1;
            while(rightBoard<sArr.length){
                if(characterCapacity[sArr[rightBoard++]]-- > 0)--count;
                while(count==0){
                    if(rightBoard-leftBoard<minLength){
                        startOfMinSubstr=leftBoard;
                        minLength=rightBoard-leftBoard;
                    }
                    if(characterCapacity[sArr[leftBoard++]]++ == 0)++count;
                }
            }
            if(minLength<sArr.length+1)return s.substring(startOfMinSubstr,startOfMinSubstr+minLength);
            return "";
        }

        //worst solution!!!
        /*public String minWindow(String s, String t) {
            var sampleHashMap= new HashMap<String,Integer>();
            for(int i=0;i<t.length();++i){
                sampleHashMap.merge(String.valueOf(t.charAt(i)), 1, Integer::sum);
            }
            var currentSymbolsInWindow=new HashMap<String,Integer>();
            var leftBound=0;
            var rightBound=0;
            var leftBoundOfMin=-1;
            var rightBoundOfMin=s.length();
            while(rightBound<s.length()){
                //System.out.println("1) "+leftBound+", "+rightBound);
                while(sampleHashMap.get(String.valueOf(s.charAt(rightBound)))==null &&
                        rightBound<s.length()-1){
                    ++rightBound;
                }
                //System.out.println("2) "+leftBound+", "+rightBound);
                if(sampleHashMap.get(String.valueOf(s.charAt(rightBound)))==null)break;
                //System.out.println("3) "+leftBound+", "+rightBound);
                currentSymbolsInWindow.merge(
                        String.valueOf(s.charAt(rightBound)),
                        1,
                        Integer::sum);
                while(leftBound<rightBound){
                    var symCount=currentSymbolsInWindow.get(String.valueOf(s.charAt(leftBound)));
                    var sampleSymCount=sampleHashMap.get(String.valueOf(s.charAt(leftBound)));
                    if(sampleSymCount!=null) {
                        if (symCount <= sampleSymCount) break;
                        currentSymbolsInWindow.merge(
                                String.valueOf(s.charAt(leftBound)),
                                -1,
                                Integer::sum);
                    }
                    ++leftBound;
                }
                //System.out.println("4) "+leftBound+", "+rightBound);
                var allGood=true;
                for(var key: sampleHashMap.keySet()){
                    if(currentSymbolsInWindow.getOrDefault(key,-1)<
                            sampleHashMap.get(key)){
                        allGood=false;
                        break;
                    }
                }
                if(allGood && rightBound-leftBound<rightBoundOfMin-leftBoundOfMin){
                    rightBoundOfMin=rightBound;
                    leftBoundOfMin=leftBound;
                    //System.out.println("!!!!!!!) "+leftBoundOfMin+", "+rightBoundOfMin);
                }
                ++rightBound;
            }
            if(leftBoundOfMin>-1 && rightBoundOfMin<s.length())
                return s.substring(leftBoundOfMin,rightBoundOfMin+1);
            return "";
        }*/
    }
}
