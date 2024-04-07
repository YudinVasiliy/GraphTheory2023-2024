package org.leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class ex224 {
    public static void main(String[] args){
        var sol= new Solution();
        System.out.println(sol.calculate("1 + 1"));
    } //модульный тест
    private static class Solution {
        public int calculate(final String s) {
            final Stack<Integer> stack = new Stack<>();

            int sign = 1, number = 0, result = 0;
            char c=0;
            var str=s.toCharArray();
            for(int i = 0; i < str.length; ++i) {
                c = str[i];
                if(c==' ')continue;
                if(Character.isDigit(c)) {
                    number = 10 * number + c - '0';
                } else if(c == '+') {
                    result += sign * number;
                    number = 0;
                    sign = 1;
                } else if(c == '-') {
                    result += sign * number;
                    number = 0;
                    sign = -1;
                } else if(c == '(') {
                    stack.push(result);
                    stack.push(sign);
                    sign = 1;
                    result = 0;
                } else/* if(c == ')') */{
                    result += sign * number;
                    number = 0;
                    result *= stack.pop();
                    result += stack.pop();
                }
            }
            if(number != 0)return result + sign * number;
            return result;
        }

        //bad solution
        /*public int calculate(String s) {
            Deque<Integer> numStack = new ArrayDeque<Integer>();
            Deque<Character> actionStack = new ArrayDeque<Character>();
            var str=s.toCharArray();
            StringBuilder numberString=new StringBuilder();
            boolean unarMinus=true;
            for(int i=0;i<str.length;++i){
                if(str[i]==' ')continue;
                if (str[i] >= '0' && str[i] <= '9') {
                    numberString.append(str[i]);
                    unarMinus=false;
                    continue;
                }
                if (str[i] == '-' && unarMinus) {
                    numStack.push(0);
                }
                if(!numberString.isEmpty()) {
                    var number=Integer.parseInt(numberString.toString());
                    numberString.delete(0,numberString.length());
                    if(!actionStack.isEmpty() && actionStack.getFirst()=='+'){
                        numStack.push(numStack.pop()+number);
                        actionStack.pop();
                    }
                    else if(!actionStack.isEmpty() && actionStack.getFirst()=='-'){
                        numStack.push(numStack.pop()-number);
                        actionStack.pop();
                    }
                    else{
                        numStack.push(number);
                    }
                }
                if(str[i]==')'){
                    actionStack.pop();
                    if(!actionStack.isEmpty() && actionStack.getFirst()!='('){
                        if(actionStack.getFirst()=='+'){
                            numStack.push(numStack.pop()+numStack.pop());
                        }
                        else if(actionStack.getFirst()=='-'){
                            var secondOp=numStack.pop();
                            numStack.push(numStack.pop()-secondOp);
                        }
                        actionStack.pop();
                    }
                }
                else{
                    if(str[i]=='(')unarMinus=true;
                    actionStack.push(str[i]);
                }
            }
            if(!numberString.isEmpty()) {
                var number=Integer.parseInt(numberString.toString());
                numberString.delete(0,numberString.length());
                if(!actionStack.isEmpty() && actionStack.getFirst()=='+'){
                    numStack.push(numStack.pop()+number);
                    actionStack.pop();
                }
                else if(!actionStack.isEmpty() && actionStack.getFirst()=='-'){
                    numStack.push(numStack.pop()-number);
                    actionStack.pop();
                }
                else{
                    numStack.push(number);
                }
            }
            return numStack.pop();
        }*/
    }
}
