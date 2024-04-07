package org.leetcode;

import java.util.*;

public class ex30 {
    public static void main(String[] args){
        var sol= new Solution();
        System.out.println(sol.findSubstring("ababababab",
                new String[]{"ababa","babab"}));
    } //модульный тест
    private static class Solution {
        public List<Integer> findSubstring(String s, String[] words) {
            if(s.length()< words.length*words[0].length())return new ArrayList<>();
            var wordLength=words[0].length();
            var result=new ArrayList<Integer>();
            Arrays.sort(words);
            var wordsHash=Arrays.hashCode(words);
            for(int start=0;start<wordLength &&
                    start+wordLength*words.length<=s.length();++start){
                Queue<String> substr=new ArrayDeque<>(words.length);
                for(int i=start;i<words.length*wordLength;i+=wordLength){
                    substr.offer(s.substring(i,i+wordLength));
                }
                var array=substr.stream().sorted().toArray(String[]::new);
                if(wordsHash==Arrays.hashCode(array))
                    if(Arrays.equals(array,words))result.add(start);
                System.out.println("1)"+substr);
                for(int endOfWord=start+wordLength*(words.length+1);
                    endOfWord<=s.length();
                    endOfWord+=wordLength){
                    System.out.println("i:"+endOfWord);
                    substr.poll();
                    substr.offer(
                            s.substring(
                                    endOfWord-wordLength,
                                    endOfWord
                            )
                    );
                    System.out.println("2)"+substr);
                    array=substr.stream().sorted().toArray(String[]::new);
                    if(wordsHash==Arrays.hashCode(array))
                        if(Arrays.equals(array,words))
                            result.add(endOfWord-wordLength*words.length);
                }
            }
            return result;
        }
    }
}

