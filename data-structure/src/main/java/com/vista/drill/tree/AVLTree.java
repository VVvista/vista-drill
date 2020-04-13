package com.vista.drill.tree;

import java.util.Comparator;

/**
 * 平衡二叉树-AVL树
 * <p>
 * 特有概念：
 * 平衡因子：某结点的左右子树的高度差
 * <p>
 * 特点：
 * 1.AVL树是特殊的二叉搜索树，|平衡因子|<=1
 * 2.AVL树的在添加、删除操作之后再想方式调整二叉树恢复平衡（即减少树的高度）
 * 3.AVL树的搜索、添加、删除的时间复杂度：o(logn)
 * 4.添加操作可能导致所有祖先节点都失衡；父节点、非祖先节点否不可能失衡
 *
 *
 *
 * @author WenTingTing by 2020/4/13
 */
public class AVLTree<E> extends BinarySearchTree<E> {


    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }


}
