package org.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ex2642 {

    public static void main(String[] args){

    } //модульный тест

    private static class Graph {

        int numberOfNodes;
        ArrayList<int[]> edges;
        //u ->[[1,10],[2,15],...]
        //      v cost v cost
        HashMap<Integer,int[]> paths;
        boolean[] pathsRowIsActual;

        public Graph(int n, int[][] edges_) {
            numberOfNodes=n;
            paths=new HashMap<>();
            edges= new ArrayList<>(Arrays.asList(edges_));
            pathsRowIsActual=new boolean[n];
        }

        public void addEdge(int[] edge) {
            if(!(
                    //if there is no shorter path between edge[0] and edge[1]
                    pathsRowIsActual[edge[0]] &&
                            paths.get(edge[0])[edge[1]]<edge[2]
            )){
                edges.add(edge);
                //update actuality of paths from nodes that can reach the edge[0] or edge[1]
                //because new edge can change paths length
                pathsRowIsActual[edge[0]]=false;
                //paths.remove(edge[0]);
                for(int key:paths.keySet()){
                    if(paths.get(key)[edge[0]]!=0) {
                        pathsRowIsActual[key] = false;
                    }
                }
            }

        }

        public int shortestPath(int node1, int node2) {
            int toReturn;
            if(pathsRowIsActual[node1]){
                toReturn=paths.get(node1)[node2];
                return toReturn!=Integer.MAX_VALUE?toReturn:-1;
            }
            pathsRowIsActual[node1]=true;
            int[] dist=new int[numberOfNodes];
            Arrays.fill(dist,Integer.MAX_VALUE);
            dist[node1] = 0;
            boolean hasUpdate=true;
            int[] edge;
            for (int i = 0; i < numberOfNodes - 1 && hasUpdate; ++i) {
                hasUpdate = false;
                for (int j = 0; j < edges.size(); ++j) {
                    edge = edges.get(j);
                    if (dist[edge[0]] < dist[edge[1]]-edge[2]) {
                        dist[edge[1]] = dist[edge[0]] +edge[2];
                        hasUpdate = true;
                    }
                }
            }
            paths.put(node1,dist);
            toReturn=dist[node2];
            return toReturn!=Integer.MAX_VALUE?toReturn:-1;
        }
    }

/**
 * Your Graph object will be instantiated and called as such:
 * Graph obj = new Graph(n, edges);
 * obj.addEdge(edge);
 * int param_2 = obj.shortestPath(node1,node2);
 */
}
