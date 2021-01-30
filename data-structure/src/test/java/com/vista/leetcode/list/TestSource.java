package com.vista.leetcode.list;


import com.vista.drill.raise.gDFS.e_路径总和2;
import com.vista.drill.raise.gDFS.f_组合总和;
import com.vista.drill.raise.h高频题.c_三数之和;
import com.vista.drill.raise.h高频题.d_pow;
import com.vista.leetcode.stack.M_856_括号的分数;
import org.junit.Test;

/**
 * @author WenTingTing by 2020/3/25
 */
public class TestSource {
    @Test
    public void test() {
        M_856_括号的分数 n = new M_856_括号的分数();
        int i = n.scoreOfParentheses("()()");
        System.out.println(i);
    }

    @Test
    public void test1() {
     /*   e_路径总和2 n = new e_路径总和2();
        int i = n.pathSum(new int[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1}, 22);
        System.out.println(i);*/
    }


    @Test
    public void test2() {
        f_组合总和 n = new f_组合总和();
        System.out.println(n.combinationSum(new int[]{2, 3, 6, 7}, 7));
    }
    @Test
    public void test3() {

        d_pow n = new d_pow();
        System.out.println(n.myPow(2,10));
    }

}
