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

## JZ12 矩阵中的路径
请设计一个函数，用来判断在一个n乘m的矩阵中是否存在一条包含某长度为len的字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。 例如 

$ \begin{bmatrix} a & b & c &e \\ s & f & c & s \\ a & d & e& e\\ \end{bmatrix}\quad ​$
⎣
⎡
​
  
a
s
a
​
  
b
f
d
​
  
c
c
e
​
  
e
s
e
​
  
⎦
⎤

  矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
数据范围：0 \le n,m \le 20\0≤n,m≤20 ,1\le len \le 25\1≤len≤25 
进阶：时间复杂度O(n^2)\O(n 
2
 ) ，空间复杂度O(n^2 )\O(n 
2
 ) 


 ### 思路

 1. 说句实话，第一次看这道题，没有一点思路。理论上来说。
 2. 回溯法，动态规划？找到第一字母的位置，向四周追踪，如果没有，就回溯。
 3. 回溯
    1. 先一个标记矩阵
    2. 遍历，如果match 字符串就1，然后向四周搜索，没有就回

### code

``` java
import java.util.*;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 
     * @param matrix char字符型二维数组 
     * @param word string字符串 
     * @return bool布尔型
     */
    public boolean hasPath (char[][] matrix, String word) {
        // write code here
        int row = matrix.length, col = matrix[0].length;
        boolean[][] flag = new boolean[row][col];
        for (int i=0; i < row; i++) {
            for (int j=0; j < col; j++) {
                flag[i][j] = false;
            }
        }
        
        for (int i=0; i < row; i++) {
            for (int j=0; j < col; j++) {
                if (helper(matrix, row, col, i, j, word, 0, flag)) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
        * Private function Help
        * 帮助找到字母k在矩阵中的位置
        */
    private boolean helper(char[][] matrix, int row, int col, int i, int j, String word, int k, boolean[][] flag) {
        // 如果超出矩阵索引，或者flag 为1,或者不匹配 就返回
        if (i < 0 || i >= row || j < 0 || j >= col || flag[i][j] || matrix[i][j] != word.toCharArray()[k]) {
            return false;
        }
        
        // 搜索完了
        if (k == word.length() - 1) {
            return true;
        }
        
        // 向四周搜索下一个
        flag[i][j] = true;
        if (helper(matrix, row, col, i-1, j, word, k+1, flag) || helper(matrix, row, col, i+1, j, word, k+1, flag)|| helper(matrix, row, col, i, j-1, word, k+1, flag)|| helper(matrix, row, col, i, j+1, word, k+1, flag)) {
            return true;
        }
        
        // 回溯
        flag[i][j] = false;
        return false;

    }
}
```

## JZ13 机器人的运动范围
地上有一个 rows 行和 cols 列的方格。坐标从 [0,0] 到 [rows-1,cols-1] 。一个机器人从坐标 [0,0] 的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于 threshold 的格子。 例如，当 threshold 为 18 时，机器人能够进入方格   [35,37] ，因为 3+5+3+7 = 18。但是，它不能进入方格 [35,38] ，因为 3+5+3+8 = 19 。请问该机器人能够达到多少个格子？

数据范围： $ 0 \le threshold \le 15 \0≤threshold≤15  ，1 \le rows,cols \le 100 \1≤rows,cols≤100 $

进阶：空间复杂度 O(nm) \O(nm)  ，时间复杂度 O(nm) \O(nm) 

### 思路
1. 和上题类似，回溯的思路比较简单，但会存在一些重复计算。动态规划就不会。但这一版结果时间和空间复杂度都比较差。

```java
public class Solution {
    public int movingCount(int threshold, int rows, int cols) {
        boolean[][] flag = new boolean[rows][cols];
        helper(threshold, rows, cols, flag, 0, 0);
        
        int res = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (flag[i][j] == true) {
                    res += 1;
                }
            }
        }
        
        return res;
    }
    
    private boolean helper(int threshold, int rows, int cols, boolean[][] flag, int i, int j) {
        if (i < 0 || i >= rows || j < 0 || j >= cols || flag[i][j]) {
            return false;
        }
        
        int value = (i % 10) + (int)(i / 10) + (j % 10) + (int)(j / 10);
        if (value > threshold) {
            return false;
        }
        
        flag[i][j] = true;
        helper(threshold, rows, cols, flag, i-1, j);
        helper(threshold, rows, cols, flag, i+1, j);
        helper(threshold, rows, cols, flag, i, j-1);
        helper(threshold, rows, cols, flag, i, j+1);
        
        return true;
        
    }
}
```

## JZ14 剪绳子

给你一根长度为 n 的绳子，请把绳子剪成整数长的 m 段（ m 、 n 都是整数， n > 1 并且 m > 1 ， m <= n ），每段绳子的长度记为 k[1],...,k[m] 。请问 k[1]*k[2]*...*k[m] 可能的最大乘积是多少？例如，当绳子的长度是 8 时，我们把它剪成长度分别为 2、3、3 的三段，此时得到的最大乘积是 18 。

数据范围： 2 \le n \le 602≤n≤60
进阶：空间复杂度 O(1)O(1) ，时间复杂度 O(n)O(n)

### 思路
1. 动态规划
   1. 只要我把所有的情况列出来，查表就可以知道对应值，但是这个怎么满足O(n)的时间复杂度呢。


```java
public class Solution {
    public int cutRope(int target) {
        // n < 4 的最优解
        if (target == 2) return 1;
        if (target == 3) return 2;
        
        int[] res = new int[target + 1];
        res[1] = 1;
        res[2] = 2;
        res[3] = 3;
        
        for (int i = 4; i <= target; i++) {
            int max = 0;
            for (int j = 1; j <= i / 2; j++) {
                int tempMax = res[j] * res[i-j];
                if (max < tempMax) max = tempMax;
            }
            res[i] = max;
        }
        
        return res[target];
    }
}
```

时间复杂度O(N^2), 空间也是O(N)

2. 数学方法
   1. 把N分成x段，只有在长度相等时乘积最大，每段长度是n/x，乘积是 $(n/x) ^ x$。求导，发现x = n/e 时，乘积最大，取整是3。

```java
    public int cutRope(int target) {
        if (target == 2 || target == 3)
            return target - 1;
        int res = 1;
        while (target > 4) {
            //如果target大于4，我们不停的让他减去3
            target = target - 3;
            //计算每段的乘积
            res = res * 3;
        }
        return target * res;
    }
```

## JZ15 二进制中1的个数

输入一个整数 n ，输出该数32位二进制表示中1的个数。其中负数用补码表示。

### 思路

1. 暴力法，不断的模2。
2. 二进制位移法，直接将数看成是二进制的数，用0x01不断右移。

```java
int val; // input data
int ans = 0;
int mark = 0x01;
while (mark != 0) {
    if (mark & val) ++ans;
    mark <<= 1;
}
```

3. 技巧，和上一种类似，但是会跳过0，只判断1.

```java
int val; // input data
int ans = 0;
int mark = 0x01;
while (mark != 0) {
    if (mark & val) ++ans;
    mark <<= 1;
}
```

### JZ16 数值的整数次方

实现函数 double Power(double base, int exponent)，求base的exponent次方。

注意：
1.保证base和exponent不同时为0。
2.不得使用库函数，同时不需要考虑大数问题
3.有特殊判题，不用考虑小数点后面0的位数。

数据范围： |base| \le 100 \∣base∣≤100  ， |exponent| \le 100 \∣exponent∣≤100  ,保证最终结果一定满足 |val| \le 10^4 \∣val∣≤10 
4
  
