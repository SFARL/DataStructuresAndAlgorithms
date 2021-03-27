This is Weijian Feng's note for leetcode, using Java,including question, example, code and optimization.

# Leetcode in order

## 1. Two sum easy

### 题目
给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。

你可以按任意顺序返回答案。

### 示例
```
输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
```

### code1
```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i=0; i < nums.length; i++) {
            for (int j=i+1; j < nums.length; j++)
            if (nums[i] + nums[j] == target) {
                return new int[]{i, j}; 
            }
        }
        return new int[]{0, 0};

    }
}
```

执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
内存消耗：38.9 MB, 在所有 Java 提交中击败了6.24%的用户

分析：运行时间N^2，运行内存 N, 里层循环

### code 2

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] indexs = new int[2];

        // 创建一个map，存互补的num的index
        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer> ();

        for (int i=0; i < nums.length; i++) {
            // 判断是否存在互补对
            if (hash.containsKey(nums[i])){
                indexs[0] = i;
                indexs[1] = hash.get(nums[i]);
                return indexs; 
            }
            hash.put(target-nums[i], i);

        }
        return indexs;

    }
}
```

执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
内存消耗：38.2 MB, 在所有 Java 提交中击败了99.05%的用户

分析：运行时间N，运行内存 常数级

## 2. Add two numbers medium

### 题目
给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。

请你将两个数相加，并以相同形式返回一个表示和的链表。

你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

### 示例
```
输入：l1 = [2,4,3], l2 = [5,6,4]
输出：[7,0,8]
解释：342 + 465 = 807.
```

```
输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
输出：[8,9,9,9,0,0,0,1]
```

### code 1
``` java
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode returnNode = new ListNode(0);
        ListNode tempNode = returnNode;
        // 哨兵节点，存tempNode的前一个。
        ListNode tempNode2 = returnNode;
        
        // 当两个对应位都有值的时候
        while((l1 != null) || (l2 != null)) {
            int valL1;
            int valL2;
            if (l1 == null) {
                valL1 = 0;
            } else {
                valL1 = l1.val;
            }
            if (l2 == null) {
                valL2 = 0;
            } else {
                valL2 = l2.val;
            }

            int tmp = valL1 + valL2;
            if (tmp < 10) {
                tempNode2 = tempNode;
                tempNode.val += tmp;
                tempNode.next = new ListNode(0);
                tempNode = tempNode.next;
            } else {
                tempNode2 = tempNode;
                tempNode.val += tmp % 10;
                tempNode.next = new ListNode(1);
                tempNode = tempNode.next;
            }
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (tempNode.val == 0) tempNode2.next =null;
        return returnNode;
```

这个失败的，第二个示例过不了。
时间：N
空间：N，创建了一个两个链表和的链表。

### code 2
```java
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 非递归
        ListNode root = new ListNode(0);
        ListNode cursor = root;

        int carry = 0; // 用来记录进位的
        while (l1 != null || l2 != null || carry != 0) {
            int val1 = l1 != null ? l1.val : 0;
            int val2 = l2 != null ? l2.val : 0;
            int sumVal = val1 + val2 + carry;
            
            carry = sumVal / 10;
            ListNode sumNode = new ListNode(sumVal % 10);
            cursor.next = sumNode;
            cursor = cursor.next;

            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }

        return root.next;

    }
```
执行用时：2 ms, 在所有 Java 提交中击败了100.00%的用户
内存消耗：38.9 MB, 在所有 Java 提交中击败了45.99%的用户
时间：N
空间：N，创建了一个两个链表和的链表。

```java
            if (l1 != null) {
                t += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                t += l2.val;
                l2 = l2.next;
            }
            
