package graphs.leetcode.Yudin;

import java.util.Arrays;

public class ex2076 {
    public static void main(String[] args){
        var sol= new Solution();
        System.out.println(Arrays.toString(sol.friendRequests(
                5, new int[][]{{0, 1}, {1, 2}, {2, 3}}, new int[][]{{0, 4}, {1, 2}, {3, 1}, {3, 4}}
        )));
    } //модульный тест
    private static class Solution {
        public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
            //algorithm using disjoint unions
            final int[] representative=new int[n];
            for(int i=0;i<n;++i)representative[i]=i;
            boolean[] result=new boolean[requests.length];
            for(int i=0;i< requests.length;++i){
                var request=requests[i];
                result[i]=true;
                for(var restriction:restrictions){
                    if( representative[restriction[0]]==representative[request[0]]&&
                        representative[restriction[1]]==representative[request[1]]||
                        representative[restriction[0]]==representative[request[1]]&&
                        representative[restriction[1]]==representative[request[0]]
                    ) {
                        result[i]=false;
                        break;
                    }
                }
                if(result[i]){
                    var representativeToChange=representative[request[1]];
                    for(int j=0;j<representative.length;++j)
                        if(representative[j]==representativeToChange)
                            representative[j]=representative[request[0]];
                }
            }
            return result;

            //works but not effective
            /*ArrayList<HashSet<Integer>> hasPathTo = new ArrayList<>(n);
            for(int i=0;i<n;++i){
                var set=new HashSet<Integer>();
                set.add(i);
                hasPathTo.add(set);
            }
            boolean[] result=new boolean[requests.length];
            for(int i=0;i< requests.length;++i){
                var request=requests[i];
                result[i]=true;
                for(var restriction:restrictions){
                    if(
                        hasPathTo.get(request[0]).contains(restriction[0]) &&
                        hasPathTo.get(request[1]).contains(restriction[1]) ||
                        hasPathTo.get(request[1]).contains(restriction[0]) &&
                        hasPathTo.get(request[0]).contains(restriction[1])
                    ) {
                        result[i]=false;
                        break;
                    }
                }
                if(result[i]){
                    hasPathTo.get(request[0]).addAll(hasPathTo.get(request[1]));
                    var friendsSet=hasPathTo.get(request[0]);
                    for(int friend:hasPathTo.get(request[1]))hasPathTo.set(friend,friendsSet);
                }
            }
            return result;*/
        }
    }
}
