package org.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

public class ex1042 {
    public static void main(String[] args){
        var sol= new Solution();
        System.out.println(Arrays.toString(sol.gardenNoAdj(
                3, new int[][]{{1,2},{2,3},{3,1}}
        )));
    } //модульный тест
    private static class Solution {
        public int[] gardenNoAdj(int n, int[][] paths) {
            int[] colors=new int[n];
            byte[] situationOfVertex=new byte[n];
            var adjacency=new ArrayList<ArrayList<Integer>>(n);
            for(int i=0;i<n;++i){
                adjacency.add(new ArrayList<>());
            }
            /*
            situation is binary number 0-15, where every i-bit means presence of sibling with color i
             */
            int[] edge;
            for(int iPath=0;iPath< paths.length;++iPath){
                edge=paths[iPath];
                adjacency.get(edge[0]-1).add(edge[1]-1);
                adjacency.get(edge[1]-1).add(edge[0]-1);
            }
            for(int vertex=0;vertex<n;++vertex){
                byte mask;
                if(situationOfVertex[vertex]%2==0){
                    colors[vertex]=1;
                    mask=1;
                }
                else if(situationOfVertex[vertex]%4==1){
                    colors[vertex]=2;
                    mask=2;
                }
                else if(situationOfVertex[vertex]%8==3){
                    colors[vertex]=3;
                    mask=4;
                }
                else {
                    colors[vertex]=4;
                    mask=8;
                }
                var siblings=adjacency.get(vertex);
                for (Integer sibling : siblings) situationOfVertex[sibling] |= mask;
            }
            return colors;
        }
    }
}
