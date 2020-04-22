package com.vista.drill.map;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * 哈希表（HashMap）
 * 也成为散列表，与TreeMap不同
 * 1.map中存储的元素无需讲究排序；
 * 2.key无需具备可比较性
 * 查询、添加、删除的时间复杂度为:O(1)
 * <p>
 * HashMap的运行原理：
 * key-> hashCode -> table[index]
 * 1.根据key生成对应的hash值，hash与数组长度结合确定key在数组中的索引位置index，时间复杂度为：O(1)
 * 数组的每个位置元素也较桶bucket，整个数组叫buckets 或者 bucket Array
 * <p>
 * 问题：不同key的hashCode可能相同，造成table[index]相同，此时称为哈希冲突
 * 解决方法：开放地址法、再哈希法、链表地址法(jdk\本文方式：通过链表将同一index的元素连接起来：链表、红黑树)
 * jdk1.8底层采用链表+红黑树的方式：table.length>=64 && 链表结点>=8，扩容为原来的两倍 << 1
 * <p>
 * 注意：
 * 1.hashConde为int型，不管key是基本数据类型、引用类型，最终计算的hashCode必为int型(32位)
 * 2.基本类型、引用类型：重写hashCode()、equals()方法
 * hashCode()：默认为地址值的hashCode,为了确定元素table[index]
 * equals():默认比较地址值，为了不添加相同的key
 * 注：如果底层用红黑树解决哈希冲突，而KEY类不要求具有实现comparable接口，
 * 所以在查找是否有相同key时，红黑树中的根据compare大小判断是左查找还是右查找就需要分情况判断(此部分比较复杂)，添加时亦然。
 * <p>
 * <p>
 * 声明：本实现底层仅用红黑树解决哈希冲突，以及扩容，未使用链表
 *
 * @author WenTingTing by 2020/4/22
 */
