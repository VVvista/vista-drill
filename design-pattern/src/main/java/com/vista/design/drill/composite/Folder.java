package com.vista.design.drill.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 容器类型
 * 文件夹类型，实现抽象类，添加
 *
 * @author WenTingTing by 2020/4/15
 */
public class Folder implements AbstractFile {
    private String name;
    private List<AbstractFile> list = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    /**
     * 添加结点
     *
     * @param file
     */
    public void add(AbstractFile file) {
        list.add(file);
    }

    /**
     * 删除结点
     *
     * @param file
     */
    public void remove(AbstractFile file) {
        list.remove(file);
    }

    /**
     * 获取结点
     *
     * @param i
     * @return
     */
    public AbstractFile get(int i) {
        return list.get(i);
    }

    /**
     * 实现公共方法
     */
    @Override
    public void killVirus() {
        for (AbstractFile file : list) {
            file.killVirus();
        }
    }
}
