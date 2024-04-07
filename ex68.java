package org.leetcode;

import java.util.ArrayList;
import java.util.List;

public class ex68 {
    public static void main(String[] args){
        var sol= new Solution();
        System.out.println(sol.fullJustify(
                new String[]{"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"},
                20));
    } //модульный тест
    private static class Solution {
        public List<String> fullJustify(String[] words, int maxWidth) {
            var rows=new ArrayList<String>();
            var startIndex=0;
            var length=-1;
            var count=0;
            while(startIndex < words.length){
                length=-1;
                count=0;
                while(startIndex+count < words.length &&
                        length+1+words[startIndex+count].length()<=maxWidth){
                    length+=1+words[startIndex+count++].length();
                }
                var buffer=new StringBuffer();
                if(startIndex+count!= words.length){
                    if(count==1){
                        buffer.append(words[startIndex]);
                        buffer.append(" ".repeat(maxWidth-length));
                    }
                    else{
                        var averageSpaces=(maxWidth-length+count-1)/(count-1);
                        //System.out.println(averageSpaces + ", "+length+", "+count);
                        buffer.append(words[startIndex]);
                        var countOfSpacesToIncr=(maxWidth-length+count-1)%(count-1)!=0?
                                (maxWidth-length+count-1)%(count-1):0;
                        /*if(averageSpaces*(count-1)!=maxWidth-length+count-1)
                            buffer.append(
                                    " ".repeat(maxWidth-length+count-1+averageSpaces*(2-count))
                            );
                        else buffer.append(" ".repeat(averageSpaces));
                        buffer.append(words[startIndex+1]);*/
                        for(int i=1;i<count;++i){
                            buffer.append(" ".repeat(
                                    averageSpaces+((i-1<countOfSpacesToIncr)?1:0))
                            );
                            buffer.append(words[startIndex+i]);
                        }
                    }
                }
                else{
                    buffer.append(words[startIndex]);
                    for(int i=1;i<count;++i){
                        buffer.append(" ");
                        buffer.append(words[startIndex+i]);
                    }
                    buffer.append(" ".repeat(maxWidth-length));
                }
                startIndex+=count;
                rows.add(buffer.toString());
                //System.out.println(buffer.toString());
            }
            return rows;
        }
    }
}
