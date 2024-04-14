package org.leetcode;

import java.util.*;

public class ex332 {
    public static void main(String[] args){
        var sol= new Solution();
        List<List<String>> tickets=new ArrayList<List<String>>();
        String[][] ticketsArr=new String[][]{
                {"JFK","SFO"},{"JFK","ATL"},{"SFO","JFK"},{"ATL","AAA"},
        {"AAA","BBB"},{"BBB","ATL"},{"ATL","AAA"}
        ,{"AAA","BBB"},{"BBB","ATL"}
        };
        System.out.print(sol.findItinerary(Arrays.stream(ticketsArr).map(ticket->List.of(ticket[0],ticket[1])).toList()));
    } //модульный тест
    private static class Solution {
        public List<String> findItinerary(List<List<String>> tickets) {


            //silly, but works
            HashMap<String, ArrayList<String>> adjacencyMap = new HashMap<>();
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
            return path;
        }
    }
}
