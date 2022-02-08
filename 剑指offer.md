剑指offer 按顺序的刷

## JZ3 数组中重复的数字

在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个重复的数字。 例如，如果输入长度为7的数组[2,3,1,0,2,5,3]，那么对应的输出是2或者3。存在不合法的输入的话输出-1

### 思路
1. 遍历数组，将每一个数字安排回对应的序列序号的位置。若出现多个数字安排到同一个位置，就是重复了。时空复杂度是O(N)
2. 其实最直接的应该是建一个hash表，判断

### code
``` java
import java.util.*;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 
     * @param numbers int整型一维数组 
     * @return int整型
     */
    public int duplicate (int[] numbers) {
        // write code here
        int i = 0;
        while (i < numbers.length) {
            int m = numbers[i];
            if (i != m) {
                // 有两种情况，a[m] ? m
                if (numbers[m] != m) {
                    // 令a[m] = m
                    numbers[i] = numbers[m];
                    numbers[m] = m;
                } else {
                    return m;
                } 
            } else {
                    i++;
                }
            
        }
        return -1;
    }
}
```

```java
class Solution {
public:
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 
     * @param numbers int整型vector 
     * @return int整型
     */
    int duplicate(vector<int>& numbers) {
        // write code here
        unordered_set<int> u_set;
        for(int i=0;i<numbers.size();++i){
          if(u_set.count(numbers[i])){//hash表存在
              return numbers[i];
          }else{//不存在
              u_set.insert(numbers[i]);//将该数字加入hash表中
          }
        }
        return -1;
    }
};
```

## JZ4 二维数组中的查找

在一个二维数组array中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
[
[1,2,8,9],
[2,4,9,12],
[4,7,10,13],
[6,8,11,15]
]
给定 target = 7，返回 true。

给定 target = 3，返回 false。

数据范围：矩阵的长宽满足 0 \le n,m \le 5000≤n,m≤500 ， 矩阵中的值满足 0 \le val \le 10^90≤val≤10 
9
 
进阶：空间复杂度 O(1)O(1) ，时间复杂度 O(n+m)O(n+m)

### 思路
1. 因为数组是有序的，对于一个target，每次比较每行最后一个，如果比这个大，就在下一行。如果比这个小，就有可能在这一行。时间复杂度O(N+M)，最多就行列各走完一次。

### code
``` java
public class Solution {
    public boolean Find(int target, int [][] array) {
        int length_1 = array.length;
        int length_2 = array[0].length;
        int i = 0, j = length_2 - 1;
        while ((i < length_1) && (j >= 0)) {
            if (target == array[i][j]) return true;
            else if (target < array[i][j]) j--;
            else i++;
        }
        return false;

    }
}
```

## JZ5 替换空格

请实现一个函数，将一个字符串s中的每个空格替换成“%20”。
例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。

数据范围:。保证字符串中的字符为大写英文字母、小写英文字母和空格中的一种。
进阶：时间复杂度  ,空间复杂度

### 思路
这题主要是需要熟悉java string的处理方法。本质上就是遍历一遍，时间复杂度O(N).
1. char array和string之间的转换，还有string.charAt()方法。
2. String builder 类的调用。类似python list，有append和toString()两个方法。
### code
```java
import java.util.*;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 
     * @param s string字符串 
     * @return string字符串
     */
    public String replaceSpace (String s) {
        // write code here
        int length = s.length();
        char[] array = new char[length * 3];
        int index = 0;
        for (int i=0; i < length; i++) {
            if (s.charAt(i) == ' ') {
                array[index++] = '%';
                array[index++] = '2';
                array[index++] = '0';
            } else {
                array[index++] = s.charAt(i);
            }
        }
        return new String(array, 0, index);

    }
}
```

```java
    public String replaceSpace(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ')
                stringBuilder.append("%20");
            else
                stringBuilder.append(s.charAt(i));
        }
        return stringBuilder.toString();
    }
```

## JZ6 从尾到头打印链表

输入一个链表的头节点，按链表从尾到头的顺序返回每个节点的值（用数组返回）。

### 思路
1. 一个for 循环，深度遍历。时间复杂度O(N)
2. 所以需要的是反转链表，最后转化为arraylist

### code
```java
import java.util.ArrayList;
public class Solution {
    ArrayList<Integer> re = new ArrayList<>();
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (listNode != null) {
            re = printListFromTailToHead(listNode.next);
            re.add(listNode.val);
        }
        return re;
        
    }
}
```

```java
import java.util.ArrayList;
public class Solution {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        
        ListNode preNode = null;
        ListNode currNode = listNode;
        ListNode nextNode = null;
        
        while (currNode != null) {
            nextNode = currNode.next;
            currNode.next = preNode;
            preNode = currNode;
            currNode = nextNode;
        }
        
        while (preNode != null) {
            res.add(preNode.val);
            preNode = preNode.next;
        }
        
        return res;
    }
}
```
## JZ7 重建二叉树

给定节点数为 n 的二叉树的前序遍历和中序遍历结果，请重建出该二叉树并返回它的头结点。

### 思路
1. 从前序遍历里拿数，在中序遍历的数组里面找，找到就可以开始分割，分成左边和右边。
    * 关键是切割点的选取。
    * 空复杂度O(N)
    * 时间复杂度O(logN)，最差是O(N)

