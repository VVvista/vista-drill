package com.vista.drill.xiaochao.高频题;

/**https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days/
 * @author Wen TingTing by 2021/2/17
 */
public class 在D天内送达包裹的能力 {
    public int shipWithinDays(int[] weights, int D) {
        int left=getMax(weights);
        int right=getSum(weights)+1;
        while(left<right){
            int mid=(left+right)/2;
            if(canFinish(weights,mid,D)){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return left;
    }

    private boolean canFinish(int[] weights, int mid, int d) {
        int res=1;
        int reCap=0;
        for (int i = 0; i < weights.length; i++) {
            if(weights[i]+reCap<=mid){
                reCap+=weights[i];
            }else{
                res++;
                reCap=weights[i];
            }

        }
        return res<=d;
    }


    private int getSum(int[] weights) {
        int result=0;
        for (int i = 0; i < weights.length; i++) {
            result+=weights[i];
        }
        return result;
    }

    private int getMax(int[] weights) {
        int max=weights[0];
        for (int i = 1; i < weights.length; i++) {
            max=Math.max(weights[i],max);
        }
        return max;
    }
}
