package org.leetcode;

import java.util.*;

public class ex2467 {
    public static void main(String[] args){
        var sol= new Solution();
        System.out.println(sol.mostProfitablePath(new int[][]
                        {{0,2},{0,5},{1,3},{1,5},{2,4}}, 4,
                new int[]{5018,8388,6224,3466,3808,3456}));
    } //модульный тест
    private static class Solution {
        public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
            var adjacency = new ArrayList<ArrayList<Integer>>(edges.length+1);
            for(int i=0;i< edges.length+1;++i){
                adjacency.add(new ArrayList<>());
            }
            for(var edge:edges){
                adjacency.get(edge[0]).add(edge[1]);
                adjacency.get(edge[1]).add(edge[0]);
            }
            Deque<Integer> stack=new ArrayDeque<>();
            var visited=new boolean[edges.length+1];
            var bobPath=new ArrayList<Integer>();
            stack.add(bob);
            //dfs for bob to 0
            while (!stack.isEmpty()) {
                int current = stack.peek();
                //System.out.println(current);
                if(!visited[current]){
                    bobPath.add(current);
                    visited[current]=true;
                }
                if(current==0)break;
                boolean hasUnvisitedChildren=false;
                for(var child:adjacency.get(current)){
                    if(!visited[child]){
                        hasUnvisitedChildren=true;
                        stack.push(child);
                    }
                }
                if(!hasUnvisitedChildren){
                    stack.remove();
                    bobPath.removeLast();
                }
            }
            for(int i=0;i<bobPath.size()/2;++i) amount[bobPath.get(i)]=0;
            if(bobPath.size()%2==1) {
                amount[bobPath.get(bobPath.size()/2)]/=2;
            }

            //dfs for alice from 0 to leaf
            stack.add(0);
            var maxAmount = Integer.MIN_VALUE;
            Arrays.fill(visited,false);
            while (!stack.isEmpty()) {
                int current = stack.peek();
                //System.out.println(current);
                visited[current]=true;
                boolean hasUnvisitedChildren=false;
                for(var child:adjacency.get(current)){
                    if(!visited[child]){
                        ///System.out.println("Unvisited child: "+child);
                        hasUnvisitedChildren=true;
                        stack.push(child);
                        amount[child]+=amount[current];
                    }
                }
                if(!hasUnvisitedChildren){
                    if(current!=0 && adjacency.get(current).size()==1 && amount[current]>maxAmount)
                        maxAmount=amount[current];
                    stack.remove();
                }
                //System.out.println(Arrays.toString(amount)+"\n-------------------------------------");
            }
            return maxAmount;
        }
    }
}