### code
``` java
import java.util.*;
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode reConstructBinaryTree(int [] pre,int [] vin) {
        if (pre.length == 0 || vin.length == 0 || pre == null || vin == null) return null;
        
        TreeNode root = reConstructBinaryTree(pre, 0, pre.length-1, vin, 0, vin.length-1);
        return root;
    }
    
    private TreeNode reConstructBinaryTree(int[] pre, int preStart, int preEnd, int[] vin, int vinStart, int vinEnd) {
        if (preStart > preEnd || vinStart > vinEnd) return null;
        
        TreeNode root = new TreeNode(pre[preStart]);
        for (int i = vinStart; i <= vinEnd; i++){
            if (pre[preStart] == vin[i]) {
                root.left = reConstructBinaryTree(pre, preStart+1, preStart + (i - vinStart), vin, vinStart, i-1);
                root.right = reConstructBinaryTree(pre, preStart + (i - vinStart) + 1, preEnd, vin, i+1, vinEnd);
            }
        }
        return root;
    }
}

```

## JZ8 二叉树的下一个结点
给定一个二叉树其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的next指针。下图为一棵有9个节点的二叉树。树中从父节点指向子节点的指针用实线表示，从子节点指向父节点的用虚线表示

### 思路
就是分清楚不同情况的节点

### code
``` java
/*
public class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}
*/
public class Solution {
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode.right != null) {
            TreeLinkNode node = pNode.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
        
        if (pNode.next != null) {
            TreeLinkNode father = pNode.next;
            if (father.left == pNode) {
                return father;
            } else if (father.right == pNode) {
                while(pNode.next !=null && pNode.next.right == pNode) {
                    pNode = pNode.next;
                }
                return pNode.next;
            }
        }
        
        return null;
        
        
    }
}
```

## JZ9 用两个栈实现队列
用两个栈来实现一个队列，使用n个元素来完成 n 次在队列尾部插入整数(push)和n次在队列头部删除整数(pop)的功能。 队列中的元素为int类型。保证操作合法，即保证pop操作时队列内已有元素。

### 思路
栈，先进后出
队列，先进先出

进的时候进A栈，出的时候，若B栈不空，出b栈，若B栈空了，a到b再出。

### code
```java
import java.util.Stack;

public class Solution {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    
    public void push(int node) {
        stack1.push(node);
    }
    
    public int pop() {
    if (stack2.isEmpty()) {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
    }
    return stack2.pop();
    }
}
```

## JZ10 斐波那契数列

大家都知道斐波那契数列，现在要求输入一个正整数 n ，请你输出斐波那契数列的第 n 项。
斐波那契数列是一个满足 fib(x)=\left\{ \begin{array}{rcl} 1 & {x=1,2}\\ fib(x-1)+fib(x-2) &{x>2}\\ \end{array} \right.fib(x)={ 
1
fib(x−1)+fib(x−2)
​
  
x=1,2
x>2
​
  的数列
数据范围：1\leq n\leq 401≤n≤40
要求：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n) ，本题也有时间复杂度 O(logn)O(logn) 的解法

### 思路
看看怎么优化，用循环比递归好，避免栈爆炸。空间复杂度提升巨大！


### code
``` java
public class Solution {
    public int Fibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        } else {
            return Fibonacci(n - 1) + Fibonacci(n - 2);
        }
    }
}
```

```java
int a = 0;
int b = 1;
int result = 0;
for (int i = 1; i < n; i++){
    result = a + b;
    a = b;
    b = result;
}
return result;
```

## JZ11 旋转数组的最小数字
有一个长度为 n 的非降序数组，比如[1,2,3,4,5]，将它进行旋转，即把一个数组最开始的若干个元素搬到数组的末尾，变成一个旋转数组，比如变成了[3,4,5,1,2]，或者[4,5,1,2,3]这样的。请问，给定这样一个旋转数组，求数组中的最小值。

### 思路
关键是找到旋转点吧
其实建一棵树，或者使用堆应该就可以满足时间logN的要求，但满足不了空间log1的要求

其实用二分查找可以，如果rotate了，临界点左边一定会比右边大。

注意二分查找后和两个边界点对比，把情况列出来就好做。尽量用循环代替递归。

### code
```java
import java.util.ArrayList;
public class Solution {
    public int minNumberInRotateArray(int [] array) {
        if (array.length == 0) return 0;
        int left = 0, right = array.length - 1;
        if (array[left] < array[right]) return array[left];
        
        while (left < right) {
            // 除了异常情况，最小的应该在mid的右边
            int middle = left + (right - left) / 2;
            if (right - left == 1) return Math.min(array[right], array[left]);
            
            if (array[middle] < array[middle - 1]) return array[middle];
            
            if (array[middle] > array[left]) {
                left = middle;
            } else if (array[middle] < array[left]) {
                right = middle;
            } else if (array[middle] == array[right]) {
                int tmp = middle + 1;
                while (tmp < right) {
                    if (array[tmp] < array[right]) return array[tmp];
                    tmp ++;
                }
                right = middle;
            }
            
            
        }
        return Math.min(array[right], array[left]);
    }
}
```