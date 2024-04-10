package org.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class ex785 {
    public static void main(String[] args){
        var sol= new Solution();
        System.out.println(sol.isBipartite(
                new int[][]{{1,2,3},{0,2},{0,1,3},{0,2}}));
    } //модульный тест
    private static class Solution {
        public boolean isBipartite(int[][] graph) {
            var typeOfPath=new int[graph.length];
            /*
            0-there is no path
            1-not even path
            2-even path
             */
            Queue<Integer> queue= new LinkedList<>();
            boolean hasNotProcessedVertex=true;
            int startVertex=0;
            while(hasNotProcessedVertex) {
                hasNotProcessedVertex=false;
                queue.add(startVertex);
                typeOfPath[startVertex]=2;
                while (!queue.isEmpty()) {
                    var current = queue.remove();
                    if (typeOfPath[current]==1)
                        for (var sibling : graph[current]) {
                            if(typeOfPath[sibling]==1)return false;
                            if (typeOfPath[sibling]==0) {
                                typeOfPath[sibling] = 2;
                                queue.add(sibling);
                            }
                        }
                    else for (var sibling : graph[current]) {
                        if(typeOfPath[sibling]==2)return false;
                        if (typeOfPath[sibling]==0) {
                            typeOfPath[sibling] = 1;
                            queue.add(sibling);
                        }
                    }
                }
                for(;startVertex< graph.length;++startVertex){
                    if(typeOfPath[startVertex]==0){
                        hasNotProcessedVertex=true;
                        break;
                    }
                }
            }
            return true;
        }
    }
}

