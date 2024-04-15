package org.leetcode;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ex332 {
    public static void main(String[] args) {
        var sol = new Solution();
        List<List<String>> tickets = new ArrayList<List<String>>();
        String[][] ticketsArr = new String[][]{
                {"JFK", "KUL"}, {"JFK", "NRT"}, {"NRT", "JFK"}
        };
        System.out.print(sol.findItinerary(Arrays.stream(ticketsArr).map(ticket -> List.of(ticket[0], ticket[1])).toList()));
    } //модульный тест

    private static class Solution {
        public List<String> findItinerary(List<List<String>> tickets) {
            //new idea
            //remembered that what we need to find is an Eulerian cycle

            //the essence of the algorithm: we do the same traversal as
            //the slow algorithm below, but without unnecessary actions with edges.
            //We will delete an edge and add the minimal (lexicographically)
            //neighboring vertex to the stack. Then build the path by adding
            //vertices from the end when the stack stops filling,
            //because the vertex will have no more edges.

            //Why this should work:
            //There can be only one "dead-end" branch in a graph,
            //where the Eulerian path can end.
            //1) If we go from a vertex to a branch hat has
            //no end to the Euler path, then we will put all the
            //necessary vertices on the stack and continue the traversal
            //in another branch until we reach the end, in which case
            // the stack will be filled with the desired path in the opposite direction
            //2) We entered a fork with an end to the path before we entered a
            // fork without an end to the path. Then we will put into the path
            // what we have already found, and then we will continue the search
            // in the remaining return branches and add their bypass to the beginning of the path
            HashMap<String, PriorityQueue<String>> adjacencyMap = new HashMap<>();
            String from;
            String to;
            for (var ticket : tickets) {
                from = ticket.getFirst();
                to = ticket.getLast();
                adjacencyMap.computeIfAbsent(from, key -> new PriorityQueue<>()).add(to);
            }
            //adjacencyMap.forEach((key, queue) -> System.out.println(key + ": " + queue));
            //System.out.println("=====================");
            Deque<String> stack = new ArrayDeque<>(tickets.size() + 1);
            var path = new LinkedList<String>();
            stack.add("JFK");
            String current;
            PriorityQueue<String> siblings;
            while (!stack.isEmpty()) {
                siblings = adjacencyMap.get(stack.peek());
                while (siblings != null && !siblings.isEmpty()) {
                    stack.push(current = siblings.remove());
                    siblings = adjacencyMap.get(current);
                }
                //System.out.println(stack);
                path.addFirst(stack.pop());
                //System.out.println(path);
                //System.out.println("------------");
            }
            return path;

                //silly, but works
                //bad decision to add to the stack all siblings at once,
                // but we can add one sibling node to stack and add another
                // when return to vertex and find this another sibling
                // in this case there are a lot of unnecessary actions with edges
            /*HashMap<String, ArrayList<String>> adjacencyMap = new HashMap<>();
            String from;
            String to;
            for (var ticket : tickets) {
                from = ticket.getFirst();
                to = ticket.getLast();
                adjacencyMap.computeIfAbsent(from, key -> new ArrayList<>()).add(to);
            }
            for (var entry : adjacencyMap.entrySet()) entry.getValue().sort((o1, o2) -> -o1.compareTo(o2));
            //adjacencyMap.forEach((key, queue) -> System.out.println(key + ": " + queue));
            //System.out.println("=====================");
            Deque<String> stack = new ArrayDeque<>(tickets.size() + 1);
            var path = new LinkedList<String>();
            stack.add("JFK");
            String current;
            int i = 0;
            while (!stack.isEmpty()) {
                current = stack.getLast();
                //System.out.println("Current: " + current);
                if (!path.isEmpty()) adjacencyMap.get(path.getLast()).remove(current);
                path.addLast(current);
                //System.out.println("Path: "+path);
                if (path.size() == tickets.size() + 1) {
                    return path;
                }
                var siblings = adjacencyMap.get(current);
                if (siblings == null || siblings.isEmpty()) {
                    while (stack.getLast().equals(path.getLast())) {
                        //System.out.println("REMOVE: "+path.getLast());
                        path.removeLast();
                        adjacencyMap.get(path.getLast()).add(stack.removeLast());
                        adjacencyMap.get(path.getLast()).sort((o1, o2) -> -o1.compareTo(o2));
                    }
                } else {
                    //System.out.println("Siblings: " + siblings);
                    stack.addAll(siblings.stream().distinct().toList());
                    //System.out.println("Stack: "+stack);}
                    //System.out.println("---------------");
                }
            }
            return path;*/
        }
    }
}
