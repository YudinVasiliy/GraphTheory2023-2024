package org.leetcode;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ex380 {
    public static void main(String[] args){
        RandomizedSet obj = new RandomizedSet();
        System.out.println(obj.insert(1));//t
        System.out.println(obj.remove(1));//t
        System.out.println(obj.insert(1));//t
        System.out.println(obj.insert(1));//f
        System.out.println(obj.remove(2));//f

        System.out.println(obj.insert(1));//f
        System.out.println(obj.insert(2));//t
        System.out.println(obj.insert(3));//t

        System.out.println(obj.getRandom());
        System.out.println(obj.getRandom());
        System.out.println(obj.getRandom());

    } //модульный тест
    private static class RandomizedSet {
        private final Map<Integer,Integer> indexOfValueMap;
        private final ArrayList<Integer> values;
        public RandomizedSet() {
            indexOfValueMap = new HashMap<Integer,Integer>();
            values = new ArrayList<>();
        }
        public boolean insert(int val) {
            if(indexOfValueMap.containsKey(val))return false;
            values.add(val);
            indexOfValueMap.put(val,values.size()-1);
            return true;
        }

        public boolean remove(int val) {
            if(!indexOfValueMap.containsKey(val))return false;
            var indexToDelete = indexOfValueMap.get(val);
            if(indexToDelete!=values.size()-1){
                var newVal = values.get(values.size()-1);
                values.set(indexToDelete, newVal);
                indexOfValueMap.put(newVal, indexToDelete);
            }
            values.remove(values.size()-1);
            indexOfValueMap.remove(val);
            return true;
        }

        public int getRandom() {
            return values.get(new Random().nextInt(values.size()));
        }
    }
}