进阶：空间复杂度 O(1)\O(1)  ，时间复杂度 O(n)\O(n) 

### 思路
1. 先按部就班整一个

```java
public class Solution {
    public double Power(double base, int exponent) {
        if (exponent == 0) return 1;
        if (base == 0) return 0;
        
        double re = 1.0;
        if (exponent > 0) {
            for (int i = 1; i <= exponent; i++) {
                re *= base;
            }
            return re;
        } else {
            for (int i = 1; i <= -exponent; i++) {
                re *= base;
            }
            return 1 / re;
        }
  }
}
```

2. 技巧 非递归的快速幂
   1. 将exponent 展开成二进制
   2. 然后展开，发现只有系数是1的需要呈上，这样负载的就变成了logn

```java
class Solution {
public:

     double Power(double b, int n) {
        if (n < 0) {
            b = 1 / b;
            n = -n;
        }
        double x = b; // 记录x^0, x^1, x^2 ...
        double ret = 1.0;
        while (n != 0) {
            if ((n & 1) != 0) {
                ret *= x; // 二进制位数是1的，乘进答案。
            }
            x *= x;
            n >>= 1;
        }
        return ret;
    }
};
```

## JZ17 打印从1到最大的n位数

输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
1. 用返回一个整数列表来代替打印
2. n 为正整数，0 < n <= 5

```java
import java.util.*;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 
     * @param n int整型 最大位数
     * @return int整型一维数组
     */
    public int[] printNumbers (int n) {
        // write code here
        if (n < 0) return null;
        if (n == 0) return new int[]{0};
        
        int max = 10;
        for (int i = 2; i <= n; i++) {
            max *= 10;
        }
        max -= 1;
        
        int[] res = new int[max];
        for (int i = 0; i < max; i++){
            res[i] = i + 1;
        }
        return res;
    }
}
```

## JZ18 删除链表的节点

给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。返回删除后的链表的头节点。

1.此题对比原题有改动
2.题目保证链表中节点的值互不相同
3.该题只会输出返回的链表和结果做对比，所以若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点

### 思路
简单直接，链表的结构

```java
import java.util.*;

/*
 * public class ListNode {
 *   int val;
 *   ListNode next = null;
 *   public ListNode(int val) {
 *     this.val = val;
 *   }
 * }
 */

public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 
     * @param head ListNode类 
     * @param val int整型 
     * @return ListNode类
     */
    public ListNode deleteNode (ListNode head, int val) {
        // write code here
        if (head == null) return null;
        if (head.val == val) return head.next;
        
        ListNode re = head;
        while(head.next != null) {
            if (head.next.val == val) {
                ListNode tmp = head.next.next;
                head.next = tmp;
                break;
            }
            head = head.next;
        }
        
        return re;
    }
}
```

## JZ19 正则表达式匹配
请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配

数据范围:
1.str 可能为空，且只包含从 a-z 的小写字母。
2.pattern 可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。
3. 0 <= str.length <= 20
4. 0 <= pattern.length <= 30
要求：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)

### 思路
感觉是一个回溯算法，但具体怎么实现，还真不知道。

还是要想明白，到底需要如何递归。

但显然，递归的时空复杂度都是比较高的。optimize应该是动态规划之类的。
```java
import java.util.*;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 
     * @param str string字符串 
     * @param pattern string字符串 
     * @return bool布尔型
     */
    public boolean match (String str, String pattern) {
        // write code here
        if (str == null && pattern == null) return true;
        if ((str == null && pattern != null) || (str != null && pattern == null)) return false;
        return matchCore(str, 0, pattern, 0);
    }
    
    private boolean matchCore(String str, int i, String pattern, int j) {
        // 递归出口
        if (i == str.length() && j == pattern.length()) return true;
        if (i < str.length() && j == pattern.length()) return false;
        
        // 分下一个是不是 *
        if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*') {
            // 如果是
            if (i < str.length() && (str.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '.')) {
                /**
                 * 当这个字符相等的时候，验证后续，后续有三种情况
                 * 1. 现在的这个字符可以出现任意次，所以str 中这个字符继续和 pattern j match
                 * 2. pattern j = . 的时候，会进到这里。 ex 'a' '.*a'
                 * 3. pattern j和当前的 i匹配到尽头了，所以是i+1和j+2匹配
                 */
                return matchCore(str, i+1, pattern, j) || matchCore(str, i, pattern, j+2) || matchCore(str, i+1, pattern, j+2);
            } else {
                return matchCore(str, i, pattern, j+2); 
            }
        } else {
            // 下一个不是 *
            if (i < str.length() && (str.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '.')) {
                return matchCore(str, i+1, pattern, j+1);
            }
            return false;
        }
    }
}
```

动态规划的来了，动态规划的要快一点。
```java
import java.util.*;

public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 
     * @param str string字符串 
     * @param pattern string字符串 
     * @return bool布尔型
     */
    public boolean match (String str, String pattern) {
        // write code here
        int n = str.length();
        int m = pattern.length();
        boolean[][] f = new boolean[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                //分成空正则和非空正则两种
                if (j == 0) {
                    f[i][j] = i == 0;
                } else {
                    //非空正则分为两种情况 * 和 非*
                    if (pattern.charAt(j - 1) != '*') {
                        if (i > 0 && (str.charAt(i - 1) == pattern.charAt(j - 1) || pattern.charAt(j - 1) == '.')) {
                            f[i][j] = f[i - 1][j - 1];
                        }
                    } else {
                        //碰到 * 了，分为看和不看两种情况
                        //不看
                        if (j >= 2) {
                            f[i][j] |= f[i][j - 2];
                        }
                        //看
                        if (i >= 1 && j >= 2 && (str.charAt(i - 1) == pattern.charAt(j - 2) || pattern.charAt(j - 2) == '.')) {
                            f[i][j] |= f[i - 1][j];
                        }
                    }
                }
            }
        }
        return f[n][m];
    }
}
```

## JZ20 表示数值的字符串

请实现一个函数用来判断字符串str是否表示数值（包括科学计数法的数字，小数和整数）。


### 思路
测试样例太多，如果是导向false的只能一个一个排除，还是要提取出通用的规则。
```java
import java.util.*;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 
     * @param str string字符串 
     * @return bool布尔型
     */
    public boolean isNumeric (String str) {
        // write code here
        if (str == null) return false;
        int len = str.length();
        boolean signal = false;
        boolean hasE = false;
        boolean hasP = false;
        boolean endSpace = false;
        boolean hasNum = false;
        for (int i=0; i < len; i++) {
            char temp = str.charAt(i);
//             if (endSpace == true && temp == ' '){
//                 return false;
//             }
            if (temp == ' ') {
                continue;
            } else {
                endSpace = true;
            }
                
            if (temp == 'e' || temp == 'E') {
                // 先处理E，e不能再开头和结尾，e不会出现两次，而且e之后不会出现点
                if (i == 0 || i == len-1) {
                    return false;
                } else if (hasE == true) {
                    return false;
                } else if (str.charAt(i-1) > '9' || str.charAt(i-1) < '0' && str.charAt(i-1) != '.'){
                    return false;
                } else {
                    hasE = true;
                    hasP = true;
                }
            } else if(temp == '.') {
                // 处理小数点，只要不出现两次就好
                if (hasP == true) {
                    return false;
                } else if (len == 1) {
                    return false;
                } else {
                    hasP = true;
                }
            } else if (temp == '-' || temp == '+') {
                // 正负号，只能在开头和结尾，还有e之后
                if (i != 0) {
                    if (str.charAt(i-1) != 'e' && str.charAt(i-1) != 'E' || i == len-1) {
                        return false;
                    }
                } else if (len == 1) {
                    return false;
                } else {
                    signal = true;
                }
            } else {
                if (temp < '0' || temp > '9') {
                    return false;
                }
                hasNum = true;
            }
        }
        
        if (hasNum) {
            return true;
        } else {
            return false;
        }
    }
}
```

