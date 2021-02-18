package com.vista.drill.xiaochao.高频题;

/**
 * https://leetcode-cn.com/problems/koko-eating-bananas/
 *
 * @author Wen TingTing by 2021/2/16
 */
public class 爱吃香蕉的珂珂 {
    public int minEatingSpeed(int[] piles, int H) {
        int max = getMaxt(piles);
        int left = 1;
        int right = max + 1;
        while (left < right) {
            int mid=(left+right)/2;
            if(canFinsh(piles,mid,H)){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return left;

    }

    private boolean canFinsh(int[] piles, int mid, int h) {
        int result=0;
        for (int i = 0; i < piles.length; i++) {
            result+=Math.ceil((double)piles[i]/mid);
        }
        return result<=h;
    }


    private int getMaxt(int[] piles) {
        int max=piles[0];
        for (int i = 1; i < piles.length; i++) {
            max=Math.max(piles[i],max);
        }
        return max;
    }

}
