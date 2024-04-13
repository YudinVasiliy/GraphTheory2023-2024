package org.leetcode;


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
            //a little optimized by myself someone else's algorithm, which is the best
            double[] maxProb = new double[n];
            maxProb[start_node] = 1.0;
            boolean hasUpdate=true;
            int u;
            int v;
            double pathProb;
            for (int i = 0; i < n - 1 && hasUpdate; ++i) {
                hasUpdate = false;
                for (int j = 0; j < edges.length; ++j) {
                    u = edges[j][0];
                    v = edges[j][1];
                    pathProb = succProb[j];
                    if (maxProb[u] * pathProb > maxProb[v]) {
                        maxProb[v] = maxProb[u] * pathProb;
                        hasUpdate = true;
                    }
                    if (maxProb[v] * pathProb > maxProb[u]) {
                        maxProb[u] = maxProb[v] * pathProb;
                        hasUpdate = true;
                    }
                }
            }
            return maxProb[end_node];

            //my slow algorithm, a lot of unnecessary actions and Dijcstra's algorithm is not suitable for this case
            /*class EdgeToVertex implements Comparable<EdgeToVertex>{
                double probability;
                int endVertexInd;

                public EdgeToVertex(double probability, int eVertexInd) {
                    this.probability = probability;
                    this.endVertexInd = eVertexInd;
                }
                @Override
                public int compareTo(EdgeToVertex o) {
                    return -Double.compare(probability, o.probability);
                }
                @Override
                public boolean equals(Object o) {
                    if (!(o instanceof EdgeToVertex vertex)) return false;
                    //System.out.println("EQUALS: "+vertexInd+", "+vertex.vertexInd);
                    return endVertexInd == vertex.endVertexInd;
                }
                @Override
                public int hashCode() {
                    return Objects.hash(endVertexInd);
                }
            }
            HashMap<Integer, ArrayList<EdgeToVertex>> edgeToVertexMap=new HashMap<>();
            for(int i=0;i< edges.length;++i){
                edgeToVertexMap.computeIfAbsent(edges[i][0], m -> new ArrayList<>()).add(new EdgeToVertex(succProb[i], edges[i][1]));
                edgeToVertexMap.computeIfAbsent(edges[i][1], m -> new ArrayList<>()).add(new EdgeToVertex(succProb[i], edges[i][0]));
            }
             */
        }
    }
}
