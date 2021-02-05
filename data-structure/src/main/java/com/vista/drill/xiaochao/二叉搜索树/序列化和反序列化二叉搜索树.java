package com.vista.drill.xiaochao.二叉搜索树;


import com.vista.drill.base.list.linkedlist.LinkedList;

/**https://leetcode-cn.com/problems/serialize-and-deserialize-bst/
 * @author Wen TingTing by 2021/2/4
 */
public class 序列化和反序列化二叉搜索树 {

    // Encodes a tree to a single string.
// 前序遍历
    /**
     * 序列化
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        serialize(root,sb);
        return sb.toString();
    }
    public void serialize(TreeNode root,StringBuffer sb ){
        if(root==null) {
            sb.append("#").append(",");
            return;
        }
        serialize(root.left,sb);
        serialize(root.right,sb);
        sb.append(root.val).append(",");
    }

    // Decodes your encoded data to tree.

    /**
     * 反序列化
     * @param data
     * @return
     */
    public TreeNode deserialize(String data) {
        String[] strings = data.split(",");
        LinkedList<String> list = new LinkedList<String>();
        for (String str:strings) {
            list.add(str);
        }
        return deserialize(list);

    }
    public TreeNode deserialize(LinkedList list){
        if(list.size()==0) return null;
        // 获取最后一个元素
        String head = (String) list.remove(list.size()-1);
        if(head.equals("#")) return null;
        TreeNode node=new TreeNode(Integer.parseInt(head));
        // 先遍历右子树
        node.right=deserialize(list);
        // 再遍历左子树
        node.left=deserialize(list);
        return node;
    }

// 前序遍历
    /**
     * 序列化
     * @param root
     * @return
     */
    public String serialize1(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        serialize1(root,sb);
        return sb.toString();
    }
    public void serialize1(TreeNode root,StringBuffer sb ){
        if(root==null) {
            sb.append("#").append(",");
            return;
        }
        sb.append(root.val).append(",");
        serialize1(root.left,sb);
        serialize1(root.right,sb);
    }

    // Decodes your encoded data to tree.

    /**
     * 反序列化
     * @param data
     * @return
     */
    public TreeNode deserialize1(String data) {
        String[] strings = data.split(",");
        LinkedList<String> list = new LinkedList<String>();
        for (String str:strings) {
            list.add(str);
        }
        return deserialize1(list);

    }
    public TreeNode deserialize1(LinkedList list){
        if(list.size()==0) return null;
        String head = (String) list.remove(0);
        if(head.equals("#")) return null;
        TreeNode node=new TreeNode(Integer.parseInt(head));
        node.left=deserialize1(list);
        node.right=deserialize1(list);
        return node;
    }
}