## JZ21 调整数组顺序使奇数位于偶数前面

输入一个长度为 n 整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前面部分，所有的偶数位于数组的后面部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。

### 思路
双指针，

```java
import java.util.*;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 
     * @param array int整型一维数组 
     * @return int整型一维数组
     */
    public int[] reOrderArray (int[] array) {
        // write code here
        int[] odd = new int[array.length];
        int[] even = new int[array.length];
        int oddPoint = 0;
        int evenPoint = 0;
        
        for (int i=0; i < array.length; i++) {
            int temp = array[i];
            if (temp % 2 == 0) {
                even[evenPoint] = temp;
                evenPoint += 1;
            } else {
                odd[oddPoint] = temp;
                oddPoint += 1;
            }
        }
        
        for (int j = 0; j < evenPoint; j++) {
            odd[oddPoint + j] = even[j];
        }
        
        return odd;
    }
}
```

## JZ22 链表中倒数最后k个结点
输入一个长度为 n 的链表，设链表中的元素的值为 ai ，返回该链表中倒数第k个节点。
如果该链表长度小于k，请返回一个长度为 0 的链表。

### 思路
1. 遍历一次，看看长度是多少。然后直接去到n-k

```java
import java.util.*;

/*
 * public class ListNode {
 *   int val;
 *   ListNode next = null;
 *   public ListNode(int val) {
 *     this.val = val;
 *   }
 * }
 */

public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 
     * @param pHead ListNode类 
     * @param k int整型 
     * @return ListNode类
     */
    public ListNode FindKthToTail (ListNode pHead, int k) {
        // write code here
        int len = 0;
        ListNode temp = pHead;
        while (temp != null) {
            temp = temp.next;
            len += 1;
        }
        
        if (len < k) {
            return null;
        }
        
        temp = pHead;
        for (int i = 1; i <= len - k; i++) {
            temp = temp.next;
        }
        return temp;
    }
}
```

## JZ23 链表中环的入口结点

给一个长度为n链表，若其中包含环，请找出该链表的环的入口结点，否则，返回null。

### 思路
1. 环的入口，这个之前看过，但确实想不起来了。
2. 快慢指针。先一个走两步，一个走一步，如果相遇，就是有环。一个从头开始，都走一步，相遇时就是环节点入口。证明一下。

环长度 $y = (n_2 - 2 \times n_1) \times k - x$，其中y是相遇点c到环起始点的距离，k是环的长度，x是非环长度。一定存在这样的y。

然后就发现 $y + x = (n_2 - 2 \times n_1) \times k$，所以从头开始走x步就是环的入口。且两个指针相遇。

```java
/*
 public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
*/
public class Solution {

    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null || pHead.next == null) return null;
        ListNode p1 = pHead.next;
        ListNode p2 = p1.next;
        
        while (p2 != null && p1 != p2) {
            p1 = p1.next;
            p2 = p2.next.next;
        }
        
        // 没有环
        if (p2 == null) return p2;
        
        p1 = pHead;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }
}
```

## JZ24 反转链表

给定一个单链表的头结点pHead(该头节点是有值的，比如在下图，它的val是1)，长度为n，反转该链表后，返回新链表的表头。

### 思路
1. 第一时间竟然没有思路，还是刷题刷少了。很直接的想法，类似递归，从第一个开始，处理好2，3之间的关系，就可以到2，3，4了。

```java
/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
public class Solution {
    public ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode one = head;
        ListNode two = head.next;
        head.next = null;
        
        while (two != null) {
            ListNode temp = two.next;
            two.next = one;
            one = two;
            two =temp;
        }
        return one;

    }
}
```

## JZ25 合并两个排序的链表
输入两个递增的链表，单个链表的长度为n，合并这两个链表并使新链表中的节点仍然是递增排序的。
数据范围： 0 \le n \le 10000≤n≤1000，-1000 \le 节点值 \le 1000−1000≤节点值≤1000
要求：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)

### 思路
1. 看着就是快慢指针

```java
/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
public class Solution {
    public ListNode Merge(ListNode list1,ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        
        // 确定链表开头
        ListNode begin = null;
        if (list1.val <= list2.val) {
            begin = list1;
            list1 = list1.next;
        } else {
            begin = list2;
            list2 = list2.next;
        }
        
        ListNode temp = begin;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                temp.next = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                list2 = list2.next;
            }
            temp = temp.next;
        }
        
        if (list1 == null) {
            temp.next = list2;
        } else {
            temp.next = list1;
        }
        
        return begin;
    }
}
```
感觉写的有点复杂了。

