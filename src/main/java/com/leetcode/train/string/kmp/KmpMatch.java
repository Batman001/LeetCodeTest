package com.leetcode.train.string.kmp;

/**
 * @author Batman on 2019/1/8.
 * 字符串匹配算法
 */
public class KmpMatch {
    /**
     * 模式字符串当匹配不成功的时, j需要移动的位置 列表计算
     * 索引从0开始
     * @param ps 模式串
     * @return
     */
    private int[] getNext(String ps){
        char[] p = ps.toCharArray();

        int[] next = new int[p.length];

        next[0] = -1;

        int j = 0;

        int k = -1;

        while(j < p.length - 1){
            if(k == -1  || p[j] == p[k]){
                next[++j] = ++k;
            }else{
                k = next[k];
            }
        }
        return next;
    }

    /**
     * KMP算法入口
     * @param ts 主字符串
     * @param ps 模式串
     * @return 返回匹配成功后的模式串在子串中开始的位置
     */
    private int Kmp(String ts, String ps){
        char[] t = ts.toCharArray();
        char[] p = ps.toCharArray();

        // 主串的位置
        int i = 0;

        // 模式串的位置
        int j = 0;

        int[] next = getNext(ps);

        while(i < t.length && j < p.length){
            if(j == -1 || t[i] == p[j]){
                i ++;
                j ++;
            }else{
                // i不需要回溯
//                i = i -j + 1;
                // j回溯到指定位置
                j = next[j];
            }
        }

        if(j == p.length){
            return i - j;
        }else{
            return -1;
        }
    }

    public static void main(String[] args) {
        String ts = "abbaabbaaba";
        String ps = "abbaaba";
        System.out.println(new KmpMatch().Kmp(ts, ps));
    }
}