```
换成这样，少两个整数，内存打败89%，但影响不大。

# Array topic

## 4. Median of Two Sorted Arrays Hard

### question
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays

### example 
```
Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.
```

```
Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
```

```
Input: nums1 = [], nums2 = [1]
Output: 1.00000
```

```
Input [3] [-2,-1]
Expected -1.00000
```

### code1

```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 先计算中位数数字，然后从头开始比较
        int length = nums1.length + nums2.length;
        int cursor1 = 0;
        int cursor2 = 0;
        
        if ((length % 2) != 0) {
            // 为奇数时只需要计算一个
            int medianIndex = length / 2;
            
            // 边界条件，一个为空
            if(nums1.length == 0) return nums2[medianIndex];
            if(nums2.length == 0) return nums1[medianIndex];
            
            
            while((cursor1 + cursor2 + 2) < medianIndex - 1) {
                if (nums1[cursor1] >= nums2[cursor2]) cursor2++;
                else cursor1++;
            }
            return Math.max(nums1[cursor1], nums2[cursor2]);
        } else {
            // 奇数时需要两个，记得比较cursor1 和 cursor2 + 1的值
            int medianIndex = length / 2;
            // 边界条件，一个为空
            if(nums1.length == 0) return (double) (nums2[medianIndex] + nums2[medianIndex - 1]) / 2;
            if(nums2.length == 0) return (double) (nums1[medianIndex] + nums1[medianIndex - 1]) / 2;
            
            while((cursor1 + cursor2 + 2) < medianIndex - 1) {
                if (nums1[cursor1] >= nums2[cursor2]) cursor2++;
                else cursor1++;
            }
            
            // 接下来，比较两个值，选择大的作为均值中小的索引，出bug了，超出索引，加个边界条件
            if(((cursor1 + 1) < nums1.length) && ((cursor2 + 1) <nums2.length)) return (double) (Math.max(nums1[cursor1], nums2[cursor2]) + Math.min(nums1[cursor1 + 1], nums2[cursor2 + 1])) / 2;
            else if ((cursor1 + 1) == nums1.length) return (double) (Math.max(nums1[cursor1], nums2[cursor2]) + nums2[cursor2 + 1]) / 2;
            else return (double) (Math.max(nums1[cursor1], nums2[cursor2]) + nums1[cursor1 + 1]) / 2;
        }
        
    }
}
```

边界条件很多，需要清楚把握住如何选择中位数。
时间复杂度：N
空间复杂度：常数

### code2
```java
public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // 确保m < n
        if (nums2.length < nums1.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        int m = nums1.length;
        int n = nums2.length;
        
        int iMin = 0;
        int iMax = m;
        int halfLen = (n + m + 1) / 2; // 因为是整数除法，所以加个1
        
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            
            if(i < iMax && nums2[j-1] > nums1[i]) {
                // 这时候i小了
                iMin = i + 1;
            } else if (i > iMin && nums1[i-1] > nums2[j]) {
                // i太大了
                iMax = i - 1;
            } else {
                // 此时i刚刚好？
                int maxLeft = 0;
                if (i == 0) maxLeft = nums2[j-1]; // 边界条件
                else if (j == 0) maxLeft = nums1[i-1];
                else maxLeft = Math.max(nums2[j-1], nums1[i-1]);
                
                if ( (m + n) % 2 == 1 ) { return maxLeft; }
                
                int minRight = 0;
                if (i == m) minRight = nums2[j];
                else if (j == n) minRight = nums1[i];
                else minRight = Math.min(nums2[j], nums1[i]);
                
                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
```
2ms 99%, 40mb 98%

空间常数项，时间log(n)，因为用了二分查找

核心思想是：
Dividing a set into two equal length subsets, that one subset is always greater than the other.
j = halflen - i，用二分查找找到符合条件的i，j

## 11. Container With Most Water Medium

### question
Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0). Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.

Notice that you may not slant the container.

### example 
```
Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
```

### code1
```java
    public int maxArea(int[] height) {
        int max = 0;
        for (int i=0; i < height.length; i++) {
            for (int j=i; j < height.length; j++) {
                int contain = (j - i) * Math.min(height[j], height[i]);
                if (contain > max) max = contain;
            }
        }
        return max;
        
    }
```

这是最直接的解法，循环遍历，时间复杂度$n^2$，空间常数。在leetcode超出运行时间了


### code2
```java
        int max = 0;
        int i = 0, j = height.length - 1;
        
        while (i != j) {
            int area = (j - i) * Math.min(height[j], height[i]);
            if (area > max) max = area;
            
            if(height[i] < height[j]) {
                // 这个时候需要选个大的做基底，所以j不动，i动
                i++;
            } else {
                j--;
            }
        }
        return max;
        
    }
```
2ms, 96%, 53mb, 7.86%

优化了查找的策略，从遍历转成了每次找大的作为底。时间n，空间也是常数啊。

## 15 3sum medium

### question

Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Notice that the solution set must not contain duplicate triplets.

### example 
```
Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
```

