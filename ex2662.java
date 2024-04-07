package org.leetcode;

import java.util.*;
import java.util.stream.IntStream;

public class ex2662 {
    public static void main(String[] args){
        var sol= new Solution();
        System.out.println(sol.minimumCost(new int[]{1,1},new int[]{10,4},
                new int[][]{{4,2,1,1,3},{1,2,7,4,4},{10,3,6,1,2},{6,1,1,2,3}}));
    } //модульный тест
    private static class Solution {
        public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
            class Vertex{
                Vertex(int x_,int y_){
                    x=x_;
                    y=y_;
                }
                final int x;
                final int y;

                @Override
                public String toString() {
                    return "Vertex{" +
                            "x=" + x +
                            ", y=" + y +
                            '}';
                }

                @Override
                public boolean equals(Object o) {
                    if (this == o) return true;
                    if (!(o instanceof Vertex vertex)) return false;
                    return x == vertex.x && y == vertex.y;
                }

                @Override
                public int hashCode() {
                    return Objects.hash(x, y);
                }
            }
            //array for adjacency making
            //0 is start, 1 is target
            Vertex startVertex=new Vertex(start[0],start[1]);
            Vertex targetVertex=new Vertex(target[0],target[1]);
            var vertexes=new ArrayList<Vertex>(2);
            vertexes.add(startVertex);
            vertexes.add(targetVertex);
            var vertexIndex = new HashMap<Vertex, Integer>(2);
            vertexIndex.put(startVertex,0);
            vertexIndex.put(targetVertex,1);
            var adjacencySize=2;
            int startVertexInd;
            int targetVertexInd;
            class SpecialRoad{
                int startVertexIndex;
                int targetVertexIndex;
                int price;

                public SpecialRoad(int startVertexIndex, int targetVertexIndex, int price) {
                    this.startVertexIndex = startVertexIndex;
                    this.targetVertexIndex = targetVertexIndex;
                    this.price = price;
                }

                @Override
                public String toString() {
                    return "SpecialRoad{" +
                            "startVertexIndex=" + startVertexIndex +
                            ", targetVertexIndex=" + targetVertexIndex +
                            ", price=" + price +
                            '}';
                }
            }
            var specialRoadsToAddToAdjacency=new SpecialRoad[specialRoads.length];
            for(int roadInd=0;roadInd<specialRoads.length;++roadInd){
                startVertex=new Vertex(specialRoads[roadInd][0],specialRoads[roadInd][1]);
                targetVertex=new Vertex(specialRoads[roadInd][2],specialRoads[roadInd][3]);
                if(!vertexIndex.containsKey(startVertex)) {
                    vertexIndex.put(startVertex, startVertexInd=adjacencySize++);
                    vertexes.add(startVertex);
                }
                else startVertexInd=vertexIndex.get(startVertex);
                if(!vertexIndex.containsKey(targetVertex)) {
                    vertexIndex.put(targetVertex, targetVertexInd=adjacencySize++);
                    vertexes.add(targetVertex);
                }
                else targetVertexInd=vertexIndex.get(targetVertex);
                specialRoadsToAddToAdjacency[roadInd]=
                        new SpecialRoad(startVertexInd,targetVertexInd,specialRoads[roadInd][4]);
            }
            //System.out.println("Vertexes: "+vertexes);
            //System.out.println(Arrays.toString(specialRoadsToAddToAdjacency));
            var adjacency=new int[adjacencySize][adjacencySize];
            /*for(int i=0;i < adjacencySize;++i){
                Arrays.fill(adjacency[i],-1);
            }*/
            for(int i=0;i<adjacencySize;++i)adjacency[i][i]=-1;
            for(int i=0;i < adjacencySize;++i){
                for(int j=0;j<adjacencySize;++j){
                    if(i!=j && adjacency[i][j]==0){
                        adjacency[i][j]=Math.abs(vertexes.get(j).x-vertexes.get(i).x)+
                                Math.abs(vertexes.get(j).y-vertexes.get(i).y);
                    }
                }
            }
            for(var road:specialRoadsToAddToAdjacency){
                if(adjacency[road.startVertexIndex][road.targetVertexIndex] > road.price)
                    adjacency[road.startVertexIndex][road.targetVertexIndex] = road.price;
            }

            //adjacency print
            /*{
                System.out.print("\t ");
                IntStream.range(0, adjacencySize).forEach(num ->
                        System.out.print(num + "\t"));
                System.out.println("\n" + "\t " + "_\t".repeat(adjacencySize));
                for (int i = 0; i < adjacencySize; ++i) {
                    System.out.print(i + "\t|");
                    for (int j = 0; j < adjacencySize; ++j) {
                        System.out.print(adjacency[i][j] + "\t");
                    }
                    System.out.println();
                }
            }*/
            //System.out.println(Arrays.toString(dijcstra(adjacency,0)));
            return dijcstra(adjacency,0)[1];
        }

        int[] dijcstra(int[][] adjacency, int src){
            var distances=new int[adjacency.length];
            Arrays.fill(distances,Integer.MAX_VALUE);
            distances[src]=0;
            class Vertex implements Comparable<Vertex>{
                int dist;
                int vertexInd;
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

            PriorityQueue<Vertex> queue=new PriorityQueue<>(adjacency.length);
            for(int i=0;i<distances.length;++i){
                queue.add(new Vertex(distances[i],i));
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
                        distances[i]> current.dist+adjacency[current.vertexInd][i]){
                        var sibling= new Vertex(distances[i],i);
                        //System.out.println("======================");
                        //System.out.println("4: "+queue.contains(new Vertex(1,4)));
                        //System.out.println(sibling);
                        queue.remove(sibling);
                        sibling.dist=distances[i]=current.dist+adjacency[current.vertexInd][i];
                        //System.out.println(sibling);
                        queue.add(sibling);
                        //System.out.println("4: "+queue.contains(new Vertex(1,4)));
                        //System.out.println(queue.contains(sibling)+"\n======================");
                    }
                }
                //System.out.println("--------------------------------------------------------------");
                //System.out.println("Current="+current);
                //System.out.println("Distances: "+Arrays.toString(distances));
                //queue.forEach(System.out::println);
            }
            return distances;
        }
    }
}