public class HashMap<K, V> implements Map<K, V> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private int size;
    private HashNode<K, V>[] table;

    private static final int DEFAULT_CAPACITY = 1 << 4;//数组默认长度，16
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * 默认数组长度为16
     */
    public HashMap() {
        this.table = new HashNode[DEFAULT_CAPACITY];
    }

    /**
     * 建议数组长度为2^n
     *
     * @param length
     */
    public HashMap(int length) {
        this.table = new HashNode[length];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * table[index]=null 而不是 table=null
     */
    @Override
    public void clear() {
        if (size == 0) return;
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
        size = 0;
    }

    /**
     * 添加元素
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public V put(K key, V value) {
        int index = index(key);// 获取key对应的索引
        int h1 = hash(key);
        HashNode<K, V> node = table[index];// 取出index位置的红黑树根节点
        // 如果根节点为null，添加到根节点
        if (node == null) {
            table[index] = new HashNode<>(key, value, null);
            size++;
            fixAfterPut(node);
            return null;
        }
        HashNode<K, V> parent = node;
        HashNode<K, V> result = null;
        boolean searched = false; // 是否已经搜索过这个key
        int cmp = 0;
        // 红黑树不为空,找到元素待添加位置
        while (node != null) {
            parent = node;
            int h2 = node.hash;
            K k2 = node.key;
            if (h1 < h2) {
                cmp = -1;
            } else if (h1 > h2) {
                cmp = 1;
            } else if (Objects.equals(key, k2)) {
                cmp = 0;
            } else if (key != null && k2 != null
                    && key.getClass() == k2.getClass()
                    && (cmp = ((Comparable) key).compareTo(k2)) != 0) {
            } else if (searched) {// 已经扫描了
                cmp = System.identityHashCode(key) - System.identityHashCode(k2);
            } else if (node.right != null && (result = node(node.right, key)) != null
                    && node.left != null && (result = node(node.left, key)) != null) {
                node = result;
                cmp = 0;
            } else {
                searched = true;
                cmp = System.identityHashCode(key) - System.identityHashCode(k2); //根据内存地址计算出的hashcode
            }

            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                V oldValue = node.value;
                node.key = key;
                node.value = value;
                return oldValue;
            }
        }
        HashNode<K, V> newNode = new HashNode<>(key, value, parent);
        red(newNode);
        // 添加新元素
        if (cmp < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        size++;

        // 添加完成后进行颜色调整
        fixAfterPut(newNode);
        return null;
    }

    /**
     * 节点添加后,调整红黑树颜色
     * 添加新节点操作，默认节点颜色为red
     * 处理逻辑同RBTree
     *
     * @param node
     */
    public void fixAfterPut(HashNode<K, V> node) {
        HashNode<K, V> parent = node.parent;

        // node为根结点，直接染成黑色，并退出
        if (parent == null) {
            black(node);
            return;
        }

        // 情况1：parent为black ，直接添加
        if (isBlack(parent)) return;

        HashNode<K, V> uncle = parent.sibling();
        HashNode<K, V> grand = red(parent.parent);
        // 情况2：parent为red，uncle为red
        if (isRED(uncle)) {
            // parent、uncle染为black,parent.parent向上合并
            black(parent);
            black(uncle);
            fixAfterPut(grand);
            return;
        }

        // 情况3：parent为red，uncle不为red(叔父节点不存在或black)
        // 旋转+grand、parent/node染色
        if (grand.isLeftChild()) {
            if (parent.isLeftChild()) {
                //LL
                black(parent);
            } else {
                //LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else {
            if (parent.isLeftChild()) {
                //RL
                black(node);
                rotateRight(parent);
            } else {
                //RR
                black(parent);
            }
            rotateLeft(grand);
        }
    }

    /**
     * 扩若操作：
     * 负载因子=节点总数量/哈希表桶数组长度>=0.75 ，数组长度扩若为原来的2倍
     * 操作：
     * 1.创建新数组，将原数组节点移动到新数组中
     * 注意：扩若为原2倍，节点所在索引共2中情况：
     * 1.索引不变
     * 2.index=index+原数组长度
     */
    private void resize() {
        int balance = size / table.length;
        // 扩若
        if (balance >= DEFAULT_LOAD_FACTOR) {
            // 创建新数组
            HashNode[] newTable = new HashNode[table.length << 1];
            HashNode<K, V>[] oldTable = this.table;
            this.table = newTable;
            Queue<HashNode<K, V>> queue = new LinkedList<>();
            for (int i = 0; i < oldTable.length; i++) {
                HashNode<K, V> root = table[i];
                if (root == null) continue;
                queue.offer(root);
                while (!queue.isEmpty()) {
                    HashNode<K, V> poll = queue.poll();
                    if (poll.left != null) {
                        queue.offer(poll.left);
                    }
                    if (poll.right != null) {
                        queue.offer(poll.right);
                    }
                    moveNode(poll);
                }
            }
        }
        // 将就数组元素移动到新数组
    }

    /**
     * 将结点移动到新数组中
     *
     * @param newNode
     */
    private void moveNode(HashNode<K, V> newNode) {
        newNode.parent = null;
        newNode.left = null;
        newNode.right = null;
        newNode.color = RED;

        int index = index(newNode.key);
        HashNode<K, V> root = table[index];
        // 添加节点为根节点
        if (root == null) {
            table[index] = newNode;
            fixAfterPut(newNode);
            return;
        }

        // 添加的不为根节点
        int h1 = newNode.hash;
        K k1 = newNode.key;
        HashNode<K, V> node = root;
        HashNode<K, V> parent = node;
        int cmp = 0;
        while (node != null) {
            K k2 = node.key;
            int h2 = node.hash;
            parent = node;
            if (h1 < h2) {
                cmp = -1;
            } else if (h1 > h2) {
                cmp = 1; // 不用再比较equals，扩若不存在key相等的情况，因此不会存在重复数据。
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) {
            } else {// 搜索也不需要，原因同上。
                cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            }

            if (cmp < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        // 看看插入到父节点的哪个位置
        if (cmp < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        newNode.parent = parent;

        // 新添加节点之后的处理
        fixAfterPut(newNode);
    }

    /**
     * 查询元素
     *
     * @param key
     * @return
     */
    @Override
    public V get(K key) {
        HashNode<K, V> node = node(key);
        return node == null ? null : node.value;
    }

    /**
     * 根据key查询数组table中是否存在相同key
     * 1.先确定key所在table[index]
     * 2.从table[index]的root开始查找是否存在相同key
     *
     * @param key
     * @return 返回key所在的节点
     */
    private HashNode<K, V> node(K key) {
        // 先确定key所在index的头结点
        HashNode<K, V> root = table[index(key)];
        return root == null ? null : node(root, key);
    }

    /**
     * * 根据key查询数组桶table[index]是否存在相同key节点
     * 1.比较k1和k2的结点的hash值
     * 1.1如果 h1<h2 ，去k2的左子树上查找
     * 1.2如果 h1>h2 ，去k2的右子树上查找
     * 1.3如果 h1>h2 且 k1.equals(k2)，查找成功，返回k2
     * 1.4如果 h1>h2 且 !k1.equals(k2)：判断k1 k2 是否可比较
     * 1.4.1 k1、k2可比较，则根据比较值决定去左子树或右子树，注意比较值=0，并不能判定两者相同，hashMap中只能是根据equals决定k1、k2相等。
     * 1.5否则，遍历右子树查找，如果查找到则返回
     * 1.6去左子树查找
     * <p>
     * 精髓：
     * 1.根据hash值确定去左子树或右子树查找，若不能决定则遍历查找；
     * 2.只能依据equals()判定key是否相同，compareTo()=0不能判定相同
     *
     * @param node
     * @param k1
     * @return
     */
    private HashNode<K, V> node(HashNode<K, V> node, K k1) {
        // k1的hash值
        int h1 = hash(k1);
        int cmp = 0;
        while (node != null) {

            K k2 = node.key;
            int h2 = node.hash;
            if (h1 < h2) {
                node = node.left;
            } else if (h1 > h2) {
                node = node.right;
            } else if (Objects.equals(k1, k2)) {//可比较性
                return node;
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) {
                node = cmp > 0 ? node.right : node.left;
            }
            // 走到这里，表示哈希值相等，不具备可比较性，也不 equals
            else if (node.right != null && (node = node(node.right, k1)) != null) {//先找右子树
                return node;
            } else {// 再去左边扫码
                node = node.left;
            }
        }
        return null;
    }

    /**
     * 查询key所在table[index]
     * 1.先计算key的hash值
     * 2.再与数组长度进行二次位运算
     *
     * @param key
     * @return
     */
    private int index(K key) {
        return hash(key) & (table.length - 1);
    }

    /**
     * 计算key的hash值
     * 为了使得key的所有信息都参与运算，在key.hashCode()基础上再使其值二进制：前16位 ^ 后16位 结果为key的hash值
     *
     * @param key
     * @return
     */
    private int hash(K key) {
        int hash = key.hashCode();
        return hash ^ (hash >> 16);//高16位与低16位做一次运算
    }

    /**
     * 删除key
     * 1.查找key所在的节点
     * 2.如果节点存在，则删除该结点（删除操作与二叉树的删除操作相同）
     *
     * @param key
     * @return
     */
    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    /**
     * 删除node节点，删除逻辑与二叉树的删除逻辑相同
     * 删除完成后再调整节点颜色
     */
    public V remove(HashNode<K, V> node) {
        if (node == null) return null;
        V oldValue = node.value;
        size--;
        //节点度为2
        if (node.hasTwoChildren()) {
            // 找到后继节点
            HashNode<K, V> successor = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.key = successor.key;
            node.value = successor.value;
            node.hash = successor.hash;
            // 删除后继节点
            node = successor;
        }
        //  删除node节点（node的度必然是1或者0）
        HashNode<K, V> replacement = node.left == null ? node.right : node.left;
        HashNode<K, V> parent = node.parent;
        int index = index(node.key);
        if (replacement != null) {// 度为1
            // parent子节点指向replacement
            if (parent == null) {
                table[index] = replacement;
            } else if (node.isLeftChild()) {
                parent.left = replacement;
            } else {
                parent.right = replacement;
            }
            // replacement.parent 指向parent
            replacement.parent = parent;

            // 颜色调整
            fixAfterRemove(replacement);
        } else if (parent == null) { // node是叶子节点并且是根节点
            table[index] = null;
        } else {// node是叶子节点，但不是根节点
            // 将parent对应的子节点置为null
            if (node.isLeftChild()) {
                parent.left = null;
            } else {
                parent.right = null;
            }
            // 删除节点之后的处理
            fixAfterRemove(node);
        }
        return oldValue;

    }

    /**
     * 节点删除后的颜色调整操作，处理逻辑等同于RBTree.afterRemove(HashNode<K, V> node)
     *
     * @param node
     */
    public void fixAfterRemove(HashNode<K, V> node) {
        // 删除结点为red，直接返回;
        // 删除结点为black，替换结点为red->此种情况下参数 node = 替换red结点
        if (isRED(node)) {
            black(node);
            return;
        }

        // 删除节点为black且没有任何子节点，进行下溢调整操作
        // 兄弟结点
        HashNode<K, V> parent = node.parent;

        // 删除根结点
        if (parent == null) return;

        // 删除非根节点
        // 判断被删除节点是左节点还是右节点
        boolean left = parent.left == null;
        HashNode<K, V> sibling = left ? parent.right : parent.left;

        if (left) {// 被删除结点为左节点，兄弟结点为右节点
            // node  sibling
            if (isRED(sibling)) { // 兄弟节点为红色，此时兄弟节点一定有两个black子节点，否则破坏了红黑树性质4
                //parent左旋，RR
                rotateLeft(parent);
                black(sibling);
                red(parent);
                // 重新赋值sibling，此时的sibling必为black，再按照sibling为black进行操作
                sibling = node.right;
            }
            if (isBlack(sibling.left) && isBlack(sibling.right)) {// sibling不存在子节点，此时叶子结点必然为黑色
                // 兄弟节点没有子节点
                Boolean black = isBlack(parent);
                //parent与sibling合并
                black(parent);
                red(sibling);
                // 如果父节点为black，则可能造成下溢，所以需要再次循环判断
                if (black) {
                    fixAfterRemove(parent);
                }
                // 如果父节点为red，则合并操作一定不会造成下溢
            } else {// 兄弟节点至少有一个结点，向兄弟节点借节点
                if (isRED(sibling.left)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }
                color(sibling, colorOf(parent));
                rotateLeft(parent);
                black(parent);
                black(sibling.right);
            }
        } else {// 被删除结点为右节点，兄弟结点为左节点
            // sibling  node
            if (isRED(sibling)) { // 兄弟节点为红色，此时兄弟节点一定有两个black子节点，否则破坏了红黑树性质4
                //parent右旋，LL
                rotateRight(parent);
                black(sibling);
                red(parent);
                // 重新赋值sibling，此时的sibling必为black，再按照sibling为black进行操作
                sibling = node.left;
            }
            //兄弟结点为black
            if (isBlack(sibling.left) && isBlack(sibling.right)) {// sibling不存在子节点，此时叶子结点必然为黑色
                // 兄弟节点没有子节点
                Boolean black = isBlack(parent);
                //parent与sibling合并
                black(parent);
                red(sibling);
                // 如果父节点为black，则可能造成下溢，所以需要再次循环判断
                if (black) {
                    fixAfterRemove(parent);
                }
                // 如果父节点为red，则合并操作一定不会造成下溢
            } else {// 兄弟节点至少有一个结点，向兄弟节点借节点
                if (isRED(sibling.left)) {
                    rotateLeft(sibling);
                    sibling = parent.left;
                }
                color(sibling, colorOf(parent));
                rotateRight(parent);
                black(parent);
                black(sibling.left);
            }
        }
    }


    /**
     * 查找结点的后继结点
     * 处理逻辑等同于RBTree.afterRemove(HashNode<K, V> node)
     *
     * @param node
     * @return
     */
    protected HashNode<K, V> successor(HashNode<K, V> node) {
        if (node == null) {
            return null;
        }
        // 如果结点有右子树，则找右子树的最左结点
        HashNode<K, V> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }
        // 如果结点不存在左子树，则向上寻找父节点
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }


    /**
     * 查找key
     *
     * @param key
     * @return
     */
    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    /**
     * 查找value
     * 思路：
     * 1.遍历table中所有index的红黑树节点，找到对应节点
     * 红黑树节点的查找此处使用 层次遍历
     *
     * @param value
     * @return
     */
    @Override
    public boolean containsValue(V value) {
        if (size == 0) return false;
        for (int i = 0; i < table.length; i++) {
            Queue<HashNode<K, V>> queue = new LinkedList<>();
            HashNode<K, V> root = table[i];
            if (root == null) continue;
            queue.offer(root);
            while (!queue.isEmpty()) {
                HashNode<K, V> poll = queue.poll();
                if (Objects.equals(value, poll.value)) {
                    return true;
                }
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
        }
        return false;
    }

    /**
     * 遍历元素
     * 此处使用层次遍历
     *
     * @param visitor
     */
    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (size == 0) return;
        Queue<HashNode<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            HashNode<K, V> root = table[i];
            if (root == null) continue;
            queue.offer(root);
            while (!queue.isEmpty()) {
                HashNode<K, V> poll = queue.poll();
                visitor.visit(poll.key, poll.value);
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
        }
    }

    /**
     * 结点进行右旋转
     * 处理逻辑等同于BBSTree
     *
     * @param grand
     */
    public void rotateRight(HashNode<K, V> grand) {
        HashNode<K, V> parent = grand.left;
        HashNode<K, V> child = parent.right;
        // 交换左右子树
        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    /**
     * 结点进行左旋转
     * 处理逻辑等同于BBSTree
     *
     * @param grand
     */
    public void rotateLeft(HashNode<K, V> grand) {
        HashNode<K, V> parent = grand.right;
        HashNode<K, V> child = parent.left;
        // 交换左右子树
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    /**
     * 处理逻辑等同于BBSTree
     *
     * @param grand
     * @param parent
     * @param child
     */
    public void afterRotate(HashNode<K, V> grand, HashNode<K, V> parent, HashNode<K, V> child) {

        // 将parent赋值给grand父节点的左右子树
        if (grand.parent == null) {
            table[index(grand.key)] = parent;
        } else if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else {
            grand.parent.right = parent;
        }

        // 赋值父节点
        parent.parent = grand.parent;
        grand.parent = parent;
        if (child != null) child.parent = grand;
    }

    /**
     * 节点染色
     * 处理逻辑等同于BBSTree
     *
     * @param node
     * @param color
     */
    public HashNode<K, V> color(HashNode<K, V> node, Boolean color) {
        if (node != null) node.color = color;
        return node;
    }

    /**
     * 将结点染成red
     * 处理逻辑等同于BBSTree
     *
     * @param node
     */
    public HashNode<K, V> red(HashNode<K, V> node) {
        return color(node, RED);
    }

    /**
     * 将结点染成black
     * 处理逻辑等同于BBSTree
     *
     * @param node
     */
    public HashNode<K, V> black(HashNode<K, V> node) {
        return color(node, BLACK);
    }

    /**
     * 判断节点颜色
     * 如果节点为null，默认无black；
     * 否则返回结点颜色
     * <p>
     * 处理逻辑等同于BBSTree
     *
     * @param node
     * @return
     */
    public boolean colorOf(HashNode<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    /**
     * 判断结点是否为black
     * 处理逻辑等同于BBSTree
     *
     * @param node
     * @return
     */
    public Boolean isBlack(HashNode<K, V> node) {
        return colorOf(node) == BLACK;
    }

    /**
     * 判断结点是否为black
     * 处理逻辑等同于BBSTree
     *
     * @param node
     * @return
     */
    public Boolean isRED(HashNode<K, V> node) {
        return colorOf(node) == RED;
    }

    /**
     * HashMap结点类
     *
     * @param <K>
     * @param <V>
     */
    private static class HashNode<K, V> {
        K key;
        V value;
        int hash;
        Boolean color = RED;

        HashNode<K, V> parent;
        HashNode<K, V> left;
        HashNode<K, V> right;

        public HashNode(K key, V value, HashNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.hash = key == null ? 0 : key.hashCode();

            this.parent = parent;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        public boolean isLeftChild() {
            return parent != null && this == this.parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == this.parent.right;
        }

        public HashNode<K, V> sibling() {
            if (parent == null) return null;
            if (isLeftChild()) return parent.right;
            if (isRightChild()) return parent.left;
            return null;
        }


    }
}