## JZ26 树的子结构
输入两棵二叉树A，B，判断B是不是A的子结构。（我们约定空树不是任意一个树的子结构）
假如给定A为{8,8,7,9,2,#,#,#,#,4,7}，B为{8,9,2}，2个树的结构如下，可以看出B是A的子结构

### 思路
1. 这应该是要对树的遍历吧。递归吧，回溯个锤子。
   首先要根节点的val 相等，然后验证左右子树是否相等

```java
/**
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class Solution {
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if (root2 == null || root1 == null) return false;
        
        if (root2.val == root1.val) {
            if (equalTree(root1, root2)) return true;
        }
        return (HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2));
        
    }
    
    private boolean equalTree(TreeNode root1, TreeNode root2) {
        // 判断2是否被1包含，此时root1.val == roo2.val
        if (root2 == null) return true;
        if (root1 == null) return false;
        
        if (root1.val == root2.val) {
            return (equalTree(root1.left, root2.left) && equalTree(root1.right, root2.right));
        }
        return false;
    }
}
```

## JZ27 二叉树的镜像
操作给定的二叉树，将其变换为源二叉树的镜像。

### 思路
递归啊，还用想吗，层次遍历，左右替换。

```java
import java.util.*;

/*
 * public class TreeNode {
 *   int val = 0;
 *   TreeNode left = null;
 *   TreeNode right = null;
 *   public TreeNode(int val) {
 *     this.val = val;
 *   }
 * }
 */

public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 
     * @param pRoot TreeNode类 
     * @return TreeNode类
     */
    public TreeNode Mirror (TreeNode pRoot) {
        // write code here
        if (pRoot == null) return null;
        TreeNode temp = pRoot.left;
        pRoot.left = Mirror(pRoot.right);
        pRoot.right = Mirror(temp);
        return pRoot;
    }
}
```

## JZ28 对称的二叉树
给定一棵二叉树，判断其是否是自身的镜像（即：是否对称）

### 思路
这个其实有点难，因为你看子树是看不出父树是否对称的。但非常好笑，只要对比子树翻转后是否相等就可以，哈哈。

```java
/*
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class Solution {
    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) return true;
        TreeNode mirrorLeft = Mirror(pRoot.left);
        return TreeValueEqual(pRoot.left, pRoot.right);
    }
    
    public TreeNode Mirror (TreeNode pRoot) {
        // write code here
        if (pRoot == null) return null;
        TreeNode temp = pRoot.left;
        pRoot.left = Mirror(pRoot.right);
        pRoot.right = Mirror(temp);
        return pRoot;
    }
    
    private boolean TreeValueEqual(TreeNode root1, TreeNode root2) {
        if (root2 == null && root1 == null) return true;
        if (root1 == null) return false;
        if (root2 == null) return false;
        
        return ((root1.val == root2.val) && (TreeValueEqual(root1.left, root2.left)) && (TreeValueEqual(root1.right, root2.right)));
    }
    
}
```

复杂了，其实直接比较值就可以了。傻了。

## JZ29 顺时针打印矩阵

输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，如果输入如下4 X 4矩阵：

[[1,2,3,4],
[5,6,7,8],
[9,10,11,12],
[13,14,15,16]]

则依次打印出数字
[1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10]

### 思路
1. 递归，一圈一圈的往里打就好。但递归不好处理，既然是数组，就循环吧。


```java
import java.util.ArrayList;
public class Solution {
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        if (matrix == null || matrix.length == 0|| matrix[0] == null || matrix[0].length == 0) {
            return new ArrayList<Integer> ();
        }
        int i = matrix.length;
        int j = matrix[0].length;
        
        
        ArrayList re = new ArrayList<Integer> (i * j);
        int start = 0;
        
        while (i > 0 && j > 0) {
            printMatrix(matrix, i, j, re, start);
            i -= 2;
            j -= 2;
            start += 1;
        }
        return re;
    }
    
    private void printMatrix(int[][] matrix, int row, int col, ArrayList re, int start) {
        for (int i = 0; i < col; i++) {
            re.add(matrix[start][start + i]);
        }
        for (int j = 0; j < row-2; j++) {
            re.add(matrix[start + j + 1][start + col - 1]);
        }
        for (int i = 0; i < col && row != 1; i++) {
            re.add(matrix[start + row - 1][start + col - 1 - i]);
        }
        for (int j = 0; j < row - 2 && col != 1; j++) {
            re.add(matrix[start + row - 2 - j][start]);
        }
    }
}
```

说实话，有些奇怪，这题，很细节。

## JZ30 包含min函数的栈
定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的 min 函数，输入操作时保证 pop、top 和 min 函数操作时，栈中一定有元素。

此栈包含的方法有：
push(value):将value压入栈中
pop():弹出栈顶元素
top():获取栈顶元素
min():获取栈中最小元素

数据范围：操作数量满足 0 \le n \le 300 \0≤n≤300  ，输入的元素满足 |val| \le 10000 \∣val∣≤10000 
进阶：栈的各个操作的时间复杂度是 O(1)\O(1)  ，空间复杂度是 O(n)\O(n) 

### 思路
1. 很直接的写一个栈。栈，先进后出
2. 傻了，题目都没看清。应该是写两个栈，一个最小的栈进栈。另外，需要去看一下基本数据结构的实现了。

```java
import java.util.Stack;

public class Solution {
    private int min_value = Integer.MAX_VALUE;
    private Stack store = new Stack<Integer> ();
    
    public void push(int node) {
        store.push(node);
        if (node < min_value) {
            min_value = node;
        }
    }
    
    public void pop() {
        store.pop();
    }
    
    public int top() {
        return (int)store.lastElement();
    }
    
    public int min() {
        return min_value;
    }
}
```

## JZ31 栈的压入、弹出序列

输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。

### 思路
1. 第一反应就是回溯，但细节没有。看答案
2. 不用回溯，一个栈模拟压入过程，不断判断栈顶元素和出栈顺序是否相等。相等出栈，不等入栈

```java
import java.util.ArrayList;

public class Solution {
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if (pushA == null && popA == null) return true;
        if (pushA == null || popA == null) return false;
        if (pushA.length != popA.length) return false;
        ArrayList stack = new ArrayList();
        int pushIndex = 0;
        int stackIndex = 0;
        stack.add(stackIndex, pushA[pushIndex]);
        
        // 开始对popA进行逐项检查
        int popIndex = 0;
        while (popIndex < popA.length) {
            // 如果栈顶元素和popA相等，就出栈
            if (stackIndex == -1) {
                stackIndex += 1;
                pushIndex += 1;
                stack.add(stackIndex, pushA[pushIndex]);
            }
            if (popA[popIndex] == (int)stack.toArray()[stackIndex]) {
                popIndex += 1;
                stackIndex -= 1;
            } else {
                // 不相等就入栈
                stackIndex += 1;
                pushIndex += 1;
                if (pushIndex == popA.length) return false;
                stack.add(stackIndex, pushA[pushIndex]);
            }
        }
        return true;
    }
}
```
勉强写出来，但是时空复杂度好像很低，看看答案。答案有栈我没有，这就是区别。

## JZ32 从上往下打印二叉树
不分行从上往下打印出二叉树的每个节点，同层节点从左至右打印。例如输入{8,6,10,#,#,2,1}，如以下图中的示例二叉树，则依次打印8,6,10,2,1(空节点不打印，跳过)，请你将打印的结果存放到一个数组里面，返回。

### 思路
1. 层次遍历，用队列

```java
import java.util.*;
/**
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class Solution {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        if (root == null) return null;
        ArrayList res = new ArrayList<Integer> ();
        Queue<TreeNode> queue = new LinkedList<> ();
        
        queue.offer(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            res.add(root.val);
            if (root.left != null) {
                queue.offer(root.left);
            }
            if (root.right != null) {
                queue.offer(root.right);
            }
        }
        return res;
    }
    
}
```

## JZ33 二叉搜索树的后序遍历序列
输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则返回 true ,否则返回 false 。假设输入的数组的任意两个数字都互不相同。

### 思路
1. 还挺难，一时间没有任何想法。只能从二叉搜索树的性质入手。左小于中小于右.
2. 递归和动态规划. 如果是后续遍历,找到第一个大于这个值的结点,这做为区分.递归看这两边是否为后续遍历.

```java
public class Solution {
    public boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence == null|| sequence.length == 0) return false;
        if (sequence.length == 1 ) {
            return true;
        }
        return HelpFun(sequence, 0, sequence.length-1);
    }
    
    private boolean HelpFun(int[] sequence, int start, int end) {
        // 递归出口，如果最后两个相同了，到叶节点了，true。
        // 如果存在右子树小于父节点，左子树大于父节点的，flase；firstLarge 已经确保了左边都小于end。
        if (start == end) return true;
        int firstLarge = FirstLarge(sequence, start, end);
        for (int i = firstLarge; i < end; i++) {
            if (sequence[i] < sequence[end]) {
                return false;
            }
        }
        
        if (firstLarge == start) {
            // 全都是右子树
            return HelpFun(sequence, firstLarge, end-1);
        } else if (firstLarge == end) {
            return HelpFun(sequence, start, end-1);
        }else {
            return (HelpFun(sequence, start, firstLarge-1) && HelpFun(sequence, firstLarge, end-1));
        }
    }
    
    private int FirstLarge(int[] sequence, int start, int end){
        int i = start;
        for (i = start; i < end; i++) {
            if (sequence[i] > sequence[end]) {
                break;
            }
        }
        return i;
    }
}
```

时间 O(n^2)

3. 中序遍历做为入栈序列,然后检查后续遍历是不是一个合法的出栈序列就可以.why? 时间O(NlogN) 排序的时间
   1. 二叉树的中序遍历和后序遍历对应着一种栈的压入、弹出序列
   2. 会碰到的情况是:前中后 入栈, 后序的是前后中. 前前mapping,中后,后中,后mapping完就会到中.

```java
import java.util.*;

public class Solution {
    public boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence == null|| sequence.length == 0) return false;
        if (sequence.length == 1 ) {
            return true;
        }
        int[] order = sequence;
        Arrays.sort(order);
        
        Stack stack = new Stack();
        int i = 0, j = 0;
        while (i < sequence.length) {
            stack.push(order[i]);
            while (!stack.isEmpty() && ((int)stack.lastElement() == sequence[j])) {
                stack.pop();
                j += 1;
            }
            i += 1;
        }
        return (j == sequence.length);
    }
}
```

4. 逆序后序遍历 + 栈 时间 O(N)

本质上是,左子树要比右子树最小的要小.
   
```java
import java.util.*;

public class Solution {
    public boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence == null|| sequence.length == 0) return false;
        if (sequence.length == 1 ) {
            return true;
        }
        Stack<Integer> stack = new Stack ();
        int root = Integer.MAX_VALUE;
        for (int i = sequence.length - 1; i > -1; i--) {
            if (sequence[i] > root) return false;
            while (!stack.isEmpty() && (stack.lastElement() > sequence[i])){
                root = stack.pop();
            }
            stack.push(sequence[i]);
        }
        return true;
    }
}
```

## JZ34 二叉树中和为某一值的路径(二)

输入一颗二叉树的根节点root和一个整数expectNumber，找出二叉树中结点值的和为expectNumber的所有路径。
1.该题路径定义为从树的根结点开始往下一直到叶子结点所经过的结点
2.叶子节点是指没有子节点的节点
3.路径只能从父节点到子节点，不能从子节点到父节点
4.总节点数目为n

### 思路
1. 要遍历到叶子节点.一个深度遍历结束. 这个需要返回所有的结果. 要把节点加进去,应该是先序遍历.

```java
import java.util.ArrayList;
/**
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class Solution {
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int expectNumber) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<> ();
        ArrayList<Integer> now = new ArrayList<> ();
        if (root == null) return res;
        helpFun(root, res, expectNumber, now);
        return res;
    }
    
    private void helpFun(TreeNode root, ArrayList res, int expectNumber, ArrayList now) {
        if (root == null) return;
        now.add(root.val);
        expectNumber -= root.val;
        if (expectNumber == 0 && root.left == null && root.right == null) {
            res.add(new ArrayList(now));
        }
        if (root.left != null) helpFun(root.left, res, expectNumber, now);
        if (root.right != null) helpFun(root.right, res, expectNumber, now);
        now.remove(now.size() - 1);
        
    }
}
```

要小心,叶子节点返回了也要remove,之前的bug在这里.

## JZ35 复杂链表的复制

输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针random指向一个随机节点），请对此链表进行深拷贝，并返回拷贝后的头结点。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）。 下图是一个含有5个结点的复杂链表。图中实线箭头表示next指针，虚线箭头表示random指针。为简单起见，指向null的指针没有画出。

### 思路
1. 我理解一下,好像没有那么难?我直接按照next copy出一个链表,然后random的指向是有序号的.用arraylist存数据.

```java
import java.util.*;
/*
public class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}
*/
public class Solution {
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) return null;
        ArrayList<RandomListNode> store = new ArrayList<> ();
        ArrayList<RandomListNode> res = new ArrayList<> ();
        
        RandomListNode temp = pHead;
        while (temp != null) {
            store.add(temp);
            res.add(new RandomListNode(temp.label));
            temp = temp.next;
        }
        
        
        for (int i = 0; i < res.size(); i++) {
            if (store.get(i).next == null) {
                res.get(i).next = null;
            } else {
                res.get(i).next = res.get(store.indexOf(store.get(i).next));
            }
            
            if (store.get(i).random == null) {
                res.get(i).random = null;
            } else {
                res.get(i).random = res.get(store.indexOf(store.get(i).random));
            }
            
        }
        return res.get(0);
    }
}
```

2. 这个更巧妙，此解法参考了大佬的做法, 主要思路是将原链表的结点对应的拷贝节点连在其后, 最后链表变成 原1 -> 拷1 -> 原2 -> 拷2 -> ... -> null 的形式
然后我们再逐步处理对应的随机指针, 使用双指针, 一个指针指向原链表的节点, 一个指向拷贝链表的节点, 那么就有 拷->random = 原->random->next (random不为空)
最后再用双指针将两条链表拆分即可, 此算法大大优化了空间复杂度, 十分优秀

```C++
class Solution {
public:
    RandomListNode* Clone(RandomListNode* pHead) {
        if(!pHead) return pHead;    // 为空则直接返回空
        unordered_map<RandomListNode*, RandomListNode*> mp;    // 创建哈希表

        RandomListNode* dummy = new RandomListNode(0);    // 哨兵节点

        RandomListNode *pre = dummy, *cur = pHead;    // 指向哨兵和链表头的指针

        while(cur){
            RandomListNode* clone = new RandomListNode(cur->label);    // 拷贝节点
            pre->next = clone;    // 与上个结点连接
            mp[cur] = clone;    // 记录映射关系
            pre = pre->next;    // 指针移动
            cur = cur->next;
        }

        for(auto& [key, value] : mp){    // 遍历哈希表
            value->random = key->random == NULL ? NULL : mp[key->random];
        }

        delete dummy;    // 释放哨兵节点空间
        return mp[pHead];
    }
};
```

## JZ36 二叉搜索树与双向链表 这题好好看好好理解.从可以new开始做,.
输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。如下图所示

数据范围：输入二叉树的节点数 0 \le n \le 10000≤n≤1000，二叉树中每个节点的值 0\le val \le 10000≤val≤1000
要求：空间复杂度O(1)O(1)（即在原树上操作），时间复杂度 O(n)O(n)

注意:
1.要求不能创建任何新的结点，只能调整树中结点指针的指向。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继
2.返回链表中的第一个节点的指针
3.函数返回的TreeNode，有左右指针，其实可以看成一个双向链表的数据结构
4.你不用输出双向链表，程序会根据你的返回值自动打印输出

### 思路
1. 限制的很死，担任，树本来就是一个链表，两个结点，
2. 从小到大，毫无疑问，中序遍历。

中序遍历递归

```java
/**
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class Solution {
    public TreeNode Convert(TreeNode root) {
        // 中序遍历
        if (root == null) return null;
        if (root.left == null && root.right == null) return root;
        
        TreeNode left = Convert(root.left); // left sub tree
        TreeNode p = left;
        while (p != null && p.right != null) {
            p = p.right;
        }
        if (p != null) {
            p.right = root;
            root.left = p;
        }
        
        TreeNode right = Convert(root.right);
        if (right != null) {
            root.right = right;
            right.left = root;
        }
        return left != null ? left : root;
    }

}
```

首先函数会返回左子树排好的结果,而且返回的是第一个node. 然后找到最右边的node,连接上.简单明了.

非递归,使用栈.

```java
import java.util.*;
/**
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class Solution {
    public TreeNode Convert(TreeNode root) {
        // 中序遍历
        if (root == null) return null;
        Stack <TreeNode> stack = new Stack<> ();
        TreeNode pre = null;
        TreeNode p = root;
        boolean flag = true;
        
        while (p != null || !stack.empty()) {
            // p is not null or stack is not empty
            // using stack to traversal tree
            while ( p != null) {
                stack.push(p);
                p = p.left;                
            }
            p = stack.pop();
            if (flag) {
                // first time to record address
                root = p;
                pre = p;
                flag = false;
            } else {
                pre.right = p;
                p.left = pre;
                pre = p;
            }
            p =p.right;
        }
        return root;
        

    }

}
```

使用一个栈,左节点全部进去,然后出栈,干活,然后处理中间节点,然后右节点入栈.

## JZ37 序列化二叉树

请实现两个函数，分别用来序列化和反序列化二叉树，不对序列化之后的字符串进行约束，但要求能够根据序列化之后的字符串重新构造出一棵与原二叉树相同的树。

二叉树的序列化(Serialize)是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，从而使得内存中建立起来的二叉树可以持久保存。序列化可以基于先序、中序、后序、层序的二叉树等遍历方式来进行修改，序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#）

二叉树的反序列化(Deserialize)是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。

例如，可以根据层序遍历的方案序列化，如下图:

层序序列化(即用函数Serialize转化)如上的二叉树转为"{1,2,3,#,#,6,7}"，再能够调用反序列化(Deserialize)将"{1,2,3,#,#,6,7}"构造成如上的二叉树。

当然你也可以根据满二叉树结点位置的标号规律来序列化，还可以根据先序遍历和中序遍历的结果来序列化。不对序列化之后的字符串进行约束，所以欢迎各种奇思妙想。

数据范围：节点数 n \le 100n≤100，树上每个节点的值满足 0 \le val \le 1500≤val≤150
要求：序列化和反序列化都是空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n)

### 思路

1. 就是一个遍历吧.加上分隔符.但问题来了,要区分没有子树的情况,写一个层次遍历吧.用队列.序列化容易想明白,但de serialize 确实不易.

de serialize 和serialize用的遍历应该是一样的,然后deserialize用一个全局变量记录遍历的顺序.
```java
import java.util.*;
/*
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class Solution {
    StringBuilder sb = new StringBuilder ();
    
    String Serialize(TreeNode root) {
        // # : null; ; : seperate.
        if (root == null) {
            sb.append("#;");
            return sb.toString();
        }
        sb.append(root.val + ";");
        Serialize(root.left);
        Serialize(root.right);
        return sb.toString();
        
    }
    
    int index = -1;
    TreeNode Deserialize(String str) {
       TreeNode root = new TreeNode(0);
        if (str == null || str.length() == 0) return null;
        String[] split = str.split(";");
        root = DeserializeCore(split);
        return root;
    }
    
    private TreeNode DeserializeCore(String[] split) {
        index ++;
        if (!split[index].equals("#")) {
            TreeNode root = new TreeNode(Integer.parseInt(split[index]));
            root.left = DeserializeCore(split);
            root.right = DeserializeCore(split);
            return root;
        }
        return null;
        
    }
}
```

## JZ38 字符串的排列

输入一个长度为 n 字符串，打印出该字符串中字符的所有排列，你可以以任意顺序返回这个字符串数组。
例如输入字符串ABC,则输出由字符A,B,C所能排列出来的所有字符串ABC,ACB,BAC,BCA,CBA和CAB。

### 思路
1. 字符串的题,没啥想法.
2. 回溯法.交换一下,递归调用排后面,交换回来,继续循环.


```java
import java.util.*;
public class Solution {
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> res = new ArrayList<> ();
        if (str == null) return res;
        
        char[] chr = str.toCharArray();
        HelpFun(chr, 0, res);
        Collections.sort(res);
        return new ArrayList<>(new HashSet<> (res));
       
    }
    
    private void HelpFun(char[] chr, int idx, ArrayList<String> res) {
        if (idx == chr.length - 1) {
            // to the bottom
            res.add(new String(chr));
        } else {
            for (int i = idx; i < chr.length; i++) {
                if (chr[idx] == chr[i] && i != idx) {
                    continue;
                    // the same character
                }
                swap(chr, idx, i);
                HelpFun(chr, idx+1, res);
                swap(chr, idx, i);
            }
        }
    }
    
    private void swap(char[] chr, int idx, int i) {
        char tmp = chr[idx];
        chr[idx] = chr[i];
        chr[i] = tmp;
    }
}

```
在我看来，这个应该不是回溯，而是一个先序遍历吧，从0开始，一直到底，然后再不停的换，但实话，感觉不太行。

## JZ39 数组中出现次数超过一半的数字

给一个长度为 n 的数组，数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
例如输入一个长度为9的数组[1,2,3,2,2,2,5,4,2]。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。

数据范围：n \le 50000n≤50000，数组中元素的值 0 \le val \le 100000≤val≤10000
要求：空间复杂度：O(1)O(1)，时间复杂度 O(n)O(n)

### 思路
1. 整一个hash map，加入，如果大于一半直接返回。但要求空间复杂度1
2. 遍历的时候记录两个值，一个是数字，一个是次数。

```java
public class Solution {
    public int MoreThanHalfNum_Solution(int [] array) {
        int key = array[0];
        int cnt = 1;
        for (int i = 1; i < array.length; i++) {
            if (array[i] == key) {
                cnt ++;
            } else {
                if (cnt == 0) {
                    key = array[i];
                    cnt = 1;
                } else {
                    cnt --;
                }
            }
        }
        return key;
    }
}
```

## JZ40 最小的K个数
给定一个长度为 n 的可能有重复值的数组，找出其中不去重的最小的 k 个数。例如数组元素是4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4(任意顺序皆可)。
数据范围：0\le k,n \le 100000≤k,n≤10000，数组中每个数的大小0 \le val \le 10000≤val≤1000
要求：空间复杂度 O(n)O(n) ，时间复杂度 O(nlogn)O(nlogn)

### 思路
1. 直接排序，反正时间也是NlogN。然会前面k个就可以。

```java
import java.util.*;

public class Solution {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> res = new ArrayList<> ();
        if (k == 0 || input == null) return res;
        
        Arrays.sort(input);
        for (int i = 0; i < k; i++) {
            res.add(input[i]);
        }
        return res;
    }
}
```

但自己写一个快排会吗？

2. 适合海量数组，利用最大堆，一个根节点大于子节点的二叉树。

```java
import java.util.*;

public class Solution {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> res = new ArrayList<> ();
        if (k == 0 || input == null) return res;
        
        int [] heap = new int[k];
        for (int i = 0; i < k; i++) {
            createHeap(heap, input, i); // create heap
        }
        
        for (int i = k; i < input.length; i++) {
            if (input[i] < heap[0]) {
                heap[0] = input[i]; // insert into heap
                heapAdj(heap); // adjust heap
            }
        }
        
        for (int i = 0; i < k; i++) {
            res.add(heap[i]);
        }
        return res;
    }
    
}
```

省略了堆的建造过程和调整过程，本质还是在输入的时候排序，回顾一下堆排序。

## JZ41 数据流中的中位数

如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。

### 思路
1. 在插入的时候就排序，同时记录数据总量的值。
2. 用一个最大堆保存中位数之前的，最小堆保存中位数之后的

```java
import java.util.*;

public class Solution {
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private PriorityQueue <Integer> maxHeap = new PriorityQueue<>(11, new Comparator<Integer> () {
        public int compare(Integer t1, Integer t2) {
            return t2.compareTo(t1);
        }
    });
    private int cnt = 0;
    
    public void Insert(Integer num) {
        cnt ++;
        int tmp;
        if ((cnt & 1) == 1) {
            // 奇数，保证了奇数进最大堆，偶数进最小堆
            if (!minHeap.isEmpty() && num > minHeap.peek()) {
                // 先判断这个数是否应该在最小堆 （既大于中位数的那堆）
                minHeap.offer(num);
                num = minHeap.poll();
            }
            maxHeap.offer(num);
        } else {
            // 偶数
            if (num < maxHeap.peek()) {
                maxHeap.offer(num);
                num = maxHeap.poll();
            }
            minHeap.offer(num);
            
        }
    }

    public Double GetMedian() {
        if (cnt == 0) return null;
        if ((cnt & 1) == 1) {
            return (double) maxHeap.peek();
        } else {
            return ((double) maxHeap.peek() + (double)minHeap.peek()) / 2;
        }
    }


}
```

## JZ42 连续子数组的最大和
输入一个长度为n的整型数组array，数组中的一个或连续多个整数组成一个子数组，子数组最小长度为1。求所有子数组的和的最大值。
要求:时间复杂度为 O(n)O(n)，空间复杂度为 O(n)O(n)
进阶:时间复杂度为 O(n)O(n)，空间复杂度为 O(1)O(1)

### 思路
1. 这个做过，没记错的话，是一个双指针，做出一个矩阵样的东西来的。错了
2. 记录一个最大值和当前值，如果比当前值小，说明前面和为负数，可以不要了。

```java
public class Solution {
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0) return 0;
        int sum = 0, left = 0, max = array[0];
        while (left < array.length) {
            sum += array[left];
            if (sum < array[left]) sum = array[left];
            if (sum > max) max = sum;
            left += 1;
        }
        return max;
        
    }
}
```

## JZ43 整数中1出现的次数
输入一个整数 n ，求 1～n 这 n 个整数的十进制表示中 1 出现的次数
例如， 1~13 中包含 1 的数字有 1 、 10 、 11 、 12 、 13 因此共出现 6 次

### 思路
1. 最直接简单的肯定就是遍历循环
2. 数学题
   1. 对于任意一位数字，该位产生的1有几种情况，如果前面还有数字那就是前面的 * 对应的为数，like 12 210，在百位就是 12 * 100. 如果该位为0，那就这么多。如果该位=1，就还要加上后面的10。如果大于1，就是 前面的12+1 * 100.

```java
public class Solution {
    public int NumberOf1Between1AndN_Solution(int n) {
        int res = 0;
        int pre = 0;
        int after = 0;
        int now = 0;
        int i = 1;
        
        while ((n / i) != 0) {
            now = (n / i) % 10;
            pre = n / (i * 10);
            after = n - (n / i) * i;
            
            if (now == 0) {
                res = res + pre * i;
            } else if (now == 1) {
                res = res + pre * i + after + 1;
            } else {
                res = res + (pre + 1) * i;
            }
            i = i * 10;
        }
        
        return res;
    }
}
```

## JZ44 数字序列中某一位的数字
数字以 0123456789101112131415... 的格式作为一个字符序列，在这个序列中第 2 位（从下标 0 开始计算）是 2 ，第 10 位是 1 ，第 13 位是 1 ，以此类题，请你输出第 n 位对应的数字。

### 思路
1. 数学题
   1. 计算n位数占多少字符，1->10, 2-> 90*2, 3->900 * 3
   2. 先确定在哪个区间，然后逐个去找。


```java
import java.util.*;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 
     * @param n int整型 
     * @return int整型
     */
    public int findNthDigit (int n) {
        // write code here
        if (n < 10) {
            return n;
        }
        int i = 1;
        int upper = 10;
        while (n >= upper) {
            n = n - upper;
            i++;
            upper = 9 * (int)Math.pow(10, i-1) * i;
        }
        
        // 然后找到所在的i位数，前面减去的部分，再加上排在第几个 i位数
        int indexNum = (int)(Math.pow(10, i-1) + n / i);
        int re = n % i; // 在i位数的哪个位置
        String str = Integer.toString(indexNum);
        return str.charAt(re) - '0';
    }
}
```

## JZ45 把数组排成最小的数

输入一个非负整数数组numbers，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
例如输入数组[3，32，321]，则打印出这三个数字能排成的最小数字为321323。
1.输出结果可能非常大，所以你需要返回一个字符串而不是整数
2.拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0

### 思路
1. 没个新的数，有n-1个点可以插进去，但可以找到比第一个比首字母数字小的slot插进去。时间复杂度n^2
2. 排序。定义排序的方法。

```java
import java.util.ArrayList;
import java.util.*;

public class Solution {
    public String PrintMinNumber(int [] numbers) {
        if (numbers == null || numbers.length == 0) return "";
        int len = numbers.length;
        String[] str = new String[len];
        for (int i = 0; i < len; i++) {
            str[i] = numbers[i] + "";
        }
        Arrays.sort(str, new Comparator<String>() {
            public int compare(String a, String b) {
                return (a+b).compareTo(b+a);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(str[i]);
        }
        return sb.toString();
    }
}
```
用的思路2，很机智。学一下。几种排序又忘了怎么写了。


## JZ46 把数字翻译成字符串

有一种将字母编码成数字的方式：'a'->1, 'b->2', ... , 'z->26'。

现在给一串数字，返回有多少种可能的译码结果

数据范围：字符串长度满足 0 < n \le 900<n≤90
进阶：空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n)

