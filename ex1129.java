package org.leetcode;

import java.util.Arrays;

public class ex1129 {
    public static void main(String[] args){
        var sol= new Solution();
        System.out.println(Arrays.toString(sol.shortestAlternatingPaths(
                3, new int[][]{{0, 1}, {1, 2}}, new int[0][0]
        )));
    } //модульный тест
    private static class Solution {
        public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
            int[] redDistance = new int[n];
            int[] blueDistance = new int[n];
            Arrays.fill(redDistance,-1);
            Arrays.fill(blueDistance,-1);
            redDistance[0]=blueDistance[0]=0;
            boolean hasUpdate=true;
            int u;
            int v;
            for (int i = 0; i < n - 1 && hasUpdate; ++i) {
                hasUpdate = false;
                for (int[] redEdge : redEdges) {
                    u = redEdge[0];
                    v = redEdge[1];
                    if (blueDistance[u] != -1 && (redDistance[v] == -1 || blueDistance[u] < redDistance[v] - 1)) {
                        redDistance[v] = blueDistance[u] + 1;
                        hasUpdate = true;
                    }
                }
                for (int[] blueEdge : blueEdges) {
                    u = blueEdge[0];
                    v = blueEdge[1];
                    if (redDistance[u] != -1 && (blueDistance[v] == -1 || redDistance[u] < blueDistance[v] - 1)) {
                        blueDistance[v] = redDistance[u] + 1;
                        hasUpdate = true;
                    }
                }
            }
//            System.out.println(Arrays.toString(redDistance));
//            System.out.println(Arrays.toString(blueDistance));
            for(int i=0;i<n;++i){
                if(blueDistance[i]!=-1&&(redDistance[i]==-1 || blueDistance[i]<redDistance[i]))redDistance[i]=blueDistance[i];
            }
            return redDistance;
        }
    }
}