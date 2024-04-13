package org.leetcode;

import java.util.*;

public class ex2642 {

    public static void main(String[] args){

    } //модульный тест

    private static class Graph {
        //without optimization
        //ford-bellman
        /*int numberOfNodes;
        ArrayList<int[]> edges;

        public Graph(int n, int[][] edges_) {
            numberOfNodes=n;
            edges= new ArrayList<>(Arrays.asList(edges_));
        }

        public void addEdge(int[] edge) {
            edges.add(edge);
        }

        public int shortestPath(int node1, int node2) {
            int toReturn;
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
            toReturn=dist[node2];
            return toReturn!=Integer.MAX_VALUE?toReturn:-1;
        }*/


        //good, but have unnecessary optimization
        //ford-bellman
        /*int numberOfNodes;
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
        }*/

        //dijcstra
        /*
        static class Vertex implements Comparable<Vertex>{
            final int dist;
            final int vertexInd;
            public Vertex(int dist, int vertexInd) {
                this.dist = dist;
                this.vertexInd = vertexInd;
            }
            @Override
            public int compareTo(Vertex o) {
                return Integer.compare(dist, o.dist);
            }
            @Override
            public boolean equals(Object o) {
                if (!(o instanceof Vertex vertex)) return false;
                //System.out.println("EQUALS: "+vertexInd+", "+vertex.vertexInd);
                return vertexInd == vertex.vertexInd;
            }
            @Override
            public int hashCode() {
                return Objects.hash(vertexInd);
            }

            @Override
            public String toString() {
                return "Vertex{" +
                        "dist=" + dist +
                        ", vertexInd=" + vertexInd +
                        '}';
            }
        }
        ArrayList<ArrayList<Vertex>> adjacency;

        public Graph(int n, int[][] edges) {
            adjacency=new ArrayList<ArrayList<Vertex>>(n);
            for(int i=0;i<n;++i){
                adjacency.add(new ArrayList<>());
            }
            for(var edge:edges){
                adjacency.get(edge[0]).add(new Vertex(edge[2], edge[1]));
            }
        }

        public void addEdge(int[] edge) {
            adjacency.get(edge[0]).add(new Vertex(edge[2], edge[1]));
        }

        public int shortestPath(int node1, int node2) {
            int toReturn;
            var distances=new int[adjacency.size()];
            Arrays.fill(distances,Integer.MAX_VALUE);
            distances[node1]=0;

            PriorityQueue<Vertex> queue=new PriorityQueue<>(adjacency.size());
            queue.add(new Vertex(0, 0));
            while (!queue.isEmpty()){
                var current=queue.remove();
                if(current.vertexInd==node2)return current.dist!=Integer.MAX_VALUE?current.dist:-1;
                var siblings=adjacency.get(current.vertexInd);
                for(var sibling:siblings){
                    if(distances[sibling.vertexInd]-sibling.dist > current.dist){
                        var updatedSibling= new Vertex(distances[sibling.vertexInd] =
                                current.dist + sibling.dist, sibling.vertexInd);
                        queue.remove(updatedSibling);
                        queue.add(updatedSibling);
                    }
                }
            }
            toReturn=distances[node2];
            return toReturn!=Integer.MAX_VALUE?toReturn:-1;
        }
         */
    }
}