### 思路
1. 显然是动态规划，写出状态转移方程。$y_{x+1} = (y_x + 1) + (y_{x - 1} + 1) * g_x$

```java
import java.util.*;


public class Solution {
    /**
     * 解码
     * @param nums string字符串 数字串
     * @return int整型
     */
    public int solve (String nums) {
        // write code here
        if (nums.equals("0") || nums.length() == 0) return 0;
        int len = nums.length();
        int[] cnt = new int[len];
        
        for (int i = len-1; i >= 0; i--) {
            if (i == len - 1) {
                if (nums.charAt(i) == '0') {
                    cnt[i] = 0;
                } else {
                    cnt[i] = 1;
                }
            } else {
                cnt[i] = cnt[i+1];
                if (canBeTwo(nums, i)) {
                    if (i == len - 2) {
                        cnt[i] += 1;
                    } else {
                        cnt[i] += cnt[i+2];
                    }
                }
            }
        }
        return cnt[0];
    }
    
    private boolean canBeTwo(String nums, int i) {
        int a = nums.charAt(i) - '0';
        int b = nums.charAt(i+1) - '0';
        if ((a*10 + b <= 26) && (a*10 + b >= 10)) {
            return true;
        }
        return false;
    }
}
```

## JZ47 礼物的最大价值
在一个m\times nm×n的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
如输入这样的一个二维数组，
[
[1,3,1],
[1,5,1],
[4,2,1]
]
那么路径 1→3→5→2→1 可以拿到最多价值的礼物，价值为12

