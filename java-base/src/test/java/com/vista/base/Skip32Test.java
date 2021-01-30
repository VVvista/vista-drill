package com.vista.base;

import com.vista.java.Skip32;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 描述： Skip32 Test
 *
 * @author Li.Wei by 2020/5/30
 */
public class Skip32Test {

    /**
     * tests compatibility with
     * https://github.com/dgryski/dskip32/
     */
    @Test
    public void testSkip32Go() {
        byte[] key = {0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, (byte)0x88, (byte)0x99, (byte)0xAA};

        int id = (int)3493209676L;

        int c = Skip32.encrypt(id, key);

        int p = Skip32.decrypt(c, key);
        Assert.assertEquals(id, p);

        System.out.println(c);

        Assert.assertEquals(c, 0x6da27100);
    }

    /**
     * 测试 Skip32 加密结果重复性问题
     */
    @Test
    public void testSkip32Dup() {
        byte[] key = {0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, (byte)0x88, (byte)0x99, (byte)0xAA};
        int maxValue = Integer.MAX_VALUE;
        Set<Integer> dup = new HashSet<Integer>();

        System.out.println(maxValue);
        for (int i = 1000000000; i < maxValue; i++) {
            dup.add(Skip32.encrypt(i, key));
        }
        System.out.println(dup.size());
    }

    /**
     * 测试 Skip32 加密结果重复性问题
     */
    @Test
    public void testSkip32DupNew() {
        byte[] key = {0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, (byte)0x88, (byte)0x99, (byte)0xAA};
        int maxValue = Integer.MAX_VALUE;
        Set<Integer> dup = new HashSet<Integer>(maxValue -16,1f);

        // System.out.println(maxValue);
        for (int i = 100000000; i < maxValue; i++) {
            System.out.println(i);
            dup.add(Skip32.encrypt(i, key));
        }
        System.out.println(dup.size());
    }

    /**
     * tests compatibility with
     * https://github.com/alestic/Crypt-Skip32/
     */
    @Test
    public void testCryptSkip32() {
        byte[] key = {(byte)0xDE, 0x26, 0x24, (byte)0xBD, 0x4F, (byte)0xFC, 0x4B, (byte)0xF0, (byte)0x9D, (byte)0xAB};

        test(key, 0, 78612854);
        test(key, 3, (int)3719912389L);
        test(key, 21, 1463300585);
        test(key, 147, 1277082297);
        test(key, 1029, (int)2878029910L);
        test(key, 7203, (int)4086218104L);
        test(key, 50421, (int)2588160464L);
        test(key, 352947, (int)2703568194L);
        test(key, 2470629, (int)2600508864L);
        test(key, 17294403, (int)4119915301L);
        test(key, 121060821, (int)4266122367L);
        test(key, 847425747, (int)2671425558L);
        test(key, (int)4294967295L, 949651845);
    }

    private void test(byte[] key, int in, int out) {
        int c = Skip32.encrypt(in, key);
        Assert.assertEquals(out, c);
    }

    /**
     * tests compatibility with
     * https://opensource.qualcomm.com/assets/dotc/skip32.c
     */
    @Test
    public void testQualcommSkip32() {
        int[] in = {0x33, 0x22, 0x11, 0x00};
        byte[] key = {0x00, (byte)0x99, (byte)0x88, 0x77, 0x66, 0x55, 0x44, 0x33, 0x22, 0x11};

        Skip32.skip32(key, in, true);
        Assert.assertEquals(0x81, in[0]);
        Assert.assertEquals(0x9d, in[1]);
        Assert.assertEquals(0x5f, in[2]);
        Assert.assertEquals(0x1f, in[3]);

        Skip32.skip32(key, in, false);
        Assert.assertEquals(0x33, in[0]);
        Assert.assertEquals(0x22, in[1]);
        Assert.assertEquals(0x11, in[2]);
        Assert.assertEquals(0x00, in[3]);
    }
}