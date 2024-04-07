package org.leetcode;

import java.util.Arrays;
import java.util.Collections;

public class ex6 {
    public static void main(String[] args){
        var sol= new Solution();
        System.out.println(sol.convert("PAYPALISHIRING",3));
    } //модульный тест
    private static class Solution {
        public String convert(String s, int numRows) {
            StringBuffer[] rows=new StringBuffer[numRows];
            for(int i=0;i<numRows;++i) rows[i]=new StringBuffer();
            int ind;
            int toMod=numRows*2-2;
            if(toMod==0)toMod=1;
            for(int i=0;i<s.length();++i){
                ind=i%toMod;
                if(ind>numRows-1) rows[numRows-2-ind%numRows].append(s.charAt(i));
                else rows[ind%numRows].append(s.charAt(i));
            }
            var result=rows[0];
            for(int i=0;i<numRows;++i)
                result.append(rows[i]);
            return result.toString();
        }
    }
}