### 思路
1. 动态规划，最后肯定是到最右下角。用一个矩阵记录，会了！

```java
import java.util.*;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 
     * @param grid int整型二维数组 
     * @return int整型
     */
    public int maxValue (int[][] grid) {
        // write code here
        int res = 0;
        if (grid.length == 0 || grid[0].length == 0) return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] cnt = new int[rows][cols];
        
        // 初始化边界
        for (int row = rows - 1; row >= 0; row--) {
            if (row == rows - 1) {
                cnt[row][cols-1] = grid[row][cols-1];
            } else {
                cnt[row][cols-1] = cnt[row+1][cols-1] + grid[row][cols-1];
            }
        }
        for (int col = cols - 2; col >= 0; col--) {
            cnt[rows-1][col] = cnt[rows-1][col+1] + grid[rows-1][col];
        }
        
        // 开始动规
        for (int row = rows-2; row >= 0; row--) {
            for (int col = cols-2; col >= 0; col--) {
                cnt[row][col] = grid[row][col] + Math.max(cnt[row+1][col],cnt[row][col+1]);
            }
        }
        return cnt[0][0];
    }
}
```

## JZ48 最长不含重复字符的子字符串

请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。

### 思路
1. 双指针，用一个hash map存现在有的值，
2. 动态规划。f(i)表示第i个字符结尾，不含重复字符的长度。

