package org.leetcode;

import java.util.Arrays;

public class ex787 {
    public static void main(String[] args){
        var sol= new Solution();
        System.out.println(sol.findCheapestPrice(4,new int[][]{{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}}, 0,  3, 1));
    } //модульный тест
    private static class Solution {

        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            //array with the shortest paths from src to every node on previous step
            int[] prevPaths=new int[n];
            Arrays.fill(prevPaths,-1);
            //initialize src element to start with
            prevPaths[src]=0;
            //array with the shortest paths from src to every node on current step
            int[] newPaths=new int[n];
            Arrays.fill(newPaths,-1);
            //variable to swap previous arrays
            int[] tmp;
            int[][] adjacency=new int[n][n];
            var min=-1;
            for(var flight:flights){
                adjacency[flight[0]][flight[1]]=flight[2];
            }
            //steps loop
            for(int step=0;step<=k;++step){
                //iterate through every step source node and find active (>=0)
                for(int prevNodeNum=0;prevNodeNum<n;++prevNodeNum){
                    if(prevPaths[prevNodeNum]!=-1){
                        //find every reachable form source node
                        for(int nextNodeNum=0;nextNodeNum<n;++nextNodeNum){
                            if(adjacency[prevNodeNum][nextNodeNum]!=0){
                                //nexNode is reachable from prev so we fill newPaths with new paths
                                newPaths[nextNodeNum]=switch (newPaths[nextNodeNum]){
                                    case -1->prevPaths[prevNodeNum]+adjacency[prevNodeNum][nextNodeNum];
                                    default -> Math.min(
                                            newPaths[nextNodeNum],
                                            prevPaths[prevNodeNum]+adjacency[prevNodeNum][nextNodeNum]
                                    );
                                };
                            }
                        }
                    }
                }
                if(newPaths[dst]!=-1){
                    if(min!=-1) min=Math.min(min,newPaths[dst]);
                    else min=newPaths[dst];
                }
                //swap arrays
                tmp=prevPaths;
                prevPaths=newPaths;
                newPaths=tmp;
            }
            return min;
        }
    }
}
