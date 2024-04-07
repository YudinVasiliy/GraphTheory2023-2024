package org.leetcode;

import java.util.Arrays;
import java.util.Collections;

public class ex151 {
    public static void main(String[] args){
        var sol= new Solution();
        System.out.println(sol.reverseWords(" no chick fill a souce? "));
    } //модульный тест
    private static class Solution {
        public String reverseWords(String s) {
            var list= new java.util.ArrayList<>(Arrays
                    .stream(s.split(" "))
                            .filter(word-> !word.isEmpty())
                            .toList());
            list.stream().forEach(word->System.out.println("\""+word+"\""));
            Collections.reverse(list);
            return "\""+String.join(" ",list)+"\"";
        }
    }
}