```java
import java.util.*;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 
     * @param s string字符串 
     * @return int整型
     */
    public int lengthOfLongestSubstring (String s) {
        
        // write code here
        if ( s == null || s.length() <= 0 || s == " ") return 0;
        int preLength = 0;
        int curLength = 0;
        int maxLength = 0;
        // 用来存储26个字母
        int[] pos = new int[26];
        for (int i = 0; i < 26; i++) {
            pos[i] = -1;
        }
        
        for (int i = 0; i < s.length(); i ++) {
            int letterNum = s.charAt(i) - 'a';
            if (pos[letterNum] < 0 || i - pos[letterNum] > preLength) {
                curLength = preLength + 1;
            } else {
                curLength = i - pos[letterNum];
            }
            
            pos[letterNum] = i;
            if (curLength > maxLength) maxLength = curLength;
            
            preLength = curLength;
        }
        
        return maxLength;
        
    }
}
```

## JZ49 丑数

把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第 n个丑数。

数据范围：0 \le n \le 20000≤n≤2000
要求：空间复杂度 O(n)O(n) ， 时间复杂度 O(n)O(n)

### 思路
1. 动态规划，一个数如果是丑数，那一定是之前的丑数的乘积。每一次只用分别乘上2，3，5.

```java
public class Solution {
    public int GetUglyNumber_Solution(int index) {
        if (index <= 0) return 0;
        int[] res = new int[index];
        
        res[0] = 1;
        int j1 = 0, j2 = 0, j3 = 0;
        
        for (int i = 1; i < index; i++) {
            res[i] = minTree(res[j1]*2, res[j2]*3, res[j3]*5);
            if (res[i] == res[j1]*2) {
                j1 += 1;
            }
            if (res[i] == res[j2]*3) {
                j2 += 1;
            } 
            if (res[i] == res[j3]*5){
                j3 += 1;
            }
        }
        return res[index-1];
    }
    
    private int minTree(int a, int b, int c) {
        if (b < a) {
            a = b;
        }
        if (c < a) {
            a = c;
        }
        return a;
    }
}
```

