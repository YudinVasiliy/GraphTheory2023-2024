package org.leetcode;

import java.util.*;

public class ex1514 {
    public static void main(String[] args){
        var sol= new Solution();
        System.out.println(sol.maxProbability(
                3,
                new int[][]{{0,1},{1,2},{0,2}},
                new double[]{0.5,0.5,0.2},
                0,
                2));
    } //модульный тест
    private static class Solution {
        public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
            class EdgeToVertex implements Comparable<EdgeToVertex>{
                double probability;
                int endVertexInd;
                int startVertexInd;

                public EdgeToVertex(double probability, int eVertexInd,int sVertexInd) {
                    this.probability = probability;
                    this.endVertexInd = eVertexInd;
                    this.startVertexInd = sVertexInd;
                }
                @Override
                public int compareTo(EdgeToVertex o) {
                    return -Double.compare(probability, o.probability);
                }
                @Override
                public boolean equals(Object o) {
                    if (!(o instanceof EdgeToVertex vertex)) return false;
                    //System.out.println("EQUALS: "+vertexInd+", "+vertex.vertexInd);
                    return startVertexInd==vertex.startVertexInd &&
                            endVertexInd == vertex.endVertexInd;
                }
                @Override
                public int hashCode() {
                    return Objects.hash(endVertexInd);
                }

                @Override
                public String toString() {
                    return "EdgeToVertex{" +
                            "probability=" + probability +
                            ", endVertexInd=" + endVertexInd +
                            ", startVertexInd=" + startVertexInd +
                            '}';
                }
            }
            HashMap<Integer,LinkedList<EdgeToVertex>> edgeToVertexMap=new HashMap<>();
            for(int i=0;i< edges.length;++i){
                if(!edgeToVertexMap.containsKey(edges[i][0])){
                    var edgeList=new LinkedList<EdgeToVertex>();
                    edgeList.add(new EdgeToVertex(succProb[i], edges[i][1]));
                    edgeToVertexMap.put(edges[i][0], edgeList);
                }
                else{
                    edgeToVertexMap.get(edges[i][0]).add(new EdgeToVertex(succProb[i], edges[i][1]));
                }
                if(!edgeToVertexMap.containsKey(edges[i][1]))
                    edgeToVertexMap.put(edges[i][1],new LinkedList<>());
                edgeToVertexMap.get();
            }
            var probabilities=new double[n];
            probabilities[start_node]=1.0;


            PriorityQueue<Vertex> queue=new PriorityQueue<>(adjacency.length);
            for(int i=0;i<probabilities.length;++i){
                queue.add(new Vertex(probabilities[i],i));
            }
            //System.out.println("Queue first: -----------------------------");
            //queue.forEach(System.out::println);
            while (!queue.isEmpty()){
                var current=queue.remove();
                //check siblings
                //System.out.println("Queue before: -----------------------------");
                //queue.forEach(System.out::println);
                for(int i=0;i< adjacency.length;++i){
                    if(i!= current.vertexInd &&
                            probabilities[i]< current.probability*adjacency[current.vertexInd][i]){
                        var sibling= new Vertex(probabilities[i],i);
                        //System.out.println("======================");
                        //System.out.println("4: "+queue.contains(new Vertex(1,4)));
                        //System.out.println(sibling);
                        queue.remove(sibling);
                        sibling.probability=probabilities[i]=current.probability*adjacency[current.vertexInd][i];
                        //System.out.println(sibling);
                        queue.add(sibling);
                        //System.out.println("4: "+queue.contains(new Vertex(1,4)));
                        //System.out.println(queue.contains(sibling)+"\n======================");
                    }
                }
                //System.out.println("--------------------------------------------------------------");
                //System.out.println("Current="+current);
                //System.out.println("Distances: "+Arrays.toString(probabilities));
                //queue.forEach(System.out::println);
            }
            return probabilities[end_node];
        }
    }
}
