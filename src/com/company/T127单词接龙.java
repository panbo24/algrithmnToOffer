package com.company;

import java.util.*;

public class T127单词接龙 {
    //BFS 辅助DS：队列、集合(标记visited)
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //第一步：先将wordList放到hash表里，便于判断某个单词是否在wordlist里面
        Set<String> wordSet = new HashSet<String>(wordList);
        if(wordSet.size() == 0 || !wordSet.contains(endWord)){
            return 0;
        }
        wordSet.remove(beginWord);
        //第二步：图的广度优先遍历，必须使用队列和表示是否访问过的visited表
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        //第三步开始BFS，包含起点，因此初始化时步数为1
        int step = 1;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                String currentWord = queue.poll();
                //先匹配出set中有的 未访问过的入队， 如果与end相同，则返回step + 1,
                if (changeWordEveryOneLetter(currentWord, endWord, queue, visited, wordSet)){
                    return step + 1;
                }
            }
            step++;
        }
        return 0;
    }

    private boolean changeWordEveryOneLetter(String currentWord, String endWord, Queue<String> queue, Set<String> visited, Set<String> wordSet) {
        char[] charArray = currentWord.toCharArray();
        for (int i = 0; i < charArray.length; i++){
            //先保存,然后恢复
            char originChar = charArray[i];
            for (char k = 'a'; k <= 'z'; k++) {
                if (k == originChar){
                    continue;
                }
                //换
                charArray[i] = k;
                String nextWord = String.valueOf(charArray);
                if (wordSet.contains(nextWord) && !visited.contains(nextWord)){
                    if (nextWord.equals(endWord)){
                        return true;
                    }
                    queue.add(nextWord);
                    visited.add(nextWord);
                }
            }
            //恢复
            charArray[i] = originChar;
        }
        return false;
    }
    //方法二：双向队列


}
