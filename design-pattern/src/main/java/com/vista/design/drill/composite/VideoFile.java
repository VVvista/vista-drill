package com.vista.design.drill.composite;

/**
 * 叶子结点
 * 文件类型，实现抽象类，重写公共方法
 *
 * @author WenTingTing by 2020/4/15
 */
public class VideoFile implements AbstractFile {
    private String name;

    public VideoFile(String name) {
        this.name = name;
    }

    /**
     * 实现公共方法逻辑
     */
    @Override
    public void killVirus() {
        System.out.println("开始进行-" + name + "-文件杀毒");
    }
}
