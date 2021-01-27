package com.company;

import java.util.*;
import java.util.zip.ZipInputStream;

public class T347topK频率元素 {
//    //思路2：维护优先队列(堆)，时间复杂度O(nlogK)
//    public int[] topKFrequent(int[] nums, int k) {
//        //第一步 构建map获得出现次数
//        Map<Integer, Integer> occurences = new HashMap<Integer, Integer>();
//        for (int num : nums){
//            //getOrDefault
//            occurences.put(num, occurences.getOrDefault(num, 0) + 1);
//        }
//
//        //第二步 写一个优先队列和comparator
//        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                //根据出现频率升序
//                return o1[1] - o2[1];
//            }
//        });
//
//        //第三步 开始遍历Map，并维护长度为k的优先队列
//        for (Map.Entry<Integer, Integer> entry : occurences.entrySet()) {
//            int num = entry.getKey(), count = entry.getValue();
//            if (queue.size() == k){
//                if (count > queue.peek()[1]){
//                    queue.poll();
//                    queue.offer(new int[]{num, count});
//                }
//            }else {
//                queue.offer(new int[]{num, count});
//            }
//        }
//        //第四步 用优先队列 构建数组
//        int[] ret = new int[k];
//        for (int i = 0; i < k; i++) {
//            ret[i] = queue.poll()[0];
//        }
//        return ret;
//    }
    // 方法二：快排
    public int[] topKFrequent(int[] nums, int k) {
        //第一步 构建map获得出现次数
        Map<Integer, Integer> occurences = new HashMap<Integer, Integer>();
        for (int num : nums){
            //getOrDefault
            occurences.put(num, occurences.getOrDefault(num, 0) + 1);
        }
        //第二部 map转成数组列表
        List<int[]> values = new ArrayList<int[]>();
        for (Map.Entry<Integer, Integer> entry : occurences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            values.add(new int[]{num, count});
        }
        //第三步 快排 并 通过递归获得最后结果数组
        int[] ret = new int[k];
        qsort(values, 0, values.size() - 1, ret, 0, k);//倒数第二个是结果数组的指针
        return ret;
    }

    private void qsort(List<int[]> values, int start, int end, int[] ret, int retIndex, int k) {
        //因为double转int是直接去掉小数部分的，所以要取到end的话，要+1
        int random = (int)(Math.random()*(end - start + 1)) + start;
        Collections.swap(values, start, random);

        //设枢纽
        int pivot = values.get(start)[1];
        int index = start;
        //快分
        for (int i = start + 1; i <= end; i++) {
            if (values.get(i)[1] >= pivot){
                Collections.swap(values, index + 1, i);
                index++;
            }
        }
        //枢纽归位
        Collections.swap(values, start, index);

        //*** 判断index - start 与 k 的关系
        if (k <= index - start) {
            qsort(values, start, index - 1, ret, retIndex, k);
        }else {
            //先把最大的一些割韭菜  构建数组(这里从start到index的个数可能等于k)
            for (int i = start; i <= index; i++) {
                ret[retIndex++] = values.get(i)[0];
            }
            //个数不等于k的话
            if (k > index - start + 1){
                qsort(values, index + 1, end, ret, retIndex, k -(index - start + 1));
            }
        }
    }

}
