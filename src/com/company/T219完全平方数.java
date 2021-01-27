package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 类名: T219完全平方数
 * 描述: TODO
 * 姓名: 科布
 * 日期: 2021-01-25 14:50
 **/
//方法一：组合 = 递归 =栈超时  因为有太多重复的栈调用
// numSquares(n)=min(numSquares(n-k) + 1)∀k∈square numbers
//方法二：重用中间解的结果来计算终解
//
public class T219完全平方数 {
//    public int numSquares(int n) {
//
//        int dp[] = new int[n + 1];
//        Arrays.fill(dp, Integer.MAX_VALUE);
//
//        //bottom case
//        dp[0] = 0;
//        //预计算平方数, 确定边界
//        int max_square_index = (int)Math.sqrt(n) + 1;
//        int square_nums[] = new int[max_square_index];
//        for (int i = 1; i < max_square_index; i++){ //从1起步
//            square_nums[i] = i * i;
//        }
//
//        for (int i = 1; i <=n; i++){
//            for (int s = 1; s < max_square_index; s++){
//                if (i < square_nums[s]){
//                    break;
//                }
//                //状态转移方程
//                dp[i] = Math.min(dp[i], dp[i - square_nums[s]] + 1);
//            }
//        }
//        return dp[n];
//    }
//    // 方法三：贪心 + 递归，减少栈溢出的可能性
//    Set<Integer> square_nums = new HashSet<>();
//
//    public int numSquares(int n) {
//        //1、准备平方列表
//        //2、主循环 对count进行循环，但是是贪心的形式
//        //3、判断是否可以分，还是用递归，边界条件就是 最后一个数是否在集合内
//        this.square_nums.clear();
//        for (int i = 1; i * i <= n; i++) {
//            square_nums.add(i * i);
//        }
//
//        int count = 1;
//        for (; count <= n; count++) {
//            if (is_divided_buy(n, count))
//                return count;
//        }
//        return count;
//
//    }
//
//    private boolean is_divided_buy(int n, int count) {
//        if (count == 1) {
//            return square_nums.contains(n);
//
//        }
//        for (Integer square : square_nums){
//            if(is_divided_buy(n - square, count - 1)){
//                return true;
//            }
//        }
//        return false;
//    }

    //使用队列
    public int numSquares(int n) {
        ArrayList<Integer> square_nums = new ArrayList<Integer>();
        for (int i = 1; i * i <= n; ++i) {
            square_nums.add(i * i);
        }

        //set用来去重
        Set<Integer> queue = new HashSet<Integer>();
        queue.add(n);

        int level = 0;
        while (queue.size() > 0) {
            level += 1;
            Set<Integer> next_queue = new HashSet<Integer>();

            for (Integer remainder : queue) {
                for (Integer square : square_nums) {
                    //重点就是找到这个，广度优先
                    if (remainder.equals(square)){
                        return level;
                    }else if (remainder < square){
                        break;
                    }else {
                        next_queue.add(remainder - square);
                    }
                }
            }
            queue = next_queue;
        }
        return level;
    }
}

