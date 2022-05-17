package Hash;
import org.junit.Test;

import java.util.*;

/**
 *
 */
public class HashMap<K,V> extends AbstractMap<K,V> implements Map<K,V>, Cloneable, Serializable {

    /*
     * Hash Map Node
     * */
    static class Node<K, V> implements Map.entry<K, V> {
        final int hash;
        final K key;
        V value;
        Node <K, V> next;

        /* Init */
        Node (int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey() {
            return this.key;
        }

        public final V getValue() {
            return this.value;
        }

        public final String toString() {
            return key + "=" + value;
        }

        public final int hashCode() {
            return Objects.hashCode(key) + Objects.hashCode(value);
        }

        public final V setValue (V newValue) {
            V oldValue = this.value;
            this.value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this) return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
                if (Objects.equal(this.key, e.getKey()) &&
                        Objects.equal(this.value, e.getValue()))
                    return true;
            }
            return false;
        }

    }

    /*
     * Black Red Tree
     * */
    static final class TreeNode<K, V> extends LinkedHashMap.Entry<K, V> {
        TreeNode<K, V> parent;
        TreeNode<K, V> left;
        TreeNode<K, V> right;
        TreeNode<K, V> prev;
        boolean red;
        TreeNode(int hash, K key, V value, Node<K, V> next) {
            super(hash, key, value, next);
        }

        /*return root of this node*/
        final TreeNode<K, V> root() {
            for (TreeNode <K, V> r = this, p;;) {
                if ((p = r.parent) == null) {
                    return r;
                }
                r = p;
            }
        }
    }

    // 序列号
    private static final long serialVersionUID = 362498820763181265L;
    // 默认的初始容量是16
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    // 最大容量
    static final int MAXIMUM_CAPACITY = 1 << 30;
    // 默认的填充因子
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    // 当桶(bucket)上的结点数大于这个值时会转成红黑树
    static final int TREEIFY_THRESHOLD = 8;
    // 当桶(bucket)上的结点数小于这个值时树转链表
    static final int UNTREEIFY_THRESHOLD = 6;
    // 桶中结构转化为红黑树对应的table的最小大小
    static final int MIN_TREEIFY_CAPACITY = 64;
    // 存储元素的数组，总是2的幂次倍
    transient Node<K,V>[] table;
    // 存放具体元素的集
    transient Set<map.entry<K,V>> entrySet;
    // 存放元素的个数，注意这个不等于数组的长度。
    transient int size;
    // 每次扩容和更改map结构的计数器
    transient int modCount;
    // 临界值 当实际大小(容量*填充因子)超过临界值时，会进行扩容
    int threshold;
    // 填充因子
    final float loadFactor;

    public HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }

    /*
    * tableSizeFor(initialCapacity)返回大于initialCapacity的最小的二次幂数值。
    * 右移多少位，就把最高位右边的第x位设置为1
    * 最后加1，就变成2的整数次方数了
    * */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;

    }

    public HashMap (int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public HashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public HashMap (Map<? extends K, ? extends V> m) {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        putMapEntries(m, false);
    }

    final void putMapEntries(Map<? extends K, ? extends V> m, boolean evict) {
        int s = m.size();
        if (s > 0) {
            if (table == null) {
                // initial
                float ft = ((float) s / loadFactor) + 1.0F;
                int t = ((ft < (float) MAXIMUM_CAPACITY ? (int) ft : MAXIMUM_CAPACITY));
                if (t > threshold)
                    threshold = tableSizeFor(t);
            }
            else if (s > threshold)
                resize();

            for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
                K key = e.getKey();
                V value = e.getValue();
                putVal(hash(key), key, value, false, evict);

            }
        }
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);

    }

    public V put (K key, V value) {
        return putValue (hash(key), key, value, false, true);
    }

    final V putValue(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
        Node<K, V>[] tab; Node<K, V> p; int n, i;
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;

        if ((p = tab[i = (n-1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else {
            Node<K, V> e; K k;
            if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else if ( p instanceof TreeNode)
                e = ((TreeNode<K, V>) p).putTreeVal(this, tab, hash, key, value);
            else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1)
                            treeifyBIn(tab, hash);
                        break;
                    }
                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }

            if (e != null) {
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
    }

}
