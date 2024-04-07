package org.leetcode;

    public class ex12 {
        public static void main(String[] args){
            var sol= new Solution();
            int num = 58;
            System.out.println(sol.intToRoman(num));
        } //модульный тест
        private static class Solution {
            public String intToRoman(int num) {
                int numToConvert=num;
                var currentIndex=0;
                int[] valueOfSym=new int[]{1000,900,500,400,100,90,50,40,10,9,5,4,1};
                String[] symbols=new String[]{"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
                StringBuffer result=new StringBuffer();
                while(currentIndex<13){
                    while(numToConvert-valueOfSym[currentIndex]>=0){
                        numToConvert-=valueOfSym[currentIndex];
                        result.append(symbols[currentIndex]);
                    }
                    ++currentIndex;
                }
                return result.toString();
            }
        }
    }