## JZ50 第一个只出现一次的字符

在一个长为 字符串中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）.（从0开始计数）

### 思路
1. Hash Map 存出现的次数
```java
public int FirstNotRepeatingChar(String str) {
        int key = 0;
        char code = '0';
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < str.length(); i++) {
            map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);
        }
        for (Map.Entry<Character, Integer> next : map.entrySet()) {
            if (next.getValue() == 1) {
                code = next.getKey();
                break;
            }
        }
        for (char chars : str.toCharArray()) {
            if (chars == code) {
                return key;
            }
            key++;
        }
        return -1;
    }
```

## JZ51 数组中的逆序对

在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P mod 1000000007

### 思路
1. 最直接的肯定是Brute Force，对每一个数字都比较一次。
```java
public class Solution {
    public int InversePairs(int [] array) {
        if (array.length == 0 || array == null) return 0;
        int res = 0;
        for (int i = 0; i < array.length; i++) {
            res += smallerThanNum(array, i);
        }
        return res % 1000000007; 
    }
    
    private int smallerThanNum(int[] array, int index) {
        int numOfSmaller = 0;
        for (int i = index + 1; i < array.length; i++) {
            if (array[i] < array[index]) {
                numOfSmaller++;
            }
        }
        return numOfSmaller;
    }
}
``` 
这个超时了，需要update。

如果需要不改变方法，的优化就是多加一个最大最小变量。

2. 归并排序，如果左边的i比右边的j大，说明有j-mid个逆序对。

```java
public class Solution {
    int cnt = 0;
    public int InversePairs(int [] array) {
        int[] tmp = new int[array.length];
        mergeSort(array, 0, array.length-1, tmp);
        return cnt;
    }
    
    private void mergeSort(int[] array, int start, int end, int[] tmp) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(array, start, mid, tmp);
            mergeSort(array, mid+1, end, tmp);
            merge(array, start, end, tmp);
        }

    }
    
    private void merge(int[] array, int start, int end, int[] tmp) {
        int mid = (start + end) / 2;
        int i = start;
        int j = mid + 1;
        int k = start;
        
        while (i <= mid && j <= end) {
            if (array[i] < array[j]) {
                tmp[k++] = array[i++];
            } else {
                tmp[k++] = array[j++];
                // 就是j 是有mid - i + 1这么多个比j大的数字
                cnt += mid - i + 1;
            }
        }
        cnt = cnt % 1000000007;
        
        while (i <= mid) {
            tmp[k++] = array[i++];
        }
        while (j <= end) {
            tmp[k++] = array[j++];
        }
        for (int t = start; t <= end; t++) {
            array[t] = tmp[t];
        }
    }
    
}
``` 


## JZ52 两个链表的第一个公共结点

输入两个无环的单向链表，找出它们的第一个公共结点，如果没有公共节点则返回空。（注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）

### 思路
1. Brute Force 遍历两个搜寻
2. 都先各自遍历一遍，用hashmap记录下值，如果值出现了一样的，那就可能是对应的点。还有一个特性，如果是相同的，那之后也会相同。那我们我不先反转链表呢。
3. 双指针，一个指针遍历list1 & list 2，一个遍历list2 & list1。这是为什么呢？如果有共同部分，设长度为x，list 1 要走a + x + b + x, list 2 要走b + x + a + x;他们最后会在最后的x之前相遇。

```java
public class Solution {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) return null;
        
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        while (p1 != p2) {
            p1 = (p1 != null) ? p1.next : pHead2;
            p2 = (p2 != null) ? p2.next : pHead1;
        }
        return p1;
    }
}
```

## JZ53 数字在升序数组中出现的次数

给定一个长度为 n 的非降序数组和一个非负数整数 k ，要求统计 k 在数组中出现的次数

### 思路
1. 非降序数组，二分查找找到第一个位置，然后前后搜就行。

```java
public class Solution {
    public int GetNumberOfK(int [] array , int k) {
        if (array.length == 0) return 0;
        
        int res = 0;
        int index = getKIndex(array, 0, array.length - 1, k);
        if (index == -1) return res;
        int i = index;
        while (i < array.length && array[i] == k) {
            res += 1;
            i++;
        }
        i = index - 1;
        while (i >= 0 && array[i] == k) {
            res += 1;
            i--;
        }
        return res;
    }
    
    private int getKIndex(int[] array, int start, int end, int k) {
        if (start > end) return -1;
        int mid = (start + end) / 2;
        if (array[mid] == k) {
            return mid;
        } else if (array[mid] < k) {
            return getKIndex(array, start, mid-1, k);
        } else {
            return getKIndex(array, mid+1, end, k);
        }
    }
}
```

2. 二分查找上下界

## JZ54 二叉搜索树的第k个节点
给定一棵结点数为n 二叉搜索树，请找出其中的第 k 小的TreeNode结点值。
1.返回第k小的节点值即可
2.不能查找的情况，如二叉树为空，则返回-1，或者k大于n等等，也返回-1
3.保证n个节点的值不一样

### 思路
1. 中序遍历，遍历一遍就是了。

```java
import java.util.*;

/*
 * public class TreeNode {
 *   int val = 0;
 *   TreeNode left = null;
 *   TreeNode right = null;
 *   public TreeNode(int val) {
 *     this.val = val;
 *   }
 * }
 */

public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 
     * @param proot TreeNode类 
     * @param k int整型 
     * @return int整型
     */
    private int cnt = 0;
    private int res = -1;
    public int KthNode (TreeNode proot, int k) {
        // write code here
        if (proot == null) return res;
        traverse(proot, k);
        return res;
    }
    
    private void traverse(TreeNode proot, int k) {
        if (proot == null) return;
        traverse(proot.left, k);
        cnt += 1;
        if (cnt == k) res = proot.val;
        traverse(proot.right, k);
    }
}
``` 