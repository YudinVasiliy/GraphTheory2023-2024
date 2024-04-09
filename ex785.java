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
            var hasEvenPath=new boolean[graph.length];
            var hasNotEvenPath=new boolean[graph.length];
            hasEvenPath[0]=true;

            Queue<Integer> queue= new LinkedList<>();
            boolean hasNotProcessedVertex=true;
            int startVertex=0;
            while(hasNotProcessedVertex) {
                hasNotProcessedVertex=false;
                queue.add(startVertex);
                hasEvenPath[startVertex]=true;
                while (!queue.isEmpty()) {
                    var current = queue.remove();
                    if (hasNotEvenPath[current] && hasEvenPath[current] ||
                            graph[current].length == 0) return false;
                    if (hasNotEvenPath[current])
                        for (var sibling : graph[current]) {
                            if (!hasEvenPath[sibling]) {
                                hasEvenPath[sibling] = true;
                                queue.add(sibling);
                            }
                        }
                    else for (var sibling : graph[current]) {
                        if (!hasNotEvenPath[sibling]) {
                            hasNotEvenPath[sibling] = true;
                            queue.add(sibling);
                        }
                    }
                }
                for(;startVertex< graph.length;++startVertex){
                    if(!hasNotEvenPath[startVertex]&&!hasEvenPath[startVertex]){
                        hasNotProcessedVertex=true;
                        break;
                    }
                }
            }
            return true;
        }
    }
}

